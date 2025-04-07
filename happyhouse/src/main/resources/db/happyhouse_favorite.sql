use happyhouse;
-- -----------------------------------------------------
-- Table `happyhouse`.`favorites`
-- -----------------------------------------------------
drop table if exists `happyhouse`.`favorites`;
CREATE TABLE IF NOT EXISTS `happyhouse`.`favorites` (
  `fvid` INT NOT NULL AUTO_INCREMENT,
  `fvuserid` VARCHAR(16) NOT NULL,
  `fvitem` INT NOT NULL,
   PRIMARY KEY (`fvid`),
  FOREIGN KEY(`fvuserid`)
  REFERENCES ssafy_member(userid) ON UPDATE CASCADE,
  FOREIGN KEY(`fvitem`)
  REFERENCES housedeal(aptCode) ON UPDATE CASCADE)
ENGINE = InnoDB ENGINE=InnoDB AUTO_INCREMENT=68865 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- tests ------------------------------------------------ 
select *
from houseinfo;
show full columns from houseinfo;

select * 
from housedeal;
show full columns from housedeal;

select *
from ssafy_member;
show full columns from ssafy_member;
show table status;

SET FOREIGN_KEY_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 1; 