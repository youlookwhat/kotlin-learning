package com.kotlin.jingbin.kotlinapp.lambdahigher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 8.1 声明高阶函数
 * */
class LambdaHigherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda_higher)
        title = "8.1 声明高阶函数"

        // 高阶函数就是以另一个函数作为参数或者返回值的函数。
        val list = listOf(0, 1, 2, 3)
        println(list.filter { it > 0 })


        /**-------------------- 8.1.1 函数类型 ----------------------*/
        // Kotlin的类型推导
        val sum = { x: Int, y: Int -> x + y }
        val action = { println(42) }

        // 这些变量的显示类型声明是什么样的？
        // 有两个Int型参数和Int型返回值的函数
        val sum2: (Int, Int) -> Int = { x, y -> x + y }
        // 没有参数和返回值的函数
        val action2: () -> Unit = { println(42) }

        /*
        * (Int, String) -> Unit
        * 参数类型           返回类型
        * 声明函数类型，需要将函数参数类型防在括号中，紧接着是一个箭头和函数的返回类型
        */

        // 标记函数类型 返回值为可空 类型：
        var canReturnNull: (Int, Int) -> Int? = { x, y -> null }

        // 标记 函数类型的可空 变量
        var funOrNull: ((Int, Int) -> Int)? = null

        // 函数类型的参数名：可以为函数类型声明中的参数指定名字：
        fun performRequest(url: String, callback: (code: Int, content: String) -> Unit) {
            // 函数类型的参数现在有了名字
            println("performRequest")
        }

        val url = "http://www.jinbeen.com"
        // 可以使用API中提供的参数名字作为lambda参数的名字
        performRequest(url, { code, content -> { println(url.split('.')) } })
        performRequest(url) { code, content -> { println(url.split('.')) } }
        // 或者你可以改变参数的名字
        performRequest(url) { code, page -> { println(url.split('.')) } }


        /**-------------------- 8.1.2 调用作为参数的函数 ----------------------*/
        // 代码清单8.1 定义一个简单高阶函数
        // 定义一个函数类型的参数
        fun twoAndThree(operation: (Int, Int) -> Int) {
            // 调用函数类型的参数
            val result = operation(2, 3)
            println("The result is $result")
        }
        twoAndThree { a, b -> a + b }// The result is 5
        twoAndThree { a, b -> a * b }// The result is 6

        /*
        * filter 函数的声明，以一个判断式作为参数
        *
        *     接收者类型      参数类型      函数类型参数
        * fun String.filter(predicate: (Char)->Boolean): String
        * Char: 作为参数传递的函数的参数类型
        * Boolean: 作为参数传递的函数的返回类型
        */

        // 代码清单8.2 实现一个简单版本的filter函数
        // 检查每个字符是否满足判断式，如果满足就将字符添加到包含结果的 StringBuilder 中
        fun String.filter(predicate: (Char) -> Boolean): String {
            val sb = StringBuilder()
            for (index in 0 until length) {
                val element = get(index)
                // 调用作为参数传递给 predicate 的函数
                if (predicate(element)) sb.append(element)
            }
            return sb.toString()
        }
        // 传递一个lambda作为 predicate 参数
        println("ab1c".filter { it in 'a'..'z' })// abc


        /**-------------------- 8.1.3 在Java中使用函数 ----------------------*/
        /*kotlin类型的声明*/
        fun processTheAnswer(f: (Int) -> Int) {
            println(f(42))
        }

        /*Java8*/
        // processTheAnswer(number->number+1)

        /*旧版的Java*/
//        processTheAnswer(new Function1 < Integer, Integer > (){
//            @Override
//            public Integer invoke(Integer number){
//                System.out.println(number);
//                return number+1;
//            }
//        });

        /**-------------------- 8.1.4 函数类型的参数默认值和null值 ----------------------*/
        // 代码清单8.3 使用了硬编码toString转换的joinToString函数
        fun <T> Collection<T>.joinToString(
                separator: String = ",",
                prefix: String = "",
                postfix: String = ""
        ): String {
            val result = StringBuilder(prefix)
            for ((index, element) in this.withIndex()) {
                if (index > 0) result.append(separator)
                // 使用默认的 toString 方法将对象转换为字符串
                result.append(element)
            }
            result.append(postfix)
            return result.toString()
        }

        // 代码清单8.4 给函数类型的参数指定默认值
        fun <T> Collection<T>.joinToString2(
                separator: String = ", ",
                prefix: String = "",
                postfix: String = "",
                // 声明一个以lambda为默认值的函数类型的参数
                transForm: (T) -> String = { it.toString() }
        ): String {
            val result = StringBuilder(prefix)
            for ((index, element) in this.withIndex()) {
                if (index > 0) result.append(separator)
                // 调用作为实参传递给 transform 形参的函数
                result.append(transForm(element))
            }
            result.append(postfix)
            return result.toString()
        }

        val letters = listOf("jingbin", "Jinbeen")
        // 使用默认的转换函数
        println(letters.joinToString2())// jingbin, Jinbeen
        // 传递一个lambda作为参数
        println(letters.joinToString2 { it.toLowerCase() })// jingbin, jinbeen
        // 使用命名参数语法传递几个参数，包括一个lambda
        println(letters.joinToString2("! ", "! ",
                " ", transForm = { it.toUpperCase() }))// JINGBIN! JINBEEN


        fun foo(callback: (() -> Unit)?) {
            // 显示的检查null
            if (callback != null) {
                callback()
            }
            // 也可以这样写
            callback?.invoke()
        }

        // 代码清单8.5 使用函数类型的可空参数
        fun <T> Collection<T>.jointToString3(
                separator: String = ", ",
                prefix: String = "",
                postfix: String = "",
                // 声明一个函数类型的可空参数
                transForm: ((T) -> String)? = null): String {
            val result = StringBuilder(prefix)
            for ((index, element) in this.withIndex()) {
                if (index > 0) result.append(separator)
                // 使用安全调用语法调用函数
                // 使用Elvis运算符处理回调没有被指定的情况
                val str = transForm?.invoke(element) ?: element.toString()
                result.append(str)
            }
            result.append(postfix)
            return result.toString()
        }


        /**-------------------- 8.1.5 返回函数的函数 ----------------------*/
        // 代码清单8.6 定义一个返回函数的函数
//        enum class Delivery { STANDARD, EXPEDITED }
        class Order(val itemCount: Int)

        fun getShippingCostCalculator(
                // 声明一个返回函数的函数
                delivery: Delivery): (Order) -> Double {
            if (delivery == Delivery.EXPEDITED) {
                // 返回lambda
                return { order -> 6 + 2.1 * order.itemCount }
            }
            // 返回lambda
            return { order -> 1.2 * order.itemCount }
        }

        // 取得的是函数，将返回的函数保存在变量中
        val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
        println("Shopping costs ${calculator(Order(3))}")// 12.3

        /*
        * 声明一个返回另一个函数的函数，需要指定一个函数类型作为返回类型。
        * getShippingCostCalculator返回了一个函数，这个函数以 Order 作为参数并返回一个 Double 类型的值。
        * 要返回一个函数，需要写一个 return 表达式，跟上一个 Lambda、一个成员引用，或者其他的函数类型的表达式，
        * 比如一个(函数类型的)局部变量。
        */

        // 代码清单8.7 在UI代码中定义一个返回函数的函数
        data class Person(
                val firstName: String,
                val lastName: String,
                val phoneNumber: String?
        )

        class ContactListFilters {
            var prefix: String = ""
            var onlyWithPhoneNumber: Boolean = false
            // 声明一个返回函数的函数
            fun getPredicate(): (Person) -> Boolean {
                val startsWithPrefix = { p: Person ->
                    p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
                }
                if (!onlyWithPhoneNumber) {
                    // 返回一个函数类型的变量
                    return startsWithPrefix
                }
                // 从这个函数返回一个lambda
                return { startsWithPrefix(it) && it.phoneNumber != null }
            }
        }

        val contacts = listOf(Person("Dmitry", "bin", "188-1"),
                Person("Jin", "been", null))
        val contactListFilters = ContactListFilters()
        with(contactListFilters) {
            prefix = "Dm"
            onlyWithPhoneNumber = true
        }
        // 将 getPredicate 返回的函数作为参数传递给 filter 函数
        println(contacts.filter(contactListFilters.getPredicate()))


        /**-------------------- 8.1.6 通过lambda去除重复代码 ----------------------*/
        // 代码清单8.8 定义站点访问数据
        data class SiteVisit(val path: String, val duration: Double, val os: Os)
//        enum class Os { WINDOWS, LINUX, MAC, IOS, ANDROID }

        val log = listOf(
                SiteVisit("/", 34.0, Os.WINDOWS),
                SiteVisit("/", 22.0, Os.MAC),
                SiteVisit("/login", 12.0, Os.WINDOWS),
                SiteVisit("/signup", 8.0, Os.IOS),
                SiteVisit("/", 16.3, Os.ANDROID)
        )

        // 代码清单8.9 使用硬解码的过滤器分析站点访问数据
        val average = log.filter { it.os == Os.WINDOWS }
                .map(SiteVisit::duration)
                .average()
        println(average)// 23.0

        // 代码清单8.10 用一个普通方法去除重复代码
        fun List<SiteVisit>.averageDurationFor(os: Os) =
                // 将重复代码抽取到函数中
                filter { it.os == os }.map(SiteVisit::duration).average()
        println(log.averageDurationFor(Os.WINDOWS))// 23.0
        println(log.averageDurationFor(Os.MAC))// 22.0

        // 代码清单8.11 用一个复杂的硬编码函数分析站点访问数据
        val averageMobileDuration = log.filter { it.os in setOf(Os.IOS, Os.ANDROID) }.map(SiteVisit::duration).average()
        println(averageMobileDuration)// 12.15

        // 代码清单8.12 用一个高阶函数去除重复代码
        fun List<SiteVisit>.averageDurationFor2(predicate: (SiteVisit) -> Boolean) =
                filter(predicate).map(SiteVisit::duration).average()

        println(log.averageDurationFor2 {
            it.os in setOf(Os.ANDROID, Os.IOS)
        })// 12.15
        println(log.averageDurationFor2 {
            it.os == Os.IOS && it.path == "/singup"
        })

    }

    enum class Os { WINDOWS, LINUX, MAC, IOS, ANDROID }
    enum class Delivery { STANDARD, EXPEDITED }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, LambdaHigherActivity::class.java)
            context.startActivity(intent)
        }
    }
}
