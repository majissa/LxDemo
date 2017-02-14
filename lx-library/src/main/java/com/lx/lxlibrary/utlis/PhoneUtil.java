package com.lx.lxlibrary.utlis;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.lx.lxlibrary.interfaces.OnContactObtainListener;
import com.lx.lxlibrary.log.Logger;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class PhoneUtil {

    /**
     * Get the device ID
     *
     * @param context
     * @return
     */
    public static String getAuid(Context context) {
        String auid = Settings.Secure.ANDROID_ID + "android_id";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            try {
                auid = tm.getDeviceId();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
            tm = null;
        }
        if (((auid == null) || (auid.length() == 0)) && (context != null))
            auid = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if ((auid == null) || (auid.length() == 0))
            auid = null;
        return auid;
    }

    /**
     * 获取Android本机IP地址
     *
     * @return
     */
    private String getIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*
     * 获取手机MAC地址
     */
    public static String getMacAddress(Context context) {
        String result = "";
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        result = wifiInfo.getMacAddress();
        return result;
    }


    /**
     * 是否包含电话号码
     *
     * @param context     上下文
     * @param phoneNumber 手机号码
     * @return
     */
    public static boolean hasPhoneNumber(Context context, String phoneNumber) {
        Cursor cursor = context.getContentResolver().query(Uri.withAppendedPath(
                PhoneLookup.CONTENT_FILTER_URI, phoneNumber), new String[]{
                PhoneLookup._ID,
                PhoneLookup.NUMBER,
                PhoneLookup.DISPLAY_NAME,
                PhoneLookup.TYPE, PhoneLookup.LABEL}, null, null, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 发短信，调用系统的
     *
     * @param context     上下文
     * @param phoneNumber 手机号码
     * @param content     短信内容，可为空
     */
    public static void sendSMS(Context context, String phoneNumber, String content) {
        Uri uri = Uri.parse("smsto://" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
    }

    /**
     * 打电话
     *
     * @param context     上下文
     * @param phoneNumber 电话号码
     */
    public static void callPhone(Context context, String phoneNumber) {
        Intent phoneIntent = new Intent("android.intent.action.CALL",
                Uri.parse("tel:" + phoneNumber));
        context.startActivity(phoneIntent);
    }

    /**
     * 打开该应用的设置页面
     *
     * @param context
     * @param packageName 该应用的包名
     */
    public static void openApplicationSetting(Context context, String packageName) {
        Intent i = new Intent("android.settings.ACTION_APPLICATION_SETTINGS ");
        String pkg = "com.android.settings";
        String cls = "com.android.settings.applications.InstalledAppDetails";
        i.setComponent(new ComponentName(pkg, cls));
        i.setData(Uri.parse("package:" + packageName));
        context.startActivity(i);
    }

    /**
     * 打开手机通讯录
     *
     * @param context
     */
    public static void openContact(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        ((Activity) context).startActivityForResult(intent, 0);
    }

    public static void contactOnActivityResult(int requestCode, int resultCode, Intent data, Activity activity, OnContactObtainListener listener) {
        if (resultCode == Activity.RESULT_OK) {
            ContentResolver reContentResolverol = activity.getContentResolver();
            Uri contactData = data.getData();
            @SuppressWarnings("deprecation")
            Cursor cursor = activity.managedQuery(contactData, null, null, null, null);
            cursor.moveToFirst();
            String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                    null,
                    null);
            if (phone.moveToNext()) {
                String usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Logger.dLi("username---->" + username);
                Logger.dLi("usernumber---->" + usernumber);
                listener.onSuccess(username, usernumber);
            } else {
                listener.onFailure();
            }
        }
    }
}
