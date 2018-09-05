package cn.com.jnpc.dao;

import cn.com.jnpc.entity.FirerisktaskRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Created by cc on 2018/7/26.
 */
public interface FirerisktaskRecordDao extends JpaRepository<FirerisktaskRecord,String>{
    Page<FirerisktaskRecord> findAll(@Nullable Specification<FirerisktaskRecord> specification, Pageable pageable);
    @Modifying
    @Query("update FirerisktaskRecord f set f.attachment=?1 where f.id=?2")
    void updateattachment(int i, String recordid);

    List<FirerisktaskRecord> findAll(Specification<FirerisktaskRecord> specification);

    List<FirerisktaskRecord> findByUnit(String unit);

    List<FirerisktaskRecord> findByUnitAndState(String unit, int i);
    @Query("select f from FirerisktaskRecord f where f.tracenumber=?1")
    FirerisktaskRecord findBytracenumber(String tracenumber);
}
