package cn.com.jnpc.dao;

import cn.com.jnpc.entity.RegularInspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/27.
 */
public interface RegularInspectDao extends JpaRepository<RegularInspect,String>{
    @Query("select r from RegularInspect r where r.unit=?1 and r.year=?2")
    List<RegularInspect> findByUnitAndYear(String unit, Integer year);
    @Query("select r from RegularInspect r where r.state=?2 and r.planchecker like %?1% ORDER BY r.planbegin ASC")
    List<RegularInspect> findMyUndoneInspect(String username, Integer i);
    @Modifying
    @Query("update RegularInspect r set r.state=?2 where r.id=?1")
    void updatestate(String taskid, int i);
    @Query("select r from RegularInspect r where r.id=?1")
    RegularInspect findByid(String taskid);
    @Query("select r from RegularInspect r where r.state=?2 and r.planbegin<=?3 and r.planchecker like %?1% order by r.planbegin ASC")
    List<RegularInspect> findrecentundotask(String username, int i, String beforedate);
}
