package cn.com.jnpc.dao;

import cn.com.jnpc.entity.EntryAndApprovalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/30.
 */
public interface EntryAndApprovalRecordDao extends JpaRepository<EntryAndApprovalRecord,String>{
    @Query("select e from EntryAndApprovalRecord e where e.id=?1")
    EntryAndApprovalRecord findByid(String id);

    @Query("select e from EntryAndApprovalRecord e where e.submitstate=1 and e.approvalstate=0")
    List<EntryAndApprovalRecord> findBycondition();
}
