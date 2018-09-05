package cn.com.jnpc.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cc on 2018/7/16.
 */
@Entity
@Table(name = "fireworkrecord")
public class FireworkRecord {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String unit;
    private String factoryBuilding;
    private String location;
    private String fireworkNumber;
    private String jobmanager;
    private Integer state;
    private String checktime;
    private String checker;
    private Integer attachment=0;

    @OneToMany(targetEntity=FWDefect.class)
    @Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name="fireworkRecord_id")
    private Set<FWDefect> FWDefects = new HashSet<FWDefect>();

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }

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

    public String getFactoryBuilding() {
        return factoryBuilding;
    }

    public void setFactoryBuilding(String factoryBuilding) {
        this.factoryBuilding = factoryBuilding;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFireworkNumber() {
        return fireworkNumber;
    }

    public void setFireworkNumber(String fireworkNumber) {
        this.fireworkNumber = fireworkNumber;
    }

    public String getJobmanager() {
        return jobmanager;
    }

    public void setJobmanager(String jobmanager) {
        this.jobmanager = jobmanager;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }
    public Set<FWDefect> getFWDefects() {
        return FWDefects;
    }

    public void setFWDefects(Set<FWDefect> FWDefects) {
        this.FWDefects = FWDefects;
    }

}
