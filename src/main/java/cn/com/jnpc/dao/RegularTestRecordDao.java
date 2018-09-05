package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularTestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/8/6.
 */
public interface RegularTestRecordDao extends JpaRepository<RegularTestRecord,String>{
    @Query("select r from RegularTestRecord r where r.taskid=?1")
    List<RegularTestRecord> findByTaskid(String taskid);
    @Query("select MIN(r.checktime) from RegularTestRecord r where r.taskid=?1")
    String findbegintime(String taskid);
    @Query("select MAX(r.checktime) from RegularTestRecord r where r.taskid=?1")
    String findendtime(String taskid);
    @Query("select distinct r.checker from RegularTestRecord r where r.taskid=?1")
    List<String> findAllCheckers(String taskid);
    @Query("select r from RegularTestRecord r where r.id=?1")
    RegularTestRecord findbyid(String recordid);
    @Modifying
    @Query("update RegularTestRecord r set r.attachment=?1 where r.id=?2")
    void updateattachment(int i, String recordid);
    @Query("select r from RegularTestRecord r where r.taskid=?1 and r.ifchecked=?2")
    List<RegularTestRecord> findUndoByTaskid(String taskid, int i);
    @Query("select r from RegularTestRecord r where r.taskid=?1 and r.kks like %?2% and r.ifchecked=0")
    List<RegularTestRecord> findByTaskidAndKks(String taskid, String kks);
    @Modifying
    @Transactional
    @Query("update RegularTestRecord r set r.checker=?2,r.checktime=?3,r.state=?4,r.ifchecked=?5 where r.id=?1")
    void updaterecordById(String id, String checker, String checktime, Integer state, int i);
}
