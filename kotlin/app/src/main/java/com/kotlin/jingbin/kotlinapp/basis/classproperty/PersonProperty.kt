package com.kotlin.jingbin.kotlinapp.basis.classproperty

/**
 * Created by jingbin on 2018/11/18.
 */

class PersonProperty {

    // 只读属性：生成一个字段和一个简单的getter
    val name: String = "kotlin_hahaha"

    // 可写属性：一个字段、一个getter和一个setter
    var isMarried: Boolean = false

    fun set() {
        isMarried = true
    }
}
