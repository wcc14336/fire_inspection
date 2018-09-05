package cn.com.jnpc.controller;

import cn.com.jnpc.entity.Location;
import cn.com.jnpc.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by cc on 2018/7/9.
 */
@Controller
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping("/findfactoryBuildings")
    @ResponseBody
    public List findfacByUnit(String unit){
        return locationService.findfacByUnit(unit);
    }
    @RequestMapping("/findlocations")
    @ResponseBody
    public List findlocationbyfac(String factoryBuilding,String unit){
        return locationService.findlocationByfac(factoryBuilding,unit);
    }
    @RequestMapping("/importantlocation")
    public ModelAndView importantlocation(ModelAndView map){
        List<Location> list=locationService.findlocationIsimportant(1);
        map.addObject("list",list);
        map.setViewName("importantlocation");
        return map;
    }
    @RequestMapping("/addimportantlocation")
    public String addimportantlocation(String unit,String factoryBuilding,String location ){
        locationService.saveImportantlocation(unit,factoryBuilding,location);
        return "redirect:/importantlocation";
    }
    @RequestMapping("/changeimportantlocation")
    public String changeimportantlocation(String id){
        locationService.changeisimportant(id);
        return "redirect:/importantlocation";
    }
}
