<h1 align="center">
  well-parent
</h1>

<h4 align="center">
	构建学成教育学院后端
	<br>🤜基于 SpringBoot 框架🤛
</h4>

## 🎮目录

- [亮点](#亮点)
- [开发工具](#开发工具)
- [目录结构](#目录结构)
- [配置](#配置)

## ✨ 亮点

- 基于SpringBoot构建 RESTfull API
- 更灵活的 API文档生成方式
- AOP(面向切面编程)设计

## 🔩 内置

1. 会员管理。
2. 权限组(角色)管理：接口权限分配和菜单权限分配。
3. 讲师管理。
4. 课程管理。
5. 课程分类管理：EasyEcel导入
6. CMS管理。
7. 订单管理。
8. 阿里云OSS、阿里云视频点播功能
9. 接口文档：根据业务代码自动生成相关的api接口文档(Swagger风格)。

## 开发工具

- SpringBoot 2.2.1.RELEASE + SpringCloud Hoxton.RELEASE
- MySQL
- IntelliJ IDEA 2020.1（开发工具）
- Navicat（数据库可视化管理工具）

## 目录结构

```java
├─ well-parent                                      //父工程
│  ├─
│  ├─ well-common                                   //公共模块父节点
│  ├─  ├─ well-common-utils                         //工具类模块，所有模块都可以依赖于它
│  ├─  ├─ well-service-base                         //well-service服务的base包
│  ├─  └─ well-spring-security                      //认证与授权模块
│  ├─
│  ├─ well-infrastructure                           //基础服务模块父节点
│  ├─  └─ well-api-gateway                          //api网关服务
│  ├─
│  ├─ well-service                                  //api接口服务父节点
│  ├─  ├─ well-service-acl                          //用户权限管理api接口服务
│  ├─  ├─ well-service-cms                          //cms api接口服务
│  ├─  ├─ well-service-edu                          //教学相关api接口服务
│  ├─  ├─ well-service-msm                          //短信api接口服务
│  ├─  ├─ well-service-order                        //订单相关api接口服务
│  ├─  ├─ well-service-oss                          //订单相关api接口服务
│  ├─  ├─ well-service-statistics                   //统计报表api接口服务
│  ├─  ├─ well-service-ucenter                      //会员api接口服务
│  ├─  └─ well-service-vod                          //视频点播api接口服务
```
## 配置文件

1. well-service-msm 模块配置文件中，配置阿里云**accessKeyId**与**secret**，**MsmServiceImpl.java**文件 **send**方法种配置参数改为自己得
2. well-service-order模块配置文件中，配置微信支付密钥
3. well-service-oss模块配置文件中，配置阿里云oss参数
4. well-service-vod模块配置文件中，配置阿里云oss参数