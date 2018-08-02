package cn.com.jnpc.service;

import cn.com.jnpc.dao.FireworkRecordDao;
import cn.com.jnpc.entity.FireworkRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
@Service
public class FireworkRecordService {
    @Autowired
    private FireworkRecordDao fireworkRecordDao;

    public List<FireworkRecord> findAllByDateAndName(String date, String username,String unit) {
        return fireworkRecordDao.findAllByDateAndName(date,username,unit);
    }

    public void save(FireworkRecord fireworkRecord) {
        fireworkRecordDao.save(fireworkRecord);
    }

    public FireworkRecord findoneById(String id){
        return  fireworkRecordDao.findoneById(id);
    };
    @Transactional
    public void updateattachment(int i, String recordid) {
        fireworkRecordDao.updateattachment(i,recordid);
    }
}
