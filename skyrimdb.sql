-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema skyrimdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema skyrimdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `skyrimdb` ;
USE `skyrimdb` ;

-- -----------------------------------------------------
-- Table `skyrimdb`.`Razas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `skyrimdb`.`Razas` (
  `idRazas` INT NOT NULL AUTO_INCREMENT,
  `nomRazas` VARCHAR(45) NOT NULL,
  `ventaja` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idRazas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skyrimdb`.`Jugador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `skyrimdb`.`Jugador` (
  `idJugador` INT NOT NULL AUTO_INCREMENT,
  `nomJugador` VARCHAR(45) NOT NULL,
  `Razas_idRazas` INT NOT NULL,
  PRIMARY KEY (`idJugador`),
  INDEX `fk_Jugador_Razas_idx` (`Razas_idRazas` ASC) VISIBLE,
  UNIQUE INDEX `idJugador_UNIQUE` (`idJugador` ASC) VISIBLE,
  CONSTRAINT `fk_Jugador_Razas`
    FOREIGN KEY (`Razas_idRazas`)
    REFERENCES `skyrimdb`.`Razas` (`idRazas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skyrimdb`.`Facciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `skyrimdb`.`Facciones` (
  `idFacciones` INT NOT NULL AUTO_INCREMENT,
  `nomFacciones` VARCHAR(45) NOT NULL,
  `LiderFaccion` VARCHAR(45) NOT NULL,
  `Jugador_idJugador` INT NOT NULL,
  PRIMARY KEY (`idFacciones`),
  INDEX `fk_Facciones_Jugador1_idx` (`Jugador_idJugador` ASC) VISIBLE,
  UNIQUE INDEX `idFacciones_UNIQUE` (`idFacciones` ASC) VISIBLE,
  CONSTRAINT `fk_Facciones_Jugador1`
    FOREIGN KEY (`Jugador_idJugador`)
    REFERENCES `skyrimdb`.`Jugador` (`idJugador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skyrimdb`.`Clases`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `skyrimdb`.`Clases` (
  `idClases` INT NOT NULL AUTO_INCREMENT,
  `NomClase` VARCHAR(45) NOT NULL,
  `Controversia` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idClases`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skyrimdb`.`Especialidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `skyrimdb`.`Especialidad` (
  `Razas_idRazas` INT NOT NULL,
  `Clases_idClases` INT NOT NULL,
  PRIMARY KEY (`Razas_idRazas`, `Clases_idClases`),
  INDEX `fk_Razas_has_Clases_Clases1_idx` (`Clases_idClases` ASC) VISIBLE,
  INDEX `fk_Razas_has_Clases_Razas1_idx` (`Razas_idRazas` ASC) VISIBLE,
  CONSTRAINT `fk_Razas_has_Clases_Razas1`
    FOREIGN KEY (`Razas_idRazas`)
    REFERENCES `skyrimdb`.`Razas` (`idRazas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Razas_has_Clases_Clases1`
    FOREIGN KEY (`Clases_idClases`)
    REFERENCES `skyrimdb`.`Clases` (`idClases`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
