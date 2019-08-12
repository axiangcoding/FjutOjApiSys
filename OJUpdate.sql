/* 
* add by axiang [20190703]
* 参照的旧数据库版本为2019年7月3日导出的OJInit.sql，新数据库的改动如下
*/

/* 对 contest 的修改 begin */
/* 对 contest 的修改 end */

/* 对 statu 的修改 begin */
ALTER TABLE statu
    ADD INDEX indexResult (result);
ALTER TABLE statu
    ADD INDEX indexLanguage (lang);
/* 对 statu 的修改 end */

/* 对 ChallengeBlock 的修改 begin */
/* 对 ChallengeBlock 的修改 end */


/* 对 t_challenge_condition 的修改 begin */
/* 对 t_challenge_condition 的修改 end */

/* 对 t_log 的修改 begin */
ALTER TABLE t_log
    CHANGE sessionUser ipAddress VARCHAR(30);
/* 对 t_log 的修改 end */

/* 对 t_verify 的修改 begin */
/* 对 t_verify 的修改 end */

/* 对 userper 的修改 begin */
ALTER TABLE userper
    MODIFY COLUMN perid INT(11)
        COMMENT '1：题目总管；2：查看代码；3：重判；4：新增比赛；5：计算rating；6：新增讨论；7：新增标签；
    8：签到管理；9：权限管理；10：奖励ACB；11：审核比赛报名；12：增加本地题目；13：挑战模式管理；
    14：密码重置；15：用户管理；16：查看log；17：考试管理；18：集训队员管理；19：商城管理；
    20：APP更新；21：认证管理（全部）；22：认证管理（校内人员）；23：认证管理（协会成员）；
    24：认证管理（退役人员）；25：认证管理（集训队员）；26：集训队员自动报名；27：添加题目；
    28：称号管理；29：组队管理';
/* 对 userper 的修改 end */

/* 新建表 begin*/
CREATE TABLE t_bug_report
(
    id       INT(11) AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    text     VARCHAR(255),
    time     DATETIME
);
/* 新建表 end*/

/* 对视图的修改 begin*/
DROP VIEW contestusersolve_view;

DROP VIEW v_user;

CREATE VIEW v_user_status AS
SELECT id,
       nick,
       ruser,
       pid,
       lang,
       submitTime,
       result,
       score,
       timeUsed,
       memoryUsed,
       codelen,
       code
FROM statu,
     users
WHERE statu.ruser = users.username
ORDER BY id DESC;
/* 对视图的修改 end */

