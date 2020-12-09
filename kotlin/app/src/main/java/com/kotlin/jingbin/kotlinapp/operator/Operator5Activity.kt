package com.kotlin.jingbin.kotlinapp.operator

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 7.5 重用属性访问的逻辑：委托属性
 * */
class Operator5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operator1)

        /*
        * Kotlin中最独特和最强大的功能：委托属性
        * 委托是一种设计模式，操作的对象不用自己执行，而是把工作微委托给另一个辅助的对象。我们把辅助对象称为委托。
        */


        /**-------------------- 7.5.1 委托属性的基本操作 ----------------------*/
        // 基础语法：
//        class Foo {
        // 关键字 by 把属性关联上委托对象
//            var p: Type by Delegate()
//        }

        class Foo {
            // 编译器会自动生成一个辅助属性
//            private val delegate = Delegate()
            // p 的访问都会调用对应的 delegate 的getValue和setValue方法
//            var p: Type
//                set(value: Type) = delegate.setValue(..., value)
//                get() = delegate.getValue(...)
        }

        class Delegate {

            // getValue 包含了实现setter的逻辑
//        fun getValue(...): Type {
//            ...
//        }

            // setValue 包含了实现setter的逻辑
//        fun setValue(x: Int, any: Type) {
//            ...
//        }
//
        }

//        val foo = Foo()
        // 通过调用 delegate.getValue(...)来实现属性的修改
//        val oldValue = foo.p
        // 通过调用 delegate.setValue(...,newValue)来实现属性的修改
//        foo.p = newValue


        /**-------------------- 7.5.2 使用委托属性：惰性初始化和 by lazy() ----------------------*/
        // 惰性初始化时一种常见的模式，知道在第一次访问该属性的时候，才根据需要创建对象的一部分。

        class Email


        // 使用额外的 _emails 属性来实现惰性加载，在没有加载之前为null，然后加载为邮件列表

        // 代码清单7.17 使用支持属性来实现惰性初始化
        class Person(val name: String) {
            // _emails 属性用来保存数据，关联委托
            private var _emails: List<Email>? = null
            val emails: List<Email>
                get() {
                    if (_emails == null) {
                        // 访问时加载邮件
                        _emails = loadEmails(this)
                    }
                    //  如果已经加载，就直接返回
                    return _emails!!
                }

            fun loadEmails(person: Person): List<Email> {
                println("Load email for ${person.name}")
                return listOf()
            }
        }

        val p = Person("jingbin")
        p.emails// 第一次访问会加载邮件: Load email for jingbin
        p.emails
        /*
        * 这里使用了所谓的 支持属性技术。有一个属性 _emails 用来存储这个属性，而另一个email用来提供对属性的读取访问。
        * 这样代码有点啰嗦，而且线程不安全，kotlin有更好的方案，使用标准库函数 lazy 返回的委托。
        */


        // 代码清单7.18 用委托属性来实现惰性初始化
        class Person2(val name: String) {
            // lazy 的参数是一个Lambda，可以调用它来初始化这个值，且默认是线程安全的。
            val emails by lazy { loadEmail(this) }

            private fun loadEmail(person: Person2): List<Email> {
                println("Load email2 for ${person.name}")
                return listOf()
            }
        }


        /**-------------------- 7.5.3 实现委托属性 ----------------------*/
        // 代码清单7.19 使用 PropertyChangeSupport 的工具类
        open class PropertyChangeAware {

            protected val changeSupport = PropertyChangeSupport(this)

            fun addPropertyChangeListener(listener: PropertyChangeListener) {
                changeSupport.addPropertyChangeListener(listener)
            }

            fun removePropertyChangeListener(listener: PropertyChangeListener) {
                changeSupport.removePropertyChangeListener(listener)
            }
        }

        // 代码清单7.20 手工实现属性修改的通知
        class Person3(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
            var age: Int = age
                set(newValue) {
                    // field 标识符允许你访问属性背后的支持字段
                    val oldValue = field
                    field = newValue
                    changeSupport.firePropertyChange("age", oldValue, newValue)
                }

            var salary: Int = salary
                set(newValue) {
                    val oldValue = field
                    field = newValue
                    changeSupport.firePropertyChange("salary", oldValue, newValue)
                }
        }

        val p3 = Person3("jingbin", 30, 40000)
        // 关联监听器，用于监听属性修改
        p3.addPropertyChangeListener(PropertyChangeListener { it ->
            println("Property ${it.propertyName} changed" + "from ${it.oldValue} to ${it.newValue}")
        })
        p3.age = 35 // Property age changed form 30 to 35
        p3.salary = 60000 // Property salary changed form 40000 to 60000


        // 代码清单7.21 提过辅助类来实现属性变化的通知
        class ObservableProperty(val propName: String, var propValue: Int, val changeSupport: PropertyChangeSupport) {
            fun getValue(): Int = propValue
            fun setValue(newValue: Int) {
                val oldValue = propValue
                propValue = newValue
                changeSupport.firePropertyChange(propName, oldValue, newValue)
            }
        }

        class Person4(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
            val _age = ObservableProperty("age", age, changeSupport)
            var age: Int
                get() = _age.getValue()
                set(value) {
                    _age.setValue(value)
                }

            val _salary = ObservableProperty("salary", salary, changeSupport)
            var salary: Int
                get() = _salary.getValue()
                set(value) {
                    _salary.setValue(value)
                }
        }
        /*
        * 你创建了一个保存属性值的类，并在修改属性时自动触发更改通知。你删除了重复的逻辑代码，但是需要相当多的样板代码来为每个属性创建
        * ObservableProperty 实例，并把getter和setter委托给它。Kotlin的委托属性可以让你摆脱这些样板代码。
        */

        // 代码清单7.22 ObservableProperty 作为属性委托
//        class ObservableProperty2(var propValue: Int, val changeSupport: PropertyChangeSupport) {
//            operator fun getValue(p: Person5, prop: KProperty<*>): Int = propValue
//            operator fun setValue(p: Person5, prop: KProperty<*>, newValue: Int) {
//                val oldValue = propValue
//                propValue = newValue
//                changeSupport.firePropertyChange(prop.name, oldValue, newValue)
//            }
//        }

        // 代码清单7.23 使用委托属性来绑定更改通知
//        class Person5(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
//            var age: Int by ObservableProperty2(age, changeSupport)
//            var salary: Int by ObservableProperty2(salary, changeSupport)
//        }

        // 右边的对象被称为委托，Kotlin会自动将委托存储在隐藏的属性中，并在访问或修改属性时调用委托的geyValue，和setValue

        // 代码清单7.24 使用Delegates.observable来实现属性修改的通知
        class Person5(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
            private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
                changeSupport.firePropertyChange(prop.name, oldValue, newValue)
            }
            var age: Int by Delegates.observable(age, observer)
            var salary: Int by Delegates.observable(salary, observer)
        }

        /*
        * by 右边的表达式不一定是新创建的实例，也可以是函数调用、另一个属性、或任何其他表达式，
        * 只要这个表达式的值，是能够被编译器用正确的参数类型来调用getValue和setValue的对象。
        */


        /**-------------------- 7.5.4 委托属性的变换规则 ----------------------*/
//        class C{
//            var prop: Type by MyDelegate()
//        }
//        val c = C()

        /*
        * MyDelegate 实例会被保存到一个隐藏的属性中，它被称为<delegate>。编译器也会将用一个KProperty类型的对象来代表这个属性，它被称为<property>。
        * 编译器生成的代码如下：
        */
//        class C {
//            private val <delegate> = MyDelegate()
//            var prop: Type
//                get() = <delegate>.getValue(this, <property>)
//                set(value:Type) = <delegate>.setValue(this,<property>,value)
//        }

        /*
        * val x = c.prop  -> val x = <delegate>.getValue(c, <property>)
        * c.prop = x      -> <delegate>.setValue(c, <property>, x)
        */


        /**-------------------- 7.5.5 在 map 中保存属性值 ----------------------*/
        // 代码清单7.25 定义一个属性，把值存在map
        class Person6 {
            private val _attributes = hashMapOf<String, String>()
            fun setAttribute(attrName: String, value: String) {
                _attributes[attrName] = value
            }

            // 从map手动检索属性
            val name: String
                get() = _attributes["name"]!!
        }

        val p6 = Person6()
        val data = mapOf("name" to "jingbin", "company" to "ali")
        for ((attrName, value) in data) {
            p6.setAttribute(attrName, value)
        }
        println(p.name) // jingbin

        // 代码清单7.26 使用委托属性把值存到map中
        class Person7 {
            private val _attributes = hashMapOf<String, String>()
            fun setAttribute(attrName: String, value: String) {
                _attributes[attrName] = value
            }

            // 把 map 作为委托属性
            val name: String by _attributes
        }

        /**-------------------- 7.5.6 框架中的委托属性 ----------------------*/
        // 代码清单7.27 使用委托属性来访问数据库列
        // user 对应数据库中的表
//        object Users : IdTable() {
        // name 和 age 对应数据库表的列
//            val name: varchar("name", length = 50).index()
//            val age = ingeter("age")
//        }

        // 每一个User示例对应表中的一个实体
//        class User(id: EntityID) : Entity(id) {
        // name 的值是数据库中对应那个用户的值
//            var name: String by Users.name
//            var age: Int by Users.age
//        }
    }

    // 代码清单7.19 使用 PropertyChangeSupport 的工具类
    open class PropertyChangeAware {

        protected val changeSupport = PropertyChangeSupport(this)

        fun addPropertyChangeListener(listener: PropertyChangeListener) {
            changeSupport.addPropertyChangeListener(listener)
        }

        fun removePropertyChangeListener(listener: PropertyChangeListener) {
            changeSupport.removePropertyChangeListener(listener)
        }
    }

    // 代码清单7.22 ObservableProperty 作为属性委托
    class ObservableProperty2(var propValue: Int, val changeSupport: PropertyChangeSupport) {
        operator fun getValue(p: Person5, prop: KProperty<*>): Int = propValue
        operator fun setValue(p: Person5, prop: KProperty<*>, newValue: Int) {
            val oldValue = propValue
            propValue = newValue
            changeSupport.firePropertyChange(prop.name, oldValue, newValue)
        }
    }

    // 代码清单7.23 使用委托属性来绑定更改通知
    class Person5(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
        var age: Int by ObservableProperty2(age, changeSupport)
        var salary: Int by ObservableProperty2(salary, changeSupport)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Operator5Activity::class.java)
            context.startActivity(intent)
        }
    }
}
