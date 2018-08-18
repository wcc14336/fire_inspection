package cn.com.jnpc.service;

import cn.com.jnpc.dao.RTDefectDao;
import cn.com.jnpc.entity.RTDefect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cc on 2018/8/9.
 */
@Service
public class RTDefectService {
    @Autowired
    private RTDefectDao rtDefectDao;

    public void save(RTDefect rtDefect) {
        rtDefectDao.save(rtDefect);
    }
}
