/* contest */
ALTER TABLE contest
    MODIFY COLUMN ctype INT(11)
        COMMENT '0：公开；1：密码；2：私有；3：注册；4：正式；5：组队';
/* contest */

/* t_gv */
RENAME TABLE t_gv TO t_kv;
/* t_gv*/

/* 新建 t_user_auth */
DROP TABLE IF EXISTS `t_user_auth`;
CREATE TABLE t_user_auth
(
    id                       INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    username                 VARCHAR(30) NOT NULL UNIQUE,
    salt                     CHAR(32)    NOT NULL,
    password                 CHAR(40)    NOT NULL,
    attempt_login_fail_count SMALLINT DEFAULT 0 COMMENT '尝试登录失败次数',
    locked                   SMALLINT DEFAULT 0 COMMENT '0为未锁定，1为锁定',
    unlock_time              DATETIME COMMENT '账号解锁时间',
    last_login_time          DATETIME,
    FOREIGN KEY (username) REFERENCES users (username)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/* 新建 t_user_auth */


/* 新建 t_bug_report */
DROP TABLE IF EXISTS `t_bug_report`;
CREATE TABLE `t_bug_report`
(
    `id`       int(11) AUTO_INCREMENT,
    `username` varchar(30) NOT NULL,
    `title`    varchar(255),
    `type`     int         NOT NULL COMMENT '0：其他 1：系统漏洞 2：功能异常 3：逻辑错误 4：界面问题 ',
    `text`     text     DEFAULT NULL,
    `time`     datetime DEFAULT NULL,
    `isFixed`  smallint COMMENT '0：未解决 1：已解决',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
/* 新建 t_bug_report */