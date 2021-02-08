# user user模块 API## 1.1 查询user详情

> **描述**：根据id查询user详情。

> **url**：/admin/user_rest/get/{id}
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
| 4        | data          | object   | user详情对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | id |
| 2 | username              | String          | 用户名，手机号 |
| 3 | password              | String          | 密码 |
| 4 | realname              | String          | 昵称 |
| 5 | state              | Integer          | 状态0正常 |
| 6 | phone              | String          | 电话号 |

## 1.2 保存user详情

> **描述**：保存user详情。

> **url**：/admin/user_rest/save
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | id |
| 2| username  | String  | 用户名，手机号 |
| 3| password  | String  | 密码 |
| 4| realname  | String  | 昵称 |
| 5| state  | Integer  | 状态0正常 |
| 6| phone  | String  | 电话号 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.3 更新user详情

> **描述**：根据id更新user的任意属性值，确保要更新的参数不能为null。

> **url**：/admin/user_rest/update/{id}
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | id |
| 2| username  | String  | 用户名，手机号 |
| 3| password  | String  | 密码 |
| 4| realname  | String  | 昵称 |
| 5| state  | Integer  | 状态0正常 |
| 6| phone  | String  | 电话号 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.4 删除一条user记录

> **描述**：根据id删除一条user记录。

> **url**：/admin/user_rest/delete/{id}
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


## 1.5 批量删除多条user记录

> **描述**：根据多个id删除多条user记录。

> **url**：/admin/user_rest/batch_delete
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


## 1.6 分页查询user

> **描述**：分页查询user。

> **url**：/admin/user_rest/page
>
> **method**：GET

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    |page      |int       |第几页 [1,n)   |
| 2    |limit      |int       |每页多少条 [0,100)   |
| 3    |safeOrderBy      |int       | 排序 例如 数据库字段名称 desc或asc   |
                |4|idFirst|   Integer   |id|
                |5|realnameFirst|   String   |昵称|

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |
| 4        | data          | array   | user详情数组对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | id |
| 2 | username              | String          | 用户名，手机号 |
| 3 | password              | String          | 密码 |
| 4 | realname              | String          | 昵称 |
| 5 | state              | Integer          | 状态0正常 |
| 6 | phone              | String          | 电话号 |
