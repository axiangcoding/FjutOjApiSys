package com.fjut.oj.service.serviceImpl;

import com.fjut.oj.mapper.StatusMapper;
import com.fjut.oj.pojo.Status;
import com.fjut.oj.pojo.ViewUserSolve;
import com.fjut.oj.pojo.ViewUserStatus;
import com.fjut.oj.service.StatusService;
import com.fjut.oj.util.ResultString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("StatusService")
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public List<Status> ShowAllStatus() {
        List<Status> list = statusMapper.ShowAllStatus();
        for (Status element : list){
            element.setOtherinfo(ResultString.getResultString(element.getResult()));
            element.setSubmitlanguage(ResultString.getSubmitLanguage(element.getLang()));
        }
        return list;
    }

    @Override
    public List<Status> getAllStatusByUsername(String username){
        return statusMapper.getAllStatusByUsername(username);
    }

    @Override
    public Integer allStatusNum(){
        return statusMapper.allStatusNum();
    }

    @Override
    public List<ViewUserStatus> queryStatus(Integer start){
        List<ViewUserStatus> list = statusMapper.queryStatus(start);
        for (ViewUserStatus element : list){
            element.setOtherinfo(ResultString.getResultString(element.getResult()));
            element.setSubmitlanguage(ResultString.getSubmitLanguage(element.getLang()));
        }
        return list;
    }

    @Override
    public Status queryStatusById(Integer id){
        Status status = statusMapper.queryStatusById(id);
        status.setOtherinfo(ResultString.getResultString(status.getResult()));
        status.setSubmitlanguage(ResultString.getSubmitLanguage(status.getLang()));
        return status;
    }

    @Override
    public Integer queryCountAllStatusByConditions(String ruser, Integer pid, Integer result, Integer language, Integer start) {
        return statusMapper.queryCountAllStatusByConditions(ruser,pid,result,language,start);
    }

    @Override
    public List<Status> queryAllStatusByConditions(String ruser, Integer pid, Integer result, Integer language, Integer start) {
        List<Status> list1 = statusMapper.queryAllStatusByConditions(ruser,pid,result,language, start);
        for (Status element : list1){
            element.setOtherinfo(ResultString.getResultString(element.getResult()));
            element.setSubmitlanguage(ResultString.getSubmitLanguage(element.getLang()));
        }
        return list1;
    }

    @Override
    public Integer querySubmitCountByUsername(String name) {
        Integer num = statusMapper.querySubmitCountByUsername(name);
        return num;
    }

    @Override
    public Integer queryMaxStatusId(){


        return statusMapper.queryMaxStatusId();
    }

    @Override
    public boolean insertStatus(Status status){
        Integer num = statusMapper.insertStatus(status);
        if (num != 0)
            return true;
        return false;
    }

    @Override
    public boolean updateStatus(Status status){
        Integer num = statusMapper.updateStatus(status);
        if (num != 0)
            return true;
        return false;
    }

    @Override
    public boolean updateStatusAfterJudge(Status status) {
        Integer num = statusMapper.updateStatusAfterJudge(status);
        if (num != 0)
            return true;
        return false;
    }

    @Override
    public List<ViewUserSolve> queryUserSolveProblemByUsername(String username) {
        return statusMapper.queryUserSolveProblemByUsername(username);
    }
}
