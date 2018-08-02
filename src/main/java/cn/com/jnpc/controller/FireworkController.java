package cn.com.jnpc.controller;

import cn.com.jnpc.entity.FireworkconformRecord;
import cn.com.jnpc.service.FireworkconformRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cc on 2018/7/23.
 */
@Controller
public class FireworkController {
    @Autowired
    private FireworkconformRecordService fireworkconformRecordService;
    @RequestMapping("/fireworkconformlist")
    public ModelAndView hotworkconforminfo(ModelAndView map,Integer number, String unit, String factoryBuilding, String location, String jobmanager, String fireworkman, String fireworkinspecter, Integer state, String checker,String start, String end){
        Integer pagesize=10;
        if (number==null||"".equals(number)){
            number=0;
        }
        Page<FireworkconformRecord> list=fireworkconformRecordService.findByCondition(unit,factoryBuilding,location,jobmanager,fireworkman,fireworkinspecter,state,checker,start,end,new PageRequest(number, pagesize));
        Map<String,String> condition=new HashMap<String ,String>();
        if (unit==null){
            condition.put("unit","");
        }else {
            condition.put("unit",unit);
        }
        if (factoryBuilding==null){
            condition.put("factoryBuilding","");
        }else {
            condition.put("factoryBuilding",factoryBuilding);
        }
        if (location==null){
            condition.put("location","");
        }else {
            condition.put("location",location);
        }
        if (jobmanager==null){
            condition.put("jobmanager","");
        }else {
            condition.put("jobmanager",jobmanager);
        }
        if (fireworkman==null){
            condition.put("fireworkman","");
        }else {
            condition.put("fireworkman",fireworkman);
        }
        if (fireworkinspecter==null){
            condition.put("fireworkinspecter","");
        }else {
            condition.put("fireworkinspecter",fireworkinspecter);
        }
        if (state==null){
            condition.put("state","");
        }else {
            condition.put("state",state+"");
        }
        if (checker==null){
            condition.put("checker","");
        }else {
            condition.put("checker",checker);
        }
        if (start==null){
            condition.put("start","");
        }else {
            condition.put("start",start);
        }
        if (end==null){
            condition.put("end","");
        }else {
            condition.put("end",end);
        }
        map.addObject("condition",condition);
        map.addObject("recordlist",list);
        map.setViewName("fireworkconformlist");
        return map;
    }
}
