--
-- DATABASE: 'library'
--

-- ----------------------------------------------------

--
-- 表的结构 'User'
--

CREATE TABLE `User` (
    `uid` int(10) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(10) NOT NULL ,
    `pass_word` char(32) NOT NULL ,
    `email` varchar(20) NOT NULL ,
    `phone` char(11) NOT NULL ,
    `rid` bigint(10) NOT NULL ,
    `real_name` varchar(10) NOT NULL ,
    `can_borrow` int(1) NOT NULL ,
PRIMARY KEY (uid)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'book'
--

CREATE TABLE `book`(
    `bid` int(10) NOT NULL ,
    `tid` int(10) NOT NULL ,
    `book_name` varchar(10) NOT NULL ,
    `writer` varchar(10) NOT NULL ,
    `publication` varchar(10) NOT NULL ,
    `publication_date` date NOT NULL ,
    `total` int(10) NOT NULL ,
    `location` varchar(10) NOT NULL ,
    PRIMARY KEY (bid)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO `book`(`bid`, `tid`, `book_name`, `writer`, `publication`, `publication_date`, `total`, `location`) values
(1,2,'SpringBoot','xxx','xxx_xxx','2001/05/28',50,'5-6-12'),
(2,2,'Spring实战','xxx','xxx_xxx','2001/05/28',50,'5-6-12'),
(3,2,'Java并发','xxx','xxx_xxx','2001/05/28',50,'5-6-12');


--
-- 表的结构 'parent_book'
--

CREATE TABLE `parent_book`(
    `pid` int(10) NOT NULL ,
    `parent_name` varchar(10) NOT NULL ,
    PRIMARY KEY (pid)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

Insert INTO  `parent_book`(pid,parent_name)
VALUES (1,'小说'),
       (2,'教育'),
       (2,'科技'),
       (2,'人文社科');



--
-- 表的结构 'book_type'
--

CREATE TABLE `book_type`(
    `tid` int(10) NOT NULL ,
    `type_name` varchar(10) NOT NULL ,
    `pid` int(10) NOT NULL,
    PRIMARY KEY (tid),
    FOREIGN KEY (pid) REFERENCES parent_book(pid)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

Insert INTO  book_type(tid,type_name,pid)
VALUES (1,'侦探/悬疑/推理',1),
       (2,'魔幻',1),
       (3,'科幻',1),
       (4,'武侠',1),
       (5,'文学类',2),
       (6,'理学类',2),
       (7,'医学类',2);


--
-- 表的结构 'appointment'
--

CREATE TABLE `appointment`(
    `id` int(10) NOT NULL ,
    `uid` int(10) NOT NULL ,
    `bid` int(10) NOT NULL ,
    `appointment_data` datetime NOT NULL ,
    PRIMARY KEY (id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;


--
-- 表的结构 'borrow'
--

CREATE TABLE `borrow`(
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `uid` int(10) NOT NULL ,
    `bid` int(10) NOT NULL ,
    `borrow_date` datetime NOT NULL ,
    `expect_return_date` datetime NOT NULL ,
    `renew` int(1) NOT NULL ,
    PRIMARY KEY (id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'back'
--

CREATE TABLE `back`(
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `uid` int(10) NOT NULL ,
    `bid` int(10) NOT NULL ,
    `borrow_date` datetime NOT NULL ,
    `return_date` datetime NOT NULL ,
    PRIMARY KEY (id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'ticket'
--

CREATE TABLE `ticket`(
    `id` bigint(10) NOT NULL ,
    `uid` bigint(10) NOT NULL ,
    `bid` bigint(10) NOT NULL ,
    `over_date` int(10) NOT NULL ,
    `ticket_money` float(10,1) NOT NULL ,
    PRIMARY KEY (id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'Admin'
--

CREATE TABLE `admin` (
    `admin_id` int(10) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(10) NOT NULL ,
    `pass_word` char(32) NOT NULL ,
    PRIMARY KEY (admin_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO `admin` values (1,'陈某',123456);


--
-- 表的结构 'recharge'
--

CREATE TABLE `recharge` (
    `recharge_id` int(10) NOT NULL AUTO_INCREMENT,
    `user_id` int(10) NOT NULL,
    `recharge_time` datetime NOT NULL ,
    `money` decimal NOT NULL ,
    PRIMARY KEY (recharge_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO `recharge` values (1,1,'2023-09-18 15:48:07',100.00);