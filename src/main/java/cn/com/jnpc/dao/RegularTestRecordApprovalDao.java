package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularTestRecordApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by cc on 2018/8/3.
 */
public interface RegularTestRecordApprovalDao extends JpaRepository<RegularTestRecordApproval,String>{
    @Query("select r from RegularTestRecordApproval r where r.id=?1")
    RegularTestRecordApproval findByid(String id);
    @Query("select r from RegularTestRecordApproval r where r.submitstate=1 and r.approvalstate=0")
    List<RegularTestRecordApproval> findbyCondition();
}
