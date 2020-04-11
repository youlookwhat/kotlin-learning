package com.kotlin.jingbin.kotlinapp.website

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R

/**
 * 网站学习：类与对象
 *  - 类与继承：https://www.kotlincn.net/docs/reference/classes.html
 * */
class ClassesAndObjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        // 1.构造函数
        class Person constructor(firstName: String)

        class Personer(name: String)

        // 2.次构造函数
        class Person1 {
            var children1: MutableList<Person1> = mutableListOf<Person1>();

            constructor(parent: Person1) {
                parent.children1.add(this)
            }
        }

        class Person2(val name: String) {
            var children2: MutableList<Person2> = mutableListOf<Person2>();

            constructor(name: String, parent: Person2) : this(name) {
                parent.children2.add(this)
            }
        }

        class DontCreateMe private constructor() { /*……*/ }

    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, ClassesAndObjectsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
