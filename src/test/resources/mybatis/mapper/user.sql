CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `long_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;