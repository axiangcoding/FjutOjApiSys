package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.UserMapper;
import com.fjut.oj.pojo.UserRadar1;
import com.fjut.oj.pojo.UserRadar2;
import com.fjut.oj.service.UserRadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cjt
 */
@Service("UserRadarService")
public class UserRadarServiceImpl implements UserRadarService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户的雷达图
     * @param user
     * @return
     */
    @Override
    public String getUserRadar(String user) {
        List<UserRadar1> listUserRadar1 = userMapper.queryUserRadar1(user);
        List<UserRadar2> listUserRadar2 = userMapper.queryUserRadar2(user);
        int[] a = new int[7];
        int[] b = new int[7];
        try {
            for (UserRadar1 userRadar1: listUserRadar1) {
                int x = userRadar1.getTtype();
                if (x >=0 && x<= 6){
                    a[x] = userRadar1.getNum() == null ? 0 : userRadar1.getNum();
                }
            }
            for (UserRadar2 userRadar2: listUserRadar2) {
                int x = userRadar2.getTtype();
                if (x >=0 && x<= 6){
                    b[x] = userRadar2.getNum() == null ? 0 : userRadar2.getNum();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        String ret = "[";
        for (int i = 0; i <= 6; ++i){
            if (i != 0){
                ret +=",";
            }
            ret += (b[i] !=0 ? a[i] * 100.0/b[i]:"0");
        }
        return ret + "]";
    }
}
