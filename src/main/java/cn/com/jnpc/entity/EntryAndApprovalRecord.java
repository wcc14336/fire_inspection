package cn.com.jnpc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cc on 2018/7/16.
 */
@Entity
@Table(name = "entryandapprovalrecord")
public class EntryAndApprovalRecord {
    @Id
    private String id;

    private String submiter;
    private String submitdate;
    private Integer submitstate;
    private Integer approvalstate;
    private String approvaler;
    private String approvaldate;
    private Integer approvalresult;
    private String comments;
    private String unit;
    private Integer year;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubmiter() {
        return submiter;
    }

    public void setSubmiter(String submiter) {
        this.submiter = submiter;
    }

    public String getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(String submitdate) {
        this.submitdate = submitdate;
    }

    public Integer getSubmitstate() {
        return submitstate;
    }

    public void setSubmitstate(Integer submitstate) {
        this.submitstate = submitstate;
    }

    public Integer getApprovalstate() {
        return approvalstate;
    }

    public void setApprovalstate(Integer approvalstate) {
        this.approvalstate = approvalstate;
    }

    public String getApprovaler() {
        return approvaler;
    }

    public void setApprovaler(String approvaler) {
        this.approvaler = approvaler;
    }

    public String getApprovaldate() {
        return approvaldate;
    }

    public void setApprovaldate(String approvaldate) {
        this.approvaldate = approvaldate;
    }

    public Integer getApprovalresult() {
        return approvalresult;
    }

    public void setApprovalresult(Integer approvalresult) {
        this.approvalresult = approvalresult;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
