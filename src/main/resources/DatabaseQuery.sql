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