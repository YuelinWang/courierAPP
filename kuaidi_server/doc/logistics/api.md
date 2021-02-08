# logistics logistics模块 API## 1.1 查询logistics详情

> **描述**：根据id查询logistics详情。

> **url**：/admin/logistics_rest/get/{id}
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
| 4        | data          | object   | logistics详情对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | id |
| 2 | message              | String          | 物流信息 |
| 3 | time              | Timestamp          | 更新时间 |
| 4 | oid              | Integer          | 订单id |

## 1.2 保存logistics详情

> **描述**：保存logistics详情。

> **url**：/admin/logistics_rest/save
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | id |
| 2| message  | String  | 物流信息 |
| 3| time  | Timestamp  | 更新时间 |
| 4| oid  | Integer  | 订单id |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.3 更新logistics详情

> **描述**：根据id更新logistics的任意属性值，确保要更新的参数不能为null。

> **url**：/admin/logistics_rest/update/{id}
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | id |
| 2| message  | String  | 物流信息 |
| 3| time  | Timestamp  | 更新时间 |
| 4| oid  | Integer  | 订单id |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.4 删除一条logistics记录

> **描述**：根据id删除一条logistics记录。

> **url**：/admin/logistics_rest/delete/{id}
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


## 1.5 批量删除多条logistics记录

> **描述**：根据多个id删除多条logistics记录。

> **url**：/admin/logistics_rest/batch_delete
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


## 1.6 分页查询logistics

> **描述**：分页查询logistics。

> **url**：/admin/logistics_rest/page
>
> **method**：GET

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    |page      |int       |第几页 [1,n)   |
| 2    |limit      |int       |每页多少条 [0,100)   |
| 3    |safeOrderBy      |int       | 排序 例如 数据库字段名称 desc或asc   |
                |4|idFirst|   Integer   |id|
                |5|oidFirst|   Integer   |订单id|

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |
| 4        | data          | array   | logistics详情数组对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | id |
| 2 | message              | String          | 物流信息 |
| 3 | time              | Timestamp          | 更新时间 |
| 4 | oid              | Integer          | 订单id |
