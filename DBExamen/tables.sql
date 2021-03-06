CREATE TABLE IF NOT EXISTS `JOBS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  `SALARY` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `GENDERS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `EMPLOYEES` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `GENDER_ID` INT NOT NULL,
  `JOB_ID` INT NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  `LAST_NAME` VARCHAR(255) NOT NULL,
  `BIRTHDATE` DATE NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_EMPLOYEES_GENDERS_idx` (`GENDER_ID` ASC) VISIBLE,
  INDEX `fk_EMPLOYEES_JOBS1_idx` (`JOB_ID` ASC) VISIBLE,
  CONSTRAINT `fk_EMPLOYEES_GENDERS`
    FOREIGN KEY (`GENDER_ID`)
    REFERENCES `GENDERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EMPLOYEES_JOBS1`
    FOREIGN KEY (`JOB_ID`)
    REFERENCES `JOBS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `EMPLOYEE_WORKED_HOURS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `EMPLOYEES_ID` INT NOT NULL,
  `WORKED_HOURS` INT NOT NULL,
  `WORKED_DATE` DATE NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_EMPLOYEE_WORKED_HOURS_EMPLOYEES1_idx` (`EMPLOYEES_ID` ASC) VISIBLE,
  CONSTRAINT `fk_EMPLOYEE_WORKED_HOURS_EMPLOYEES1`
    FOREIGN KEY (`EMPLOYEES_ID`)
    REFERENCES `EMPLOYEES` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;