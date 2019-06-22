package com.kotlin.jingbin.kotlinapp.utils

import android.util.Log

/**
 * Created by jingbin on 2018/11/18.
 * 静态方法使用object
 */

object LogUtil {

    private const val TAG = "---------------jing"

    fun e(message: Boolean) {
        Log.e(TAG, message.toString())
    }

    fun e(message: Int) {
        Log.e(TAG, message.toString())
    }

    fun e(message: String) {
        Log.e(TAG, message)
    }

    fun e(message: Any) {
        Log.e(TAG, message.toString())
    }

    fun e(tag: String, message: String) {
        Log.e(tag, message)
    }
}
