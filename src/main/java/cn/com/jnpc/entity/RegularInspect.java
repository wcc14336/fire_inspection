package cn.com.jnpc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cc on 2018/7/27.
 */
@Entity
@Table(name = "regularinspect")
public class RegularInspect {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String unit;
    private Integer year;
    private String checkbasis;
    private String checkproject;
    private String checkfrequency;
    private String planchecker;
    private String planbegin;
    private String planend;
    private String begintime;
    private String endtime;
    private String reason;
    private String checker;
    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCheckbasis() {
        return checkbasis;
    }

    public void setCheckbasis(String checkbasis) {
        this.checkbasis = checkbasis;
    }

    public String getCheckproject() {
        return checkproject;
    }

    public void setCheckproject(String checkproject) {
        this.checkproject = checkproject;
    }

    public String getCheckfrequency() {
        return checkfrequency;
    }

    public void setCheckfrequency(String checkfrequency) {
        this.checkfrequency = checkfrequency;
    }

    public String getPlanchecker() {
        return planchecker;
    }

    public void setPlanchecker(String planchecker) {
        this.planchecker = planchecker;
    }

    public String getPlanbegin() {
        return planbegin;
    }

    public void setPlanbegin(String planbegin) {
        this.planbegin = planbegin;
    }

    public String getPlanend() {
        return planend;
    }

    public void setPlanend(String planend) {
        this.planend = planend;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
