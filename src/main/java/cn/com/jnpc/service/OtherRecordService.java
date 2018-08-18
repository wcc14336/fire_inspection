package cn.com.jnpc.service;

import cn.com.jnpc.dao.OtherRecordDao;
import cn.com.jnpc.entity.OtherRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
@Service
public class OtherRecordService {
    @Autowired
    private OtherRecordDao otherRecordDao;

    public List<OtherRecord> findAllByDateAndName(String date, String username,String unit) {
        return otherRecordDao.findAllByDateAndName(date,username,unit);
    }

    public void save(OtherRecord otherRecord) {
        otherRecordDao.save(otherRecord);
    }

    public OtherRecord finfoneById(String recordid) {
        return otherRecordDao.findoneById(recordid);
    }
    @Transactional
    public void updateattachment(int i, String recordid) {
        otherRecordDao.updateattachment(i,recordid);
    }

    public List<OtherRecord> findByUnitAndChecktime(String unit, String starttime, String endtime) {
        return otherRecordDao.findByUnitAndChecktime(unit,starttime,endtime);
    }
}
