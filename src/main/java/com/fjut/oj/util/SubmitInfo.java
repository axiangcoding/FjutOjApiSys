package com.fjut.oj.util;

/**
 * @author Created by Administrator on 2015/5/21.
 */
public class SubmitInfo {
    //评测的rid
    public int rid;
    //目标oj对应的题目id
    public String pid;
    //代码
    public String code;
    //语言id 0:G++  1:GCC 2:JAVA
    public int language;
    //是否是重判
    public boolean rejduge;
    public int getRid() {
        return rid;
    }
    public SubmitInfo(int rid,String pid,int language,String code,boolean rejduge){
        this.rid=rid;
        this.pid=pid;
        this.code=code;
        this.language=language;
        this.rejduge=rejduge;
    }

    public SubmitInfo(){
        this.rid = 15005;
        this.pid = "1000";
        this.code = "#include<cstdio>\n" +
                "using namespace std;\n" +
                "int main()\n" +
                "{ \n" +
                "     int a, b;\n" +
                "     while(scanf(\"%d %d\",&a ,&b) != EOF)\n" +
                "     {\n" +
                "       printf(\"%d\\n\", a + b);\n" +
                "     }\n" +
                "     return 0;" +
                "\n";
        this.language = 0;
        boolean rejduge = false;
    }
}
