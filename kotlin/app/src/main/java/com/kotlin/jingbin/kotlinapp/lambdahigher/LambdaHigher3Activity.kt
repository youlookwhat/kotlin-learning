package com.kotlin.jingbin.kotlinapp.lambdahigher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 8.3 高阶函数中的控制流
 * */
class LambdaHigher3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda_higher)
        title = "8.3 高阶函数中的控制流"

        /**-------------------- 8.3.1 lambda 中的返回语句：从一个封闭的函数返回 ----------------------*/
        // 代码清单8.18 在一个普通循环中使用return
        data class Person(val name: String, val age: Int)

        val people = listOf(Person("Alice", 29), Person("Bob", 31))
        fun lookForAlice(people: List<Person>) {
            for (person in people) {
                if (person.name == "Alice") {
                    println("Found!")
                    return
                }
            }
            // 如果 people 中没有 Alice ，这一行就会被打印出来
            println("Alice is no found")
        }
        lookForAlice(people)// Found!

        // 代码清单8.19 在传递给 forEach 的lambda中使用return
        fun lookForAlice2(people: List<Person>) {
            people.forEach {
                if (it.name == "Alice") {
                    // 和 8.18 中一样返回
                    println("Found!!")
                    return
                }
            }
            println("Alice is no found")
        }
        // 只有在以lambda作为参数的函数是内联函数的时候 才能从更外层的函数返回。


        /**-------------------- 8.3.2 从lambda返回: 使用标签返回 ----------------------*/
        /*
        * 也可以在 lambda 表达式中使用局部返回。lambda中的局部返回跟for循环中的break表达式类似。
        * 要区分局部返回和非局部返回，要用到标签。
        */

        // 代码清单8.20 用一个标签实现局部返回
        fun lookForAlice3(people: List<Person>) {
            // 给 lambda 表达式加上标签
            people.forEach lable@{
                // return@lable 引用了这个标签
                if (it.name == "Alice") return@lable
            }
            // 这一行总是会打印出来
            println("Alice might be somewhere")
        }
        lookForAlice3(people)// Alice might be somewhere

        /*
        * people.forEach lable@{
        *        if (it.name == "Alice") return@lable
        * }
        *   lable@ lambda标签
        *   @lable 返回表达式标签
        */

        // 代码清单8.21 用函数名作为 return 标签
        fun lookForAlice4(people: List<Person>) {
            people.forEach {
                // return@forEach 从lambda表达式返回
                if (it.name == "Alice") return@forEach
            }
            println("Alice might be somewhere")
        }

        // 带标签的 this 表达式
        // 这个 lambda 的隐式接收者可以通过 this@sb 访问
        val apply = StringBuilder().apply sb@{
            listOf(1, 2, 3).apply {
                // this 指向作用域内最近的隐式接收者。
                // 所有隐式接收者都可以被访问，外层的接收者通过显示的标签访问
                this@sb.append(this.toString())
            }
        }
        println(apply)// [1,2,3]


        /**-------------------- 8.3.3 匿名函数：默认使用局部返回 ----------------------*/
        // 代码清单8.22 在匿名函数中使用 return
        fun lookForAlice5(people: List<Person>) {
            // 使用匿名函数取代 lambda 表达式
            people.forEach(fun(person) {
                // return 指向最近的函数：一个匿名函数
                if (person.name == "Alice") return
                println("${person.name} is not Alice")
            })
        }
        println(lookForAlice5(people))

        // 代码清单8.23 在filter中使用匿名函数
        people.filter(fun(person): Boolean {
            return person.age < 30
        })

        // 代码清单8.24 使用表达式体匿名函数
        people.filter(fun(person) = person.age < 30)
        // return 从最近的使用 fun 关键字声明的函数返回。
        // 8.22 中返回的是 fun(person)

        // 以下返回的是 fun lookForAlice6
        fun lookForAlice6(people: List<Person>) {
            people.forEach {
                if (it.name == "Alice") return
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, LambdaHigher3Activity::class.java)
            context.startActivity(intent)
        }
    }
}
