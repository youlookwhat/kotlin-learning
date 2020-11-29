package com.kotlin.jingbin.kotlinapp

import com.kotlin.jingbin.kotlinapp.typesystom.TypeSystem1Activity
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by jingbin on 2020/11/29.
 */
class MyTest {

    // 声明一个可空类型的属性并初始化为null
    private var myService: TypeSystem1Activity.MyService? = null

    @Before
    fun setUp() {
        // 在setUp方法中提供真正的初始化器
        myService = TypeSystem1Activity.MyService()
    }

    @Test
    fun testAction() {
        // 必须注意可空性：要么用!!，要么用?.
        Assert.assertEquals("foo", myService!!.performAction())
    }
}