

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `khuyen_mai`
--

DROP TABLE IF EXISTS `khuyen_mai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khuyen_mai` (
  `Id_KM` int(11) NOT NULL,
  `Ten_KM` varchar(45) NOT NULL,
  `TGDR` date NOT NULL,
  `TGKT` date NOT NULL,
  PRIMARY KEY (`Id_KM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khuyen_mai`
--

LOCK TABLES `khuyen_mai` WRITE;
/*!40000 ALTER TABLE `khuyen_mai` DISABLE KEYS */;
INSERT INTO `khuyen_mai`(`Id_KM`,`Ten_KM`,`TGDR`,`TGKT`) VALUES 
(1,'Đợt I','2015/10/10','2015/10/12'),
(2,'Đợt II ','2015/11/1','2015/11/14'),
(3,'Đợt III','2015/11/20','2015/11/30'),
(4,'Đợt IV','2015/12/3','2015/12/10');
/*!40000 ALTER TABLE `khuyen_mai` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `ncc`
--

DROP TABLE IF EXISTS `ncc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ncc` (
  `Id_NCC` int(11) NOT NULL,
  `Ten_NCC` varchar(45) NOT NULL,
  `TTLH` varchar(45) NOT NULL,
  PRIMARY KEY (`Id_NCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ncc`
--

LOCK TABLES `ncc` WRITE;
/*!40000 ALTER TABLE `ncc` DISABLE KEYS */;
INSERT INTO `ncc`(`Id_NCC`,`Ten_NCC`,`TTLH`) VALUES
(1,'VINAMILK','Hải Phòng - Việt Nam'),
(2,'Fonterra','California,USA'),
(3,'Nestle','South Korea'),
(4,'Brain TVC','England'),
(5,'Monte','Việt Nam'),
(6,'TH TRUE MILK','Ba Vì Việt Nam'),
(7,'Clear','Thailand'),
(8,'PS','USA'),
(9,'Romano','USA'),
(10,'Lifebuoy','France'),
(11,'X-men','Japan'),
(12,'Công ty thực phẩm A','Vĩnh Phúc - Việt Nam'),
(13,'KFC','Hai Bà Trưng - Hà Nội -Việt Nam'),
(14,'Thượng Đình','Nguyên Trãi - Hà Nôi - VIệt Nam'),
(15,'Gia công B','Vũng Tàu- Việt Nam'),
(16,'Xiaomi','Trung Quốc');
/*!40000 ALTER TABLE `ncc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhomhang`
--

DROP TABLE IF EXISTS `nhomhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nhomhang` (
  `ID_NhomHang` int(11) NOT NULL,
  `Ten_Nhomhang` varchar(60) NOT NULL,
  PRIMARY KEY (`ID_NhomHang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhomhang`
--

LOCK TABLES `nhomhang` WRITE;
/*!40000 ALTER TABLE `nhomhang` DISABLE KEYS */;

INSERT INTO `nhomhang` VALUES 
(1,'Thực Phẩm Khô Ngọt - Mặn'),
(2,'Thực Phẩm Đông Lạnh - Thức Uống'),
(3,'Hóa Mỹ Phẩm'),
(4,'THực Phẩm Tươi Sống - Thực Phẩm Chế Biến'),
(5,'Thời Trang'),
(6,'Vật Dụng Gia Đình'),
(7,'Điện Máy');

/*!40000 ALTER TABLE `nhomhang` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `mat_hang`
--

DROP TABLE IF EXISTS `mat_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mat_hang` (
  `MH_ID_MatHang` int(11) NOT NULL,
  `Gia_Nhap` int(11) NOT NULL,
  `Gia_Ban` int(11) NOT NULL,
  `Ten_MH` varchar(45) NOT NULL,
  `Soluong` int(11) NOT NULL,
  `MH_ID_NCC` int(11) NOT NULL,
  `MH_ID_NhomHang` int(11) NOT NULL,
  PRIMARY KEY (`MH_ID_MatHang`,`MH_ID_NCC`,`MH_ID_NhomHang`),

  FOREIGN KEY (`MH_ID_NCC`) REFERENCES `ncc` (`Id_NCC`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`MH_ID_NhomHang`) REFERENCES `nhomhang` (`ID_NhomHang`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mat_hang` WRITE;
/*!40000 ALTER TABLE `mat_hang` DISABLE KEYS */;
INSERT INTO `mat_hang`(`MH_ID_MatHang`,`Gia_Nhap`,`Gia_Ban`,`Ten_MH`,`Soluong`,`MH_ID_NCC`,`MH_ID_NhomHang`) VALUES 
(1,5000,6000,'Sữa bột cô gái Hà Lan',10000,1,1),
(2,400000,620000,'Sữa ANMUM',50,2,1),
(3,300000,330000,'Sữa ANMUM MATERNA',100,2,1),
(4,400000,450000,'Sữa Nan Nga số 3 800g',200,3,1),
(5,420000,450000,'Sữa Nan Nga số 1 800g',150,3,1),
(6,300000,330000,'Sữa Nan Gro 3 900g',100,3,1),
(7,130000,150000,'Nước cốt gà Brain',500,4,2),
(8,50000,55000,'Váng sữa monte',1000,5,2),
(9,20000,25000,'Lốc 4 hộp sữa TH true milk 110ml',1000,6,2),
(10,100000,120000,'Dầu gội đầu clear 650g',1000,7,3),
(11,10000,10500,'Kem đánh răng PS',1500,8,3),
(12,12000,16000,'xà phòng romano 90g',500,9,3),
(13,15000,18000,'xà phong lifebuoy',500,10,3),
(14,13000,17000,'xà phòng x-men 90g',500,11,3),
(15,10000,11000,'Mướp đắng',50,12,4),
(16,12000,15000,'Rau muống',50,12,4),
(17,60000,65000,'Gà rán',50,13,4),
(18,1000000,1500000,'Giày da cao cấp',60,14,5),
(19,2000000,2100000,'Bộ nồi thủy tinh',100,15,6),
(20,100000,200000,'Bộ dao cao cấp',100,15,6),
(21,690000,700000,'BÌnh đun nước siêu tốc',100,2,7),
(22,700000,900000,'Tai nghe xiaomi',100,16,7);


/*!40000 ALTER TABLE `mat_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mat_hang_co_khuyen_mai`
--

DROP TABLE IF EXISTS `mat_hang_co_khuyen_mai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mat_hang_co_khuyen_mai` (
  `ID_Dot_KM` int(11) NOT NULL,
  `KM_ID_MatHang` int(11) NOT NULL,
  `KM_ID_NCC` int(11) NOT NULL,
  `KM_ID_NhomHang` int(11) NOT NULL,
  `Gia_KM` int NOT NULL,
  PRIMARY KEY (`KM_ID_MatHang`,`KM_ID_NCC`,`KM_ID_NhomHang`),
  FOREIGN KEY (`KM_ID_MatHang`) REFERENCES `mat_hang` (`MH_ID_MatHang`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY ( `KM_ID_NCC`) REFERENCES `ncc` ( `Id_NCC`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`KM_ID_NhomHang`) REFERENCES `nhomhang` (`ID_NhomHang`) ON DELETE NO ACTION ON UPDATE NO ACTION	,
  FOREIGN KEY(`ID_Dot_KM`)REFERENCES `khuyen_mai`(`Id_KM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mat_hang_co_khuyen_mai`
--

LOCK TABLES `mat_hang_co_khuyen_mai` WRITE;
/*!40000 ALTER TABLE `mat_hang_co_khuyen_mai` DISABLE KEYS */;
INSERT INTO `mat_hang_co_khuyen_mai`(`ID_Dot_KM`,`KM_ID_MatHang`,`KM_ID_NCC`,`KM_ID_NhomHang`,`Gia_KM`) VALUES 
(1,1,1,1,4000),
(1,2,2,1,60000),
(1,3,2,1,300000),
(2,4,3,1,8000000),
(2,22,16,8,800000);
/*!40000 ALTER TABLE `mat_hang_co_khuyen_mai` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table`account`
LOCK TABLES `account` WRITE;
INSERT INTO `account` VALUES
('admin','admin123');
UNLOCK TABLES;
/*!40101 SET character_set_client = @saved_cs_client */;

--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


