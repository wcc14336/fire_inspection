package cn.com.jnpc.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cc on 2018/7/20.
 */
@Entity
@Table(name = "fireworkconformrecord")
public class FireworkconformRecord {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String unit;
    private String factoryBuilding;
    private String location;
    private String fireworkNumber;
    private String jobmanager;
    private String fireworkman;
    private String fireworkinspecter;
    private String firerisknumber;
    private Integer state;
    private String checker;
    private String checkdate;
    private Integer attachment;
    @OneToMany(targetEntity=FCDefect.class)
    @Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name="fireworkconformRecord_id")
    private Set<FCDefect> fcDefects = new HashSet<FCDefect>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFireworkman() {
        return fireworkman;
    }

    public void setFireworkman(String fireworkman) {
        this.fireworkman = fireworkman;
    }

    public String getFireworkinspecter() {
        return fireworkinspecter;
    }

    public void setFireworkinspecter(String fireworkinspecter) {
        this.fireworkinspecter = fireworkinspecter;
    }

    public String getFirerisknumber() {
        return firerisknumber;
    }

    public void setFirerisknumber(String firerisknumber) {
        this.firerisknumber = firerisknumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(String checkdate) {
        this.checkdate = checkdate;
    }

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }

    public Set<FCDefect> getFcDefects() {
        return fcDefects;
    }

    public void setFcDefects(Set<FCDefect> fcDefects) {
        this.fcDefects = fcDefects;
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
}
