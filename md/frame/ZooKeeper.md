## ZooKeeper
1. 功能：维护、协调、管理、监控
2. 特点：
   1. 最终一致性：客户端看到的数据是最终一致的
   2. 可靠性：服务器保存了消息，那么它就一直存在
   3. 实时性：zk不能保证同时得到刚更新的数据
   4. 独立性：不同客户端直接互不影响
   5. 原子性：更新要么成功，要么失败
3. 应用场景
   1. 数据发布与订阅（配置中心）
      1. 配置信息特点
         1. 数量小的kv
         2. 数据内容在运行时会发生动态变化
         3. 集群机器共享、配置一致
      2. 模式
         1. 推：服务端给注册了监控节点的客户端Watcher事件通知
         2. 拉：客户端获得通知后，主动到服务端拉取数据
   2. 命名服务
      1. 在分布式环境下，对服务、应用统一命名，便于识别不同服务。可通过名称来获取资源地址、提供者信息
      2. 按照层次结构组织服务、应用名称。可将服务信息和地址写到ZK，客户端拉取可用服务列表
   3. 配置管理
      1. 程序分布式部署在不同的机器，可将程序的配置信息放在ZK的znode下。当znode发生变化时，可以通过zk中某个目录节点的内容，利用watch通知给各个客户端从而更改配置
   4. 集群管理：是否有机器退出或加入、选举master
      1. 集群监控
      2. 集群控制
   5. 分布式通知与协调：一个服务需要知道它所管理的子服务的状态
   6. 分布式锁：处于不同节点上不同的服务，他们可能顺序访问一些资源
      1. 写锁
      2. 读锁
      3. 时序锁
   7. 分布式队列
      1. 同步队列：当一个队列的成员都聚齐时，队列才可用
      2. FIFO队列：类似生产者消费者模型
4. 工作原理
   1. 角色
      1. server
         1. leader（唯一）
         2. learner（多个）
            1. follower（只处理读请求，写请求转发给leader）
            2. observer（不参与选举和投标）
         3. 每个server保存一份数据服务，返回统一的数据视图
      2. client
      3. 
   2. 原子广播（Zab协议）
      1. 恢复模式（选主，无leader时服务不可用）
         1. 发现：当服务器或leader挂掉，进入恢复模式
         2. 同步：选举出leader（paxos协议）
         3. 广播：数据同步，进入同步模式
      2. 广播模式（同步）
   3. 事务：每一次数据更新
      1. 更新过程
         1. client发送一个写请求
         2. follower转发给leader（如无，进入选举，且选举期间服务不可用）
         3. leader接收到写请求，生成事务id（Zxid）编号，例如8，并将书屋id和写请求写入队列
         4. follower从队列收到log，检查本地已生效或未生效的Zxid与接收到的Zxid：8比较，如果本地小于等于8，拒绝；否则接收。（？？？是不是反掉了）
         5. follower回复leader收到提议，并记录到本地日志
         6. leader收到过半follower的投票数后，给follower发送通知提议生效
         7. 返回给client写成功状态
      2. 事务编号
         1. 62位
         2. 32位leader唯一标识+32递增数字（网上形容的年号+年份，比如中平元年）
   4. 通知过程 
      1. 客户端注册watcher 
      2. 服务端处理watch 
      3. 客户端回调watcher（一次性）
   5. 分布式锁实现
      1. client去对应节点下创建临时有序节点
      2. 如果当前创建节点不是第一个节点，则对上一个节点加监听器（是否只能实现公平锁）
5. 设计理念
   1. 高性能：树形结构，内存存储，读写分离
   2. 高可用：半数以上机器存活，服务就能正常运行，且leader自行选举
   3. 顺序一致性：每个事务请求，都由leader处理，且事务id递增
   4. 最终一致性：通过提议投票方式保证数据提交的可靠性，且提交后，大部分节点能够看到最新数据
6. 持久化
   1. 快照：记录内存中全量数据（文件较大，定期备份，通常不是最新的）
   2. TxnLog 事务增量日志：记录每一条删改记录
7. 脑裂问题
   1. 原因：由于心跳超时，follower认为leader死了，发起新的leader选举，这时候旧的leader恢复，导致2个leader存在
   2. 解决方案
      1. 法定人数问题：节点数达到n，集群无效
      2. 冗余通信：集群内使用多种通信方式
      3. 共享资源：能看到共享资源标识在集群中，看不到的则不在集群中，能够获取共享资源的锁的就是leader