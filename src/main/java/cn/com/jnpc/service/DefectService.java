package cn.com.jnpc.service;

import cn.com.jnpc.dao.*;
import cn.com.jnpc.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cc on 2018/7/19.
 */
@Service
public class DefectService {
    @Autowired
    private IPRDefectDao iprDefectDao;
    @Autowired
    private FWDefectDao fwDefectDao;
    @Autowired
    private DPDefectDao dpDefectDao;
    @Autowired
    private FDDefectDao fdDefectDao;
    @Autowired
    private PVDefectDao pvDefectDao;
    @Autowired
    private ODefectDao oDefectDao;
    @Autowired
    private FCDefectDao fcDefectDao;
    @Autowired
    private RTDefectDao rtDefectDao;

    public void save(IPRDefect iprDefect) {
        iprDefectDao.save(iprDefect);
    }

    public void save(FWDefect defect1) {
        fwDefectDao.save(defect1);
    }

    public void save(DPDefect dpDefect) {
        dpDefectDao.save(dpDefect);
    }

    public void save(FDDefect fdDefect) {
        fdDefectDao.save(fdDefect);
    }

    public void save(PVDefect pvDefect) {
        pvDefectDao.save(pvDefect);
    }

    public void save(ODefect oDefect) {
        oDefectDao.save(oDefect);
    }

    public void save(FCDefect fcDefect) {
        fcDefectDao.save(fcDefect);
    }

}
