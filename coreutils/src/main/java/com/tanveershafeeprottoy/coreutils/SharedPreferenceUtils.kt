package com.tanveershafeeprottoy.coreutils

import android.content.Context
import android.preference.PreferenceManager

object SharedPreferenceUtils {

    fun write(context: Context, key: String, value: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply()
    }

    fun write(context: Context, key: String, value: Int) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value).apply()
    }

    fun write(context: Context, key: String, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(key, value).apply()
    }

    fun read(context: Context, key: String, defaultValue: String): String? {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, defaultValue)
    }

    fun read(context: Context, key: String, defaultValue: Int): Int {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, defaultValue)
    }

    fun read(context: Context, key: String, defaultValue: Boolean): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, defaultValue)
    }
}