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

    fun write(context: Context, key: String, value: Long) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(key, value).apply()
    }

    fun readString(context: Context, key: String): String? {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, "")
    }

    fun readInt(context: Context, key: String): Int {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, -1)
    }

    fun readBoolean(context: Context, key: String): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false)
    }

    fun readLong(context: Context, key: String): Long {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(key, (-1).toLong())
    }
}