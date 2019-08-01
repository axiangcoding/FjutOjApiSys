package com.fjut.oj.judge.util.Vjudge;

import com.fjut.oj.judge.entity.OJ.BNUOJ.BNUOJ;
import com.fjut.oj.judge.entity.OJ.CF.CF;
import com.fjut.oj.judge.entity.OJ.CodeVS.CodeVS;
import com.fjut.oj.judge.entity.OJ.HDU.HDU;
import com.fjut.oj.judge.entity.OJ.HUST.HUST;
import com.fjut.oj.judge.entity.OJ.NBUT.NBUT;
import com.fjut.oj.judge.entity.OJ.OTHOJ;
import com.fjut.oj.judge.entity.OJ.PKU.PKU;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
/**
 * Created by Administrator on 2015/12/14 0014.
 */
@Controller
public interface Submitter {
    OTHOJ[] ojs ={
            new HDU(),
            new BNUOJ(),
            new NBUT(),
            new PKU(),
            new HUST(),
            new CF(),
            new CodeVS(),/*
            new JudgeSystem(),
            new Acdream(),
            new JudgeSystem(),
            new CFGym(),
            new FOJ(),
            new DistributedJudgeSystem(),
            new BZOJ(),
            new ZOJ(),
            new SPOJ(),*/
    };

    String oj_keys[] = {"hdu","foj","nbut","pku","hust","cf","codevs",
            /*"judge_system","acdream","judge_system_game", "cf_gym",
            "foj", "new_judge","bzoj","zoj","spoj"*/};
    //OJ列表。判题OJ顺序不能改变，否则导致已有题目的OJ不正确

    VJudge m = new VJudge();

    int doSubmit(String user, int pid, int cid, int language, String code, Timestamp submittime);
    int reJudge(int rid);
}
