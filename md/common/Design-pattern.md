## 设计模式
### 面向对象六大原则
1. 单一职责。There should never be more than one reason for a class to change.
2. 里氏替换原则。
    1. 说明
        1. if for each object o1 of types S there is an object o2 of Type T such that
for all programs P defined in terms of T,the behavior of P is unchanged where o1 is substituted for 
o2 then S is a subType of T.(如果对每一个类型为S的对象o1，都有类型为T的对象o2，使得以T定义的所有程序在P所在对象的o1
都替换为o2时，程序P的行为没有发生变化，那么类型S是类型T的子类型)
        2. Functions that use pointers of references to base classes must be able to user objects of derived class without known it.
        (所有引用积累的地方必须能透明的使用子类的对象)
    2. 引申含义
        1. 子类必须完全重写父类的方法
        2. 子类可以扩展自己的行为（通常会保持一致，否则部分场景难以通用）
        3. 子类的参数范围必须大于等于父类
        4. 子类的返回值范围必须小于等于父类
3. 依赖倒置原则
    1. 说明 High level modules should not depend upon low level modules.Both should be depend upon abstractions.Abstractions should
    not depend in details.Details show be depend abstractions.(高层模块不应该依赖于底层模块，两者都应该依赖于抽象。抽象不应该依赖于细节。细节应该依赖于抽象。)
    2. 三种写法
        1. 构造器注入依赖
        2. setter方法注意依赖
        3. 接口参数注入依赖
    3. 代码规范
        1. 每个类尽量都有接口或者抽象类，或者两者都具备(必须有抽象才能依赖倒置)
        2. 变量的表面类型尽量抽象，如果要初始化一个OrderService，写法：IOrderService orderService=...;(Utils不需要)
        3. 任何类都不应该从具体类派生。如class xxx extends IOrderServiceImpl{}。基本上不能超过2次继承，如果是维护老代码除外。
4. 接口隔离原则
5. 最少知道原则（迪米特原则）
   1. 就任何对象而言，在该对象的方法内，只应该调用属于以下范围内方法
      1. 该对象本身
      2. 被当作方法参数而传递进来的对象
      3. 此方法所创建或实例化的对象
      4. 对象的任何组件（被实例变量引用的任何对象）
6. 开闭原则（对扩展开放，对修改关闭）

### 面向对象原则
1. 把会变化的部分取出并封装起来，好让其他部分不会受影响
2. 面向接口编程，而不是针对实现编程
3. 多用组合，少用继承
4. 为了交互对象之间的松耦合设计而努力

### 23种模式分类
1. 创建型模式
2. 结构型模式
3. 行为型模式

### 策略模式：定义了算法族，分别封装起来，让它们之间可以互相替换，此模式让算法的变化独立于实用算法的客户

### 观察者模式（/test/src/main/java/liufeng/design/pattern/observer）
1. 组成
   1. Observer 类（观察者）
      1. update()
   2. Subject 类（主题）
      1. registerObserver()
      2. removeObserver()
      3. notifyObservers()
   3. Subject和Observer的实现类
   4. 类图![观察者模式类图](../img/dp/observer-uml.png)
2. 实例代码：liufeng.design.pattern.observer.WeatherData
3. Java 内置API
   1. java.util.Observer类
   2. java.util.Observable类
      1. addObserver()
      2. deleteObserver()
      3. notifyObservers()
      4. setChanged();// 如果没有setChanged，notifyObservers()将不会真正通知到观察者
   3. 注意点
      1. Observable是class，而不是interface，而Java不支持多继承
      2. Observable的setChanged()是projected()方法
4. JDK 哪些地方使用观察者模式
   1. Swing
### 装饰者模式（/test/src/main/java/liufeng/design/pattern/decorate）
### 工厂模式（/test/src/main/java/liufeng/design/pattern/factory）
### 单例模式
### 命令模式（/test/src/main/java/liufeng/design/pattern/command）
### 适配器模式（/test/src/main/java/liufeng/design/pattern/adapter）与外观模式
### 模版方法模式（/test/src/main/java/liufeng/design/pattern/template）
### 迭代器（/test/src/main/java/liufeng/design/pattern/iterator）与组合模式
### 状态模式
### 代理模式
### 复合模式


### 面试题
1. 代理模式和装饰器模式有什么区别？
   1. 都是结构型模式，代理模式重在访问权限的控制，而装饰器模式重在功能的加强
2. 享元模式和单例模式的区别？
   1. 单例模式是创建型模式，重在只能有一个对象
   2. 享元模式是结构型模式，重在节约内存使用，提升程序性能。可能有多个对象

