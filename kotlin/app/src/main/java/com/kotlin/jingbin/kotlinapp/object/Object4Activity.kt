package com.kotlin.jingbin.kotlinapp.`object`

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.io.File

/**
 * 4.4 “object”关键字：将声明一个类与创建一个实例结合起来
 */
class Object4Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        title = "“object”关键字：将声明一个类与创建一个实例结合起来"

        // 使用的核心理念：定义一个类并同时创建一个对象

        /**------------------------- 4.4.1 对象声明：创建单例易如反掌 -------------------------*/
        // 对象声明将 类声明与该类的 单一实例 声明结合到了一起
        // 表示一个组织的工资单
//        object Payroll {
//            val allEmployees = arrayListOf<Person>()
//            fun calculateSalary() {
//                for (person in allEmployees) {
////                person.name
//                }
//            }
//        }

        // 对象声明不允许有构造方法，且定义的时候就创建了，不需要调用构造方法!
//        Payroll.allEmployees.add()

        // 代码清单4.23 使用对象来实现 Comparator
//        object CaseInsensitiveFileComparator : Comparator<File> {
//            override fun compare(o1: File, o2: File): Int {
//                return o1.parent.compareTo(o2.path, ignoreCase = true)
//            }
//        }

        CaseInsensitiveFileComparator.compare(File("2"), File("1"))
        // 可以在任何可以使用普通对象的地方使用单例对象
        val listOf = listOf(File(""), File("2"))
        // sortedWith 返回一个可以根据特定的比较器排序过的列表
        println(listOf.sortedWith(CaseInsensitiveFileComparator))

        // 代码清单4.24 使用嵌套类实现Comparator
//        data class Person(val name: String) {
//            object NameComparator : Comparator<Person> {
//                override fun compare(o1: Person, o2: Person): Int {
//                    return o1.name.compareTo(o2.name)
//                }
//
//            }
//        }


        /**------------------------- 4.4.2 伴生对象：工厂方法和静态成员的地盘 -------------------------*/
        // companion 获得了直接通过容器类名称来访问这个对象的方法和属性的能力
//        class A{
//            companion object {
//                fun bar{
//                    println("companion object called")
//                }
//            }
//        }

        // 代码清单 4.25 定义一个拥有多个从构造方法的类
//        class User {
//            val nickName: String
//
//            constructor(email: String) {
//                nickName = email.substringBefore("@")
//            }
//
//            constructor(id: Int) {
//                nickName = id.toString()
//            }
//        }
        // 代码清单4.26 使用工厂方法
//        class User2 private constructor(val nickName: String) {
//            companion object {
//                fun newSubUser(email: String) = User2(email.substringBefore("@"))
//                fun newFBUser(id: Int) = User2(id.toString())
//            }
//        }
        User2.newFBUser(123)
        User2.newSubUser("jingbin127@gmail.com")

        /**------------------------- 4.4.3 作为普通对象使用的伴生对象 -------------------------*/
        // 代码清单4.27 声明一个命名伴生对象
//        class Person2(val name: String) {
//            companion object Loader {
//                fun fromJSON(jsonText: String): Object4Activity.Person2 = Person2(jsonText)
//            }
//        }
        Person2.Loader.fromJSON("{name:jingbin}")
        Person2.fromJSON("{name:jingbin}")

        // 代码清单4.28 在伴生对象中实现接口
//        interface JSONFactory<T> {
//            fun fromJSON(jsonText: String): T
//        }
//
//        class Person3 {
//            companion object : Object4Activity.JSONFactory<Person3> {
//                // 实现接口的伴生对象
//                override fun fromJSON(jsonText: String): Person3 = fromJSON(jsonText + "哈哈")
//            }
//        }

        // 4.29 为伴生对象定一个扩展函数
//        class Person4(val firstName: String, val lastName: String) {
//            // 声明一个空的伴生对象
//            companion object {}
//        }
//
//        // 声明一个扩展函数 默认Companion
//        fun Object4Activity.Person4.Companion.fromJSON(jsonText: String): Object4Activity.Person4 = Person4(jsonText, "bin")

        /**------------------------- 4.4.4 对象表达式：改变写法的匿名内部类 -------------------------*/
        // 代码清单 4.30 使用匿名对象来实现事件监听器
//        window.addMouseListener(
//                // 声明一个继承MouseAdapter的匿名对象
//                object : MouseAdapter() {
//                    override fun mouseClicked(e: MouseEvent) {}
//                    override fun mouseEntered(e: MouseEvent) {}
//                })
        // 与对象声明不同，匿名对象不是单例的。每次执行都会创建一个新的对象实例。

        //代码清单4.31 从匿名对象访问局部变量
//        fun countClicks(window: Window) {
//            // 局部变量
//            var clickCount = 0
//            window.addMouseListener(object : MouseAdapter() {
//                override fun mouseClicked(e: MouseEvent) {
//                    // 更新变量的值
//                    clickCount++
//                }
//            })
//        }
    }

    // 代码清单 4.30 使用匿名对象来实现事件监听器

    // 4.29 为伴生对象定一个扩展函数
    class Person4(val firstName: String, val lastName: String) {
        // 声明一个空的伴生对象
        companion object {}
    }

    // 声明一个扩展函数 默认Companion
    fun Person4.Companion.fromJSON(jsonText: String): Person4 = Person4(jsonText, "bin")

    // 代码清单4.28 在伴生对象中实现接口
    interface JSONFactory<T> {
        fun fromJSON(jsonText: String): T
    }

    class Person3 {
        companion object : JSONFactory<Person3> {
            // 实现接口的伴生对象
            override fun fromJSON(jsonText: String): Person3 = fromJSON(jsonText + "哈哈")
        }
    }

    // 代码清单4.27 声明一个命名伴生对象
    class Person2(val name: String) {
        companion object Loader {
            fun fromJSON(jsonText: String): Person2 = Person2(jsonText)
        }
    }

    // 代码清单4.26 使用工厂方法
    class User2 private constructor(val nickName: String) {
        companion object {
            fun newSubUser(email: String) = User2(email.substringBefore("@"))
            fun newFBUser(id: Int) = User2(id.toString())
        }
    }

    // 代码清单 4.25 定义一个拥有多个从构造方法的类
    class User {
        val nickName: String

        constructor(email: String) {
            nickName = email.substringBefore("@")
        }

        constructor(id: Int) {
            nickName = id.toString()
        }
    }


    // 代码清单4.24 使用嵌套类实现Comparator
    data class Person(val name: String) {
        object NameComparator : Comparator<Person> {
            override fun compare(o1: Person, o2: Person): Int {
                return o1.name.compareTo(o2.name)
            }

        }
    }

    // 代码清单4.23 使用对象来实现 Comparator
    object CaseInsensitiveFileComparator : Comparator<File> {
        override fun compare(o1: File?, o2: File?): Int {
            if (o1 != null && o1.parent != null) {
                if (o2 != null) {
                    return o1.parent.compareTo(o2.path, ignoreCase = true)
                }
            }
            return 1
        }
    }

    // 表示一个组织的工资单
    object Payroll {
        val allEmployees = arrayListOf<Person>()
        fun calculateSalary() {
            for (person in allEmployees) {
//                person.name
            }
        }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Object4Activity::class.java)
            context.startActivity(intent)
        }
    }
}
