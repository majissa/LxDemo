package com.lx.lxlibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lx.lxlibrary.R;

import java.io.IOException;

/**
 * Created by 李响
 * 创建日期 2016/11/18
 * <p>
 * 播放声音的TextView
 * <p>
 * 描述：1.MediaPlayer的生命周期图（idle状态-->intialized状态--->preparing状态-->prepared状态-->start状态-->stop状态或者paused状态-->playbackCompleleted状态）
 * 2。常用方法reset() Resets the MediaPlayer to its uninitialized state. After calling
 * this method, you will have to initialize it again by setting the
 * data source and calling prepare().
 * release():确定MediaPlayer不在使用的时候释放
 * stop()
 * pause()
 * start()等方法
 */
public class MediaPlayerTextView extends TextView {

    private MediaPlayer mPlayer;

    private boolean isPause;
    private boolean isStart;
    private String mfilePath;
    private onMediaPlayerListener onMediaPlayerListener;
    private String pauseText;
    private String resumeText;

    public MediaPlayerTextView(Context context) {
        super(context);
        init(context, null);
    }

    public MediaPlayerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MediaPlayerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MediaPlayerTextView);
        pauseText = a.getString(R.styleable.MediaPlayerTextView_pauseText);
        resumeText = a.getString(R.styleable.MediaPlayerTextView_resumeText);
        a.recycle();
    }

    public void init(String filePath, final onMediaPlayerListener onMediaPlayerListener) {
        this.onMediaPlayerListener = onMediaPlayerListener;
        this.mfilePath = filePath;
        // TODO Auto-generated method stub
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    onMediaPlayerListener.OnPreparedListener(mp);
                }
            });
            //保险起见，设置报错监听
            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // TODO Auto-generated method stub
                    setText(pauseText);
                    mPlayer.reset();
                    onMediaPlayerListener.onError(mp, what, extra);
                    return false;
                }
            });
        } else {
            mPlayer.reset();//重置
        }
    }

    public void playSound() {
        playSound(mfilePath);
    }

    public void playSound(String mfilePath) {
        if (mPlayer != null && !mPlayer.isPlaying()) {
            try {
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (!TextUtils.isEmpty(pauseText)) {
                            setText(pauseText);
                        }
                        onMediaPlayerListener.onCompletion(mp);
                    }
                });
                mPlayer.setDataSource(mfilePath);
                mPlayer.prepare();
                mPlayer.start();
                if (!TextUtils.isEmpty(resumeText)) {
                    setText(resumeText);
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        isStart = true;
    }

    public void stopSound() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
            if (!TextUtils.isEmpty(pauseText)) {
                setText(pauseText);
            }
        }
    }

    public void restartSound() {
        if (mPlayer != null && !mPlayer.isPlaying() && isStart) {
            mPlayer.seekTo(0);
            mPlayer.start();
            if (!TextUtils.isEmpty(resumeText)) {
                setText(resumeText);
            }
        }
    }

    //停止函数
    public void pauseSound() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
            if (!TextUtils.isEmpty(pauseText)) {
                setText(pauseText);
            }
            isPause = true;
        }
    }

    //继续
    public void resumeSound() {

        if (mPlayer != null && isPause) {
            mPlayer.start();
            if (!TextUtils.isEmpty(resumeText)) {
                setText(resumeText);
            }
            isPause = false;
        }
    }

    public void release() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
        isStart = false;
    }

    public void reset() {
        if (mPlayer != null) {
            mPlayer.reset();
        }
        isStart = false;
    }

    public boolean isPlaying() {
        if (mPlayer != null) {
            return mPlayer.isPlaying();
        } else {
            return false;
        }
    }

    public boolean isStart() {
        return isStart;
    }

    /**
     * 没有时间就放回-1
     *
     * @return
     */
    public int getDuration() {
        if (mPlayer != null) {
            return mPlayer.getDuration();
        } else {
            return -1;
        }
    }

    public interface onMediaPlayerListener {

        void OnPreparedListener(MediaPlayer mp);

        void onCompletion(MediaPlayer mediaPlayer);

        void onError(MediaPlayer mp, int what, int extra);
    }
}
