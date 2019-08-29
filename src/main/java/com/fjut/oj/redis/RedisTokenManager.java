package com.fjut.oj.redis;

import com.fjut.oj.exception.AuthExpireException;
import com.fjut.oj.pojo.TokenModel;
import com.fjut.oj.util.DESUtils;
import com.fjut.oj.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis操作类，封装了对Redis的一些操作
 *
 * @Author: axiang [20190705]
 */
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    private RedisTemplate redis;

    String key = "LongLiveTheKing!";


    /**
     * TODO:暂时只保存用户名和一个UUID生成的无意义的token，如果需要可以添加其他信息，如登录IP等
     * @param username
     * @return
     */
    @Override
    public TokenModel createToken(String username) {
        String token = UUIDUtils.getUUID32();
        redis.boundValueOps(username).set(token);
        TokenModel model = new TokenModel(username, token);
        return model;
    }


    @Override
    public boolean checkToken(TokenModel model) {
        if (null == model) {
            return false;
        }
        String token;
        try {
            token = redis.boundValueOps(model.getUsername()).get().toString();
        } catch (NullPointerException e) {
            throw new AuthExpireException(e);
        }
        if (null == token || !token.equals(model.getToken())) {
            return false;
        }
        // 如果鉴权成功，延长过期时间
        // redis.boundValueOps(model.getUsername()).expire();
        return true;
    }

    @Override
    public String createAuth(TokenModel model) {
        String username = model.getUsername();
        String token = model.getToken();
        String auth = username + "_" + token;
        String encryptAuth = DESUtils.encrypt(auth, key);
        return encryptAuth;

    }

    @Override
    public TokenModel getToken(String encryptAuth) {
        if (encryptAuth == null || encryptAuth.length() == 0) {
            return null;
        }
        // 对加密后的auth进行解密
        String authentication = DESUtils.decrypt(encryptAuth, key);
        if (null == authentication || 0 == authentication.length()) {
            return null;
        }
        String[] param = authentication.split("_");
        if (2 != param.length) {
            return null;
        }
        return new TokenModel(param[0], param[1]);
    }

    @Override
    public void deleteToken(String username) {
        redis.delete(username);
    }
}
