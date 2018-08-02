package cn.com.jnpc.dao;

import cn.com.jnpc.entity.FireworkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
public interface FireworkRecordDao extends JpaRepository<FireworkRecord,String >{
    @Query("select f from FireworkRecord f where f.checktime=?1 and f.checker=?2 and f.unit=?3")
    List<FireworkRecord> findAllByDateAndName(String date, String username,String unit);
    @Query("select f from FireworkRecord f where f.id=?1")
    FireworkRecord findoneById(String id);
    @Modifying
    @Query("update FireworkRecord f set f.attachment=?1 where f.id=?2")
    void updateattachment(int i, String recordid);
}
