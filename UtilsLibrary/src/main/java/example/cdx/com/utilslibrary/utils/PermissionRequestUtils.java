package example.cdx.com.utilslibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionActivity;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * 权限申请工具
 */
public class PermissionRequestUtils {

    private PermissionRequestUtils() {
    }

    /**
     * 申请权限
     * @param activity 上下文
     * @param callback 回调
     * @param permissions 权限数组，或者单个权限
     */
    //申请权限,默认跳转设置
    public static void requestPermission(Context activity, PermissionCallback callback, String... permissions) {
        requestPermission(activity, callback, true, permissions);
    }

    /**
     * 申请权限
     * @param activity 上下文
     * @param callback 回调
     * @param goSeting 是否跳转设置界面
     * @param permissions 权限数组，或者单个权限
     */
    //申请权限
    public static void requestPermission(final Context activity,  final PermissionCallback callback, final boolean goSeting, String... permissions) {
        if (Build.VERSION.SDK_INT < 23) {
            //申请成功
            callback.onSuccessful();
            return;
        }
        AndPermission.with(activity)
                .permission(permissions)
                .rationale(new Rationale() {
                    @Override
                    public void showRationale(Context context, List<String> permissions, RequestExecutor executor) {
                        //继续执行申请权限的操作
                        executor.execute();
                    }
                })
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        //申请成功
                        callback.onSuccessful();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        // 权限申请失败回调。
                        callback.onFailure();
                        if (goSeting && AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                            //跳转设置
                            showSetting(activity, permissions);
                        }
                    }
                })
                .start();
    }

    /**
     * 检查权限
     * @param context 上下文
     * @param permission 权限
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 权限申请结果的逻辑
     */
    public interface PermissionCallback {
        void onSuccessful();
        void onFailure();
    }

    /**
     * 跳转到设置界面
     * @param context
     * @param permissions
     */
    public static void showSetting(final Context context, List<String> permissions) {

        Intent intent = new Intent();
        intent.setClass(context, PermissionActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("permissions", (Serializable) permissions);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
