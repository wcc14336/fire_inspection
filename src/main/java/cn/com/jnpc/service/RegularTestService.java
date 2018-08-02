package cn.com.jnpc.service;

import cn.com.jnpc.dao.RegularTestDao;
import cn.com.jnpc.entity.RegularTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 2018/8/2.
 */
@Service
public class RegularTestService {
    @Autowired
    private RegularTestDao regularTestDao;

    public List<RegularTest> findByUnitAndYear(String unit, Integer year) {
        return regularTestDao.findByUnitAndYear(unit,year);
    }
}
