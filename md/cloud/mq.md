# MQ
### 概念
1. 概念：是一种跨进程的通讯机制，用于传递消息
#### 高并发如何保证消息不丢失
1. 生产者开启投递消息的confirm机制
2. 等待confirm期间，消息缓存到高性能kv数据库（或者本地消息表）
3. 使用MQ集群机制

### RocketMQ

#### 部署
1. docker部署单机版，详见./native/docket.md

#### 官方文档学习
1. 详见../frame/rocketmq.md