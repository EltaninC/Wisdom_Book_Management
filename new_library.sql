--
-- DATABASE: 'library'
--

-- ----------------------------------------------------

--
-- 表的结构 'User'
--

CREATE TABLE `user`
(
    `user_id`           int(10) NOT NULL AUTO_INCREMENT,
    `user_name`         varchar(10) NOT NULL,
    `password`          char(32)    NOT NULL,
    `email`             varchar(20) NOT NULL,
    `phone`             char(11)    NOT NULL,
    `role_id`           int(10) NOT NULL,
    `real_name`         varchar(10) NOT NULL,
    `borrow_limit`      int(10) NOT NULL,
    `borrowed_quantity` int(10) NOT NULL,
    `deposit`           float(10)   NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (role_id) REFERENCES Role (role_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'Role'
--

CREATE TABLE `Role`
(
    `role_id`   int(10) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(10) NOT NULL,
    PRIMARY KEY (role_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'book'
--

CREATE TABLE `book`
(
    `book_id`           int(10) NOT NULL,
    `type_id`           int(10) NOT NULL,
    `book_name`         varchar(10) NOT NULL,
    `writer`            varchar(15) NOT NULL,
    `publication`       varchar(10) NOT NULL,
    `publication_date`  date        NOT NULL,
    `language`          varchar(10) NOT NULL,
    `single_book_state` varchar(10) NOT NULL,
    `total`             int(10) NOT NULL,
    `shelf_code`        varchar(20) NOT NULL,
    PRIMARY KEY (book_id),
    FOREIGN KEY (type_id) REFERENCES book_type (type_id),
    FOREIGN KEY (shelf_code) REFERENCES shelf (shelf_code)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO `book`(`book_id`, `type_id`, `book_name`, `writer`, `publication`, `publication_date`, `language`,
                   `single_book_state`, `total`,`shelf_code`)
values (1, 2, 'SpringBoot', 'xxx', 'xxx_xxx', '2001/05/28', 'Chinese', '普通外借', 50, 'A001'),
       (2, 2, 'Spring实战', 'xxx', 'xxx_xxx', '2001/05/28', 'English', '普通外借', 10, 'B002'),
       (3, 2, 'Java并发', 'xxx', 'xxx_xxx', '2001/05/28', 'Spanish', '普通外借', 12, 'B002');

--
-- 表的结构 'parent_book'
--

CREATE TABLE `parent_book`
(
    `parent_id`   int(10) NOT NULL,
    `parent_name` varchar(10) NOT NULL,
    PRIMARY KEY (pid)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

Insert INTO `parent_book`(parent_id, parent_name)
VALUES (1, '小说'),
       (2, '教育'),
       (2, '科技'),
       (2, '人文社科');



--
-- 表的结构 'book_type'
--

CREATE TABLE `book_type`
(
    `type_id`   int(10) NOT NULL,
    `type_name` varchar(10) NOT NULL,
    `parent_id` int(10) NOT NULL,
    PRIMARY KEY (tid),
    FOREIGN KEY (parent_id) REFERENCES parent_book (parent_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

Insert INTO book_type(type_id, type_name, parent_id)
VALUES (1, '侦探/悬疑/推理', 1),
       (2, '魔幻', 1),
       (3, '科幻', 1),
       (4, '武侠', 1),
       (5, '文学类', 2),
       (6, '理学类', 2),
       (7, '医学类', 2);


--
-- 表的结构 'appointment'
--

CREATE TABLE `appointment`
(
    `appointment_id`   int(10) NOT NULL,AUTO_INCREMENT,
    `user_id`          int(10) NOT NULL,
    `book_id`          int(10) NOT NULL,
    `appointment_data` date NOT NULL,
    PRIMARY KEY (appointment_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (book_id) REFERENCES book (book_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;


--
-- 表的结构 'borrow'
--

CREATE TABLE `borrow`
(
    `borrow_id`          int(10) NOT NULL AUTO_INCREMENT,
    `user_id`            int(10) NOT NULL,
    `book_code`          varchar(20) NOT NULL,
    `borrow_date`        datetime    NOT NULL,
    `expect_return_date` datetime    NOT NULL,
    `renew`              int(1) NOT NULL,
    PRIMARY KEY (borrow_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (book_code) REFERENCES a_book (book_code)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'back'
--

CREATE TABLE `back`
(
    `back_id`     int(10) NOT NULL AUTO_INCREMENT,
    `user_id`     int(10) NOT NULL,
    `book_code`   varchar(20) NOT NULL,
    `borrow_date` datetime    NOT NULL,
    `return_date` datetime    NOT NULL,
    `over_date`   varchar(15) NOT NULL,
    PRIMARY KEY (back_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (book_code) REFERENCES a_book (book_code)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'a_book'
--

CREATE TABLE `a_book`
(
    `book_id`    int(10) NOT NULL,
    `book_code`  varchar(20) NOT NULL,
    PRIMARY KEY (book_code),
    FOREIGN KEY (book_id) REFERENCES book (book_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO a_book(book_id, book_code) VALUES
(1,'AB01'),(1,'AB02'),(1,'AB03'),(1,'AB04'),(1,'AB05'),(1,'AB06'),(1,'AB07'),(1,'AB08'),(1,'AB09'),
(1,'AB10'),(1,'AB11'),(1,'AB12'),(1,'AB13'),(1,'AB14'),(1,'AB15'),(1,'AB16'),(1,'AB17'),(1,'AB18'),
(1,'AB19'),(1,'AB20'),(1,'AB21'),(1,'AB22'),(1,'AB23'),(1,'AB24'),(1,'AB25'),(1,'AB26'),(1,'AB27'),
(1,'AB28'),(1,'AB29'),(1,'AB30'),(1,'AB31'),(1,'AB32'),(1,'AB33'),(1,'AB34'),(1,'AB35'),(1,'AB36'),
(1,'AB37'),(1,'AB38'),(1,'AB39'),(1,'AB40'),(1,'AB41'),(1,'AB42'),(1,'AB43'),(1,'AB44'),(1,'AB45'),
(1,'AB46'),(1,'AB47'),(1,'AB48'),(1,'AB49'),(1,'AB50'),(2,'AC01'),(2,'AC02'),(2,'AC03'),(2,'AC04'),
(2,'AC05'),(2,'AC06'),(2,'AC07'),(2,'AC08'),(2,'AC09'),(3,'AD01'),(3,'AD02'),(3,'AD03'),(3,'AD04'),
(3,'AD05'),(3,'AD06'),(3,'AD07'),(3,'AD08'),(3,'AD09'),(3,'AD10'),(3,'AD11')

--
-- 表的结构 'shelf'
--

CREATE TABLE `shelf`
(
    `shelf_code` varchar(20) NOT NULL,
    `location`   varchar(20) NOT NULL,
    PRIMARY KEY (shelf_code)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO shelf (shelf_code, location) VALUES ('A001', 'North Wall');
INSERT INTO shelf (shelf_code, location) VALUES ('B002', 'West Corner');
INSERT INTO shelf (shelf_code, location) VALUES ('C003', 'East Wall');
INSERT INTO shelf (shelf_code, location) VALUES ('D004', 'South Wall');
INSERT INTO shelf (shelf_code, location) VALUES ('E005', 'Northeast Corner');
INSERT INTO shelf (shelf_code, location) VALUES ('F006', 'Southwest Corner');
INSERT INTO shelf (shelf_code, location) VALUES ('G007', 'Center Aisle');
INSERT INTO shelf (shelf_code, location) VALUES ('H008', 'Back Wall');
INSERT INTO shelf (shelf_code, location) VALUES ('I009', 'Front Entrance');
INSERT INTO shelf (shelf_code, location) VALUES ('J010', 'Near Checkout');
INSERT INTO shelf (shelf_code, location) VALUES ('K011', 'Middle Aisle');
INSERT INTO shelf (shelf_code, location) VALUES ('L012', 'Endcap');
INSERT INTO shelf (shelf_code, location) VALUES ('M013', 'Top Shelf');
INSERT INTO shelf (shelf_code, location) VALUES ('N014', 'Bottom Shelf');
INSERT INTO shelf (shelf_code, location) VALUES ('O015', 'Left Wall');
INSERT INTO shelf (shelf_code, location) VALUES ('P016', 'Right Wall');
INSERT INTO shelf (shelf_code, location) VALUES ('Q017', 'Near Restrooms');
INSERT INTO shelf (shelf_code, location) VALUES ('R018', 'Far Corner');
INSERT INTO shelf (shelf_code, location) VALUES ('S019', 'Adjacent to Exit');
INSERT INTO shelf (shelf_code, location) VALUES ('T020', 'On Display');

--
-- 表的结构 'ticket'
--

CREATE TABLE `ticket`
(
    `ticket_id`          int(10) NOT NULL,AUTO_INCREMENT,
    `user_id`            int(10) NOT NULL,
    `book_code`          varchar(20) NOT NULL,
    `ticket_type`        varchar(10) NOT NULL,
    `ticket_description` varchar(30) NOT NULL,
    `ticket_money`       float(10)   NOT NULL,
    PRIMARY KEY (ticket_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (book_code) REFERENCES a_book (book_code)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

--
-- 表的结构 'Admin'
--

CREATE TABLE `admin`
(
    `admin_id`  int(10) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(10) NOT NULL,
    `pass_word` char(32)    NOT NULL,
    PRIMARY KEY (admin_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO `admin`
values (1, '陈某', 123456);

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

--
-- 表的结构 'refund'
--

CREATE TABLE `refund` (
      `refund_id` int(10) NOT NULL AUTO_INCREMENT,
      `recharge_id` int(10) NOT NULL,
      `user_id` int(10) NOT NULL,
      `refund_time` datetime NOT NULL ,
      `money` decimal NOT NULL ,
      PRIMARY KEY (refund_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO `refund` values (1,1,1,'2023-09-18 15:48:07',100.00);

--
-- 表的结构 'appointment'
--

CREATE TABLE `appointment` (
      `appointment_id` int(10) NOT NULL AUTO_INCREMENT,
      `book_id` int(10) NOT NULL,
      `user_id` int(10) NOT NULL,
      `start_date` datetime NOT NULL ,
      `end_date` datetime NOT NULL ,
      `state` char(10) NOT NULL ,
      PRIMARY KEY (appointment_id)
)ENGINE = InnoDb DEFAULT CHAR SET = utf8;

INSERT INTO `appointment` values (1,1,1,'2023-09-18 15:48:07','2023-09-20 15:48:07','待取书');