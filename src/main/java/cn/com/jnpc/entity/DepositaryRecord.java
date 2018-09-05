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
@Table(name = "depositaryrecord")
public class DepositaryRecord {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String unit;
    private String factoryBuilding;
    private String location;
    private String checktime;
    private String checker;
    private Integer attachment=0;
    @OneToMany(targetEntity=DPDefect.class)
    @Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name="depositaryRecord_id")
    private Set<DPDefect> dpDefects = new HashSet<DPDefect>();

    public Set<DPDefect> getDpDefects() {
        return dpDefects;
    }

    public void setDpDefects(Set<DPDefect> dpDefects) {
        this.dpDefects = dpDefects;
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

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }
}
