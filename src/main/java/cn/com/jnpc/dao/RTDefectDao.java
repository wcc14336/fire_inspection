package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RTDefect;
import cn.com.jnpc.entity.RegularTestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/8/6.
 */
public interface RTDefectDao extends JpaRepository<RTDefect,String>{
    @Query("select r from RTDefect r where r.regularTestRecord=?1")
    List<RTDefect> findByRecord(RegularTestRecord regulartestRecord);
}
