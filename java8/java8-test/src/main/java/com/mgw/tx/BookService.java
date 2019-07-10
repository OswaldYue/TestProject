package com.mgw.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired BookDao bookDao;

    @Transactional(rollbackFor = Exception.class)
    public void checkout(String username,String isbn) {

        bookDao.updateStock(isbn);
        int price = bookDao.getPrice(isbn);
        bookDao.updateBalance(username,price);

        int i = 10 / 0;
    }


    /**
     * 事务细节:
     * readOnly  只读
     * noRollbackFor  那些异常可以不回滚
     * rollbackForClassName
     * isolation 事务的隔离级别
     * propagation 事务的传播级别
     * timeout 超时(单位为秒)  事务超出指定执行时长后自动回滚
     *
     * 事务的隔离级别:
     * 脏读(事务1修改数据库值为20,事务2读取数据为20,此时事务1回滚到原来的30,则事务2读取的数据无效),
     * 不可重复读(同一事物中,因为其他线程操作数据库,前后两次读取的结果不一致),
     * 幻读(事务1读取数据库,事务2则添加数据,事务1再次读时,多了几条数据)
     *
     * 隔离级别就是为了解决上面3件事
     *
     * 隔离级别有4种:
     * 1.读未提交(允许事务1读取事务2未提交的修改)
     * 2.读已提交(要去事务1只能读事务2已提交的修改)
     * 3.可重复读(确保事务1可以多次从一个字段中读取到相同的值,即事务1执行期间禁止其他事务对这个字段进行更新)
     * 4.串行化()
     * */
    @Transactional(rollbackFor = Exception.class)
    public void checkout2(String username,String isbn) {

        bookDao.updateStock(isbn);
        int price = bookDao.getPrice(isbn);
        bookDao.updateBalance(username,price);

        int i = 10 / 0;
    }

}
