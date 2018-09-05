package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularTest;
import cn.com.jnpc.entity.RegularTestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/8/2.
 */
public interface RegularTestDao extends JpaRepository<RegularTest,String>{
    @Query("select r from RegularTest r where r.unit=?1 and r.year=?2")
    List<RegularTest> findByUnitAndYear(String unit, Integer year);
    @Query("select r from RegularTest r where r.state=?2 and r.planchecker like %?1% ORDER BY r.planbegin ASC")
    List<RegularTest> findMyUndonetask(String username, Integer i);
    @Query("select r from RegularTest r where r.id=?1")
    RegularTest findbyid(String taskid);
    @Query("select r from RegularTest r where r.state=?2 and r.planbegin<=?3 and r.planchecker like %?1% order by r.planbegin ASC")
    List<RegularTest> findrecentUndotesttask(String username, int i, String beforedate);

}
