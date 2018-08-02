package cn.com.jnpc.dao;

import cn.com.jnpc.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by cc on 2018/7/9.
 */
public interface LocationDao extends JpaRepository<Location,String >{
    @Query("select l.factoryBuilding from Location l where l.unit=?1")
    List findfacByUnit(String unit);

    @Query("select l.location from Location l where l.factoryBuilding=?1")
    List findlocationByFac(String factoryBuilding);

    @Query("select l from Location l where l.isimportant=?1")
    List<Location> findAllIsimportant(int i);

    @Query("select l from Location l where l.unit=?1 and l.factoryBuilding=?2 and l.location=?3")
    Location findByLocation(String unit, String factoryBuilding, String location);
    @Query("select l from Location l where l.id=?1")
    Location findoneById(String id);
}