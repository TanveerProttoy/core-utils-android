package com.tanveershafeeprottoy.coreutils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * @author Tanveer Shafee Prottoy
 */
public class CoreUtils {

    private static Intent createEmailIntent(String[] addresses) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"));// only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        return intent;
    }

    private static Intent createCameraIntent(Context context, String authority, File file) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(context, authority, file));
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        return intent;
    }

    public static void showToastMessage(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void showToastMessage(Context context, String message, int duration, int gravity) {
        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    public static void showAlertDialog(Context context, String title, String msg, int positiveBtnTxtId) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
                .setPositiveButton(positiveBtnTxtId, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

    public static void showAlertDialog(Context context, String title, String msg,
                                       int positiveBtnTxtId, int negativeBtnTxtId,
                                       DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context).setTitle(title).setMessage(msg)
                .setPositiveButton(positiveBtnTxtId, onClickListener)
                .setNegativeButton(negativeBtnTxtId, onClickListener)
                .create().show();
    }

    public static void startActivityForResult(AppCompatActivity appCompatActivity, String action, int requestCode, String errorString) {
        try {
            appCompatActivity.startActivityForResult(new Intent(action), requestCode);
        }
        catch(ActivityNotFoundException a) {
            showToastMessage(appCompatActivity.getApplicationContext(), errorString, Toast.LENGTH_LONG);
        }
    }

    public static void startActivityForResult(Fragment fragment, String action, int requestCode, String errorString) {
        try {
            fragment.startActivityForResult(new Intent(action), requestCode);
        }
        catch(IllegalStateException i) {
            showToastMessage(fragment.getActivity().getApplicationContext(), errorString, Toast.LENGTH_LONG);
        }
    }

    public static void startActivityForResult(AppCompatActivity appCompatActivity, String action, Uri uri, int requestCode) {
        appCompatActivity.startActivityForResult(new Intent(action, uri), requestCode);
    }

    public static void startActivityForResult(Fragment fragment, String action, Uri uri, int requestCode) {
        fragment.startActivityForResult(new Intent(action, uri), requestCode);
    }

    public static void startActivityWithImplicitIntent(AppCompatActivity appCompatActivity, String action, Uri uri) {
        appCompatActivity.startActivity(new Intent(action, uri));
    }

    public static void startActivityWithImplicitIntent(Fragment fragment, String action, Uri uri) {
        fragment.startActivity(new Intent(action, uri));
    }

    public static void startActivityWithEmailIntent(AppCompatActivity appCompatActivity, String[] addresses) {
        appCompatActivity.startActivity(createEmailIntent(addresses));
    }

    public static void startActivityWithEmailIntent(Fragment fragment, String[] addresses) {
        fragment.startActivity(createEmailIntent(addresses));
    }

    public static void startActivityWithCameraIntent(File file, AppCompatActivity appCompatActivity, String authority, int requestCode, String errorString) {
        try {
            appCompatActivity.startActivityForResult(createCameraIntent(appCompatActivity, authority, file), requestCode);
        }
        catch(ActivityNotFoundException a) {
            showToastMessage(appCompatActivity.getApplicationContext(), errorString, Toast.LENGTH_LONG);
        }
    }

    public static void startActivityWithCameraIntent(File file, Fragment fragment, String authority, int requestCode, String errorString) {
        FragmentActivity fragmentActivity = fragment.getActivity();
        try {
            fragment.startActivityForResult(createCameraIntent(fragmentActivity, authority, file), requestCode);
        }
        catch(ActivityNotFoundException a) {
            showToastMessage(fragmentActivity.getApplicationContext(), errorString, Toast.LENGTH_LONG);
        }
    }

    public static boolean isViewVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    public static void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public static void hideView(View view) {
        view.setVisibility(View.GONE);
    }

    /**
     * @param context must be ApplicationContext
     **/
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }
        catch(PackageManager.NameNotFoundException n) {
            return "";
        }
    }

    public static void setEditTextError(EditText editText, String errorString) {
        editText.setError(errorString);
        editText.requestFocus();
    }

    public static SpannableString createSpannableString(String string, int startIndex, int endIndex,
                                                        float proportion, int color) {
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new RelativeSizeSpan(proportion), startIndex, endIndex, 0); // set size
        spannableString.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, 0); // set color
        return spannableString;
    }
}
