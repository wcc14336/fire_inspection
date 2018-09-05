package cn.com.jnpc.controller;

import cn.com.jnpc.dao.CategoryDao;
import cn.com.jnpc.dao.NameDao;
import cn.com.jnpc.entity.Category;
import cn.com.jnpc.entity.Equipment;
import cn.com.jnpc.entity.Name;
import cn.com.jnpc.service.EquipService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
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
    @Autowired
    private NameDao nameDao;
    @Autowired
    private CategoryDao categoryDao;
    @RequestMapping("/equiplist")
    public ModelAndView equiplist(HttpSession session,ModelAndView map, Integer number, String unit, String factoryBuilding, String location, String KKS, String name, String category, String enteringman, String updatetimestart, String updatetimeend){
        //session.setAttribute("username","李四");
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
        List<String> list1 = categoryDao.finddistinctcategory();
        List<String> list2 = nameDao.finddistinctname();
        map.addObject("names",list2);
        map.addObject("categorys",list1);
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
    @RequestMapping("/EquipExcel")
    public void EquipExcel(HttpServletResponse response, String unit, String factoryBuilding, String location, String KKS, String name, String category, String enteringman, String updatetimestart, String updatetimeend) throws IOException {
        List<Equipment> list=equipService.findByCondition(unit,factoryBuilding,location,KKS,name,category,enteringman,updatetimestart,updatetimeend);
        String fileName="设备信息列表"+".xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("设备信息");
        int rowNum=1;
        String[] headers={"机组","厂房","位置","KKS","名称","类别","配置时间","定检周期","试验周期","更换周期","录入人","更新时间"};
        HSSFRow row=sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for(Equipment equipment:list){
            HSSFRow row1=sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(equipment.getUnit());
            row1.createCell(1).setCellValue(equipment.getFactoryBuilding());
            row1.createCell(2).setCellValue(equipment.getLocation());
            row1.createCell(3).setCellValue(equipment.getKks());
            row1.createCell(4).setCellValue(equipment.getName());
            row1.createCell(5).setCellValue(equipment.getCategory());
            row1.createCell(6).setCellValue(equipment.getConfigurationtime());
            row1.createCell(7).setCellValue(equipment.getCheckcycle());
            row1.createCell(8).setCellValue(equipment.getTestcycle());
            row1.createCell(9).setCellValue(equipment.getReplacecycle());
            row1.createCell(10).setCellValue(equipment.getEnteringman());
            row1.createCell(11).setCellValue(equipment.getUpdatetime());
            rowNum++;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-disposition","attachment;filename="+fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
