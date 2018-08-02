package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/8/2.
 */
public interface RegularTestDao extends JpaRepository<RegularTest,String>{
    @Query("select r from RegularTest r where r.unit=?1 and r.year=?2")
    List<RegularTest> findByUnitAndYear(String unit, Integer year);
}
