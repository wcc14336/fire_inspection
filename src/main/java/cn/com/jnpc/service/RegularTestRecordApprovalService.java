package cn.com.jnpc.service;

import cn.com.jnpc.dao.RegularTestRecordApprovalDao;
import cn.com.jnpc.entity.RegularTestRecordApproval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 2018/8/3.
 */
@Service
public class RegularTestRecordApprovalService {
    @Autowired
    private RegularTestRecordApprovalDao regularTestRecordApprovalDao;

    public RegularTestRecordApproval findById(String id) {
        return regularTestRecordApprovalDao.findByid(id);
    }

    public void save(RegularTestRecordApproval regularTestRecordApproval) {
        regularTestRecordApprovalDao.save(regularTestRecordApproval);
    }

    public List<RegularTestRecordApproval> findbyCondition() {
        return regularTestRecordApprovalDao.findbyCondition();
    }
}
