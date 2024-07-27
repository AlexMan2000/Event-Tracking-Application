# 埋点管理系统设计

# 数据库设计

![image-20240727155138352](./README.assets/database_schema.png)

- Project和Module之间存在多对多关系
- Module和Page之间存在多对多关系
- Page和Event之间存在多对多关系
- Project, Module, Page, Event和Parameter之间均存在多对多关系







# 后端设计

后端使用Java + Spring + Jpa实现

## Controller层

每个对象提供下列五个路由:

- `base/meta`: 用于获取一个对象的元信息，包括`对象ID, 对象编码`
- `base/all`: 用于获取所有对象，结果包含外键对象数组
- `base/allfiltered`: 用于获取所有对象(经过字段筛选)，结果包含外键对象数组
- `base/{id}`: 根据`对象ID`获取对象
- `base/create`: 创建一个新的对象
- `base/update`: 更新已有对象，通过`对象ID`找到数据库中已有对象





## Service层

**Service为每个对象提供五个方法, 和Controller层对应**

```
List<EventEntityGetObjectDTO> getAllEntities();

List<EventEntityGetObjectDTO> getAllEntitiesFiltered(EntitySearchCriteria searchCriteria);

List<GetIdentifiersDTO> getAllMetaData();

EntityGetObjectDTO getEntityById(Long entityId);

Message createEntity(EntityUpdateObjectDTO entityUpdateObjectDTO);

Message updateEntity(EntityUpdateObjectDTO entityUpdateObjectDTO);
```

**Service层为每个对象增改方法提供辅助方法:**

- 将一个用户提交对象转化为具有正确创建/修改时间的数据库持久化对象。







## Persistence层

`Persistence`为每个对象提供两类抽象:

- `EntityRepository`: 用于处理增删改查
- `Entity1Entity2MappingRepository`用于处理多对多关系











# 前端设计

前端使用React + Vite +Typescript实现

## 数据展示

![image-20240727155255701](./README.assets/image-20240727155255701.png)

- 点击左侧菜单栏切换需要管理的对象
- 上方字段用于过滤对象条目



## 新增数据



![image-20240727155443959](./README.assets/image-20240727155443959.png)![image-20240727155447708](./README.assets/image-20240727155447708.png)![image-20240727160028724](./README.assets/image-20240727160028724.png)

- 点击`Add New Entity`弹出对话框，用户输入新增对象信息注入数据库
- 点击`添加新参数`选择将要添加到当前对象的额外属性
- 点击`Create`提交修改



## 修改数据

![image-20240727160040617](./README.assets/image-20240727160040617.png)

- 点击右侧`Edit`进行数据修改
- 点击`Submit`提交修改