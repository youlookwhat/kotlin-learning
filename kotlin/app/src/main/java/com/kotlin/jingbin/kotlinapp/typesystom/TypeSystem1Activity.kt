package com.kotlin.jingbin.kotlinapp.typesystom

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 6.1 可空性
 * Kotlin怎样表示允许为null的值，以及Kotlin提供的处理这些值的工具
 */
class TypeSystem1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_system)

        /**-------------------- 6.1.1 可空类型 ? ----------------------*/
        // Kotlin和Java最重要的区别：对可空类型的显式的支持。

        /*java*/
//        int strLen(String s){
//            return s.length()
//        }

        fun strLen(s: String) = s.length
        // 在编译器会标记成错误（这个函数中的参数被声明成String类型，在Kotlin中这表示它必须包含一个String实例）
//        strLen(null)

        // 如果允许调用这个方法的时候传给它所有的可能的实参，包括null，需要显示地在类型名称后面加上问号来标记它：
        fun strLenSafe(s: String?) = {}
        // Type? = Type or null

        val e: String? = null
//        val f: String = null // 错误
//        strLen(e)// 错误

        // 代码清单6.1 使用if检查处理null
        fun strLenSafe2(s: String?): Int = if (s != null) s.length else 0
        strLenSafe2(e)// 0
        strLenSafe2("sss")// 3

        /**-------------------- 6.1.2 类型的含义 ----------------------*/
        /*
        * 类型就是数据的分类......决定了该类型可能的值，以及该类型的值上可以完成的操作
        * 可空和非可空的对象在运行时没有什么区别；
        * 可空类型并不是非空类型的包装。
        * 所有的检查都发生在编译期。这意味着使用Kotlin的可空类型并不会在运行时带来额外的开销
        */

        /**-------------------- 6.1.3 安全调用运算符: ?. ----------------------*/
        // ?. 它允许你把一次null检查和一次方法调用合并成一个操作
        val ok: String? = null
        println(ok?.toUpperCase())// null
        println(
                if (ok != null) {
                    ok.toUpperCase()
                } else null
        )
        // 这次调用的结果类型也是可空的。
        fun printAllCaps(s: String?) {
            // allCaps可能也是null
            val allCaps: String? = s?.toLowerCase()
            println(allCaps)
        }

        // 安全调用不光可以调用方法，也能访问属性。
        // 代码清单6.2 使用安全调用处理可空属性
        class Employee(val name: String, val manager: Employee?)

        fun managerName(employee: Employee): String? = employee.manager?.name
        val ceo = Employee("Da Boss", null)
        val developer = Employee("jingbin", ceo)
        managerName(ceo)// null
        managerName(developer)// Da Boss

        // 代码清单6.3 链接多个安全调用
        class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)

        class Company(val name: String, val address: Address?)
        class Person(val name: String, val company: Company?)

        fun Person.countryName(): String {
            val country = this.company?.address?.country
            return if (country != null) country else "unKnow"
//            return country ?: "unKnow"
        }

        val person = Person("beijing", null)
        println(person.countryName())// unKnow

        /**-------------------- 6.1.4 Elvis运算符 ?: ----------------------*/
        
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, TypeSystem1Activity::class.java)
            context.startActivity(intent)
        }
    }
}
