package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularInspectRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/31.
 */
public interface RegularInspectRecordDao extends JpaRepository<RegularInspectRecord,String>{
    @Query("select r from RegularInspectRecord r where r.taskid=?1")
    List<RegularInspectRecord> findByTaskid(String taskid);
    @Modifying
    @Query("update RegularInspectRecord r set r.attachment=?1 where r.id=?2")
    void updateattachment(int i, String recordid);
    @Query("select MIN(r.checktime) from RegularInspectRecord r where r.taskid=?1")
    String findbegintime(String taskid);
    @Query("select MAX(r.checktime) from RegularInspectRecord r where r.taskid=?1")
    String findendtime(String taskid);
    @Query("select distinct r.checker from RegularInspectRecord r where r.taskid=?1")
    List<String> findAllcheckers(String taskid);
}
