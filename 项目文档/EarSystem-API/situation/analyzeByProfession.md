# 按专业进行分析

## 描述

- 获取按照专业分析招聘需求的统计信息

## 请求url

- `/situation/analyzeByProfession`

## 请求方法

- GET

## 请求参数

无

## 返回参数

|参数名|数据类型|描述|
|---|:---:|---|
|-|Array\<object>|统计信息数据|
|.**profession**|string|专业|
|.**value**|number|需求量|

> 返回示例
>
> ```json
> [
>     {"profession":"心理学类","VALUE":139},
>     {"profession":"历史学类","VALUE":104},
>     {"profession":"外国语言文学类","VALUE":104},
>     ……
> ]
>```
