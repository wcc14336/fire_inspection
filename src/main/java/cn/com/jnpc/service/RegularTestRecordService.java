package cn.com.jnpc.service;

import cn.com.jnpc.dao.RegularTestRecordDao;
import cn.com.jnpc.entity.RegularTestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/8/6.
 */
@Service
public class RegularTestRecordService {
    @Autowired
    private RegularTestRecordDao regularTestRecordDao;

    public List<RegularTestRecord> findByTaskid(String taskid) {
        return regularTestRecordDao.findByTaskid(taskid);
    }

    public void save(RegularTestRecord regularTestRecord) {
        regularTestRecordDao.save(regularTestRecord);
    }

    public String findbegintime(String taskid) {
        return regularTestRecordDao.findbegintime(taskid);
    }

    public String findendtime(String taskid) {
        return regularTestRecordDao.findendtime(taskid);
    }

    public List<String> findAllcheckers(String taskid) {
        return regularTestRecordDao.findAllCheckers(taskid);
    }

    public RegularTestRecord findbyid(String recordid) {
        return regularTestRecordDao.findbyid(recordid);
    }
    @Transactional
    public void updateattachment(Integer i, String recordid) {
        regularTestRecordDao.updateattachment(1,recordid);
    }

    public List<RegularTestRecord> findUndoByTaskid(String taskid, int i) {
        return regularTestRecordDao.findUndoByTaskid(taskid,i);
    }

    public List<RegularTestRecord> findUndoBytaskidAndkks(String taskid, String kks) {
        return regularTestRecordDao.findByTaskidAndKks(taskid,kks);
    }

    public void updaterecordById(String id, String checker, String checktime, Integer state, int i) {
        regularTestRecordDao.updaterecordById(id,checker,checktime,state,i);
    }
}
