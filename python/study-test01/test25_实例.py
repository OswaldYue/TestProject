#! /user/bin/python
# -*- coding:utf-8 -*-

# Python3 实例
# 以下实例在 Python3.4.3 版本下测试通过：

# Python Hello World 实例
# Python 数字求和
# Python 平方根
# Python 二次方程
# Python 计算三角形的面积
# Python 计算圆的面积
# Python 随机数生成
# Python 摄氏温度转华氏温度
# Python 交换变量
# Python if 语句
# Python 判断字符串是否为数字
# Python 判断奇数偶数
# Python 判断闰年
# Python 获取最大值函数
# Python 质数判断
# Python 输出指定范围内的素数
# Python 阶乘实例
# Python 九九乘法表
# Python 斐波那契数列
# Python 阿姆斯特朗数
# Python 十进制转二进制、八进制、十六进制
# Python ASCII码与字符相互转换
# Python 最大公约数算法
# Python 最小公倍数算法
# Python 简单计算器实现
# Python 生成日历
# Python 使用递归斐波那契数列
# Python 文件 IO
# Python 字符串判断
# Python 字符串大小写转换
# Python 计算每个月天数
# Python 获取昨天日期
# Python list 常用操作
# Python 约瑟夫生者死者小游戏
# Python 实现秒表功能
# Python 计算 n 个自然数的立方和
# Python 计算数组元素之和
# Python 数组翻转指定个数的元素
# Python 将列表中的头尾两个元素对调
# Python 将列表中的指定位置的两个元素对调
# Python 翻转列表
# Python 判断元素是否在列表中存在
# Python 清空列表
# Python 复制列表
# Python 计算元素在列表中出现的次数
# Python 计算列表元素之和
# Python 计算列表元素之积
# Python 查找列表中最小元素
# Python 查找列表中最大元素
# Python 移除字符串中的指定位置字符
# Python 判断字符串是否存在子字符串
# Python 判断字符串长度
# Python 使用正则表达式提取字符串中的 URL
# Python 将字符串作为代码执行
# Python 字符串翻转
# Python 对字符串切片及翻转
# Python 按键(key)或值(value)对字典进行排序
# Python 计算字典值之和
# Python 移除字典点键值(key/value)对
# Python 合并字典
# Python 将字符串的时间转换为时间戳
# Python 获取几天前的时间
# Python 将时间戳转换为指定格式日期
# Python 打印自己设计的字体
# Python 二分查找
# Python 线性查找
# Python 插入排序
# Python 快速排序
# Python 选择排序
# Python 冒泡排序
# Python 归并排序
# Python 堆排序
# Python 计数排序
# Python 希尔排序
# Python 拓扑排序

# 详情查询: https://www.runoob.com/python3/python3-examples.html 

x = True
y = False
z = False

if x or y and z:
    print("yes")  # yes  and的优先级更高
else:
    print("no")

x = True
y = False
z = False

# 优先级顺序为 NOT、AND、OR
if not x or y: # (!x) or y  false
    print(1)
elif not x or not y and z: # (!x) or (!y) and z  false 
    print(2)
elif not x or y or not y and x: # (!x) or y or (!y) and x   true
    print(3)
else:
    print(4)
