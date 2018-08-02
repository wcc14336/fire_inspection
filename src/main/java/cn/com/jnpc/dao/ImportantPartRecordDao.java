package cn.com.jnpc.dao;

import cn.com.jnpc.entity.ImportantPartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/13.
 */
public interface ImportantPartRecordDao extends JpaRepository<ImportantPartRecord,String >{
    @Query("select i from ImportantPartRecord i where i.checktime=?1 and i.checker=?2 and i.unit=?3")
    List<ImportantPartRecord> findAllByDateAndName(String date, String username,String unit);
    @Query("select i from ImportantPartRecord i where i.id=?1")
    ImportantPartRecord findOneById(String id);
    @Modifying
    @Query("update ImportantPartRecord i set i.attachment=?1 where i.id=?2")
    void updateattachment(Integer i,String recordid);
}
