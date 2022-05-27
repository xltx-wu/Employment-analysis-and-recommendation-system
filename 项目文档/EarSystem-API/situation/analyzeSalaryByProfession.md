# 按专业分析薪资

## 描述

- 获取按照专业分析薪资的统计信息

## 请求url

- `/situation/analyzeSalaryByProfession`

## 请求方法

- GET

## 请求参数

无

## 返回参数

|参数名|数据类型|描述|
|---|:---:|---|
|-|Array\<object>|统计信息数据|
|.**profession**|string|专业|
|.**max**|number|最高薪资|
|.**min**|number|最低薪资|
|.**avg**|number|平均薪资|

> 返回示例
>
> ```json
> [
>     {"profession":"心理学类","min":8,"avg":13,"max":18},
>     {"profession":"历史学类","min":7,"avg":12,"max":18},
>     {"profession":"外国语言文学类","min":7,"avg":12,"max":18},
>     ……
> ]
>```
