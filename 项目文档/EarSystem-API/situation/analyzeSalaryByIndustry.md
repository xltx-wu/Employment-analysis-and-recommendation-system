# 按行业分析薪资

## 描述

- 获取按照行业分析薪资的统计信息

## 请求url

- `/situation/analyzeSalaryByIndustry`

## 请求方法

- GET

## 请求参数

无

## 返回参数

|参数名|数据类型|描述|
|---|:---:|---|
|-|Array\<object>|统计信息数据|
|.**industry**|string|行业|
|.**max**|number|最高薪资|
|.**min**|number|最低薪资|
|.**avg**|number|平均薪资|

> 返回示例
>
> ```json
> [
>     {"min":7,"avg":13,"max":19,"industry":"教育"},
>     {"min":8,"avg":12,"max":17,"industry":"制造业"},
>     {"min":7,"avg":11,"max":16,"industry":"金融业"},
>     ……
> ]
>```
