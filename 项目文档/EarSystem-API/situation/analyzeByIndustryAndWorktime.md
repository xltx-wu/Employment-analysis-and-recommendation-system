# 按行业和工作经验分析

## 描述

- 获取按照行业和工作经验分析招聘需求的统计信息

## 请求url

- `/situation/analyzeByIndustryAndWorktime`

## 请求方法

- GET

## 请求参数

无

## 返回参数

|参数名|数据类型|描述|
|---|:---:|---|
|-|Array\<object>|统计信息数据|
|.**worktime**|number|工作经验|
|.**value**|number|需求量|

> 返回示例
>
> ```json
> {
>     "住宿和餐饮业":[
>         {"value":2,"workTime":0},
>         {"value":6,"workTime":1},
>         {"value":5,"workTime":2},
>         ……
>     ],
>     "金融业":[
>         ……
>     ],
>     "房地产业":[
>         ……
>     ],
>     ……
> }
>```
