package cn.com.jnpc.controller;

import cn.com.jnpc.dao.PersonDao;
import cn.com.jnpc.entity.MaintenanceRecord;
import cn.com.jnpc.service.MaintenanceRecordService;
import cn.com.jnpc.utils.EmailUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 2018/8/10.
 */
@Controller
public class MaintenanceController {
    @Autowired
    private MaintenanceRecordService maintenanceRecordService;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private PersonDao personDao;
    @RequestMapping("/maintenance")
    public ModelAndView maintenance(ModelAndView map,Integer number,String unit,String kks,String name,String maintainer,String starttime,String endtime){
        Integer pagesize=10;
        if (number==null||"".equals(number)){
            number=0;
        }
        Page<MaintenanceRecord> list=maintenanceRecordService.findByCondition(unit,kks,name,maintainer,starttime,endtime,new PageRequest(number,pagesize));
        Map<String,String> condition=new HashMap<>();
        if(unit==null){
            condition.put("unit","");
        }else {
            condition.put("unit",unit);
        }
        if(kks==null){
            condition.put("kks","");
        }else {
            condition.put("kks",kks);
        }
        if(name==null){
            condition.put("name","");
        }else {
            condition.put("name",name);
        }
        if(maintainer==null){
            condition.put("maintainer","");
        }else {
            condition.put("maintainer",maintainer);
        }
        if(starttime==null){
            condition.put("starttime","");
        }else {
            condition.put("starttime",starttime);
        }
        if(endtime==null){
            condition.put("endtime","");
        }else {
            condition.put("endtime",endtime);
        }
        map.addObject("condition",condition);
        map.addObject("recordlist",list);
        map.setViewName("maintenance");
        return map;
    }

    @RequestMapping("/changemaintenancerecord")
    public String changemaintenancerecord(MaintenanceRecord maintenanceRecord){
        maintenanceRecordService.save(maintenanceRecord);
        return "redirect:/maintenance";
    }
    @RequestMapping("/maintenancerecordcommit")
    public String maintenancerecordcommit(String id){
        maintenanceRecordService.updateifcommit(id);
        List<String> list=personDao.findEngineer();
        //List<String> list=new ArrayList<>();
        list.add("1433658618@qq.com");
        emailUtil.sendEmail("维修保养任务待审批","系统中有维修保养任务需要审批",list);
        return "redirect:/maintenance";
    }
    @RequestMapping("/maintenancerecordapproval")
    public String maintenancerecordapproval(String id,Integer ifpassed){
        if (ifpassed==1){
            maintenanceRecordService.recordpassed(id);
        }else {
            maintenanceRecordService.recordnotpassed(id);
        }
        return "redirect:/maintenance";
    }
    @RequestMapping("/maintenancerecordExcel")
    public void maintenancerecordExcel(HttpServletResponse response,String unit,String kks,String name,String maintainer,String starttime,String endtime) throws IOException {
        List<MaintenanceRecord> list=maintenanceRecordService.findByCondition(unit,kks,name,maintainer,starttime,endtime,new Sort(Sort.Direction.ASC, "maintaintime"));
        String fileName;
        if (unit==null||"".equals(unit)){
            fileName="消防维修保养清单.xls";
        }else {
            fileName=unit+"号机组消防维修保养清单.xls";
        }
        fileName= URLEncoder.encode(fileName,"UTF-8");
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("消防维修保养清单");
        int rowNum=1;
        String[] headers={"机组","厂房","位置","kks码","名称","维修工作负责人","维修时间","工单号","备注"};
        HSSFRow row=sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (MaintenanceRecord record:list){
            HSSFRow row1=sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(record.getUnit());
            row1.createCell(1).setCellValue(record.getFactoryBuilding());
            row1.createCell(2).setCellValue(record.getLocation());
            row1.createCell(3).setCellValue(record.getKks());
            row1.createCell(4).setCellValue(record.getName());
            row1.createCell(5).setCellValue(record.getMaintainer());
            row1.createCell(6).setCellValue(record.getMaintaintime());
            row1.createCell(7).setCellValue(record.getNumber());
            row1.createCell(8).setCellValue(record.getAttachment()==1?"有附件":"无附件");
            rowNum++;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-disposition","attachment;filename="+fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
