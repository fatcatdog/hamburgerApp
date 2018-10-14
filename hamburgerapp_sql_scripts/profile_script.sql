DROP TABLE IF EXISTS `profile`;
CREATE TABLE  `profile` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(255),
	`favorite_food` varchar(255),
  `favorite_food_brand` varchar(255),
	`profile_picture` varchar(255),  
  PRIMARY KEY  (`id`),
    CONSTRAINT `profile` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ;
