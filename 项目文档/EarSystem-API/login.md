# 用户登录

## 描述

- 用户登录接口（密码）

## 请求url

- `/login`

## 请求方法

- POST

## 请求参数

|参数名|数据类型|描述|
|---|:---:|---|
|**username**|string|用户名|
|**password**|string|密码|
|**code**|string|图形验证码|

>请求示例
>
>```json
>{
>    "username":"user",
>    "password":"123456",
>    "code":"as45"
>}
>```

## 返回参数

&nbsp;

### 登录成功

|参数名|数据类型|描述|
|---|:---:|---|
|**userdetails**|object|用户状态信息|
|userdetails.**username**|string|用户名|
|userdetails.**Enabled**|boolean|账号是否激活|
|userdetails.**AccountNonExpired**|boolean|账号是否未过期|
|userdetails.**credentialsNonExpired**|boolean|密码是否未过期|
|userdetails.**AccountNonLocked**|boolean|账号是否未锁定|
|userdetails.**Authorities**|Array\<string>|用户权限列表|
|**success**|boolean|登录是否成功|

> 返回示例
>
> ```json
> {
>    "userdetails":{
>        "username":"xiaoliang",
>        "Enabled":true,
>        "AccountNonExpired":true,
>        "credentialsNonExpired":true,
>        "AccountNonLocked":true,
>        "Authorities":["admin"]
>    },
>    "success":true
> }
>```

&nbsp;

### 登录失败

|参数名|数据类型|描述|
|---|:---:|---|
|**message**|string|登录失败信息|
|**success**|boolean|是否登录成功|

>返回示例
>
>```json
>{
>    "message":"用户名或密码错误",
>    "success":false
>}
>```
