# 按工作经验分析薪资

## 描述

- 获取按照工作经验分析薪资的统计信息

## 请求url

- `/situation/analyzeSalaryByWorktime`

## 请求方法

- GET

## 请求参数

|参数名|数据类型|描述|
|---|:---:|---|
|**mode**|string|指定获取模式：max、min和avg|

## 返回参数

|参数名|数据类型|描述|
|---|:---:|---|
|-|Array\<object>|统计信息数据|
|.**worktime**|number|工作经验|
|.**value**|number|薪资|

> 返回示例
>
> ```json
> [
>     {"worktime":6,"value":30.0},
>     {"worktime":4,"value":30.0},
>     {"worktime":7,"value":29.3},
>     ……
> ]
>```
