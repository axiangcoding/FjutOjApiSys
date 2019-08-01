package com.fjut.oj.judge.entity.OJ.BZOJ;

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

public class BZOJ extends OTHOJ{
    public static Map<String,Result> ResultMap;
    public static String URL = "http://www.lydsy.com/JudgeOnline";//Main.GV.getJSONObject("bzoj").getString("URL");//"http://www.lydsy.com/JudgeOnline";
    @Override
    public String getRid(String user, VjSubmitter s) {
        Document d = s.client.get(URL+"/status.php?user_id="+s.getUsername());
        Element e = d.select("body>center>table").get(1).select("tr").get(1);
        if(e == null){
            return "0";
        }else{
            Elements tds = e.select("td");
            return tds.get(0).text();
        }
    }

    @Override
    public problemHTML getProblemHTML(String pid) {
        Document d = MyClient.getMyClient().get(getProblemURL(pid));
        Elements pics = d.select("img");
        for(int i=0;i<pics.size();i++){
            String pic_url = pics.get(i).attr("src");
            if(pic_url.indexOf("http") != 0){
                pics.get(i).attr("src",URL+"/"+pic_url);
            }
        }

        problemHTML pHTML = new problemHTML();
        pHTML.setTitle(d.select("title").text());
        Elements ds = d.select(".content");
        pHTML.setDis(ds.get(0).html());
        pHTML.setInput(ds.get(1).html());
        pHTML.setOutput(ds.get(2).html());
        pHTML.addSample(d.select(".sampledata").get(0).text(),d.select(".sampledata").get(1).text());

        String limit=d.select("center").text();
        pHTML.setTimeLimit(limit.substring(limit.indexOf("Time Limit:")+12,limit.indexOf("Sec")-1)+"000ms");
        pHTML.setMenoryLimit(limit.substring(limit.indexOf("Memory Limit:")+14,limit.indexOf("MB")-1)+"000KB");
        if(limit.indexOf("Special Judge") != -1){
            //System.out.println(limit.indexOf("Special Judge"));
            pHTML.setSpj(true);
        }
        else{
            pHTML.setSpj(false);
        }
        //pHTML.addSample(d.select(".content").get(0).tagName("pre").html(),d.select(".data").get(1).tagName("pre").html());
        //Elements limit = d.select(".center"); System.out.println("test");
       /* String time_limit = d.select(".center").get(2).text();
        time_limit = time_limit.substring(time_limit.indexOf("Time Limit:")+11,time_limit.indexOf("mSec"));
        String memory_limit =  d.select(".center").get(2).text();
        memory_limit = memory_limit.substring(memory_limit.indexOf("Memory Limit :")+14,memory_limit.indexOf("KB"));
        pHTML.setTimeLimit(time_limit.trim()+"MS");
        pHTML.setMenoryLimit((Integer.parseInt(memory_limit.trim())/1000)+"MB");*/

        // pHTML.setTimeLimit(limit.select(".green").get(0).text());
        pHTML.setInt64(get64IO(null));
        return pHTML;
    }

    @Override
    public String getTitle(String pid) {
        Document d = MyClient.getMyClient().get(getProblemURL(pid));
        return d.select("title").text();
    }

    public String submit(VjSubmitter s) {
        MyClient client = s.client;
        if(client == null) return "error";

        //Document d = s.client.get(URL+"/login.php");
        //  String action = "login.php";
        // System.out.println(action);
        List<NameValuePair> login_para = new ArrayList<>();
        login_para.add(new BasicNameValuePair("user_id",s.getUsername()));
        login_para.add(new BasicNameValuePair("password",s.getPassword()));
        login_para.add(new BasicNameValuePair("submit","Submit"));
        //login_para.add(new BasicNameValuePair("submit","Submit"));

        //System.out.println("action = "+action);
        System.out.println(client.Post(URL+"/login.php",login_para));

        List<NameValuePair> para = new ArrayList<>();
        // para.add(new BasicNameValuePair("usr",s.getUsername()));
        // para.add(new BasicNameValuePair("pid",s.getSubmitInfo().pid));
        // para.add(new BasicNameValuePair("code",s.getSubmitInfo().code));

        para.add(new BasicNameValuePair("language",getTrueLanguage(s.getSubmitInfo().language,s.getSubmitInfo().pid)+""));
        para.add(new BasicNameValuePair("id",s.getSubmitInfo().pid));
        para.add(new BasicNameValuePair("source",s.getSubmitInfo().code));
        System.out.println(client.Post(URL+"/submit.php",para));
        return "success";
    }

    private static Map<String,Result> res_map;
    private static List<Pair<Integer,CodeLanguage>> languageList;
    static {
        res_map = new HashMap<>();
        res_map.put("Running_&_Judging",Result.PENDING);
        res_map.put("Pending_Rejudging",Result.RUNNING);
        res_map.put("Pending",Result.RUNNING);
        res_map.put("Accepted",Result.AC);
        res_map.put("Wrong_Answer",Result.WA);
        res_map.put("Presentation_Error",Result.PE);
        res_map.put("Time_Limit_Exceed",Result.TLE);
        res_map.put("Memory_Limit_Exceed",Result.MLE);
        res_map.put("Output_Limit_Exceed",Result.OLE);
        res_map.put("Compile_Error",Result.CE);
        res_map.put("Compiling",Result.PENDING);
        res_map.put("Runtime_Error",Result.RE);
        //res_map.put("Restrict Function Call",Result.DANGER);
        //res_map.put("System Error",Result.ERROR);

        languageList = new ArrayList<>();
        languageList.add(new Pair<>(1,CodeLanguage.GPP));
        languageList.add(new Pair<>(0,CodeLanguage.GCC));
        languageList.add(new Pair<>(2,CodeLanguage.PASCAL));
        languageList.add(new Pair<>(3,CodeLanguage.JAVA));
        languageList.add(new Pair<>(6,CodeLanguage.PYTHON3));
    }


    @Override
    public RES getResult(VjSubmitter s) {
        Document d = s.client.get(URL+"/status.php?user_id="+s.getUsername());
        Element e = d.select("body>center>table").get(1).select("tr").get(1);
        RES res = new RES();
        if(e == null){
            res.setR(Result.ERROR);
        }else{
            Elements tds = e.select("td");
            String res_str = tds.get(3).text();
            System.out.println("res_str="+res_str);
            Result rs = res_map.get(res_str);
            if(rs == null) res.setR(Result.ERROR);
            else res.setR(rs);
            res.setTime(tds.get(5).text().replace(" ","").replace("ms","MS"));
            res.setMemory(tds.get(4).text().replace(" ","").replace("kb","KB"));
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
        Document d = s.client.get(URL+"/ceinfo.php?sid="+rid);
        return d.select("pre").html();
    }

    @Override
    public String getProblemURL(String pid) {
        return URL+"/problem.php?id="+pid;
    }

    @Override
    public String getName() {
        return "BZOJ";
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

