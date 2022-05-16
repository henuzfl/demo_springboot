CREATE TABLE IF NOT EXISTS `account`
(
    `id`         int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `sys_uer_id` int(10) UNSIGNED NOT NULL,
    `money`      bigint           NOT NULL,
    `created_at` datetime         NOT NULL,
    `updated_at` datetime         NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `stock`
(
    `id`         int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`       varchar(63)      NOT NULL,
    `count`      int(11)          NOT NULL,
    `price`      int(11)          NOT NULL,
    `created_at` datetime         NOT NULL,
    `updated_at` datetime         NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `order_tbl`
(
    `id`         int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_id` int(10) UNSIGNED NOT NULL,
    `stock_id`   int(10) UNSIGNED NOT NULL,
    `count`      int(11)          NOT NULL,
    `money`      int(11)          NOT NULL,
    `created_at` datetime         NOT NULL,
    `updated_at` datetime         NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `account`
VALUES (1, 1, 100000, '2022-05-16 16:04:11', '2022-05-16 16:04:14');

INSERT INTO `stock`
VALUES (1, '香烟', 25, 1000, '2022-05-16 16:04:44', '2022-05-16 16:04:47');
INSERT INTO `stock`
VALUES (2, '啤酒', 50, 1500, '2022-05-16 16:05:15', '2022-05-16 16:05:18');
INSERT INTO `stock`
VALUES (3, '瓜子', 15, 500, '2022-05-16 16:05:35', '2022-05-16 16:05:38');