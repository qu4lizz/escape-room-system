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



-- Dump completed on 2023-06-21 23:05:50
