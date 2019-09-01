package com.fjut.oj.localjudge;

import com.fjut.oj.pojo.LocalJudgeSubmitInfoPO;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author axiang [20190731] 与本地评测机的通讯
 */
@Component
public class LocalJudgeHttpClient extends Thread {

    @Value("${oj.localJudgePath}")
    private String localJudgePath;

    public String submitToLocalJudge(LocalJudgeSubmitInfoPO localJudgeSubmitInfoBO) {
        try {
            String postURL = localJudgePath;
            PostMethod postMethod;
            postMethod = new PostMethod(postURL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
            NameValuePair[] data = new NameValuePair[]{
                    new NameValuePair("type", "submit"),
                    new NameValuePair("pid", localJudgeSubmitInfoBO.getPid().toString()),
                    new NameValuePair("rid", localJudgeSubmitInfoBO.getRid().toString()),
                    new NameValuePair("timelimit", localJudgeSubmitInfoBO.getTimeLimit().toString()),
                    new NameValuePair("memorylimit", localJudgeSubmitInfoBO.getMemoryLimit().toString()),
                    new NameValuePair("code", localJudgeSubmitInfoBO.getCode()),
                    new NameValuePair("language", localJudgeSubmitInfoBO.getLanguageId().toString()),
            };
            postMethod.setRequestBody(data);
            HttpClient httpClient = new HttpClient();
            // 执行POST方法
            int response = httpClient.executeMethod(postMethod);
            String result = postMethod.getResponseBodyAsString();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getResultFromLocalJudge(Integer rid) {
        try {
            String postURL = localJudgePath;
            PostMethod postMethod;
            postMethod = new PostMethod(postURL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
            NameValuePair[] data = new NameValuePair[]{
                    new NameValuePair("type", "getResult"),
                    new NameValuePair("rid", rid.toString() )
            };
            postMethod.setRequestBody(data);
            HttpClient httpClient = new HttpClient();
            // 执行POST方法
            int response = httpClient.executeMethod(postMethod);
            String result = postMethod.getResponseBodyAsString();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }
}
