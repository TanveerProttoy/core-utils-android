package com.tanveershafeeprottoy.coreutils

import android.text.TextUtils
import android.util.Patterns

object ValidationUtils {

    fun isValidInput(string: String): Boolean {
        if(TextUtils.isEmpty(string)) {
            return false
        }
        return true
    }

    fun areValidInputs(vararg strings: String): Boolean {
        for(string in strings) {
            if(TextUtils.isEmpty(string)) {
                return false
            }
        }
        return true
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}