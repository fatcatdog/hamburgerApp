DROP TABLE IF EXISTS `picture`;
CREATE TABLE  `picture` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `pic_url` varchar(255) NOT NULL,
  `pic_name` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
      `brand` varchar(255) NOT NULL,
      `extension` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`),
    CONSTRAINT `picture` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ;
