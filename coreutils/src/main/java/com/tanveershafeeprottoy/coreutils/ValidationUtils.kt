package com.tanveershafeeprottoy.coreutils

import android.text.TextUtils
import android.util.Patterns

object ValidationUtils {

    fun isValidString(string: String): Boolean {
        if(TextUtils.isEmpty(string)) {
            return false
        }
        return true
    }

    fun areValidStrings(vararg strings: String): Boolean {
        for(string in strings) {
            if(TextUtils.isEmpty(string)) {
                return false
            }
        }
        return true
    }

    fun isValidInteger(integer: String): Boolean {
        return integer.toIntOrNull() != null
    }

    fun isValidDouble(double: String): Boolean {
        return double.toDoubleOrNull() != null
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}