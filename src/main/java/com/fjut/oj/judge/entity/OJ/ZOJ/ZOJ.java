package com.fjut.oj.judge.entity.OJ.ZOJ;

import com.fjut.oj.judge.entity.OJ.CodeLanguage;
import com.fjut.oj.judge.entity.OJ.OTHOJ;
import com.fjut.oj.judge.entity.RES;
import com.fjut.oj.judge.entity.Result;
import com.fjut.oj.judge.util.HTML.problemHTML;
import com.fjut.oj.judge.util.MyClient;
import com.fjut.oj.judge.util.Pair;
import com.fjut.oj.judge.util.Vjudge.VjSubmitter;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZOJ extends OTHOJ{
    public static Map<String,Result> ResultMap;
    public static String URL = "http://acm.zju.edu.cn";//"http://acm.zju.edu.cn";
    @Override
    public String getRid(String user, VjSubmitter s) {
        Document d = s.client.get(URL+"/onlinejudge"+"/showRuns.do?contestId=1&handle="+s.getUsername());

        Elements e = d.select(".list").select("tr");

        if(e == null || e.size() < 1){
            return "0";
        }else{
            Elements tds = e.get(1).select("td");
            return tds.get(0).text();
        }
    }

    @Override
    public problemHTML getProblemHTML(String pid) {
        Document d = MyClient.getMyClient().get(getProblemURL(pid));
        problemHTML pHTML = new problemHTML();
        pHTML.setTitle(d.select(".bigProblemTitle").get(0).text());

        Elements pics = d.select("img");
        for(int i=0;i<pics.size();i++){
            String pic_url = pics.get(i).attr("src");
            if(pic_url.indexOf("http") != 0){
                pics.get(i).attr("src",URL+"/onlinejudge"+"/"+pic_url);
            }
        }

        Elements ds = d.select("div#content_body");
        //pHTML.setInput(ds.get(1).text());
        //pHTML.setOutput(ds.get(2).text());
        //pHTML.addSample(d.select("pre").get(0).text(),d.select("pre").get(1).text());

        String limit=d.select("center").text();
        System.out.println(limit);
        pHTML.setTimeLimit(limit.substring(limit.indexOf("Time Limit:")+12,limit.indexOf("Seconds")-1)+"000MS");
        pHTML.setMenoryLimit(limit.substring(limit.indexOf("Memory Limit:")+14,limit.indexOf("KB")-1)+"KB");
        if(limit.contains("Special Judge")){
            pHTML.setSpj(true);
        }else{
            pHTML.setSpj(false);
        }
        pHTML.setInt64(get64IO(null));

        ds.select("center").get(0).remove();
        ds.select("center").get(0).remove();
        ds.select("hr").get(0).remove();
        ds.select("hr").get(0).remove();
        ds.select("center").last().remove();
        pHTML.setDis(ds.html());

        return pHTML;
    }

    @Override
    public String getTitle(String pid) {
        Document d = MyClient.getMyClient().get(getProblemURL(pid));
        return d.select("center").get(0).text();
    }

    @Override
    public String submit(VjSubmitter s) {
        MyClient client = s.client;
        if(client == null) return "error";

        //Document d = s.client.get(URL+"/login.php");
        //  String action = "login.php";
        // System.out.println(action);
        List<NameValuePair> login_para = new ArrayList<>();
        login_para.add(new BasicNameValuePair("handle",s.getUsername()));
        login_para.add(new BasicNameValuePair("password",s.getPassword()));
        login_para.add(new BasicNameValuePair("submit","Login"));
        //login_para.add(new BasicNameValuePair("submit","Submit"));

        //System.out.println("action = "+action);
        client.Post(URL+"/onlinejudge"+"/login.do",login_para);

        List<NameValuePair> para = new ArrayList<>();

        para.add(new BasicNameValuePair("languageId",getTrueLanguage(s.getSubmitInfo().language,s.getSubmitInfo().pid)+""));
        //para.add(new BasicNameValuePair("id",s.getSubmitInfo().pid));
        para.add(new BasicNameValuePair("source",s.getSubmitInfo().code));
        para.add(new BasicNameValuePair("submit","Submit"));
        //System.out.println(URL+"/submit.do?problemId="+ (Integer.parseInt(s.getSubmitInfo().pid)-1000));
        client.Post(URL+"/onlinejudge"+"/submit.do?problemId="+ (Integer.parseInt(s.getSubmitInfo().pid)-1000),para);
        //System.out.println(URL+"submit.do?problemId="+s.getSubmitInfo().pid.indexOf(0));
        return "success";
    }

    private static Map<String,Result> res_map;
    private static List<Pair<Integer,CodeLanguage>> languageList;
    static {
        res_map = new HashMap<>();

        res_map.put("Pending",Result.PENDING);
        //res_map.put("Padding",Result.PENDING);
        res_map.put("Running",Result.RUNNING);
        res_map.put("Accepted",Result.AC);
        res_map.put("Wrong Answer",Result.WA);
        res_map.put("Presentation Error",Result.PE);
        res_map.put("Time Limit Exceeded",Result.TLE);
        res_map.put("Memory Limit Exceeded",Result.MLE);
        res_map.put("Output Limit Exceeded",Result.OLE);
        res_map.put("Compilation Error",Result.CE);
        res_map.put("Compiling",Result.PENDING);
        res_map.put("Runtime Error",Result.RE);
        //res_map.put("Restrict Function Call",Result.DANGER);
        //res_map.put("System Error",Result.ERROR);
        res_map.put("Non-zero Exit Code",Result.RE);
        res_map.put("Floating Point Error",Result.RE);
        res_map.put("Segmentation Fault",Result.RE);

        languageList = new ArrayList<>();
        languageList.add(new Pair<>(2,CodeLanguage.GPP));
        languageList.add(new Pair<>(1,CodeLanguage.GCC));
        //languageList.add(new Pair<>(,CodeLanguage));
        languageList.add(new Pair<>(4,CodeLanguage.JAVA));
        languageList.add(new Pair<>(5,CodeLanguage.PYTHON3));
    }


    @Override
    public RES getResult(VjSubmitter s) {
        Document d = s.client.get(URL+"/onlinejudge"+"/showRuns.do?contestId=1&handle="+s.getUsername());
        Element e = d.select(".list").select("tr").get(1);
        RES res = new RES();
        if(e == null){
            res.setR(Result.ERROR);
        }else{
            Elements tds = e.select("td");
            String res_str = tds.get(2).text();
            // System.out.println("res_str="+res_str);
            Result rs = res_map.get(res_str);
            //System.out.println(res_str);
            if(rs == null) res.setR(Result.ERROR);
            else res.setR(rs);
            res.setTime(tds.get(5).text()+"MS");
            res.setMemory(tds.get(6).text()+"KB");
            if(res.getR()==Result.CE || res.getTime().equals("")) res.setTime("-");
            if(res.getR()==Result.CE || res.getMemory().equals("")) res.setMemory("-");

            if(res.getR()==Result.CE){
                String rid = tds.get(0).text();
                res.setCEInfo(getCEInfo(rid,s));
            }
        }
        return res;
    }

    private String getCEInfo(String rid,VjSubmitter s){

        Document d = s.client.get(URL+"/onlinejudge"+"/showRuns.do?contestId=1&handle="+s.getUsername());
        Elements e = d.select(".list").select("tr").get(1).select("td").get(2).select("a");

        return s.client.getString(URL+e.attr("href"));
    }

    @Override
    public String getProblemURL(String pid) {
        return URL+"/onlinejudge"+"/showProblem.do?problemCode="+pid;
    }

    @Override
    public String getName() {
        return "ZOJ";
    }

    @Override
    public String get64IO(String pid) {
        return "%lld";
    }

    @Override
    public List<Pair<Integer, CodeLanguage>> getLanguageList(String pid) {
        return languageList;
    }
}

