package cn.com.jnpc.dao;

import cn.com.jnpc.entity.FireworkconformRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

/**
 * Created by cc on 2018/7/23.
 */
public interface FireworkconformRecordDao extends JpaRepository<FireworkconformRecord,String>{


    Page<FireworkconformRecord> findAll(@Nullable Specification<FireworkconformRecord> specification, Pageable pageable);

    @Query("select f from FireworkconformRecord f where f.id=?1")
    FireworkconformRecord findoneById(String recordid);
    @Modifying
    @Query("update FireworkconformRecord f set f.attachment=?1 where f.id=?2")
    void updateattachment(int i, String recordid);
}
