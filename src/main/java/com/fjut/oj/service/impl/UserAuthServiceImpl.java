package com.fjut.oj.service.impl;

import com.fjut.oj.mapper.UserAuthMapper;
import com.fjut.oj.service.UserAuthService;
import com.fjut.oj.util.SHAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author axiang [20190828]
 */
@Service("UserAuthService")
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    UserAuthMapper userAuthMapper;

    @Override
    public boolean queryUserExistByUsername(String username) {
        Integer ans = userAuthMapper.queryUserAuthCountByUsername(username);
        return ans == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean queryUserAuthByUsernameAndPassword(String username, String password) {
        int AttemptTime = 5;
        // 对密码进行加密处理
        String salt = userAuthMapper.queryUserAuthSaltByUsername(username);
        String newPwd = salt + password;
        String encryptPwd = SHAUtils.SHA1(newPwd);
        // 查询用户名密码是否对应上
        Integer ans = userAuthMapper.queryUserAuthByUsernameAndPassword(username, encryptPwd);
        // 登录成功
        if (1 == ans) {
            // 重置尝试失败次数为0
            userAuthMapper.updateUserAuthAttemptFailSetZeroByUsername(username);
            return true;
        }
        // 登录失败
        else {
            //更新登录失败次数
            userAuthMapper.updateUserAuthAttemptFailAddOneByUsername(username);
            //查询登录失败次数
            Integer failLoginCount = userAuthMapper.queryUserAuthAttemptNumberByUsername(username);
            //超过5次登录失败，则账号锁定五分钟
            if (failLoginCount >= AttemptTime) {
                //设置时间为现在时间的五分钟后
                long timeLaterFiveMin = System.currentTimeMillis() + (60 * 5 * 1000);
                userAuthMapper.updateUserAuthUnlockTimeByUsername(new Date(timeLaterFiveMin), username);
            }
            return false;
        }
    }

    @Override
    public String queryUserSaltByUsername(String username) {
        return userAuthMapper.queryUserAuthSaltByUsername(username);
    }

    @Override
    public Date queryUserUnlockTimeByUsername(String username) {
        return userAuthMapper.queryUserAuthUnlockTimeByUsername(username);
    }

    @Override
    public Integer queryUserAuthAttemptNumberByUsername(String username) {
        return userAuthMapper.queryUserAuthAttemptNumberByUsername(username);
    }


}
