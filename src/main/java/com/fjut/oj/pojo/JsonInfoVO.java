package com.fjut.oj.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的与前端交互的类
 *
 * @author axiang [20190617]
 */
public class JsonInfoVO {
    private Integer code;
    private String msg;
    private List<Object> datas = new ArrayList<>();
    private static String TYPE_SUCCESS = "success";
    private static String TYPE_FAIL = "fail";
    private static String TYPE_ERROR = "error";

    public JsonInfoVO() {

    }

    public JsonInfoVO(String type, String msg) {
        if (TYPE_SUCCESS.equalsIgnoreCase(type)) {
            setSuccess(msg);
        } else if (TYPE_FAIL.equalsIgnoreCase(type)) {
            setFail(msg);
        } else if (TYPE_ERROR.equalsIgnoreCase(type)) {
            setError(msg);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setSuccess() {
        this.code = 100;
    }

    public void setSuccess(String msg) {
        this.code = 100;
        this.msg = msg;
    }

    public void setFail() {
        this.code = 200;
    }

    public void setFail(String msg) {
        this.code = 200;
        this.msg = msg;
    }

    public void setError() {
        this.code = 400;

    }

    public void setError(String msg) {
        this.code = 400;
        this.msg = msg;
    }

    public void cleanDatas() {
        this.datas.clear();
    }

    public void addInfo(Object object) {
        datas.add(object);
    }

    @Override
    public String toString() {
        return "JsonInfoVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", datas=" + datas +
                '}';
    }
}
