package cn.com.jnpc.service;

import cn.com.jnpc.dao.LocationDao;
import cn.com.jnpc.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cc on 2018/7/9.
 */
@Service
public class LocationService {
    @Autowired
    private LocationDao locationDao;

    public List findfacByUnit(String unit){
        return locationDao.findfacByUnit(unit);
    }
    public List findlocationByfac(String factoryBuilding,String unit){
        return locationDao.findlocationByFac(factoryBuilding,unit);
    }

    public List<Location> findlocationIsimportant(int i) {
        return locationDao.findAllIsimportant(i);
    }



    public void saveImportantlocation(String unit, String factoryBuilding, String location) {
        Location l=locationDao.findByLocation(unit,factoryBuilding,location);
        l.setIsimportant(1);
        locationDao.save(l);
    }

    public void changeisimportant(String id) {
        Location location = locationDao.findoneById(id);
        location.setIsimportant(0);
        locationDao.save(location);
    }
}
