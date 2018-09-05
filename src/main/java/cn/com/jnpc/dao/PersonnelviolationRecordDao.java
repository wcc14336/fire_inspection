package cn.com.jnpc.dao;

import cn.com.jnpc.entity.PersonnelviolationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
public interface PersonnelviolationRecordDao extends JpaRepository<PersonnelviolationRecord,String>{
    @Query("select p from PersonnelviolationRecord p where p.checktime=?1 and p.checker=?2 and p.unit=?3")
    List<PersonnelviolationRecord> findAllByDateAndName(String date, String username,String unit);
    @Query("select p from PersonnelviolationRecord p where p.id=?1")
    PersonnelviolationRecord findoneById(String recordid);
    @Modifying
    @Query("update PersonnelviolationRecord p set p.attachment=?1 where p.id=?2")
    void updateattachment(int i, String recordid);
    @Query("select p from PersonnelviolationRecord p where p.unit=?1 and p.checktime between ?2 and ?3")
    List<PersonnelviolationRecord> findByUnitAndChecktime(String unit, String starttime, String endtime);
    @Query("select p from PersonnelviolationRecord p where p.unit=?1 and p.factoryBuilding=?2 and p.location=?3 and p.checker=?4 and p.checktime=?5 and p.passnumber=?6")
    PersonnelviolationRecord findByUnitAndFactoryBuildingAndLocationAndCheckAndPassnumber(String unit, String factoryBuilding, String location, String checker, String checktime, String passnumber);
}
