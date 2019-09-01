package com.fjut.oj.pojo;

/**
 * 挑战模块开启表
 *
 * @author axiang [20190901]
  */

public class ChallengeOpenblockPO {

    private String  username;
    private Integer block;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "ChallengeOpenblockPO{" +
                "username='" + username + '\'' +
                ", block=" + block +
                '}';
    }
}
