package cn.com.jnpc.service;

import cn.com.jnpc.dao.DepositaryRecordDao;
import cn.com.jnpc.entity.DepositaryRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
@Service
public class DepositaryRecordService {
    @Autowired
    private DepositaryRecordDao depositaryRecordDao;

    public List<DepositaryRecord> findAllByDateAndName(String date, String username,String unit) {
        return depositaryRecordDao.findAllByDateAndName(date,username,unit);
    }

    public void save(DepositaryRecord depositaryRecord) {
        depositaryRecordDao.save(depositaryRecord);
    }

    public DepositaryRecord findoneById(String recordid) {
        return depositaryRecordDao.findoneById(recordid);
    }
    @Transactional
    public void updateattachment(int i, String recordid) {
        depositaryRecordDao.updateattachment(i,recordid);

    }
}