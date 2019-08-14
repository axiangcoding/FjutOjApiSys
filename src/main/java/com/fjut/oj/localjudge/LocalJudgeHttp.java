package com.fjut.oj.localjudge;

import com.fjut.oj.pojo.LocalJudgeSubmitInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 * @Author: axiang [20190731] 与本地评测机的通讯
 */
public class LocalJudgeHttp extends Thread {
    /**
     * 生产环境下用默认评测机
     */
    private static String LOCALJUDGEURL = "http://localhost:8100";
    /**
     * 开发环境下使用虚拟机评测机
     */
//    private static String LOCALJUDGEURL = "http://192.168.122.129:8100";

    public static String submitToLocalJudge(LocalJudgeSubmitInfo localJudgeSubmitInfo) {
        try {
            String postURL = LOCALJUDGEURL;
            PostMethod postMethod = null;
            postMethod = new PostMethod(postURL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;");
            NameValuePair[] data = new NameValuePair[]{
                    new NameValuePair("type", "submit"),
                    new NameValuePair("pid", localJudgeSubmitInfo.getPid().toString()),
                    new NameValuePair("rid", localJudgeSubmitInfo.getRid().toString()),
                    new NameValuePair("timelimit", localJudgeSubmitInfo.getTimelimit().toString()),
                    new NameValuePair("memorylimit", localJudgeSubmitInfo.getMemorylimit().toString()),
                    new NameValuePair("code", localJudgeSubmitInfo.getCode()),
                    new NameValuePair("language", localJudgeSubmitInfo.getLanguageId().toString()),
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

    public static String getResultFromLocalJudge(Integer rid) {
        try {
            String postURL = LOCALJUDGEURL;
            PostMethod postMethod = null;
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
