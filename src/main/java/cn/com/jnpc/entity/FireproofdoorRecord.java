package cn.com.jnpc.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cc on 2018/7/16.
 */
@Entity
@Table(name = "fireproofdoorrecord")
public class FireproofdoorRecord {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String unit;
    private String factoryBuilding;
    private String location;
    private String kks;
    private String name;
    private Integer state;
    private Integer attachment=0;
    private String checktime;
    private String checker;
    @OneToMany(targetEntity=FDDefect.class)
    @Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name="fireproofdoorRecord_id")
    private Set<FDDefect> fdDefects = new HashSet<FDDefect>();

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }

    public Set<FDDefect> getFdDefects() {
        return fdDefects;
    }

    public void setFdDefects(Set<FDDefect> fdDefects) {
        this.fdDefects = fdDefects;
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

    public String getKks() {
        return kks;
    }

    public void setKks(String kks) {
        this.kks = kks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


}
