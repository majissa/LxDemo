package com.lx.lxdemo.activity;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lx.lxdemo.R;
import com.lx.lxdemo.bean.ApkInfo;
import com.lx.lxdemo.utils.ApkUtils;
import com.lx.lxlibrary.activity.BaseActivity;
import com.lzy.okhttpserver.download.DownloadInfo;
import com.lzy.okhttpserver.download.DownloadManager;
import com.lzy.okhttpserver.download.DownloadService;
import com.lzy.okhttpserver.listener.DownloadListener;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;

import java.io.File;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建人：LX
 * 创建日期：2016/9/21
 * 描述：
 */
public class LoadApkActivity extends BaseActivity {
    private String apkUrl = "http://aixiaoqu.me//apk/20160921/8e8505fc5bfe4e178f5a1bed29ba3fbe.apk";
    private ImageView iv_apk;
    private TextView tv_apkName;
    private Button btn_load;
    private ApkInfo apkInfo;
    private MyListener listener;
    private DownloadManager downloadManager;
    private DownloadInfo downloadInfo;
    private TextView tv_downloadSize;
    private TextView tv_netSpeed;
    private TextView tv_Progress;
    private ProgressDialog pbProgress;


    @Override
    protected void initView(View view) {
        iv_apk = (ImageView) view.findViewById(R.id.iv_apk);
        tv_apkName = (TextView) view.findViewById(R.id.tv_apkName);
        tv_downloadSize = (TextView) view.findViewById(R.id.tv_downloadSize);
        tv_netSpeed = (TextView) view.findViewById(R.id.tv_netSpeed);
        tv_Progress = (TextView) view.findViewById(R.id.tv_Progress);
        btn_load = (Button) view.findViewById(R.id.btn_load);
        pbProgress = new ProgressDialog(this);
        pbProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
        pbProgress.setIndeterminate(false);
    }

    @Override
    protected void initEvent() {
        btn_load.setOnClickListener(this);
    }

    @Override
    protected void initValue() {
        downloadManager = DownloadService.getDownloadManager(context);
        initApkInfo();
       /* if (downloadManager.getTaskByUrl(apkInfo.getUrl()) == null) {
            btn_load.setEnabled(true);
        } else {
            btn_load.setEnabled(false);
        }*/
    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_load_apk;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_load:
                ApkUtils.updateApk(context, apkUrl, "aixiaoqu");
//                filedown();
              /*  downloadInfo = downloadManager.getTaskByUrl(apkInfo.getUrl());
                listener = new MyListener();
                downloadManager.addTask(apkInfo.getUrl(), listener);*/
                break;
        }
    }

    private void filedown() {
        OkHttpUtils.get(apkUrl).tag(this).execute(new FileCallback("aixiaoqu.apk") {
            @Override
            public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                ApkUtils.install(LoadApkActivity.this, file);
            }

            @Override
            public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                refresgUi(currentSize, totalSize, progress, networkSpeed);
            }
        });
    }

    private void initApkInfo() {
        apkInfo = new ApkInfo();
        apkInfo.setName("叮当快药");
        apkInfo.setIconUrl("http://pic3.apk8.com/small2/14315954626414886.png");
        apkInfo.setUrl("http://aixiaoqu.me//apk/20160921/8e8505fc5bfe4e178f5a1bed29ba3fbe.apk");
    }

    private class MyListener extends DownloadListener {

        @Override
        public void onProgress(DownloadInfo downloadInfo) {
            refreshUi(downloadInfo);
        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {
            System.out.println("onFinish");
            Toast.makeText(LoadApkActivity.this, "下载完成:" + downloadInfo.getTargetPath(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {
            System.out.println("onError");
            if (errorMsg != null)
                Toast.makeText(LoadApkActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    }

    private void refresgUi(long currentSize, long totalSize, float progress, long networkSpeed) {
        tv_downloadSize.setText(currentSize + "/" + totalSize);
        String networkSpeeds = Formatter.formatFileSize(LoadApkActivity.this, networkSpeed);
        tv_netSpeed.setText(networkSpeeds + "/s");
        tv_Progress.setText((Math.round(progress * 10000) * 1.0f / 100) + "%");
        pbProgress.setTitle("正在下载");
        pbProgress.setMessage("请稍后...");
        pbProgress.setMax((int) totalSize);
        pbProgress.setProgress((int) currentSize);
        pbProgress.show();
    }

    private void refreshUi(DownloadInfo downloadInfo) {
        String downloadLength = Formatter.formatFileSize(LoadApkActivity.this, downloadInfo.getDownloadLength());
        String totalLength = Formatter.formatFileSize(LoadApkActivity.this, downloadInfo.getTotalLength());
        tv_downloadSize.setText(downloadLength + "/" + totalLength);
        String networkSpeed = Formatter.formatFileSize(LoadApkActivity.this, downloadInfo.getNetworkSpeed());
        tv_netSpeed.setText(networkSpeed + "/s");
        tv_Progress.setText((Math.round(downloadInfo.getProgress() * 10000) * 1.0f / 100) + "%");
        pbProgress.setTitle("正在下载");
        pbProgress.setMessage("请稍后...");
        pbProgress.setMax((int) downloadInfo.getTotalLength());
        pbProgress.setProgress((int) downloadInfo.getDownloadLength());
        pbProgress.show();
        switch (downloadInfo.getState()) {
            case DownloadManager.NONE:
//                download.setText("下载");
                break;
            case DownloadManager.DOWNLOADING:
//                download.setText("暂停");
                break;
            case DownloadManager.PAUSE:
//                download.setText("继续");
                break;
            case DownloadManager.WAITING:
//                download.setText("等待");
                break;
            case DownloadManager.ERROR:
//                download.setText("出错");
                break;
            case DownloadManager.FINISH:
                pbProgress.cancel();
                ApkUtils.install(LoadApkActivity.this, new File(downloadInfo.getTargetPath()));
                break;
        }
    }
}
