# 寻友网站

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
##### 项目编写过程中遇到的问题和解决方法  以及项目编写过程中的想法和思考
在项目编写的过程中在用户数量在100以内时

