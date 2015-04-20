-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema game
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema game
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `game` DEFAULT CHARACTER SET utf8 ;
USE `game` ;

-- -----------------------------------------------------
-- Table `game`.`hero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`hero` (
  `idHero` INT(11) NOT NULL,
  `heroName` VARCHAR(45) NULL DEFAULT NULL,
  `heroType` INT(11) NULL DEFAULT NULL,
  `userID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idHero`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`login` (
  `userID` INT(11) NOT NULL,
  `userName` VARCHAR(45) NULL DEFAULT NULL,
  `userPassword` VARCHAR(45) NULL DEFAULT NULL,
  `userEmail` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `userID_UNIQUE` (`userID` ASC),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
