package cn.com.jnpc.service;

import cn.com.jnpc.dao.RegularInspectRecordDao;
import cn.com.jnpc.entity.RegularInspectRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/31.
 */
@Service
public class RegularInspectRecordService {
    @Autowired
    private RegularInspectRecordDao regularInspectRecordDao;

    public void save(RegularInspectRecord regularInspectRecord) {
        regularInspectRecordDao.save(regularInspectRecord);
    }

    public List<RegularInspectRecord> findByTaskid(String taskid) {
        return regularInspectRecordDao.findByTaskid(taskid);
    }
    @Transactional
    public void updateattachment(int i, String recordid) {
        regularInspectRecordDao.updateattachment(i,recordid);
    }

    public String findbegintime(String taskid) {
        return regularInspectRecordDao.findbegintime(taskid);
    }

    public String findendtime(String taskid) {
        return regularInspectRecordDao.findendtime(taskid);
    }

    public List<String> findAllcheckers(String taskid) {
        return regularInspectRecordDao.findAllcheckers(taskid);
    }

    public void deleteAllByTaskid(String id) {
        regularInspectRecordDao.deleteAllByTaskid(id);
    }

    public List<RegularInspectRecord> findUndoByTaskid(String taskid, int i) {
        return regularInspectRecordDao.findUndoByTaskid(taskid,i);
    }

    public RegularInspectRecord findbyId(String id) {
        return regularInspectRecordDao.findbyId(id);
    }

    public void updaterecordById(String id, String checker, String checktime, String defectdesc, String method, Integer state, int ifchecked) {
        regularInspectRecordDao.updaterecordByid(id,checker,checktime,defectdesc,method,state,ifchecked);
    }

    public List<RegularInspectRecord> findBytaskidAndkks(String taskid, String kks) {
        return regularInspectRecordDao.findBytaskAndkks(taskid,kks);
    }
}
