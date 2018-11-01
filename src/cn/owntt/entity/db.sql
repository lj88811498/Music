CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `author` varchar(64) default null,
  `mSize` varchar(8) default null,
  `url` varchar(255) default null,
  `createTime` datetime default now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

