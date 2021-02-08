# moments moments模块 API## 1.1 查询moments详情

> **描述**：根据朋友圈查询moments详情。

> **url**：/admin/moments_rest/get/{id}
>
> **method**：GET

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    | id       | Integer     | 朋友圈，必填 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |
| 4        | data          | object   | moments详情对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | 朋友圈 |
| 2 | content              | String          | 朋友圈内容 |
| 3 | uid              | Integer          | 用户id |
| 4 | time              | Timestamp          | 发布时间 |

## 1.2 保存moments详情

> **描述**：保存moments详情。

> **url**：/admin/moments_rest/save
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | 朋友圈 |
| 2| content  | String  | 朋友圈内容 |
| 3| uid  | Integer  | 用户id |
| 4| time  | Timestamp  | 发布时间 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.3 更新moments详情

> **描述**：根据朋友圈更新moments的任意属性值，确保要更新的参数不能为null。

> **url**：/admin/moments_rest/update/{id}
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | 朋友圈 |
| 2| content  | String  | 朋友圈内容 |
| 3| uid  | Integer  | 用户id |
| 4| time  | Timestamp  | 发布时间 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.4 删除一条moments记录

> **描述**：根据朋友圈删除一条moments记录。

> **url**：/admin/moments_rest/delete/{id}
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    | id       | Integer     | 朋友圈，必填 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |


## 1.5 批量删除多条moments记录

> **描述**：根据多个朋友圈删除多条moments记录。

> **url**：/admin/moments_rest/batch_delete
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    | ids[]       | Integer     | 朋友圈，必填 |
| 2    | ids[]       | Integer     | 朋友圈，必填 |
| ...    | ids[]       | Integer     | 朋友圈，必填 |
| n    | ids[]       | Integer     | 朋友圈，必填 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |


## 1.6 分页查询moments

> **描述**：分页查询moments。

> **url**：/admin/moments_rest/page
>
> **method**：GET

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    |page      |int       |第几页 [1,n)   |
| 2    |limit      |int       |每页多少条 [0,100)   |
| 3    |safeOrderBy      |int       | 排序 例如 数据库字段名称 desc或asc   |
                |4|idFirst|   Integer   |朋友圈|
                |5|contentFirst|   String   |朋友圈内容|
                |6|uidFirst|   Integer   |用户id|
                |7|timeFirst|   Timestamp   |发布时间|
                |8|timeSecond|   Timestamp   |发布时间|

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |
| 4        | data          | array   | moments详情数组对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | 朋友圈 |
| 2 | content              | String          | 朋友圈内容 |
| 3 | uid              | Integer          | 用户id |
| 4 | time              | Timestamp          | 发布时间 |
