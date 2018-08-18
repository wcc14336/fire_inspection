package cn.com.jnpc.dao;

import cn.com.jnpc.entity.FWDefect;
import cn.com.jnpc.entity.FireworkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/19.
 */
public interface FWDefectDao extends JpaRepository<FWDefect,String >{
    @Query("select f from FWDefect f where f.fireworkRecord=?1")
    List<FWDefect> findByrecordid(FireworkRecord record);
}
