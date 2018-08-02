package cn.com.jnpc.service;

import cn.com.jnpc.dao.RegularInspectDao;
import cn.com.jnpc.entity.RegularInspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/27.
 */
@Service
public class RegularInspectService {
    @Autowired
    private RegularInspectDao regularInspectDao;

    public List<RegularInspect> findByUnitAndYear(String unit, Integer year) {
        return regularInspectDao.findByUnitAndYear(unit,year);
    }

    public void save(RegularInspect regularInspect) {
        regularInspectDao.save(regularInspect);
    }

    @Transactional
    public void deleteById(String id) {
        regularInspectDao.deleteById(id);
    }


    public List<RegularInspect> findMyUndoneInspect(String username, Integer i) {
        return regularInspectDao.findMyUndoneInspect(username,i);
    }
    @Transactional
    public void updatestate(String taskid, int i) {
        regularInspectDao.updatestate(taskid,i);
    }

    public RegularInspect findByid(String taskid) {
        return regularInspectDao.findByid(taskid);
    }
}
