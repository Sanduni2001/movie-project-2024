-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinema
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `bookingId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `movieId` int(11) NOT NULL,
  `showDate` varchar(10) NOT NULL,
  `showTime` time DEFAULT NULL,
  `numberOfSeats` int(11) NOT NULL,
  `totalPrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`bookingId`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (10,10,3,'2024-12-19','09:00:00',1,10.00),(25,10,13,'2025-01-11','13:00:00',2,40.00),(32,10,12,'2025-01-11','09:00:00',1,10.00);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `movieId` int(11) NOT NULL AUTO_INCREMENT,
  `movieName` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `releaseDate` date NOT NULL,
  `directorName` varchar(255) NOT NULL,
  `musicBy` varchar(255) DEFAULT NULL,
  `produceBy` varchar(255) DEFAULT NULL,
  `ticketPrice` decimal(10,2) NOT NULL,
  `image` varchar(255) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`movieId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (3,'Kungfu Panda','Panda','2024-12-18','Saduni','Janidu','Shashen',10.00,'cs2.png','Now Showing'),(12,'The Longest Ride','Cow Boy','2025-01-04','Didulaka','Kavi','Navo',10.00,'cs4.png','Now Showing'),(13,'Sonic','Hedge Hog','2025-01-06','Shashen','Janidu','Navo',20.00,'ns4.png','Now Showing'),(14,'Ghost','Horror','2025-01-11','Saduni','Ushan','Lakmith',7.00,'cs5.png','Now Showing'),(15,'Spider Man','Spider','2025-01-08','Achi','Kavi','Navo',25.00,'ns6.png','Now Showing'),(16,'Fast and the Furious','Car','2025-01-02','Chamidu','Ushan','Navo',5.00,'ns7.png','Now Showing'),(17,'Harri Potter','Magic','2025-01-09','Shashen','Kavi','Didu',9.00,'ns8.png','Now Showing'),(19,'kgf','Tamil','2025-01-03','Abc','AA','AAA',10.00,'cs7.png','Now Showing');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `description` text NOT NULL,
  `stars` int(11) NOT NULL,
  `createdDate` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (2,9,'Abcde',4,'2024-12-17 07:37:03'),(3,9,'Abc',2,'2024-12-17 07:37:38'),(4,10,'good',4,'2024-12-17 10:07:17'),(5,10,'yuxsdgy',5,'2024-12-31 11:48:08');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showdate`
--

DROP TABLE IF EXISTS `showdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showdate` (
  `showDateId` int(11) NOT NULL AUTO_INCREMENT,
  `movieId` int(11) NOT NULL,
  `showDate` date NOT NULL,
  PRIMARY KEY (`showDateId`),
  KEY `movieId` (`movieId`),
  CONSTRAINT `showdate_ibfk_1` FOREIGN KEY (`movieId`) REFERENCES `movies` (`movieId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showdate`
--

LOCK TABLES `showdate` WRITE;
/*!40000 ALTER TABLE `showdate` DISABLE KEYS */;
INSERT INTO `showdate` VALUES (4,3,'2024-12-19'),(13,12,'2025-01-11'),(14,13,'2025-01-11'),(15,14,'2025-01-11'),(16,15,'2025-01-15'),(17,16,'2025-01-11'),(18,17,'2025-01-23'),(19,19,'2025-01-10');
/*!40000 ALTER TABLE `showdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `showtimes`
--

DROP TABLE IF EXISTS `showtimes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showtimes` (
  `showTimeId` int(11) NOT NULL AUTO_INCREMENT,
  `movieId` int(11) NOT NULL,
  `showDateId` int(11) NOT NULL,
  `showTime` varchar(8) NOT NULL,
  PRIMARY KEY (`showTimeId`),
  KEY `movieId` (`movieId`),
  KEY `showDateId` (`showDateId`),
  CONSTRAINT `showtimes_ibfk_1` FOREIGN KEY (`movieId`) REFERENCES `movies` (`movieId`),
  CONSTRAINT `showtimes_ibfk_2` FOREIGN KEY (`showDateId`) REFERENCES `showdate` (`showDateId`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showtimes`
--

LOCK TABLES `showtimes` WRITE;
/*!40000 ALTER TABLE `showtimes` DISABLE KEYS */;
INSERT INTO `showtimes` VALUES (7,3,4,'09:00:00'),(8,3,4,'13:00:00'),(9,3,4,'16:00:00'),(32,12,13,'09:00:00'),(33,12,13,'13:00:00'),(34,12,13,'16:00:00'),(35,12,13,'21:00:00'),(36,13,14,'09:00:00'),(37,13,14,'13:00:00'),(38,13,14,'21:00:00'),(39,14,15,'09:00:00'),(40,14,15,'16:00:00'),(41,15,16,'09:00:00'),(42,15,16,'13:00:00'),(43,15,16,'16:00:00'),(44,15,16,'21:00:00'),(45,16,17,'13:00:00'),(46,16,17,'16:00:00'),(47,16,17,'21:00:00'),(48,17,18,'09:00:00'),(49,17,18,'13:00:00'),(50,17,18,'16:00:00'),(51,17,18,'21:00:00'),(52,19,19,'09:00:00'),(53,19,19,'13:00:00'),(54,19,19,'16:00:00'),(55,19,19,'21:00:00');
/*!40000 ALTER TABLE `showtimes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mobile` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  `isActive` tinyint(1) DEFAULT 0,
  `emailVerified` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (4,'admin','admin@gmail.com',1234567890,'123','ADMIN',1,1),(10,'Hiruni','Punithachintha@gmail.com',765694258,'12345678','CLIENT',1,1),(11,'Punith Achintha','punithhirmibura0307@gmail.com',765694258,'123','CLIENT',1,1),(12,'Janidu','amittest@gmail.com',765694258,'123','CLIENT',0,0),(13,'Janidu','ishen556@gmail.com',765694258,'123','CLIENT',0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verificationcode`
--

DROP TABLE IF EXISTS `verificationcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verificationcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `createdAt` datetime NOT NULL,
  `expiresAt` datetime NOT NULL,
  `isUsed` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `verificationcode_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationcode`
--

LOCK TABLES `verificationcode` WRITE;
/*!40000 ALTER TABLE `verificationcode` DISABLE KEYS */;
INSERT INTO `verificationcode` VALUES (7,10,'630653','2024-12-17 15:26:28','2024-12-17 15:41:28',1),(8,11,'478031','2024-12-31 18:35:51','2024-12-31 18:50:51',1),(9,12,'681683','2024-12-31 18:38:20','2024-12-31 18:53:20',0),(10,13,'619964','2024-12-31 18:40:16','2024-12-31 18:55:16',0),(11,11,'104895','2024-12-31 18:42:07','2024-12-31 18:57:07',1);
/*!40000 ALTER TABLE `verificationcode` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-01 23:33:55
