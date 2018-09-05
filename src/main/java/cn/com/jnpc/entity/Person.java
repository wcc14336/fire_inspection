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
@Table(name = "person")
public class Person {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String number;
    private String name;
    private String email;
    private Integer inspect;
    private Integer test;
    private Integer maintance;
    private Integer engineer;
    private Integer admin;

    public Integer getInspect() {
        return inspect;
    }

    public void setInspect(Integer inspect) {
        this.inspect = inspect;
    }

    public Integer getTest() {
        return test;
    }

    public void setTest(Integer test) {
        this.test = test;
    }

    public Integer getMaintance() {
        return maintance;
    }

    public void setMaintance(Integer maintance) {
        this.maintance = maintance;
    }

    public Integer getEngineer() {
        return engineer;
    }

    public void setEngineer(Integer engineer) {
        this.engineer = engineer;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
