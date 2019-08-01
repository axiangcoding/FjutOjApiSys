package com.fjut.oj.judge.util.Vjudge;

import com.fjut.oj.judge.entity.AccountConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 处理机
 * Created by Administrator on 2015/5/21.
 */
public class VJudge {

    List<VjSubmitter> listVjSubmitters=new ArrayList<VjSubmitter>();
    List<BlockingQueue<SubmitInfo>> queue = new ArrayList<BlockingQueue<SubmitInfo>>();
    BlockingQueue<SubmitInfo> localQueue = new LinkedBlockingQueue<SubmitInfo>();

    public VJudge(){//初始化

        //System.out.println("create Main!!!");
        /* 本地判题机
        for(int i = 0; i< 1; i++){
            s.add(new SubmitterLocal(0,"","",-1,this));
        }
        */

        AccountConfig accountConfig = new AccountConfig();

        String ojkeys[] = Submitter.oj_keys;
        for(int j = 0;j < ojkeys.length; j++){
            String sss = ojkeys[j];
            int size = AccountConfig.mp.get(sss).length / 2;

            System.out.println("VJudge37:" + size);

            for(int i = 0; i < size; i++){
                String username = AccountConfig.mp.get(sss)[i * 2];
                String password = AccountConfig.mp.get(sss)[i * 2 + 1];

                listVjSubmitters.add(new VjSubmitter(j, username, password, j * 10 + i,this));
                //listVjSubmitters.add(new VjSubmitter(j, "fjutvjudge", "fjutvjudge9999", j*10+i,this));
            }
            queue.add(new LinkedBlockingQueue<SubmitInfo>());
        }
        DO();
    }

    public int addSubmit(SubmitInfo info,int oj){
        try {
            queue.get(oj).put(info);
        } catch (InterruptedException e) {
            // Tool.log(e);
        }
        return 1;
    }

    public int addSubmit(SubmitInfo info){
        try {
            localQueue.put(info);
        } catch (InterruptedException e) {
            //Tool.log(e);
        }
        return 1;
    }

    private void DO(){
        for (VjSubmitter vjSubmitter : listVjSubmitters) {
            Thread t1 = new Thread(vjSubmitter);
            vjSubmitter.setSelfThread(t1);
            t1.start();
        }
    }

    public List<VjSubmitter> getSubmitters(){
        return listVjSubmitters;
    }
}
