package com.kotlin.jingbin.kotlinapp.classproperty

/**
 * Created by jingbin on 2018/11/18.
 * 自定义访问器
 * 也可以使用函数返回，实现和性能没有差别，唯一的差别是可读性
 * 通常来说：
 * 如果描述的是类的特征(属性)，应该把它声明成属性。
 */
class Rectangle(val height: Int, val width: Int) {

    // 函数表达式 可以赋值
    val isSquare: Boolean
    // 声明属性的getter
        get() {
            return height == width
        }

}