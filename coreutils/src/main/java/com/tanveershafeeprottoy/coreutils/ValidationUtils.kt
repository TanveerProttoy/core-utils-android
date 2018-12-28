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

    fun isValidInteger(integer: String): Int? {
        return integer.toIntOrNull()
    }

    fun isValidDouble(double: String): Double? {
        return double.toDoubleOrNull()
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}