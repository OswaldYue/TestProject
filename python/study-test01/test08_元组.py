# Python3 元组
# Python 的元组与列表类似，不同之处在于元组的元素不能修改
# 元组使用小括号，列表使用方括号
# 元组创建很简单，只需要在括号中添加元素，并使用逗号隔开即可

tup1 = ('Google', 'Runoob', 1997, 2000)
tup2 = (1, 2, 3, 4, 5 )
tup3 = "a", "b", "c", "d";   #  不需要括号也可以
print(type(tup3))  # <class 'tuple'>
# 创建空元组
tup1 = ()
# 元组中只包含一个元素时，需要在元素后面添加逗号，否则括号会被当作运算符使用：
tup1 = (50)
print(type(tup1))     # 不加逗号，类型为整型  <class 'int'>
tup1 = (50,)
print(type(tup1))     # 加上逗号，类型为元组  <class 'tuple'>
# 元组与字符串类似，下标索引从0开始，可以进行截取，组合等
# 访问元组
# 元组可以使用下标索引来访问元组中的值，如下实例:
tup1 = ('Google', 'Runoob', 1997, 2000)
tup2 = (1, 2, 3, 4, 5, 6, 7 )
print ("tup1[0]: ", tup1[0])
print ("tup2[1:5]: ", tup2[1:5])

# 修改元组
# 元组中的元素值是不允许修改的，但我们可以对元组进行连接组合，如下实例:
tup1 = (12, 34.56)
tup2 = ('abc', 'xyz')
# 以下修改元组元素操作是非法的。
# tup1[0] = 100
# 创建一个新的元组
tup3 = tup1 + tup2
print (tup3)  # (12, 34.56, 'abc', 'xyz')
# 删除元组
# 元组中的元素值是不允许删除的，但我们可以使用del语句来删除整个元组，如下实例:
tup = ('Google', 'Runoob', 1997, 2000)
print (tup)
del tup
# print ("删除后的元组 tup : ")  # NameError: name 'tup' is not defined
# print (tup)

# 元组运算符
# 与字符串一样，元组之间可以使用 + 号和 * 号进行运算。这就意味着他们可以组合和复制，运算后会生成一个新的元组
# Python表达式	                              结果	                           描述
# len((1, 2, 3))	                         3	                              计算元素个数
# (1, 2, 3) + (4, 5, 6)	                     (1, 2, 3, 4, 5, 6)	              连接
# ('Hi!',) * 4	                             ('Hi!', 'Hi!', 'Hi!', 'Hi!')	  复制
# 3 in (1, 2, 3)	                         True	                          元素是否存在
# for x in (1, 2, 3): print (x,)	         1 2 3	                           迭代

# 元组索引，截取
# 因为元组也是一个序列，所以我们可以访问元组中的指定位置的元素，也可以截取索引中的一段元素，如下所示：
# 元组：L = ('Google', 'Taobao', 'Runoob')
# Python表达式	                  结果	                    描述
# L[2]	                         'Runoob'	               读取第三个元素
# L[-2]	                         'Taobao'	               反向读取；读取倒数第二个元素
# L[1:]	                         ('Taobao', 'Runoob')	   截取元素，从第二个开始后的所有元素

# 元组内置函数
# Python元组包含了以下内置函数
# 序号	  方法及描述	                                    实例
# 1	     len(tuple)计算元组元素个数	                        tuple1 = ('Google', 'Runoob', 'Taobao');len(tuple1);3
# 2	     max(tuple)返回元组中元素最大值	                    tuple2 = ('5', '4', '8');max(tuple2);'8'
# 3	     min(tuple)返回元组中元素最小值	                    tuple2 = ('5', '4', '8');min(tuple2);'4'
# 4	     tuple(seq)将列表转换为元组	                        list1= ['Google', 'Taobao', 'Runoob', 'Baidu'];tuple1=tuple(list1);tuple1;('Google', 'Taobao', 'Runoob', 'Baidu')

# 来看一个"可变的"tuple：
t = ('a', 'b', ['A', 'B'])
t[2][0] = 'X'
t[2][1] = 'Y'
print(t) # ('a', 'b', ['X', 'Y'])
t[2].append('Z') 
print(t) # ('a', 'b', ['X', 'Y', 'Z'])
del t[2][2]
print(t) # ('a', 'b', ['X', 'Y'])
# del t[1]  # TypeError: 'tuple' object doesn't support item deletion
# 这个tuple定义的时候有3个元素，分别是'a'，'b'和一个list。不是说tuple一旦定义后就不可变了吗？怎么后来又变了？
# 先看看定义的时候tuple包含的3个元素：当我们把list的元素'A'和'B'修改为'X'和'Y'后，tuple变为：表面上看，tuple的元素确实变了，但其实变的不是tuple的元素，而是list的元素
# tuple一开始指向的list并没有改成别的list，所以，tuple所谓的"不变"是说，tuple的每个元素，指向永远不变
# 即指向'a'，就不能改成指向'b'，指向一个list，就不能改成指向其他对象，但指向的这个list本身是可变的！理解了"指向不变"后，要创建一个内容也不变的tuple怎么做？
# 那就必须保证tuple的每一个元素本身也不能变

# 元组所指向的内存实际上保存的是元组内数据的内存地址集合（即 t[0], t[1]...t[n] 的内存地址），
# 且元组一旦建立，这个集合就不能增加修改删除，一旦集合内的地址发生改变，必须重新分配元组空间保存新的地址集（元组类似 C 语言里的指针数组，只不过这个数组不能被修改）
print("连接前：")
t1=(1,2,"3")
t2=("4",5,["d1","d2"])
print("t1=",t1)  
print("t2=",t2)  
print("t1:",id(t1))                # 1495622563304
print("t2:",id(t2))                # 1495622563784
print("t1[0]:",id(t1[0]))          # 140724081094912
print("t1[1]:",id(t1[1]))          # 140724081094944 
print("t1[2]:",id(t1[2]))          # 1495622529264
print("t2[0]:",id(t2[0]))          # 1495623248112
print("t2[1]:",id(t2[1]))          # 140724081095040
print("t2[2]:",id(t2[2]))          # 1495622529352
print("连接后：")
t1= t1+t2
print("t1(t1+t2):",id(t1))         # 1495622558536
print("t1[0]:",id(t1[0]))          # 140724081094912
print("t1[1]:",id(t1[1]))          # 140724081094944
print("t1[2]:",id(t1[2]))          # 1495622529264
print("t1[3]:",id(t1[3]))          # 1495623248112
print("t1[4]:",id(t1[4]))          # 140724081095040
print("t1[5]:",id(t1[5]))          # 1495622529352
t1[5].append("d3")                 
print("t1[5]增加d3:")
print("t1[5]=",t1[5])              # t1[5]= ['d1', 'd2', 'd3']       
print("t1[5]:",id(t1[5]))          # 1495622529352
print("t2=",t2)                    # ('4', 5, ['d1', 'd2', 'd3'])
# 测试结论：元组 t1 跟 t2 连接并赋值 t1 后，t1 地址发生变化（因地址集合变化），t1[0], t1[1], t1[2], t2[0], t2[1], t2[2] 地址不变且保存在连接后的 t1，
# 元组内数据根据自身类型确定是否可修改值（t1[0]..t1[4] 分别为不可修改的数据类型，t1[5] 为可修改的列表），连接后 t1[5] 跟 t2[2] 地址一样，t1[5] 变化将会导致 t2[2] 变化














