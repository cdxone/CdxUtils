package example.cdx.com.utilslibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * 1、可以跳转到设置界面中子界面的工具类
 * 2、设置界面中的子元素界面非常多，下面的工具类只是其中的一部分，都是通过Settings去控制
 */
public class SettingUtils {


    /**
     * 跳转系统的辅助功能界面
     * @param context
     */
    public static void startAccessibilityActivity(Context context){
        Intent intent =  new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 开启添加添加账户界面
     * @param context
     */
    public static void startAddAccountActivity(Context context){
        Intent intent =  new Intent(Settings.ACTION_ADD_ACCOUNT);
        context.startActivity(intent);
    }


    /**
     * 飞行模式，无线网和网络设置界面
     * @param context
     */
    public static void startAirplaneModeActivity(Context context) {
        Intent intent =  new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转到APN界面
     * @param context
     */
    public static void startApnActivity(Context context) {
        Intent intent =  new Intent(Settings.ACTION_APN_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转到应用程序信息界面
     * @param context
     */
    public static void startApplicationDetailSActivity(Context context) {
        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent =  new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,packageURI);
        context.startActivity(intent);
    }

    /**
     * 跳转到跳转开发人员选项界面
     * @param context
     */
    public static void startApplicationDevelopmentTActivity(Context context) {

        Intent intent =  new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转应用程序列表界面
     * @param context
     */
    public static void startApplicationActivity(Context context) {
        Intent intent =  new Intent(Settings.ACTION_APPLICATION_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转系统的蓝牙设置界面
     * @param context
     */
    public static void startBlueToothActivity(Context context){
        Intent intent =  new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转日期时间设置界面
     * @param context
     */
    public static void startDataRoamingActivity(Context context){
        Intent intent =  new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转手机状态界面
     * @param context
     */
    public static void startDeviceInfoActivity(Context context){
        Intent intent =  new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 跳转手机显示界面
     */
    public static void startDisplayActivity(Context context){
        Intent intent =  new Intent(Settings.ACTION_DISPLAY_SETTINGS);
        context.startActivity(intent);
    }

}
