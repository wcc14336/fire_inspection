package cn.com.jnpc.controller;

import cn.com.jnpc.entity.FirerisktaskRecord;
import cn.com.jnpc.service.FirerisktaskRecordService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 2018/7/26.
 */
@Controller
public class FirerisktaskRecordController {
    @Autowired
    private FirerisktaskRecordService firerisktaskRecordService;
    @RequestMapping("/firerisktasktasklist")
    private ModelAndView firerisktasktasklist(ModelAndView map,Integer number, String unit, String factoryBuilding, String location, String jobmanager, String fireworker, String fireworkinspecter, Integer state,String inspecter){
        Integer pagesize=10;
        if (number==null||"".equals(number)){
            number=0;
        }
        Page<FirerisktaskRecord> list=firerisktaskRecordService.findByCondition(unit,factoryBuilding,location,jobmanager,fireworker,fireworkinspecter,state,inspecter,new PageRequest(number, pagesize));
        map.setViewName("firerisktasklist");
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
        if (fireworker==null){
            condition.put("fireworker","");
        }else {
            condition.put("fireworkman",fireworker);
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
        if (inspecter==null){
            condition.put("inspecter","");
        }else {
            condition.put("inspecter",inspecter);
        }
        map.addObject("condition",condition);
        map.addObject("recordlist",list);
        return map;
    }
    @RequestMapping("/changefirerisktask")
    public String changefirerisktask(FirerisktaskRecord firerisktaskRecord){
        firerisktaskRecordService.save(firerisktaskRecord);
        return "redirect:/firerisktasktasklist";
    }
    @RequestMapping("/firerisktaskExcel")
    public void firerisktaskExcel(String unit,String factoryBuilding,String location,String jobmanager,String fireworker,String fireworkinspecter,Integer state,String inspecter,HttpServletResponse response) throws IOException {
        List<FirerisktaskRecord> list=firerisktaskRecordService.findAllByCondition(unit,factoryBuilding,location,jobmanager,fireworker,fireworkinspecter,state,inspecter);
        String fileName = "重大火灾风险作业跟踪监督单"  + ".xls";//设置要导出的文件的名字
        fileName = URLEncoder.encode(fileName, "UTF-8");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("重大火灾风险作业跟踪监督单");
        int rowNum = 1;
        String[] headers = { "机组", "厂房", "房间", "重大火灾风险分析单号","工作负责人","测量数据","测量时间","动火人","监火人","监督人1","测量数据","测量时间","监督人2","测量数据","测量时间","状态","备注"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for(FirerisktaskRecord record:list){
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(record.getUnit());
            row1.createCell(1).setCellValue(record.getFactoryBuilding());
            row1.createCell(2).setCellValue(record.getLocation());
            row1.createCell(3).setCellValue(record.getTracenumber());
            row1.createCell(4).setCellValue(record.getJobmanager());
            row1.createCell(5).setCellValue(record.getMeasurement());
            row1.createCell(6).setCellValue(record.getMeasuretime());
            row1.createCell(7).setCellValue(record.getFireworker());
            row1.createCell(8).setCellValue(record.getFireinspecter());
            row1.createCell(9).setCellValue(record.getInspecter1());
            row1.createCell(10).setCellValue(record.getMeasurement1());
            row1.createCell(11).setCellValue(record.getMeasuretime1());
            row1.createCell(12).setCellValue(record.getInspecter2());
            row1.createCell(13).setCellValue(record.getMeasurement2());
            row1.createCell(14).setCellValue(record.getMeasuretime2());
            row1.createCell(15).setCellValue(record.getState()==1?"已完工":"未完工");
            row1.createCell(16).setCellValue(record.getAttachment()==1?"有附件":"无附件");
            rowNum++;
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
