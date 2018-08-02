package cn.com.jnpc.service;

import cn.com.jnpc.dao.FireworkconformRecordDao;
import cn.com.jnpc.entity.FireworkconformRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 2018/7/23.
 */
@Service
public class FireworkconformRecordService {
    @Autowired
    private FireworkconformRecordDao fireworkconformRecordDao;

    public Page<FireworkconformRecord> findByCondition(String unit, String factoryBuilding, String location, String jobmanager, String fireworkman, String fireworkinspecter, Integer state, String checker, String start, String end, PageRequest pageRequest) {
        return fireworkconformRecordDao.findAll(new Specification<FireworkconformRecord>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<FireworkconformRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list =new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(unit)&&unit!=""){
                    list.add(criteriaBuilder.like(root.get("unit").as(String.class),"%"+unit+"%"));
                }
                if (!StringUtils.isEmpty(factoryBuilding)&&factoryBuilding!=""){
                    list.add(criteriaBuilder.like(root.get("factoryBuilding").as(String.class),"%"+factoryBuilding+"%"));
                }
                if (!StringUtils.isEmpty(location)&&location!=""){
                    list.add(criteriaBuilder.like(root.get("location").as(String.class),"%"+location+"%"));
                }
                if (!StringUtils.isEmpty(jobmanager)&&jobmanager!=""){
                    list.add(criteriaBuilder.like(root.get("jobmanager").as(String.class),"%"+jobmanager+"%"));
                }
                if (!StringUtils.isEmpty(fireworkman)&&fireworkman!=""){
                    list.add(criteriaBuilder.like(root.get("fireworkman").as(String.class),"%"+fireworkman+"%"));
                }
                if (!StringUtils.isEmpty(fireworkinspecter)&&fireworkinspecter!=""){
                    list.add(criteriaBuilder.like(root.get("fireworkinspecter").as(String.class),"%"+fireworkinspecter+"%"));
                }
                if (state!=null){
                    list.add(criteriaBuilder.equal(root.get("state").as(Integer.class),state));
                }
                if (!StringUtils.isEmpty(checker)&&checker!=""){
                    list.add(criteriaBuilder.like(root.get("checker").as(String.class),"%"+checker+"%"));
                }
                if (!StringUtils.isEmpty(start)&&start!=""&&!StringUtils.isEmpty(end)&&end!=""){
                    list.add(criteriaBuilder.between(root.get("configurationtime").as(String .class),start,end));
                }

                Predicate[] p=new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageRequest);
    }

    public FireworkconformRecord findoneById(String recordid) {
        return fireworkconformRecordDao.findoneById(recordid);
    }
    @Transactional
    public void updateattachment(int i, String recordid) {
        fireworkconformRecordDao.updateattachment(i,recordid);
    }
}
