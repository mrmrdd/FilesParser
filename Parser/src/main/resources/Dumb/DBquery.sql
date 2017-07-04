
DROP TABLE IF EXISTS `lines_table`;

CREATE TABLE `lines_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `lines_map`;

CREATE TABLE `lines_map` (
  `lines_id` bigint(20) NOT NULL,
  `map` longblob,
  `map_key` varchar(255) NOT NULL,
  PRIMARY KEY (`lines_id`,`map_key`),
  CONSTRAINT `FKs6l39iof37ea0fgidpcdc8c68` FOREIGN KEY (`lines_id`) REFERENCES `lines_table` (`id`));

DROP TABLE IF EXISTS `source_files`;

CREATE TABLE `source_files` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bytes` longblob,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `lines_table` VALUES (1);
