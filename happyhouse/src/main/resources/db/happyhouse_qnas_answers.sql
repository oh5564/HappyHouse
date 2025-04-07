-- #### happyhouse.qnas ####------------------------------------
use happyhouse;
create table IF NOT EXISTS `happyhouse`.`qnas` (
	`articleno` int auto_increment,
    `subject` varchar(100) not null,
    `content` varchar(2000),
    `userid` varchar(16),
    `regtime` timestamp default current_timestamp,
	PRIMARY KEY (`articleno`),
    foreign key(`userid`)
    references `happyhouse`.`ssafy_member` (`userid`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;  
    
insert into qnas(subject,content,userid) values('hey','what','ssafy');
-- ALTER TABLE qnas DROP CONSTRAINT qnas_ibfk_1;
select *
from qnas;
-- #### happyhouse.qnas end ####------------------------------------
-- #### happyhouse.answers ####------------------------------------
drop table answers;
create table IF NOT EXISTS `happyhouse`.`answers` (
    `qnano` int not null,
	`ansno` int not null auto_increment,
    `answriter` varchar(16) not null,
    `anscontent` varchar(2000),
    `isselected` boolean,
    `isrecommended` boolean,
    `upvote` int,
    `regtime` timestamp default current_timestamp,
	PRIMARY KEY (`ansno`),
    foreign key(`qnano`)
    references `happyhouse`.`qnas` (`articleno`),
    foreign key(`answriter`)
    references `happyhouse`.`ssafy_member` (`userid`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci; 

insert into answers(qnano, ansno, answriter, anscontent, isselected, isrecommended, upvote, regtime)
 values('1', '1', 'admin', '답변입니다.', false, false, 5,'2022-05-06 00:00:00');

select * 
from answers;

-- #### happyhouse.answers.end ####------------------------------------