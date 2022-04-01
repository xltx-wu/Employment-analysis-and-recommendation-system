import requests
import xlwt
from loguru import logger
from bs4 import BeautifulSoup
import pymysql
class GWYSpider():
    def __init__(self):
        pass
    # 存储数据库
    def SpiderAndSaveToMysql(self,url):
        # mysql数据库连接:参数：链接地址，端口，用户名，密码，数据库
        db = pymysql.connect(host="localhost",port= 3306,user="root", password="990871", db="JobInfo",charset='utf8' )
        logger.info("数据库初始化成功")
        # 获取游标
        cursor = db.cursor()
        headers = {"User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.104 Safari/537.36",
        }
        response = requests.get(url)
        logger.info("获取响应成功！")
        soup = BeautifulSoup(response.text, 'html.parser')
        logger.info("数据解析成功")
        data = soup.find('table')
        if len(data) > 0:
            logger.info("数据获取成功!")
        else:
            logger.info("数据获取失败!")
            exit(1)
        enc = response.encoding
        logger.info("encoding = ", enc)
        # workbook = xlwt.Workbook(encoding='utf-8')
        # sheet = workbook.add_sheet('sheet1')
        col,row = 0,0
        coldata = []
        i = 0
        table = data.contents[0]
        for row_items in table.contents:
            for item in row_items.contents:
                item_str = item.getText().encode(enc).decode('utf-8')
                # sheet.write(row, col, item_str)
                # 跳过表头
                if row != 0:
                    # 记录每行数据
                    coldata.append(str(item_str))
                col += 1
            # 有数据
            if(len(coldata) != 0):
                sql = "INSERT INTO gwy(name,jobunit, jobname, jobdescribe, jobcode,jobnum,professional,schooling,degree,politicallandscape,experience,test,otherconditions,grassrootslevel,note)\
                    VALUES ('{0}','{1}','{2}','{3}','{4}','{5}','{6}','{7}','{8}','{9}','{10}','{11}','{12}','{13}','{14}')"\
                    .format(coldata[0],coldata[1],coldata[2],coldata[3],coldata[4],coldata[5],coldata[6],coldata[7],coldata[8],coldata[9],coldata[10],coldata[11],coldata[12],coldata[13],coldata[14])
                try:
                    cursor.execute(sql)
                    db.commit()
                    logger.info("插入数据成功,当前第"+str(i)+"条数据")
                    i = i + 1
                except:
                    # 发生错误时回滚
                    db.rollback()
                # 临时数据清除
                coldata.clear()
            row += 1
            col = 0
        logger.info("数据收集成功!")
        db.close()
        logger.info("数据库关闭")
        logger.info("数据已存储mysql!")
    # 存储excel
    def SpiderAndSaveToExcel(self,url):        
        headers = {"User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.104 Safari/537.36",
        }
        response = requests.get(url)
        logger.info("获取响应成功！")
        soup = BeautifulSoup(response.text, 'html.parser')
        logger.info("数据解析成功")
        data = soup.find('table')
        if len(data) > 0:
            logger.info("数据获取成功!")
        else:
            logger.info("数据获取失败!")
            exit(1)
        enc = response.encoding
        logger.info("encoding = ", enc)
        workbook = xlwt.Workbook(encoding='utf-8')
        sheet = workbook.add_sheet('sheet1')
        col,row = 0,0
        table = data.contents[0]
        for row_items in table.contents:
            for item in row_items.contents:
                item_str = item.getText().encode(enc).decode('utf-8')
                sheet.write(row, col, item_str)
                col += 1
            row += 1
            col = 0
        logger.info("数据收集成功!")
        with open('./公务员招聘信息.xls', 'wb') as f:
            workbook.save(f)
        logger.info("数据已存储excel!")
if __name__ == '__main__':
    # 2022陕西公务员招聘数据
    url = 'http://www.shaanxi.gov.cn/xw/ztzl/zxzt/zkzl/2022/2022/202202/t20220222_2211446.html'
    gwy = GWYSpider()
    gwy.SpiderAndSaveToMysql(url)
    # gwy.SpiderAndSaveToExcel(url)
