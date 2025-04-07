-- -----------------------------------------------------
-- Table `happyhouse`.`hospitals`
-- -----------------------------------------------------
drop table if exists `happyhouse`.`covidhospital`;
CREATE TABLE IF NOT EXISTS `happyhouse`.`covidhospital` (
  `hospitalname` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `lat` FLOAT NOT NULL,
  `lng` FLOAT NOT NULL,
  `israt` VARCHAR(45) NOT NULL,
  `isclinic` VARCHAR(45) NOT NULL, 
  `ispcr` VARCHAR(45) NOT NULL, 
  `sigungu` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`hospitalname`))
ENGINE = InnoDB;


alter table hospitals rename covidhospital;

select * from covidhospital;

select hospitalname, phone, address, isclinic, ispcr, sigungu
from covidhospital;

