# 用户注册

## 描述

- 用户注册接口

## 请求url

- `/register`

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

|参数名|数据类型|描述|
|---|:---:|---|
|**message**|string|后台返回信息|
|**success**|boolean|是否注册成功|

> 返回示例
>
> ```json
> {
>    "message":"注册成功！",
>    "success":true
> }
>```
