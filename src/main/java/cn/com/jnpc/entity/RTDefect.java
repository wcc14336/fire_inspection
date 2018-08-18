package cn.com.jnpc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by cc on 2018/8/6.
 */
@Entity
@Table(name = "rtdefect")
/*@GenericGenerator(name="genID", strategy="increment")*/
public class RTDefect {
    @Id
    @GenericGenerator(strategy = "uuid",name = "idGenerator")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String defectdesc;
    private String method;
    private String tracenumber;
    //多对一，@JoinColumn与@column类似，指定映射的数据库字段
    @ManyToOne(targetEntity = RegularTestRecord.class)
    @JoinColumn(name="regulartestRecord_id",updatable=false)
    private RegularTestRecord regularTestRecord;

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

    public RegularTestRecord getRegularTestRecord() {
        return regularTestRecord;
    }

    public void setRegularTestRecord(RegularTestRecord regularTestRecord) {
        this.regularTestRecord = regularTestRecord;
    }
}
