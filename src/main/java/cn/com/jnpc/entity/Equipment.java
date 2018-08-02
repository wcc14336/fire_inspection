package cn.com.jnpc.entity;



import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by cc on 2018/7/6.
 */
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    private String kks;
    @NotNull
    private String unit;
    @NotNull
    private String factoryBuilding;
    @NotNull
    private String location;
    @NotNull
    private String name;
    @NotNull
    private String category;

    private String configurationtime;
    private String checkcycle;
    private String testcycle;
    private String replacecycle;
    private String enteringman;
    private String updatetime;


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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getConfigurationtime() {
        return configurationtime;
    }

    public void setConfigurationtime(String configurationtime) {
        this.configurationtime = configurationtime;
    }

    public String getCheckcycle() {
        return checkcycle;
    }

    public void setCheckcycle(String checkcycle) {
        this.checkcycle = checkcycle;
    }

    public String getTestcycle() {
        return testcycle;
    }

    public void setTestcycle(String testcycle) {
        this.testcycle = testcycle;
    }

    public String getReplacecycle() {
        return replacecycle;
    }

    public void setReplacecycle(String replacecycle) {
        this.replacecycle = replacecycle;
    }

    public String getEnteringman() {
        return enteringman;
    }

    public void setEnteringman(String enteringman) {
        this.enteringman = enteringman;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
