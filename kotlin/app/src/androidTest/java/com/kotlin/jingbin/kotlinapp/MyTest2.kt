package com.kotlin.jingbin.kotlinapp

import com.kotlin.jingbin.kotlinapp.typesystom.TypeSystem1Activity
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by jingbin on 2020/11/29.
 */
class MyTest2 {

    // 声明一个不需要初始化器的非空类型的属性
    private lateinit var myService: TypeSystem1Activity.MyService

    @Before
    fun setUp() {
        // 像之前的例子一样在setUp方法中初始化属性
        myService = TypeSystem1Activity.MyService()
    }

    @Test
    fun testAction() {
        // 不需要null检查直接访问属性
        Assert.assertEquals("foo", myService.performAction())
    }
}