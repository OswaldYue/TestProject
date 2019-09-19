#! /user/bin/python
# -*- coding:utf-8 -*-


# Python3 File(文件) 方法
# open() 方法
# Python open() 方法用于打开一个文件，并返回文件对象，在对文件进行处理过程都需要使用到这个函数，如果该文件无法被打开，会抛出 OSError
# 注意：使用 open() 方法一定要保证关闭文件对象，即调用 close() 方法
# open() 函数常用形式是接收两个参数：文件名(file)和模式(mode)
# open(file, mode='r')
# 完整的语法格式为：
# open(file, mode='r', buffering=-1, encoding=None, errors=None, newline=None, closefd=True, opener=None)
# 参数说明:
# file: 必需，文件路径（相对或者绝对路径）。
# mode: 可选，文件打开模式
# buffering: 设置缓冲
# encoding: 一般使用utf8
# errors: 报错级别
# newline: 区分换行符
# closefd: 传入的file参数类型
# opener:
# mode 参数有：
# 模式	    描述
# t	        文本模式 (默认)
# x	        写模式，新建一个文件，如果该文件已存在则会报错
# b	        二进制模式
# +	        打开一个文件进行更新(可读可写)
# U	        通用换行模式（Python 3 不支持）
# r	        以只读方式打开文件。文件的指针将会放在文件的开头。这是默认模式
# rb	    以二进制格式打开一个文件用于只读。文件指针将会放在文件的开头。这是默认模式。一般用于非文本文件如图片等
# r+	    打开一个文件用于读写。文件指针将会放在文件的开头
# rb+	    以二进制格式打开一个文件用于读写。文件指针将会放在文件的开头。一般用于非文本文件如图片等
# w	        打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件
# wb	    以二进制格式打开一个文件只用于写入。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。一般用于非文本文件如图片等
# w+	    打开一个文件用于读写。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件
# wb+ 	    以二进制格式打开一个文件用于读写。如果该文件已存在则打开文件，并从开头开始编辑，即原有内容会被删除。如果该文件不存在，创建新文件。一般用于非文本文件如图片等
# a	        打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。也就是说，新的内容将会被写入到已有内容之后。如果该文件不存在，创建新文件进行写入
# ab	    以二进制格式打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。也就是说，新的内容将会被写入到已有内容之后。如果该文件不存在，创建新文件进行写入
# a+	    打开一个文件用于读写。如果该文件已存在，文件指针将会放在文件的结尾。文件打开时会是追加模式。如果该文件不存在，创建新文件用于读写
# ab+	    以二进制格式打开一个文件用于追加。如果该文件已存在，文件指针将会放在文件的结尾。如果该文件不存在，创建新文件用于读写
# 默认为文本模式，如果要以二进制模式打开，加上 b 



# file 对象
# file 对象使用 open 函数来创建，下表列出了 file 对象常用的函数：
# 序号	方法及描述
# 1	    file.close() 关闭文件。关闭后文件不能再进行读写操作
# 2	    file.flush() 刷新文件内部缓冲，直接把内部缓冲区的数据立刻写入文件, 而不是被动的等待输出缓冲区写入
# 3	    file.fileno() 返回一个整型的文件描述符(file descriptor FD 整型), 可以用在如os模块的read方法等一些底层操作上
# 4	    file.isatty() 如果文件连接到一个终端设备返回 True，否则返回 False
# 5	    file.next() Python 3 中的 File 对象不支持 next() 方法返回文件下一行
# 6	    file.read([size]) 从文件读取指定的字节数，如果未给定或为负则读取所有
# 7	    file.readline([size]) 读取整行，包括 "\n" 字符
# 8	    file.readlines([sizeint]) 读取所有行并返回列表，若给定sizeint>0，返回总和大约为sizeint字节的行, 实际读取值可能比 sizeint 较大, 因为需要填充缓冲区
# 9	    file.seek(offset[, whence]) 设置文件当前位置
# 10	file.tell() 返回文件当前位置
# 11	file.truncate([size]) 从文件的首行首字符开始截断，截断文件为 size 个字符，无 size 表示从当前位置截断；截断之后后面的所有字符被删除，其中 Widnows 系统下的换行代表2个字符大小
# 12	file.write(str) 将字符串写入文件，返回的是写入的字符长度
# 13	file.writelines(sequence) 向文件写入一个序列字符串列表，如果需要换行则要自己加入每行的换行符

# 检索指定路径下后缀是 py 的所有文件：
import os
import os.path

#path = 'D:\\04.test-code\\python\\study-test01'
ls = []
def getAppointFile(path,ls):
    fileList = os.listdir(path)
    try:
        for tmp in fileList:
            pathTmp = os.path.join(path,tmp)
            if True==os.path.isdir(pathTmp):
                getAppointFile(pathTmp,ls)
            elif pathTmp[pathTmp.rfind('.')+1:].upper()=='PY':
                ls.append(pathTmp)
    except PermissionError:
        pass


def main():
    while True:
        path = "D:\\04.test-code\\python\\study-test01"
        # path = input('请输入路径:').strip()
        if os.path.isdir(path) == True:
            break
    getAppointFile(path,ls)
    #print(len(ls))
    print(ls)
    print(len(ls))


main()


# 获取文件后缀：
def getfile_fix(filename):
     return filename[filename.rfind('.')+1:]
print(getfile_fix('runoob.txt')) # txt


# 用户输入"xxx.txt"类文档文件名
# 用户输入被替换的"待替换字"
# 用户输入替换目标"新的字"
# 用户判断是否全部替换 yes/no
def file_replace(file_name, rep_word, new_word):
    file_name = "D:\\04.test-code\\python\\study-test01\\test.txt"
    f_read = open(file_name)

    content = []
    count = 0

    for eachline in f_read:
        if rep_word in eachline:
            count = count+eachline.count(rep_word)
            eachline = eachline.replace(rep_word, new_word)
        # 将替换后的一行数据存起来  内存极易撑爆
        content.append(eachline)    

    decide = input('\n文件 %s 中共有%s个【%s】\n您确定要把所有的【%s】替换为【%s】吗？\n【YES/NO】：' \
                   % (file_name, count, rep_word, rep_word, new_word))

    if decide in ['YES', 'Yes', 'yes']:
        f_write = open(file_name, 'w')
        f_write.writelines(content)
        f_write.close()

    f_read.close()


file_name = input('请输入文件名：')
rep_word = input('请输入需要替换的单词或字符：')
new_word = input('请输入新的单词或字符：')
file_replace(file_name, rep_word, new_word)


