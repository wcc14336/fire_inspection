package cn.com.jnpc.dao;

import cn.com.jnpc.entity.ImportantPartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/13.
 */
public interface ImportantPartRecordDao extends JpaRepository<ImportantPartRecord,String >{
    @Query("select i from ImportantPartRecord i where i.checktime=?1 and i.checker=?2 and i.unit=?3")
    List<ImportantPartRecord> findAllByDateAndName(String date, String username,String unit);
    @Query("select i from ImportantPartRecord i where i.id=?1")
    ImportantPartRecord findOneById(String id);
    @Modifying
    @Query("update ImportantPartRecord i set i.attachment=?1 where i.id=?2")
    void updateattachment(Integer i,String recordid);
    @Query("select i from ImportantPartRecord i where i.unit=?1 and i.checktime between ?2 and ?3")
    List<ImportantPartRecord> findByChecktimeAndUnit(String unit, String starttime, String endtime);
    @RestResource(path = "findByDayAndCheckerAndLocation",rel = "findByDayAndCheckerAndLocation")
    @Query("select i from ImportantPartRecord i where i.unit=?1 and i.factoryBuilding=?2 and i.location=?3 and i.checker=?4 and i.checktime=?5")
    ImportantPartRecord findByDayAndCheckerAndLocation(@Param("unit") String unit,@Param("factoryBuilding") String factoryBuilding,@Param("location") String location,@Param("checker") String checker,@Param("checktime") String checktime);
    @Modifying
    @Transactional
    @RestResource(path = "updateimportantpartrecord",rel = "updateimportantpartrecord")
    @Query("update ImportantPartRecord i set i.state=?6,i.measurements=?7 where i.unit=?1 and i.factoryBuilding=?2 and i.location=?3 and i.checker=?4 and i.checktime=?5")
    int updateimportantpartrecord(@Param("unit") String unit, @Param("factoryBuilding") String factoryBuilding,@Param("location") String location,@Param("checker") String checker,@Param("checktime") String checktime,@Param("state") Integer state,@Param("measurements") String measurements);
}
