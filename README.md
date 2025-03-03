# 寻友网站                                                                  
作者：陈毓轩

### 使用技术
##### 前端
- Vue 3 https://cli.vuejs.org/zh/
- Vite脚手架 https://vitejs.cn/guide/
- Vant组件库 https://develop365.gitlab.io/vant/zh-CN/home/
##### 后端
- Java + SpringBoot
- SpringMVC + MyBatis + MyBatis Plus
- MySQL
- Redis
- Swagger + Knife4j接口文档
****

### SQL表设计
##### 用户表
```create table user
(
    id           bigint auto_increment comment 'id'
        primary key,
    username     varchar(256)                       null comment '用户昵称',
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态  0代表正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除 ',
    userRole     int      default 0                 not null comment '用户角色 0-普通用户  1-管理员',
    tags         varchar(1024)                      null comment '标签列表',
    planetCode   varchar(512)                       null comment '编号',
    profile      varchar(512)                       null comment '个人简介'
)
    comment '用户表';
```
##### 队伍表
```
create table team
(
    id          bigint auto_increment comment 'id'
        primary key,
    name        varchar(256)                       not null comment '队伍名称',
    description varchar(1024)                      null comment '描述',
    maxNum      int      default 1                 not null comment '最大人数',
    expireTime  datetime                           null comment '过期时间',
    userId      bigint                             null comment '用户id',
    status      int      default 0                 not null comment '0 - 公开，1 - 私有，2 - 加密',
    password    varchar(512)                       null comment '密码',
    createTime  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete    tinyint  default 0                 not null comment '是否删除'
)
    comment '队伍';
```
##### 用户-队伍表
```
create table user_team
(
    id         bigint auto_increment comment 'id'
        primary key,
    userId     bigint                             null comment '用户id',
    teamId     bigint                             null comment '队伍id',
    joinTime   datetime                           null comment '加入时间',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '用户队伍关系';
```
----

### 项目功能
本项目包含的功能有
1. 用户注册
2. 用户登录
3. 用户修改信息
4. 用户退出登录
5. 用户设置标签
6. 用户搜索心仪朋友
7. 系统默认提供最匹配用户信息的朋友
8. 用户创建队伍
9. 用户加入队伍
10. 用户退出队伍
11. 用户查看队伍信息以及自身队伍情况
-----
### 在项目编写过程中遇到的的问题以及解决方法

- 在编写用户登录的过程中为解决集群间登录态同步问题，使用Redis来储存Session实现分布式Session。
- 为提高用户使用体验使用，本项目在首页将直接显示最匹配用户信息的朋友，为实现这一目标使用Redis缓存高频访问的用户信息列表。在测试过程中当数据库用户很少时本功能无任何异常，但当数据库中用户为百万量级时用户首页加载过慢，为了解决该问题，使用Spring Schedule定时任务来实现缓存预热，及在特点时间系统将自动从数据库当中将目标数据缓存在Redis中，使用Redisson分布式锁来实现操作互斥，避免在多机部署时该定时任务重复执行。
- 在编写队伍相关功能时，经测试发现会出现同一用户重复加入队伍、入队人数超过限制的情况，为解决问题使用Redisson分布式锁来实现操作互斥，保证接口幂等性。




