package com.tanveershafeeprottoy.coreutils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File

/**
 * @author Tanveer Shafee Prottoy
 */
object AppUtils {

    fun showToastMessage(context: Context?, message: String, duration: Int) {
        Toast.makeText(context, message, duration).show()
    }

    fun showAlertDialog(context: Context, title: String?, msg: String?, positiveBtnTxtId: Int) {
        AlertDialog.Builder(context).setTitle(title).setMessage(msg)
                .setPositiveButton(positiveBtnTxtId) { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
    }

    fun showAlertDialog(context: Context, title: String?, msg: String?,
                        positiveBtnTxtId: Int, negativeBtnTxtId: Int,
                        onClickListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context).setTitle(title).setMessage(msg)
                .setPositiveButton(positiveBtnTxtId, onClickListener)
                .setNegativeButton(negativeBtnTxtId, onClickListener)
                .create().show()
    }

    /**
     * @param context must be either AppCompatActivity, or support Fragment
     */
    fun startActivityForResult(context: Context, action: String, requestCode: Int, errorString: String) {
        try {
            when(context) {
                is AppCompatActivity -> {
                    context.startActivityForResult(Intent(action), requestCode)
                }
                is Fragment -> {
                    context.startActivityForResult(Intent(action), requestCode)
                }
                else -> {
                    throw TypeCastException()
                }
            }
        }
        catch(a: ActivityNotFoundException) {
            showToastMessage(context.applicationContext, errorString, Toast.LENGTH_LONG)
        }
    }

    /**
     * @param context must be either AppCompatActivity, or support Fragment
     * @throws TypeCastException
     */
    fun startActivityForResult(context: Context, action: String, uri: Uri, requestCode: Int) {
        when(context) {
            is AppCompatActivity -> {
                context.startActivityForResult(Intent(action, uri), requestCode)
            }
            is Fragment -> {
                context.startActivityForResult(Intent(action, uri), requestCode)
            }
            else -> {
                throw TypeCastException()
            }
        }
    }

    /**
     * @param context must be either AppCompatActivity, or support Fragment
     * @throws TypeCastException
     */
    fun startActivityWithImplicitIntent(context: Context, action: String, uri: Uri) {
        when(context) {
            is AppCompatActivity -> {
                context.startActivity(Intent(action, uri))
            }
            is Fragment -> {
                context.startActivity(Intent(action, uri))
            }
            else -> {
                throw TypeCastException()
            }
        }
    }

    /**
     * @param context must be either AppCompatActivity, or support Fragment
     * @throws TypeCastException
     */
    fun startActivityWithEmailIntent(context: Context, addresses: Array<String>) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        when(context) {
            is AppCompatActivity -> {
                context.startActivity(intent)
            }
            is Fragment -> {
                context.startActivity(intent)
            }
            else -> {
                throw TypeCastException()
            }
        }
    }

    /**
     * @param context must be either AppCompatActivity, or support Fragment
     * @throws TypeCastException
     */
    fun startActivityWithCameraIntent(file: File, context: Context, authorities: String, requestCode: Int, errorString: String) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val outputUri = FileProvider.getUriForFile(context, authorities, file)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        try {
            when(context) {
                is AppCompatActivity -> {
                    context.startActivityForResult(intent, requestCode)
                }
                is Fragment -> {
                    context.startActivityForResult(intent, requestCode)
                }
                else -> {
                    throw TypeCastException()
                }
            }
        }
        catch(a: ActivityNotFoundException) {
            showToastMessage(context.applicationContext, errorString, Toast.LENGTH_LONG)
        }
    }

    fun isViewVisible(view: View): Boolean {
        return view.visibility == View.VISIBLE
    }

    fun showView(view: View) {
        view.visibility = View.VISIBLE
    }

    fun hideView(view: View) {
        view.visibility = View.GONE
    }

    /**
     * @param context must be ApplicationContext
     */
    fun getVersionName(context: Context): String {
        return try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        }
        catch(e: PackageManager.NameNotFoundException) {
            ""
        }
    }

    fun setEditTextError(editText: EditText, errorString: String) {
        editText.error = errorString
        editText.requestFocus()
    }

    fun createSpannableString(string: String, start: Int, end: Int, proportion: Float, color: Int): SpannableString {
        val spannableString = SpannableString(string)
        spannableString.setSpan(RelativeSizeSpan(proportion), start, end, 0) // set size
        spannableString.setSpan(ForegroundColorSpan(color), start, end, 0)// set color
        return spannableString
    }
}