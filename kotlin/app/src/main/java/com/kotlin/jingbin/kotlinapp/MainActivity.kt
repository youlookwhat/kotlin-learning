package com.kotlin.jingbin.kotlinapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kotlin.jingbin.kotlinapp.`object`.Object1Activity
import com.kotlin.jingbin.kotlinapp.`object`.Object2Activity
import com.kotlin.jingbin.kotlinapp.`object`.Object3Activity
import com.kotlin.jingbin.kotlinapp.`object`.Object4Activity
import com.kotlin.jingbin.kotlinapp.annotations.AnnotationsReflection1Activity
import com.kotlin.jingbin.kotlinapp.annotations.AnnotationsReflection2Activity
import com.kotlin.jingbin.kotlinapp.basis.classproperty.PersonActivity
import com.kotlin.jingbin.kotlinapp.basis.enumwhen.whencode.WhenActivity
import com.kotlin.jingbin.kotlinapp.basis.trycatch.TryCatchActivity
import com.kotlin.jingbin.kotlinapp.basis.whilefor.WhileForActivity
import com.kotlin.jingbin.kotlinapp.function.SetOfActivity
import com.kotlin.jingbin.kotlinapp.generic.Generic1Activity
import com.kotlin.jingbin.kotlinapp.generic.Generic2Activity
import com.kotlin.jingbin.kotlinapp.generic.Generic3Activity
import com.kotlin.jingbin.kotlinapp.lambda.*
import com.kotlin.jingbin.kotlinapp.lambdahigher.LambdaHigher1Activity
import com.kotlin.jingbin.kotlinapp.lambdahigher.LambdaHigher2Activity
import com.kotlin.jingbin.kotlinapp.lambdahigher.LambdaHigher3Activity
import com.kotlin.jingbin.kotlinapp.operator.*
import com.kotlin.jingbin.kotlinapp.typesystom.TypeSystem1Activity
import com.kotlin.jingbin.kotlinapp.typesystom.TypeSystem2Activity
import com.kotlin.jingbin.kotlinapp.typesystom.TypeSystem3Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**Kotlin基础*/
        // 类和属性
        bt_class.setOnClickListener { PersonActivity.start(this) }
        // 枚举和when
        bt_enumwhen.setOnClickListener { WhenActivity.start(this) }
        // while 和 for
        bt_while_for.setOnClickListener { WhileForActivity.start(this) }
        // try catch 和 finally
        bt_try_catch.setOnClickListener { TryCatchActivity.start(this) }

        /**函数的定义与调用*/
        // 在Kotlin中创建集合
        bt_list_of.setOnClickListener { SetOfActivity.start(this) }

        /**类、对象和接口*/
        // 定义类的继承结构
        bt_object_1.setOnClickListener { Object1Activity.start(this) }
        bt_object_2.setOnClickListener { Object2Activity.start(this) }
        bt_object_3.setOnClickListener { Object3Activity.start(this) }
        bt_object_4.setOnClickListener { Object4Activity.start(this) }

        /**Lambda编程*/
        bt_lambda_1.setOnClickListener { Lambda1Activity.start(this) }
        bt_lambda_2.setOnClickListener { Lambda2Activity.start(this) }
        bt_lambda_3.setOnClickListener { Lambda3Activity.start(this) }
        bt_lambda_4.setOnClickListener { Lambda4Activity.start(this) }
        bt_lambda_5.setOnClickListener { Lambda5Activity.start(this) }

        /**Kotlin的类型系统*/
        bt_type_1.setOnClickListener { TypeSystem1Activity.start(this) }
        bt_type_2.setOnClickListener { TypeSystem2Activity.start(this) }
        bt_type_3.setOnClickListener { TypeSystem3Activity.start(this) }

        /**运算符重载及其他约定*/
        bt_operator_1.setOnClickListener { Operator1Activity.start(this) }
        bt_operator_2.setOnClickListener { Operator2Activity.start(this) }
        bt_operator_3.setOnClickListener { Operator3Activity.start(this) }
        bt_operator_4.setOnClickListener { Operator4Activity.start(this) }
        bt_operator_5.setOnClickListener { Operator5Activity.start(this) }

        /**高阶函数：Lambda作为形参和返回值*/
        bt_lambda_higher_1.setOnClickListener { LambdaHigher1Activity.start(this) }
        bt_lambda_higher_2.setOnClickListener { LambdaHigher2Activity.start(this) }
        bt_lambda_higher_3.setOnClickListener { LambdaHigher3Activity.start(this) }

        /**泛型*/
        bt_generic1.setOnClickListener { Generic1Activity.start(this) }
        bt_generic2.setOnClickListener { Generic2Activity.start(this) }
        bt_generic3.setOnClickListener { Generic3Activity.start(this) }

        /**注解与反射*/
        bt_annotation1.setOnClickListener { AnnotationsReflection1Activity.start(this) }
        bt_annotation2.setOnClickListener { AnnotationsReflection2Activity.start(this) }

        kotlinDefine()

    }

    private fun kotlinDefine() {
        // 1.hello world
        println("hello world kotlin!")
        tv_content.text = "hello world kotlin!"

        // 2.函数
        // 网址
        Log.e("sum", sum(1, 1).toString())
        Log.e("sum2", sum2(1, 3).toString())
        logSum(2, 3)
        logSum2(3, 4)
        // 书籍
        Log.e("max", max(1, 2).toString())
        Log.e("max2", max2(10, 2).toString())


        // ------------3.定义变量--------------------
        /** 1. 可变变量与不可变变量*/
        // 一次性赋值
        val a: Int = 1 // 立即赋值
        val b = 2     // 自动推断出Int的值
        val c: Int     // 如果没有初始值类型不能省略
        c = 3         // 明确赋值

        // 可变变量：   注意是var
        var x = 5
        x += 1
        Log.e("x", x.toString())

        // 顶层赋值
        val PI = 3.14    // val
        var y = 0        // var

        // 不用写在方法体外面
        fun incrementX() {
            y += 1
            Log.e("y", y.toString())
        }
        incrementX()

        /** 2. 使用字符串模板*/
        var a1 = 1
        val s1 = "a is $a1"
        a1 = 3
        // 模板中的任意表达式
        val s2 = "${s1.replace("is", "was")},but no is $a1"
        // a was 1, but now is 3
        Log.e("s2", s2)
    }

    // 求和
    private fun sum(a: Int, b: Int): Int {
        return a + b
    }

    // 将表达式作为函数体、返回值类型自动推断的函数：
    fun sum2(a: Int, b: Int) = a + b

    // 函数返回无意义的值：
    fun logSum(a: Int, b: Int): Unit {
        Log.e("logSum", (a + b).toString())
    }

    // Unit 返回类型可以省略(类型推导)
    fun logSum2(a: Int, b: Int) {
        Log.e("logSum2", (a + b).toString())
    }

    //----------------------------------------

    /**
     *  求最大值
     * if是表达式而不是语句，表达式有值，语句没有。
     * java中所有的控制结构都是语句
     * kotlin中除了循环以外大多数控制结构都是表达式
     */
    private fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    /**
     * 如果函数体写在花括号中，我们说这个函数有代码块体。
     * 如果直接返回了一个表达式体，他就有表达式体。
     */
    fun max2(a: Int, b: Int): Int = if (a > b) a else b
}
