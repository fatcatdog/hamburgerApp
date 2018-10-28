DROP TABLE IF EXISTS `comment`;
CREATE TABLE  `comment` (
  `id` int(11) NOT NULL,
`author_id` int(11) NOT NULL,
  `picture_id` int(11) NOT NULL,
  `content` VARCHAR(255) NOT NULL,
  PRIMARY KEY  (`id`),
	FOREIGN KEY (picture_id) REFERENCES picture(ID)
    );
