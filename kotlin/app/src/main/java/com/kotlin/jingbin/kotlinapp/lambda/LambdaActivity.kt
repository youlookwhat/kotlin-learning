package com.kotlin.jingbin.kotlinapp.lambda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.jingbin.kotlinapp.R
import com.kotlin.jingbin.kotlinapp.lambda.LambdaActivity.Person
import kotlinx.android.synthetic.main.activity_lambda.*

/**
 * 5.1 Lambda表达式和成员引用
 * */
class LambdaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lambda)

        title = "Lambda表达式和成员引用"

        /*--------------- 5.1.1 Lambda简介：作为函数参数的代码块-------------*/
        // 代码清单5.1 用匿名内部类实现监听器 java
        /* Java */
//        tv_click.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick (View view) {
//                ／＊点击后执行的动作＊／
//            }
//        }

        // 代码清单5.2 用lambda实现监听器 kotlin
        tv_click.setOnClickListener { }

        /*--------------- 5.1.2 Lambda和集合-------------*/
        data class Person(val name: String, val age: Int)

        // 代码清单5.3 手动在集合中搜索
        fun findTheOldest(people: List<Person>) {
            var maxAge = 0
            // 储存年龄最大的人
            var theOldest: Person? = null
            for (person in people) {
                if (person.age > maxAge) {
                    maxAge = person.age
                    theOldest = person
                }
            }
            println(theOldest)
        }

        val listOf = listOf(Person("Alice", 29), Person("Bob", 28))
        findTheOldest(listOf)// Person(name=Alice, age=29)

        // 代码清单 5.4 用lambda在集合中搜索
        val listOf2 = listOf(Person("Alice", 29), Person("Bob", 28))
        // 比较年龄找到最大的元素
        println("5.4--" + listOf2.maxBy { it.age })// 5.4--Person(name=Alice, age=29)

        // 代码清单5.5 用成员引用搜索
//        listOf2.maxBy { Person :: age }


        /*--------------- 5.1.3 Lambda表达式的语法-------------*/
        val sum = { x: Int, y: Int -> x + y }
        // -> 前面是参数 后面是函数体，且lambda始终在{}内

        // 调用保存在变量中的lambda
        println(sum(1, 1))

        // 可以直接调用lambda表达式(无意义)
        val log = { println(42) }()

        // 可以使用库函数 run 来运行传给它的lambda，运行lambda中的代码块
        kotlin.run { println(66) }

        // 回到这行代码
        val listOf3 = listOf(Person("Alice", 29), Person("Bob", 28))
        // 1、{}里是一个lambda表达式，把它作为实参传给函数。这个lambda接收一个类型为Person的参数并返回它的年龄
        listOf3.maxBy({ p: Person -> p.age })
        // 2、kotlin语法约定：如果lambda表达式是函数调用的最后一个实参，可以放在括号的外边。
        listOf3.maxBy() { p: Person -> p.age }
        // 3、当lambda时函数唯一的实参时，还可以去掉调用代码中的空括号对
        listOf3.maxBy { p: Person -> p.age }

        // 代码清单5.6 把lambda作为命名实参传递
        val listOf4 = listOf(Person("Alice", 29), Person("Bob", 30))
        val name = listOf4.joinToString(separator = " ", transform = { p: Person -> p.name })
        println(name)// Alice Bob

        // 代码清单5.7 把lambda放在括号外传递
        listOf4.joinToString(" ") { p: Person -> p.name }

        // 代码清单5.8 省略lambda参数类型
        // 显示地次写出参数类型
        listOf4.maxBy { p: Person -> p.age }
        // 推导出参数类型。和局部变量一样，如果lambda参数的类型可以被推导出来，你就不需要显示地指定它。
        listOf4.maxBy { p -> p.age }

        // 代码清单5.9 使用默认参数名称
        // 使用默认参数名称it代替命名参数，仅在实参名称没有显示地指定时这个默认的名称才会生成
        listOf4.maxBy { it.age }
        // 如果你用变量存储lambda，那么就没有可以推断出参数类型的上下文，所以你必须显示地指定参数类型
        val getAge = { p: Person -> p.age }
        listOf4.maxBy(getAge)
        // lambda可以包含更多的语句
        val sum2 = { x: Int, y: Int ->
            println("the sum of $x and $y ..")
            x + y
        }
        println(sum2(1, 1))


        /*--------------- 5.1.4 在作用域中访问变量-------------*/
        // 代码清单5.10 在lambda中使用函数参数
        fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
            // 接收lambda作为实参，指定对每个元素的操作
            messages.forEach {
                // 在lambda中访问“prefix”参数
                println("$prefix $it")
            }
        }

        val errors = listOf("403 Forbidden", "404 Not Found")
        printMessagesWithPrefix(errors, "Error:")

        // 代码清单5.11 在lambda中改变局部变量
        fun printProblemCounts(responses: Collection<String>) {
            var clientErrors = 0
            var serverErrors = 0
            responses.forEach {
                if (it.startsWith("4")) {
                    clientErrors++
                } else if (it.startsWith("5")) {
                    serverErrors++
                }
            }
            println("$clientErrors client errors, $serverErrors server errors")
        }

        val responses = listOf("200 ok", "418 I'm a teapot", "500 Internal Server Error")
        // 1 client errors, 1 server errors
        printProblemCounts(responses)
        /**
         * 和Java不一样，Kotlin允许在lambda内部访问非final变量甚至修改它们。我们称这些变量被lambda捕捉。
         * 原理：
         * 1、当你捕捉 final 变量时，它的值和使用这个值的 lambda 代码存储。
         * 2、而对非 final 变量来说，它的值被封装在一个特殊的包装器中，这样你就可以改变这个值，而对这个包装器的引用会和 lambda 代码一起存储。
         */
        // 模拟捕捉可变变量的类
        class Ref<T>(var value: T)

        val ref = Ref(0)
        // 形式上是不变量被捕捉了，但是存储在字段中的实际值是可以修改的。
        val inc = { ref.value++ }

        // 上面的是这里的原理
        var counter = 0
        val inc1 = { counter++ }


        /*--------------- 5.1.5 成员引用-------------*/
        // 来源：如果你想要当做参数传递的代码已经被定义成了函数，该怎么办？使用 :: 预算符将函数转换为一个值就可以传递它。
        /**  :: 之前是类； :: 之后是成员(一个方法或一个属性)，成员后面不能加括号 */
        val getAge2 = Person::age

        // 同样的功能
        val getAges = { p: Person -> p.age }
        // 成员引用和调用该函数的lambda具有一样的类型，所以可以互换使用
        listOf4.maxBy(Person::age)

        // 还可以使用顶层函数(不是类的成员)
        fun salute() = println("Salute!")
        run(::salute)

        // 如果lambda要委托给一个接收多个参数的函数，提供成员引用代替它将会非常方便
        fun sendEmail(person: Person, message: String) = "$person send $message !"
        val action = { person: Person, message: String ->
            // 这个lambda委托给sendEmail函数
            sendEmail(person, message)
        }
        // 可以用成员引用代替
        val nextEmail = ::sendEmail

        action(Person("action", 1), "哈哈哈")
        nextEmail(Person("nextEmail", 1), "哈哈哈")

        // 可以用构造方法引用存储或延期执行创建类实例的动作。构造方法引用的形式是在双冒号后指定类名称：
//        data class Person(val name: String, val age: Int)
        // 创建"Person"实例的动作被保存成了值
        val createPerson = ::Person
        val p = createPerson("Alice", 29)
        println(p)// Person(name=Alice, age=29)

        // 还可以用同样的方式引用扩展函数:
        fun Person.isAdult() = age >= 21
        val kFunction1 = Person::isAdult
        // 尽管isAduth不是Person类的成员，还是可以通过引用访问它，这和访问实例的成员没什么两样:person.isAduth()

        // 绑定引用
        val p2 = Person("Dmitry", 22)
        val personsAgeFunction = Person::age
//        val personsAgeFunction0 = fun(p:Person):Int =p.age
//        val personsAgeFunction1 = fun(p:Person):Int {
//            return p.age
//        }
//        val personsAgeFunction2 = { p: Person -> p.age }
        println(personsAgeFunction(p2))// 22

        // 在kotlin1.1中可以使用绑定成员引用
        val dmitrysAgeFunction = p2::age
        println(dmitrysAgeFunction)

        /**
         * 注意：
         * personsAgeFunction 是一个单参数函数（返回给定人的年龄），
         * 而 dmitrysAgeFunction 一个零参数的函数（返回已经指定好的人的年龄）
         * 在 Kotlin 1.1 之前，你需要显式地写出 lambda { p. age ｝，而不是使用绑定成员引用 p: age
         */
    }

    data class Person(val name: String, val age: Int)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, LambdaActivity::class.java)
            context.startActivity(intent)
        }
    }
}