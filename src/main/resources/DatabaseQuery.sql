CREATE TABLE IF NOT EXISTS `account` (
	`accountId` INT AUTO_INCREMENT,
	`accountName` VARCHAR(30) NOT NULL,
	`accountPassword` VARCHAR(60) NOT NULL,
	`accountBirthday` DATE,
	`accountEmail` VARCHAR(30),
	`accountAddress` VARCHAR(40),
	PRIMARY KEY (`accountId`)
)

CREATE TABLE IF NOT EXISTS `accountRole` (
	`accountId` INT,
	PRIMARY KEY (`accountId`),
	CONSTRAINT `accountRole` FOREIGN KEY (`accountId`) REFERENCES `account`(`accountId`)
)
