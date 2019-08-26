/* contest */
ALTER TABLE contest
    MODIFY COLUMN ctype INT(11)
        COMMENT '0：公开；1：密码；2：私有；3：注册；4：正式；5：组队';
/* contest */

/* t_bug_report */

DROP TABLE IF EXISTS `t_bug_report`;

CREATE TABLE `t_bug_report`
(
    `id`       int(11) AUTO_INCREMENT,
    `username` varchar(30) NOT NULL,
    `title`    varchar(255),
    `type`     int         NOT NULL COMMENT '0：其他 1：系统漏洞 2：功能异常 3：逻辑错误 4：界面问题 ',
    `text`     varchar(255) DEFAULT NULL,
    `time`     datetime     DEFAULT NULL,
    `isFixed`  smallint,
    PRIMARY KEY (`id`)
);