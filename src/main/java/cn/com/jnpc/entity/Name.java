package cn.com.jnpc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by cc on 2018/7/6.
 */
@Entity
@Table(name = "name")
public class Name {
    @Id
    private String id;
    @NotNull
    private String name;
    private String namedesx;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamedesx() {
        return namedesx;
    }

    public void setNamedesx(String namedesx) {
        this.namedesx = namedesx;
    }
}
