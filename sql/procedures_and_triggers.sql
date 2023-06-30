--
-- Dumping routines for database 'escape_room'
--

DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `addInventory`(IN _location VARCHAR(45), OUT _id INT)
BEGIN
	INSERT INTO Inventory(location) VALUES (_location);
    SET _id = last_insert_id();
END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `addQuest`(IN _name VARCHAR(45), IN _solution VARCHAR(200), IN _inventory_id INT, OUT _id INT)
BEGIN
    INSERT INTO Quest(name, solution, Inventory_idInventory, Room_name) VALUES (_name, _solution, _inventory_id, NULL);
    SET _id = LAST_INSERT_ID();
END ;;
DELIMITER ;


DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `averageScoreForRoom`(IN _roomName VARCHAR(30), OUT _score BIGINT)
BEGIN
	SELECT AVG(score) INTO _score
    FROM Game
    WHERE Room_name=_roomName AND score<>9223372036854775807; #Long.MAX_VALUE
END ;;
DELIMITER ;


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


DELIMITER ;;
CREATE DEFINER=`monty`@`localhost` PROCEDURE `summarizePayments`(out _sum DECIMAL(7,2))
BEGIN
	SELECT SUM(price) INTO _sum FROM Game;
END ;;
DELIMITER ;




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


DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`monty`@`localhost`*/ /*!50003 TRIGGER deleteReservationsOnGameDelete
AFTER DELETE ON Game
FOR EACH ROW
BEGIN
    DELETE FROM Reservation
    WHERE startDate = OLD.startDate AND Room_name = OLD.Room_name;
END */;;
DELIMITER ;


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