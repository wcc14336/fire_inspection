package cn.com.jnpc.dao;

import cn.com.jnpc.entity.Equipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Created by cc on 2018/7/6.
 */
public interface EquipmentDao extends JpaRepository<Equipment,String>,JpaSpecificationExecutor<Equipment> {

    Page<Equipment> findAll(Pageable pageable);
    //Page<Equipment> findAll(Equipment equipment,Pageable pageable);

    @Override
    Page<Equipment> findAll(@Nullable Specification<Equipment> specification, Pageable pageable);

    @Query("select e from Equipment e WHERE e.unit=?1 and e.name=?2")
    List<Equipment> findAllByCondition(String unit, String checkproject);
    @Query("select e from Equipment e where e.kks=?1")
    Equipment findByKks(String kks);
    @Query("select e from Equipment e where e.kks like %?1%")
    List<Equipment> findBykksLike(String kks);
}
