package cn.com.jnpc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cc on 2018/7/31.
 */
@Entity
@Table(name = "regularinspectrecord")
public class RegularInspectRecord {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String taskid;
    private String unit;
    private Integer year;
    private String factoryBuilding;
    private String location;
    private String kks;
    private String name;
    private String checkcycle;
    private Integer ifchecked;
    private Integer state;
    private String checker;
    private String checktime;
    private String defectdesc;
    private String method;
    private String tracenumber;
    private Integer attachment=0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
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

    public String getCheckcycle() {
        return checkcycle;
    }

    public void setCheckcycle(String checkcycle) {
        this.checkcycle = checkcycle;
    }

    public Integer getIfchecked() {
        return ifchecked;
    }

    public void setIfchecked(Integer ifchecked) {
        this.ifchecked = ifchecked;
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

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getDefectdesc() {
        return defectdesc;
    }

    public void setDefectdesc(String defectdesc) {
        this.defectdesc = defectdesc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTracenumber() {
        return tracenumber;
    }

    public void setTracenumber(String tracenumber) {
        this.tracenumber = tracenumber;
    }

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }
}
