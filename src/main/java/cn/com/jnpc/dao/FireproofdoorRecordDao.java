package cn.com.jnpc.dao;

import cn.com.jnpc.entity.FireproofdoorRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
public interface FireproofdoorRecordDao extends JpaRepository<FireproofdoorRecord,String> {
    @Query("select f from FireproofdoorRecord f where f.checktime=?1 and f.checker=?2 and f.unit=?3")
    List<FireproofdoorRecord> findAllByDateAndName(String date, String username,String unit);
    @Query("select f from FireproofdoorRecord f where f.id=?1")
    FireproofdoorRecord findoneById(String id);
    @Modifying
    @Query("update FireproofdoorRecord f set f.attachment=?1 where f.id=?2")
    void updateattachment(int i, String recordid);
    @Query("select f from FireproofdoorRecord f where f.unit=?1 and f.checktime between ?2 and ?3")
    List<FireproofdoorRecord> findByUnitAndChecktime(String unit, String starttime, String endtime);
}
