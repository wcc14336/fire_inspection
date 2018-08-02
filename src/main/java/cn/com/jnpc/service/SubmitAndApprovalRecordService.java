package cn.com.jnpc.service;

import cn.com.jnpc.dao.SubmitAndApprovalRecordDao;
import cn.com.jnpc.entity.SubmitAndApprovalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 2018/7/16.
 */
@Service
public class SubmitAndApprovalRecordService {
    @Autowired
    private SubmitAndApprovalRecordDao submitAndApprovalRecordDao;

    public void save(SubmitAndApprovalRecord record) {
        submitAndApprovalRecordDao.save(record);
    }

    public SubmitAndApprovalRecord findById(String id) {
        return submitAndApprovalRecordDao.findOneById(id);
    }

    public List<SubmitAndApprovalRecord> findApprovalstate0() {
        return submitAndApprovalRecordDao.findApprovalstateis0();
    }
}
