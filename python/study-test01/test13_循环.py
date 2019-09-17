#! /user/bin/python
# -*- coding:utf-8 -*-

# Python3 循环语句
# Python中的循环语句有 for 和 while


# while 循环
# Python中while语句的一般形式：
# while 判断条件：
#     语句

# 同样需要注意冒号和缩进。另外，在 Python 中没有 do..while 循环
# 以下实例使用了 while 来计算 1 到 100 的总和：
print("==================================1================================")
n = 100
sum = 0
counter = 1
while counter <= n:
    sum = sum + counter
    counter += 1
print("1 到 %d 之和为: %d" % (n,sum))


# while 循环使用 else 语句
# 在 while … else 在条件语句为 false 时执行 else 的语句块：
count = 0
while count < 5:
   print (count, " 小于 5")
   count = count + 1
else:
   print (count, " 大于或等于 5")


# 简单语句组
# 类似if语句的语法，如果你的while循环体中只有一条语句，你可以将该语句与while写在同一行中， 如下所示：
# flag = 1
# while (flag): print ('大讲堂!')



# for 语句
# Python for循环可以遍历任何序列的项目，如一个列表或者一个字符串
# for循环的一般格式如下：
# for <variable> in <sequence>:
#     <statements>
# else:
#     <statements>

languages = ["C", "C++", "Perl", "Python"] 
for x in languages:
    print (x)

# 以下 for 实例中使用了 break 语句，break 语句用于跳出当前循环体：
sites = ["Baidu", "Google","Runoob","Taobao"]
for site in sites:
    if site == "Runoob":
        print("大讲堂!")
        break
    print("循环数据 " + site)
else:
    print("没有循环数据!")
print("完成循环!")

# range()函数
# 如果你需要遍历数字序列，可以使用内置range()函数。它会生成数列，例如:
for i in range(5):
    print(i)
# 也可以使用range指定区间的值：
for i in range(55,59) :
    print(i)
# 也可以使range以指定数字开始并指定不同的增量(甚至可以是负数，有时这也叫做'步长'):
for i in range(0, 10, 3) :
    print(i)
# 负数：
for i in range(-10, -100, -30) :
    print(i)

# 可以结合range()和len()函数以遍历一个序列的索引,如下所示:
print("==================================2================================")
a = ['Google', 'Baidu', 'Runoob', 'Taobao', 'QQ']
for i in range(len(a)):
    print(i, a[i])
# 还可以使用range()函数来创建一个列表：
print(list(range(5)))  # [0, 1, 2, 3, 4]

# break和continue语句及循环中的else子句
# break 语句可以跳出 for 和 while 的循环体。如果你从 for 或 while 循环中终止，任何对应的循环 else 块将不执行。 实例如下：
for letter in 'Runoob':     # 第一个实例
    if letter == 'b':
        break
    print ('当前字母为 :', letter)
var = 10                    # 第二个实例
while var > 0:              
    print ('当期变量值为 :', var)
    var = var -1
    if var == 5:
        break
print ("Good bye!")
# continue语句被用来告诉Python跳过当前循环块中的剩余语句，然后继续进行下一轮循环
for letter in 'Runoob':     # 第一个实例
    if letter == 'o':        # 字母为 o 时跳过输出
        continue
    print ('当前字母 :', letter)
var = 10                    # 第二个实例
while var > 0:              
    var = var -1
    if var == 5:             # 变量为 5 时跳过输出
        continue
    print ('当前变量值 :', var)
print ("Good bye!")

# 循环语句可以有 else 子句，它在穷尽列表(以for循环)或条件变为 false (以while循环)导致循环终止时被执行,但循环被break终止时不执行
# 如下实例用于查询质数的循环例子:
for n in range(2, 10):
    for x in range(2, n):
        if n % x == 0:
            print(n, '等于', x, '*', n//x)
            break
    else:
        # 循环中没有找到元素
        print(n, ' 是质数')

# pass 语句
# Python pass是空语句，是为了保持程序结构的完整性
# pass 不做任何事情，一般用做占位语句，如下实例
# while True:
#     pass  # 等待键盘中断 (Ctrl+C)

# 最小的类:
class MyEmptyClass:
    pass

# 以下实例在字母为 o 时 执行 pass 语句块:
for letter in 'Runoob': 
    if letter == 'o':
        pass
        print ('执行 pass 块')
    print ('当前字母 :', letter)
print ("Good bye!")


# 使用内置 enumerate 函数进行遍历:
# for index, item in enumerate(sequence):
#     process(index, item)
sequence = [12, 34, 34, 23, 45, 76, 89]
for i, j in enumerate(sequence):
    print(i, j)

# 使用循环嵌套来实现99乘法法则:
#外边一层循环控制行数
#i是行数
i=1
while i<=9:
    #里面一层循环控制每一行中的列数
    j=1
    while j<=i:
        mut =j*i
        print("%d*%d=%d"%(j,i,mut), end="\t")
        j+=1
    print("")
    i+=1

# for 循环的嵌套使用实例：
for i in range(1,6):
    for j in range(1, i+1):
        print("*",end='')
    print('\r')

# 1-100 的和:
# print(sum(range(101))) # TypeError: 'int' object is not callable

# while 循环语句和 for 循环语句使用 else 的区别：
# 1、如果 else 语句和 while 循环语句一起使用，则当条件变为 False 时，则执行 else 语句
# 2、如果 else 语句和 for 循环语句一起使用，else 语句块只在 for 循环正常终止时执行

# pass只是为了防止语法错误
# if a>1:
#     pass #如果没有内容，可以先写pass，但是如果不写pass，就会语法错误
# pass就是一条空语句。在代码段中或定义函数的时候，如果没有内容，或者先不做任何处理，直接跳过，就可以使用pass

#十进制转化
while True:
    number = input('请输入一个整数(输入Q退出程序)：') 
    if number in ['q','Q']:
        break                #如果输入的是q或Q,结束退出
    elif not number.isdigit():
        print('您的输入有误！只能输入整数(输入Q退出程序)！请重新输入')
        continue         #如果输入的数字不是十进制,结束循环,重新开始
    else :
        number = int(number)
        print('十进制 --> 十六进制 ：%d -> 0x%x' %(number,number))
        print('十进制 -->   八进制 ：%d -> 0o%o' %(number,number))
        print('十进制 -->   二进制 ：%d ->'%number,bin(number))



