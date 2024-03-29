### 概念
1. JVM：java程序运行环境，JVM针对不同的系统有不同的实现，屏蔽了操作系统的差异和细节，实现了跨平台特性。
2. 字节码：编译器将java代码编译成jvm可以理解的格式，jvm将字节码转换成计算器可识别的二进制机器码。
3. 如果两个对象相等，则 hashcode 一定也是相同的。两个对象相等,对两个对象分别调用 equals 方法都返回 true。但是，两个对象有相同的 hashcode 值，它们也不一定是相等的 。因此，equals 方法被覆盖过，则 hashCode 方法也必须被覆盖。
4. 静态方法是属于类的，在类加载的时候就会分配内存，可以通过类名直接访问。而非静态成员属于实例对象，只有在对象实例化之后才存在，然后通过类的实例对象去访问。在类的非静态成员不存在的时候静态成员就已经存在了，此时调用在内存中还不存在的非静态成员，属于非法操作。
5. java只有值传递，按值调用(call by value) 表示方法接收的是调用者提供的值，按引用调用（call by reference) 表示方法接收的是调用者提供的变量地址。引用类型因为是浅拷贝，所以修改后会影响原有的变量。


### web
1. Servlet
    1. 是JavaEE规范的一种，主要为了扩展Java作为Web服务的功能
    2. 从 Jar 包上来说，Servlet 规范就是两个 Jar 文件。servlet-api.jar 和 jsp-api.jar，Jsp 也是一种Servlet
    3. 从package上来说，就是 javax.servlet 和 javax.servlet.http 两个包。 
    4. 从接口来说，就是规范了 Servlet 接口、Filter 接口、Listener 接口、ServletRequest 接口、 ServletResponse 接口等