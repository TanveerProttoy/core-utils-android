package com.tanveershafeeprottoy.coreutils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.tanveershafeeprottoy.coreutils.Constants.PERMISSION_GRANTED_CODE

object PermissionUtils {

    fun checkSmsPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, Constants.PERMISSION_RECEIVE_SMS) == PERMISSION_GRANTED_CODE
    }

    fun requestPermissions(appCompatActivity: AppCompatActivity, permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(appCompatActivity, permissions, requestCode)
    }

    fun requestPermissions(fragment: Fragment, permissions: Array<String>, requestCode: Int) {
        fragment.requestPermissions(permissions, requestCode)
    }
}