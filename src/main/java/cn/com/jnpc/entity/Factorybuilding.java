package cn.com.jnpc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by cc on 2018/7/6.
 */
@Entity
@Table(name = "factorybuilding")
public class Factorybuilding {
    @Id
    private String id;
    @NotNull
    private String factoryname;

    private String factorydesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactoryname() {
        return factoryname;
    }

    public void setFactoryname(String factoryname) {
        this.factoryname = factoryname;
    }

    public String getFactorydesc() {
        return factorydesc;
    }

    public void setFactorydesc(String factorydesc) {
        this.factorydesc = factorydesc;
    }
}
