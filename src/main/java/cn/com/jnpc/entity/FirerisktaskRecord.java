package cn.com.jnpc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cc on 2018/7/26.
 */
@Entity
@Table(name = "firerisktaskrecord")
public class FirerisktaskRecord {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String unit;
    private String factoryBuilding;
    private String location;
    private String tracenumber;
    private String jobmanager;
    private String fireworker;
    private String fireinspecter;
    private String measurement;
    private String measuretime;
    private String inspecter1;
    private String measurement1;
    private String measuretime1;
    private String inspecter2;
    private String measurement2;
    private String measuretime2;
    private Integer state;
    private Integer attachment=0;

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

    public String getTracenumber() {
        return tracenumber;
    }

    public void setTracenumber(String tracenumber) {
        this.tracenumber = tracenumber;
    }

    public String getJobmanager() {
        return jobmanager;
    }

    public void setJobmanager(String jobmanager) {
        this.jobmanager = jobmanager;
    }

    public String getFireworker() {
        return fireworker;
    }

    public void setFireworker(String fireworker) {
        this.fireworker = fireworker;
    }

    public String getFireinspecter() {
        return fireinspecter;
    }

    public void setFireinspecter(String fireinspecter) {
        this.fireinspecter = fireinspecter;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getMeasuretime() {
        return measuretime;
    }

    public void setMeasuretime(String measuretime) {
        this.measuretime = measuretime;
    }

    public String getInspecter1() {
        return inspecter1;
    }

    public void setInspecter1(String inspecter1) {
        this.inspecter1 = inspecter1;
    }

    public String getMeasurement1() {
        return measurement1;
    }

    public void setMeasurement1(String measurement1) {
        this.measurement1 = measurement1;
    }

    public String getMeasuretime1() {
        return measuretime1;
    }

    public void setMeasuretime1(String measuretime1) {
        this.measuretime1 = measuretime1;
    }

    public String getInspecter2() {
        return inspecter2;
    }

    public void setInspecter2(String inspecter2) {
        this.inspecter2 = inspecter2;
    }

    public String getMeasurement2() {
        return measurement2;
    }

    public void setMeasurement2(String measurement2) {
        this.measurement2 = measurement2;
    }

    public String getMeasuretime2() {
        return measuretime2;
    }

    public void setMeasuretime2(String measuretime2) {
        this.measuretime2 = measuretime2;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAttachment() {
        return attachment;
    }

    public void setAttachment(Integer attachment) {
        this.attachment = attachment;
    }
}
