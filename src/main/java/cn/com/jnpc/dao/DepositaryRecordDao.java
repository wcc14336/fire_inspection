package cn.com.jnpc.dao;

import cn.com.jnpc.entity.DepositaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
public interface DepositaryRecordDao extends JpaRepository<DepositaryRecord,String>{
    @Query("select d from DepositaryRecord d where d.checktime=?1 and d.checker=?2 and d.unit=?3")
    List<DepositaryRecord> findAllByDateAndName(String date, String username,String unit);
    @Query("select d from DepositaryRecord d where d.id=?1")
    DepositaryRecord findoneById(String recordid);
    @Modifying
    @Query("update DepositaryRecord d set d.attachment=?1 where d.id=?2")
    void updateattachment(int i, String recordid);
}
