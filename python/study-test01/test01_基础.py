# !user/bin/python3
# --coding:utf-8--
print("hello word")



# 查看python内部使用的关键字
import keyword
print(keyword.kwlist)


# python中使用缩进来表示表达式之间的关系 其他语言一般使用{}
if True:
    print("True")
else:
    print("False")

if False:
    print("answer ")
    print("True")
else:
    print("answer")
    print("False")



# 多行注释可以用多个 # 号，还有 ''' 和 """：
# 第一个注释
# 第二个注释
 
'''
第三注释
第四注释
'''
 
"""
第五注释
第六注释
"""

# 通常是一行写完一条语句，但如果语句很长，我们可以使用反斜杠(\)来实现多行语句
total = 1 + \
    2 + \
    3
print(total)

#数字(Number)类型
#python中数字有四种类型：整数、布尔型、浮点数和复数
#int (整数), 如 1, 只有一种整数类型 int，表示为长整型，没有 python2 中的 Long
#bool (布尔), 如 True
#float (浮点数), 如 1.23、3E-2
#complex (复数), 如 1 + 2j、 1.1 + 2.2j
i = 2
#TypeError: can only concatenate str (not "int") to str
# print("i = " + i)
print(i)


#字符串(String)
# python中单引号和双引号使用完全相同
# 使用三引号('''或""")可以指定一个多行字符串
# 转义符 '\'
# 反斜杠可以用来转义，使用r可以让反斜杠不发生转义。。 如 r"this is a line with \n" 则\n会显示，并不是换行
# 按字面意义级联字符串，如"this " "is " "string"会被自动转换为this is string
# 字符串可以用 + 运算符连接在一起，用 * 运算符重复
# Python 中的字符串有两种索引方式，从左往右以 0 开始，从右往左以 -1 开始
# Python中的字符串不能改变
# Python 没有单独的字符类型，一个字符就是长度为 1 的字符串
# 字符串的截取的语法格式如下：变量[头下标:尾下标:步长]
word = '字符串'
sentence = "这是一个句子。"
paragraph = """这是一个段落，
可以由多行组成"""

str1='RunoobMGW'
print(str1)                 # 输出字符串
print(str1[0:-1])           # 输出第一个到倒数第二个的所有字符
print(str1[0])              # 输出字符串第一个字符
print(str1[2:5])            # 输出从第三个开始到第五个的字符
print(str1[2:])             # 输出从第三个开始的后的所有字符
print(str1 * 2)             # 输出字符串两次
print(str1 + '你好')        # 连接字符串
print('------------------------------')
print('hello\nrunoobMGW')      # 使用反斜杠(\)+n转义特殊字符
print(r'hello\nrunoobMGW')     # 在字符串前面添加一个 r，表示原始字符串，不会发生转义


# 等待用户输入
# 执行下面的程序在按回车键后就会等待用户输入：
input("\n\n按下 enter 键后退出。")  #\n\n 两个换行字符


# 同一行显示多条语句
# Python可以在同一行中使用多条语句，语句之间使用分号(;)分割，以下是一个简单的实例：
import sys; x = 'runoob'; sys.stdout.write(x + '\n')


# 多个语句构成代码组
# 缩进相同的一组语句构成一个代码块，我们称之代码组。
# 像if、while、def和class这样的复合语句，首行以关键字开始，以冒号( : )结束，该行之后的一行或多行代码构成代码组。
# 我们将首行及后面的代码组称为一个子句(clause)。
# 如下实例：
# if expression : 
#    suite
# elif expression : 
#    suite 
# else : 
#    suite


# Print 输出
# print 默认输出是换行的，如果要实现不换行需要在变量末尾加上 end=""：
x="a"
y="b"
# 换行输出
print( x )
print( y )
print('---------')
# 不换行输出
print( x, end=" " )
print( y, end=" " )
print()


# import 与 from...import
# 在 python 用 import 或者 from...import 来导入相应的模块
# 将整个模块(somemodule)导入，格式为： import somemodule
# 从某个模块中导入某个函数,格式为： from somemodule import somefunction
# 从某个模块中导入多个函数,格式为： from somemodule import firstfunc, secondfunc, thirdfunc
# 将某个模块中的全部函数导入，格式为： from somemodule import *
# 例如:导入 sys 模块
import sys
print('================Python import mode==========================');
print ('命令行参数为:')
for i in sys.argv:
    print (i)  #若没有输入参数 这里就为空
#python 路径为['D:\\04.test-code\\python\\study-test01', 'D:\\07.win10DevSoft\\python3\\python37.zip', 'D:\\07.win10DevSoft\\python3\\DLLs', 'D:\\07.win10DevSoft\\python3\\lib', 'D:\\07.win10DevSoft\\python3', 'C:\\Users\\OswaldYue\\AppData\\Roaming\\Python\\Python37\\site-packages', 'D:\\07.win10DevSoft\\python3\\lib\\site-packages']
print ('\n python 路径为',sys.path) 

# 导入 sys 模块的 argv,path 成员
from sys import argv,path  #  导入特定的成员
print('================python from import===================================')
#path: ['D:\\04.test-code\\python\\study-test01', 'D:\\07.win10DevSoft\\python3\\python37.zip', 'D:\\07.win10DevSoft\\python3\\DLLs', 'D:\\07.win10DevSoft\\python3\\lib', 'D:\\07.win10DevSoft\\python3', 'C:\\Users\\OswaldYue\\AppData\\Roaming\\Python\\Python37\\site-packages', 'D:\\07.win10DevSoft\\python3\\lib\\site-packages']
print('path:',path) # 因为已经导入path成员，所以此处引用时不需要加sys.path



# 命令行参数
# 很多程序可以执行一些操作来查看一些基本信息，Python可以使用-h参数查看各参数帮助信息：
# Python3 命令行参数
# python test.py arg1 arg2 arg3
# python 中也可以所用 sys 的 sys.argv 来获取命令行参数：
# sys.argv 是命令行参数列表。
# len(sys.argv) 是命令行参数个数。
import sys
print ('参数个数为:', len(sys.argv), '个参数。')
print ('参数列表:', str(sys.argv))

