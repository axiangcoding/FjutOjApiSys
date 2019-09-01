package com.fjut.oj.pojo;

/**
 * Token的Model类
 *
 * @author axiang [20190705]
 */
public class TokenModel {
    private String username;
    private String token;

    public TokenModel(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
