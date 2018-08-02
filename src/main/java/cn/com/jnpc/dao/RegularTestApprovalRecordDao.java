package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularTestApprovalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by cc on 2018/8/2.
 */
public interface RegularTestApprovalRecordDao extends JpaRepository<RegularTestApprovalRecord,String>{
    @Query("select r from RegularTestApprovalRecord r where r.id=?1")
    RegularTestApprovalRecord findByid(String id);
}
