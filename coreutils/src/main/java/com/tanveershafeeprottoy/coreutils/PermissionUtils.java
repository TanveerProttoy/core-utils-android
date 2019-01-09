package com.tanveershafeeprottoy.coreutils;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * @author Tanveer Shafee Prottoy
 */

public class PermissionUtils {

    public static boolean checkSmsPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermissions(AppCompatActivity appCompatActivity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(appCompatActivity, permissions, requestCode);
    }

    public static void requestPermissions(Fragment fragment, String[] permissions, int requestCode) {
        fragment.requestPermissions(permissions, requestCode);
    }
}
