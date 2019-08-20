package com.fjut.oj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TODO: Controller中全部更改后废弃
 * @author cjt
 */
public class JsonMsg {

    private int code;
    private String msg;
    private List<Object> data = new ArrayList<Object>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getdata() {
        return data;
    }

    public void setdata(List<Object> data) {
        this.data = data;
    }

    public static JsonMsg success(){
        JsonMsg res = new JsonMsg();
        res.setCode(100);
        res.setMsg("操作成功！");
        return res;
    }

    public static JsonMsg fail(){
        JsonMsg res = new JsonMsg();
        res.setCode(200);
        res.setMsg("操作失败！");
        return res;
    }

    public JsonMsg addInfo(Object obj){
        this.data.add(obj);
        return this;
    }
}
