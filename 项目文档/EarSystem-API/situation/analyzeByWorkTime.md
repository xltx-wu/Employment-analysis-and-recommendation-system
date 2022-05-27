# 按工作经验分析

## 描述

- 获取按照工作经验分析的统计信息

## 请求url

- `/situation/analyzeByWorkTime`

## 请求方法

- GET

## 请求参数

无

## 返回参数

|参数名|数据类型|描述|
|---|:---:|---|
|-|Array\<object>|统计信息数据|
|.**worktime**|number|工作经验|
|.**value**|number|招聘信息数量|

> 返回示例
>
> ```json
> [
>     {"worktime":6,"value":103},
>     {"worktime":4,"value":105},
>     {"worktime":7,"value":98},
>     ……
> ]
>```
