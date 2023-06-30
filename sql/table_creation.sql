-- MySQL Script generated by MySQL Workbench
-- Tue 20 Jun 2023 21:41:35 CEST
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema escape_room
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema escape_room
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `escape_room` ;
USE `escape_room` ;

-- -----------------------------------------------------
-- Table `escape_room`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Room` (
  `name` VARCHAR(30) NOT NULL,
  `maxPlayers` INT NOT NULL,
  `duration` TIME NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`User` (
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(30) NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`GameMaster`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`GameMaster` (
  `User_username` VARCHAR(30) NOT NULL,
  INDEX `fk_GameMaster_User1_idx` (`User_username` ASC) VISIBLE,
  PRIMARY KEY (`User_username`),
  CONSTRAINT `fk_GameMaster_User1`
    FOREIGN KEY (`User_username`)
    REFERENCES `escape_room`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Team` (
  `name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Game` (
  `startDate` TIMESTAMP NOT NULL,
  `Room_name` VARCHAR(30) NOT NULL,
  `endDate` TIMESTAMP NOT NULL,
  `score` BIGINT NOT NULL,
  `GameMaster_User_username` VARCHAR(30) NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `Team_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`startDate`, `Room_name`),
  INDEX `fk_Game_Room1_idx` (`Room_name` ASC) VISIBLE,
  INDEX `fk_Game_GameMaster1_idx` (`GameMaster_User_username` ASC) VISIBLE,
  INDEX `fk_Game_Team1_idx` (`Team_name` ASC) VISIBLE,
  CONSTRAINT `fk_Game_Room1`
    FOREIGN KEY (`Room_name`)
    REFERENCES `escape_room`.`Room` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_GameMaster1`
    FOREIGN KEY (`GameMaster_User_username`)
    REFERENCES `escape_room`.`GameMaster` (`User_username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_Team1`
    FOREIGN KEY (`Team_name`)
    REFERENCES `escape_room`.`Team` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Admin` (
  `User_username` VARCHAR(30) NOT NULL,
  INDEX `fk_Admin_User_idx` (`User_username` ASC) VISIBLE,
  PRIMARY KEY (`User_username`),
  CONSTRAINT `fk_Admin_User`
    FOREIGN KEY (`User_username`)
    REFERENCES `escape_room`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`NonUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`NonUser` (
  `email` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Player` (
  `NonUser_email` VARCHAR(45) NOT NULL,
  INDEX `fk_Player_NonUser1_idx` (`NonUser_email` ASC) VISIBLE,
  PRIMARY KEY (`NonUser_email`),
  CONSTRAINT `fk_Player_NonUser1`
    FOREIGN KEY (`NonUser_email`)
    REFERENCES `escape_room`.`NonUser` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Designer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Designer` (
  `NonUser_email` VARCHAR(45) NOT NULL,
  INDEX `fk_Designer_NonUser1_idx` (`NonUser_email` ASC) VISIBLE,
  PRIMARY KEY (`NonUser_email`),
  CONSTRAINT `fk_Designer_NonUser1`
    FOREIGN KEY (`NonUser_email`)
    REFERENCES `escape_room`.`NonUser` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Inventory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Inventory` (
  `idInventory` INT NOT NULL AUTO_INCREMENT,
  `location` VARCHAR(45) NULL,
  PRIMARY KEY (`idInventory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Quest`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Quest` (
  `idQuest` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `solution` VARCHAR(200) NOT NULL,
  `Inventory_idInventory` INT NULL,
  `Room_name` VARCHAR(30) NULL,
  PRIMARY KEY (`idQuest`),
  INDEX `fk_Quest_Inventory1_idx` (`Inventory_idInventory` ASC) VISIBLE,
  INDEX `fk_Quest_Room1_idx` (`Room_name` ASC) VISIBLE,
  CONSTRAINT `fk_Quest_Inventory1`
    FOREIGN KEY (`Inventory_idInventory`)
    REFERENCES `escape_room`.`Inventory` (`idInventory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Quest_Room1`
    FOREIGN KEY (`Room_name`)
    REFERENCES `escape_room`.`Room` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Lock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Lock` (
  `Quest_idQuest` INT NOT NULL,
  INDEX `fk_Lock_Quest1_idx` (`Quest_idQuest` ASC) VISIBLE,
  PRIMARY KEY (`Quest_idQuest`),
  CONSTRAINT `fk_Lock_Quest1`
    FOREIGN KEY (`Quest_idQuest`)
    REFERENCES `escape_room`.`Quest` (`idQuest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Puzzle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Puzzle` (
  `Quest_idQuest` INT NOT NULL,
  `difficulty` INT NOT NULL,
  INDEX `fk_table1_Quest1_idx` (`Quest_idQuest` ASC) VISIBLE,
  PRIMARY KEY (`Quest_idQuest`),
  CONSTRAINT `fk_table1_Quest1`
    FOREIGN KEY (`Quest_idQuest`)
    REFERENCES `escape_room`.`Quest` (`idQuest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Designer_has_Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Designer_has_Room` (
  `Designer_NonUser_email` VARCHAR(45) NOT NULL,
  `Room_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Designer_NonUser_email`, `Room_name`),
  INDEX `fk_Designer_has_Room_Room1_idx` (`Room_name` ASC) VISIBLE,
  INDEX `fk_Designer_has_Room_Designer1_idx` (`Designer_NonUser_email` ASC) VISIBLE,
  CONSTRAINT `fk_Designer_has_Room_Designer1`
    FOREIGN KEY (`Designer_NonUser_email`)
    REFERENCES `escape_room`.`Designer` (`NonUser_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Designer_has_Room_Room1`
    FOREIGN KEY (`Room_name`)
    REFERENCES `escape_room`.`Room` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`GameReview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`GameReview` (
  `review` VARCHAR(100) NOT NULL,
  `Player_NonUser_email` VARCHAR(45) NOT NULL,
  `Game_startDate` TIMESTAMP NOT NULL,
  `Game_Room_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Player_NonUser_email`, `Game_startDate`, `Game_Room_name`),
  INDEX `fk_GameReview_Player1_idx` (`Player_NonUser_email` ASC) VISIBLE,
  INDEX `fk_GameReview_Game1_idx` (`Game_startDate` ASC, `Game_Room_name` ASC) VISIBLE,
  CONSTRAINT `fk_GameReview_Player1`
    FOREIGN KEY (`Player_NonUser_email`)
    REFERENCES `escape_room`.`Player` (`NonUser_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GameReview_Game1`
    FOREIGN KEY (`Game_startDate` , `Game_Room_name`)
    REFERENCES `escape_room`.`Game` (`startDate` , `Room_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Player_has_Team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Player_has_Team` (
  `Player_NonUser_email` VARCHAR(45) NOT NULL,
  `Team_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Player_NonUser_email`, `Team_name`),
  INDEX `fk_Player_has_Team_Team1_idx` (`Team_name` ASC) VISIBLE,
  INDEX `fk_Player_has_Team_Player1_idx` (`Player_NonUser_email` ASC) VISIBLE,
  CONSTRAINT `fk_Player_has_Team_Player1`
    FOREIGN KEY (`Player_NonUser_email`)
    REFERENCES `escape_room`.`Player` (`NonUser_email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Player_has_Team_Team1`
    FOREIGN KEY (`Team_name`)
    REFERENCES `escape_room`.`Team` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`Reservation` (
  `startDate` TIMESTAMP NOT NULL,
  `Room_name` VARCHAR(30) NOT NULL,
  `Team_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`startDate`, `Room_name`),
  INDEX `fk_Reservation_Room1_idx` (`Room_name` ASC) VISIBLE,
  INDEX `fk_Reservation_Team1_idx` (`Team_name` ASC) VISIBLE,
  CONSTRAINT `fk_Reservation_Room1`
    FOREIGN KEY (`Room_name`)
    REFERENCES `escape_room`.`Room` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservation_Team1`
    FOREIGN KEY (`Team_name`)
    REFERENCES `escape_room`.`Team` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `escape_room`.`GameLog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `escape_room`.`GameLog` (
  `log` VARCHAR(300) NOT NULL,
  `Game_startDate` TIMESTAMP NOT NULL,
  `Game_Room_name` VARCHAR(30) NOT NULL,
  INDEX `fk_GameLog_Game1_idx` (`Game_startDate` ASC, `Game_Room_name` ASC) VISIBLE,
  PRIMARY KEY (`Game_startDate`, `Game_Room_name`),
  CONSTRAINT `fk_GameLog_Game1`
    FOREIGN KEY (`Game_startDate` , `Game_Room_name`)
    REFERENCES `escape_room`.`Game` (`startDate` , `Room_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;