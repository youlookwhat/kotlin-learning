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
        /**
         * 《Effective Java》："要么为继承做好设计并记录文档，要么禁止这么做。"
         * Java 的类和方法默认是open的，而 Kotlin 中默认都是final的。
         * 如果你想允许创建一个类的子类，需要使用 open 修饰符来标识这个类。
         * 此外需要给每一个可以被重写的属性或方法添加 open 修饰符。
         */

        // 代码清单 6 声明一个带一个 open 方法的open类
        open class RichButton : Clickable {

            // 这个函数重写了一个open函数并且它本身同样是open的
            override fun click() {}

            // 这个函数是final的：不能在子类中重写它
            fun disable() {}

            // 这个函数使open的：可以在子类中重写它
            open fun animate() {}
        }

        /**
         * 注意：如果你重写了一个基类或者接口的成员，重写了的成员同样默认是open的。
         * 如果你想改变这一行为，阻止子类的操作，可以显示将重写的成员变量设置为final。
         */

        // 代码清单 7 禁止重写
        open class RichButton2 : Clickable {

            // 没有final的override 意味着是open的
            final override fun click() {}
        }

        /**
         * open 类和智能转换
         * 属性默认是final的，可以在大多数属性上不加思考的使用智能转换，这提高了你的代码表现力。
         * if (e is Sum) {
         * // 变量 e 被智能转换了类型
         * return eval(e.left) + eval(e.right)
         * }
         */

        // 代码清单 8 声明一个抽象类
        // 这个类是抽象的，不能直接创建它的实例
        abstract class Animated {

            // 这个函数是抽象的：它没有实现必须被子类重写
            abstract fun animate()

            // 抽象类的抽象函数并不是默认open的，但是可以被标注为open的
            open fun stopAnimating() {}

            fun animateTeice() {}
        }

        // 测试
        class Button3 : Animated() {
            // 一定实现
            override fun animate() {

            }

            // 可以选择实现
            override fun stopAnimating() {
                super.stopAnimating()
            }
            // 不能实现
//            animateTeice()
        }

        /**
         * 修饰符      相关成员                评注
         * final      不能被重写              类中成员默认使用
         * open       可以被重写              需要明确地表明
         * abstract   必须被重写              只能在抽象类中使用：抽象成员不能实现
         * override   重写父类或接口中的成员    如果没有使用final表明，重写的成员默认是开放的
         */

        /**
         * open : 控制继承修饰符。
         * 要被继承的类必须是open修饰的，因为类默认是final的。
         * 同理，要被重写的属性和方法也必须被open修饰。
         */

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
