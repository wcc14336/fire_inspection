package cn.com.jnpc.controller;

import cn.com.jnpc.entity.Equipment;
import cn.com.jnpc.service.EquipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 2018/7/6.
 */
@Controller
public class EquipmentController {
    @Autowired
    private EquipService equipService;
    @RequestMapping("/equiplist")
    public ModelAndView equiplist(HttpSession session,ModelAndView map, Integer number, String unit, String factoryBuilding, String location, String KKS, String name, String category, String enteringman, String updatetimestart, String updatetimeend){
        session.setAttribute("username","李四");
        Integer pagesize=10;
        if (number==null||"".equals(number)){
            number=0;
        }
        Page<Equipment> list = equipService.findByCondition(unit,factoryBuilding,location,KKS,name,category,enteringman,updatetimestart,updatetimeend, new PageRequest(number, pagesize));
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
        if (KKS==null){
            condition.put("KKS","");
        }else {
            condition.put("KKS",KKS);
        }
        if (name==null){
            condition.put("name","");
        }else {
            condition.put("name",name);
        }
        if (category==null){
            condition.put("category","");
        }else {
            condition.put("category",category);
        }
        if (enteringman==null){
            condition.put("enteringman","");
        }else {
            condition.put("enteringman",enteringman);
        }
        if (updatetimestart==null){
            condition.put("updatetimestart","");
        }else {
            condition.put("updatetimestart",updatetimestart);
        }
        if (updatetimeend==null){
            condition.put("updatetimeend","");
        }else {
            condition.put("updatetimeend",updatetimeend);
        }
        map.addObject("condition",condition);
        map.addObject("equiplist",list);
        map.setViewName("equiplist");
        return  map;
    }
    @RequestMapping("/addEquip")
    public String addEquip(Equipment equipment){
        equipService.add(equipment);
        return "redirect:/equiplist";
    }
    @RequestMapping("/changeEquip")
    public String changeEquip(Equipment equipment){
        equipService.add(equipment);
        return "redirect:/equiplist";
    }
    @RequestMapping("/deleteEquip")
    public String deleteEquip(String kks){
        equipService.deleteEquipByID(kks);
        return "redirect:/equiplist";
    }
    @RequestMapping("/importEquip")
    public String importEquip(@RequestParam(value = "file") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        equipService.importFile(filename, file);
        return "redirect:/equiplist";
    }

}
