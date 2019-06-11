package com.kotlin.jingbin.kotlinapp.function;

import com.kotlin.jingbin.kotlinapp.utils.DebugUtil;

public class SetOfJava {

    public void start() {
        String f = "12.345-6.A";

//        String[] split = f.split(".");
//        String[] split = f.split("\\.");
        String[] split = f.split("\\\n");
//        String[] split = f.split("\\\\n");

        DebugUtil.e(split.length);
        for (String d : split) {
            DebugUtil.e(d);
        }
    }
}
