package com.fjut.oj.pojo;

import java.util.Date;

/**
 * @author axiang [20191008]
 */
public class StatusCountBO {
    private Integer totalCount;
    private Integer acCount;
    private Date submitDay;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAcCount() {
        return acCount;
    }

    public void setAcCount(Integer acCount) {
        this.acCount = acCount;
    }

    public Date getSubmitDay() {
        return submitDay;
    }

    public void setSubmitDay(Date submitDay) {
        this.submitDay = submitDay;
    }

    @Override
    public String toString() {
        return "StatusCountBO{" +
                "totalCount=" + totalCount +
                ", submitDay=" + submitDay +
                '}';
    }
}
