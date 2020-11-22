package com.kotlin.jingbin.kotlinapp.`object`

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.util.jar.Attributes

/**
 * 4.2 声明一个带非默认构造方法后属性的类
 */
class Object2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        title = "声明一个带非默认构造方法后属性的类"


        /**------------------------- 4.2.1初始化类：主构造方法和初始化语句块 -------------------------*/
        // 被括号围起来的叫主构造方法。目的：1表明构造方法的参数 2定义使用这些参数初始化属性
        class User(val nickName: String)

        // 1.明确的写法：
        /**
         * 关键字：
         * 1、constructor:用来开始一个构造方法或从构造方法的声明
         * 2、init:用来引入一个初始化语句块。主构造方法有语法限制，不能包含初始化代码。
         */
        class User1 constructor(_nickName: String) {
            val nickName: String

            init {
                nickName = _nickName
            }
        }

        // 2.用参数来初始化属性
        class User2 constructor(_nickName: String) {
            val nickName = _nickName
        }

        // 3.val 意味着相应的属性会用构造方法的参数来初始化
        open class User3(val nickName: String)

        // 为构造方法声明一个默认值：
        class User4(val nickName: String, val isSuc: Boolean = true)
        // 创作构造方法时不需要new
        val user4 = User4("jingbin")
        val user42 = User4("jingbin", false)
        val user43 = User4("jingbin", isSuc = false)
        println(user4.isSuc)// true
        println(user42.isSuc)// false
        println(user43.isSuc)// false

        // 如果所有的参数都有默认值，会生成一个额外不带参数的构造方法来使用所有的默认值
        class User5(val nickName: String = "jingbin", val isSuc: Boolean = true)
        // 使用不带参数的，但是默认值是声明的默认值
        val user5 = User5()

        // 如果你的类具有一个父类，主构造方法同样需要初始化父类
        class TwitterUser(nickName: String) : User3(nickName) {

        }

        // 将会生成一个不带任何参数的构造方法
        open class Button

        // 必须显示的调用父类的构造方法，即使没有任何参数，空括号表示没有参数的构造方法
        class RadioButton : Button()

        // 包装自己的类不被其他的类实例化，有一个private构造方法
        class Secretive private constructor() {}

        /**------------------------- 4.2.2 构造方法：用不同的方式来初始化父类 -------------------------*/
        // 大部分的情况不需要声明多个构造方法，因为可以在构造方法中写默认值来规避
        class User6(val nickName: String, val isSuc: Boolean = true)

        // 比较常见到的是一个View
        open class View {
            // 从构造方法
            constructor(ctx: Context) {

            }

            constructor(ctx: Context, attr: Attributes?) {

            }
        }

        // 如果想扩展这个类的，可以声明同样的构造方法
        class MyButton : View {
            // 调用父类的构造方法
            constructor(context: Context) : super(context) {

            }

            constructor(context: Context, attributes: Attributes) : super(context, attributes) {

            }
        }

        // 也可以使用this关键字调用自己的另一个构造方法
        class MyButton1 : View {
            // 委托给这个类的另一个构造方法
            constructor(context: Context) : this(context, null) {

            }

            constructor(context: Context, attributes: Attributes?) : super(context, attributes) {

            }
        }

        /**------------------------- 4.2.3 实现在接口中声明的属性 -------------------------*/
        // Kotlin中接口可以包含抽象属性的声明，Java貌似不行,实际上就是一个getName()的方法
//        interface User8 {
//            val name: String
//        }
        // 代码清单 4.14 实现一个接口属性
        // 主构造方法属性
        class PrivateUser(override val name: String) : User8

        // 每次访问时都会调用substringBefore
        class SubScribingUser(val email: String) : User8 {
            override val name: String
                // 在@之前的字符串
                get() = email.substringBefore("@")
        }

        // 只会调用一次id.toString()
        class FaceBookUser(val id: Int) : User8 {
            // 初始化属性
            override val name = id.toString()
        }

        // 接口中还可以包含getter和setter的属性
//        interface User9 {
//            val email: String
//            val name: String get() = email.substringBefore("@")
//        }

        /**------------------------- 4.2.4 通过getter或setter访问支持字段 -------------------------*/
        class User10(val name: String) {
            var address: String = "unspecified"
                set(value: String) {
                    // 在set中访问支持字段
                    println("$field --> $value")
                    // field 是原来的值，value是设置的值
                    field = value
                }
        }

        val user10 = User10("jingbin")
        // 将 jingbin 重新设置为 jinbeen "jingbin --> jinbeen"
        user10.address = "jinbeen"

        /**------------------------- 4.2.5 修改访问器的可见性 -------------------------*/
        // 代码清单 4.16 声明一个具有private setter的属性
        class LengthCounter {
            var counter: Int = 0
                // 不能在类外部修改这个属性
                private set

            fun addWord(word: String) {
                counter += word.length
            }
        }
        LengthCounter().addWord("jingbin")
    }

    interface User9 {
        val email: String
        val name: String get() = email.substringBefore("@")
    }

    interface User8 {
        val name: String
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Object2Activity::class.java)
            context.startActivity(intent)
        }
    }
}
