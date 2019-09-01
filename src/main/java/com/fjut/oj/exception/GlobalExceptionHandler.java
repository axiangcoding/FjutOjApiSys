package com.fjut.oj.exception;

import com.fjut.oj.pojo.JsonInfoVO;
import com.fjut.oj.pojo.LogPO;
import com.fjut.oj.service.LogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Date;


/**
 * TODO: 暂时设计为得到Exception直接保存到数据库中，后面设置定时任务将文件中的日志写入数据库
 * FIXME: 如果与前端为跨域，前端无法正常得到带错误提示的JSON字符串，只会显示跨域问题，实际上并不是跨域产生的
 * 全局异常处理类，将异常信息保存到数据库中，只返回前端带错误提示的JSON字符串
 * 返回JSON字符串结构为：{code:400 msg:"异常信息" datas:[]}
 *
 * @author axiang [20190620]
 */
@ControllerAdvice()
@ResponseBody
public class GlobalExceptionHandler {
    @Autowired
    LogService logService;

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public JsonInfoVO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String msg = "请求解析失败";
        LOGGER.error(msg, e);
        addExceptionToDatabase(e);
        return new JsonInfoVO("ERROR", msg);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JsonInfoVO handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String msg = "不支持当前请求方法";
        LOGGER.error(msg, e);
        addExceptionToDatabase(e);
        return new JsonInfoVO("ERROR", msg);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public JsonInfoVO handleHttpMediaTypeNotSupportedException(Exception e) {
        String msg = "不支持当前媒体类型";
        LOGGER.error(msg, e);
        addExceptionToDatabase(e);
        return new JsonInfoVO("ERROR", msg);
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public JsonInfoVO handleException(Exception e, HttpServletResponse response) {
        String msg;
        LOGGER.error("服务运行异常", e);
        if (e instanceof NullPointerException) {
            msg = "空指针错误！";
            addExceptionToDatabase(e);
        } else if (e instanceof NumberFormatException) {
            msg = "数字解析错误！";
        } else if (e instanceof SQLException) {
            msg = "SQL语句错误！";
        } else if (e instanceof MissingServletRequestParameterException) {
            msg = "请求参数不完整！";
        } else if (e instanceof RedisConnectionFailureException) {
            msg = "服务器异常！";
        } else if (e instanceof AuthExpireException) {
            msg = "认证已过期！";
        } else if (e instanceof NotLoginException) {
            msg = "需要登录权限！";
        } else if (e instanceof NotOwnerException) {
            msg = "请求的内容无法访问！";
            addExceptionToDatabase(e);
        } else if (e instanceof NotAdminException) {
            msg = "需要管理员权限！";
        } else {
            msg = "服务器内部错误！";
            addExceptionToDatabase(e);
        }
        return new JsonInfoVO("ERROR", msg);
    }


    /**
     * 将错误信息存入数据库中
     *
     * @param e
     */
    private void addExceptionToDatabase(Exception e) {
        LogPO log = new LogPO();
        log.setText(getErrorInfoFromException(e));
        log.setTime(new Date());
        if (e instanceof NotOwnerException) {
            log.setIpAddress(((NotOwnerException) e).getIp());
        }
        logService.insertLog(log);
    }

    /**
     * 获取Exception中错误详情的字符串
     *
     * @param e
     * @return
     */
    private String getErrorInfoFromException(Exception e) {
        String res = "";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            e.printStackTrace(pw);
            res = sw.toString();
            pw.close();
            sw.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return res;
    }


}
