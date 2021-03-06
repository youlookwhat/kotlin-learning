# kotlin-learning

|书籍：[《Kotlin实战》][1]、GitHub：[JetBrains/kotlin][2]、[Kotlin 语言中文站][3]|
|:----:|

>
 - 书籍：[《Kotlin实战》][1]
 - GitHub：[JetBrains/kotlin][2]
 - [Kotlin 语言中文站][3]

**第一部分 Kotlin 简介**

 - [1.Kotlin: 定义和目的](https://github.com/youlookwhat/kotlin-learning/blob/master/01.Kotlin%20%E5%AE%9A%E4%B9%89%E5%92%8C%E7%9B%AE%E7%9A%84.md)
 - [2.Kotlin 基础](https://github.com/youlookwhat/kotlin-learning/blob/master/02.Kotlin%20%E5%9F%BA%E7%A1%80.md)
 - [3.函数的定义和目的](https://github.com/youlookwhat/kotlin-learning/blob/master/03.%E5%87%BD%E6%95%B0%E7%9A%84%E5%AE%9A%E4%B9%89%E4%B8%8E%E8%B0%83%E7%94%A8.md)
 - [4.类、对象和接口](https://github.com/youlookwhat/kotlin-learning/blob/master/04.%E7%B1%BB%E3%80%81%E5%AF%B9%E8%B1%A1%E5%92%8C%E6%8E%A5%E5%8F%A3.md)
 - [5.lambda 编程](https://github.com/youlookwhat/kotlin-learning/blob/master/05.lambda%20%E7%BC%96%E7%A8%8B.md)
 - [6.Kotlin 的类型系统](https://github.com/youlookwhat/kotlin-learning/blob/master/06.kotlin%E7%9A%84%E7%B1%BB%E5%9E%8B%E7%B3%BB%E7%BB%9F.md)

**第二部分 拥抱Kotlin**

 - [7.运算符重载及其他约定](https://github.com/youlookwhat/kotlin-learning/blob/master/07.%E8%BF%90%E7%AE%97%E7%AC%A6%E9%87%8D%E8%BD%BD%E5%8F%8A%E5%85%B6%E4%BB%96%E7%BA%A6%E5%AE%9A.md)
 - [8.高阶函数：Lambda作为形参和返回值](https://github.com/youlookwhat/kotlin-learning/blob/master/08.%E9%AB%98%E9%98%B6%E5%87%BD%E6%95%B0%EF%BC%9ALambda%E4%BD%9C%E4%B8%BA%E5%BD%A2%E5%8F%82%E5%92%8C%E8%BF%94%E5%9B%9E%E5%80%BC.md)
 - [9.泛型](https://github.com/youlookwhat/kotlin-learning/blob/master/09.%E6%B3%9B%E5%9E%8B.md)
 - [10.注解与反射](https://github.com/youlookwhat/kotlin-learning/blob/master/10.%E6%B3%A8%E8%A7%A3%E4%B8%8E%E5%8F%8D%E5%B0%84.md)
 - 11.DSL 构建

## 1.Kotlin: 定义和目的
本章内容包括：
> - Kotlin 的基本示范
> - Kotlin 语言的主要特征
> - Android 和服务端开发的可能性
> - Kotlin 与其他语言的区别
> - 用 Kotlin 编写并运行代码

### 总结
 - Kotlin 是静态类型语言并支持类型推导，允许维护正确性与性能的同时保持源代码的整洁。
 - Kotlin 支持面向对象很函数式的两种编程风格，通过头等函数使更高级别的抽象成为可能，通过支持不可变值简化了测试和多线程开发。
 - 在服务端应用程序中它工作的很好，全面支持现在所有的java框架，为常见的任务提供了新工具，如生成HTML和持久化。
 - 在 Android 上它也可以工作，这得益于紧凑的运行时、对 Andrid API 特殊的编辑器支持以及丰富的库，为常见Android开发任务提供了 Kotlin 友好的函数。
 - 它是免费和开源的，全面支持主流的 IDE 和构建系统。
 - Kotlin 是务实的、安全的、简洁的，与 Java 可互操作，意味着它专注于已经使用证明过的解决方案处理常见任务，防止常见的像 NullPointerException 这样的错误，支持紧凑的易读的代码，以及提供与 Java 无限制的集成。


## 2.Kotlin基础

本章内容包括：
> - 声明函数、变量、类、枚举以及类型
> - Kotlin中的控制结构
> - 智能转换
> - 抛出和处理异常

### 总结
 - fun 关键字用来声明函数。Val关键字和var关键字分别用来声明只读变量和可变变量。
 - 字符串模板帮组你避免繁琐的字符串拼接。在变量名称前加上 $ 前缀或者用 ${} 包围一个表达式，来把值注入到字符串中。
 - 值对象类在Kotlin中以简洁的方式表示。
 - 熟悉的if现在是带返回值的表达式。
 - when表达式类似于Java中的switch但功能更强大。
 - 在检查过变量具有某种类型之后不必显示地转换它的类型:编译器使用智能转换字段帮你完成。
 - for、while、和 do-while 循环与java类似，但是for循环现在更加方便，特别是当你需要迭代map的时候，又或是迭代集合需要下标的时候。
 - 简洁的语法 1..5 会创建一个区间。区间和数列允许Kotlin在for循环中使用统一的语法和同一套抽象机制，并且还可以使用in运算符和!in运算符来检查值是否属于某个区间。
 - Kotlin中的异常处理和java非常相似，除了Kotlin不要求你声明函数可以抛出异常。


## 3.函数的定义与调用

本章内容包括：
> - 用于处理集合、字符串和正则表达式的函数
> - 使用命名参数、默认参数，以及中辍调用的语法
> - 通过扩展函数和属性来适配Java库
> - 使用顶层函数、局部函数和属性架构代码

### 总结
 - Kotlin 没有定义自己的集合类，而是在Java集合类的基础上提供了更丰富的API。
 - Kotlin 可以给函数参数定义默认值，这样大大降低了重载函数的必要性，而且命名参数让多参数函数的调用更加易读。
 - Kotlin 允许更灵活的代码结构：函数和属性都可以直接在文件中声明，而不仅仅在类中作为成员。
 - Kotlin 可以用扩展函数和属性来扩展任何类的API,包括在外部中定义的类，而不需要修改其源代码，也没有运行时的开销。
 - 中辍调用提供了处理单个参数的，类似调用运算符方法的简明语法。
 - Koltin 为普通字符串和正则表达式都提供了大量的方便字符串处理的函数。
 - 三重引号的字符串提供了一种简洁的方式，解决了原本在Java中需要进行大量啰嗦的转义和字符串连接的问题。
 - 局部函数帮助你保持代码的整洁的同时，避免重复。

## 4.类、对象和接口

本章内容包括：
> - 类和接口
> - 非默认属性和构造方法
> - 数据类
> - 类委托
> - 使用 object 关键字

### 总结
- Kotlin 的接口与 Java 的相似，但是可以包含默认实现 (Java 从第8版才开始支持）和属性。
- 所有的声明默认都是 final public
- 要想使声明不是 final 的，将其标记为 open
- internal 声明在同一模块中可见。
- 嵌套类默认不是内部类。使用 inner 关键字来存储外部类的引用。
- sealed 类的子类只能嵌套在自身的声明中（Kotlin 1.1 允许将子类放置在同一文件的任意地方）。
- 初始化语句块和从构造方法为初始化类实例提供了灵活性
- 使用 field 标识符在访问器方法体中引用属性的支持字段
- 数据类提供了编译器生成的 equals hashCode toString copy 和其他方法。
- 类委托帮助避免在代码中出现许多相似的委托方法。
- 对象声明是Kotlin 中定义单例类的方法。
- 伴生对象（与包级别函数和属性 起）替代了 Java 静态方法和字段定义
- 伴生对象与其他对象一样，可以实现接口，也可以拥有有扩展函数和属性
- 对象表达式是 Kotlin 中针对 Java 匿名内部类的替代品，并增加了诸如实现个接口的能力和修改在创建对象的作用域中定义的变 的能力等功能

## 5.lambda 编程

本章内容包括：
> - Lambda 表达式和成员引用
> - 以函数式风格使用集合
> - 序列：惰性地执行集合操作
> - 在 Kotlin中使用 Java 函数式接口
> - 使用带接收者的 lambda

### 总结
- Lambda 允许你把代码块当作参数传递给函数。
- Kotlin 可以把 lambda 放在括号外传递给函数，而且可以用 it 引用单个的lambda 参数。
- lambda 中的代码可以访问和修改包含这个 lambda 调用的函数中的变量。
- 通过在函数名称前加上前缀 :: ，可以创建方法、构造方法及属性的引用，并用这些引用代替 lambda 传递给函数。
- 使用像 filter map all any 等函数，大多数公共的集合操作不需要手动迭代元素就可以完成。
- 序列允许你合并一个集合上的多次操作，而不需要创建新的集合来保存中间结果。
- 可以把 lambda 作为实参传给接 Java 函数式接口（带单抽象方法的接口，也叫作 SAM 接口）作为形参的方法。
- 带接收者的 lambda 种特殊的 lambda ，可以在这种 lambda 中直接访问一个特殊接收者对象的方法。
- with 标准库函数允许你调用同一个对象的多个方法，而不需要反复写出这个对象的引用 apply 函数让你使用构建者风格的 API 创建和初始化任何对象


## 6.Kotlin 的类型系统

本章内容包括：
> - 处理 null 的可空类型和语法
> - 基本数据类型和它们对应的Java类型
> - Kotlin 的集合，以及它们和Java的关系

### 总结
- Kotlin 对可空类型的支持，可以帮助我们在编译期，检测出潜在的NullPointerException错误。
- Kotlin 提供了像安全调用（？．）、 Elvis 运算符（？：）、非 言（ ！！）及let 函数这样的工具来简洁地处理可空类型。
- as ？运算符提供了 种简单的方式来把值转换成 个类型，以及处理当它拥有不同类型时的情况。
- Java 中的类型在 Kotlin 中被解释成平台类型，允许开发者把它们当作可空或非空来对待。
- 表示基本数字的类型（如 Int ）看起来用起来都像普通的类，但通常会被编译成 Java 基本数据类型。
- 可空的基本数据类型（如 Int ？）对应着 Java 中的装箱基本数据类型（如java.lang.Integer ）。
- Any 类型是所有其他类型的超类型，类 Java Object 。而 Unit 类比于void
- 不会正常终止的函数使用 Nothing 类型作为返回类型。
- Kotlin 使用标准 Java 集合类，并通过区分只读和可变集合来增强它们。
- 当你在 Kotlin 中继承 Java 类或者实现 Java 接口时，你需要仔细考虑参数的可空性和可变性。
- Kotlin的Array 类就像普通的泛型类 但它会被编译成 Java 数组。
- 基本数据类型的数组使用像 IntArray 这样的特殊类来表示。


## 7.运算符重载及其他约定

本章内容包括：
> - 运算符重载
> - 约定：支持各种运算的特殊命名函数
> - 委托属性

### 总结
- Kotlin 允许使用对应名称的函数来重载一些标准的数学运算，但是不能定义自己的运算符。
- 比较运算符映射为 equals和 compareTo 方法的调用。
- 通过定义名为 get set contains 的函数，就可以让你自己的类与Kotlin 的集合一样，使用［］和 in 运算符。
- 可以通过约定来创建区间，以及迭代集合和数组。
- 解构声明可以展开单个对象用来初始化多个变量，这可以方便地用来从函数返回多个值。它们可以自动处理数据类，可以通过给自己的类定义名为 componentN 的函数来支持。
- 委托属性可以用来重用逻辑，这些逻辑控制如何存储、初始化、访问和修改属性值，这是用来构建框架的一个强大的工具。
- lazy 标准库函数提供了一种实现惰性初始化属性的简单方法。
- Delegates.observable 函数可以用来添加属性更改的观察者。委托属性可以使用任意 map 来作为属性委托，来灵活来处理具有可变属性集的对象。


## 8.高阶函数：Lambda作为形参和返回值

本章内容包括：
> - 函数类型
> - 离阶函数及其在组织代码过程中的应用
> - 内联函数
> - 非局部返回和标签
> - 重名函数

### 总结
- 函数类型可以让你声明一个持有函数引用的变量、参数或者函数返回值。
- 高阶函数以其他函数作为参数或者返回值。可以用函数类型作为函数参数或者返回值的类型来创建这样的函数。
- 内联函数被编译以后，它的字节码连同传递给它的 lambda 的字节码会被插入到调用函数的代码中，这使得函数调用相比于直接编写相同的代码，不会产生额外的运行时开销。
- 高阶函数促进了一个组件内的不同部分的代码重用，也可以让你构建功能强大的通用库。
- 内联函数可以让你使用非局部返回一一在 lambda 中从包含函数返回的返回表达式。
- 匿名函数给 lambda 表达式提供了另一种可选的语法，用不同的规则来解析 return 表达式。可以在需要编写有多个退出点的代码块的时候使用它们。


## 9.泛型

本章内容包括：
> - 声明泛型函数和类
> - 类型擦除和实化类型参数
> - 声明点变型和使用点变型

### 总结
- Kotlin 的泛型和 Java 相当接近：它们使用同样的方式声明泛型函数和泛型类。
- 和 Java 样，泛型类型的类型实参只在编译期存在。
- 不能把带类型实参的类型和 is 运算符一起使用 ，因为类型实参在运行时将被擦除。
- 内联函数的类型参数可以标记成实化的，允许你在运行时对它们使用 is 检查，以及获得 java.lang.Class 实例。
- 变型是一种说明两种拥有相同基础类型和不同类型参数的泛型类型之间子类型化关系的方式，它说明了如果其中一个泛型类型的类型参数是另一个的类型参数的子类型 这个泛型类型就是另外一个泛型类型的子类型或者超类型。
- 可以声明一个类在某个类型参数上是协变的，如果该参数只是用在 out 位置。
- 逆变的情况正好相反：可以声明一个类在某个类型参数上是逆变的，如果该参数只是用在 in 位置。
- 在Kotlin 中的 只读接口 List 声明成了协变的，这 意味着 List<String＞ 是 List<Any> 的子类型。
- 在函数接口声明成了在第一个类型参数上逆变而在第二个类型参数上协变，使(Animal) -> Int 成为（ Cat ）一＞ Number 的子类型。
- 在Kotlin 中既可以为整个泛型类指定变型（声明点变型），也可以为泛型类型特定的使用指定变型（使用点变型）。
- 当确切的类型实参是未知的或者不重要的时候，可以使用星号投影语法。

## 10.注解与反射

本章内容包括：
> - 应用和定义注解
> - 在运行时使用反射对类进行自省
> - 一个真正的 Kotlin 项目实例

### 总结
- Kotlin 中应用注解的语法和 Java 几乎一模一样。
- 在Kotlin 中可以让你应用注解的目标的范围比 Java 更广，其中包括了文件和表达式。
- 一个注解的参数可以是一个基本数据类型、一个字符串、一个枚举、一个类引用、一个其他注解类的实例，或者前面这些元素组成的数组。
- 如果单个 Kotlin 声明产生了多个字节码元素，像＠get Rule 这样指定一个注解的使用点目标，允许你选择注解如何应用。
- 注解类的声明是这样的，它是一个拥有主构造方法且没有类主体的类，其构造方法中所有参数都被标记成 val 属性。
- 元注解可以用来指定（使用点）目标、保留期模式和其他注解的特性。
- 反射 API 让你在运行时动态地列举和访问一个对象的方法和属性。它拥有许多接口来表示不同种类的声明，例如类（ KClass ）、函数（ KFunctio川等。
- 要获取一个 KClass 的实例，如果类是静态己知的，可以使用 ClassName::class ：否则，使用 obj.javaClass kotlin 从对象实例上取得类。
- KFunction 接口和 KProperty 接口都继承了 KCallable ，它提供了
通用的 call 方法。
- KCallable. callBy 方法能用来调用带默认参数值的方法。
- KFunctionO、KFunctionl 等这种不同参数数量的函数可以使用 invoke方法调用。
- KPropertyO 和 KPropertyl 是接收者数量不同的属性，支持用 get 方法取回值。KMutablePropertyO 和 KMutableProperty1 继承了这些接口。支持通过 set 方法来改变属性的值。

[1]:https://book.douban.com/subject/27093660/
[2]:https://github.com/JetBrains/kotlin
[3]:https://www.kotlincn.net/docs/reference/android-overview.html