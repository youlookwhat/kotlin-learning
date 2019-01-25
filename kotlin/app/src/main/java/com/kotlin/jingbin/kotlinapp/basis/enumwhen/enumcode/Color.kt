package com.kotlin.jingbin.kotlinapp.basis.enumwhen.enumcode

/**
 * Created by jingbin on 2018/11/20.
 * 带属性的枚举类
 * LogUtil.e(Color.BLUE.rgb())
 */
enum class Color(
        // 声明枚举常量的属性
        val r: Int, val g: Int, val b: Int) {
    // 在每一个常量创建的时候指定属性值
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);// 分号

    fun rgb() = (r * 256 + g) * 256 + b
}

