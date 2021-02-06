CREATE TABLE IF NOT EXISTS `account` (
	`accountId` INT AUTO_INCREMENT,
	`accountName` VARCHAR(64) NOT NULL,
	`accountPassword` VARCHAR(64) NOT NULL,
	`accountBirthday` DATE,
	`accountRegisterDate` DATE,
	`accountEmail` VARCHAR(32),
	`accountAddress` VARCHAR(64),
	PRIMARY KEY (`accountId`)
)

CREATE TABLE IF NOT EXISTS `accountRole` (
	`accountId` INT,
	`accountRole` VARCHAR(10),
	`roleName` VARCHAR(20),
	PRIMARY KEY (`accountId`),
	CONSTRAINT `accountRole` FOREIGN KEY (`accountId`) REFERENCES `account`(`accountId`)
)

CREATE TABLE IF NOT EXISTS `persistent_logins`(
	`username` VARCHAR(64),
	`series` VARCHAR(64),
	`Token` VARCHAR(64),
	`last_used` DATETIME
)

CREATE TABLE IF NOT EXISTS `accountface`(
	`accountId` INT,
	`accountFace` BLOB,
	PRIMARY KEY (`accountId`),
	CONSTRAINT `accountface` FOREIGN KEY (`accountId`) REFERENCES `account`(`accountId`)
)
