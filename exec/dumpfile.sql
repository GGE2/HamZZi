-- MySQL dump 10.19  Distrib 10.3.37-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: i8d209.p.ssafy.io    Database: ham_db
-- ------------------------------------------------------
-- Server version	10.3.37-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ham_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ham_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ham_db`;

--
-- Table structure for table `achievement`
--

DROP TABLE IF EXISTS `achievement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `achievement` (
  `achievement_id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`achievement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement`
--

LOCK TABLES `achievement` WRITE;
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `count`
--

DROP TABLE IF EXISTS `count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `count` (
  `count_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `quest` int(11) NOT NULL,
  `todo` int(11) NOT NULL,
  PRIMARY KEY (`count_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `count`
--

LOCK TABLES `count` WRITE;
/*!40000 ALTER TABLE `count` DISABLE KEYS */;
INSERT INTO `count` VALUES (1,'관리자',0,0),(2,'블랙',0,0),(3,'리종길',0,0),(4,'구미209',0,0),(5,'햄찌1',0,0),(6,'햄찌2',0,0),(7,'햄찌3',0,0);
/*!40000 ALTER TABLE `count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `count_daily`
--

DROP TABLE IF EXISTS `count_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `count_daily` (
  `daily_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `quest` int(11) NOT NULL,
  `todo` int(11) NOT NULL,
  PRIMARY KEY (`daily_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `count_daily`
--

LOCK TABLES `count_daily` WRITE;
/*!40000 ALTER TABLE `count_daily` DISABLE KEYS */;
INSERT INTO `count_daily` VALUES (1,'관리자',0,0),(2,'블랙',0,0),(3,'리종길',0,0),(4,'구미209',0,0),(5,'햄찌1',0,0),(6,'햄찌2',0,0),(7,'햄찌3',0,0);
/*!40000 ALTER TABLE `count_daily` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `count_weekly`
--

DROP TABLE IF EXISTS `count_weekly`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `count_weekly` (
  `weekly_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `quest` int(11) NOT NULL,
  `todo` int(11) NOT NULL,
  PRIMARY KEY (`weekly_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `count_weekly`
--

LOCK TABLES `count_weekly` WRITE;
/*!40000 ALTER TABLE `count_weekly` DISABLE KEYS */;
INSERT INTO `count_weekly` VALUES (1,'관리자',0,0),(2,'블랙',0,0),(3,'리종길',0,0),(4,'구미209',0,2),(5,'햄찌1',0,0),(6,'햄찌2',0,0),(7,'햄찌3',0,0);
/*!40000 ALTER TABLE `count_weekly` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend` (
  `friend_id` bigint(20) NOT NULL,
  `friend_nickname` varchar(255) NOT NULL,
  `user_nickname` varchar(255) NOT NULL,
  `relation` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guild`
--

DROP TABLE IF EXISTS `guild`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guild` (
  `guild_id` bigint(20) NOT NULL,
  `admin_nickname` varchar(255) DEFAULT NULL,
  `guild_name` varchar(255) DEFAULT NULL,
  `personnel` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`guild_id`),
  UNIQUE KEY `UK_bxei242mqcydp2xpt90lhj3hh` (`guild_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guild`
--

LOCK TABLES `guild` WRITE;
/*!40000 ALTER TABLE `guild` DISABLE KEYS */;
INSERT INTO `guild` VALUES (1,'리종길','같이 걸어요',1),(3,'구미209','청천이귀여워',4);
/*!40000 ALTER TABLE `guild` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guild_user`
--

DROP TABLE IF EXISTS `guild_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guild_user` (
  `user_nickname` varchar(255) NOT NULL,
  `admin` bit(1) NOT NULL DEFAULT b'0',
  `guild_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_nickname`),
  KEY `FKb9t28734xyf955pp93ln7kukc` (`guild_id`),
  CONSTRAINT `FKb9t28734xyf955pp93ln7kukc` FOREIGN KEY (`guild_id`) REFERENCES `guild` (`guild_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guild_user`
--

LOCK TABLES `guild_user` WRITE;
/*!40000 ALTER TABLE `guild_user` DISABLE KEYS */;
INSERT INTO `guild_user` VALUES ('구미209','',3),('리종길','',1),('햄찌1','\0',3),('햄찌2','\0',3),('햄찌3','\0',3);
/*!40000 ALTER TABLE `guild_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,4,'모자'),(2,1,4,'모자'),(3,1,4,'모자'),(4,2,4,'모자'),(30,1,4,'옷'),(31,2,4,'옷'),(60,1,4,'배경');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_user`
--

DROP TABLE IF EXISTS `item_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_user` (
  `item_user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_user_id`),
  KEY `FKifmvp6c49o60le5vvdhcd1f0y` (`item_id`),
  CONSTRAINT `FKifmvp6c49o60le5vvdhcd1f0y` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_user`
--

LOCK TABLES `item_user` WRITE;
/*!40000 ALTER TABLE `item_user` DISABLE KEYS */;
INSERT INTO `item_user` VALUES (1,'구미209',1),(2,'구미209',30),(3,'구미209',60),(4,'구미209',31),(5,'구미209',4),(6,'구미209',3),(7,'구미209',2);
/*!40000 ALTER TABLE `item_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet`
--

DROP TABLE IF EXISTS `pet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet` (
  `pet_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `exp` int(11) DEFAULT NULL,
  `graduate_date` date DEFAULT NULL,
  `is_graduate` bit(1) NOT NULL DEFAULT b'0',
  `level` int(11) NOT NULL DEFAULT 1,
  `user_nickname` varchar(255) DEFAULT NULL,
  `pet_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet`
--

LOCK TABLES `pet` WRITE;
/*!40000 ALTER TABLE `pet` DISABLE KEYS */;
INSERT INTO `pet` VALUES (1,'2023-02-17',0,NULL,'\0',1,'관리자','관리자'),(2,'2023-02-17',0,NULL,'\0',1,'블랙','펄'),(3,'2023-02-17',0,NULL,'\0',1,'리종길','햄찌'),(4,'2023-02-17',0,'2023-02-17','',5,'구미209','구미209'),(5,'2023-02-17',0,'2023-02-17','',5,'구미209','싸피8기'),(6,'2023-02-17',0,'2023-02-17','',5,'구미209','끝끝'),(7,'2023-02-17',0,NULL,'\0',1,'구미209','포청천'),(8,'2023-02-17',0,NULL,'\0',1,'햄찌1','햄찌1'),(9,'2023-02-17',0,NULL,'\0',1,'햄찌2','햄찌2'),(10,'2023-02-17',0,NULL,'\0',1,'햄찌3','햄찌3');
/*!40000 ALTER TABLE `pet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_info`
--

DROP TABLE IF EXISTS `pet_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_info` (
  `pet_info_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `pet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pet_info_id`),
  KEY `FKhp17c1tn1k1m3w9oiiafxr5fq` (`pet_id`),
  CONSTRAINT `FKhp17c1tn1k1m3w9oiiafxr5fq` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_info`
--

LOCK TABLES `pet_info` WRITE;
/*!40000 ALTER TABLE `pet_info` DISABLE KEYS */;
INSERT INTO `pet_info` VALUES (1,0,1),(2,0,2),(3,0,3),(4,1,4),(5,1,5),(6,1,6),(7,0,7),(8,0,8),(9,0,9),(10,0,10);
/*!40000 ALTER TABLE `pet_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pet_stat`
--

DROP TABLE IF EXISTS `pet_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pet_stat` (
  `pet_stat_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `artistic` int(11) NOT NULL DEFAULT 0,
  `energetic` int(11) NOT NULL DEFAULT 0,
  `etc` int(11) NOT NULL DEFAULT 0,
  `inactive` int(11) NOT NULL DEFAULT 0,
  `intelligent` int(11) NOT NULL DEFAULT 0,
  `physical` int(11) NOT NULL DEFAULT 0,
  `pet_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pet_stat_id`),
  KEY `FKre528l5f9o2wxl5k2vte7i9wr` (`pet_id`),
  CONSTRAINT `FKre528l5f9o2wxl5k2vte7i9wr` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`pet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pet_stat`
--

LOCK TABLES `pet_stat` WRITE;
/*!40000 ALTER TABLE `pet_stat` DISABLE KEYS */;
INSERT INTO `pet_stat` VALUES (1,0,0,0,0,0,0,1),(2,0,0,0,0,0,0,2),(3,0,0,0,0,0,0,3),(4,6,5,7,11,32,30,4),(5,0,0,0,0,31,30,5),(6,11,8,5,9,17,16,6),(7,0,0,0,0,0,0,7),(8,0,0,0,0,0,0,8),(9,0,0,0,0,0,0,9),(10,0,0,0,0,0,0,10);
/*!40000 ALTER TABLE `pet_stat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quest`
--

DROP TABLE IF EXISTS `quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quest` (
  `quest_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `point` int(11) NOT NULL DEFAULT 0,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`quest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quest`
--

LOCK TABLES `quest` WRITE;
/*!40000 ALTER TABLE `quest` DISABLE KEYS */;
INSERT INTO `quest` VALUES (1,'회사/학교에 지각하지 않기',3,'daily'),(2,'5000보 걷기',1,'daily'),(3,'10000보 걷기',3,'daily'),(4,'20000보 걷기',5,'daily'),(5,'50000보 걷기',5,'weekly'),(6,'100000보 걷기',10,'weekly');
/*!40000 ALTER TABLE `quest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quest_daily`
--

DROP TABLE IF EXISTS `quest_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quest_daily` (
  `quest_daily_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ischeck` bit(1) NOT NULL DEFAULT b'0',
  `user_nickname` varchar(255) NOT NULL,
  `quest_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`quest_daily_id`),
  KEY `FK6owxlllifjpvfbltqe9ko1wbu` (`quest_id`),
  CONSTRAINT `FK6owxlllifjpvfbltqe9ko1wbu` FOREIGN KEY (`quest_id`) REFERENCES `quest` (`quest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quest_daily`
--

LOCK TABLES `quest_daily` WRITE;
/*!40000 ALTER TABLE `quest_daily` DISABLE KEYS */;
INSERT INTO `quest_daily` VALUES (1,'\0','관리자',1),(2,'\0','관리자',2),(3,'\0','관리자',3),(4,'\0','관리자',4),(5,'\0','블랙',1),(6,'\0','블랙',2),(7,'\0','블랙',3),(8,'\0','블랙',4),(9,'\0','리종길',1),(10,'\0','리종길',2),(11,'\0','리종길',3),(12,'\0','리종길',4),(13,'\0','구미209',1),(14,'\0','구미209',2),(15,'\0','구미209',3),(16,'\0','구미209',4),(17,'\0','햄찌1',1),(18,'\0','햄찌1',2),(19,'\0','햄찌1',3),(20,'\0','햄찌1',4),(21,'\0','햄찌2',1),(22,'\0','햄찌2',2),(23,'\0','햄찌2',3),(24,'\0','햄찌2',4),(25,'\0','햄찌3',1),(26,'\0','햄찌3',2),(27,'\0','햄찌3',3),(28,'\0','햄찌3',4);
/*!40000 ALTER TABLE `quest_daily` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quest_weekly`
--

DROP TABLE IF EXISTS `quest_weekly`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quest_weekly` (
  `quest_weekly_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ischeck` bit(1) NOT NULL DEFAULT b'0',
  `user_nickname` varchar(255) NOT NULL,
  `quest_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`quest_weekly_id`),
  KEY `FK8vcl4xa99ftc01p422rhhkee0` (`quest_id`),
  CONSTRAINT `FK8vcl4xa99ftc01p422rhhkee0` FOREIGN KEY (`quest_id`) REFERENCES `quest` (`quest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quest_weekly`
--

LOCK TABLES `quest_weekly` WRITE;
/*!40000 ALTER TABLE `quest_weekly` DISABLE KEYS */;
INSERT INTO `quest_weekly` VALUES (1,'\0','관리자',5),(2,'\0','관리자',6),(3,'\0','블랙',5),(4,'\0','블랙',6),(5,'\0','리종길',5),(6,'\0','리종길',6),(7,'\0','구미209',5),(8,'\0','구미209',6),(9,'\0','햄찌1',5),(10,'\0','햄찌1',6),(11,'\0','햄찌2',5),(12,'\0','햄찌2',6),(13,'\0','햄찌3',5),(14,'\0','햄찌3',6);
/*!40000 ALTER TABLE `quest_weekly` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `todo`
--

DROP TABLE IF EXISTS `todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todo` (
  `todo_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `datetime` varchar(255) NOT NULL,
  `ischeck` bit(1) DEFAULT b'0',
  `user_nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`todo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo`
--

LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` VALUES (1,'1','2023-02-17','','구미209'),(2,'22323','2023-02-17','','구미209'),(3,'3','2023-02-17','\0','구미209');
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `fcm_token` varchar(255) DEFAULT NULL,
  `uid` varchar(255) NOT NULL,
  `user_nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKc4v8o81dq97fq4vxils90jwnp` (`user_nickname`),
  CONSTRAINT `FKc4v8o81dq97fq4vxils90jwnp` FOREIGN KEY (`user_nickname`) REFERENCES `user_profile` (`user_nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin123@naver.com',NULL,'aoxsx1C2Bhe3XRpv67L2YJiFUIz1','관리자'),(2,'dono3107@naver.com','cPWScQk1SciWOa55C4U46N:APA91bFtZNlt377A_8BmSWNzZrnzSMeHbSy78CvUVjbLu17q3mzcxO3dqYgLLairf_Gnuwcl3gGq98ISfuBECzcbEpQYY3_38xJd-KuVIW_NnqpmYSKqLKZdBsGFmjGKi0OFV8iMldXf','kakao2643949297','블랙'),(3,'jong.gil.lee93@gmail.com',NULL,'lZfV4Cm6XzMGLUpJfcIG2rknkEI3','리종길'),(4,'jsmon3537@gmail.com',NULL,'h0WnC800QVYDyqx40LW2RnoCTAI2','구미209'),(6,'jsmon1@naver.com',NULL,'J8knjOcTNyM0fbGSVIEwt5hJVCH2','햄찌1'),(7,'jsmon2@naver.com',NULL,'VFj7mewmJ0NxVsZpC203b56MedA3','햄찌2'),(8,'jsmon3@naver.com',NULL,'5JHY1JfZ48bPjcy7KnXpfIvIgF03','햄찌3');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `user_nickname` varchar(255) NOT NULL,
  `background` int(11) NOT NULL,
  `dress` int(11) NOT NULL,
  `finish_datetime` int(11) NOT NULL,
  `hat` int(11) NOT NULL,
  `latitude` double NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `longitude` double NOT NULL,
  `pedometer` int(11) NOT NULL,
  `point` int(11) NOT NULL DEFAULT 0,
  `rest_point` int(11) NOT NULL DEFAULT 3,
  PRIMARY KEY (`user_nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES ('관리자',0,0,0,0,0,NULL,0,0,20000,3),('구미209',0,0,0,0,0,NULL,0,0,1775,1),('리종길',0,0,0,0,0,NULL,0,0,50,3),('블랙',0,0,0,0,0,NULL,0,0,50,3),('햄찌1',0,0,0,0,0,NULL,0,0,50,3),('햄찌2',0,0,0,0,0,NULL,0,0,50,3),('햄찌3',0,0,0,0,0,NULL,0,0,50,3);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 10:36:44
