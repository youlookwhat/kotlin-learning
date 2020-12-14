package com.kotlin.jingbin.kotlinapp.lambdahigher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * 8.2 内联函数：消除lambda带来的运行时开销
 * */
class LambdaHigher2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda_higher)
        title = "8.2 内联函数：消除lambda带来的运行时开销"

        /**-------------------- 8.2.1 内联函数如何运作 ----------------------*/
        // 代码清单8.13 定义一个内联函数
        // 函数用于确保一个共享资源不会并发地被多个线程访问。函数锁住一个Lock对象，执行代码块，然后释放锁。
//        inline fun <T> synchronized(lock: Lock, action: () -> T): T {
//            lock.lock()
//            try {
//                return action()
//            } finally {
//                lock.unlock()
//            }
//        }

        val l = ReentrantLock()
        synchronized(l) {}

        fun foo(l: Lock) {
            println("Before sync")
            synchronized(l) {
                println("Action")
            }
            println("After sync")
        }

        // 编译后的 foo 函数为：
        fun _foo() {
            println("Beforesync ")
            l.lock()
            try {
                println("Action")
            } finally {
                l.unlock()
            }
            println("After sync")
        }

        // 在调用内联函数的时候也可以传递函数类型的变量作为参数
        class LockOwner(val lock: Lock) {
            fun runUnderLock(body: () -> Unit) {
                synchronized(lock, body)
            }
        }

        // runUnderLock 会被编译成类似于以下函数的字节码
        class LockOwner2(val lock: Lock) {
            fun _runUnderLock(body: () -> Unit) {
                lock.lock()
                try {
                    // body 没有内联，因为在调用的地方还没有lambda
                    body()
                } finally {
                    lock.unlock()
                }
            }
        }


        /**-------------------- 8.2.2 内联函数的限制 ----------------------*/
        // 一般来说，参数如果被直接调用或者作为参数传递给另一个 inline 函数，他是可以被内联的。
//        fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> {
//            return TransformingSequence(this, transform)
//        }

        // 接收非内联lambda的参数，可以用 noinline 修饰符来标记它：
//        inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
//
//        }


        /**-------------------- 8.2.3 内联集合操作 ----------------------*/
        // 代码清单8.14 使用lambda过滤一个集合
        data class Person(val name: String, val age: Int)

        val people = listOf(Person("jingbin", 28), Person("mayun", 44))
        println(people.filter { it.age < 30 })// [Person(name=jingbin, age=28)]

        // 代码清单8.15 手动过滤一个集合
        val result = mutableListOf<Person>()
        for (person in result) {
            if (person.age < 30) {
                result.add(person)
            }
        }
        println(result)// [Person(name=jingbin, age=28)]

        /*
        *  filter被声明成了内联函数，以上两个代码产生的字节码其实是一样的。
        */
        println(people.filter { it.age < 30 }.map(Person::name))// jingbin

        /**
         * filter 和 map 都是内联函数。
         * filter 创建了一个中间集合来保存列表过滤的结果，然后map函数生成的代码会读取这个这个结果。
         * 如果有大量的元素处理，中间的开销还有问题，这时可以在调用链的后面加上一个 asSequence 调用，
         * 用序列来替代集合，大量的数据处理时加上即可。
         */
        // 用序列替代集合？
        println(people.filter { it.age < 30 }.map(Person::name).asSequence())// jingbin
        println(people.asSequence().filter { it.age < 30 }.map(Person::name))// jingbin


        /**-------------------- 8.2.4 决定何时将函数声明成内联 ----------------------*/
        /*
        * 使用 inline 关键字只能提高带有lambda参数的函数的性能，其他的情况需要额外度量和研究。
        * Kotlin标准库中的内联函数总是很小的。
        */

        /**-------------------- 8.2.5 使用内联lambda管理资源----------------------*/

//        val l :Lock = ...
        // 在加锁的情况下执行指定的操作
//        l.withLock {  }

        // withLock 函数的定义：
        // action： 需要加锁的地方被抽取到一个独立的方法中
        fun <T> Lock.withLock(action: () -> T): T {
            lock()
            try {
                return action()
            } finally {
                unlock()
            }
        }

        // 代码清单8.16 在Java中使用 try-with-resource
        //    static String readFirstLineFromFile(String path) throws IOException {
        //        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
        //            return bufferedReader.readLine();
        //        }
        //    }

        // 代码清单8.17 使用use函数作资源管理。[不会引发任何的性能开销]
        fun readFirstLineFromFile(path: String): String {
            // 构建 BufferedReader ，调用 use 函数，传递一个 lambda 执行文件操作
            BufferedReader(FileReader(path)).use { br ->
                // 从函数中返回文件的一行
                return br.readLine()
            }
        }
    }

    // // 接收非内联lambda的参数，可以用 noinline 修饰符来标记它：
    inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {

    }

    // 代码清单8.13 定义一个内联函数
    inline fun <T> synchronized(lock: Lock, action: () -> T): T {
        lock.lock()
        try {
            return action()
        } finally {
            lock.unlock()
        }
    }

//    val l = Lock()
//    synchronized(l){}

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, LambdaHigher2Activity::class.java)
            context.startActivity(intent)
        }
    }
}
