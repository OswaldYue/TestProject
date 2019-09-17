# Python3 集合
# 集合（set）是一个无序的不重复元素序列
# 可以使用大括号 { } 或者 set() 函数创建集合，注意：创建一个空集合必须用 set() 而不是 { }，因为 { } 是用来创建一个空字典
# 创建格式：
# parame = {value01,value02,...}
# 或者
# set(value)


basket = {'apple', 'orange', 'apple', 'pear', 'orange', 'banana'}
print(basket)                      # 这里演示的是去重功能  {'apple', 'orange', 'banana', 'pear'}
print('orange' in basket)                 # 快速判断元素是否在集合内  True
print('crabgrass' in basket)         # False
# 下面展示两个集合间的运算
a = set('abracadabra')
b = set('alacazam')
print(a)                 # {'b', 'c', 'a', 'd', 'r'}
print(a - b)                              # 集合a中包含而集合b中不包含的元素  {'b', 'd', 'r'}
print(a | b)                              # 集合a或b中包含的所有元素  {'l', 'b', 'c', 'a', 'm', 'd', 'r', 'z'}
print(a & b)                              # 集合a和b中都包含了的元素  {'a', 'c'}
print(a ^ b)                              # 不同时包含于a和b的元素  {'l', 'b', 'm', 'd', 'r', 'z'}

# 类似列表推导式，同样集合支持集合推导式(Set comprehension):
a = {x for x in 'abracadabra' if x not in 'abc'}
print(a)  # {'r', 'd'}

# 集合的基本操作
# 1、添加元素
# 语法格式如下：
# s.add( x )
# 将元素 x 添加到集合 s 中，如果元素已存在，则不进行任何操作
thisset = set(("Google", "Runoob", "Taobao"))
thisset.add("Facebook")
print(thisset)  # {'Runoob', 'Facebook', 'Google', 'Taobao'}
# 还有一个方法，也可以添加元素，且参数可以是列表，元组，字典等，语法格式如下：
# s.update( x )
# x 可以有多个，用逗号分开
thisset = set(("Google", "Runoob", "Taobao"))
thisset.update({1,3})
print(thisset) # {1, 'Runoob', 3, 'Google', 'Taobao'}
thisset.update([1,4],[5,6]) 
print(thisset)  # {'Google', 1, 3, 4, 5, 6, 'Runoob', 'Taobao'}

# 2、移除元素
# 语法格式如下：
# s.remove( x )
# 将元素 x 从集合 s 中移除，如果元素不存在，则会发生错误
thisset = set(("Google", "Runoob", "Taobao"))
thisset.remove("Taobao")
print(thisset) # {'Runoob', 'Google'}
# thisset.remove("Facebook")   # 不存在会发生错误 KeyError: 'Facebook'
# 此外还有一个方法也是移除集合中的元素，且如果元素不存在，不会发生错误。格式如下所示：
# s.discard( x )
thisset = set(("Google", "Runoob", "Taobao"))
thisset.discard("Facebook")  # 不存在不会发生错误
print(thisset) # {'Taobao', 'Runoob', 'Google'}
# 也可以设置随机删除集合中的一个元素，语法格式如下：
# s.pop() 
thisset = set(("Google", "Runoob", "Taobao", "Facebook"))
x = thisset.pop()
print(x)
# 多次执行测试结果都不一样
# 然而在交互模式，pop 是删除集合的第一个元素（排序后的集合的第一个元素）

# 3、计算集合元素个数
# 语法格式如下：
# len(s)
thisset = set(("Google", "Runoob", "Taobao"))
print(len(thisset))

# 4、清空集合
# 语法格式如下：
# s.clear()
thisset = set(("Google", "Runoob", "Taobao"))
thisset.clear()
print(thisset) # set()

# 5、判断元素是否在集合中存在
# 语法格式如下：
# x in s
thisset = set(("Google", "Runoob", "Taobao"))
print("Runoob" in thisset)  # True
print("Facebook" in thisset) # False

# 集合内置方法完整列表
# 方法	                            描述
# add()	                            为集合添加元素
# clear()	                        移除集合中的所有元素
# copy()	                        拷贝一个集合
# difference()	                    返回多个集合的差集
# difference_update()	            移除集合中的元素，该元素在指定的集合也存在。
# discard()	                        删除集合中指定的元素
# intersection()	                返回集合的交集
# intersection_update()	            返回集合的交集。
# isdisjoint()	                    判断两个集合是否包含相同的元素，如果没有返回 True，否则返回 False。
# issubset()	                    判断指定集合是否为该方法参数集合的子集。
# issuperset()	                    判断该方法的参数集合是否为指定集合的子集
# pop()	                            随机移除元素
# remove()	                        移除指定元素
# symmetric_difference()	        返回两个集合中不重复的元素集合。
# symmetric_difference_update()	    移除当前集合中在另外一个指定集合相同的元素，并将另外一个指定集合中不同的元素插入到当前集合中。
# union()	                        返回两个集合的并集
# update()	                        给集合添加元素


# s.update( "字符串" ) 与 s.update( {"字符串"} ) 含义不同:
#  s.update( {"字符串"} ) 将字符串添加到集合中，有重复的会忽略
#  s.update( "字符串" ) 将字符串拆分单个字符后，然后再一个个添加到集合中，有重复的会忽略
thisset = set(("Google", "Runoob", "Taobao"))
print(thisset)
thisset.update({"Facebook"})
print(thisset)  # {'Runoob', 'Taobao', 'Google', 'Facebook'}
thisset.update("Yahoo")
print(thisset)  # {'Facebook', 'o', 'Y', 'Google', 'Runoob', 'a', 'Taobao', 'h'}

# set() 中参数注意事项
# 1.创建一个含有一个元素的集合
my_set = set(('apple',))
print(my_set) # {'apple'}
# 2.创建一个含有多个元素的集合
my_set = set(('apple','pear','banana'))
print(my_set) # {'banana', 'apple', 'pear'}
# 3.如无必要，不要写成如下形式
my_set = set('apple')
print(my_set) # {'p', 'l', 'e', 'a'}
{'l', 'e', 'p', 'a'}
my_set1 = set(('apple'))
print(my_set1)

# 集合用 set.pop() 方法删除元素的不一样的感想如下:
# 1、对于 python 中列表 list、tuple 类型中的元素，转换集合是，会去掉重复的元素如下:
list = [1,1,2,3,4,5,3,1,4,6,5]
print(set(list)) # {1, 2, 3, 4, 5, 6}
tuple = (2,3,5,6,3,5,2,5)
print(set(tuple)) #{2, 3, 5, 6}
# 2、集合对 list 和 tuple 具有排序(升序)，举例如下:
print(set([9,4,5,2,6,7,1,8])) #{1, 2, 4, 5, 6, 7, 8, 9}
# 3、集合的 set.pop() 的不同认为
# 有人认为 set.pop() 是随机删除集合中的一个元素、我在这里说句非也！对于是字典和字符转换的集合是随机删除元素的。当集合是由列表和元组组成时、set.pop() 是从左边删除元素的如下:
# 列表实例：
set1 = set([9,4,5,2,6,7,1,8])
print(set1) # {1, 2, 4, 5, 6, 7, 8, 9}
print(set1.pop())
print(set1) # {2, 4, 5, 6, 7, 8, 9}
set1 = set((6,3,1,7,2,9,8,0))
print(set1) # {0, 1, 2, 3, 6, 7, 8, 9}
print(set1.pop())
print(set1) # {1, 2, 3, 6, 7, 8, 9}





