# 按行业进行分析

## 描述

- 获取按照行业分析招聘需求的统计信息

## 请求url

- `/situation/analyzeByIndustry`

## 请求方法

- GET

## 请求参数

无

## 返回参数

|参数名|数据类型|描述|
|---|:---:|---|
|-|Array\<object>|统计信息数据|
|.**industry**|string|行业|
|.**value**|number|需求量|

> 返回示例
>
> ```json
> [
>     {"industry":"教育","value":919},
>     {"industry":"制造业","value":1316},
>     {"industry":"金融业","value":1275},
>     ……
> ]
>```
