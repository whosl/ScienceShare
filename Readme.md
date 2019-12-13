# 科技专家资源共享系统

ScienceShare（暂命名）

UserCenter部分

若有需要实现的用户相关的API，请戳我。

# UserCenter API

## 错误码

| 错误码 | 描述       |
| ------ | ---------- |
| 200    | 成功       |
| 201    | 已存在     |
| 300    | 不存在     |
| 305    | 邮箱不合法 |
| 306    | 邮箱已注册 |
| 400    | 失败       |
| 402    | 密码无效   |
| 404    | 接口不存在 |



## 接口实例（部分）

### 获取用户身份

#### 简要描述

- 获取用户身份，返回身份代码，具体对应如下：

  | 身份代码 | 身份                               |
  | -------- | ---------------------------------- |
  | 0        | 未认证专家                         |
  | 1        | 普通用户（默认用户注册为普通用户） |
  | 2        | 已认证科技专家                     |
  | 3        | 管理员                             |

#### Eureka请求URL

- `http://UserCenter/getIdentify`

#### 请求方式

- GET

#### 参数

| 参数名   | 类型   | 说明   |
| -------- | ------ | ------ |
| username | string | 用户名 |

#### 实例

请求URL：

> http://UserCenter/register?username=温卓林

返回实例：

> {
>
> ​    "code": 201,
>
> ​    "message": "用户已存在！",
>
> ​    "data": null
>
> }



### 将未认证专家转为已认证专家

#### 简要描述

- 将未认证专家转为已认证专家

#### Eureka请求URL

- `http://UserCenter/authenticateExpert`

#### 请求方式

- GET

#### 参数

| 参数名   | 类型   | 说明   |
| -------- | ------ | ------ |
| username | string | 用户名 |

#### 实例

请求URL：

> http://UserCenter/authenticateExpert?username=温卓林

返回实例：

> {
>
> ​    "code": 200,
>
> ​    "message": "专家认证通过！",
>
> ​    "data": null
>
> }



### 获取所有认证专家

#### 简要描述

- 获取所有认证专家，返回值包含所有专家的所有信息

#### Eureka请求URL

- `http://UserCenter/allAuthExpert`

#### 请求方式

- GET

#### 参数

| 参数名   | 类型   | 说明   |
| -------- | ------ | ------ |
| username | string | 用户名 |

#### 实例

请求URL：

> http://UserCenter/allAuthExpert

返回实例：

> [
>
> ​    {
>
> ​        "id": "5df3b50d3d365d0ccd13f5a5",
>
> ​        "username": "温卓林",
>
> ​        "password": "8025850F63A29BB186E6F80C5A5320D302F80B",
>
> ​        "identity": 2,
>
> ​        "notifications": [
>
> ​            {
>
> ​                "username": "我们",
>
> ​                "type": 1,
>
> ​                "message": "已通过您的专家认证申请！",
>
> ​                "read": 0,
>
> ​                "notifiDate": "2019-12-13T23:58:51.625",
>
> ​                "notifiNo": 0,
>
> ​                "fatherId": "auth"
>
> ​            }
>
> ​        ],
>
> ​        "unreadNotification": 1,
>
> ​        "emailAddress": "982267311@qq.com",
>
> ​        "prestige": 0,
>
> ​        "createdDate": "2019-12-13T23:58:05.291",
>
> ​        "avatarUrl": "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
>
> ​    }
>
> ]



### 获取所有未认证专家

#### 简要描述

- 获取所有认证专家，返回值包含所有专家的所有信息

#### Eureka请求URL

- `http://UserCenter/allUnAuthExpert`

#### 请求方式

- GET

#### 参数

| 参数名   | 类型   | 说明   |
| -------- | ------ | ------ |
| username | string | 用户名 |

#### 实例

请求URL：

> http://UserCenter/allUnAuthExpert

返回实例（返回内容与获取所有认证专家相同）：

> []



### 修改用户密码

#### 简要描述

- 修改已注册用户的密码

#### Eureka请求URL

- `http://UserCenter/changePassword`

#### 请求方式

- GET

#### 参数

| 参数名      | 类型   | 说明     |
| ----------- | ------ | -------- |
| username    | string | 用户名   |
| oldPassword | string | 旧的密码 |
| newPassword | string | 新的密码 |

#### 实例

请求URL：

> http://UserCenter/changePassword?username=温卓林&oldPassword=111111&newPassword=123456

返回实例：

> {
>
> ​    "code": 402,
>
> ​    "message": "密码错误！",
>
> ​    "data": null
>
> }



### 用户登录

#### 简要描述

* 用户登录

#### Eureka请求URL

* `http://UserCenter/login`

#### 请求方式

* GET

#### 参数

| 参数名   | 类型   | 说明   |
| -------- | ------ | ------ |
| username | string | 用户名 |
| password | string | 密码   |

#### 实例

请求URL：

> http://UserCenter/login?username=温卓林&password=123456

返回实例：

> {
>
> ​    "code": 200,
>
> ​    "message": "登录成功",
>
> ​    "data": null
>
> }



### 用户注册

#### 简要描述

- 用户注册，该接口直接绕过邮箱验证注册用户，但会记录下邮箱，致使该邮箱无法再次注册

#### Eureka请求URL

- `http://UserCenter/register`

#### 请求方式

- GET

#### 参数

| 参数名       | 类型   | 说明     |
| ------------ | ------ | -------- |
| username     | string | 用户名   |
| password     | string | 密码     |
| emailAddress | string | 注册邮箱 |

#### 实例

请求URL：

> http://UserCenter/register?username=温卓林&password=123456&emailAddress=982267311@qq.com

返回实例：

> {
>
> ​    "code": 201,
>
> ​    "message": "用户已存在！",
>
> ​    "data": null
>
> }







