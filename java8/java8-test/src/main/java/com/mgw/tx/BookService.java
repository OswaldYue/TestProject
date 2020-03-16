package com.mgw.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired BookDao bookDao;

    @Transactional(rollbackFor = Exception.class)
    public void checkout(String username,String isbn) {

//        bookDao.updateStock(isbn);
//        int price = bookDao.getPrice(isbn);
//        bookDao.updateBalance(username,price);
//
//        int i = 10 / 0;
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
     * 1.读未提交(允许事务1读取事务2未提交的修改,不可解决脏读,不可重复读,幻读)
     * 2.读已提交(要去事务1只能读事务2已提交的修改,可解决脏读,但不解决不可重复读,幻读)
     * 3.可重复读(确保事务1可以多次从一个字段中读取到相同的值,即事务1执行期间禁止其他事务对这个字段进行更新,可解决脏读,不可重复读,但不可解决幻读)
     * 4.串行化(确保事务1可以多次从一个表中读取到相同的行,在事务1执行期间,禁止其他事务对这个表进行添加,更新,删除等操作.可避免任何并发问题,单性能低下,不会用到,可解决脏读,不可重复读,幻读)
     *
     * 事务传播级别:
     * 传播行为(事务的传播+事务的行为)
     * 如果有多个事务进行嵌套运行,子事务是否要和大事务共用一个事务;
     *  AService {
     *      tx_a() {
     *          tx_b() {
     *
     *          }
     *          tx_c() {
     *
     *          }
     *      }
     *  }
     *  问题:若c事务出问题,b是否需要回滚?  答:可控  可回滚也可不回滚
     *
     * REQUIRED:如果有事务在运行,当前的方法就在这个事务内运行,否则,就启动一个新的事务,并在自己的事务内运行
     * REQUIRES_NEW:当前的方法必须启动新事务,并在自己的事务内运行,如果有事务正在运行,则将其挂起
     * SUPPORTS:如果有事务在运行,当前的方法就在这个事务内运行,否则它可以不运行在事务中
     * NOT_SUPPORTED:当前方法不应该运行在事务中,如果有运行的事务,将其挂起
     * MANDATORY:当前方法必须运行在事务内,如果没有事务就抛异常
     * NEVER:当前方法不应该运行在事务中,如果有运行的事务,就抛异常
     * NESTED:如果有事务在运行,当前的方法就应该在这个事务的嵌套事务内运行,否则,就启动一个新的事务,并在它自己的事务内运行
     *
     * REQUIRED:当前事务和之前的大事务公用一个事务
     * REQUIRES_NEW:当前事务总是使用一个新的事务,并挂起之前的事务
     *
     * mulTx() {
     *
     *      //REQUIRED
     *     a() {
     *         //REQUIRES_NEW
     *         b() {}
     *         //REQUIRED
     *         c() {}
     *     }
     *     //REQUIRES_NEW
     *     d() {
     *         //REQUIRED
     *         e() {
     *             //REQUIRES_NEW
     *             f() {}
     *         }
     *         //REQUIRES_NEW
     *         g() {}
     *     }
     *     10/0; //b,d整个分支以下全部成功
     * }
     *
     * *      mulTx() {
     *      *
     *      *      //REQUIRED
     *      *     a() {
     *      *         //REQUIRES_NEW
     *      *         b() {}
     *      *         //REQUIRED
     *      *         c() {}
     *      *     }
     *      *     //REQUIRES_NEW
     *      *     d() {
     *                 DDD(){}
     *      *         //REQUIRED
     *      *         e() {
     *      *             //REQUIRES_NEW
     *      *             f() {
     *                      10/0;(f,e,g(还未执行到)蹦,DDD蹦不蹦不确定得看是REQUIRES_NEW(不蹦)还是REQUIRED(蹦),a,c蹦,b不蹦)
     *                    }
     *      *         }
     *      *         //REQUIRES_NEW
     *      *         g() {}
     *      *     }
     *      * }
     *   任何处蹦,已经执行的REQUIRES_NEW不会蹦,如果没有try-catch会一层层向上抛异常
     *
     *   如果是REQUIRED,事务的属性都是继承大事务的,REQUIRES_NEW是可以调整的,默认REQUIRED
     *   REQUIRED:将之前事务用的connection传递给这个方法使用
     *   REQUIRES_NEW:直接使用新的connection
     *
     *
     * */
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRES_NEW)
    public void checkout2(String username,String isbn) {

        bookDao.updateStock(isbn);
        int price = bookDao.getPrice(isbn);

        bookDao.updateBalance(username,price);

    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void updatePrice(String isbn,int price) {

        bookDao.updatePrice(isbn,price);

//        int i = 10/0;
    }

    @Transactional(rollbackFor = Exception.class)
    public void mulTx() {

        checkout2("Tom","ISBN-01");

        updatePrice("ISBN-02",998);

        //虽然上面两个方法都是Propagation.REQUIRES_NEW,但是其实上面两个方法没有更新成功 why?因为事务回滚了
        int i = 10/0;

    }

}
