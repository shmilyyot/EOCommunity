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

INSERT INTO `community` VALUES (1, '蝙蝠侠', '布鲁斯·韦恩（Bruce Wayne）即蝙蝠侠（Batman），是美国DC漫画旗下的超级英雄', '/images/community/batman_community.jpg');
INSERT INTO `community` VALUES (2, '超人', '超人（Superman）是美国DC漫画旗下的超级英雄，美国漫画史上第一个超级英雄', '/images/community/superman_community.jpg');
INSERT INTO `community` VALUES (3, '神奇女侠', '戴安娜·普林斯（Diana Prince）即神奇女侠（Wonder Woman），是美国DC漫画旗下的超级英雄', '/images/community/wonderwomen_community.jpg');
INSERT INTO `community` VALUES (4, '疑难解答', '可以在此处反馈bug或提出建议，以及提出疑问', '/images/community/question_community.png');

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS 	`post`(
	`postId` INT NOT NULL AUTO_INCREMENT,
	`communityId` INT NOT NULL,
	`accountId` INT NOT NULL,
	`postTitle` VARCHAR(64) NOT NULL,
	`postTime` DATETIME(6) NOT NULL,
	PRIMARY KEY (`postId`)
)

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS 	`comment`(
	`commentId` INT NOT NULL AUTO_INCREMENT,
	`postId` INT NOT NULL,
	`accountId` INT NOT NULL,
	`commentTime` DATETIME(6) NOT NULL,
	`commentText` TEXT NOT NULL,
	`commentTo` INT NOT NULL,
	`commentStatus` TINYINT NOT NULL,
	`commentFloor` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`commentId`)
)


DROP TABLE IF EXISTS `favPost`;
CREATE TABLE IF NOT EXISTS 	`favPost`(
	`favId` INT NOT NULL AUTO_INCREMENT,
	`accountId` INT NOT NULL,
	`url` VARCHAR(255) NOT NULL,
	`title` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`favId`)
)