package com.fjut.oj.judge.entity.OJ.SPOJ;

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
import java.util.List;
import java.util.Map;

public class SPOJ extends OTHOJ{
    public static Map<String,Result> ResultMap;
    public static String URL = "http://www.spoj.com";
    //Main.GV.getJSONObject("bzoj").getString("URL");//"http://www.spoj.com";
    @Override
    public String getRid(String user, VjSubmitter s) {

        MyClient client = s.client;
        List<NameValuePair> login_para = new ArrayList<>();
        login_para.add(new BasicNameValuePair("next_raw","/"));
        login_para.add(new BasicNameValuePair("autologin","1"));
        login_para.add(new BasicNameValuePair("login_user",s.getUsername()));
        login_para.add(new BasicNameValuePair("password",s.getPassword()));

        client.Post(URL+"/login",login_para);

        Document d = s.client.get(URL+"/status/"+s.getUsername());
        //System.out.println(URL+"/status/"+s.getUsername());
        Elements e = d.select("table.problems.table.newstatus>tbody").select("tr");
        if(e == null ||e.size()<1){
            return "0";
        }else{
            Elements tds = e.get(0).select("td");
            return tds.get(0).select("a").text();
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
        pHTML.setTitle(d.select("div.prob>h2#problem-name.text-center").text());
        pHTML.setDis(d.select("div.prob>div#problem-body").html());

        Elements ds1 =d.select("table#problem-meta.probleminfo>tbody");
        pHTML.setTimeLimit(ds1.select("tr").get(2).select("td").get(1).text().replace("s","000MS"));
        pHTML.setMenoryLimit(ds1.select("tr").get(4).select("td").get(1).text());

        pHTML.setInt64(get64IO(null));
        return pHTML;
    }

    @Override
    public String getTitle(String pid) {
        Document d = MyClient.getMyClient().get(getProblemURL(pid));
        return d.select("div.prob>h2#problem-name.text-center").text();
    }

    @Override
    public String submit(VjSubmitter s) {
        MyClient client = s.client;
        if(client == null) return "error";


        List<NameValuePair> login_para = new ArrayList<>();
        login_para.add(new BasicNameValuePair("next_raw","/"));
        login_para.add(new BasicNameValuePair("autologin","1"));
        login_para.add(new BasicNameValuePair("login_user",s.getUsername()));
        login_para.add(new BasicNameValuePair("password",s.getPassword()));


        client.Post(URL+"/login",login_para);


        List<NameValuePair> para = new ArrayList<>();

        para.add(new BasicNameValuePair("subm_file",""));
        para.add(new BasicNameValuePair("file",s.getSubmitInfo().code));
        para.add(new BasicNameValuePair("lang",getTrueLanguage(s.getSubmitInfo().language,s.getSubmitInfo().pid)+""));
        //para.add(new BasicNameValuePair("lang","41"));
        para.add(new BasicNameValuePair("problemcode",s.getSubmitInfo().pid));
        para.add(new BasicNameValuePair("submit","Submit!"));

        client.Post(URL+"/submit/complete/",para);
        return "success";
    }

    //private static Map<String,Result> res_map;
    private static List<Pair<Integer,CodeLanguage>> languageList;
    static {
        /*res_map = new HashMap<>();
        res_map.put("running.. (0) edit    ideone it",Result.PENDING);
        res_map.put("pending rejudging",Result.RUNNING);
        res_map.put("pending edit    ideone it",Result.RUNNING);
        res_map.put("accepted edit    ideone it",Result.AC);
        res_map.put("wrong answer edit    ideone it",Result.WA);
        res_map.put("presentation error edit    ideone it",Result.PE);
        res_map.put("time limit exceeded edit    ideone it",Result.TLE);
        res_map.put("memory limit exceed edit    ideone it",Result.MLE);
        res_map.put("output limit exceed edit    ideone it",Result.OLE);
        res_map.put("compilation error edit    ideone it",Result.CE);
        res_map.put("compiling edit    ideone it",Result.PENDING);
        res_map.put("runtime error (SIGSEGV) edit    ideone it",Result.RE);
        res_map.put("runtime error (NZEC) edit    ideone it",Result.RE);
        */
        //res_map.put("Restrict Function Call",Result.DANGER);
        //res_map.put("System Error",Result.ERROR);

        languageList = new ArrayList<>();
        languageList.add(new Pair<>(41,CodeLanguage.GPP));
        languageList.add(new Pair<>(11,CodeLanguage.GCC));
        languageList.add(new Pair<>(22,CodeLanguage.PASCAL));
        languageList.add(new Pair<>(10,CodeLanguage.JAVA));
        languageList.add(new Pair<>(116,CodeLanguage.PYTHON3));
    }
    private Result getResultFromString(String str) {
        if(str.contains("running")) return Result.RUNNING;
        if(str.contains("pending")) return Result.PENDING;
        if(str.contains("accepted")) return Result.AC;
        if(str.contains("wrong answer")) return Result.WA;
        if(str.contains("presentation error")) return Result.PE;
        if(str.contains("time limit exceeded")) return Result.TLE;
        if(str.contains("memory limit exceed")) return Result.MLE;
        if(str.contains("output limit exceed")) return Result.OLE;
        if(str.contains("compilation error")) return Result.CE;
        if(str.contains("compiling")) return Result.RUNNING;
        if(str.contains("runtime error")) return Result.RE;
        return Result.ERROR;
    }

    @Override
    public RES getResult(VjSubmitter s) {

        MyClient client = s.client;
        List<NameValuePair> login_para = new ArrayList<>();
        login_para.add(new BasicNameValuePair("next_raw","/"));
        login_para.add(new BasicNameValuePair("autologin","1"));
        login_para.add(new BasicNameValuePair("login_user",s.getUsername()));
        login_para.add(new BasicNameValuePair("password",s.getPassword()));


        client.Post(URL+"/login",login_para);

        Document d = s.client.get(URL+"/status/"+s.getUsername());
        //System.out.println(URL+"/status/"+s.getUsername());
        Element e = d.select("table.problems.table.newstatus>tbody").select("tr").get(0);

        RES res = new RES();
        if(e == null){
            res.setR(Result.ERROR);
        }else{
            Elements tds = e.select("td");
            String res_str = tds.get(4).text();
            //System.out.println("res_str="+res_str);
            Result rs = getResultFromString(res_str);
            if(rs == null) res.setR(Result.ERROR);
            else res.setR(rs);
            res.setTime(tds.get(5).text().replace(" ","").replace("ms","MS"));
            res.setMemory(tds.get(6).text().replace(" ","").replace("kb","KB"));
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
        Document d = s.client.get(URL+"/error/"+rid);
        return d.select("small").html();
    }

    @Override
    public String getProblemURL(String pid) {
        return URL+"/problems/"+pid;
    }

    @Override
    public String getName() {
        return "SPOJ";
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

