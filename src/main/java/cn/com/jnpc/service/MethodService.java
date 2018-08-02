package cn.com.jnpc.service;

import cn.com.jnpc.dao.MethodDao;
import cn.com.jnpc.entity.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by cc on 2018/7/25.
 */
@Service
public class MethodService {
    @Autowired
    private MethodDao methodDao;

    public List<Method> getAll() {
       return  methodDao.findAll();
    }
    @Transactional
    public void deletemethodById(String id) {
        methodDao.deleteById(id);
    }

    public void save(Method method) {
        methodDao.save(method);
    }
}
