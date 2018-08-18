package cn.com.jnpc.service;

import cn.com.jnpc.dao.MaintenanceRecordDao;
import cn.com.jnpc.entity.MaintenanceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * Created by cc on 2018/8/10.
 */
@Service
public class MaintenanceRecordService {
    @Autowired
    private MaintenanceRecordDao maintenanceRecordDao;

    public Page<MaintenanceRecord> findByCondition(String unit, String kks, String name, String maintainer, String starttime, String endtime, PageRequest pageRequest) {
        return maintenanceRecordDao.findAll(new Specification<MaintenanceRecord>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<MaintenanceRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                if (!StringUtils.isEmpty(unit)&&unit!=""){
                    list.add(criteriaBuilder.like(root.get("unit").as(String.class),"%"+unit+"%"));
                }
                if (!StringUtils.isEmpty(kks)&&kks!=""){
                    list.add(criteriaBuilder.like(root.get("kks").as(String.class),"%"+kks+"%"));
                }
                if (!StringUtils.isEmpty(name)&&name!=""){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+name+"%"));
                }
                if (!StringUtils.isEmpty(maintainer)&&maintainer!=""){
                    list.add(criteriaBuilder.like(root.get("maintainer").as(String.class),"%"+maintainer+"%"));
                }
                if (!StringUtils.isEmpty(starttime)&&starttime!=""&&!StringUtils.isEmpty(endtime)&&endtime!=""){
                    list.add(criteriaBuilder.between(root.get("maintaintime").as(String .class),starttime,endtime));
                }
                Predicate[] p=new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageRequest);
    }

    public void save(MaintenanceRecord maintenanceRecord) {
        maintenanceRecordDao.save(maintenanceRecord);
    }

    @Transactional
    public void updateattachment(int i, String recordid) {
        maintenanceRecordDao.updateattachment(i,recordid);
    }
    @Transactional
    public void updateifcommit(String id) {
        maintenanceRecordDao.updateifconfig(id);
    }
    @Transactional
    public void recordpassed(String id) {
        maintenanceRecordDao.recordpassed(id);
    }
    @Transactional
    public void recordnotpassed(String id) {
        maintenanceRecordDao.recordnotpassed(id);
    }

    public List<MaintenanceRecord> findByCondition(String unit, String kks, String name, String maintainer, String starttime, String endtime, Sort sort) {
        return maintenanceRecordDao.findAll(new Specification<MaintenanceRecord>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<MaintenanceRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list =new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(unit)&&unit!=""){
                    list.add(criteriaBuilder.like(root.get("unit").as(String.class),"%"+unit+"%"));
                }
                if (!StringUtils.isEmpty(kks)&&kks!=""){
                    list.add(criteriaBuilder.like(root.get("kks").as(String.class),"%"+kks+"%"));
                }
                if (!StringUtils.isEmpty(name)&&name!=""){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+name+"%"));
                }
                if (!StringUtils.isEmpty(maintainer)&&maintainer!=""){
                    list.add(criteriaBuilder.like(root.get("maintainer").as(String.class),"%"+maintainer+"%"));
                }
                if (!StringUtils.isEmpty(starttime)&&starttime!=""&&!StringUtils.isEmpty(endtime)&&endtime!=""){
                    list.add(criteriaBuilder.between(root.get("maintaintime").as(String .class),starttime,endtime));
                }

                Predicate[] p=new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },sort);
    }
}
