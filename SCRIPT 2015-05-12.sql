-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema game
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema game
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `game` DEFAULT CHARACTER SET utf8 ;
USE `game` ;

-- -----------------------------------------------------
-- Table `game`.`enemy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`enemy` (
  `enemyID` INT(11) NOT NULL AUTO_INCREMENT,
  `enemyName` VARCHAR(45) NOT NULL,
  `enemyBaseHP` INT(11) NOT NULL,
  `enemyBaseMinDamage` INT(11) NOT NULL,
  `enemyBaseMaxDamage` INT(11) NOT NULL,
  `enemyBaseSpeed` INT(11) NOT NULL,
  PRIMARY KEY (`enemyID`),
  UNIQUE INDEX `enemyID_UNIQUE` (`enemyID` ASC),
  UNIQUE INDEX `enemyName_UNIQUE` (`enemyName` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`login` (
  `userID` INT(11) NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NULL DEFAULT NULL,
  `userPassword` VARCHAR(45) NULL DEFAULT NULL,
  `userQuestion` VARCHAR(100) NULL DEFAULT NULL,
  `userAnswer` VARCHAR(20) NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `userID_UNIQUE` (`userID` ASC),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game`.`hero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`hero` (
  `idHero` INT(11) NOT NULL AUTO_INCREMENT,
  `heroName` VARCHAR(45) NOT NULL,
  `heroType` INT(11) NOT NULL,
  `userID` INT(11) NOT NULL,
  `heroLevel` INT(11) NOT NULL,
  `heroGold` INT(11) NOT NULL,
  `heroCurrentHP` INT(4) NOT NULL,
  `heroEXP` INT(11) NOT NULL,
  `heroBaseHP` INT(11) NOT NULL,
  `heroBaseSpeed` INT(11) NOT NULL,
  `heroBaseDamage` INT(11) NOT NULL,
  PRIMARY KEY (`idHero`),
  UNIQUE INDEX `idHero_UNIQUE` (`idHero` ASC),
  UNIQUE INDEX `heroName_UNIQUE` (`heroName` ASC),
  INDEX `userID_idx` (`userID` ASC),
  CONSTRAINT `userID`
    FOREIGN KEY (`userID`)
    REFERENCES `game`.`login` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game`.`weapon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`weapon` (
  `weaponID` INT NOT NULL AUTO_INCREMENT,
  `weaponName` VARCHAR(45) NOT NULL,
  `weaponMinDamage` INT NOT NULL,
  `weaponMaxDamage` INT NOT NULL,
  `weaponSpeed` INT NOT NULL,
  `weaponLevel` INT NOT NULL,
  `weaponType` INT NOT NULL,
  `weaponGold` INT NOT NULL,
  PRIMARY KEY (`weaponID`),
  UNIQUE INDEX `weaponID_UNIQUE` (`weaponID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`.`armor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`armor` (
  `armorID` INT NOT NULL AUTO_INCREMENT,
  `armor` INT NOT NULL,
  `armorType` INT NOT NULL,
  `armorLevel` INT NOT NULL,
  `armorSpeed` INT NOT NULL,
  `armorName` VARCHAR(45) NOT NULL,
  `armorGold` INT NOT NULL,
  PRIMARY KEY (`armorID`),
  UNIQUE INDEX `armorID_UNIQUE` (`armorID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`.`hero_has_weapon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`hero_has_weapon` (
  `hero_idHero` INT(11) NOT NULL,
  `weapon_weaponID` INT NOT NULL,
  PRIMARY KEY (`hero_idHero`, `weapon_weaponID`),
  INDEX `fk_hero_has_weapon_weapon1_idx` (`weapon_weaponID` ASC),
  INDEX `fk_hero_has_weapon_hero1_idx` (`hero_idHero` ASC),
  CONSTRAINT `fk_hero_has_weapon_hero1`
    FOREIGN KEY (`hero_idHero`)
    REFERENCES `game`.`hero` (`idHero`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hero_has_weapon_weapon1`
    FOREIGN KEY (`weapon_weaponID`)
    REFERENCES `game`.`weapon` (`weaponID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `game`.`hero_has_armor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `game`.`hero_has_armor` (
  `hero_idHero` INT(11) NOT NULL,
  `armor_armorID` INT NOT NULL,
  PRIMARY KEY (`hero_idHero`, `armor_armorID`),
  INDEX `fk_hero_has_armor_armor1_idx` (`armor_armorID` ASC),
  INDEX `fk_hero_has_armor_hero1_idx` (`hero_idHero` ASC),
  CONSTRAINT `fk_hero_has_armor_hero1`
    FOREIGN KEY (`hero_idHero`)
    REFERENCES `game`.`hero` (`idHero`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hero_has_armor_armor1`
    FOREIGN KEY (`armor_armorID`)
    REFERENCES `game`.`armor` (`armorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
