package com.fjut.oj.util;

import com.fjut.oj.pojo.enums.Result;
import com.fjut.oj.pojo.enums.CodeLanguage;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cjt
 */
public class HDU {

    public static String URL = "http://acm.hdu.edu.cn";
    public static String submitURL = "/submit.php?action=submit";
    public static String problemURL = "/showproblem.php";
    public static String TitleSelect = "h1";
    public static String statusURL = "/status.php";
    public static String statusFormUser = "user";
    public static String statuSelect = "table:nth-child(2) tr:nth-child(4) table tr:nth-child(3)";
    public static String DesSelect = ".panel_content";
    public static String SampleInputSelect = "pre";
    //private static Map<String,Result> resultMap;
    public static String loginURL = "/userloginex.php?action=login";
    public static String Int64 = "%I64d";
    public static Map<String, Result> resultMap;
    public static List<Pair<Integer, CodeLanguage>> languageList;
    public static String rid;
    public static String[] submitusername = {"cjt001", "cjt002", "cjt003", "cjt004", "cjt005", "cjt006", "cjt007", "cjt008", "cjt009", "cjt010"};
    public static String[] submitpassword = {"fjutacm", "fjutacm", "fjutacm", "fjutacm", "fjutacm", "fjutacm", "fjutacm", "fjutacm", "fjutacm", "fjutacm"};
    public static boolean[] check = {false, false, false, false, false, false, false, false, false, false};
    private static MyClient hc = new MyClient();

    static {
        resultMap = new HashMap<String, Result>();
        resultMap.put("Queuing", Result.PENDING);
        resultMap.put("Compiling", Result.PENDING);
        resultMap.put("Running", Result.PENDING);
        resultMap.put("Accepted", Result.AC);
        resultMap.put("Wrong Answer", Result.WA);
        resultMap.put("Runtime Error (ACCESS_VIOLATION)", Result.RE);
        resultMap.put("Runtime Error (ARRAY_BOUNDS_EXCEEDED)", Result.RE);
        resultMap.put("Runtime Error (FLOAT_DENORMAL_OPERAND)", Result.RE);
        resultMap.put("Runtime Error (FLOAT_DIVIDE_BY_ZERO)", Result.RE);
        resultMap.put("Runtime Error (FLOAT_OVERFLOW)", Result.RE);
        resultMap.put("Runtime Error (FLOAT_UNDERFLOW)", Result.RE);
        resultMap.put("Runtime Error (INTEGER_DIVIDE_BY_ZERO)", Result.RE);
        resultMap.put("Runtime Error (INTEGER_OVERFLOW)", Result.RE);
        resultMap.put("Runtime Error (STACK_OVERFLOW)", Result.RE);
        resultMap.put("Time Limit Exceeded", Result.TLE);
        resultMap.put("Memory Limit Exceeded", Result.MLE);
        resultMap.put("Output Limit Exceeded", Result.OLE);
        resultMap.put("Presentation Error", Result.PE);
        resultMap.put("Compilation Error", Result.CE);
        resultMap.put("System Error", Result.DANGER);

        languageList = new ArrayList<>();
        languageList.add(new Pair<>(0, CodeLanguage.GPP));
        languageList.add(new Pair<>(1, CodeLanguage.GCC));
        languageList.add(new Pair<>(4, CodeLanguage.PASCAL));
        languageList.add(new Pair<>(5, CodeLanguage.JAVA));
        languageList.add(new Pair<>(6, CodeLanguage.C_SHARP));
    }

    public static String getSubmitURL() {
        return URL + submitURL;
    }

    public static String getLoginURL() {
        return URL + loginURL;
    }

    public static String getStatusURL(String user) {
        return URL + statusURL + "?" + statusFormUser + "=" + user;
    }

    public String getName() {
        return "HDU";
    }

    public String get64IO(String pid) {
        return Int64;
    }

    public List<Pair<Integer, CodeLanguage>> getLanguageList(String pid) {
        return languageList;
    }

    public static String getProblemURL(String pid) {
        return URL + problemURL + "?pid=" + pid;
    }

    private static Result getResultMap(String v) {
        return resultMap.get(v);
    }

    public static String getRid(String user) {
        try {
            Document d = Jsoup.connect(getStatusURL(user)).get();
            Element e = d.select(statuSelect).first();
            return (e.select("td:nth-child(1)").first().text());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String getTitle(String pid) {
        Document doc;
        try {
            doc = Jsoup.connect(getProblemURL(pid)).get();
            return doc.select(TitleSelect).get(0).text();
        } catch (IOException e) {
            return "获取题目出错";
        }
    }

    public static ProblemHTML getProblemHTML(String pid) {
        Document doc;
        ProblemHTML p = new ProblemHTML();
        try {
            doc = Jsoup.connect(getProblemURL(pid)).get();
        } catch (IOException e) {
            System.out.println("未连接到目标oj");
            return null;
        }
        Elements es = doc.select("img");
        for (Element e : es) {
            String link = e.attr("src");
            e.attr("src", URL + "/" + link);
        }

        p.setTitle(doc.select(TitleSelect).get(0).text());
        p.setDis(doc.select(DesSelect).get(0).html());
        p.setInput(doc.select(DesSelect).get(1).html());
        p.setOutput(doc.select(DesSelect).get(2).html());
        Elements e2 = doc.select(SampleInputSelect);
        p.addSample("<pre class='sample'>"
                        + e2.get(e2.size() - 2).text() + "</pre>",
                "<pre class='sample'>"
                        + e2.get(e2.size() - 1).text() + "</pre>");
        p.setInt64(Int64);
        String s = doc.select("span").get(0).text();
        //System.out.println(s);
        String s1 = s.substring(s.indexOf("/") + 1, s.indexOf("(Java/Others)") - 4) + "MS";
        String s2 = s.substring(s.indexOf("Memory Limit:") + 13);
        s2 = s2.substring(s2.indexOf("/") + 1, s2.indexOf("K (Java/Others)") - 1) + "KB";
        p.setTimeLimit(s1);
        p.setMenoryLimit(s2);
        if (s.contains("Special Judge")) p.setSpj(1);
        //System.out.println(p);
        return p;
    }

    public static RES getResultReturn() {
        int wait[] = {3, 2, 1, 2, 3, 5, 7, 8, 9, 10};
        int i = 0;
        RES r;
        do {
            try {
                Thread.sleep(wait[i] * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
            r = getResult();

        } while (!r.canReturn());
        return r;
    }

    /**
     * public static int getTrueLanguage(int i,String pid){
     * List<Pair<Integer,CodeLanguage>> languageList = getLanguageList(pid);
     * for(Pair<Integer,CodeLanguage> pair : languageList){
     * if(pair.getValue().getId() == i){
     * return pair.getKey();
     * }
     * }
     * return 0;
     * }
     */

    public static String submit() {
        String ret = Login();
        if (ret.equals("error")) return "error";
        int num = Integer.parseInt(ret);
        String submitId = "15005";
        String problemid = "1000";
        String language = "0";
        String usercode = "#include<cstdio>\n" +
                "using namespace std;\n" +
                "int main()\n" +
                "{ \n" +
                "     int a, b;\n" +
                "     while(scanf(\"%d %d\",&a ,&b) != EOF)\n" +
                "     {\n" +
                "       printf(\"%d\\n\", a + b);\n" +
                "     }\n" +
                "     return 0;" +
                "}\n";
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        formparams.add(new BasicNameValuePair("problemid", problemid));
        formparams.add(new BasicNameValuePair("language", language));
        formparams.add(new BasicNameValuePair("usercode", usercode));

        if (hc.Post(getSubmitURL(), formparams) == null) {
            System.out.println("errorsubmit");
            return "error";
        }
        rid = getRid(submitusername[1]);
        System.out.println("successsubmit");
        return "success";
    }

    public static RES getResult() {
        Element e;
        Document d = null;
        RES r = new RES();
        try {
            d = Jsoup.connect(getStatusURL(submitusername[1])).get();
            //System.out.println(d);
        } catch (IOException e1) {
            e1.printStackTrace();
            r.setR(Result.PENDING);
            return r;
        }
        e = d.select(statuSelect).first();
        System.out.println("get:" + getResultMap(e.select("td:nth-child(3)").first().text()).name());
        r.setR(getResultMap(e.select("td:nth-child(3)").first().text()));
        if (r.canReturn()) {
            r.setTime(e.select("td:nth-child(5)").first().text());
            r.setMemory(e.select("td:nth-child(6)").first().text() + "B");
        }
        if (r.getR() == Result.CE) {
            r.setCEInfo(getCEInfo());
            System.out.println(getCEInfo());
        }
        return r;
    }

    public static String Login() {
        List<BasicNameValuePair> formparams = new ArrayList<>();
        int i;
        for (i = 1; i < 10; ++i) {
            if (check[i] == true)
                continue;
            else
                break;
        }
        if (i == 10) return "error";
        System.out.println(submitusername[i] + " " + submitpassword[i]);
        formparams.add(new BasicNameValuePair("username", submitusername[i]));
        formparams.add(new BasicNameValuePair("userpass", submitpassword[i]));
        if (hc.Post(getLoginURL(), formparams) == null) {
            System.out.println("error");
            return "error";
        }
        System.out.println("success");
        // check[i] = true;
        return "" + i;
    }

    public static String getCEInfo() {
        Element e;
        Document d = null;
        try {
            d = Jsoup.connect("http://acm.hdu.edu.cn/viewerror.php?rid=" + rid).get();
            return d.select("pre").first().html();
        } catch (IOException e1) {
            e1.printStackTrace();
            return "获取失败";
        } catch (NullPointerException e2) {
            return "获取失败";
        }
    }

    public static void main(String[] args) {
        String url = "2500";
        // getProblemHTML(url);
        //getResult();
        // Login();
        submit();
        getResultReturn();
    }
}