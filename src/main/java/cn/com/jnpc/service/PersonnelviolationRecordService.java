package cn.com.jnpc.service;

import cn.com.jnpc.dao.PersonnelviolationRecordDao;
import cn.com.jnpc.entity.PersonnelviolationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
@Service
public class PersonnelviolationRecordService {
    @Autowired
    private PersonnelviolationRecordDao personnelviolationRecordDao;

    public List<PersonnelviolationRecord> findAllByDateAndName(String date, String username,String unit) {
        return personnelviolationRecordDao.findAllByDateAndName(date,username,unit);
    }

    public void save(PersonnelviolationRecord personnelviolationRecord) {
        personnelviolationRecordDao.save(personnelviolationRecord);
    }

    public PersonnelviolationRecord findoneById(String recordid) {
        return personnelviolationRecordDao.findoneById(recordid);
    }
    @Transactional
    public void updateattachment(int i, String recordid) {
        personnelviolationRecordDao.updateattachment(i,recordid);
    }

    public List<PersonnelviolationRecord> findByUnitAndChecktime(String unit, String starttime, String endtime) {
        return personnelviolationRecordDao.findByUnitAndChecktime(unit,starttime,endtime);
    }

    public PersonnelviolationRecord findByUnitAndFactoryBuildingAndLocationAndCheckerAndChecktimeAndPassnumber(String unit, String factoryBuilding, String location, String checker, String checktime,String passnumber) {
        return personnelviolationRecordDao.findByUnitAndFactoryBuildingAndLocationAndCheckAndPassnumber(unit,factoryBuilding,location,checker,checktime,passnumber);
    }
}
