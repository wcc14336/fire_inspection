package cn.com.jnpc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cc on 2018/8/10.
 */
@Entity
@Table(name = "maintenancerecord")
public class MaintenanceRecord {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String unit;
    private String factoryBuilding;
    private String location;
    private String kks;
    private String name;
    private String maintainer;
    private String maintaintime;
    private String number;
    private Integer attachment=0;
    private Integer ifcommit;
    private Integer ifpassed;

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

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getMaintaintime() {
        return maintaintime;
    }

    public void setMaintaintime(String maintaintime) {
        this.maintaintime = maintaintime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }

    public Integer getIfcommit() {
        return ifcommit;
    }

    public void setIfcommit(Integer ifcommit) {
        this.ifcommit = ifcommit;
    }

    public Integer getIfpassed() {
        return ifpassed;
    }

    public void setIfpassed(Integer ifpassed) {
        this.ifpassed = ifpassed;
    }
}
