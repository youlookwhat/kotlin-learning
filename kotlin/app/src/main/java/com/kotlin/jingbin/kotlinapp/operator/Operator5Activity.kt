package com.kotlin.jingbin.kotlinapp.operator

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.lang.reflect.Type
import java.time.LocalDate
import kotlin.properties.ReadWriteProperty

/**
 * 7.5 重用属性访问的逻辑：委托属性
 * */
class Operator5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator1)

        /*
        * Kotlin中最独特和最强大的功能：委托属性
        * 委托是一种设计模式，操作的对象不用自己执行，而是把工作微委托给另一个辅助的对象。我们把辅助对象称为委托。
        */


        /**-------------------- 7.5.1 委托属性的基本操作 ----------------------*/
        // 基础语法：
//        class Foo {
//            var p: Type by Delegate()
//        }

        class Foo {
//            private val delegate = Delegate()
//            var p: Type
//                set(value: Type) = delegate.setValue(1, value)
//                get() = delegate.getValue(1)
        }

    }

    class Delegate {
//        fun setValue(x: Int, any: Type) {
//
//        }
//
//        fun getValue(i: Int): Type {
//            return i.Ty
//        }

    }


    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Operator5Activity::class.java)
            context.startActivity(intent)
        }
    }
}
