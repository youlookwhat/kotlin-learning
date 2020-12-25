package com.kotlin.jingbin.kotlinapp.annotations

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.generic.Generic3Activity
import java.time.temporal.Temporal
import kotlin.experimental.ExperimentalTypeInference
import kotlin.reflect.KClass

/**
 * 10.1 声明并应用注解
 * */
class AnnotationsReflection1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotations_reflection)

        /**-------------------------10.1.1 应用注解-------------------------*/

        @Deprecated("Use removeAt(index) instead.", ReplaceWith("removeAt(index)"))
        fun remove(index: Int) {
        }

        /*
        * 指定注解实参的语法与Java有些微小的差别：
        *  - 要把一个类指定为注解实参，在类名后加上 ::class:@MyAnnotation (MyClass:class)
        *  - 要把另一个注解指定为一个实参，去掉直接名称前面的 @。
        *  - 要把一个数组指定为一个实参，使用arrayOf函数：@RequestMapping(path = arrayOf("/foo","/bar"))。
        */


//        @Test(timeout = 100L)
//        fun testMethod() {
//        }

        /**-------------------------10.1.2 注解目标-------------------------*/

        /*
        * @get : Rule
        *
        * @get 使用点目标
        * Rule 注解名称
        */

        /*
        class HasTempFolder{
            // 注解的是 getter 而不是属性
            @get :Rule
            val folder = TemporaryFiolder()
        }*/

        /*
        * Kotlin 支持的使用点目标的完整列表如下：
        *  - property  -- Java的注解不能应用这种使用点目标
        *  - field     -- 为属性生成的字段
        *  - get       -- 属性的getter
        *  - set       -- 属性的setter
        *  - receiver  -- 扩展函数护着扩展属性的接收者参数
        *  - param     -- 构造方法的参数
        *  - setparam  -- 属性setter的参数
        *  - delegate  -- 为委托属性存储委托实例的字段
        *  - file      -- 包含在文件中声明的顶层函数和属性的类
        *
        * 用注解控制 JAVA API
        *  - @JvmName       将改变由Kotlin生成的Java方法或字段的名称
        *  - @JvmStatic     能被用在对象声明合作和伴生对象的方法上，把它们暴露成Java的静态方法
        *  - @JvmOverloads  指导Kotlin编译器为带默认参数值的函数生成多个重载(函数)
        *  - @JvmField      可以应用于一个属性，把这个属性暴露成一个没有访问器的共有Java字段
        */


        /**-------------------------10.1.3 使用注解订制 JSON 序列化-------------------------*/
        /*
        *  @JsonExclude 注解用来标记一个属性，这个属性应该排除在序列化和反序列化之外。
        *  @JsonName    注解让你说明代表这个属性的(JSON)键值对之中的键应该是一个给定的字符串，而不是属性的名称。
        */
//        data class Person(@JsonName("alias") val firstName: String, @JsonExclude val age: Int? = null)


        /**-------------------------10.1.4 声明注解-------------------------*/
        // 对拥有参数的注解来说，在类的主构造方法中声明这些参数：
//        annotation class JsonName(val name:String)

        /*Java中声明同样的注解：*/
//        public @ interface JsonName {String value(); }


        /**-------------------------10.1.5 元注解：控制如何处理一个注解-------------------------*/
        // 可以应用到注解类上的注解被称为 元注解。

        // @ Target 元注解说明了注解可以被应用的元素类型。
//        @Target(AnnotationTarget.PROPERTY_CLASS)
//        annotation class JsonExclude

        /*
        *  @Retention 元注解：
        * 说明你声明的注解是否会存储到.class 文件，以及在运行时是否可以通过反射来访问它。
        * Java 默认会在运行时存在，所以Kotlin的默认行为不同：注解拥有RUNTIME保留期。
        * */

        /**-------------------------10.1.6 使用类做注解参数-------------------------*/
        /*
        interface Company {
            val name: String
        }

        data class CompanyImpl(override val name: String) : Company
        data class Person(
                val name: String,
                @DeserializeInterface(CompanyImpl::class) val company: Company
        )

        annotation class DeserializeInterface(val targetClass: KClass<out Any>)
        */

        /**-------------------------10.1.7 使用泛型类做注解参数-------------------------*/

        // @CustomSerializer 注解接收一个自定义序列化器类的引用作为实参。这个序列化器类应该实现 ValueSerializer 接口：
//        interface ValueSerializer<T> {
//            fun toJsonValue(value: T): Any?
//        }
    }

//    interface ValueSerializer<T> {
//        fun toJsonValue(value: T): Any?
//        fun fromJsonValue(jsonValue: Any?): T
//    }
//
//    data class Person2(
//            val name: String,
//            @CustomSerializer(DateSerializer::class) val company: Date
//    )
//
//    annotation class CustomSerializer(val targetClass: KClass<out Any>)


    interface Company {
        val name: String
    }

    data class CompanyImpl(override val name: String) : Company
    data class Person(
            val name: String,
            @DeserializeInterface(CompanyImpl::class) val company: Company
    )

    annotation class DeserializeInterface(val targetClass: KClass<out Any>)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, AnnotationsReflection1Activity::class.java)
            context.startActivity(intent)
        }
    }
}