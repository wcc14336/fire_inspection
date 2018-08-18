package cn.com.jnpc.dao;

import cn.com.jnpc.entity.OtherRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
public interface OtherRecordDao extends JpaRepository<OtherRecord,String>{
    @Query("select o from OtherRecord o where o.checktime=?1 and o.checker=?2 and o.unit=?3")
    List<OtherRecord> findAllByDateAndName(String date, String username,String unit);
    @Query("select o from OtherRecord o where o.id=?1")
    OtherRecord findoneById(String recordid);
    @Modifying
    @Query("update OtherRecord o set o.attachment=?1 where o.id=?2")
    void updateattachment(int i, String recordid);
    @Query("select o from OtherRecord o where o.unit=?1 and o.checktime between ?2 and ?3")
    List<OtherRecord> findByUnitAndChecktime(String unit, String starttime, String endtime);
}
