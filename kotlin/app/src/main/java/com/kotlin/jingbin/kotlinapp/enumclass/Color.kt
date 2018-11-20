package com.kotlin.jingbin.kotlinapp.enumclass

/**
 * Created by jingbin on 2018/11/20.
 * 带属性的枚举类
 * LogUtil.e(Color.BULE.rgb())
 */
enum class Color(
        // 声明枚举常量的属性
        val r: Int, val g: Int, val b: Int) {
    // 在每一个常量创建的时候指定属性值
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    WELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BULE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIILET(238, 130, 238);// 分号

    fun rgb() = (r * 256 + g) * 256 + b
}

