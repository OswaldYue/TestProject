#! /user/bin/python
# -*- coding:utf-8 -*-


# Python3 函数
# 函数是组织好的，可重复使用的，用来实现单一，或相关联功能的代码段
# 函数能提高应用的模块性，和代码的重复利用率。你已经知道Python提供了许多内建函数，比如print()。但你也可以自己创建函数，这被叫做用户自定义函数

# 定义一个函数
# 你可以定义一个由自己想要功能的函数，以下是简单的规则：

# 函数代码块以 def 关键词开头，后接函数标识符名称和圆括号 ()
# 任何传入参数和自变量必须放在圆括号中间，圆括号之间可以用于定义参数
# 函数的第一行语句可以选择性地使用文档字符串—用于存放函数说明
# 函数内容以冒号起始，并且缩进
# return [表达式] 结束函数，选择性地返回一个值给调用方。不带表达式的return相当于返回 None
# 语法
# Python 定义函数使用 def 关键字，一般格式如下：

# def 函数名（参数列表）:
#     函数体
# 默认情况下，参数值和参数名称是按函数声明中定义的顺序匹配起来的
def hello() :
   print("Hello World!")

hello()

def area(width, height):
    return width * height
 
def print_welcome(name):
    print("Welcome", name)
 
print_welcome("DWG")
w = 4
h = 5
print("width =", w, " height =", h, " area =", area(w, h))

# 函数调用
# 定义一个函数：给了函数一个名称，指定了函数里包含的参数，和代码块结构
# 这个函数的基本结构完成以后，你可以通过另一个函数调用执行，也可以直接从 Python 命令提示符执行
# 如下实例调用了 printme() 函数：
# 定义函数
def printme( str ):
   # 打印任何传入的字符串
   print (str)
   return
# 调用函数
printme("我要调用用户自定义函数!")
printme("再次调用同一函数")

# 参数传递
# 在 python 中，类型属于对象，变量是没有类型的：
# a=[1,2,3]
# a="Runoob"
# 以上代码中，[1,2,3] 是 List 类型，"Runoob" 是 String 类型，而变量 a 是没有类型，她仅仅是一个对象的引用（一个指针），可以是指向 List 类型对象，也可以是指向 String 类型对象

# 可更改(mutable)与不可更改(immutable)对象
# 在 python 中，strings, tuples, 和 numbers 是不可更改的对象，而 list,dict 等则是可以修改的对象
# 不可变类型：变量赋值 a=5 后再赋值 a=10，这里实际是新生成一个 int 值对象 10，再让 a 指向它，而 5 被丢弃，不是改变a的值，相当于新生成了a
# 可变类型：变量赋值 la=[1,2,3,4] 后再赋值 la[2]=5 则是将 list la 的第三个元素值更改，本身la没有动，只是其内部的一部分值被修改了

# python 函数的参数传递：
# 不可变类型：类似 c++ 的值传递，如 整数、字符串、元组。如fun（a），传递的只是a的值，没有影响a对象本身。比如在 fun（a）内部修改 a 的值，只是修改另一个复制的对象，不会影响 a 本身
# 可变类型：类似 c++ 的引用传递，如 列表，字典。如 fun（la），则是将 la 真正的传过去，修改后fun外部的la也会受影响
# python 中一切都是对象，严格意义我们不能说值传递还是引用传递，我们应该说传不可变对象和传可变对象

# python 传不可变对象实例
def ChangeInt( a ):
    a = 10
b = 2
ChangeInt(b)
print( b )  # 2
# 实例中有 int 对象 2，指向它的变量是 b，在传递给 ChangeInt 函数时，按传值的方式复制了变量 b，a 和 b 都指向了同一个 Int 对象，在 a=10 时，则新生成一个 int 值对象 10，并让 a 指向它

# 传可变对象实例
# 可变对象在函数里修改了参数，那么在调用这个函数的函数里，原始的参数也被改变了。例如：
# 可写函数说明
def changeme( mylist ):
   "修改传入的列表"
   mylist.append([1,2,3,4])
   print ("函数内取值: ", mylist)  # 函数内取值:  [10, 20, 30, [1, 2, 3, 4]]
   return
# 调用changeme函数
mylist = [10,20,30]
changeme( mylist )
print ("函数外取值: ", mylist) # 函数外取值:  [10, 20, 30, [1, 2, 3, 4]]

# 参数
# 以下是调用函数时可使用的正式参数类型：
# 必需参数
# 关键字参数
# 默认参数
# 不定长参数

# 必需参数
# 必需参数须以正确的顺序传入函数。调用时的数量必须和声明时的一样
# 调用 printme() 函数，你必须传入一个参数，不然会出现语法错误：

# 可写函数说明
def printme1( str ):
   "打印任何传入的字符串"
   print (str)
   return
# 调用 printme1 函数，不加参数会报错
# printme1()


# 关键字参数
# 关键字参数和函数调用关系紧密，函数调用使用关键字参数来确定传入的参数值
# 使用关键字参数允许函数调用时参数的顺序与声明时不一致，因为 Python 解释器能够用参数名匹配参数值
# 以下实例在函数 printme2() 调用时使用参数名：
#可写函数说明
def printme2( str ):
   "打印任何传入的字符串"
   print (str)
   return
#调用printme函数
printme2( str = "DWG")
# 以下实例中演示了函数参数的使用不需要使用指定顺序：
#可写函数说明
def printinfo( name, age ):
   "打印任何传入的字符串"
   print ("名字: ", name)
   print ("年龄: ", age)
   return
#调用printinfo函数
printinfo( age=50, name="runoob" )

# 默认参数
# 调用函数时，如果没有传递参数，则会使用默认参数。以下实例中如果没有传入 age 参数，则使用默认值：
#可写函数说明
def printinfo1( name, age = 35 ):
   "打印任何传入的字符串"
   print ("名字: ", name)
   print ("年龄: ", age)
   return
 
#调用printinfo函数
printinfo1( age=50, name="runoob" )
print ("------------------------")
printinfo1( name="runoob" )
# 默认参数必须放在最后面，否则会报：
# SyntaxError: non-default argument follows default argument


# 不定长参数
# 你可能需要一个函数能处理比当初声明时更多的参数。这些参数叫做不定长参数，和上述 2 种参数不同，声明时不会命名。基本语法如下：
# def functionname([formal_args,] *var_args_tuple ):
#    "函数_文档字符串"
#    function_suite
#    return [expression]
# 加了星号 * 的参数会以元组(tuple)的形式导入，存放所有未命名的变量参数
# 可写函数说明
def printinfo2( arg1, *vartuple ):
   "打印任何传入的参数"
   print ("输出: ")
   print (arg1)
   print (vartuple)
 
# 调用printinfo 函数
printinfo2( 70, 60, 50 )
# 如果在函数调用时没有指定参数，它就是一个空元组。我们也可以不向函数传递未命名的变量。如下实例：
# 可写函数说明
def printinfo3( arg1, *vartuple ):
   "打印任何传入的参数"
   print ("输出: ")
   print (arg1)
   for var in vartuple:
      print (var)
   return
# 调用printinfo 函数
printinfo3( 10 )
printinfo3( 70, 60, 50 )

# 还有一种就是参数带两个星号 **基本语法如下：
# def functionname([formal_args,] **var_args_dict ):
#    "函数_文档字符串"
#    function_suite
#    return [expression]
# 加了两个星号 ** 的参数会以字典的形式导入
def printinfo4( arg1, **vardict ):
   "打印任何传入的参数"
   print ("输出: ")
   print (arg1)
   print (vardict)  # {'a': 2, 'b': 3}
# 调用printinfo 函数
printinfo4(1, a=2,b=3)



# 声明函数时，参数中星号 * 可以单独出现，例如:
def f(a,b,*,c):
    return a+b+c
# 如果单独出现星号 * 后的参数必须用关键字传入
# f(1,2,3) # TypeError: f() takes 2 positional arguments but 3 were given
f(1,2,c=3) # 正确



# 匿名函数
# python 使用 lambda 来创建匿名函数
# 所谓匿名，意即不再使用 def 语句这样标准的形式定义一个函数
# lambda 只是一个表达式，函数体比 def 简单很多
# lambda的主体是一个表达式，而不是一个代码块。仅仅能在lambda表达式中封装有限的逻辑进去
# lambda 函数拥有自己的命名空间，且不能访问自己参数列表之外或全局命名空间里的参数
# 虽然lambda函数看起来只能写一行，却不等同于C或C++的内联函数，后者的目的是调用小函数时不占用栈内存从而增加运行效率
# 语法
# lambda 函数的语法只包含一个语句，如下：
# lambda [arg1 [,arg2,.....argn]]:expression

# 匿名函数
sum = lambda arg1, arg2: arg1 + arg2
# 调用sum函数
print ("相加后的值为 : ", sum( 10, 20 ))
print ("相加后的值为 : ", sum( 20, 20 ))
# lambda 匿名函数也是可以使用"关键字参数"进行参数传递
g= lambda x,y : x**2+y**2
g(x=2,y=3)
# 同样地，lambda 匿名函数也可以设定默认值
g= lambda x=0,y=0 : x**2+y**2
g(2)
# 注意：如果只打算给其中一部分参数设定默认值，那么应当将其放在靠后的位置（和定义函数时一样，避免歧义），否则会报错


# return语句
# return [表达式] 语句用于退出函数，选择性地向调用方返回一个表达式。不带参数值的return语句返回None。之前的例子都没有示范如何返回数值，以下实例演示了 return 语句的用法：
def sum1( arg1, arg2 ):
   # 返回2个参数的和."
   total = arg1 + arg2
   print ("函数内 : ", total)
   return total
# 调用sum函数
total = sum1( 10, 20 )
print ("函数外 : ", total)
# 函数可以返回多个值  如果是多个返回值默认是使用元组的


# 关于可更改与不可更改类型， 以及其它语言的值类型与引用类型的介绍，一直一来感觉都不太严谨， 说法是否正确有待验证
# 简单的说就是，不可更改类型传到函数里重新赋值后，两次输出值不一样，而可更改类型传到函数里对对象的"属性" 重新赋值后输出值一样
# 这里照搬一下例子：
def changeme1( mylist ):
   "修改传入的列表"
   mylist.append([1,2,3,4])
   print ("函数内取值: ", mylist) # 函数内取值:  [10, 20, 30, [1, 2, 3, 4]]
   return
# 调用changeme1函数
mylist = [10,20,30]
changeme1( mylist )
print ("函数外取值: ", mylist) # 函数外取值:  [10, 20, 30, [1, 2, 3, 4]]
# 请注意：上面特意用了引号标准的部分，对可变类型或者引用的操作修改的是传过来的对象的属性
# 可以这么理解(例子有点随意)：我在画画，小明来了说他也要画，我让他和我一起画，他如果和我在同一个画板上画，那么我们两的画就会同时改变
# 而如果他说不，我要另外用一块画板，然后重新拿了块画板画起来了，那么我们两的画自然就不一样了

# 同理可更改类型 的属性进行操作，这只是对引用的内存块里面的值进行操作，引用并没变，自然所有引用它的对象的值都变了
# 而对不可更改的对象进行操作，因为它引用的内存块只是对应一个固定的值，不能进行修改，要重新复制实际上就是更新引用

# 如果我们运行下面的例子，对可更改类型的引用进行修改，结果就不一样了
def changeme2( mylist ):
   "修改传入的列表"
   mylist = [1,2,3,4]
   print ("函数内取值: ", mylist) # 函数内取值:  [1, 2, 3, 4]
   return
 
# 调用changeme2函数
mylist = [10,20,30]
changeme2( mylist )
print ("函数外取值: ", mylist) # 函数外取值:  [10, 20, 30]

# 对于变量作用域，变量的访问以 L（Local） –> E（Enclosing） –> G（Global） –>B（Built-in） 的规则查找
# 即：在局部找不到，便会去局部外的局部找（例如闭包），再找不到就会去全局找，再者去内建中找
# 观察以下几个例子，均从内部函数输出变量 x：
# 1. 局部作用域
x = int(3.3)
x = 0
def outer():
    x = 1
    def inner():
        x = 2
        print(x) # 2 执行结果为 2，因为此时直接在函数 inner 内部找到了变量 x
    inner()
outer()
# 2.闭包函数外的函数中
x = int(3.3)
x = 0
def outer1():
    x = 1
    def inner():
        i = 2
        print(x) # 1 执行结果为 1，因为在内部函数 inner 中找不到变量 x，继续去局部外的局部——函数 outer 中找，这时找到了，输出 1
    inner()
outer1()
# 3.全局作用域
x = int(3.3)
x = 0
def outer2():
    o = 1
    def inner():
        i = 2
        print(x) # 0 执行结果为 0，在局部（inner函数）、局部的局部（outer函数）都没找到变量 x，于是访问全局变量，此时找到了并输出
    inner()
outer2()
# 4. 内建作用域
x = int(3.3)
g = 0
def outer3():
    o = 1
    def inner():
        i = 2
        print(x) # 3 执行结果为 3，在局部（inner函数）、局部的局部（outer函数）以及全局变量中都没有找到变量x，于是访问内建变量，此时找到了并输出
    inner()
outer3()

# 函数内可以访问全局变量，但不能更新(修改)其值
a = 10
def sum2 ( n ) :
   n += a
   print ('a = ', a, end = ' , ' )
   print ( 'n = ', n ) # a =  10 , n =  13
sum2(3)
# 如果引用了还没更新的值则会报错 :
a = 10
def sum3 ( n ) :
   n += a
   # a = 11 # 会报错 UnboundLocalError: local variable 'a' referenced before assignment
   print ('a = ', a, end = ' , ' )
   print ( 'n = ', n )
sum3(3)
# 可以加上 global 引用以更新变量值 :
a = 10
def sum4 ( n ) :
   global a
   n += a
   a = 11
   print ('a = ', a, end = ' , ' )
   print ( 'n = ', n ) # a =  11 , n =  13
sum4 ( 3 )
print ( '外 a = ', a ) # 外 a =  11

# 函数也可以以一个函数为其参数:
def hello1 () :
  print ("Hello1, world!")

def execute(f):
  "执行一个没有参数的函数"
  f()
execute(hello1) # Hello1, world!


# 可以通过 函数名.__doc__ 的方式来显示函数的说明文档，感觉这个如果在阅读比较大的程序时应该会有用，同时也在提示自己在写函数时注意添加文档说明
def add(a,b):
    "这是 add 函数文档"
    return a+b
print (add.__doc__) # 这是 add 函数文档

# 函数返回值的注意事项: 不同于 C 语言，Python 函数可以返回多个值，多个值以元组的方式返回:
def fun(a,b):    
    "返回多个值，结果以元组形式表示"
    return a,b,a+b
print(fun(1,2)) # (1, 2, 3)

# 函数的装饰器
# 在不改变当前函数的情况下, 给其增加新的功能:
def log(pr):#将被装饰函数传入
    def wrapper():
        print("**********")      
        return pr()#执行被装饰的函数
    return wrapper#将装饰完之后的函数返回（返回的是函数名）
@log
def pr():
    print("我是小小洋")
pr()
# **********
# 我是小小洋
# 回调函数和返回函数的实例就是装饰器


# 关于 global 和 nonlocal 的知识：
# nonlocal 只能修改外层函数的变量而不能修改外层函数所引用的全局变量，给一个例子如下：
x = 0
def outer4():
    global x
    x = 1    
    def inner():
        # nonlocal x # SyntaxError: no binding for nonlocal 'x' found
        x = 2        
        print(x) # 2
    inner()

outer4()
print(x) # 1


# global 关键字会跳过中间层直接将嵌套作用域内的局部变量变为全局变量:
# 测试代码如下:
num = 20
def outer5():
    num = 10
    def inner():
        global num
        print (num) # 20
        num = 100
        print (num) # 100
    inner()
    print(num) # 10
outer5()
print (num) # 100

# Python 定义一个 class 可以编写一个它的构造函数 _init_() 其中形参 self 在实例化时相当于 myname：
class demo:
    name = ""
    def _init_(self):
        self.ex()
        self.start()
    def inputName(self):
        global name
        name = input("輸入您的姓名：")
    def getFirstName(self):
        if len(name) <= 0:
            x = "別鬧！請輸入姓名！"
            return x
        else:
            x = name[0]
            return x
    def getLastName(self):
        if len(name) <= 1:
            y = "別鬧！長度不夠！"
            return y
        else:
            y = name[1:]
            return y
myname = demo()
myname.inputName()
print(myname.getFirstName())
print(myname.getLastName())


# 函数的参数分为形参和实参
# 1. 什么是形参
# 对于函数来说，形式参数简称形参，是指在定义函数时，定义的一个变量名
# 下面的代码中，x、y、z 就是形参
def foo(x, y, z):   # x,y,z 都是形参
    print("x=", x)
    print("y=", y)
    print("z=", z)
foo(1,3,5)  # 此处的1,3,5是实参
# 形参的作用：是用来接收调用函数时输入的值
# 2. 什么是实参
# 对于函数来说，实际参数简称实参
# 是指在调用函数时传入的实际的数据，这会被绑定到函数的形参上
# 函数调用时，将值绑定到变量名上，函数调用结束，解除绑定，并且实参将不再存在于程序中





