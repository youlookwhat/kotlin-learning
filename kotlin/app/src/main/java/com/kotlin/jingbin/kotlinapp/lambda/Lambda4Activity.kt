package com.kotlin.jingbin.kotlinapp.lambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.`object`.JavaCode
import kotlinx.android.synthetic.main.activity_lambda.*
import org.jetbrains.anko.toast

/**
 * 5.4 使用Java函数式调用接口
 * */
class Lambda4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        title = "使用Java函数式调用接口"

        /*--------------- 5.4.1 把lambda当做参数传递给Java方法-------------*/
        // 可以把lambda传给任何期望函数式接口的地方。

        // 下面的方法有一个Runnable类型的参数
        /*java*/
//        void postponeComputation(int delay,Runnable computation)
        // 在Kotlin中可以调用它并把一个lambda作为实参传给它。编译器会自动把它转换为一个Runnable实例
        JavaCode().postponeComputation(1000) { println(42) }
        JavaCode().postponeComputation(1000, { println(42) })
        // 通过显示的创建一个实现了Runnable的匿名对象也能达到同样的效果：
        JavaCode().postponeComputation(1000, object : Runnable {
            override fun run() {
                println(42)
            }
        })
        /**
         * 当显示的声明对象时，每次调用都会创建一个新的实例。
         * 使用lambda的情况不同:如果lambda没有访问任何来自自定义它的函数的变量，相应的匿名类实例可以在多次调用之间重用
         */
        // 整个程序里只会创建一个Runnable的示例
        JavaCode().postponeComputation(1000) { println(42) }

        // 等价于这种实现
        val runnable = Runnable { println(42) }
        JavaCode().postponeComputation(1000, runnable)

        // 下面实例每次调用都会使用一个新的Runnable实例，把id值存储在它的字段中:
        // lambda会捕捉id这个变量
        fun handleComputation(id: String) {
            // 每次handleComputation调用时都创建一个Runnable的新实例
            JavaCode().postponeComputation(1000) { println(42) }
        }

        /**
         * 每个lambda表达式都会被编译成一个匿名类，除非它是一个内联的lambda。
         * 如果lambda捕捉到了变量，每次被捕捉的变量会在匿名类中有对应的字段，而且每次(对lambda的)调用都会创建一个这个类的匿名类的实例。
         * 编译器给每个被捕捉的变量生成了一个字段和一个构造方法参数。
         */
//        class HandleComputation$1(val id:String):Runnable{
//            override fun run(){
//                println(id)
//            }
//        }
//        fun handleComputation(id:String){
//            JavaCode().postponeComputation(1000,HandleComputation$1(id))
//        }


        /*--------------- 5.4.2 SAM构造方法：显示地把lambda转换成函数式接口-------------*/
        // SAM构造方法是编译器生成的函数，让你执行从lambda到函数接口实例的显示转换。

        // 代码清单5.14 使用SAM构造方法来返回值
        fun createAllDoneRunnable(): Runnable {
            return Runnable { println("All done!") }
        }
        createAllDoneRunnable().run()

        // 代码清单5.15 使用SAM构造方法来重用listener实例
        val listener = View.OnClickListener { view ->
            val text = when (view.id) {
                R.id.tv_click -> "first"
                else -> "unkown"
            }
            toast(text)
        }
        tv_click.setOnClickListener(listener)

    }

    data class Person(val name: String, val age: Int)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Lambda4Activity::class.java)
            context.startActivity(intent)
        }
    }
}