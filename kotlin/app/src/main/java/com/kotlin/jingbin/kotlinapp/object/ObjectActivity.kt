package com.kotlin.jingbin.kotlinapp.`object`

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.utils.LogUtil

/**
 * 4.1 定义类继承结构
 */
class ObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        title = "类、对象和接口"


        /**------------------------- 4.1 定义类继承结构-------------------------*/

        /*--------------- 4.1.1 kotlin 中的接口-------------*/

        // 代码清单 1 声明一个简单的接口
//        interface Clickable {
//            fun click()
//        }

        // 代码清单 2 实现一个简单的接口
        class Button : Clickable2 {
            override fun click() {
                println(" I was clicked")
                LogUtil.e(" I was clicked")
            }
        }
        Button().click()
        Button().showOff()
        /**
         * 说明：
         * Kotlin 在后面使用【冒号:】 来代替java中的 extends 和 implements 关键字。
         * 可实现多个接口继承一个类。
         */

        // 代码清单 3 在接口定义一个带方法体的方法

        // 代码清单 4 定义另一个实现了同样方法的接口

        /**
         * 可以在接口中定义一个带方法体的方法。
         * 如果你的类实现了两个接口，这两个接口都定义了相同的方法，则编译器会强制你实现那个方法。
         */

        // 代码清单 5 调用继承自接口方法的实现
        class Button2 : Clickable2, Focusable {
//            override fun click() {
//                LogUtil.e("I was clicked2")
//            }

            override fun click() = LogUtil.e("I was clicked2")


            // 必须提供显示实现
            override fun showOff() {
                /** 使用尖括号加上父类型名字的 "super" 表明了你想要调用哪一个父类的方法*/
                super<Clickable2>.showOff()
                super<Focusable>.showOff()
            }
        }

        // 验证所有继承的方法都能被调用到
        LogUtil.e("--------分割线--------")
        val button = Button()
        button.click()
        button.showOff()
        val button2 = Button2()
        button2.click()
        button2.showOff()
        button2.setFocus(true)


        /*--------------- 4.1.2 open、final和abstract修饰符: 默认为final -------------*/


    }


    // 代码清单 1 声明一个简单的接口
    interface Clickable {
        fun click()
    }

    // 代码清单 3 在接口定义一个带方法体的方法
    interface Clickable2 {
        // 普通的声明
        fun click()

        // 带默认实现的方法
        fun showOff() = LogUtil.e("I'm clickable!")
    }

    // 代码清单 4 定义另一个实现了同样方法的接口
    interface Focusable {
        fun setFocus(b: Boolean) = LogUtil.e("I ${if (b) "got" else "lost"} focus.")
        fun showOff() = LogUtil.e("I'm clickable!")
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, ObjectActivity::class.java)
            context.startActivity(intent)
        }
    }
}
