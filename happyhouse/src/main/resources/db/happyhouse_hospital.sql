-- -----------------------------------------------------
-- Table `happyhouse`.`hospital`
-- -----------------------------------------------------
drop table if exists `happyhouse`.`hospital`;
CREATE TABLE IF NOT EXISTS `happyhouse`.`hospital` (
  `hospitalname` VARCHAR(45) NOT NULL,
  `codename` VARCHAR(45) DEFAULT NULL,
  `dongname` VARCHAR(45) DEFAULT NULL,
  `address` VARCHAR(45) DEFAULT NULL,
  `phone` VARCHAR(45) DEFAULT NULL,
  `homepage` VARCHAR(45) DEFAULT NULL,
  `xloc` FLOAT DEFAULT NULL,
  `yloc` FLOAT DEFAULT NULL,
  PRIMARY KEY (`hospitalname`))
  ENGINE = InnoDB;
  
  select * 
  from hospital;
  