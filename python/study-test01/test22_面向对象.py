# Python3 面向对象
# Python从设计之初就已经是一门面向对象的语言，正因为如此，在Python中创建一个类和对象是很容易的。本章节我们将详细介绍Python的面向对象编程
# 如果你以前没有接触过面向对象的编程语言，那你可能需要先了解一些面向对象语言的一些基本特征，在头脑里头形成一个基本的面向对象的概念，这样有助于你更容易的学习Python的面向对象编程
# 接下来我们先来简单的了解下面向对象的一些基本特征

# 面向对象技术简介
# 类(Class): 用来描述具有相同的属性和方法的对象的集合。它定义了该集合中每个对象所共有的属性和方法。对象是类的实例
# 方法：类中定义的函数
# 类变量：类变量在整个实例化的对象中是公用的。类变量定义在类中且在函数体之外。类变量通常不作为实例变量使用
# 数据成员：类变量或者实例变量用于处理类及其实例对象的相关的数据
# 方法重写：如果从父类继承的方法不能满足子类的需求，可以对其进行改写，这个过程叫方法的覆盖（override），也称为方法的重写
# 局部变量：定义在方法中的变量，只作用于当前实例的类
# 实例变量：在类的声明中，属性是用变量来表示的。这种变量就称为实例变量，是在类声明的内部但是在类的其他成员方法之外声明的
# 继承：即一个派生类（derived class）继承基类（base class）的字段和方法。继承也允许把一个派生类的对象作为一个基类对象对待
#   例如，有这样一个设计：一个Dog类型的对象派生自Animal类，这是模拟"是一个（is-a）"关系（例如，Dog是一个Animal）
# 实例化：创建一个类的实例，类的具体对象
# 对象：通过类定义的数据结构实例。对象包括两个数据成员（类变量和实例变量）和方法
# 和其它编程语言相比，Python 在尽可能不增加新的语法和语义的情况下加入了类机制
# Python中的类提供了面向对象编程的所有基本功能：类的继承机制允许多个基类，派生类可以覆盖基类中的任何方法，方法中可以调用基类中的同名方法
# 对象可以包含任意数量和类型的数据
# 类定义
# 语法格式如下：
# class ClassName:
#     <statement-1>
#     .
#     .
#     .
#     <statement-N>
# 类实例化后，可以使用其属性，实际上，创建一个类之后，可以通过类名访问其属性
# 类对象
# 类对象支持两种操作：属性引用和实例化
# 属性引用使用和 Python 中所有的属性引用一样的标准语法：obj.name
# 类对象创建后，类命名空间中所有的命名都是有效属性名。所以如果类定义是这样:
class MyClass:
    """一个简单的类实例"""
    i = 12345
    def f(self):
        return 'hello world'
# 实例化类
x = MyClass()
# 访问类的属性和方法
print("MyClass 类的属性 i 为：", x.i)
print("MyClass 类的方法 f 输出为：", x.f())
# 以上创建了一个新的类实例并将该对象赋给局部变量 x，x 为空的对象
# 执行以上程序输出结果为：
# 类有一个名为 __init__() 的特殊方法（构造方法），该方法在类实例化时会自动调用，像下面这样：
# def __init__(self):
#     self.data = []
# 类定义了 __init__() 方法，类的实例化操作会自动调用 __init__() 方法。如下实例化类 MyClass，对应的 __init__() 方法就会被调用:
x = MyClass()
# 当然， __init__() 方法可以有参数，参数通过 __init__() 传递到类的实例化操作上。例如:
class Complex:
    def __init__(self, realpart, imagpart):
        self.r = realpart
        self.i = imagpart
x = Complex(3.0, -4.5)
print(x.r, x.i)  # 3.0 -4.5 
# self代表类的实例，而非类
# 类的方法与普通的函数只有一个特别的区别——它们必须有一个额外的第一个参数名称, 按照惯例它的名称是 self
class Test:
    def prt(self):
        print(self) # <__main__.Test object at 0x00000230141CF148>
        print(self.__class__) # <class '__main__.Test'>
t = Test()
t.prt()

# 类的方法
# 在类的内部，使用 def 关键字来定义一个方法，与一般函数定义不同，类方法必须包含参数 self, 且为第一个参数，self 代表的是类的实例
#类定义
class people:
    #定义基本属性
    name = ''
    age = 0
    #定义私有属性,私有属性在类外部无法直接进行访问
    __weight = 0
    street="aaa"
    __city = "xian" # 双划线开头且尾部没有划线的变量视为私有
    __gent__ = "man"
    #定义构造方法
    def __init__(self,n,a,w):
        self.name = n
        self.age = a
        self.__weight = w
    def speak(self):
        print("%s 说: 我 %d 岁。" %(self.name,self.age)) # runoob 说: 我 10 岁。
# 实例化类
p = people('runoob',10,30)
p.speak()
print(p.street) # aaa
# print(p.__weight__) # AttributeError: 'people' object has no attribute '__weight__'
# print(p.__city) # AttributeError: 'people' object has no attribute '__city'
print(p.__gent__) # man

# 继承
# Python 同样支持类的继承，如果一种语言不支持继承，类就没有什么意义。派生类的定义如下所示:
# class DerivedClassName(BaseClassName1):
#     <statement-1>
#     .
#     .
#     .
#     <statement-N>
# 需要注意圆括号中基类的顺序，若是基类中有相同的方法名，而在子类使用时未指定，python从左至右搜索 即方法在子类中未找到时，从左到右查找基类中是否包含方法
# BaseClassName（示例中的基类名）必须与派生类定义在一个作用域内。除了类，还可以用表达式，基类定义在另一个模块中时这一点非常有用:
# class DerivedClassName(modname.BaseClassName):

#单继承示例
class student(people):
    grade = ''
    def __init__(self,n,a,w,g):
        #调用父类的构函
        people.__init__(self,n,a,w)
        self.grade = g
    #覆写父类的方法
    def speak(self):
        print("%s 说: 我 %d 岁了，我在读 %d 年级"%(self.name,self.age,self.grade))  # ken 说: 我 10 岁了，我在读 3 年级
s = student('ken',10,60,3)
s.speak()

# 多继承
# Python同样有限的支持多继承形式。多继承的类定义形如下例:
# class DerivedClassName(Base1, Base2, Base3):
#     <statement-1>
#     .
#     .
#     .
#     <statement-N>
# 需要注意圆括号中父类的顺序，若是父类中有相同的方法名，而在子类使用时未指定，python从左至右搜索 即方法在子类中未找到时，从左到右查找父类中是否包含方法
#另一个类，多重继承之前的准备
class speaker():
    topic = ''
    name = ''
    def __init__(self,n,t):
        self.name = n
        self.topic = t
    def speak(self):
        print("我叫 %s，我是一个演说家，我演讲的主题是 %s"%(self.name,self.topic))
#多重继承
class sample(speaker,student):
    a =''
    def __init__(self,n,a,w,g,t):
        student.__init__(self,n,a,w,g)
        speaker.__init__(self,n,t)
test = sample("Tim",25,80,4,"Python")
test.speak()   #方法名同，默认调用的是在括号中排前地父类的方法  我叫 Tim，我是一个演说家，我演讲的主题是 Python

# 方法重写
# 如果你的父类方法的功能不能满足你的需求，你可以在子类重写你父类的方法，实例如下：
class Parent:        # 定义父类
   def myMethod(self):
      print ('调用父类方法')
 
class Child(Parent): # 定义子类
   def myMethod(self):
      print ('调用子类方法')
 
c = Child()          # 子类实例
c.myMethod()         # 子类调用重写方法
super(Child,c).myMethod() #用子类对象调用父类已被覆盖的方法
# super() 函数是用于调用父类(超类)的一个方法
# 执行以上程序输出结果为：

# Python 子类继承父类构造函数说明
# 如果在子类中需要父类的构造方法就需要显式地调用父类的构造方法，或者不重写父类的构造方法
# 子类不重写 __init__，实例化子类时，会自动调用父类定义的 __init__
class Father(object):
    def __init__(self, name):
        self.name=name
        print ( "name: %s" %( self.name) )
    def getName(self):
        return 'Father ' + self.name
 
class Son(Father):
    def getName(self):
        return 'Son '+self.name
 
if __name__=='__main__':
    son=Son('runoob')
    print ( son.getName() )

# 如果重写了__init__ 时，实例化子类，就不会调用父类已经定义的 __init__，语法格式如下：
class Son1(Father):
    def __init__(self, name):
        print ( "hi1" )
        self.name =  name
    def getName(self):
        return 'Son '+self.name
if __name__=='__main__':
    son1=Son1('runoob')
    print ( son1.getName())

# 如果重写了__init__ 时，要继承父类的构造方法，可以使用 super 关键字：
# super(子类，self).__init__(参数1，参数2，....)
# 还有一种经典写法：
# 父类名称.__init__(self,参数1，参数2，...)

class Son2(Father):
    def __init__(self, name):
        super(Son2, self).__init__(name)
        print ("hi2")
        self.name =  name
    def getName(self):
        return 'Son '+self.name
 
if __name__=='__main__':
    son2=Son2('runoob')
    print ( son2.getName() )

# 类属性与方法
# 类的私有属性
# __private_attrs：两个下划线开头，声明该属性为私有，不能在类的外部被使用或直接访问。在类内部的方法中使用时 self.__private_attrs
# 类的方法
# 在类的内部，使用 def 关键字来定义一个方法，与一般函数定义不同，类方法必须包含参数 self，且为第一个参数，self 代表的是类的实例
# self 的名字并不是规定死的，也可以使用 this，但是最好还是按照约定是用 self
# 类的私有方法
# __private_method：两个下划线开头，声明该方法为私有方法，只能在类的内部调用 ，不能在类的外部调用。self.__private_methods
class JustCounter:
    __secretCount = 0  # 私有变量
    publicCount = 0    # 公开变量
 
    def count(self):
        self.__secretCount += 1
        self.publicCount += 1
        print (self.__secretCount)
 
counter = JustCounter()
counter.count()  # 1
counter.count()  # 2 
print (counter.publicCount) # 2
# print (counter.__secretCount)  # 报错，实例不能访问私有变量  AttributeError: 'JustCounter' object has no attribute '__secretCount'

# 类的私有方法实例如下：
class Site:
    def __init__(self, name, url):
        self.name = name       # public
        self.__url = url   # private
 
    def who(self):
        print('name  : ', self.name)
        print('url : ', self.__url)
 
    def __foo(self):          # 私有方法
        print('这是私有方法')
 
    def foo(self):            # 公共方法
        print('这是公共方法')
        self.__foo()
 
x = Site('菜鸟教程', 'www.runoob.com')
x.who()        # 正常输出
x.foo()        # 正常输出
# x.__foo()      # 报错  AttributeError: 'Site' object has no attribute '__foo'

# 类的专有方法：
# __init__ : 构造函数，在生成对象时调用
# __del__ : 析构函数，释放对象时使用
# __repr__ : 打印，转换
# __setitem__ : 按照索引赋值
# __getitem__: 按照索引获取值
# __len__: 获得长度
# __cmp__: 比较运算
# __call__: 函数调用
# __add__: 加运算
# __sub__: 减运算
# __mul__: 乘运算
# __truediv__: 除运算
# __mod__: 求余运算
# __pow__: 乘方

# 运算符重载
# Python同样支持运算符重载，我们可以对类的专有方法进行重载，实例如下：
class Vector:
   def __init__(self, a, b):
      self.a = a
      self.b = b
 
   def __str__(self):
      return 'Vector (%d, %d)' % (self.a, self.b)
   
   def __add__(self,other):
      return Vector(self.a + other.a, self.b + other.b)
 
v1 = Vector(2,10)
v2 = Vector(5,-2)
print (v1 + v2)  # Vector (7, 8)
# v1 + v2 调用的是__add__()方法  然后打印时调用了__str__()方法


# 针对 __str__ 方法给出一个比较直观的例子：
class people1:
    def __init__(self,name,age):
        self.name=name
        self.age=age

    def __str__(self):
        return '这个人的名字是%s,已经有%d岁了！'%(self.name,self.age)

a=people1('孙悟空',999)
print(a) # 这个人的名字是孙悟空,已经有999岁了！
#如果将上面类的__str__()方法注释,将得到<__main__.people1 object at 0x000001D754DCA548>这么一串字符

# 最新的 Python3.7 中(2018.07.13)，对类的构造函数进行了精简
# 3.7 版本：
from dataclasses import dataclass
@dataclass
class A:
  x:int
  y:int
  def add(self):
    return self.x + self.y

# 相当于以前的：
class A1:
  def __init__(self,x,y):
    self.x = x
    self.y = y
  def add(self):
    return self.x + self.y

# 实际上，对于类 A， 实例化时不需要参数；
# 而对于类 B，实例化时需要输入 (x, y) 参数，这才是两者的核心区别
# 定义类时，若需要输入参数，则一般必须使用 __init__()方法
# 若不需要输入参数，是否使用 __init__() 方法都可以
# 和版本是否对类的构造函数进行了精简，关系不大


# Python3 中类的静态方法、普通方法、类方法
# 静态方法: 用 @staticmethod 装饰的不带 self 参数的方法叫做静态方法，类的静态方法可以没有参数，可以直接使用类名调用
# 普通方法: 默认有个self参数，且只能被对象调用
# 类方法: 默认有个 cls 参数，可以被类和对象调用，需要加上 @classmethod 装饰器
class Classname:
    @staticmethod
    def fun():
        print('静态方法')

    @classmethod
    def a(cls):
        print('类方法')

    # 普通方法
    def b(self):
        print('普通方法')

Classname.fun()
Classname.a()

C = Classname()
C.fun()
C.a()
C.b()

# 反向运算符重载：
# __radd__: 加运算
# __rsub__: 减运算
# __rmul__: 乘运算
# __rdiv__: 除运算
# __rmod__: 求余运算
# __rpow__: 乘方
# 复合重载运算符：
# __iadd__: 加运算
# __isub__: 减运算
# __imul__: 乘运算
# __idiv__: 除运算
# __imod__: 求余运算
# __ipow__: 乘方
# 运算符重载的时候：
class Vector1:
    def __init__(self, a, b):
        self.a = a
        self.b = b
    def __str__(self):
        return 'Vector1 (%d, %d)' % (self.a, self.b)

    def __repr__(self):
        return 'Vector1 (%d, %d)' % (self.a, self.b)

    def __add__(self,other):
        if other.__class__ is Vector1:
            return Vector1(self.a + other.a, self.b + other.b)
        elif other.__class__ is int:
            return Vector1(self.a+other,self.b)

    def __radd__(self,other):
        """反向算术运算符的重载
        __add__运算符重载可以保证V+int的情况下不会报错，但是反过来int+V就会报错，通过反向运算符重载可以解决此问题
        """

        if other.__class__ is int or other.__class__ is float:
            return Vector1(self.a+other,self.b)
        else:
            raise ValueError("值错误")

    def __iadd__(self,other):
        """复合赋值算数运算符的重载
        主要用于列表，例如L1+=L2,默认情况下调用__add__，会生成一个新的列表，
        当数据过大的时候会影响效率，而此函数可以重载+=，使L2直接增加到L1后面
        """

        if other.__class__ is Vector1:
            return Vector1(self.a + other.a, self.b + other.b)
        elif other.__class__ is int:
            return Vector1(self.a+other,self.b)
v1 = Vector1(2,10)
v2 = Vector1(5,-2)
print (v1 + v2)  # Vector1 (7, 8) 调用__add__()方法
print (v1+5) # Vector1 (7, 10) 调用__radd__()方法
print (6+v2) # Vector1 (11, -2) 调用__iadd__()方法


# 关于 __name__
# 首先需要了解 __name__ 是属于 python 中的内置类属性，就是它会天生就存在与一个 python 程序中，代表对应程序名称
# 比如所示的一段代码里面（这个脚本命名为 pcRequests.py），我只设了一个函数，但是并没有地方运行它，所以当 run 了这一段代码之后我们有会发现这个函数并没有被调用
# 但是当我们在运行这个代码时这个代码的 __name__ 的值为 __main__ （一段程序作为主线运行程序时其内置名称就是 __main__）

# 此处没有导入网络相关的包  所以报错  假设这个类在pcRequests.py文件中
# import requests
# class requests(object):
#     def __init__(self,url):
#         self.url=url
#         self.result=self.getHTMLText(self.url)
#     
#     def getHTMLText(url):
#         try:
#             r=requests.get(url,timeout=30)
#             r.raise_for_status()
#             r.encoding=r.apparent_encoding
#             return r.text
#         except:
#             return "This is a error."
# print(__name__)
# 结果：
# __main__
# Process finished with exit code 0
# 当这个 pcRequests.py 作为模块被调用时，则它的 __name__ 就是它自己的名字：
# import pcRequestspcRequestsc=pcRequestsc.__name__
# 结果：
# 'pcRequests'
# 看到这里应该能明白，自己的 __name__ 在自己用时就是 main，当自己作为模块被调用时就是自己的名字


# Python3 类方法总结
#  普通方法：对象访问
#  私有方法：两个下划线开头，只能在类内部访问
#  静态方法：类和对象访问，不能和其他方法重名，不然会相互覆盖，后面定义的会覆盖前面的
#  类方法：类和对象访问，不能和其他方法重名，不然会相互覆盖，后面定义的会覆盖前面的
#  多继承情况下：从左到右查找方法，找到为止，不然就抛出异常
# 继承
# 单继承:
class Student(people):
    grade=''
    def __init__(self,n,a,w,g):
        people.__init__(self,n,a,w)
        self.grade = g

    # 覆写父类方法
    def speak(self):
        print("%s 说: 我 %d 岁了，我在读 %d 年级"%(self.name,self.age,self.grade))

class Speak():
    topic=''
    name=''
    def __init__(self,n,t):
        self.name = n
        self.topic = t
    # 普通方法，对象调用
    def speak(self):
        print("我叫 %s，我是一个演说家，我演讲的主题是 %s"%(self.name,self.topic))

    # 私有方法，self调用
    def __song(self):
        print('唱一首歌自己听',self)

    # 静态方法，对象和类调用，不能和其他方法重名，不然会相互覆盖，后面定义的会覆盖前面的
    @staticmethod
    def song():
        print('唱一首歌给类听:静态方法')

    # 普通方法，对象调用
    def song1(self):
        print('唱一首歌给你们听',self)
        
    # 类方法，对象和类调用，不能和其他方法重名，不然会相互覆盖，后面定义的会覆盖前面的
    @classmethod
    def song2(self):
        print('唱一首歌给类听:类方法',self)

class Sample(Speak,Student):
    a = ''
    def __init__(self,n,a,w,g,t):
        Student.__init__(self,n,a,w,g)
        Speak.__init__(self,n,t)

test = Sample('Song',24,56,7,'Python')
test.speak() # 我叫 Song，我是一个演说家，我演讲的主题是 Python
test.song1() # 唱一首歌给你们听 <__main__.Sample object at 0x000002B725859E48>
Sample.song() # 唱一首歌给类听:静态方法
Sample.song2() #  一首歌给类听:类方法 <class '__main__.Sample'>
test.song() # 唱一首歌给类听:静态方法


# 所有专有方法中，__init__()要求无返回值，或者返回 None。而其他方法，如__str__()、__add__()等，一般都是要返回值的，如下所示：
# class Complex:
#     def __init__(self, realpart, imagpart):
#         self.r = realpart
#         self.i = imagpart
#         return 'hello'

# x = Complex(3.0, -4.5)  报错 TypeError: __init__() should return None, not 'str'
# 而对于 __str__()、__add__() 等 一般都要返回
# def __str__(self):
#         return 'Vector (%d, %d)' % (self.a, self.b)

# def __repr__(self):
#     return 'Vector (%d, %d)' % (self.a, self.b)

# def __add__(self,other):
#     if other.__class__ is Vector:
#         return Vector(self.a + other.a, self.b + other.b)
#     elif other.__class__ is int:
#         return Vector(self.a+other,self.b)

# 类的专有方法中，也是存在默认优先级的，多个方法都有返回值，但一般优先取 __str__() 的返回值，如下面例子：
class Vector2:
    def __init__(self, a, b):
        self.a = a
        self.b = b
    def __repr__(self):
        return 'Vector2 (%d, %d)' % (self.b, self.a)
    def __str__(self):
        return 'Vector2 (%d, %d)' % (self.a, self.b)
    def __add__(self,other):
        return Vector2(self.a + other.a, self.b + other.b)

v1 = Vector2(2,10)
print (v1)  # Vector2 (2, 10)
# 结果是 Vector2(2,10)，而不是 Vector2(10,2)。这里优先使用 __str__() 的返回值
print(v1.__repr__()) # Vector2 (10, 2)

# 在 Python 中，方法分为三类实例方法、类方法、静态方法。三者区别看代码如下：
class Test1(object):
    def InstanceFun(self):
        print("InstanceFun")
        print(self)
    @classmethod
    def ClassFun(cls):
        print("ClassFun")
        print(cls)
    @staticmethod
    def StaticFun():
        print("StaticFun")

t = Test1()
t.InstanceFun()     # 输出InstanceFun，打印对象内存地址“<__main__.Test1 object at 0x00000192F93F0848>”
Test1.ClassFun()    # 输出ClassFun，打印类位置 <class '__main__.Test1'>
Test1.StaticFun()    # 输出StaticFun
t.StaticFun()       # 输出StaticFun
t.ClassFun()        # 输出ClassFun，打印类位置 <class '__main__.Test1'>
# Test1.InstanceFun() # 错误，TypeError: unbound method instanceFun() must be called with Test instance as first argument
Test1.InstanceFun(t)    # 输出InstanceFun，打印对象内存地址“<__main__.Test1 object at 0x00000192F93F0848>”
# t.ClassFun(Test)    # 错误   classFun() takes exactly 1 argument (2 given)   
# 可以看到，在 Python 中，两种方法的主要区别在于参数。实例方法隐含的参数为类实例 self，而类方法隐含的参数为类本身 cls
# 静态方法无隐含参数，主要为了类实例也可以直接调用静态方法
# 所以逻辑上类方法应当只被类调用，实例方法实例调用，静态方法两者都能调用。主要区别在于参数传递上的区别，实例方法悄悄传递的是self引用作为参数，而类方法悄悄传递的是 cls 引用作为参数
# Python 实现了一定的灵活性使得类方法和静态方法，都能够被实例和类二者调用


# 类的二元方法运算符重载介绍的并不全面，文中介绍的全是正向方法，其实还有反向方法，就地方法。下面补充一些
# 当解释器碰到 a+b 时，会做以下事情：
# 从 a 类中找 __add__ 若返回值不是 NotImplemented, 则调用 a.__add__(b)
# 若 a 类中没有 __add__ 方法，则检查 b 有没有 __radd__ 。如果如果有，则调用 b.__radd__(a)，若没有，则返回 NotImplemented
# 接上条，若 b 也没有 __radd__ 方法，则抛出 TypeError，在错误消息中知名操作数类型不支持
# 比如：向量类 <Myvector> 应当有向量与整数的乘法:
# >>>a = Myvector([1,2,3])
# >>>print(a.value)
# [1,2,3]
# >>>b=3
# >>>c = a*b   #此时调用Myvector.__mul__()
# >>>print(c.value)
# [3,6,9]
# >>> d=b*a  #这句会出错
# 期望得到 b*a 也返回一个向量，b*a 应该等于 a*b。此时就需要在 Myvector 类中定义一个__rmul__方法

# def __rmul__(self, other):
#     if isinstance(other, int):
#         return Myvector([a*other for a in self.m])
# 每个运算符都有正向方法重载，反向方法重载。有一些有就地方法（即不返回新的对象，而是修改原本对象）


# __str__函数
# __str__ 是一个类的方法，在打印类对象，获取其属性信息时调用。打印一个实例化对象时，默认打印的其实时一个对象的地址，但是我们可以对其进行重载，打印我们想要的信息
# 例如上面的例子中进行的重载





























