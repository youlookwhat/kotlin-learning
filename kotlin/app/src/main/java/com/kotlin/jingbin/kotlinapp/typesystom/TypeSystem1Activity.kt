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
//        fun strLenSafe2(s: String?): Int = s?.length ?: 0
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
        // kotlin中有方便的运算符来提供null的默认值。
        fun foo(s: String?) {
            // 如果 s 为null，结果是一个空的字符串
            val t: String = s ?: ""
        }

        // 代码清单6.4 使用Elvis运算符处理null值
        fun strLenSafe3(s: String?): Int = s?.length ?: 0
        println(strLenSafe3("aaa"))// 3
        println(strLenSafe3(null))// 0

        // 代码清单6.5 同时使用throw和Elvis运算符
//        class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
        fun printShipping(person: Person) {
            // 缺少address就抛出异常
            val address = person.company?.address ?: throw IllegalArgumentException("No address")
            // 使用with函数避免在这一行中重复使用四次address
            with(address) {
                println(streetAddress)
                println("$zipCode $city $country")
            }
        }

        val address = Address("高新区", 1000, "武汉", "中国")
        val company = Company("keji", address)
        val person1 = Person("jingbin", company)
        printShipping(person1)
        printShipping(Person("jingbin", null))// java.lang.IllegalArgumentException:No address


        /**-------------------- 6.1.5 安全转换 as? ----------------------*/
        // as? 运算符尝试把值转换成指定的类型，如果值不是合适的类型就返回null
//        foo as? Type
//        foo is Type --- foo as Type
//        foo !is Type --- null

        // 代码清单6.6 使用安全转换实现equals
        class Person2(val firstName: String, val lastName: String) {
            override fun equals(o: Any?): Boolean {
                // 检查类型，如果不匹配就返回false
                val otherPerson = o as? Person2 ?: return false
                // 在安全转换后，变量otherPerson被智能转换为Person类型
                return otherPerson.firstName == o.firstName && otherPerson.lastName == o.lastName
            }

            override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
        }

        val p1 = Person2("jing", "bin")
        val p2 = Person2("jing", "bin")
        // == 运算符会调用equals方法
        println(p1 == p2)// true
        println(p1.equals(12))// false


        /**-------------------- 6.1.6 非空断言 !! ----------------------*/
        // 有时候你并不需要Kotlin的这些支持来处理null值，你只需要直接告诉编译器这个值实际上并不是null。
        // 非空断言是Kotlin提供最简单直率的处理可空类型值的工具。
//        foo!!
//        foo != null  ---  foo
//        foo == null  ---  NullPointerException

        // 代码清单6.7 使用非空断言
        fun ignoreNulls(s: String?) {
            // 异常在这一行：告诉编译器 我知道这个值不为null，如果我错了我准备好了接收这个异常
            val sNotNull: String = s!!
            println(sNotNull.length)
        }
//        ignoreNulls(null)

        // 代码清单6.8 在Swing action中使用非空断言
//        class CopyRowAction(val list: JList<String>) : AbstractAction {
//            override fun isEnabled(): Boolean = list.selectedValue != null
        // 只会在isEnabled返回 true 时调用
//            override fun actionPerformed(e: ActionEvent) {
//                val selectedValue = list.selectedValue!!
//            }
//        }

        // 不要写这样的代码，因为不知道 跟踪信息只表明异常发生在哪一行代码
        person1.company!!.address!!.country


        /**-------------------- 6.1.7 let 函数 ----------------------*/
        /*
        * let函数让处理可空表达式变得更容易，和安全调用运算符一起，它允许你对表达式求值，检查求值结果是否为null，并把结果保存为一个变量。
        * 所有这些变动都在同一个简洁的表达式中。
        * let 函数做的所有事情就是把一个调用它的对象编程lambda表达式的参数。
        */
//        foo?.let {
//            ...it...
//        }
//        foo !=null // 在lambda内部it是非空的
//        foo ==null // 什么都不会发生

        // 代码清单6.9 使用let调用一个接受非空参数的函数
        fun sendEmailTo(email: String) {
            println("Sending email to $email")
        }

        val email: String? = "jingbin@qq.com"
        // let函数只在email的值非空时才会被调用
        email?.let { sendEmailTo(it) }

        val email2: String? = null
        // 不会被调用
        email2?.let { sendEmailTo(it) }

//        val person3 : Person? = getTheBestInWord()
//        if (person3!=null) sendEmailTo(person3.email)
        // 等同于
//        getTheBestInWord()?.let{sendEmailTo(it.email)}


        /**-------------------- 6.1.8 延迟初始化的属性 ----------------------*/

        //代码清单6.10 使用非空断言访问可空属性
//        open class MyService {
////            fun performAction(): String = "foo"
////        }
////        class MyTest {
////
////            // 声明一个可空类型的属性并初始化为null
////            private var myService: TypeSystem1Activity.MyService? = null
////
////            @Before
////            fun setUp() {
////                // 在setUp方法中提供真正的初始化器
////                myService = TypeSystem1Activity.MyService()
////            }
////
////            @Test
////            fun testAction() {
////                // 必须注意可空性：要么用!!，要么用?.
////                Assert.assertEquals("foo", myService!!.performAction())
////            }
////        }

        // 代码清单6.11 使用延迟初始化属性 lateinit
//        class MyTest2 {
//
//            // 声明一个不需要初始化器的非空类型的属性
//            private lateinit var myService: TypeSystem1Activity.MyService
//
//            @Before
//            fun setUp() {
//                // 像之前的例子一样在setUp方法中初始化属性
//                myService = TypeSystem1Activity.MyService()
//            }
//
//            @Test
//            fun testAction() {
//                // 不需要null检查直接访问属性
//                Assert.assertEquals("foo", myService.performAction())
//            }
//        }

        // 延迟初始化的属性都是var的。


        /**-------------------- 6.1.9 可空类性的扩展 ----------------------*/
        // isEmpty 是否是"" isBlank是否是Null或""

        //代码清单6.12 用可空接收者调用扩展函数
        fun verifyUserInput(input: String?) {
            // 这里不需要安全调用
            if (input.isNullOrBlank()) {
                println("isNullOrBlank....")
            }
        }
        // 接收者调用isNullOrBlank并不会导致任何异常
        verifyUserInput(null)

        /*
        * input 可空类型的值
        * isNullOrBlank() 可空类型的拓展
        * . 不需要安全调用
        */
        // 可空字符串的扩展
        fun String?.isNullOrBlank(): Boolean = this == null || this.isBlank()
        // 当你为一个可空类型(以?结尾)定义扩展函数时，这意味着你可以对可空的值调用这个函数；
        // 并且函数体中的this可能为null，所以你必须显示的检查。在可空类型的扩展函数中，this可能为null

        val person5: String? = null
        // 没有安全调用，所以 it 是可空类型
//        person5.let { sendEmailTo(it) }
        person5?.let { sendEmailTo(it) }

        /**-------------------- 6.1.10 类型参数的可空性 ----------------------*/
        // Kotlin中所有泛型类和泛型函数的类型参数默认都是可空的。

        // 代码清单6.13 处理可空的类型参数
        fun <T> printHashCode(t: T) {
            // 因为 t 可能为null，所以必须使用安全调用
            println(t?.hashCode())
        }
        // T 被推导成 Any?
        printHashCode(null)

        // 要是类型参数非空，必须要为它指定一个非空的上界，那样泛型会拒绝可空类型作为实参。
        // 代码清单6.14 为类型参数声明非空上界
        fun <T : Any> printHashCode2(t: T) {
            // T 不是可空的
            println(t.hashCode())
        }
//        printHashCode2(null)

        /**-------------------- 6.1.11 可空性和Java ----------------------*/

    }

    open class MyService {
        fun performAction(): String = "foo"
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, TypeSystem1Activity::class.java)
            context.startActivity(intent)
        }
    }
}
