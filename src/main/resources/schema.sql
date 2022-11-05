/* ** Root ** */
DROP DATABASE IF EXISTS `profilemanager`;
CREATE DATABASE `profilemanager`;

DROP USER IF EXISTS 'profilemanager'@'%';
CREATE USER 'profilemanager'@'%' IDENTIFIED WITH mysql_native_password BY 'profilemanager';
GRANT ALL PRIVILEGES ON *.* TO 'profilemanager'@'%' WITH GRANT OPTION;

/* ** Application User ** */
USE profilemanager;

DROP TABLE IF EXISTS `profile`;

CREATE TABLE `profile` (
	`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(255) NOT NULL
);

INSERT INTO `profile` (`id`, `name`) VALUES (1,	'nandha'), (2,	'kumar'), (3,	'viswa');

DROP TABLE IF EXISTS `profile_detail`;

CREATE TABLE `profile_detail` (
	`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`profileId` int NOT NULL,
	`otherDetails` varchar(255) NOT NULL,
	FOREIGN KEY (profileId) REFERENCES profile(id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO `profile_detail` (`profileId`, `otherDetails`) VALUES (1, 'nandha detail 1'), (1, 'nandha detail 2'), (3, 'viswa detail 1');