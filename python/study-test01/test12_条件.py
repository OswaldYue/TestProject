#! /user/bin/python
# -*- coding:utf-8 -*-

# Python3 条件控制
# Python 条件语句是通过一条或多条语句的执行结果（True 或者 False）来决定执行的代码块
# if 语句
# Python中if语句的一般形式如下所示：
# if condition_1:
#     statement_block_1
# elif condition_2:
#     statement_block_2
# else:
#     statement_block_3
# 如果 "condition_1" 为 True 将执行 "statement_block_1" 块语句
# 如果 "condition_1" 为False，将判断 "condition_2"
# 如果"condition_2" 为 True 将执行 "statement_block_2" 块语句
# 如果 "condition_2" 为False，将执行"statement_block_3"块语句
# Python 中用 elif 代替了 else if，所以if语句的关键字为：if – elif – else
# 注意：
# 1、每个条件后面要使用冒号 :，表示接下来是满足条件后要执行的语句块
# 2、使用缩进来划分语句块，相同缩进数的语句在一起组成一个语句块
# 3、在Python中没有switch – case语句

# 以下是一个简单的 if 实例：
print("==================================1================================")
var1 = 100
if var1:
    print ("1 - if 表达式条件为 true")
    print (var1)  # 100
 
var2 = 0
if var2: # 此if不会执行
    print ("2 - if 表达式条件为 true")
    print (var2)
print ("Good bye!")
# 从结果可以看到由于变量 var2 为 0，所以对应的 if 内的语句没有执行
# 以下实例演示了狗的年龄计算判断：
print("==================================2================================")
age = int(input("请输入你家狗狗的年龄: "))
print("")
if age <= 0:
    print("你是在逗我吧!")
elif age == 1:
    print("相当于 14 岁的人。")
elif age == 2:
    print("相当于 22 岁的人。")
elif age > 2:
    human = 22 + (age -2)*5
    print("对应人类年龄: ", human)
 
### 退出提示
input("点击 enter 键退出")


# 以下为if中常用的操作运算符:
# 操作符	描述
# <	       小于
# <=	   小于或等于
# >	       大于
# >=	   大于或等于
# ==	   等于，比较两个值是否相等
# !=	   不等于


# 该实例演示了数字猜谜游戏
print("==================================3================================")
number = 7
guess = -1
print("数字猜谜游戏!")
while guess != number:
    guess = int(input("请输入你猜的数字："))
 
    if guess == number:
        print("恭喜，你猜对了！")
    elif guess < number:
        print("猜的数字小了...")
    elif guess > number:
        print("猜的数字大了...")

# if 嵌套
# 在嵌套 if 语句中，可以把 if...elif...else 结构放在另外一个 if...elif...else 结构中
# if 表达式1:
#     语句
#     if 表达式2:
#         语句
#     elif 表达式3:
#         语句
#     else:
#         语句
# elif 表达式4:
#     语句
# else:
#     语句
print("==================================4================================")
num=int(input("输入一个数字："))
if num%2==0:
    if num%3==0:
        print ("你输入的数字可以整除 2 和 3")
    else:
        print ("你输入的数字可以整除 2，但不能整除 3")
else:
    if num%3==0:
        print ("你输入的数字可以整除 3，但不能整除 2")
    else:
        print  ("你输入的数字不能整除 2 和 3")

# 以下实例 x 为 0-99 取一个数，y 为 0-199 取一个数,如果 x>y 则输出 x，如果 x 等于 y 则输出 x+y，否则输出y
print("==================================5================================")
import random
x = random.choice(range(100))
y = random.choice(range(200))
if x > y:
    print('x:',x)
elif x == y:
    print('x+y:', x + y)
else:
    print('y:',y)


print("==================================6================================")
"""对上面例子的一个扩展"""
print("=======欢迎进入狗狗年龄对比系统========")
while True:
    try:
        age = int(input("请输入您家狗的年龄:"))
        print(" ")
        age = float(age)
        if age < 0:
            print("您在逗我？")
        elif age == 1:
            print("相当于人类14岁")
            break
        elif age == 2:
            print("相当于人类22岁")
            break
        else:
            human = 22 + (age - 2)*5
            print("相当于人类：",human)
            break
    except ValueError:
        print("输入不合法，请输入有效年龄")
###退出提示
input("点击 enter 键退出")


print("==================================7================================")
a=0
if a: 
    print(11)
else:
    print(22) # 22

a=None
if a:
    print(11)
else:
    print(22) # 22
a="None"
if a:
    print(11) # 11
else:
    print(22)
# 条件为真：不为 0, 不为None,True,字符串不为空串,"None"也视为不为空串的字符串

# 下表列出了不同数值类型的 true 和 false 情况：
# 类型	                     False	                       True
# 布尔	                     False(与0等价)	                True(与1等价)
# 数值	                     0,   0.0	                    非零的数值
# 字符串	                 '',  ""(空字符串)	             非空字符串
# 容器	                     [],  (),  {},  set()	        至少有一个元素的容器对象
# None	                     None	                        非None对象

# if 的两种格式
# 可以括号限定代码域，加强代码可读性
name ="pag"
if name == "pag":
  print(name=="pag")   # True

if (name == "pag"):{
  print(name == "pag") # True
}

# 取随机数扩展。取随机数直到两数相等，显示取数次数
print("==================================8================================")
import random
x = random.choice(range(100))
y = random.choice(range(100))
b,c = x,y
a = 1
print(x,y)
while x != y:
    if x > y:
        print('x:',x)
    elif x == y:
        print('x+y:', x + y,'totall cal ',a,'times') # 这句永远都执行不到
    else:
        print('y:',y)
    x = random.choice(range(100))
    y = random.choice(range(100))
    a = a+1
print('initialized data:',b,c,'x+y:', x + y,'total cal ',a,'times')







