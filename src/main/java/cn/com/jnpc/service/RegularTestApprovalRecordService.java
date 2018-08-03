package cn.com.jnpc.service;

import cn.com.jnpc.dao.RegularTestApprovalRecordDao;
import cn.com.jnpc.entity.RegularTestApprovalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 2018/8/2.
 */
@Service
public class RegularTestApprovalRecordService {
    @Autowired
    private RegularTestApprovalRecordDao regularTestApprovalRecordDao;

    public RegularTestApprovalRecord findByid(String id) {
        return regularTestApprovalRecordDao.findByid(id);
    }

    public void save(RegularTestApprovalRecord regularTestApprovalRecord) {
        regularTestApprovalRecordDao.save(regularTestApprovalRecord);
    }

    public List<RegularTestApprovalRecord> findbyCondition() {
        return regularTestApprovalRecordDao.findByCondition();
    }
}
