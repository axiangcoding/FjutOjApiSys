package com.fjut.oj.redis;

import com.fjut.oj.pojo.TokenModel;

/**
 * 对token进行操作
 * @Author: axiang [20190705]
 */
public interface TokenManager {
    /**
     * 创建一个token，关联上指定用户
     * @param username
     * @return
     */
    TokenModel createToken(String username);

    /**
     * 检查token是否有效
     * @param model
     * @return
     */
    boolean checkToken(TokenModel model);


    /**
     * 从加密字符串中获取TokenModel
     * @param auth
     * @return
     */
    TokenModel getToken(String auth);

    /**
     * 清除token
     * @param username
     */
    void deleteToken(String username);

}
