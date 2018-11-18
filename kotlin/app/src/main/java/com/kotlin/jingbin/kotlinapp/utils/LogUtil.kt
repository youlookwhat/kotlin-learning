package com.kotlin.jingbin.kotlinapp.utils

import android.util.Log

/**
 * Created by jingbin on 2018/11/18.
 * 静态方法使用object
 */

object LogUtil {

    fun e(message: Boolean) {
        Log.e("jing", message.toString())
    }

    fun e(message: Int) {
        Log.e("jing", message.toString())
    }

    fun e(message: String) {
        Log.e("jing", message)
    }

    fun e(tag: String, message: String) {
        Log.e(tag, message)
    }
}
