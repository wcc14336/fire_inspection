package cn.com.jnpc.service;

import cn.com.jnpc.dao.ImportantPartRecordDao;
import cn.com.jnpc.entity.ImportantPartRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/13.
 */
@Service
public class ImportantPartRecordService {
    @Autowired
    private ImportantPartRecordDao importantPartRecordDao;


    public List<ImportantPartRecord> findAllByDateAndName(String date, String username,String unit) {
        return importantPartRecordDao.findAllByDateAndName(date,username,unit);
    }

    public void save(ImportantPartRecord importantPartRecord) {
        importantPartRecordDao.save(importantPartRecord);
    }

    public ImportantPartRecord findoneById(String recordid) {
        return importantPartRecordDao.findOneById(recordid);
    }
    @Transactional
    public void updateattachment(Integer i,String recordid) {
        importantPartRecordDao.updateattachment(i,recordid);
    }

    public List<ImportantPartRecord> findByChecktimeAndUnit(String unit, String starttime, String endtime) {
        return importantPartRecordDao.findByChecktimeAndUnit(unit,starttime,endtime);
    }
}
