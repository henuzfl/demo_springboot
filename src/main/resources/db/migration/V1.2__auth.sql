DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`         int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `parent_id`  int(10) UNSIGNED NOT NULL COMMENT '父级id',
    `name`       varchar(63)      NOT NULL COMMENT '权限名',
    `url`        varchar(1023) DEFAULT NULL COMMENT 'url',
    `permission` varchar(127)  DEFAULT NULL COMMENT '权限表达式',
    `created_at` datetime(0)      NOT NULL COMMENT '创建时间',
    `updated_at` datetime(0)      NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) COMMENT ='权限表';


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(63)      NOT NULL COMMENT '角色名',
    `description` varchar(127) DEFAULT NULL COMMENT '描述',
    `created_at`  datetime(0)      NOT NULL COMMENT '创建时间',
    `updated_at`  datetime(0)      NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) COMMENT ='角色表';

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `sys_role_id`       int(10) UNSIGNED NOT NULL COMMENT '角色主键',
    `sys_permission_id` int(10) UNSIGNED NOT NULL COMMENT '权限主键',
    PRIMARY KEY (`sys_role_id`, `sys_permission_id`)
) COMMENT ='角色权限关系表';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`         int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`       varchar(63)      NOT NULL COMMENT '用户名',
    `password`   varchar(63)      NOT NULL COMMENT '密码',
    `created_at` datetime(0)      NOT NULL COMMENT '创建时间',
    `updated_at` datetime(0)      NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`)
) COMMENT ='用户表';


DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `sys_user_id` int(10) UNSIGNED NOT NULL COMMENT '用户主键',
    `sys_role_id` int(10) UNSIGNED NOT NULL COMMENT '角色主键',
    PRIMARY KEY (`sys_user_id`, `sys_role_id`)
) COMMENT ='用户角色关系表';

INSERT INTO `sys_role`
VALUES (1, 'admin', '管理员', '2022-05-11 13:56:28', '2022-05-11 13:56:30');
INSERT INTO `sys_role`
VALUES (2, 'user', '普通用户', '2022-05-11 13:56:41', '2022-05-11 13:56:43');
INSERT INTO `sys_role`
VALUES (3, 'guest', '访客', '2022-05-11 13:56:58', '2022-05-11 13:56:59');

INSERT INTO `sys_user`
VALUES (1, 'admin', '$2a$10$LTqKTXXRb26SYG3MvFG1UuKhMgc/i6IbUl2RgApiWd39y1EqlXbD6', '2022-05-11 14:03:04',
        '2022-05-11 14:03:06');

INSERT INTO `sys_user_role`
VALUES (1, 1);
