package cn.com.jnpc.service;

import cn.com.jnpc.dao.EntryAndApprovalRecordDao;
import cn.com.jnpc.entity.EntryAndApprovalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 2018/7/30.
 */
@Service
public class EntryAndApprovalRecordSerivce {
    @Autowired
    private EntryAndApprovalRecordDao entryAndApprovalRecordDao;

    public EntryAndApprovalRecord findById(String id) {
        return entryAndApprovalRecordDao.findByid(id);
    }

    public void save(EntryAndApprovalRecord record) {
        entryAndApprovalRecordDao.save(record);
    }


    public List<EntryAndApprovalRecord> findBycondition() {
        return entryAndApprovalRecordDao.findBycondition();
    }
}
