package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.*;
import com.fjut.oj.pojo.*;
import com.fjut.oj.service.ChallengeService;
import com.fjut.oj.service.UserService;
import com.fjut.oj.util.ResultString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author cjt
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public int queryUserCount() {
        return userMapper.queryUserCount();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean insertUser(UserPO user, UserAuthPO userAuth) {
        Integer ansUser = userMapper.insertUser(user);
        Integer ansAuth = userAuthMapper.insertUserAuth(userAuth);
        //用户注册成功
        if (1 == ansUser && 1 == ansAuth) {
            // 发送欢迎消息
            MessagePO message = new MessagePO();
            message.setStatus(0);
            message.setUser(user.getUsername());
            message.setTitle("欢迎您，新用户！");
            message.setText("Talk is cheap, show me the code! <br />" +
                    "欢迎您，新朋友！" +
                    "<br />欢迎来到一码当先的世界，请在首页上跟随教程熟悉我们吧！");
            message.setTime(new Date());
            // 七天后过期
            message.setDeadline(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)));
            messageMapper.insertMessage(message);
            // 解锁挑战模块
            challengeService.insertOpenBlock(user.getUsername(), 1);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int updateUserByUsername(UserPO user) {
        return userMapper.updateUserByUsername(user);
    }

    @Override
    public UserPO getUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    @Override
    public Integer getUserByUsernameAndPassword(String username, String password) {
        return userMapper.getUserByUsernameAndPassword(username, password);
    }


    @Override
    public void deleteUserByUsername(String username) {
        userMapper.deleteUserByUsername(username);
    }

    @Override
    public Integer queryPutTagNumByUsername(String username) {
        int num = userMapper.queryPutTagNumByUsername(username);
        return num;
    }

    @Override
    public List<Integer> queryStatusProblemsByUsername(Integer status, String username) {
        List<Integer> list = userMapper.queryStatusProblemsByUsername(status, username);
        return list;
    }

    @Override
    public List<Integer> queryNotPutTagProblemsByUsername(String username) {

        List<Integer> list1 = userMapper.queryStatusProblemsByUsername(1, username);
        List<Integer> list2 = userMapper.queryCanViewCodeProblemsByUsername(username);
        List<Integer> list3 = new ArrayList<>();

        for (Integer pid : list1) {
            if (!list2.contains(pid)) {
                list3.add(pid);
            }
        }
        return list3;
    }

    @Override
    public List<UserPO> queryRichTop10() {
        List<UserPO> list = userMapper.queryRichTop10();
        return list;
    }

    @Override
    public List<UserPO> queryAcNumTop10() {
        List<UserPO> list = userMapper.queryAcnumTop10();
        return list;
    }

    @Override
    public List<Integer> queryUserPermission(String username) {
        List<Integer> list = userMapper.queryUserPermission(username);
        return list;
    }

    @Override
    public List<String> queryAwardInfo(String username) {
        List<AwardInfo> list = userMapper.queryAwardInfo(username);
        List<String> tostring_list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            tostring_list.add(list.get(i).getTime() + ": 参加" + ResultString.contestLevelToStr(list.get(i).getContestLevel())
                    + "获得" + ResultString.awardLevelToStr(list.get(i).getAwardLevel())
                    + "" + list.get(i).getText());

        }
        return tostring_list;
    }

    @Override
    public Map<String, Integer> getRatingGraph(String username) {
        Map<String, Integer> list = new HashMap<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<RatingGraph> graphs = userMapper.getRatingGraph(username);
        try {
            Calendar end = Calendar.getInstance();
            end.setTime(dateFormat.parse(dateFormat.format(new Date())));
            String time = "2015-10-01";
            Calendar start = Calendar.getInstance();
            start.setTime(dateFormat.parse(time));
            list.put(dateFormat.format(start.getTime()), 0);
            start.add(Calendar.DAY_OF_YEAR, 1);
            while (start.before(end)) {
                for (int i = 1; i < graphs.size(); i++) {
                    if (graphs.get(i).getTime().contains(dateFormat.format(start.getTime()))) {
                        list.put(dateFormat.format(start.getTime()), graphs.get(i).getRating());
                    } else {
                        list.put(dateFormat.format(start.getTime()), graphs.get(i - 1).getRating());
                    }
                }
                start.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer addAcnum(String username) {
        return userMapper.updateACNumAddOneByUsername(username);
    }

    /**
     * FIXME:更换函数后失效
     *
     * @param username
     * @return
     */
    @Override
    public Object getAcGraph(String username) {
        return null;
    }

    @Override
    public Integer queryAcbNumber(String username) {
        return userMapper.queryAcbNumber(username);
    }

    @Override
    public Integer updateAcbNumber(String username, Integer acbChange) {
        return userMapper.updateACBNumber(username, acbChange);
    }

    @Override
    public String getUserAvatar(String username){
        return userMapper.getUserAvatar(username);
    }

//        List<String> timeList = new ArrayList<>();
//        List<Integer> numList = new ArrayList<>();
//        List<Status> list_1 = statusMapper.queryUserSolveProblemByUsername(username);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Calendar end = Calendar.getInstance();
//            end.setTime(dateFormat.parse(dateFormat.format(new Date())));
//            String time = "2015-09-01";
//            Calendar start = Calendar.getInstance();
//            start.setTime(dateFormat.parse(time));
//            int i =0;int num=0;
//            timeList.add(dateFormat.format(start.getTime()));
//            numList.add(num);
//            while (start.before(end)) {
//                while (i<=list_1.size()-1&&list_1.get(i).getSubmitTime().substring(0,10).equals(dateFormat.format(start.getTime()))){
//                    num +=1;
//                    timeList.add(dateFormat.format(start.getTime()));
//                    numList.add(num);
//                    i++;
//                }
//                timeList.add(dateFormat.format(start.getTime()));
//                numList.add(num);
//                start.add(Calendar.DAY_OF_YEAR, 1);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        List<Object> list = new ArrayList<>();
//        list.add(timeList);
//        list.add(numList);
//        return list;
//    }
}
