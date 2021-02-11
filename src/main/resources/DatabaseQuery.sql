DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
	`accountId` INT AUTO_INCREMENT,
	`accountName` VARCHAR(64) NOT NULL,
	`accountPassword` VARCHAR(64) NOT NULL,
	`accountBirthday` DATE,
	`accountRegisterDate` DATE,
	`accountEmail` VARCHAR(32),
	`accountAddress` VARCHAR(64),
	`accountAvatar` VARCHAR(255),
	PRIMARY KEY (`accountId`)
)

DROP TABLE IF EXISTS `accountRole`;
CREATE TABLE IF NOT EXISTS `accountRole` (
	`accountId` INT,
	`accountRole` VARCHAR(10),
	`roleName` VARCHAR(20),
	PRIMARY KEY (`accountId`),
	CONSTRAINT `accountRole` FOREIGN KEY (`accountId`) REFERENCES `account`(`accountId`)
)

DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE IF NOT EXISTS `persistent_logins`(
	`username` VARCHAR(64),
	`series` VARCHAR(64),
	`Token` VARCHAR(64),
	`last_used` DATETIME
)

DROP TABLE IF EXISTS `community`;
CREATE TABLE IF NOT EXISTS 	`community`(
	`communityId` INT NOT NULL AUTO_INCREMENT,
	`communityName` VARCHAR(64) NOT NULL,
	`communityDescription` VARCHAR(255),
	`communityAvatar` VARCHAR(255),
	PRIMARY KEY (`communityId`)
)

INSERT INTO `community` VALUES (1, 'CPP', '世界上最好、最难用的编程语言', '/images/community/cpp_community.png');
INSERT INTO `community` VALUES (2, 'JAVA', '一种编程语言', '/images/community/java_community.png');
INSERT INTO `community` VALUES (3, 'Python', '一种编程语言', '/images/community/python_community.png');

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS 	`post`(
	`postId` INT NOT NULL AUTO_INCREMENT,
	`communityId` INT NOT NULL,
	`accountId` INT NOT NULL,
	`postTitle` VARCHAR(64) NOT NULL,
	`postTime` DATE NOT NULL,
	PRIMARY KEY (`postId`)
)
INSERT INTO `post` VALUES (1,1,8, '为什么C++是世界上最好的语言？','2021-02-19 01:38:17' );
INSERT INTO `post` VALUES (2,2,9, '为什么Java是世界上最好的语言？','2021-02-19 01:45:17' );
INSERT INTO `post` VALUES (3,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:18');
INSERT INTO `post` VALUES (4,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:19');
INSERT INTO `post` VALUES (5,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:20');
INSERT INTO `post` VALUES (6,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:21');
INSERT INTO `post` VALUES (7,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:22');
INSERT INTO `post` VALUES (8,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:23');
INSERT INTO `post` VALUES (9,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:24');
INSERT INTO `post` VALUES (10,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:25');
INSERT INTO `post` VALUES (11,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:26');
INSERT INTO `post` VALUES (12,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:27');
INSERT INTO `post` VALUES (13,3,9, '为什么Python是世界上最好的语言？','2021-02-19 01:45:28');

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS 	`comment`(
	`commentId` INT NOT NULL AUTO_INCREMENT,
	`postId` INT NOT NULL,
	`accountId` INT NOT NULL,
	`commentTime` DATETIME NOT NULL,
	`commentText` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`commentId`)
)
INSERT INTO `comment` VALUES (1,1,8, '2021-02-19 01:40:17','说得好！');
INSERT INTO `comment` VALUES (2,2,9, '2021-02-19 01:56:17','说得好！');
INSERT INTO `comment` VALUES (3,2,10, '2021-02-19 01:58:17','说得好！');
INSERT INTO `comment` VALUES (4,2,10, '2021-02-19 01:58:18','说得好！');
INSERT INTO `comment` VALUES (5,2,10, '2021-02-19 01:58:19','说得好！');
INSERT INTO `comment` VALUES (6,2,10, '2021-02-19 01:58:20','说得好！');
INSERT INTO `comment` VALUES (7,2,10, '2021-02-19 01:58:21','说得好！');
INSERT INTO `comment` VALUES (8,2,10, '2021-02-19 01:58:22','说得好！');
INSERT INTO `comment` VALUES (9,2,10, '2021-02-19 01:58:23','说得好！');
INSERT INTO `comment` VALUES (10,2,10, '2021-02-19 01:58:24','说得好！');
INSERT INTO `comment` VALUES (11,2,10, '2021-02-19 01:58:25','说得好！');
INSERT INTO `comment` VALUES (12,2,10, '2021-02-19 01:58:26','说得好！');
INSERT INTO `comment` VALUES (13,2,10, '2021-02-19 01:58:27','说得好！');
INSERT INTO `comment` VALUES (14,2,10, '2021-02-19 01:58:28','说得好！');
INSERT INTO `comment` VALUES (15,2,10, '2021-02-19 01:58:29','说得好！');
INSERT INTO `comment` VALUES (16,2,10, '2021-02-19 01:58:30','说得好！');