/* 题目难度表 */
CREATE TABLE `t_problem_difficult`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT,
    `pid`           int(11)     DEFAULT NULL,
    `difficultType` smallint(6) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `pid` (`pid`),
    CONSTRAINT `t_problem_difficult_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `problem` (`pid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

/* 讨论表*/
DROP TABLE t_discuss;

CREATE TABLE `t_discuss`
(
    id       int(11) PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(50),
    time     DATETIME,
    author   VARCHAR(30),
    priority DOUBLE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

/* 讨论回复表 */
DROP TABLE t_discussreply;

CREATE TABLE `t_discuss_reply`
(
    id         INT(11) PRIMARY KEY AUTO_INCREMENT,
    discuss_id INT(11)     NOT NULL,
    reply_id   INT(11),
    time       DATETIME,
    author     VARCHAR(30) NOT NULL,
    text       TEXT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;


/* 简易题目难度认定SQL语句 */
# INSERT INTO t_problem_difficult (pid, difficultType)
#     (SELECT pid,
#             (
#                 if(totalAc / totalSubmit > 0.4, 0,
#                    (IF(totalAc / totalSubmit > 0.2, 1, 2))))
#      FROM problem)
