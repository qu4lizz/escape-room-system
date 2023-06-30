-- MariaDB dump 10.19  Distrib 10.11.4-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: escape_room
-- ------------------------------------------------------
-- Server version	10.11.4-MariaDB

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
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Admin` (
  `User_username` varchar(30) NOT NULL,
  PRIMARY KEY (`User_username`),
  KEY `fk_Admin_User_idx` (`User_username`),
  CONSTRAINT `fk_Admin_User` FOREIGN KEY (`User_username`) REFERENCES `User` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES
('admin');
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Designer`
--

DROP TABLE IF EXISTS `Designer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Designer` (
  `NonUser_email` varchar(45) NOT NULL,
  PRIMARY KEY (`NonUser_email`),
  KEY `fk_Designer_NonUser1_idx` (`NonUser_email`),
  CONSTRAINT `fk_Designer_NonUser1` FOREIGN KEY (`NonUser_email`) REFERENCES `NonUser` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Designer`
--

LOCK TABLES `Designer` WRITE;
/*!40000 ALTER TABLE `Designer` DISABLE KEYS */;
INSERT INTO `Designer` VALUES
('designer1@mail.com'),
('designer2@mail.com');
/*!40000 ALTER TABLE `Designer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Designer_has_Room`
--

DROP TABLE IF EXISTS `Designer_has_Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Designer_has_Room` (
  `Designer_NonUser_email` varchar(45) NOT NULL,
  `Room_name` varchar(30) NOT NULL,
  PRIMARY KEY (`Designer_NonUser_email`,`Room_name`),
  KEY `fk_Designer_has_Room_Room1_idx` (`Room_name`),
  KEY `fk_Designer_has_Room_Designer1_idx` (`Designer_NonUser_email`),
  CONSTRAINT `fk_Designer_has_Room_Designer1` FOREIGN KEY (`Designer_NonUser_email`) REFERENCES `Designer` (`NonUser_email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Designer_has_Room_Room1` FOREIGN KEY (`Room_name`) REFERENCES `Room` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Designer_has_Room`
--

LOCK TABLES `Designer_has_Room` WRITE;
/*!40000 ALTER TABLE `Designer_has_Room` DISABLE KEYS */;
INSERT INTO `Designer_has_Room` VALUES
('designer1@mail.com','The Lost Temple'),
('designer1@mail.com','The Secret Laboratory'),
('designer2@mail.com','The Haunted Hotel'),
('designer2@mail.com','The Mystery Mansion');
/*!40000 ALTER TABLE `Designer_has_Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Game`
--

DROP TABLE IF EXISTS `Game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Game` (
  `startDate` timestamp NOT NULL,
  `Room_name` varchar(30) NOT NULL,
  `endDate` timestamp NOT NULL,
  `score` bigint(20) NOT NULL,
  `GameMaster_User_username` varchar(30) NOT NULL,
  `price` decimal(5,2) NOT NULL,
  `Team_name` varchar(30) NOT NULL,
  PRIMARY KEY (`startDate`,`Room_name`),
  KEY `fk_Game_Room1_idx` (`Room_name`),
  KEY `fk_Game_GameMaster1_idx` (`GameMaster_User_username`),
  KEY `fk_Game_Team1_idx` (`Team_name`),
  CONSTRAINT `fk_Game_GameMaster1` FOREIGN KEY (`GameMaster_User_username`) REFERENCES `GameMaster` (`User_username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_Room1` FOREIGN KEY (`Room_name`) REFERENCES `Room` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_Team1` FOREIGN KEY (`Team_name`) REFERENCES `Team` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Game`
--

LOCK TABLES `Game` WRITE;
/*!40000 ALTER TABLE `Game` DISABLE KEYS */;
INSERT INTO `Game` VALUES
('2023-05-10 13:30:00','The Lost Temple','2023-05-10 14:22:12',3132,'gm2',90.00,'Mystic Queen'),
('2023-05-11 09:45:00','The Mystery Mansion','2023-05-11 10:54:51',4191,'gm1',120.00,'Mystic Queen'),
('2023-05-11 11:15:00','The Mystery Mansion','2023-05-11 12:33:21',4701,'gm1',80.00,'Avengers'),
('2023-05-12 09:45:00','The Lost Temple','2023-05-12 10:41:12',3372,'gm2',60.00,'Squad'),
('2023-05-12 10:15:00','The Lost Temple','2023-05-12 11:03:44',2924,'gm1',60.00,'Avengers'),
('2023-05-14 10:15:00','The Mystery Mansion','2023-05-14 11:45:00',9223372036854775807,'gm1',80.00,'Squad');
/*!40000 ALTER TABLE `Game` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`monty`@`localhost`*/ /*!50003 TRIGGER calculateScoreOnGameAdd
AFTER INSERT ON Game
FOR EACH ROW
BEGIN
    UPDATE Game
    SET score = TIMESTAMPDIFF(SECOND, NEW.startDate, NEW.endDate)
    WHERE startDate = NEW.startDate AND Room_name = NEW.Room_name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`monty`@`localhost`*/ /*!50003 TRIGGER deleteReservationsOnGameDelete
AFTER DELETE ON Game
FOR EACH ROW
BEGIN
    DELETE FROM Reservation
    WHERE startDate = OLD.startDate AND Room_name = OLD.Room_name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`monty`@`localhost`*/ /*!50003 TRIGGER deleteLogAndReviews
AFTER DELETE ON Game
FOR EACH ROW
BEGIN
    DELETE FROM GameReview
    WHERE Game_startDate = OLD.startDate
    AND Game_Room_name = OLD.Room_name;

    DELETE FROM GameLog
    WHERE Game_startDate = OLD.startDate
    AND Game_Room_name = OLD.Room_name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `GameLog`
--

DROP TABLE IF EXISTS `GameLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GameLog` (
  `log` varchar(300) NOT NULL,
  `Game_startDate` timestamp NOT NULL,
  `Game_Room_name` varchar(30) NOT NULL,
  PRIMARY KEY (`Game_startDate`,`Game_Room_name`),
  KEY `fk_GameLog_Game1_idx` (`Game_startDate`,`Game_Room_name`),
  CONSTRAINT `fk_GameLog_Game1` FOREIGN KEY (`Game_startDate`, `Game_Room_name`) REFERENCES `Game` (`startDate`, `Room_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GameLog`
--

LOCK TABLES `GameLog` WRITE;
/*!40000 ALTER TABLE `GameLog` DISABLE KEYS */;
INSERT INTO `GameLog` VALUES
('GAME FINISHED!','2023-05-10 13:30:00','The Lost Temple'),
('GAME FINISHED!','2023-05-11 09:45:00','The Mystery Mansion'),
('GAME FINISHED!','2023-05-11 11:15:00','The Mystery Mansion'),
('GAME FINISHED!','2023-05-12 09:45:00','The Lost Temple'),
('GAME FINISHED!','2023-05-12 10:15:00','The Lost Temple'),
('GAME ENDED!','2023-05-14 10:15:00','The Mystery Mansion');
/*!40000 ALTER TABLE `GameLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GameMaster`
--

DROP TABLE IF EXISTS `GameMaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GameMaster` (
  `User_username` varchar(30) NOT NULL,
  PRIMARY KEY (`User_username`),
  KEY `fk_GameMaster_User1_idx` (`User_username`),
  CONSTRAINT `fk_GameMaster_User1` FOREIGN KEY (`User_username`) REFERENCES `User` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GameMaster`
--

LOCK TABLES `GameMaster` WRITE;
/*!40000 ALTER TABLE `GameMaster` DISABLE KEYS */;
INSERT INTO `GameMaster` VALUES
('gm1'),
('gm2');
/*!40000 ALTER TABLE `GameMaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GameReview`
--

DROP TABLE IF EXISTS `GameReview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GameReview` (
  `review` varchar(200) NOT NULL,
  `Player_NonUser_email` varchar(45) NOT NULL,
  `Game_startDate` timestamp NOT NULL,
  `Game_Room_name` varchar(30) NOT NULL,
  PRIMARY KEY (`Player_NonUser_email`,`Game_startDate`,`Game_Room_name`),
  KEY `fk_GameReview_Player1_idx` (`Player_NonUser_email`),
  KEY `fk_GameReview_Game1_idx` (`Game_startDate`,`Game_Room_name`),
  CONSTRAINT `fk_GameReview_Game1` FOREIGN KEY (`Game_startDate`, `Game_Room_name`) REFERENCES `Game` (`startDate`, `Room_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_GameReview_Player1` FOREIGN KEY (`Player_NonUser_email`) REFERENCES `Player` (`NonUser_email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GameReview`
--

LOCK TABLES `GameReview` WRITE;
/*!40000 ALTER TABLE `GameReview` DISABLE KEYS */;
INSERT INTO `GameReview` VALUES
('The clues provided just the right amount of guidance without giving away the solutions. It made the victory more rewarding.','aa@mail.com','2023-05-12 09:45:00','The Lost Temple'),
('The room lacked proper maintenance. Some puzzles were malfunctioning, which took away from the overall experience.','aa@mail.com','2023-05-14 10:15:00','The Mystery Mansion'),
('Absolutely loved the escape room! The puzzles were challenging and kept us engaged throughout.','ana@mail.com','2023-05-10 13:30:00','The Lost Temple'),
('The room had a perfect balance of difficulty. It wasn\'t too easy or too hard, just the right level of challenge.','ana@mail.com','2023-05-11 09:45:00','The Mystery Mansion'),
('The room had a variety of puzzles, each unique and cleverly designed. It kept us guessing and thinking outside the box.','cc@mail.com','2023-05-11 11:15:00','The Mystery Mansion'),
('The room had a great flow, with one puzzle leading to another seamlessly. It felt like a well-crafted journey.','cc@mail.com','2023-05-12 10:15:00','The Lost Temple'),
('The props and decorations in the room were top-notch. It added to the immersive experience and made it more enjoyable.','dd@mail.com','2023-05-11 11:15:00','The Mystery Mansion'),
('The adrenaline rush we felt when we solved the final puzzle and escaped within the time limit was incredible!','dd@mail.com','2023-05-12 10:15:00','The Lost Temple'),
('The teamwork required in solving the puzzles was fantastic. It was a great bonding experience for our group.','marko@mail.com','2023-05-10 13:30:00','The Lost Temple'),
('The theme and storyline of the room were captivating. We felt like we were part of an exciting adventure.','marko@mail.com','2023-05-11 09:45:00','The Mystery Mansion'),
('The attention to detail in the room was incredible. It really felt like we were immersed in a different world.','mirko@mail.com','2023-05-10 13:30:00','The Lost Temple'),
('The staff was friendly and helpful. They provided clear instructions and made the whole experience enjoyable.','mirko@mail.com','2023-05-11 09:45:00','The Mystery Mansion');
/*!40000 ALTER TABLE `GameReview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Inventory` (
  `idInventory` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idInventory`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Inventory`
--

LOCK TABLES `Inventory` WRITE;
/*!40000 ALTER TABLE `Inventory` DISABLE KEYS */;
INSERT INTO `Inventory` VALUES
(2,'Banja Luka 1'),
(3,'Banja Luka 2');
/*!40000 ALTER TABLE `Inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lock`
--

DROP TABLE IF EXISTS `Lock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Lock` (
  `Quest_idQuest` int(11) NOT NULL,
  PRIMARY KEY (`Quest_idQuest`),
  KEY `fk_Lock_Quest1_idx` (`Quest_idQuest`),
  CONSTRAINT `fk_Lock_Quest1` FOREIGN KEY (`Quest_idQuest`) REFERENCES `Quest` (`idQuest`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lock`
--

LOCK TABLES `Lock` WRITE;
/*!40000 ALTER TABLE `Lock` DISABLE KEYS */;
INSERT INTO `Lock` VALUES
(37),
(39),
(41),
(43),
(45),
(47);
/*!40000 ALTER TABLE `Lock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NonUser`
--

DROP TABLE IF EXISTS `NonUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NonUser` (
  `email` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NonUser`
--

LOCK TABLES `NonUser` WRITE;
/*!40000 ALTER TABLE `NonUser` DISABLE KEYS */;
INSERT INTO `NonUser` VALUES
('aa@mail.com','aa'),
('ana@mail.com','ana'),
('bb@mail.com','bb'),
('cc@mail.com','cc'),
('dd@mail.com','dd'),
('designer1@mail.com','designer1'),
('designer2@mail.com','designer2'),
('marko@mail.com','marko'),
('mirko@mail.com','mirko');
/*!40000 ALTER TABLE `NonUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Player`
--

DROP TABLE IF EXISTS `Player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Player` (
  `NonUser_email` varchar(45) NOT NULL,
  PRIMARY KEY (`NonUser_email`),
  KEY `fk_Player_NonUser1_idx` (`NonUser_email`),
  CONSTRAINT `fk_Player_NonUser1` FOREIGN KEY (`NonUser_email`) REFERENCES `NonUser` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Player`
--

LOCK TABLES `Player` WRITE;
/*!40000 ALTER TABLE `Player` DISABLE KEYS */;
INSERT INTO `Player` VALUES
('aa@mail.com'),
('ana@mail.com'),
('bb@mail.com'),
('cc@mail.com'),
('dd@mail.com'),
('marko@mail.com'),
('mirko@mail.com');
/*!40000 ALTER TABLE `Player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Player_has_Team`
--

DROP TABLE IF EXISTS `Player_has_Team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Player_has_Team` (
  `Player_NonUser_email` varchar(45) NOT NULL,
  `Team_name` varchar(30) NOT NULL,
  PRIMARY KEY (`Player_NonUser_email`,`Team_name`),
  KEY `fk_Player_has_Team_Team1_idx` (`Team_name`),
  KEY `fk_Player_has_Team_Player1_idx` (`Player_NonUser_email`),
  CONSTRAINT `fk_Player_has_Team_Player1` FOREIGN KEY (`Player_NonUser_email`) REFERENCES `Player` (`NonUser_email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Player_has_Team_Team1` FOREIGN KEY (`Team_name`) REFERENCES `Team` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Player_has_Team`
--

LOCK TABLES `Player_has_Team` WRITE;
/*!40000 ALTER TABLE `Player_has_Team` DISABLE KEYS */;
INSERT INTO `Player_has_Team` VALUES
('aa@mail.com','Squad'),
('ana@mail.com','Mystic Queen'),
('bb@mail.com','Squad'),
('cc@mail.com','Avengers'),
('dd@mail.com','Avengers'),
('marko@mail.com','Mystic Queen'),
('mirko@mail.com','Mystic Queen');
/*!40000 ALTER TABLE `Player_has_Team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Puzzle`
--

DROP TABLE IF EXISTS `Puzzle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Puzzle` (
  `Quest_idQuest` int(11) NOT NULL,
  `difficulty` int(11) NOT NULL,
  PRIMARY KEY (`Quest_idQuest`),
  KEY `fk_table1_Quest1_idx` (`Quest_idQuest`),
  CONSTRAINT `fk_table1_Quest1` FOREIGN KEY (`Quest_idQuest`) REFERENCES `Quest` (`idQuest`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Puzzle`
--

LOCK TABLES `Puzzle` WRITE;
/*!40000 ALTER TABLE `Puzzle` DISABLE KEYS */;
INSERT INTO `Puzzle` VALUES
(38,3),
(40,4),
(42,1),
(44,4),
(46,2),
(48,3);
/*!40000 ALTER TABLE `Puzzle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Quest`
--

DROP TABLE IF EXISTS `Quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Quest` (
  `idQuest` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `solution` varchar(200) NOT NULL,
  `Inventory_idInventory` int(11) DEFAULT NULL,
  `Room_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idQuest`),
  KEY `fk_Quest_Inventory1_idx` (`Inventory_idInventory`),
  KEY `fk_Quest_Room1_idx` (`Room_name`),
  CONSTRAINT `fk_Quest_Inventory1` FOREIGN KEY (`Inventory_idInventory`) REFERENCES `Inventory` (`idInventory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Quest_Room1` FOREIGN KEY (`Room_name`) REFERENCES `Room` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Quest`
--

LOCK TABLES `Quest` WRITE;
/*!40000 ALTER TABLE `Quest` DISABLE KEYS */;
INSERT INTO `Quest` VALUES
(37,'The Enchanted Box','5723',NULL,'The Lost Temple'),
(38,'The Cryptic Riddles','Sphinx',NULL,'The Lost Temple'),
(39,'The Mysterious Symbols','2468',NULL,'The Lost Temple'),
(40,'The Haunted Portrait','Ghost',NULL,'The Lost Temple'),
(41,'The Hidden Hieroglyphs','12345',3,NULL),
(42,'The Puzzle Cube','Solve',3,NULL),
(43,'The Secret Code','7896',NULL,'The Mystery Mansion'),
(44,'The Mind Teasers','Brain',NULL,'The Mystery Mansion'),
(45,'The Time Machine','1875',NULL,'The Mystery Mansion'),
(46,'The Crystal Maze','Reflection',NULL,'The Mystery Mansion'),
(47,'The Puzzle Box','3921',NULL,'The Mystery Mansion'),
(48,'The Mysterious Manuscript','Cipher',NULL,'The Mystery Mansion');
/*!40000 ALTER TABLE `Quest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `startDate` timestamp NOT NULL,
  `Room_name` varchar(30) NOT NULL,
  `Team_name` varchar(30) NOT NULL,
  PRIMARY KEY (`startDate`,`Room_name`),
  KEY `fk_Reservation_Room1_idx` (`Room_name`),
  KEY `fk_Reservation_Team1_idx` (`Team_name`),
  CONSTRAINT `fk_Reservation_Room1` FOREIGN KEY (`Room_name`) REFERENCES `Room` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservation_Team1` FOREIGN KEY (`Team_name`) REFERENCES `Team` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES
('2023-05-11 11:15:00','The Mystery Mansion','Avengers'),
('2023-05-12 10:15:00','The Lost Temple','Avengers'),
('2023-06-22 11:15:00','The Haunted Hotel','Avengers'),
('2023-05-10 13:30:00','The Lost Temple','Mystic Queen'),
('2023-05-11 09:45:00','The Mystery Mansion','Mystic Queen'),
('2023-05-12 09:45:00','The Lost Temple','Squad');
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`monty`@`localhost`*/ /*!50003 TRIGGER checkReservationAvailability
BEFORE INSERT ON Reservation
FOR EACH ROW
BEGIN
    DECLARE existingReservationCount INT;
    
    SELECT COUNT(*) INTO existingReservationCount
    FROM Reservation
    WHERE Room_name = NEW.Room_name
        AND startDate < ADDTIME(NEW.startDate, (SELECT duration FROM Room WHERE name = NEW.Room_name))
        AND ADDTIME(startDate, (SELECT duration FROM Room WHERE name = NEW.Room_name)) > NEW.startDate;
    
    IF existingReservationCount > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot make reservation. The room is already reserved for the selected time slot.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
  `name` varchar(30) NOT NULL,
  `maxPlayers` int(11) NOT NULL,
  `duration` time NOT NULL,
  `price` decimal(5,2) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES
('Secret of the Lost Island',6,'01:00:00',35.00),
('The Haunted Hotel',10,'01:15:00',35.00),
('The Lost Temple',6,'01:00:00',30.00),
('The Mystery Mansion',8,'01:30:00',40.00),
('The Secret Laboratory',4,'00:45:00',25.00);
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Team`
--

DROP TABLE IF EXISTS `Team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Team` (
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Team`
--

LOCK TABLES `Team` WRITE;
/*!40000 ALTER TABLE `Team` DISABLE KEYS */;
INSERT INTO `Team` VALUES
('Avengers'),
('Mystic Queen'),
('Squad');
/*!40000 ALTER TABLE `Team` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`monty`@`localhost`*/ /*!50003 TRIGGER checkTeamNameExists
BEFORE INSERT ON Team
FOR EACH ROW
BEGIN
    DECLARE team_count INT;
    
    SELECT COUNT(*) INTO team_count
    FROM Team
    WHERE name = NEW.name;
    
    IF team_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cannot insert team. The team name already exists.';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES
('admin','admin'),
('gm1','gm1'),
('gm2','gm2');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'escape_room'
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addInventory` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `addInventory`(IN _location VARCHAR(45), OUT _id INT)
BEGIN
	INSERT INTO Inventory(location) VALUES (_location);
    SET _id = last_insert_id();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addQuest` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `addQuest`(IN _name VARCHAR(45), IN _solution VARCHAR(200), IN _inventory_id INT, OUT _id INT)
BEGIN
    INSERT INTO Quest(name, solution, Inventory_idInventory, Room_name) VALUES (_name, _solution, _inventory_id, NULL);
    SET _id = LAST_INSERT_ID();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `averageScoreForRoom` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `averageScoreForRoom`(IN _roomName VARCHAR(30), OUT _score BIGINT)
BEGIN
	SELECT AVG(score) INTO _score
    FROM Game
    WHERE Room_name=_roomName AND score<>9223372036854775807; #Long.MAX_VALUE
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `checkRoomAvailability` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `checkRoomAvailability`(
    IN checkTime TIMESTAMP,
    IN roomName VARCHAR(30),
    OUT isAvailable BOOLEAN
)
BEGIN
    DECLARE _duration TIME;
    SET isAvailable = FALSE;
    
    SELECT duration INTO _duration FROM Room WHERE name = roomName;
    
    IF EXISTS (
        SELECT * FROM Reservation
        WHERE startDate > TIMESTAMPADD(SECOND, -TIME_TO_SEC(_duration), checkTime)
          AND startDate < TIMESTAMPADD(SECOND, TIME_TO_SEC(_duration), checkTime)
          AND Room_name = roomName
    ) THEN
        SET isAvailable = FALSE;
    ELSE
        SET isAvailable = TRUE;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteQuest` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `deleteQuest`(IN _id INT)
BEGIN
	IF EXISTS (SELECT * FROM Puzzle WHERE Quest_idQuest=_id)
    THEN
		DELETE FROM Puzzle WHERE Quest_idQuest=_id;
    ELSE
		DELETE FROM `Lock` WHERE Quest_idQuest=_id;
	END IF;
	DELETE FROM Quest WHERE idQuest=_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getReservationsInNextNDays` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `getReservationsInNextNDays`(IN days INT)
BEGIN
    DECLARE currentDate DATE;
    DECLARE endDate DATE;
    
    SET currentDate = CURDATE();
    SET endDate = currentDate + INTERVAL days DAY;
    
    SELECT startDate, Room_name, Team_name
    FROM Reservation
    WHERE startDate >= currentDate AND startDate < endDate;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `login`(IN _username VARCHAR(50), IN _password VARCHAR(50), OUT _status BOOLEAN, OUT _type VARCHAR(10))
BEGIN
    SELECT COUNT(*) > 0 INTO _status
    FROM User u
    WHERE u.username = _username AND u.password = _password;
    
    IF (_status)
    THEN
        IF EXISTS (SELECT * FROM Admin WHERE User_username = _username)
        THEN
            SET _type = 'ADMIN';
        ELSE
            SET _type = 'GAMEMASTER';
        END IF;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `summarizePayments` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `summarizePayments`(out _sum DECIMAL(7,2))
BEGIN
	SELECT SUM(price) INTO _sum FROM Game;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-21 23:05:50
