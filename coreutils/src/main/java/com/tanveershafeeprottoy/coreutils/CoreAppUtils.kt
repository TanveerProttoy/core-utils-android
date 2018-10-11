package com.tanveershafeeprottoy.coreutils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

/**
 * @author Tanveer Shafee Prottoy
 */
object CoreAppUtils {

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
                        onPositiveClickListener: DialogInterface.OnClickListener,
                        onNegativeClickListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context).setTitle(title).setMessage(msg)
                .setPositiveButton(positiveBtnTxtId, onPositiveClickListener)
                .setNegativeButton(negativeBtnTxtId, onNegativeClickListener)
                .create().show()
    }

    fun createSpinnerAdapter(context: Context, stringArrayRes: Int, spinner: Spinner): ArrayAdapter<CharSequence> {
        return ArrayAdapter.createFromResource(context, stringArrayRes,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
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

    fun isProgressbarVisible(progressbarHolder: View): Boolean {
        if(progressbarHolder.visibility == View.VISIBLE) {
            return true
        }
        return false
    }
}