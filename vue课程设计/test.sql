# Host: localhost  (Version 5.7.26)
# Date: 2020-06-19 15:43:26
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "l_user"
#

CREATE TABLE `l_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "l_user"
#

INSERT INTO `l_user` VALUES (1,'test','test'),(2,'11223','134234'),(4,'qwer','lll'),(6,'lu','666'),(7,'luzihao','123');

#
# Structure for table "l_userword"
#

CREATE TABLE `l_userword` (
  `userid` int(11) NOT NULL,
  `wordid` int(11) NOT NULL,
  `collection` tinyint(3) unsigned zerofill NOT NULL,
  `history` tinyint(3) unsigned zerofill NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

#
# Data for table "l_userword"
#

INSERT INTO `l_userword` VALUES (2,2,000,001);

#
# Structure for table "l_words"
#

CREATE TABLE `l_words` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) DEFAULT NULL,
  `translate` varchar(255) DEFAULT NULL,
  `exp` varchar(255) DEFAULT NULL,
  `speak` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

#
# Data for table "l_words"
#

INSERT INTO `l_words` VALUES (1,'curriculum','课程','We have to build computers into the school curriculum ','kəˈrɪkjələm'),(2,'experiment','实验','This was demonstrated in a laboratory experiment with rats','ɪkˈsperɪmənt'),(14,'apple','苹果','Give me an apple','apple');
