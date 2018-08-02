package cn.com.jnpc.service;

import cn.com.jnpc.dao.RegularInspectApprovalRecordDao;
import cn.com.jnpc.entity.RegularInspectApprovalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 2018/8/1.
 */
@Service
public class RegularInspectApprovalRecordService {
    @Autowired
    private RegularInspectApprovalRecordDao regularInspectApprovalRecordDao;

    public RegularInspectApprovalRecord findById(String id) {
        return regularInspectApprovalRecordDao.findByid(id);
    }

    public void save(RegularInspectApprovalRecord regularInspectApprovalRecord) {
        regularInspectApprovalRecordDao.save(regularInspectApprovalRecord);
    }

    public List<RegularInspectApprovalRecord> findBycondition() {
        return regularInspectApprovalRecordDao.findBycondition();
    }
}
