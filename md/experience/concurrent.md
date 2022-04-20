# 并发技巧

### 分段锁
1. 描述：Java提供了原子类，但是高并发下可能会长时间自旋充实导致性能下降
2. 改进：JDK1.8提供LongAddr，维护了base变量和cell数组，普通情况下对base变量进行更新，高并发情况下，对cell数组元素进行CAS更新，获取值时将所有值累加
3. 类似例子：ConcurrentHashMap

### 双缓冲区
1. 描述：程序向磁盘写入文件时，如果采用同步写入，会阻塞很长时间
2. 改进
   1. 使用内存作为缓冲，当内存写满之后，使用另一个线程异步写入磁盘，但是原线程必须阻塞等待
   2. 使用2个缓冲区作，当其中一个内存写满之后，使用另一个线程异步写入磁盘，但是多线程同时写入必须获取缓冲区的锁
   3. 将缓冲区拆分为若干个，获取到对应锁的线程写入缓冲区，其他线程等待。当写缓冲区写满之后，当前线程放弃锁进行刷盘，或者切换缓冲区，使用另一个线程异步写入磁盘