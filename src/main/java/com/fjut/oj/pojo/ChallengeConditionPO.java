package com.fjut.oj.pojo;

/**
 * 挑战模式条件
 *
 * @author axiang [20190901]
 */
public class ChallengeConditionPO {

    private Integer id;
    private Integer belongBlockId;
    private Integer type;
    private Integer par;
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBelongBlockId() {
        return belongBlockId;
    }

    public void setBelongBlockId(Integer belongBlockId) {
        this.belongBlockId = belongBlockId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPar() {
        return par;
    }

    public void setPar(Integer par) {
        this.par = par;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ChallengeConditionPO{" +
                "id=" + id +
                ", belongBlockId=" + belongBlockId +
                ", type=" + type +
                ", par=" + par +
                ", num=" + num +
                '}';
    }
}
