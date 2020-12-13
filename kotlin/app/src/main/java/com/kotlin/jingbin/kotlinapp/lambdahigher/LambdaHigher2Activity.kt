package com.kotlin.jingbin.kotlinapp.lambdahigher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
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
