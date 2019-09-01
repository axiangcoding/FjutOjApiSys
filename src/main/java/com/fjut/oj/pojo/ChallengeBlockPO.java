package com.fjut.oj.pojo;

/**
 * 挑战模块
 *
 * @author axiang
 */
public class ChallengeBlockPO {
    private Integer id;
    private String name;
    private Integer gro;
    private String text;
    private Integer isEditing;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGro() {
        return gro;
    }

    public void setGro(Integer gro) {
        this.gro = gro;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getIsEditing() {
        return isEditing;
    }

    public void setIsEditing(Integer isEditing) {
        this.isEditing = isEditing;
    }

    @Override
    public String toString() {
        return "ChallengeBlockPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gro=" + gro +
                ", text='" + text + '\'' +
                ", isEditing=" + isEditing +
                '}';
    }
}
