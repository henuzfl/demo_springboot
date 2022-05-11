CREATE TABLE IF NOT EXISTS `user`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `name`       varchar(255) NOT NULL,
    `age`        int(2)       NOT NULL,
    `created_at` datetime     NOT NULL,
    `updated_at` datetime     NOT NULL,
    PRIMARY KEY (`id`)
    );


insert into user (name, age, created_at, updated_at)
values ('John', 20, now(), now());