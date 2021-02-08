# orders orders模块 API## 1.1 查询orders详情

> **描述**：根据id查询orders详情。

> **url**：/admin/orders_rest/get/{id}
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
| 4        | data          | object   | orders详情对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | id |
| 2 | number              | String          | 快递单号 |
| 3 | receiver              | String          | 收货人 |
| 4 | sender              | String          | 发货人 |
| 5 | saddress              | String          | 发货地址 |
| 6 | raddress              | String          | 收获地址 |
| 7 | sphone              | String          | 发货人手机号 |
| 8 | rphone              | String          | 收货人手机号 |
| 9 | uid              | Integer          | 寄件人id |
| 10 | state              | Integer          | 0为快递中1为已派件2以签收 |

## 1.2 保存orders详情

> **描述**：保存orders详情。

> **url**：/admin/orders_rest/save
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | id |
| 2| number  | String  | 快递单号 |
| 3| receiver  | String  | 收货人 |
| 4| sender  | String  | 发货人 |
| 5| saddress  | String  | 发货地址 |
| 6| raddress  | String  | 收获地址 |
| 7| sphone  | String  | 发货人手机号 |
| 8| rphone  | String  | 收货人手机号 |
| 9| uid  | Integer  | 寄件人id |
| 10| state  | Integer  | 0为快递中1为已派件2以签收 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.3 更新orders详情

> **描述**：根据id更新orders的任意属性值，确保要更新的参数不能为null。

> **url**：/admin/orders_rest/update/{id}
>
> **method**：POST

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1| id  | Integer  | id |
| 2| number  | String  | 快递单号 |
| 3| receiver  | String  | 收货人 |
| 4| sender  | String  | 发货人 |
| 5| saddress  | String  | 发货地址 |
| 6| raddress  | String  | 收获地址 |
| 7| sphone  | String  | 发货人手机号 |
| 8| rphone  | String  | 收货人手机号 |
| 9| uid  | Integer  | 寄件人id |
| 10| state  | Integer  | 0为快递中1为已派件2以签收 |

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |

## 1.4 删除一条orders记录

> **描述**：根据id删除一条orders记录。

> **url**：/admin/orders_rest/delete/{id}
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


## 1.5 批量删除多条orders记录

> **描述**：根据多个id删除多条orders记录。

> **url**：/admin/orders_rest/batch_delete
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


## 1.6 分页查询orders

> **描述**：分页查询orders。

> **url**：/admin/orders_rest/page
>
> **method**：GET

> **输入**

| 序号 | 字段名称 | 字段类型 | 字段描述     |
| ---- | -------- | -------- | ------------ |
| 1    |page      |int       |第几页 [1,n)   |
| 2    |limit      |int       |每页多少条 [0,100)   |
| 3    |safeOrderBy      |int       | 排序 例如 数据库字段名称 desc或asc   |
                |4|idFirst|   Integer   |id|
                |5|numberFirst|   String   |快递单号|
                |6|receiverFirst|   String   |收货人|
                |7|senderFirst|   String   |发货人|
                |8|saddressFirst|   String   |发货地址|
                |9|raddressFirst|   String   |收获地址|
                |10|sphoneFirst|   String   |发货人手机号|
                |11|rphoneFirst|   String   |收货人手机号|
                |12|uidFirst|   Integer   |寄件人id|
                |13|stateFirst|   Integer   |0为快递中1为已派件2以签收|

> **输出**

| 序号     | 字段名称      | 字段类型 | 字段描述                |
| -------- | ------------- | -------- | ----------------------- |
| 1        | flag          | boolean  | 接口调用，成功/失败标识 |
| 2        | code          | int      | 错误码，成功时=0        |
| 3        | desc          | string   | 结果描述                |
| 4        | data          | array   | orders详情数组对象            |
|          |               |          |                         |
| 字段解释 |               |          |                         |
| 1 | id              | Integer          | id |
| 2 | number              | String          | 快递单号 |
| 3 | receiver              | String          | 收货人 |
| 4 | sender              | String          | 发货人 |
| 5 | saddress              | String          | 发货地址 |
| 6 | raddress              | String          | 收获地址 |
| 7 | sphone              | String          | 发货人手机号 |
| 8 | rphone              | String          | 收货人手机号 |
| 9 | uid              | Integer          | 寄件人id |
| 10 | state              | Integer          | 0为快递中1为已派件2以签收 |
