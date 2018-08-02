package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularInspectApprovalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/8/1.
 */
public interface RegularInspectApprovalRecordDao extends JpaRepository<RegularInspectApprovalRecord,String> {
    @Query("select r from RegularInspectApprovalRecord r where r.id=?1")
    RegularInspectApprovalRecord findByid(String id);
    @Query("select r from RegularInspectApprovalRecord r where r.submitstate=1 and r.approvalstate=0")
    List<RegularInspectApprovalRecord> findBycondition();
}
