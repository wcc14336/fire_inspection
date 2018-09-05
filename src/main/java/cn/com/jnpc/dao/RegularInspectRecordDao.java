package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularInspectRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
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
    @Transactional
    @Modifying
    @Query("delete from RegularInspectRecord r where r.taskid=?1")
    void deleteAllByTaskid(String id);
    @Query("select r from RegularInspectRecord r where r.taskid=?1 and r.ifchecked=?2")
    List<RegularInspectRecord> findUndoByTaskid(String taskid, int i);
    @Query("select r from RegularInspectRecord r where r.id=?1")
    RegularInspectRecord findbyId(String id);
    @Modifying
    @Transactional
    @Query("update RegularInspectRecord r set r.checker=?2,r.checktime=?3,r.defectdesc=?4,r.method=?5,r.state=?6,r.ifchecked=?7 where r.id=?1")
    void updaterecordByid(String id, String checker, String checktime, String defectdesc, String method, Integer state, int ifchecked);
    @Query("select r from RegularInspectRecord r where r.taskid=?1 and r.kks like %?2% and r.ifchecked=0")
    List<RegularInspectRecord> findBytaskAndkks(String taskid, String kks);
}
