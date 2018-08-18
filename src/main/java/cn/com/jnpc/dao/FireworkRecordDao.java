package cn.com.jnpc.dao;

import cn.com.jnpc.entity.FireworkRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
public interface FireworkRecordDao extends JpaRepository<FireworkRecord,String >{
    @Query("select f from FireworkRecord f where f.checktime=?1 and f.checker=?2 and f.unit=?3")
    List<FireworkRecord> findAllByDateAndName(String date, String username,String unit);
    @Query("select f from FireworkRecord f where f.id=?1")
    FireworkRecord findoneById(String id);
    @Modifying
    @Query("update FireworkRecord f set f.attachment=?1 where f.id=?2")
    void updateattachment(int i, String recordid);
    @Query("select f from FireworkRecord f where f.unit=?1 and f.checktime between ?2 and ?3")
    List<FireworkRecord> findByUnitAndChecktime(String unit, String starttime, String endtime);
    @RestResource(path = "fireworklist",rel = "fireworklist")
    @Query("select f from FireworkRecord f where f.unit=?1 and f.state=?2")
    List<FireworkRecord> findAllByUnitAndState(@Param("unit") String unit,@Param("state") Integer state);
    @Transactional
    @Modifying
    @RestResource(path = "updatefireworkrecord",rel = "updatefireworkrecord")
    @Query("update FireworkRecord f set f.checker=?6,f.checktime=?7,f.state=?8 where f.unit=?1 and f.factoryBuilding=?2 and f.location=?3 and f.fireworkNumber=?4 and f.jobmanager=?5")
    int updatefireworkrecord(@Param("unit") String unit,@Param("factoryBuilding") String factoryBuilding,@Param("location") String location,@Param("fireworkNumber") String fireworkNumber,@Param("jobmanager") String jobmanager,@Param("checker") String checker,@Param("checktime") String checktime,@Param("state") Integer state);

}
