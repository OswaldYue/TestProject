# Python3 列表
# 序列是Python中最基本的数据结构。序列中的每个元素都分配一个数字 - 它的位置，或索引，第一个索引是0，第二个索引是1，依此类推。
# Python有6个序列的内置类型，但最常见的是列表和元组。
# 序列都可以进行的操作包括索引，切片，加，乘，检查成员。
# 此外，Python已经内置确定序列的长度以及确定最大和最小的元素的方法。
# 列表是最常用的Python数据类型，它可以作为一个方括号内的逗号分隔值出现。
# 列表的数据项不需要具有相同的类型
# 创建一个列表，只要把逗号分隔的不同的数据项使用方括号括起来即可。如下所示：
list1 = ['Google', 'Runoob', 1997, 2000]
list2 = [1, 2, 3, 4, 5 ]
list3 = ["a", "b", "c", "d"]
# 访问列表中的值
# 使用下标索引来访问列表中的值，同样你也可以使用方括号的形式截取字符，如下所示：
print ("list1[0]: ", list1[0])
print ("list2[1:5]: ", list2[1:5])
# 更新列表
# 你可以对列表的数据项进行修改或更新，你也可以使用append()方法来添加列表项，如下所示：
list = ['Google', 'Runoob', 1997, 2000]
print ("第三个元素为 : ", list[2])
list[2] = 2001
print ("更新后的第三个元素为 : ", list[2])
# 删除列表元素
# 可以使用 del 语句来删除列表的的元素，如下实例：
print ("原始列表 : ", list)
del list[2]
print ("删除第三个元素 : ", list)
# Python列表脚本操作符
# 列表对 + 和 * 的操作符与字符串相似。+ 号用于组合列表，* 号用于重复列表
# 如下所示：
# Python表达式	                              结果	                               描述
# len([1, 2, 3])	                         3	                                  长度
# [1, 2, 3] + [4, 5, 6]	                     [1, 2, 3, 4, 5, 6]	                  组合
# ['Hi!'] * 4	                             ['Hi!', 'Hi!', 'Hi!', 'Hi!']	      重复
# 3 in [1, 2, 3]	                         True	                              元素是否存在于列表中
# for x in [1, 2, 3]: print(x, end=" ")	     1 2 3	                              迭代

# Python列表截取与拼接
# Python的列表截取与字符串操作类型，如下所示：
# L=['Google', 'Runoob', 'Taobao']
# 操作：
# Python表达式	                结果	                描述
# L[2]	                       'Taobao'	               读取第三个元素
# L[-2]	                       'Runoob'	               从右侧开始读取倒数第二个元素: count from the right
# L[1:]	                       ['Runoob', 'Taobao']	   输出从第二个元素开始后的所有元素
# 列表还支持拼接操作：
squares = [1, 4, 9, 16, 25]
squares += [36, 49, 64, 81, 100]
print(squares)  # [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]

# 嵌套列表
# 使用嵌套列表即在列表里创建其它列表，例如：
a = ['a', 'b', 'c']
n = [1, 2, 3]
x = [a, n]
print(x)   # [['a', 'b', 'c'], [1, 2, 3]]
print(x[0])   # ['a', 'b', 'c']
print(x[0][1])  # b

# Python列表函数&方法
# Python包含以下函数:
# 序号	函数
# 1	   len(list)   列表元素个数
# 2	   max(list)   返回列表元素最大值
# 3	   min(list)   返回列表元素最小值
# 4	   list(seq)   将元组转换为列表

# Python包含以下方法:
# 序号	方法
# 1	   list.append(obj)                         在列表末尾添加新的对象
# 2	   list.count(obj)                          统计某个元素在列表中出现的次数
# 3	   list.extend(seq)                         在列表末尾一次性追加另一个序列中的多个值（用新列表扩展原来的列表）
# 4	   list.index(obj)                          从列表中找出某个值第一个匹配项的索引位置
# 5	   list.insert(index, obj)                  将对象插入列表
# 6	   list.pop([index=-1])                     移除列表中的一个元素（默认最后一个元素），并且返回该元素的值
# 7	   list.remove(obj)                         移除列表中某个值的第一个匹配项
# 8	   list.reverse()                           反向列表中元素
# 9	   list.sort( key=None, reverse=False)      对原列表进行排序
# 10   list.clear()                             清空列表
# 11   list.copy()                              复制列表

# 创建二维列表
list_2d = [ [0 for i in range(5)] for i in range(5)]
list_2d[0].append(3)
list_2d[0].append(5)
list_2d[2].append(7)
print(list_2d)  # [[0, 0, 0, 0, 0, 3, 5], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 7], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0]]

l = [i for i in range(0,15)]
print(l)  # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
print(l[::2])  # [0, 2, 4, 6, 8, 10, 12, 14]

print("===========================0====================================")
a = [1, 2, 3]
b = a
c = []
c = a
d = a[:]   # 通过截取列表而实现copy的列表 则是新生成一个列表
print(a, b, c, d)  # [1, 2, 3] [1, 2, 3] [1, 2, 3] [1, 2, 3]
print(id(a)) # 2730773688008
print(id(b)) # 2730773688008
print(id(c)) # 2730773688008
print(id(d)) # 2730773687944
b[0] = 'b'
print(a,b,c,d)
print(id(a))
print(id(b))
print(id(c))
print(id(d))
# 列表推导式书写形式：　　
# [表达式 for 变量 in 列表]
# 或者
# [表达式 for 变量 in 列表 if 条件]
print("============================1======================================")
li = [1,2,3,4,5,6,7,8,9]
print ([x**2 for x in li])  # [1, 4, 9, 16, 25, 36, 49, 64, 81]
print ([x**2 for x in li if x>5])  # [36, 49, 64, 81]
print (dict([(x,x*10) for x in li]))  # {1: 10, 2: 20, 3: 30, 4: 40, 5: 50, 6: 60, 7: 70, 8: 80, 9: 90}
print ([ (x, y) for x in range(10) if x % 2 if x > 3 for y in range(10) if y > 7 if y != 8 ])  # [(5, 9), (7, 9), (9, 9)]
# 分析: if x % 2 if x > 3 表示x % 2 !=0 且 x > 3 此时满足条件的只要 5 7 9   而if y > 7 if y != 8 的条件只要9  

vec=[2,4,6]
vec2=[4,3,-9]
sq = [vec[i]+vec2[i] for i in range(len(vec))]
print (sq)  # [6, 7, -3]
print ([x*y for x in [1,2,3] for y in  [1,2,3]])  # [1, 2, 3, 2, 4, 6, 3, 6, 9]
testList = [1,2,3,4]
def mul2(x):
    return x*2
print ([mul2(i) for i in testList])  # [2, 4, 6, 8]

print("================================2=====================================")
# 通过列表切片方式复制列表：
# 1.1 列表复制
my_foods = ['pizza', 'falafel', 'carrot cake']
friend_foods = my_foods[:]
print("My favorite foods are:")
print(my_foods)      # ['pizza', 'falafel', 'carrot cake']
print("\nMy friend's favorite foods are:")
print(friend_foods)  # ['pizza', 'falafel', 'carrot cake']
# 1.2 验证确实实现了两个列表
my_foods.append('cannoli')
friend_foods.append('ice cream')
print("My favorite foods are:")
print(my_foods)      # ['pizza', 'falafel', 'carrot cake', 'cannoli']
print("\nMy friend's favorite foods are:")  
print(friend_foods)  # ['pizza', 'falafel', 'carrot cake', 'ice cream']

print("===============================3======================================")
# 通过简单赋值方式复制列表：
my_foods = ['pizza', 'falafel', 'carrot cake']
friend_foods = my_foods
my_foods.append('cannoli')
friend_foods.append('ice cream')
print("My favorite foods are:") 
print(my_foods)     # ['pizza', 'falafel', 'carrot cake', 'cannoli', 'ice cream']
print("\nMy friend's favorite foods are:") 
print(friend_foods) # ['pizza', 'falafel', 'carrot cake', 'cannoli', 'ice cream']

#通过列表切片复制的列表其实复制后是新的列表 而通过简单赋值方式复制的列表则是原列表

# 说明了 Python 列表是链式存储结构，并非顺序存储。
a=[1,2,3,4]
for i in range(len(a)):
    print(id(a[i]))
a[1]=100
print("------------------------------")
for i in range(len(a)):
    print(id(a[i])) 
# 因为打印的地址不是连续的数组间隔地址

# Python 列表函数&方法的对象不仅可以是字符串也可以是列表
list1 = ["Googl",'Runoob',1997,2002]
list2 = [1,2,3,4,5,6]
list2.append(list1)
print ("list2:",list2)  # list2: [1, 2, 3, 4, 5, 6, ['Googl', 'Runoob', 1997, 2002]]
#简单理解：python 在操作对象的时候，会根据自身对对象的定义进行操作，这里 list1 被定义的就是列表，那么 list.append(obj) 在操作 list1 时就作为列表追加

print("===============================4======================================")
import copy
a = [1,2,3,4]
b = a
d = copy.copy(a)
b[0] = 'b'
print(a,b,d) # ['b', 2, 3, 4] ['b', 2, 3, 4] [1, 2, 3, 4]
print(id(a),id(b),id(d))  # 2580027454408 2580027454408 2580027453768

# 当创建一个列表时 可能需要为空的 此时最好先为其赋None值
list_empty = [None]*10
print(list_empty)  # [None, None, None, None, None, None, None, None, None, None]






