DROP TABLE IF EXISTS `upvote`;
CREATE TABLE `upvote` (
  `id` int(11) NOT NULL,
  `author_id` int(11) NOT NULL,
  `picture_id` int(11) NOT NULL,

  PRIMARY KEY  (`id`),
    CONSTRAINT `author` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
    CONSTRAINT `pic` FOREIGN KEY (`picture_id`) REFERENCES `picture` (`id`)
);
