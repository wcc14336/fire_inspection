package cn.com.jnpc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by cc on 2018/7/19.
 */
@Entity
@Table(name = "FDdefect")
//@GenericGenerator(name="genID", strategy="increment")
public class FDDefect {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String defectdesc;
    private String method;
    private String tracenumber;
    //多对一，@JoinColumn与@column类似，指定映射的数据库字段
    @ManyToOne(targetEntity = FireproofdoorRecord.class)
    @JoinColumn(name="fireproofdoorRecord_id",updatable=false)
    private FireproofdoorRecord fireproofdoorRecord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public FireproofdoorRecord getFireproofdoorRecord() {
        return fireproofdoorRecord;
    }

    public void setFireproofdoorRecord(FireproofdoorRecord fireproofdoorRecord) {
        this.fireproofdoorRecord = fireproofdoorRecord;
    }
}
