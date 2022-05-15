# 获取招聘信息

## 描述

- 根据用户输入筛选获取相应招聘信息

## 请求url

- `/jobinfo/getbykey`

## 请求方法

- POST

## 请求参数

|参数名|数据类型|描述|
|---|:---:|---|
|**city**|string|城市|
|**workTime**|number|工作经验|
|**industry**|string|行业|
|**minSalary**|number|最低薪资|
|**maxSalary**|number|最高薪资|
|**offset**|number|偏移量|
|**rows**|number|行数|

>请求示例
>
>```json
>{
>    "city":"北京市",
>    "workTime":2,
>    "industry":"制造业",
>    "minSalary":4,
>    "maxSalary":12,
>    "offset":0,
>    "rows":20
>}
>```

## 返回参数

|参数名|数据类型|描述|
|---|:---:|---|
|**-**|Array\<object>|招聘信息列表|
|object.**id**|number|招聘信息唯一标识符|
|object.**jobname**|string|工作名称|
|object.**company**|string|公司名称|
|object.**degree**|string|学历要求|

> 返回示例
>
> ```json
> [
>     {"id":10000001,"jobname":"工作名称","company":"崔食品有限责任公司","degree":null},
>     {"id":10000146,"jobname":"工作名称","company":"向記贸易有限责任公司","degree":null},
>     {"id":10000442,"jobname":"工作名称","company":"邵食品有限责任公司","degree":null},
>     {"id":10000450,"jobname":"工作名称","company":"袁有限责任公司","degree":null},
>     {"id":10000551,"jobname":"工作名称","company":"陈电讯有限责任公司","degree":null},
>        ...
> ]
>```
