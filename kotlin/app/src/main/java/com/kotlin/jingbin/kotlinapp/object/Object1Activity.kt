package com.kotlin.jingbin.kotlinapp.`object`

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.utils.LogUtil
import java.io.Serializable

/**
 * 4.1 定义类继承结构
 */
class Object1Activity : AppCompatActivity() {

     var sss = "ddd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)

        title = "类、对象和接口"


        /**------------------------- 4.1 定义类继承结构-------------------------*/

        /*--------------- 4.1.1 kotlin 中的接口-------------*/

        // 代码清单 1 声明一个简单的接口
//        interface Clickable {
//            fun click()
//        }

        // 代码清单 2 实现一个简单的接口
        class Button : Clickable2 {
            override fun click() {
                println(" I was clicked")
                LogUtil.e(" I was clicked")
            }
        }
        Button().click()
        Button().showOff()
        /**
         * 说明：
         * Kotlin 在后面使用【冒号:】 来代替java中的 extends 和 implements 关键字。
         * 可实现多个接口继承一个类。
         */

        // 代码清单 3 在接口定义一个带方法体的方法

        // 代码清单 4 定义另一个实现了同样方法的接口

        /**
         * 可以在接口中定义一个带方法体的方法。
         * 如果你的类实现了两个接口，这两个接口都定义了相同的方法，则编译器会强制你实现那个方法。
         */

        // 代码清单 5 调用继承自接口方法的实现
        class Button2 : Clickable2, Focusable {
//            override fun click() {
//                LogUtil.e("I was clicked2")
//            }

            override fun click() = LogUtil.e("I was clicked2")


            // 必须提供显示实现
            override fun showOff() {
                /** 使用尖括号加上父类型名字的 "super" 表明了你想要调用哪一个父类的方法*/
                super<Clickable2>.showOff()
                super<Focusable>.showOff()
            }
        }

        // 验证所有继承的方法都能被调用到
        LogUtil.e("--------分割线--------")
        val button = Button()
        button.click()
        button.showOff()
        val button2 = Button2()
        button2.click()
        button2.showOff()
        button2.setFocus(true)


        /*--------------- 4.1.2 open、final和abstract修饰符: 默认为final -------------*/
        /**
         * 《Effective Java》："要么为继承做好设计并记录文档，要么禁止这么做。"
         * Java 的类和方法默认是open的，而 Kotlin 中默认都是final的。
         * 如果你想允许创建一个类的子类，需要使用 open 修饰符来标识这个类。
         * 此外需要给每一个可以被重写的属性或方法添加 open 修饰符。
         */

        // 代码清单 6 声明一个带一个 open 方法的open类
        open class RichButton : Clickable {

            // 这个函数重写了一个open函数并且它本身同样是open的
            override fun click() {}

            // 这个函数是final的：不能在子类中重写它
            fun disable() {}

            // 这个函数使open的：可以在子类中重写它
            open fun animate() {}
        }

        /**
         * 注意：如果你重写了一个基类或者接口的成员，重写了的成员同样默认是open的。
         * 如果你想改变这一行为，阻止子类的操作，可以显示将重写的成员变量设置为final。
         */

        // 代码清单 7 禁止重写
        open class RichButton2 : Clickable {

            // 没有final的override 意味着是open的
            final override fun click() {}
        }

        /**
         * open 类和智能转换
         * 属性默认是final的，可以在大多数属性上不加思考的使用智能转换，这提高了你的代码表现力。
         * if (e is Sum) {
         * // 变量 e 被智能转换了类型
         * return eval(e.left) + eval(e.right)
         * }
         */

        // 代码清单 8 声明一个抽象类
        // 这个类是抽象的，不能直接创建它的实例
        abstract class Animated {

            // 这个函数是抽象的：它没有实现必须被子类重写
            abstract fun animate()

            // 抽象类的抽象函数并不是默认open的，但是可以被标注为open的
            open fun stopAnimating() {}

            fun animateTwice() {}
        }

        // 测试
        class Button3 : Animated() {
            // 一定实现
            override fun animate() {

            }

            // 可以选择实现
            override fun stopAnimating() {
                super.stopAnimating()
            }
            // 不能实现
//            animateTeice()
        }
        /*--------------- 4.1.3 可见性修饰符：默认为 public -------------*/
        /**
         * 修饰符      相关成员                评注
         * final      不能被重写              类中成员默认使用
         * open       可以被重写              需要明确地表明
         * abstract   必须被重写              只能在抽象类中使用：抽象成员不能实现
         * override   重写父类或接口中的成员    如果没有使用final表明，重写的成员默认是开放的
         */

        /**
         * open : 控制继承修饰符。
         * 要被继承的类必须是open修饰的，因为类默认是final的。
         * 同理，要被重写的属性和方法也必须被open修饰。
         */


        /*--------------- 4.1.4 内部类和嵌套类：默认是嵌套类 -------------*/
        // Kotlin 的嵌套类不能访问外部类的实例，除非你特别地做出了要求。
        // 代码清单 9 声明一个包含可序列化状态的视图
//        interface State : Serializable
//        interface View {
//            fun getCurrentState(): ObjectActivity.State
//            fun restoreState(state: ObjectActivity.State) {}
//        }
        // 代码清单 4.10 用带内部类的 Java 代码来实现 View
//    public class Button implements ObjectActivity.View {
//
//        @NotNull
//        @Override
//        public ObjectActivity.State getCurrentState() {
//            return new ButtonState();
//        }
//
//        @Override
//        public void restoreState(@NotNull ObjectActivity.State state) {
//
//        }
//
//        public class ButtonState implements ObjectActivity.State {
//        }
//    }
        /**
         * 问题：
         * 这个代码有什么问题？为什么你会得到一个 java io NotSerializable Exception : Button 异常，如果你试图序列化声明的按钮的状态？
         * 这最开始可能会看起来奇怪：你序列化的变量是 ButtonState 类型的 state ，并不是 Button类型。
         * 当你想起来这是在 Java 中时所有的事情都清楚了，当你在另个类中声明一个类时，它会默认变成内部类。这个例子中的 ButtonState 类隐式地存储了它的外
         * 部Button 类的引用。这就解释了为什么 ButtonState 不能被序列化： Button不是可序列化的，并且它的引用破坏了 ButtonState 的序列化
         * 要修复这个问题，你需要声明 ButtonState 类是 static 的。将一个嵌套类声明为 static 会从这个类中删除包围它的类的隐式引用。
         */
        // Kotlin 中，内部类的默认行为与我们刚刚描述的是相反的
        // 代码清单4.11 在Kotlin中使用嵌套类实现View
//        class Button4 : View {
//            override fun getCurrentState(): State = ButtonState2()
//            override fun restoreState(state: State) {}
//
//            // 这个类与Java中的静态嵌套类类似
//            class ButtonState2 : State {}
//
//        }
        /**
         * 嵌套类和内部类在Java与Kotlin中的对应关系
         * 类A在另一个类B中声明        在Java中           在Kotlin中
         * 嵌套类(不存储外部类的引用)   static class A    class A
         * 内部类(存储外部类的引用)     class A           inner class A
         */

        /*--------------- 4.1.5 密封类：定义受限的类继承结构 -------------*/
        // 代码清单4.1.2 作为接口实现的表达试
//        interface Expr
//        class Num(val value: Int) : ObjectActivity.Expr
//        class Sum(val left: ObjectActivity.Expr, val right: ObjectActivity.Expr) : ObjectActivity.Expr
//
//        fun eval(e: ObjectActivity.Expr): Int =
//                when (e) {
//                    is Num -> e.value
//                    is Sum -> eval(e.right) + eval(e.left)
//                    // 必须检查“else”分支
//                    else -> throw IllegalArgumentException("Unknown expression")
//                }

        // 为解决else的情况，增加sealed类修饰符，对可能创建的子类做出了严格的限制。所有的直接子类必须嵌套在父类中。
        // 代码清单4.1.3 作为密封类的表达式
//        sealed class ExprS {
//            // 将基类标记为密封的..
//            class Num(val value: Int) : ObjectActivity.ExprS()
//
//            // 将所有可能的类作为嵌套类列出
//            class sum(val left: ObjectActivity.ExprS, val right: ObjectActivity.ExprS) : ObjectActivity.ExprS()
//        }
//
//        fun evalS(e: ObjectActivity.ExprS): Int =
//                when (e) {
//                    is ObjectActivity.ExprS.Num -> e.value
//                    is ObjectActivity.ExprS.sum -> evalS(e.left) + evalS(e.right)
//                }


    }

    // 代码清单4.1.3 作为密封类的表达式
    sealed class ExprS {
        // 将基类标记为密封的..
        class Num(val value: Int) : ExprS()

        // 将所有可能的类作为嵌套类列出
        class sum(val left: ExprS, val right: ExprS) : ExprS()
    }

    fun evalS(e: ExprS): Int =
            when (e) {
                is ExprS.Num -> e.value
                is ExprS.sum -> evalS(e.left) + evalS(e.right)
            }

    // 代码清单4.1.2 作为接口实现的表达试
    interface Expr
    class Num(val value: Int) : Expr
    class Sum(val left: Expr, val right: Expr) : Expr

    fun eval(e: Expr): Int =
            when (e) {
                is Num -> e.value
                is Sum -> eval(e.right) + eval(e.left)
                // 必须检查“else”分支
                else -> throw IllegalArgumentException("Unknown expression")
            }


    // 代码清单4.11 在Kotlin中使用嵌套类实现View
    class Button4 : View {
        override fun getCurrentState(): State = ButtonState2()
        override fun restoreState(state: State) {}

        // 这个类与Java中的静态嵌套类类似
        class ButtonState2 : State {}

    }

    // 代码清单 1 声明一个简单的接口
    interface Clickable {
        fun click()
    }

    // 代码清单 3 在接口定义一个带方法体的方法
    interface Clickable2 {
        // 普通的声明
        fun click()

        // 带默认实现的方法
        fun showOff() = LogUtil.e("I'm clickable!")
    }

    // 代码清单 4 定义另一个实现了同样方法的接口
    interface Focusable {
        fun setFocus(b: Boolean) = LogUtil.e("I ${if (b) "got" else "lost"} focus.")
        fun showOff() = LogUtil.e("I'm clickable!")
    }

    // 代码清单 9 声明一个包含可序列化状态的视图
    interface State : Serializable
    interface View {
        fun getCurrentState(): State
        fun restoreState(state: State) {}
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, Object1Activity::class.java)
            context.startActivity(intent)
        }
    }
}
