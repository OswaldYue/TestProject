#! user/bin/pythion
# -*- coding=utf-8 -*-


#Python3 基本数据类型
# Python 中的变量不需要声明。每个变量在使用前都必须赋值，变量赋值以后该变量才会被创建
# 在 Python 中，变量就是变量，它没有类型，我们所说的"类型"是变量所指的内存中对象的类型
# 等号（=）用来给变量赋值
# 等号（=）运算符左边是一个变量名,等号（=）运算符右边是存储在变量中的值。例如：
counter = 100          # 整型变量
miles   = 1000.0       # 浮点型变量
name    = "runoob"     # 字符串

print (counter)
print (miles)
print (name)
print("============================1=============================")
# 多个变量赋值
# Python允许你同时为多个变量赋值。例如：
a = b = c = 1
print(a)
print(b)
print(c)
# 以上实例，创建一个整型对象，值为 1，从后向前赋值，三个变量被赋予相同的数值。
# 也可以为多个对象指定多个变量。例如：
a, b, c = 1, 2, "runoob"
# 以上实例，两个整型对象 1 和 2 的分配给变量 a 和 b，字符串对象 "runoob" 分配给变量 c
print("============================2=============================")
print(a)
print(b)
print(c)



# 标准数据类型
# Python3 中有六个标准的数据类型：

# Number（数字）
# String（字符串）
# List（列表）
# Tuple（元组）
# Set（集合）
# Dictionary（字典）

# Python3 的六个标准数据类型中：
# 不可变数据（3 个）：Number（数字）、String（字符串）、Tuple（元组）
# 可变数据（3 个）：List（列表）、Dictionary（字典）、Set（集合）


# Number（数字）
# Python3 支持 int、float、bool、complex（复数）
# 在Python 3里，只有一种整数类型 int，表示为长整型，没有 python2 中的 Long
# 像大多数语言一样，数值类型的赋值和计算都是很直观的
# 内置的 type() 函数可以用来查询变量所指的对象类型
print("============================3=============================")
a, b, c, d = 20, 5.5, True, 4+3j
print(type(a), type(b), type(c), type(d)) #<class 'int'> <class 'float'> <class 'bool'> <class 'complex'>
# 此外还可以用 isinstance 来判断：
i = 111
print(isinstance(i,int))  #True
# isinstance 和 type 的区别在于：
# type()不会认为子类是一种父类类型
# isinstance()会认为子类是一种父类类型
# 注意：在 Python2 中是没有布尔型的，它用数字 0 表示 False，用 1 表示 True。到 Python3 中，把 True 和 False 定义成关键字了，但它们的值还是 1 和 0，它们可以和数字相加
# 当你指定一个值时，Number 对象就会被创建：
print("============================4=============================")
var1 = 1
var2 = 10
var3 = 100
print(var1)
print(var2)
print(var3)
# 也可以使用del语句删除一些对象引用
# del语句的语法是：
# del var1[,var2[,var3[....,varN]]]
# 可以通过使用del语句删除单个或多个对象。例如：
del var1
del var2, var3
# print(var1) #NameError: name 'var1' is not defined
# print(var2) #NameError: name 'var2' is not defined
# print(var3) #NameError: name 'var3' is not defined



# 数值运算
print("============================5=============================")
print(5 + 4)  # 加法
print(4.3 - 2) # 减法
print(3 * 7)  # 乘法
print(2 / 4)  # 除法，得到一个浮点数
print(2 // 4) # 除法，得到一个整数
print(17 % 3) # 取余 
print(2 ** 5) # 乘方  2的5次方
# 注意：
# 1、Python可以同时为多个变量赋值，如a, b = 1, 2
# 2、一个变量可以通过赋值指向不同类型的对象
# 3、数值的除法包含两个运算符：/ 返回一个浮点数，// 返回一个整数
# 4、在混合计算时，Python会把整型转换成为浮点数
# 数值类型实例
# int	               float	        complex
# 10	               0.0	            3.14j
# 100	               15.20	        45.j
# -786	               -21.9	        9.322e-36j
# 080	               32.3e+18	        .876j
# -0490	               -90.	            -.6545+0J
# -0x260	           -32.54e100	    3e+26J
# 0x69	               70.2E-12	        4.53e-7j
# Python还支持复数，复数由实数部分和虚数部分构成，可以用a + bj,或者complex(a,b)表示， 复数的实部a和虚部b都是浮点型




# String（字符串）
# Python中的字符串用单引号 ' 或双引号 " 括起来，同时使用反斜杠 \ 转义特殊字符。
# 字符串的截取的语法格式如下：
# 变量[头下标:尾下标]
# 索引值以 0 为开始值，-1 为从末尾的开始位置

# 从后面索引:      -6    -5    -4    -3    -2    -1
# 从前面索引:      0     1     2     3     4     5
#                 a     b     c     d     e     f
# 从前面截取:    :    1    2      3     4     5
# 从后面截取:    :    -5   -4     -3    -2    -1

print("============================6=============================")
str = 'Runoob' 
print (str)          # 输出字符串                               Runoob
print (str[0:-1])    # 输出第一个到倒数第二个的所有字符           Runoo
print (str[0])       # 输出字符串第一个字符                      R  
print (str[2:5])     # 输出从第三个开始到第五个的字符             noo
print (str[2:])      # 输出从第三个开始的后的所有字符             noob
print (str * 2)      # 输出字符串两次                           RunoobRunoob
print (str + "TEST") # 连接字符串                               RunoobTEST
# 注意:若是 变量[头下标:尾下标]这种标准的字符截取  则包头不包尾
# Python 使用反斜杠(\)转义特殊字符，如果你不想让反斜杠发生转义，可以在字符串前面添加一个 r，表示原始字符串：
print('mmm\noob')
print(r'mm\noob')
# 另外，反斜杠(\)可以作为续行符，表示下一行是上一行的延续。也可以使用 """...""" 或者 '''...''' 跨越多行
# 注意，Python 没有单独的字符类型，一个字符就是长度为1的字符串
# 与 C 字符串不同的是，Python 字符串不能被改变。向一个索引位置赋值，比如word[0] = 'm'会导致错误
# 注意：
# 1、反斜杠可以用来转义，使用r可以让反斜杠不发生转义
# 2、字符串可以用+运算符连接在一起，用*运算符重复
# 3、Python中的字符串有两种索引方式，从左往右以0开始，从右往左以-1开始
# 4、Python中的字符串不能改变






# List（列表）
# List（列表） 是 Python 中使用最频繁的数据类型
# 列表可以完成大多数集合类的数据结构实现。列表中元素的类型可以不相同，它支持数字，字符串甚至可以包含列表（所谓嵌套）
# 列表是写在方括号 [] 之间、用逗号分隔开的元素列表
# 和字符串一样，列表同样可以被索引和截取，列表被截取后返回一个包含所需元素的新列表
# 列表截取的语法格式如下：
# 变量[头下标:尾下标]  包头不包尾
# 索引值以 0 为开始值，-1 为从末尾的开始位置
# 加号 + 是列表连接运算符，星号 * 是重复操作。如下实例：
print("============================7=============================")
list = [ 'abcd', 786 , 2.23, 'runoob', 70.2 ]
tinylist = [123, 'runoob']
print (list)            # 输出完整列表                                ['abcd', 786, 2.23, 'runoob', 70.2]
print (list[0])         # 输出列表第一个元素                           abcd
print (list[1:3])       # 从第二个开始输出到第三个元素                  [786, 2.23]
print (list[2:])        # 输出从第三个元素开始的所有元素                [2.23, 'runoob', 70.2]
print (tinylist * 2)    # 输出两次列表                                [123, 'runoob', 123, 'runoob']
print (list + tinylist) # 连接列表                                    ['abcd', 786, 2.23, 'runoob', 70.2, 123, 'runoob']                             
# 与Python字符串不一样的是，列表中的元素是可以改变的：
a = [1, 2, 3, 4, 5, 6]
a[0] = 9
a[2:5] = [13, 14, 15]
print(a)
a[2:5] = []   # 将对应的元素值设置为 [] 
print(a)
# List 内置了有很多方法，例如 append()、pop() 等等
# 注意：
# 1、List写在方括号之间，元素用逗号隔开
# 2、和字符串一样，list可以被索引和切片
# 3、List可以使用+操作符进行拼接
# 4、List中的元素是可以改变的
# Python 列表截取可以接收第三个参数，参数作用是截取的步长，以下实例在索引 1 到索引 4 的位置并设置为步长为 2（间隔一个位置）来截取字符串：
letters = ['c','h','e','c','k','i','o']
print(letters)
sub_letters = letters[1:4:2]
print(sub_letters)
# 如果第三个参数为负数表示逆向读取，以下实例用于翻转字符串：

def reverWords(input):
    # 通过空格将字符串分割  把各个单词分割为列表
    inputWords = input.split(" ")

    # 翻转字符串
    # 假设列表 list = [1,2,3,4],  
    # list[0]=1, list[1]=2 ，而 -1 表示最后一个元素 list[-1]=4 ( 与 list[3]=4 一样)
    # inputWords[-1::-1] 有三个参数
    # 第一个参数 -1 表示最后一个元素
    # 第二个参数为空，表示移动到列表末尾
    # 第三个参数为步长，-1 表示逆向
    inputWords = inputWords[-1::-1]

    # 重新组合字符串
    output = ' '.join(inputWords)

    return output

if __name__ == "__main__":
    input = 'I like you'
    rw = reverWords(input)
    print(rw)





# Tuple（元组）
# 元组（tuple）与列表类似，不同之处在于元组的元素不能修改。元组写在小括号 () 里，元素之间用逗号隔开
# 元组中的元素类型也可以不相同：
print("============================8=============================")
tuple = ( 'abcd', 786 , 2.23, 'runoob', 70.2  )
tinytuple = (123, 'runoob')
print (tuple)             # 输出完整元组                                      ('abcd', 786, 2.23, 'runoob', 70.2)
print (tuple[0])          # 输出元组的第一个元素                               abcd
print (tuple[1:3])        # 输出从第二个元素开始到第三个元素                    (786, 2.23)
print (tuple[2:])         # 输出从第三个元素开始的所有元素                      (2.23, 'runoob', 70.2)
print (tinytuple * 2)     # 输出两次元组                                      (123, 'runoob', 123, 'runoob')
print (tuple + tinytuple) # 连接元组                                          ('abcd', 786, 2.23, 'runoob', 70.2, 123, 'runoob')
# 元组与字符串类似，可以被索引且下标索引从0开始，-1 为从末尾开始的位置。也可以进行截取（看上面，这里不再赘述）
# 其实，可以把字符串看作一种特殊的元组
tup = (1, 2, 3, 4, 5, 6)
print(tup[0])
print(tup[1:5])
# tup[0] = 11  # 修改元组元素的操作是非法的   TypeError: 'tuple' object does not support item assignment
# 虽然tuple的元素不可改变，但它可以包含可变的对象，比如list列表
# 构造包含 0 个或 1 个元素的元组比较特殊，所以有一些额外的语法规则
tup1 = ()    # 空元组
tup2 = (20,) # 一个元素，需要在元素后添加逗号
print(tup1)
print(tup2)
# string、list 和 tuple 都属于 sequence（序列）
# 注意：
# 1、与字符串一样，元组的元素不能修改
# 2、元组也可以被索引和切片，方法一样
# 3、注意构造包含 0 或 1 个元素的元组的特殊语法规则
# 4、元组也可以使用+操作符进行拼接






# Set（集合）
# 集合（set）是由一个或数个形态各异的大小整体组成的，构成集合的事物或对象称作元素或是成员
# 基本功能是进行成员关系测试和删除重复元素
# 可以使用大括号 { } 或者 set() 函数创建集合，注意：创建一个空集合必须用 set() 而不是 { }，因为 { } 是用来创建一个空字典
# 创建格式：
# parame = {value01,value02,...}
# 或者
# set(value)
print("============================9=============================")
student = {'Tom', 'Jim', 'Mary', 'Tom', 'Jack', 'Rose'}
print(student)   # 输出集合，重复的元素被自动去掉

# 成员测试
if 'Rose' in student :
    print('Rose 在集合中')
else :
    print('Rose 不在集合中')
 
# set可以进行集合运算
a = set('abracadabra')
b = set('alacazam')
print(a)                                                        #{'a', 'd', 'b', 'r', 'c'}
print(a - b)     # a 和 b 的差集                                 #{'b', 'd', 'r'}
print(a | b)     # a 和 b 的并集                                 #{'a', 'd', 'l', 'm', 'b', 'r', 'z', 'c'}
print(a & b)     # a 和 b 的交集                                 #{'a', 'c'} 
print(a ^ b)     # a 和 b 中不同时存在的元素                      #{'l', 'd', 'z', 'r', 'm', 'b'}





# Dictionary（字典）
# 字典（dictionary）是Python中另一个非常有用的内置数据类型
# 列表是有序的对象集合，字典是无序的对象集合。两者之间的区别在于：字典当中的元素是通过键来存取的，而不是通过偏移存取
# 字典是一种映射类型，字典用 { } 标识，它是一个无序的 键(key) : 值(value) 的集合
# 键(key)必须使用不可变类型
# 在同一个字典中，键(key)必须是唯一的
print("============================10=============================")
dict1 = {}
dict1['one'] = "1 - 教程"
dict1[2]     = "2 - 工具"
tinydict = {'name': 'runoob','code':1, 'site': 'www.runoob.com'}
print (dict1['one'])       # 输出键为 'one' 的值                               #1 - 教程
print (dict1[2])           # 输出键为 2 的值                                   #2 - 工具
print (tinydict)          # 输出完整的字典                                    #{'name': 'runoob', 'code': 1, 'site': 'www.runoob.com'}
print (tinydict.keys())   # 输出所有键                                        #dict_keys(['name', 'code', 'site'])
print (tinydict.values()) # 输出所有值                                        #dict_values(['runoob', 1, 'www.runoob.com'])

# 构造函数 dict() 可以直接从键值对序列中构建字典如下：
dict2 = dict([('Runoob', 1), ('Google', 2), ('Taobao', 3)])
print(dict2)                                                         #{'Runoob': 1, 'Google': 2, 'Taobao': 3}
dict3 = {x: x**2 for x in (2, 4, 6)}
print(dict3)                                                         #{2: 4, 4: 16, 6: 36}
dict4 = dict(Runoob=1, Google=2, Taobao=3)
print(dict4)                                                         #{'Runoob': 1, 'Google': 2, 'Taobao': 3}
# 另外，字典类型也有一些内置的函数，例如clear()、keys()、values()等
# 注意：
# 1、字典是一种映射类型，它的元素是键值对
# 2、字典的关键字必须为不可变类型，且不能重复
# 3、创建空字典使用 { }





# Python数据类型转换
# 有时候，我们需要对数据内置的类型进行转换，数据类型的转换，你只需要将数据类型作为函数名即可
# 以下几个内置的函数可以执行数据类型之间的转换。这些函数返回一个新的对象，表示转换的值
# int(x [,base])                               将x转换为一个整数  base参数可有可无   表示进制数  默认是10
# float(x)                                     将整数和字符串转换成浮点数
# complex([real[, imag]])                      创建一个值为 real + imag * j 的复数或者转化一个字符串或数为复数。如果第一个参数为字符串，则不需要指定第二个参数 
#   real -- int, long, float或字符串 
#   imag -- int, long, float
# str(x)                                       将x对象的string格式
# repr(x)                                      将x对象转化为供解释器读取的形式 返回对象的 string 格式
# eval(expression[, globals[, locals]])        执行一个字符串表达式，并返回表达式的值
# tuple(s)                                     将列表转换为元组
# list(s)                                      将元组或字符串转换为列表
# 注：元组与列表是非常类似的，区别在于元组的元素值不能修改，元组是放在括号中，列表是放于方括号中
# set(s)                                       创建一个无序不重复元素集，可进行关系测试，删除重复数据，还可以计算交集、差集、并集等
# dict(s)                                      用于创建一个字典
# frozenset(s)                                 返回一个冻结的集合，冻结后集合不能再添加或删除任何元素
# chr(x)                                       用一个范围在 range（256）内的（就是0～255）整数作参数，返回一个对应的字符   可以是十进制数,也可以是十六进制数
# ord(x)                                       将一个字符转换为它的整数值
# hex(x)                                       将一个整数转换为一个十六进制字符串
# oct(x)                                       将一个整数转换为一个八进制字符串































