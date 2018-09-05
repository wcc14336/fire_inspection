package cn.com.jnpc.controller;

import cn.com.jnpc.dao.MethodDao;
import cn.com.jnpc.entity.FCDefect;
import cn.com.jnpc.entity.FireworkconformRecord;
import cn.com.jnpc.service.FireworkconformRecordService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cc on 2018/7/23.
 */
@Controller
public class FireworkController {
    @Autowired
    private FireworkconformRecordService fireworkconformRecordService;
    @Autowired
    private MethodDao methodDao;
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
        List<String> list1=methodDao.finddistinctmethod();
        map.addObject("methods",list1);
        map.addObject("condition",condition);
        map.addObject("recordlist",list);
        map.setViewName("fireworkconformlist");
        return map;
    }
    @RequestMapping("/fireworkconformExcel")
    public void fireworkconformExcel(HttpServletResponse response,String unit, String factoryBuilding, String location, String jobmanager, String fireworkman, String fireworkinspecter, Integer state, String checker,String start, String end) throws IOException {
        List<FireworkconformRecord> list=fireworkconformRecordService.findByCondition(unit,factoryBuilding,location,jobmanager,fireworkman,fireworkinspecter,state,checker,start,end);
        String fileName="动火作业现场确认单清单"+start+"-"+end+".xls";
        fileName= URLEncoder.encode(fileName,"UTF-8");
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("动火作业现场确认清单");
        int rowNum=1;
        String[] headers={"机组","厂房","位置","动火证号","工作负责人","动火人","监火人","重大火灾风险分析单号","现场确认","确认人","确认时间","备注","缺陷描述","整改方式","跟踪单号"};
        HSSFRow row=sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row.createCell(i);
            HSSFRichTextString test=new HSSFRichTextString(headers[i]);
            cell.setCellValue(test);
        }
        for(FireworkconformRecord record:list){
            Set<FCDefect> fcDefects = record.getFcDefects();
            if (fcDefects.size()!=0){
                for (FCDefect defect:fcDefects){
                    HSSFRow row1=sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue(record.getUnit());
                    row1.createCell(1).setCellValue(record.getFactoryBuilding());
                    row1.createCell(2).setCellValue(record.getLocation());
                    row1.createCell(3).setCellValue(record.getFireworkNumber());
                    row1.createCell(4).setCellValue(record.getJobmanager());
                    row1.createCell(5).setCellValue(record.getFireworkman());
                    row1.createCell(6).setCellValue(record.getFireworkinspecter());
                    row1.createCell(7).setCellValue(record.getFirerisknumber());
                    row1.createCell(8).setCellValue(record.getState()==1?"已确认":"未确认");
                    row1.createCell(9).setCellValue(record.getChecker());
                    row1.createCell(10).setCellValue(record.getCheckdate());
                    row1.createCell(11).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                    row1.createCell(12).setCellValue(defect.getDefectdesc());
                    row1.createCell(13).setCellValue(defect.getMethod());
                    row1.createCell(14).setCellValue(defect.getTracenumber());
                    rowNum++;
                }
            }else {
                HSSFRow row1=sheet.createRow(rowNum);
                row1.createCell(0).setCellValue(record.getUnit());
                row1.createCell(1).setCellValue(record.getFactoryBuilding());
                row1.createCell(2).setCellValue(record.getLocation());
                row1.createCell(3).setCellValue(record.getFireworkNumber());
                row1.createCell(4).setCellValue(record.getJobmanager());
                row1.createCell(5).setCellValue(record.getFireworkman());
                row1.createCell(6).setCellValue(record.getFireworkinspecter());
                row1.createCell(7).setCellValue(record.getFirerisknumber());
                row1.createCell(8).setCellValue(record.getState()==1?"已确认":"未确认");
                row1.createCell(9).setCellValue(record.getChecker());
                row1.createCell(10).setCellValue(record.getCheckdate());
                row1.createCell(11).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                rowNum++;
            }

        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-disposition","attachment;filename="+fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
