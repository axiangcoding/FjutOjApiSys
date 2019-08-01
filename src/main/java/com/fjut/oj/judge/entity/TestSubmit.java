package com.fjut.oj.judge.entity;

import com.fjut.oj.judge.util.Vjudge.Submitter;
import com.fjut.oj.judge.util.Vjudge.SubmitterImp;

import java.sql.Timestamp;

public class TestSubmit {

    public static void main(String[] args){
        Submitter sm = new SubmitterImp();
        String user = "cjt152";
        int pid = 1000;
        int cid = -1;
        int language = 0;
        String code = "#include<stdio.h>\nint main(){int a,b;while(~scanf(\"%d%d\",&a,&b))printf(\"%d\\n\",a+b);}";
        Timestamp submittime = new Timestamp(System.currentTimeMillis());
        //sm.doSubmit(user, pid, cid, language, code, submittime);
        //sm.doSubmit(user, pid, cid, language, code, submittime);
        // sm.doSubmit(user, pid, cid, language, code, submittime);
        //VJudge vj = new VJudge();
    }
}
