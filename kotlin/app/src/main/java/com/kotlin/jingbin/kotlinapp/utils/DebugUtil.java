package com.kotlin.jingbin.kotlinapp.utils;

import android.util.Log;

public class DebugUtil {

    public static void e(int s) {
        e(String.valueOf(s));
    }

    public static void e(String s) {
        Log.e("jingbin", s);
    }

}
