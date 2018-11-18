package com.kotlin.jingbin.kotlinapp.utils

import android.util.Log

/**
 * Created by jingbin on 2018/11/18.
 */

class LogUtil {

    fun e(message: String) {
        Log.e("jing", message)
    }

    fun e(tag: String, message: String) {
        Log.e(tag, message)
    }
}
