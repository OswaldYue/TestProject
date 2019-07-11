DROP TABLE IF EXISTS `test_account` ;
CREATE TABLE `test_account` (
  `username` varchar(255) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `test_book`;
CREATE TABLE `test_book` (
  `isbn` varchar(255) NOT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `test_stock`;
CREATE TABLE `test_stock` (
  `isbn` varchar(255) NOT NULL,
  `stock` int(11) DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO test_account VALUES('Jerry',800),('Tom',10000);
INSERT INTO test_book VALUES('ISBN-01','book01',100),('ISBN-02','book02',200),('ISBN-03','book03',300),('ISBN-04','book04',400),('ISBN-05','book05',500);
INSERT INTO test_stock VALUES('ISBN-01',1000),('ISBN-02',2000),('ISBN-03',3000),('ISBN-04',4000),('ISBN-05',5000);


UPDATE test_account SET balance = 10000;
UPDATE test_book SET price = 100;
UPDATE test_stock SET stock = 1000;