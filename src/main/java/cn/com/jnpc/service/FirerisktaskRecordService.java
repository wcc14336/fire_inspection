package cn.com.jnpc.service;

import cn.com.jnpc.dao.FirerisktaskRecordDao;
import cn.com.jnpc.entity.FirerisktaskRecord;
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
 * Created by cc on 2018/7/26.
 */
@Service
public class FirerisktaskRecordService {
    @Autowired
    private FirerisktaskRecordDao firerisktaskRecordDao;

    public Page<FirerisktaskRecord> findByCondition(String unit, String factoryBuilding, String location, String jobmanager, String fireworker, String fireworkinspecter, Integer state, String inspecter, PageRequest pageRequest) {
        return firerisktaskRecordDao.findAll(new Specification<FirerisktaskRecord>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<FirerisktaskRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(unit) && unit != "") {
                    list.add(criteriaBuilder.like(root.get("unit").as(String.class), "%" + unit + "%"));
                }
                if (!StringUtils.isEmpty(factoryBuilding) && factoryBuilding != "") {
                    list.add(criteriaBuilder.like(root.get("factoryBuilding").as(String.class), "%" + factoryBuilding + "%"));
                }
                if (!StringUtils.isEmpty(location) && location != "") {
                    list.add(criteriaBuilder.like(root.get("location").as(String.class), "%" + location + "%"));
                }
                if (!StringUtils.isEmpty(jobmanager) && jobmanager != "") {
                    list.add(criteriaBuilder.like(root.get("jobmanager").as(String.class), "%" + jobmanager + "%"));
                }
                if (!StringUtils.isEmpty(fireworker) && fireworker != "") {
                    list.add(criteriaBuilder.like(root.get("fireworkman").as(String.class), "%" + fireworker + "%"));
                }
                if (!StringUtils.isEmpty(fireworkinspecter) && fireworkinspecter != "") {
                    list.add(criteriaBuilder.like(root.get("fireworkinspecter").as(String.class), "%" + fireworkinspecter + "%"));
                }
                if (state != null) {
                    list.add(criteriaBuilder.equal(root.get("state").as(Integer.class), state));
                }
                if(!StringUtils.isEmpty(inspecter)&&inspecter!=""){
                    Predicate p1=criteriaBuilder.like(root.get("inspecter1").as(String.class),"%"+inspecter+"%");
                    Predicate p2=criteriaBuilder.like(root.get("inspecter2").as(String.class),"%"+inspecter+"%");
                    list.add(criteriaBuilder.or(p1,p2));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageRequest);
    }
    @Transactional
    public void updateattachment(int i, String recordid) {
        firerisktaskRecordDao.updateattachment(i,recordid);
    }

    public void save(FirerisktaskRecord firerisktaskRecord) {
        firerisktaskRecordDao.save(firerisktaskRecord);
    }

    public List<FirerisktaskRecord> findAllByCondition(String unit, String factoryBuilding, String location, String jobmanager, String fireworker, String fireworkinspecter, Integer state, String inspecter) {
        return firerisktaskRecordDao.findAll(new Specification<FirerisktaskRecord>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<FirerisktaskRecord> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (!StringUtils.isEmpty(unit) && unit != "") {
                    list.add(criteriaBuilder.like(root.get("unit").as(String.class), "%" + unit + "%"));
                }
                if (!StringUtils.isEmpty(factoryBuilding) && factoryBuilding != "") {
                    list.add(criteriaBuilder.like(root.get("factoryBuilding").as(String.class), "%" + factoryBuilding + "%"));
                }
                if (!StringUtils.isEmpty(location) && location != "") {
                    list.add(criteriaBuilder.like(root.get("location").as(String.class), "%" + location + "%"));
                }
                if (!StringUtils.isEmpty(jobmanager) && jobmanager != "") {
                    list.add(criteriaBuilder.like(root.get("jobmanager").as(String.class), "%" + jobmanager + "%"));
                }
                if (!StringUtils.isEmpty(fireworker) && fireworker != "") {
                    list.add(criteriaBuilder.like(root.get("fireworkman").as(String.class), "%" + fireworker + "%"));
                }
                if (!StringUtils.isEmpty(fireworkinspecter) && fireworkinspecter != "") {
                    list.add(criteriaBuilder.like(root.get("fireworkinspecter").as(String.class), "%" + fireworkinspecter + "%"));
                }
                if (state != null) {
                    list.add(criteriaBuilder.equal(root.get("state").as(Integer.class), state));
                }
                if(!StringUtils.isEmpty(inspecter)&&inspecter!=""){
                    Predicate p1=criteriaBuilder.like(root.get("inspecter1").as(String.class),"%"+inspecter+"%");
                    Predicate p2=criteriaBuilder.like(root.get("inspecter2").as(String.class),"%"+inspecter+"%");
                    list.add(criteriaBuilder.or(p1,p2));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        });
    }
}
