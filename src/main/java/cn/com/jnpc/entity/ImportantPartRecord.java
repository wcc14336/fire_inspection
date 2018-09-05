package cn.com.jnpc.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by cc on 2018/7/13.
 */
@Entity
@Table(name = "importantPartRecord")
public class ImportantPartRecord {

    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String unit;
    private String factoryBuilding;
    private String location;
    private Integer state;
    private String measurements;
    private String checktime;
    private String checker;
    @OneToMany(targetEntity=IPRDefect.class)
    @Cascade(value={CascadeType.SAVE_UPDATE})
    @JoinColumn(name="importantPartRecord_id")
    private Set<IPRDefect> IPRdefects = new HashSet<IPRDefect>();

    private Integer attachment=0;


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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
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

    public Set<IPRDefect> getIPRdefects() {
        return IPRdefects;
    }

    public void setIPRdefects(Set<IPRDefect> IPRdefects) {
        this.IPRdefects = IPRdefects;
    }
}
