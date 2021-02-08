# friend friend模块 API## 1.1 查询friend详情

> **描述**：根据id查询friend详情。

> **url**：/admin/friend_rest/get/{id}
>
> **method**：GET

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    | id       | Integer     | id，必填 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |
| 4        | data          | object   | friend详情对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | id |
| 2 | uid              | Integer          | 用户id |
| 3 | fid              | Integer          | 好友id |
| 4 | time              | Timestamp          | 添加好友时间 |

## 1.2 保存friend详情

> **描述**：保存friend详情。

> **url**：/admin/friend_rest/save
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | id |
| 2| uid  | Integer  | 用户id |
| 3| fid  | Integer  | 好友id |
| 4| time  | Timestamp  | 添加好友时间 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.3 更新friend详情

> **描述**：根据id更新friend的任意属性值，确保要更新的参数不能为null。

> **url**：/admin/friend_rest/update/{id}
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | id |
| 2| uid  | Integer  | 用户id |
| 3| fid  | Integer  | 好友id |
| 4| time  | Timestamp  | 添加好友时间 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.4 删除一条friend记录

> **描述**：根据id删除一条friend记录。

> **url**：/admin/friend_rest/delete/{id}
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    | id       | Integer     | id，必填 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |


## 1.5 批量删除多条friend记录

> **描述**：根据多个id删除多条friend记录。

> **url**：/admin/friend_rest/batch_delete
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    | ids[]       | Integer     | id，必填 |
| 2    | ids[]       | Integer     | id，必填 |
| ...    | ids[]       | Integer     | id，必填 |
| n    | ids[]       | Integer     | id，必填 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |


## 1.6 分页查询friend

> **描述**：分页查询friend。

> **url**：/admin/friend_rest/page
>
> **method**：GET

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    |page      |int       |第几页 [1,n)   |
| 2    |limit      |int       |每页多少条 [0,100)   |
| 3    |safeOrderBy      |int       | 排序 例如 数据库字段名称 desc或asc   |
                |4|idFirst|   Integer   |id|
                |5|uidFirst|   Integer   |用户id|
                |6|fidFirst|   Integer   |好友id|
                |7|timeFirst|   Timestamp   |添加好友时间|
                |8|timeSecond|   Timestamp   |添加好友时间|

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |
| 4        | data          | array   | friend详情数组对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | id |
| 2 | uid              | Integer          | 用户id |
| 3 | fid              | Integer          | 好友id |
| 4 | time              | Timestamp          | 添加好友时间 |
