/* contest */
    ALTER TABLE contest
    MODIFY COLUMN ctype INT(11)
        COMMENT '0：公开；1：密码；2：私有；3：注册；4：正式；5：组队';
/* contest */