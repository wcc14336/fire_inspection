package cn.com.jnpc.dao;

import cn.com.jnpc.entity.FireworkconformRecord;
import cn.com.jnpc.entity.MaintenanceRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Created by cc on 2018/8/10.
 */
public interface MaintenanceRecordDao extends JpaRepository<MaintenanceRecord,String>{
    Page<MaintenanceRecord> findAll(@Nullable Specification<MaintenanceRecord> specification, Pageable pageable);
    @Modifying
    @Query("update MaintenanceRecord m set m.attachment=?1 where m.id=?2")
    void updateattachment(int i, String recordid);
    @Modifying
    @Query("update MaintenanceRecord m set m.ifcommit=1 where m.id=?1")
    void updateifconfig(String id);
    @Modifying
    @Query("update MaintenanceRecord m set m.ifpassed=1 where m.id=?1")
    void recordpassed(String id);
    @Modifying
    @Query("update MaintenanceRecord m set m.ifcommit=0 where m.id=?1")
    void recordnotpassed(String id);

    List<MaintenanceRecord> findAll(Specification<MaintenanceRecord> specification, Sort sort);
    @Query("select m from MaintenanceRecord m where m.id=?1")
    MaintenanceRecord findByid(String id);
}
