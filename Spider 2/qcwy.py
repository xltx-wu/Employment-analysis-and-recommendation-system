import datetime
from operator import le
import time
import pymysql
from selenium import webdriver
import re
from loguru import logger
class MyCommonSpider:
    def __init__(self):
        pass
    def get_data(self, url, send_keys='', pages_you_want=1, search_field='', search_button='', page_field='',
                    next_button=''):
            '''
            获取页面数据的函数
            :param url: 获取页面的地址
            :param send_keys: 搜索框中的搜索关键词
            :param pages_you_want: 你需要爬取的总页数
            :param search_field: 搜索框的xPath
            :param search_button: 搜索按钮的xPath
            :param page_field:  页面填写框
            :param next_button: 下一页按钮
            :return: 返回存储所有页面数据的list[str]
            '''
            browser = webdriver.Chrome()  # 获取一个浏览器对象 使用谷歌浏览器
            browser.maximize_window()  # 整个页面显示
            browser.get(url)  # 获取页面
            time.sleep(3)
            if send_keys != '' and search_field != '':
                browser.find_element_by_xpath(search_field).clear()  # 清空搜索框数据
                browser.find_element_by_xpath(search_field).send_keys(send_keys)  # 写入搜索关键字
            if search_button != '':
                browser.find_element_by_xpath(search_button).click()  # 点击搜索按钮
            time.sleep(3)
            datas = []  # 存储所有页面数据的列表
            for i in range(pages_you_want):
                time.sleep(3)
                print(f'当前提取第{i + 1}页数据')
                datas.append(browser.page_source)  # 添加首页数据
                if page_field != "":  # 如果当前页面有可以填写页码值的元素
                    if pages_you_want > 1:  # 想要的页数大于1
                        browser.find_element_by_xpath(page_field).clear()  # 清空页码数据
                        browser.find_element_by_xpath(page_field).send_keys((i + 2))  # 写入下一页的页码 +2
                browser.find_element_by_xpath(next_button).click()  # 点击下一页的页码元素
            logger.info('页面元素提取完毕')
            browser.quit()
            return datas
    def data_analysis(self, data_source, p_list):
            '''
            对整个提取的页面数据进行分析
            :param data_source: 待分析的数据列表
            :param p_list: 存有匹配页面元素正则表达字符串的列表
            :return analysis_list: 返回存有所有数据的二维数组
            '''
            analysis_dict = {}  # 存储分析好数据的字典
            for i in p_list:  # 循环在字典中创建空列表用于存放对应
                analysis_dict[i[0]] = []  # 根据存储在plist第i个列表中第0个的键名，生成字典对应关键字 每一个待匹配的正则表达式，生成一张列表
            for data in data_source:  # 循环提取单个页面的str数据
                for j in p_list:  # 循环提取待匹配的正则表达字符串
                    # print(j[0]) 键
                    # print(j[1])  正则匹配式
                    # print(analysis_dict[j[0]]) 列表
                    analysis_dict[j[0]].append(re.findall(j[1], data, re.S))  # 与当前页面数据进行正则匹配 循环查找出所有需要的数据 存入字典中对应的列表
            return analysis_dict
    def join_list_in_dict(self, data_dict):
            '''
            转换数据，将字典中的参数全部
            :param data_dict: 存储所有数据的字典 原格式为 k,v[][] 转换为 k,v[] value值从二维数组变为一维列表
            :return:
            '''
            for k, v in data_dict.items():
                changed_list = []
                for i in range(len(v)):
                    changed_list += v[i]
                data_dict[k] = changed_list
    def save_to_excel(self, analysis_dict, excel_name, sheet_name=''):
            '''
            存入excel表中
            :param analysis_dict: 处理后的数据字典
            :param excel_name: 存储的表名
            :return:
            '''
            import pandas as pd
            data = pd.DataFrame(analysis_dict)
            if sheet_name != "":
                data.to_excel(excel_name, index=True, sheet_name=sheet_name)
            else:
                data.to_excel(excel_name, index=True)
            print(f'excel文件{excel_name}已保存')
    def save_to_mysql(self,analysis_dict):
        _name = analysis_dict['工作名称']
        _salary = analysis_dict['工资水平']
        _company = analysis_dict['公司名称']
        _demand = analysis_dict['需求情况']
        _releasetime = analysis_dict['发布时间']
        _url = analysis_dict['连接地址']
        # mysql数据库连接:参数：链接地址，端口，用户名，密码，数据库
        db = pymysql.connect(host="localhost",port= 3306,user="root", password="990871", db="JobInfo",charset='utf8' )
        logger.info("数据库初始化成功")
        # 获取游标
        cursor = db.cursor()
        # 数据插入
        for i in range(len(_name)):
            sql = "INSERT INTO qcwy(name,salary, company, demand, releasetime,url)\
            VALUES ('{0}','{1}','{2}','{3}','{4}','{5}')".format(_name[i],_salary[i],_company[i],_demand[i],_releasetime[i],_url[i])
            try:
                cursor.execute(sql)
                db.commit()
                logger.info("插入数据成功,当前第"+str(i)+"条数据")
            except:
                # 发生错误时回滚
                db.rollback()
        db.close()
        logger.info("数据库关闭")
    def spdier(self,send_keys,pages_you_want):
            now = datetime.datetime.now()
            '''测试方法'''
            url = 'https://www.51job.com'  # 使用51job测试
            self.send_keys = send_keys
            self.pages_you_want = pages_you_want
            search_field = '//*[@id="kwdselectid"]'
            search_button = '/html/body/div[3]/div/div[1]/div/button'
            page_field = '//*[@id="jump_page"]'
            next_button = '/html/body/div[2]/div[3]/div/div[2]/div[4]/div[2]/div/div/div/span[3]'
    
            datas = self.get_data(url=url,
                                send_keys=send_keys,
                                pages_you_want=pages_you_want,
                                search_field=search_field,
                                search_button=search_button,
                                page_field=page_field,
                                next_button=next_button
                                )
            p_job = ['工作名称', '<p class="t"><span title="(.*?)".*?</span>']  # 工作名称
            p_time = ['发布时间', '<span class="time">(.*?)</span>']  # 发布时间
            p_salary = ['工资水平', '<p class="info"><span class="sal">(.*?)</span>']  # 工资水平
            p_company = ['公司名称', '<div class="er">.*?title="(.*?)".*?</a>']  # 公司名称
            p_link = ['连接地址', '<div class="e_icons ick"></div> <a href="(.*?)"']  # 链接地址
            p_needs = ['需求情况', '<span class="d at">(.*?)</span>']  # 需求
            p_list = [p_job, p_salary, p_company, p_needs, p_time, p_link]  # 匹配
            analysis_dict = self.data_analysis(data_source=datas,
                                            p_list=p_list)
            self.join_list_in_dict(data_dict=analysis_dict)  # 将的到的字典放入该函数中 将所有页的列表数据重新链接起来
            # self.save_to_excel(analysis_dict, f'{send_keys}招聘信息.xlsx', sheet_name=send_keys)  # 字典存入excel
            self.save_to_mysql(analysis_dict)
            logger.info(f'完成,时间：{datetime.datetime.now() - now}')  # 计算一下时间
if __name__ == '__main__':
    # keys:要爬取的岗位
    # keys = ['java','c++','python','大数据','嵌入式','前端']
    keys = ['安全','策划','运营','游戏']
    # 每一类要爬取的页数
    pages = 5
    qcwy = MyCommonSpider()
    for key in keys:
        logger.info("当前关键字:{0}".format(key))
        qcwy.spdier(key,pages)