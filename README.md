# 埋点管理系统设计

## Week 1 搭建前后端框架

20240518: 搭建后端脚手架，测试后端接口

- 已跑通controller-service-dto流程
- 已完成DagEntity的CRUD操作

20240519: 搭建前端脚手架，测试前后端数据传递

- 已能够通过axios访问后端controller

20240520: 测试用户注册登录功能

- 已完成用户注册功能
- 已完成用户登录跳转页面

## Week 2

20240528: 设计埋点数据管理层

- 创建多个数据源，用于不同服务对象
- 设计事件对象状态机

![image-20240529184758316](F:\Study_Notes_Backup\Full_Stack_Projects\Event-Tracking-Management-System\README.assets\image-20240529184758316.png)



20240529~20240530：设计埋点数据层

- 设计JPAEntityDTO之间的交互关系

- 重构数据库，添加多对多映射

  ![image-20240529230720531](F:\Study_Notes_Backup\Full_Stack_Projects\Event-Tracking-Management-System\README.assets\image-20240529230720531.png)

- 编写后端接口, 尝试模仿https://help.aliyun.com/document_detail/252718.html?spm=a2c4g.252716.0.0.68201b146emhcr中的对象操作逻辑

20240531: 编写前端页面测试后端功能

- 测试后端接口
- 编写前端页面

## Week 3~4 Idle

## Week 5

20240616: 编写前端页面，调试后端接口

- 对代码进行重构
- 编写添加事件页面

20240617: 编写前端页面，调试后端接口

- 对代码进行重构
- 编写添加事件页面
- 编写添加参数页面

20240618: 编写前端页面，调试后端接口