package cn.com.jnpc.service;

import cn.com.jnpc.dao.FireproofdoorRecordDao;
import cn.com.jnpc.entity.FireproofdoorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
@Service
public class FireproofdoorRecordService {
    @Autowired
    private FireproofdoorRecordDao fireproofdoorRecordDao;

    public List<FireproofdoorRecord> findAllByDateAndName(String date, String username,String unit) {
        return fireproofdoorRecordDao.findAllByDateAndName(date,username,unit);
    }

    public void save(FireproofdoorRecord fireproofdoorRecord) {
        fireproofdoorRecordDao.save(fireproofdoorRecord);
    }

    public FireproofdoorRecord findoneById(String recordid) {
        return fireproofdoorRecordDao.findoneById(recordid);
    }
    @Transactional
    public void updateattachment(int i, String recordid) {
        fireproofdoorRecordDao.updateattachment(i,recordid);
    }

    public List<FireproofdoorRecord> findByUnitAndChecktime(String unit, String starttime, String endtime) {
        return fireproofdoorRecordDao.findByUnitAndChecktime(unit,starttime,endtime);
    }

    public FireproofdoorRecord findBykksAndCheckerAndChecktime(String kks, String checker, String checktime) {
        return fireproofdoorRecordDao.findBykksAndCheckerAndChecktime(kks,checker,checktime);
    }
}
