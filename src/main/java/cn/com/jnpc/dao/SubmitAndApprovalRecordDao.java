package cn.com.jnpc.dao;

import cn.com.jnpc.entity.SubmitAndApprovalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
public interface SubmitAndApprovalRecordDao extends JpaRepository<SubmitAndApprovalRecord,String>{
    @Query("select s from SubmitAndApprovalRecord s where s.id=?1")
    SubmitAndApprovalRecord findOneById(String id);
    @Query("select s from SubmitAndApprovalRecord s where s.approvalstate=0")
    List<SubmitAndApprovalRecord> findApprovalstateis0();
}
