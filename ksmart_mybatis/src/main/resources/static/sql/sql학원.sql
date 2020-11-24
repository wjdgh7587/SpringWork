-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.62 - MySQL Community Server (GPL)
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- ksmart37db 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `ksmart37db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ksmart37db`;

-- 함수 ksmart37db.func_sum 구조 내보내기
DELIMITER //
CREATE DEFINER=`ksmart37id`@`%` FUNCTION `func_sum`(
	`intValue1` INT,
	`intValue2` INT
) RETURNS int(11)
    COMMENT '두수의 합을 반환하는 사용자 정의함수'
BEGIN
  DECLARE resultSum INT;

  SET resultSum = intValue1 + intValue2;

  RETURN resultSum;
END//
DELIMITER ;

-- 프로시저 ksmart37db.memberProc 구조 내보내기
DELIMITER //
CREATE DEFINER=`ksmart37id`@`%` PROCEDURE `memberProc`(
	IN `mName` VARCHAR(50)
)
BEGIN
	SELECT
		*
   FROM
		tb_member AS m
   WHERE
		m.m_name like concat('%', mName, '%');
END//
DELIMITER ;

-- 프로시저 ksmart37db.memberProc2 구조 내보내기
DELIMITER //
CREATE DEFINER=`ksmart37id`@`%` PROCEDURE `memberProc2`(
	IN `mId` VARCHAR(50),
	IN `mPw` VARCHAR(50),
	OUT `result1` VARCHAR(50),
	IN `result2` INT
)
BEGIN
	select
		m.m_email as '이메일' into result1		
	from
	tb_member as m
	WHERE
		m.m_id = mId && m.m_pw = mPw; 
	
	select
		m.m_name as '멤버이름'into result2		
	from
	tb_member as m
	WHERE
		m.m_id = mId && m.m_pw = mPw ;
END//
DELIMITER ;

-- 프로시저 ksmart37db.memberProcIf 구조 내보내기
DELIMITER //
CREATE DEFINER=`ksmart37id`@`%` PROCEDURE `memberProcIf`(
	IN `memberId` VARCHAR(50)


)
BEGIN
   DECLARE memberLevel INT;
   
   SET memberLevel = 0;

   SELECT
     m.m_level into memberLevel
   FROM
     tb_member as m
   WHERE
     m.m_id = memberId;
	
	case
   when (memberLevel = 1) THEN
       SELECT '관리자' AS '권한';
   when(memberLevel = 2) THEN
       SELECT '판매자' AS '권한';
   when(memberLevel = 3) THEN
       SELECT '구매자' AS '권한';
   ELSE SELECT '등록된 아이디가 없습니다.' AS '권한';   
   END case;
END//
DELIMITER ;

-- 테이블 ksmart37db.tb_goods 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_goods` (
  `g_code` varchar(50) NOT NULL,
  `g_name` varchar(50) NOT NULL,
  `g_price` int(11) NOT NULL,
  `g_seller_id` varchar(50) NOT NULL,
  `g_reg_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`g_code`),
  KEY `FK_g_seller_id` (`g_seller_id`),
  CONSTRAINT `FK_g_seller_id` FOREIGN KEY (`g_seller_id`) REFERENCES `tb_member` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_goods:~56 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_goods` DISABLE KEYS */;
INSERT INTO `tb_goods` (`g_code`, `g_name`, `g_price`, `g_seller_id`, `g_reg_date`) VALUES
	('g001', '모니터', 100000, 'id002', '2020-03-17'),
	('g002', '키보드', 100000, 'id002', '2020-03-17'),
	('g003', '마우스', 50000, 'id006', '2020-01-31'),
	('g004', 'SSD', 200000, 'id007', '2020-03-16'),
	('g005', 'USB', 5000, 'id010', '2020-04-15'),
	('g006', 'CPU', 400000, 'id012', '2020-05-02'),
	('g007', '스피커', 500000, 'id006', '2020-03-17'),
	('g008', '스캐너', 100000, 'id007', '2020-02-10'),
	('g009', '프린터', 80000, 'id006', '2020-03-01'),
	('g010', 'RAM', 130000, 'id012', '2020-03-07'),
	('g011', '그래픽카드', 600000, 'id006', '2020-03-07'),
	('g012', '키보드', 50000, 'id007', '2020-05-09'),
	('g013', '마우스', 30000, 'id012', '2020-06-17'),
	('g014', 'SSD', 170000, 'id007', '2020-07-20'),
	('g015', 'USB', 8000, 'id012', '2020-08-14'),
	('g016', 'CPU', 600000, 'id006', '2020-03-13'),
	('g017', '스피커', 20000, 'id007', '2020-09-12'),
	('g018', '스캐너', 200000, 'id006', '2020-03-11'),
	('g019', '프린터', 50000, 'id012', '2020-10-10'),
	('g020', 'RAM', 80000, 'id006', '2020-10-19'),
	('g021', '그래픽카드', 770000, 'id012', '2020-12-07'),
	('g022', '키보드', 130000, 'id007', '2020-11-06'),
	('g023', '마우스', 70000, 'id010', '2020-06-02'),
	('g024', 'SSD', 800000, 'id012', '2020-06-01'),
	('g025', 'USB', 100000, 'id010', '2020-05-09'),
	('g026', 'CPU', 700000, 'id006', '2020-06-08'),
	('g027', '스피커', 40000, 'id010', '2020-07-07'),
	('g028', '스캐너', 50000, 'id012', '2020-08-26'),
	('g029', '프린터', 30000, 'id012', '2020-09-23'),
	('g030', 'RAM', 90000, 'id002', '2020-04-06'),
	('g031', '그래픽카드', 440000, 'id012', '2020-04-27'),
	('g032', '키보드', 60000, 'id006', '2020-05-26'),
	('g033', '그래픽카드', 70000, 'id007', '2020-06-17'),
	('g034', '키보드', 135000, 'id012', '2020-05-19'),
	('g035', '그래픽카드', 120000, 'id012', '2020-05-18'),
	('g036', '마우스', 90000, 'id002', '2020-05-01'),
	('g037', '키보드', 10000, 'id010', '2020-03-30'),
	('g038', 'USB', 6000, 'id002', '2020-02-12'),
	('g039', '키보드', 55000, 'id006', '2020-02-17'),
	('g040', 'USB', 13000, 'id002', '2020-01-14'),
	('g041', '키보드', 160000, 'id006', '2020-01-27'),
	('g042', '스캐너', 190000, 'id010', '2020-01-07'),
	('g043', 'USB', 10000, 'id002', '2020-11-07'),
	('g044', '키보드', 13000, 'id010', '2020-12-27'),
	('g045', 'USB', 123000, 'id002', '2020-12-19'),
	('g046', '키보드', 19000, 'id006', '2020-10-10'),
	('g047', 'USB', 148000, 'id002', '2020-10-01'),
	('g048', '키보드', 340000, 'id012', '2020-10-05'),
	('g049', '키보드', 410000, 'id012', '2020-10-09'),
	('g050', 'CPU', 200000, 'id007', '2020-09-13'),
	('g051', '키보드', 60000, 'id007', '2020-09-27'),
	('g052', 'SSD', 800000, 'id010', '2020-04-28'),
	('g053', '프린터', 100000, 'id012', '2020-05-29'),
	('g054', 'SSD', 125000, 'id007', '2020-06-30'),
	('g055', '키보드', 34000, 'id007', '2020-09-17'),
	('g056', '마우스', 28000, 'id002', '2020-02-21');
/*!40000 ALTER TABLE `tb_goods` ENABLE KEYS */;

-- 테이블 ksmart37db.tb_login 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_login` (
  `login_num` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(200) NOT NULL,
  `login_date` date NOT NULL,
  `logout_date` date NOT NULL,
  PRIMARY KEY (`login_num`),
  KEY `FK_login_id` (`login_id`),
  CONSTRAINT `FK_login_id` FOREIGN KEY (`login_id`) REFERENCES `tb_member` (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_login:~98 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_login` DISABLE KEYS */;
INSERT INTO `tb_login` (`login_num`, `login_id`, `login_date`, `logout_date`) VALUES
	(1, 'id001', '2020-03-17', '2020-03-17'),
	(2, 'id002', '2020-03-17', '2020-03-17'),
	(3, 'id001', '2020-02-17', '2020-02-17'),
	(4, 'id001', '2020-02-18', '2020-02-18'),
	(5, 'id001', '2020-02-19', '2020-02-19'),
	(6, 'id001', '2020-02-20', '2020-02-20'),
	(7, 'id001', '2020-02-21', '2020-02-21'),
	(8, 'id001', '2020-02-22', '2020-02-22'),
	(9, 'id001', '2020-02-23', '2020-02-23'),
	(10, 'id001', '2020-02-24', '2020-02-24'),
	(11, 'id001', '2020-02-25', '2020-02-25'),
	(12, 'id001', '2020-02-26', '2020-02-26'),
	(13, 'id001', '2020-02-27', '2020-02-27'),
	(14, 'id001', '2020-02-28', '2020-02-28'),
	(15, 'id001', '2020-02-29', '2020-02-29'),
	(16, 'id001', '2020-03-01', '2020-03-01'),
	(17, 'id001', '2020-03-02', '2020-03-02'),
	(18, 'id001', '2020-03-03', '2020-03-03'),
	(19, 'id001', '2020-03-04', '2020-03-04'),
	(20, 'id001', '2020-03-05', '2020-03-05'),
	(21, 'id001', '2020-03-06', '2020-03-06'),
	(22, 'id001', '2020-03-07', '2020-03-07'),
	(23, 'id001', '2020-03-08', '2020-03-08'),
	(24, 'id001', '2020-03-09', '2020-03-09'),
	(25, 'id001', '2020-03-10', '2020-03-10'),
	(26, 'id001', '2020-03-11', '2020-03-11'),
	(27, 'id001', '2020-03-12', '2020-03-12'),
	(28, 'id001', '2020-03-13', '2020-03-13'),
	(29, 'id001', '2020-03-14', '2020-03-14'),
	(30, 'id001', '2020-03-15', '2020-03-15'),
	(31, 'id001', '2020-03-16', '2020-03-16'),
	(32, 'id001', '2020-03-17', '2020-03-17'),
	(33, 'id001', '2020-03-18', '2020-03-18'),
	(34, 'id001', '2020-03-19', '2020-03-19'),
	(35, 'id001', '2020-03-20', '2020-03-20'),
	(36, 'id001', '2020-03-21', '2020-03-21'),
	(37, 'id001', '2020-03-22', '2020-03-22'),
	(38, 'id001', '2020-03-23', '2020-03-23'),
	(39, 'id001', '2020-03-24', '2020-03-24'),
	(40, 'id001', '2020-03-25', '2020-03-25'),
	(41, 'id001', '2020-03-26', '2020-03-26'),
	(42, 'id001', '2020-03-27', '2020-03-27'),
	(43, 'id001', '2020-03-28', '2020-03-28'),
	(44, 'id001', '2020-03-29', '2020-03-29'),
	(45, 'id001', '2020-03-30', '2020-03-30'),
	(46, 'id001', '2020-03-31', '2020-03-31'),
	(47, 'id001', '2020-04-01', '2020-04-01'),
	(48, 'id001', '2020-04-02', '2020-04-02'),
	(49, 'id001', '2020-04-03', '2020-04-03'),
	(50, 'id001', '2020-04-04', '2020-04-04'),
	(51, 'id001', '2020-04-05', '2020-04-05'),
	(52, 'id001', '2020-04-06', '2020-04-06'),
	(53, 'id001', '2020-04-07', '2020-04-07'),
	(54, 'id001', '2020-04-08', '2020-04-08'),
	(55, 'id001', '2020-04-09', '2020-04-09'),
	(56, 'id001', '2020-04-10', '2020-04-10'),
	(57, 'id001', '2020-04-11', '2020-04-11'),
	(58, 'id001', '2020-04-12', '2020-04-12'),
	(59, 'id001', '2020-04-13', '2020-04-13'),
	(60, 'id001', '2020-04-14', '2020-04-14'),
	(61, 'id001', '2020-04-15', '2020-04-15'),
	(62, 'id001', '2020-04-16', '2020-04-16'),
	(63, 'id003', '2020-02-18', '2020-02-18'),
	(64, 'id002', '2020-02-19', '2020-02-19'),
	(65, 'id005', '2020-02-21', '2020-02-21'),
	(66, 'id002', '2020-02-23', '2020-02-23'),
	(67, 'id003', '2020-02-24', '2020-02-24'),
	(68, 'id004', '2020-02-25', '2020-02-25'),
	(69, 'id005', '2020-02-26', '2020-02-26'),
	(70, 'id006', '2020-02-27', '2020-02-27'),
	(71, 'id007', '2020-03-02', '2020-03-02'),
	(72, 'id008', '2020-03-02', '2020-03-02'),
	(73, 'id014', '2020-03-03', '2020-03-03'),
	(75, 'id003', '2020-03-05', '2020-03-05'),
	(76, 'id004', '2020-03-06', '2020-03-06'),
	(77, 'id005', '2020-03-07', '2020-03-07'),
	(78, 'id006', '2020-03-08', '2020-03-08'),
	(79, 'id007', '2020-03-09', '2020-03-09'),
	(80, 'id008', '2020-03-10', '2020-03-10'),
	(81, 'id009', '2020-03-11', '2020-03-11'),
	(82, 'id013', '2020-03-12', '2020-03-12'),
	(83, 'id014', '2020-03-13', '2020-03-13'),
	(85, 'id005', '2020-03-16', '2020-03-16'),
	(86, 'id006', '2020-03-17', '2020-03-17'),
	(87, 'id007', '2020-03-18', '2020-03-18'),
	(88, 'id008', '2020-03-19', '2020-03-19'),
	(89, 'id014', '2020-03-20', '2020-03-20'),
	(91, 'id003', '2020-03-22', '2020-03-22'),
	(92, 'id004', '2020-03-23', '2020-03-23'),
	(93, 'id005', '2020-03-24', '2020-03-24'),
	(94, 'id009', '2020-03-25', '2020-03-25'),
	(95, 'id013', '2020-03-30', '2020-03-30'),
	(96, 'id014', '2020-03-31', '2020-03-31'),
	(98, 'id005', '2020-04-02', '2020-04-02'),
	(99, 'id006', '2020-04-03', '2020-04-03'),
	(100, 'id007', '2020-04-04', '2020-04-04'),
	(101, 'id013', '2020-04-05', '2020-04-05'),
	(102, 'id014', '2020-04-06', '2020-04-06');
/*!40000 ALTER TABLE `tb_login` ENABLE KEYS */;

-- 테이블 ksmart37db.tb_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_member` (
  `m_id` varchar(200) NOT NULL,
  `m_pw` varchar(200) NOT NULL,
  `m_name` varchar(200) NOT NULL,
  `m_level` int(11) DEFAULT NULL,
  `m_email` varchar(200) NOT NULL,
  `m_addr` varchar(200) NOT NULL,
  `m_reg_date` date NOT NULL,
  PRIMARY KEY (`m_id`),
  KEY `FK_m_level` (`m_level`),
  CONSTRAINT `FK_m_level` FOREIGN KEY (`m_level`) REFERENCES `tb_member_level` (`level_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_member:~14 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_member` DISABLE KEYS */;
INSERT INTO `tb_member` (`m_id`, `m_pw`, `m_name`, `m_level`, `m_email`, `m_addr`, `m_reg_date`) VALUES
	('id001', 'pw001', '홍01', 1, '홍01@ksmart.or.kr', '전라북도 전주시 덕진구 금암동', '2020-11-24'),
	('id002', 'pw002', '홍02', 2, '홍02@ksmart.or.kr', '전라북도 전주시 완산구 서신동', '2020-03-17'),
	('id003', 'pw003', '홍03', 3, '홍03@ksmart.or.kr', '전라북도 전주시 덕진구 인후동', '2020-03-17'),
	('id004', 'pw004', '홍04', 3, '홍04@ksmart.or.kr', '전라북도 전주시 완산구 효자동', '2020-03-17'),
	('id005', 'pw005', '홍05', 3, '홍05@ksmart.or.kr', '전라북도 전주시 완산구 서서학동', '2020-03-17'),
	('id006', 'pw006', '홍06', 2, '홍06@ksmart.or.kr', '전라북도 전주시 덕진구 송천동', '2020-03-17'),
	('id007', 'pw007', '홍07', 2, '홍07@ksmart.or.kr', '전라북도 전주시 완산구 서신동', '2020-03-17'),
	('id008', 'pw008', '홍08', 3, '홍08@ksmart.or.kr', '전라북도 전주시 덕진구 호성동', '2020-03-17'),
	('id009', 'pw009', '홍09', 3, '홍09@ksmart.or.kr', '전라북도 전주시 완산구 중화산동', '2020-03-17'),
	('id010', 'pw010', '홍10', 2, '홍10@ksmart.or.kr', '전라북도 전주시 완산구 중화산동', '2020-03-17'),
	('id011', 'pw011', '홍11', 3, '홍11@ksmart.or.kr', '전라북도 전주시 덕진구 우아동', '2020-03-17'),
	('id012', 'pw012', '홍12', 2, '홍12@ksmart.or.kr', '전라북도 전주시 덕진구 인후동', '2020-03-17'),
	('id013', 'pw013', '홍13', 3, '홍13@ksmart.or.kr', '전라북도 전주시 덕진구 금암동', '2020-03-17'),
	('id014', 'pw014', '홍14', 3, '홍14@ksmart.or.kr', '전라북도 전주시 덕진구 송천동', '2020-03-17');
/*!40000 ALTER TABLE `tb_member` ENABLE KEYS */;

-- 테이블 ksmart37db.tb_member_level 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_member_level` (
  `level_num` int(11) NOT NULL AUTO_INCREMENT,
  `level_name` varchar(200) DEFAULT NULL,
  `level_reg_date` date DEFAULT NULL,
  PRIMARY KEY (`level_num`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_member_level:~4 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_member_level` DISABLE KEYS */;
INSERT INTO `tb_member_level` (`level_num`, `level_name`, `level_reg_date`) VALUES
	(1, '관리자', '2020-10-07'),
	(2, '판매자', '2020-03-17'),
	(3, '구매자', '2020-03-17'),
	(4, '회원', '2020-10-14');
/*!40000 ALTER TABLE `tb_member_level` ENABLE KEYS */;

-- 테이블 ksmart37db.tb_order 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_order` (
  `o_num` int(11) NOT NULL AUTO_INCREMENT,
  `o_id` varchar(200) DEFAULT NULL,
  `o_g_code` varchar(200) DEFAULT NULL,
  `o_amount` int(11) DEFAULT NULL,
  `o_reg_date` datetime DEFAULT NULL,
  PRIMARY KEY (`o_num`),
  KEY `FK_o_g_code` (`o_g_code`),
  KEY `FK_o_id` (`o_id`),
  CONSTRAINT `FK_o_id` FOREIGN KEY (`o_id`) REFERENCES `tb_member` (`m_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_order:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` (`o_num`, `o_id`, `o_g_code`, `o_amount`, `o_reg_date`) VALUES
	(1, 'id003', 'g006', 2, '2020-03-17 13:00:17'),
	(2, 'id004', 'g026', 5, '2020-03-17 14:55:17'),
	(3, 'id008', 'g052', 10, '2020-03-17 14:55:41'),
	(4, 'id009', 'g034', 20, '2020-03-17 14:56:05'),
	(5, 'id011', 'g052', 40, '2020-03-17 14:56:25'),
	(6, 'id014', 'g004', 8, '2020-03-17 14:56:49'),
	(7, 'id003', 'g008', 1, '2020-03-17 14:57:10'),
	(8, 'id005', 'g014', 20, '2020-03-17 14:57:26'),
	(9, 'id005', 'g027', 50, '2020-03-17 14:57:57'),
	(10, 'id013', 'g020', 13, '2020-03-17 14:58:26');
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;

-- 테이블 ksmart37db.tb_test 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_test` (
  `t_name` varchar(100) NOT NULL COMMENT '이름',
  `t_season` varchar(100) NOT NULL COMMENT '계절',
  `t_amount` int(11) NOT NULL COMMENT '수량'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_test:~9 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_test` DISABLE KEYS */;
INSERT INTO `tb_test` (`t_name`, `t_season`, `t_amount`) VALUES
	('김성주', '겨울', 10),
	('정동영', '여름', 15),
	('김성주', '가을', 25),
	('김성주', '봄', 3),
	('김성주', '봄', 37),
	('정동영', '겨울', 40),
	('김성주', '여름', 14),
	('김성주', '겨울', 22),
	('정동영', '여름', 64);
/*!40000 ALTER TABLE `tb_test` ENABLE KEYS */;

-- 테이블 ksmart37db.tb_user 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_user` (
  `u_id` varchar(100) NOT NULL COMMENT '사용자 아이디',
  `u_pw` varchar(100) NOT NULL COMMENT '사용자 비밀번호',
  `u_name` varchar(100) NOT NULL COMMENT '사용자 이름',
  `u_birth` date DEFAULT NULL COMMENT '사용자 생년월일',
  `u_add` varchar(100) NOT NULL COMMENT '사용자 주소',
  `u_mobile1` varchar(100) DEFAULT NULL COMMENT '사용자 연락처 1',
  `u_mobile2` varchar(100) DEFAULT NULL COMMENT '사용자 연락처 2',
  `u_reg_date` date DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_user:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` (`u_id`, `u_pw`, `u_name`, `u_birth`, `u_add`, `u_mobile1`, `u_mobile2`, `u_reg_date`) VALUES
	('id001', 'pw001', '홍01', '2019-01-10', '덕진동', '010', '00010001', '2020-03-10'),
	('id002', 'pw002', '홍02', '2020-03-10', '호성동', '010', '00020002', '2020-03-10'),
	('id003', 'pw003', '홍03', '2020-03-10', '금암동', '010', '00030003', '2020-03-10'),
	('id004', 'pw004', '홍04', '2020-03-10', '조촌동', '010', '00040004', '2020-03-10'),
	('id005', 'pw005', '홍05', '2020-03-10', '서서학동', '010', '00050005', '2020-03-10'),
	('id006', 'pw006', '홍06', '2020-03-10', '금암동', '010', '00060006', '2020-03-10'),
	('id007', 'pw007', '홍07', '2020-03-10', '송천동', '010', '00070007', '2020-03-10'),
	('id008', 'pw008', '홍08', '2020-03-10', '서신동', '010', '00080008', '2020-03-10'),
	('id009', 'pw009', '홍09', '2020-03-10', '효자동', '010', '00090009', '2020-03-10'),
	('id010', 'pw010', '홍10', '2019-02-10', '삼천동', '010', '00100010', '2020-03-10');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;

-- 테이블 ksmart37db.tb_user_backup 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_user_backup` (
  `u_id` varchar(100) NOT NULL COMMENT '사용자 아이디',
  `u_pw` varchar(100) NOT NULL COMMENT '사용자 비밀번호',
  `u_name` varchar(100) NOT NULL COMMENT '사용자 이름',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_user_backup:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_user_backup` DISABLE KEYS */;
INSERT INTO `tb_user_backup` (`u_id`, `u_pw`, `u_name`) VALUES
	('id001', 'pw001', '홍01'),
	('id002', 'pw002', '홍02'),
	('id003', 'pw003', '홍03'),
	('id004', 'pw004', '홍04'),
	('id005', 'pw005', '홍05'),
	('id006', 'pw006', '홍06'),
	('id007', 'pw007', '홍07'),
	('id008', 'pw008', '홍08'),
	('id009', 'pw009', '홍09'),
	('id010', 'pw010', '홍10');
/*!40000 ALTER TABLE `tb_user_backup` ENABLE KEYS */;

-- 테이블 ksmart37db.tb_user_backup_all 구조 내보내기
CREATE TABLE IF NOT EXISTS `tb_user_backup_all` (
  `u_id` varchar(100) NOT NULL COMMENT '사용자 아이디',
  `u_pw` varchar(100) NOT NULL COMMENT '사용자 비밀번호',
  `u_name` varchar(100) NOT NULL COMMENT '사용자 이름',
  `u_birth` date DEFAULT NULL COMMENT '사용자 생년월일',
  `u_add` varchar(100) NOT NULL COMMENT '사용자 주소',
  `u_mobile1` varchar(100) DEFAULT NULL COMMENT '사용자 연락처 1',
  `u_mobile2` varchar(100) DEFAULT NULL COMMENT '사용자 연락처 2',
  `u_reg_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.tb_user_backup_all:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `tb_user_backup_all` DISABLE KEYS */;
INSERT INTO `tb_user_backup_all` (`u_id`, `u_pw`, `u_name`, `u_birth`, `u_add`, `u_mobile1`, `u_mobile2`, `u_reg_date`) VALUES
	('id001', 'pw001', '홍01', '2020-03-10', '덕진동', '010', '00010001', '2020-03-10'),
	('id002', 'pw002', '홍02', '2020-03-10', '호성동', '010', '00020002', '2020-03-10'),
	('id003', 'pw003', '홍03', '2020-03-10', '금암동', '010', '00030003', '2020-03-10'),
	('id004', 'pw004', '홍04', '2020-03-10', '조촌동', '010', '00040004', '2020-03-10'),
	('id005', 'pw005', '홍05', '2020-03-10', '서서학동', '010', '00050005', '2020-03-10'),
	('id006', 'pw006', '홍06', '2020-03-10', '금암동', '010', '00060006', '2020-03-10'),
	('id007', 'pw007', '홍07', '2020-03-10', '송천동', '010', '00070007', '2020-03-10'),
	('id008', 'pw008', '홍08', '2020-03-10', '서신동', '010', '00080008', '2020-03-10'),
	('id009', 'pw009', '홍09', '2020-03-10', '효자동', '010', '00090009', '2020-03-10'),
	('id010', 'pw010', '홍10', '2020-03-10', '삼천동', '010', '00100010', '2020-03-10');
/*!40000 ALTER TABLE `tb_user_backup_all` ENABLE KEYS */;

-- 테이블 ksmart37db.test_user 구조 내보내기
CREATE TABLE IF NOT EXISTS `test_user` (
  `u_id` varchar(100) NOT NULL COMMENT '사용자 아이디',
  `u_pw` varchar(100) NOT NULL COMMENT '사용자 비밀번호',
  `u_name` varchar(100) NOT NULL COMMENT '사용자 이름',
  `u_birth` date DEFAULT NULL COMMENT '사용자 생년월일',
  `u_add` varchar(100) NOT NULL COMMENT '사용자 주소',
  `u_mobile1` varchar(100) DEFAULT NULL COMMENT '사용자 연락처 1',
  `u_mobile2` varchar(100) DEFAULT NULL COMMENT '사용자 연락처 2',
  `u_reg_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.test_user:~10 rows (대략적) 내보내기
/*!40000 ALTER TABLE `test_user` DISABLE KEYS */;
INSERT INTO `test_user` (`u_id`, `u_pw`, `u_name`, `u_birth`, `u_add`, `u_mobile1`, `u_mobile2`, `u_reg_date`) VALUES
	('id001', 'pw001', '홍01', '2019-01-10', '덕진동', '010', '00010001', '2020-03-10'),
	('id002', 'pw002', '홍02', '2020-03-10', '호성동', '010', '00020002', '2020-03-10'),
	('id003', 'pw003', '홍03', '2020-03-10', '금암동', '010', '00030003', '2020-03-10'),
	('id004', 'pw004', '홍04', '2020-03-10', '조촌동', '010', '00040004', '2020-03-10'),
	('id005', 'pw005', '홍05', '2020-03-10', '서서학동', '010', '00050005', '2020-03-10'),
	('id006', 'pw006', '홍06', '2020-03-10', '금암동', '010', '00060006', '2020-03-10'),
	('id007', 'pw007', '홍07', '2020-03-10', '송천동', '010', '00070007', '2020-03-10'),
	('id008', 'pw008', '홍08', '2020-03-10', '서신동', '010', '00080008', '2020-03-10'),
	('id009', 'pw009', '홍09', '2020-03-10', '효자동', '010', '00090009', '2020-03-10'),
	('id010', 'pw010', '홍10', '2019-02-10', '삼천동', '010', '00100010', '2020-03-10');
/*!40000 ALTER TABLE `test_user` ENABLE KEYS */;

-- 프로시저 ksmart37db.totalSumProc 구조 내보내기
DELIMITER //
CREATE DEFINER=`ksmart37id`@`%` PROCEDURE `totalSumProc`(
	IN `intValue` INT

)
BEGIN
	DECLARE i INT;
	DECLARE resultSum INT;
	SET i = 0;
	SET resultSum = 0;
	
	-- WHILE(i<intValue) DO
-- SET i=i+1;
-- SET resultSum = resultSum + i;
-- END WHILE;
-- 
-- 	SELECT resultSum as '총합계';	

	LOOP_sum: LOOP 
					IF(i=intValue) THEN
						LEAVE LOOP_sum;
					END IF;
					SET i = i+1;
					SET resultSum = resultSum + i;
				END LOOP;
	SELECT resultSum as '2를 제외한 총합';					

END//
DELIMITER ;

-- 테이블 ksmart37db.t_member 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_member` (
  `m_id` varchar(200) NOT NULL,
  `m_pw` varchar(200) NOT NULL,
  `m_name` varchar(200) NOT NULL,
  `m_level` varchar(50) DEFAULT NULL,
  `m_email` varchar(200) NOT NULL,
  `m_addr` varchar(200) NOT NULL,
  `m_reg_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.t_member:~15 rows (대략적) 내보내기
/*!40000 ALTER TABLE `t_member` DISABLE KEYS */;
INSERT INTO `t_member` (`m_id`, `m_pw`, `m_name`, `m_level`, `m_email`, `m_addr`, `m_reg_date`) VALUES
	('id001', 'pw001', '홍01', '1', '홍01@ksmart.or.kr', '전라북도 전주시 덕진구 금암동', '2020-03-17'),
	('id002', 'pw002', '홍02', '2', '홍02@ksmart.or.kr', '전라북도 전주시 완산구 서신동', '2020-03-17'),
	('id003', 'pw003', '홍03', '3', '홍03@ksmart.or.kr', '전라북도 전주시 덕진구 인후동', '2020-03-17'),
	('id004', 'pw004', '홍04', '3', '홍04@ksmart.or.kr', '전라북도 전주시 완산구 효자동', '2020-03-17'),
	('id005', 'pw005', '홍05', '3', '홍05@ksmart.or.kr', '전라북도 전주시 완산구 서서학동', '2020-03-17'),
	('id006', 'pw006', '홍06', '2', '홍06@ksmart.or.kr', '전라북도 전주시 덕진구 송천동', '2020-03-17'),
	('id007', 'pw007', '홍07', '2', '홍07@ksmart.or.kr', '전라북도 전주시 완산구 서신동', '2020-03-17'),
	('id008', 'pw008', '홍08', '3', '홍08@ksmart.or.kr', '전라북도 전주시 덕진구 호성동', '2020-03-17'),
	('id009', 'pw009', '홍09', '3', '홍09@ksmart.or.kr', '전라북도 전주시 완산구 중화산동', '2020-03-17'),
	('id010', 'pw010', '홍10', '2', '홍10@ksmart.or.kr', '전라북도 전주시 완산구 중화산동', '2020-03-17'),
	('id011', 'pw011', '홍11', '3', '홍11@ksmart.or.kr', '전라북도 전주시 덕진구 우아동', '2020-03-17'),
	('id012', 'pw012', '홍12', '2', '홍12@ksmart.or.kr', '전라북도 전주시 덕진구 인후동', '2020-03-17'),
	('id013', 'pw013', '홍13', '3', '홍13@ksmart.or.kr', '전라북도 전주시 덕진구 금암동', '2020-03-17'),
	('id014', 'pw014', '홍14', '3', '홍14@ksmart.or.kr', '전라북도 전주시 덕진구 송천동', '2020-03-17'),
	('id014', 'pw014', '홍14', '2', '홍14@ksmart.or.kr', '전라북도 전주시 덕진구 금암동', '2020-11-17');
/*!40000 ALTER TABLE `t_member` ENABLE KEYS */;

-- 테이블 ksmart37db.t_member_trigger_backup 구조 내보내기
CREATE TABLE IF NOT EXISTS `t_member_trigger_backup` (
  `m_id` varchar(200) NOT NULL,
  `m_pw` varchar(200) NOT NULL,
  `m_name` varchar(200) NOT NULL,
  `m_level` int(11) DEFAULT NULL,
  `m_email` varchar(200) NOT NULL,
  `m_addr` varchar(200) NOT NULL,
  `m_reg_date` date NOT NULL,
  `m_modify_type` varchar(50) DEFAULT NULL,
  `m_modify_date` date DEFAULT NULL,
  `m_modify_user` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 ksmart37db.t_member_trigger_backup:~2 rows (대략적) 내보내기
/*!40000 ALTER TABLE `t_member_trigger_backup` DISABLE KEYS */;
INSERT INTO `t_member_trigger_backup` (`m_id`, `m_pw`, `m_name`, `m_level`, `m_email`, `m_addr`, `m_reg_date`, `m_modify_type`, `m_modify_date`, `m_modify_user`) VALUES
	('id015', 'pw015', '홍15', 3, '홍15@ksmart.or.kr', '전라북도 전주시 완산구 서신동', '2020-03-17', '수정', '2020-11-17', 'ksmart37id@%'),
	('id015', 'pw015', '홍16', 3, '홍15@ksmart.or.kr', '전라북도 전주시 완산구 서신동', '2020-03-17', '수정', '2020-11-17', 'ksmart37id@%');
/*!40000 ALTER TABLE `t_member_trigger_backup` ENABLE KEYS */;

-- 뷰 ksmart37db.v_max_amt_member 구조 내보내기
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `v_max_amt_member` (
	`v_id` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`v_email` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`v_g_name` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	`v_max_amt` BIGINT(21) NULL
) ENGINE=MyISAM;

-- 뷰 ksmart37db.v_sleeper_member 구조 내보내기
-- VIEW 종속성 오류를 극복하기 위해 임시 테이블을 생성합니다.
CREATE TABLE `v_sleeper_member` (
	`m_id` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`m_pw` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`m_name` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`m_level` INT(11) NULL,
	`m_email` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`m_addr` VARCHAR(200) NOT NULL COLLATE 'utf8_general_ci',
	`m_reg_date` DATE NOT NULL
) ENGINE=MyISAM;

-- 트리거 ksmart37db.t_member_after_update 구조 내보내기
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `t_member_after_update` AFTER DELETE ON `t_member` FOR EACH ROW BEGIN
INSERT INTO t_member_trigger_backup(
	m_id,
	m_pw,
	m_name,
	m_level,
	m_email,
	m_addr,
	m_reg_date,
	m_modify_type,
	m_modify_date,
	m_modify_user
)VALUES(
	OLD.m_id,
	OLD.m_pw,
	OLD.m_name,
	OLD.m_level,
	OLD.m_email,
	OLD.m_addr,
	OLD.m_reg_date,
	'수정',
	CURDATE(),
	CURRENT_USER()
	/*현재 테이블에 접근하고 있는 사람 표시*/
);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 트리거 ksmart37db.t_member_before_insert 구조 내보내기
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `t_member_before_insert` BEFORE INSERT ON `t_member` FOR EACH ROW BEGIN
CASE
WHEN NEW.m_level = '관리자' THEN SET NEW.m_level = '1';
WHEN NEW.m_level = '판매자' THEN SET NEW.m_level = '2';
ELSE SET NEW.m_level = '3';
END CASE;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 뷰 ksmart37db.v_max_amt_member 구조 내보내기
-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `v_max_amt_member`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ksmart37id`@`%` SQL SECURITY DEFINER VIEW `v_max_amt_member` AS select `m`.`m_id` AS `v_id`,`m`.`m_email` AS `v_email`,`g`.`g_name` AS `v_g_name`,max((`g`.`g_price` * `o`.`o_amount`)) AS `v_max_amt` from ((`tb_order` `o` join `tb_goods` `g` on((`o`.`o_g_code` = `g`.`g_code`))) join `tb_member` `m` on((`m`.`m_id` = `o`.`o_id`))) group by `m`.`m_id`;

-- 뷰 ksmart37db.v_sleeper_member 구조 내보내기
-- 임시 테이블을 제거하고 최종 VIEW 구조를 생성
DROP TABLE IF EXISTS `v_sleeper_member`;
CREATE ALGORITHM=UNDEFINED DEFINER=`ksmart37id`@`%` SQL SECURITY DEFINER VIEW `v_sleeper_member` AS select `m`.`m_id` AS `m_id`,`m`.`m_pw` AS `m_pw`,`m`.`m_name` AS `m_name`,`m`.`m_level` AS `m_level`,`m`.`m_email` AS `m_email`,`m`.`m_addr` AS `m_addr`,`m`.`m_reg_date` AS `m_reg_date` from (`tb_member` `m` left join `tb_login` `l` on((`m`.`m_id` = `l`.`login_id`))) where isnull(`l`.`login_id`);

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
