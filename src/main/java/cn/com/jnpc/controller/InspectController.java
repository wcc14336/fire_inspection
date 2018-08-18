package cn.com.jnpc.controller;

import cn.com.jnpc.entity.*;
import cn.com.jnpc.service.*;
import cn.com.jnpc.utils.EmailUtil;
import cn.com.jnpc.utils.PdfUtil;
import com.lowagie.text.DocumentException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by cc on 2018/7/12.
 */
@Controller
public class InspectController {
    @Autowired
    private ImportantPartRecordService importantPartRecordService;
    @Autowired
    private FireworkRecordService fireworkRecordService;
    @Autowired
    private DepositaryRecordService depositaryRecordService;
    @Autowired
    private FireproofdoorRecordService fireproofdoorRecordService;
    @Autowired
    private PersonnelviolationRecordService personnelviolationRecordService;
    @Autowired
    private OtherRecordService otherRecordService;
    @Autowired
    private SubmitAndApprovalRecordService submitAndApprovalRecordService;
    @Autowired
    private DefectService defectService;
    @Autowired
    private FireworkconformRecordService fireworkconformRecordService;
    @Autowired
    private EmailUtil emailUtil;

    SubmitAndApprovalRecord record;
    @RequestMapping("/dailyinspect")
    public String dailyinspect(){

        return "dailyinspect";
    }

    @RequestMapping("/dailyinspectinfo")
    @ResponseBody
    public ModelAndView dailyinspectinfo(ModelAndView map, String date, HttpSession session,String unit,String username){
        if (username==null||"".equals(username)){
            username = (String) session.getAttribute("username");
        }
        List<ImportantPartRecord> importantPartRecords=importantPartRecordService.findAllByDateAndName(date,username,unit);
        List<FireworkRecord> fireworkRecords=fireworkRecordService.findAllByDateAndName(date,username,unit);
        List<DepositaryRecord> depositaryRecords=depositaryRecordService.findAllByDateAndName(date,username,unit);
        List<FireproofdoorRecord> fireproofdoorRecords=fireproofdoorRecordService.findAllByDateAndName(date,username,unit);
        List<PersonnelviolationRecord> personnelviolationRecords=personnelviolationRecordService.findAllByDateAndName(date,username,unit);
        List<OtherRecord> otherRecords=otherRecordService.findAllByDateAndName(date,username,unit);
        String id=username+"-"+date+"-"+unit;
        SubmitAndApprovalRecord submitAndApprovalRecord=submitAndApprovalRecordService.findById(id);
        if (submitAndApprovalRecord==null){
            record=new SubmitAndApprovalRecord();
            record.setId(id);
            submitAndApprovalRecordService.save(record);
        }
        SubmitAndApprovalRecord submitAndApprovalRecord1=submitAndApprovalRecordService.findById(id);
        map.addObject("submitAndApprovalRecord",submitAndApprovalRecord1);
        map.addObject("fireworkRecords",fireworkRecords);
        map.addObject("importantPartRecords",importantPartRecords);
        map.addObject("depositaryRecords",depositaryRecords);
        map.addObject("fireproofdoorRecords",fireproofdoorRecords);
        map.addObject("personnelviolationRecords",personnelviolationRecords);
        map.addObject("otherRecords",otherRecords);
        Map<String ,String > condition=new HashMap();
        condition.put("date",date);
        condition.put("unit",unit);
        condition.put("username",username);
        map.addObject("condition",condition);
        map.setViewName("dailyinspectinfo");
        return map;
    }

    @RequestMapping("/changedefect")
    public String changeiRecord(String id,String defectdesc,String method,String tracenumber,String defect, String recordid, String date, String username, String unit, RedirectAttributes attr){
        switch (defect){
            case "IPRdefect":
                IPRDefect iprDefect=new IPRDefect();
                iprDefect.setId(id);
                iprDefect.setDefectdesc(defectdesc);
                iprDefect.setMethod(method);
                iprDefect.setTracenumber(tracenumber);
                ImportantPartRecord record=importantPartRecordService.findoneById(recordid);
                iprDefect.setImportantPartRecord(record);
                defectService.save(iprDefect);
                break;
            case "FWdefect":
                FWDefect defect1=new FWDefect();
                defect1.setId(id);
                defect1.setDefectdesc(defectdesc);
                defect1.setMethod(method);
                defect1.setTracenumber(tracenumber);
                FireworkRecord record1 = fireworkRecordService.findoneById(recordid);
                defect1.setFireworkRecord(record1);
                defectService.save(defect1);
                break;
            case "DPdefect":
                DPDefect dpDefect=new DPDefect();
                dpDefect.setId(id);
                dpDefect.setDefectdesc(defectdesc);
                dpDefect.setMethod(method);
                dpDefect.setTracenumber(tracenumber);
                DepositaryRecord record2=depositaryRecordService.findoneById(recordid);
                dpDefect.setDepositaryRecord(record2);
                defectService.save(dpDefect);
                break;
            case "FDdefect":
                FDDefect fdDefect=new FDDefect();
                fdDefect.setId(id);
                fdDefect.setDefectdesc(defectdesc);
                fdDefect.setMethod(method);
                fdDefect.setTracenumber(tracenumber);
                FireproofdoorRecord record3=fireproofdoorRecordService.findoneById(recordid);
                fdDefect.setFireproofdoorRecord(record3);
                defectService.save(fdDefect);
                break;
            case "PVdefect":
                PVDefect pvDefect=new PVDefect();
                pvDefect.setId(id);
                pvDefect.setDefectdesc(defectdesc);
                pvDefect.setMethod(method);
                pvDefect.setTracenumber(tracenumber);
                PersonnelviolationRecord record4=personnelviolationRecordService.findoneById(recordid);
                pvDefect.setPersonnelviolationRecord(record4);
                defectService.save(pvDefect);
                break;
            case "Odefect":
                ODefect oDefect=new ODefect();
                oDefect.setId(id);
                oDefect.setDefectdesc(defectdesc);
                oDefect.setMethod(method);
                oDefect.setTracenumber(tracenumber);
                OtherRecord record5=otherRecordService.finfoneById(recordid);
                oDefect.setOtherRecord(record5);
                defectService.save(oDefect);
                break;
            case "fcdefect":
                FCDefect fcDefect=new FCDefect();
                fcDefect.setId(id);
                fcDefect.setDefectdesc(defectdesc);
                fcDefect.setMethod(method);
                fcDefect.setTracenumber(tracenumber);
                FireworkconformRecord record6=fireworkconformRecordService.findoneById(recordid);
                fcDefect.setFireworkconformRecord(record6);
                defectService.save(fcDefect);
                return "redirect:/fireworkconformlist";
        }
        attr.addAttribute("date",date);
        attr.addAttribute("username",username);
        attr.addAttribute("unit",unit);
        return "redirect:/dailyinspectinfo";
    }

    @RequestMapping("/changefRecord")
    public String changefRecord(FireworkRecord fireworkRecord ,String date){
        fireworkRecordService.save(fireworkRecord);
        return "redirect:/dailyinspectinfo?date="+date;
    }

    @RequestMapping("/changedRecord")
    public String changedRecord(DepositaryRecord depositaryRecord ,String date){
        depositaryRecordService.save(depositaryRecord);
        return "redirect:/dailyinspectinfo?date="+date;
    }

    @RequestMapping("/changefireproofdoorRecord")
    public String changefireproofdoorRecord(FireproofdoorRecord fireproofdoorRecord ,String date){
        fireproofdoorRecordService.save(fireproofdoorRecord);
        return "redirect:/dailyinspectinfo?date="+date;
    }

    @RequestMapping("/changepRecord")
    public String changepRecord(PersonnelviolationRecord personnelviolationRecord ,String date){
        personnelviolationRecordService.save(personnelviolationRecord);
        return "redirect:/dailyinspectinfo?date="+date;
    }

    @RequestMapping("/changeoRecord")
    public String changeoRecord(OtherRecord otherRecord ,String date){
        otherRecordService.save(otherRecord);
        return "redirect:/dailyinspectinfo?date="+date;
    }

    @RequestMapping("/submit")
    public String  submit(String submiter,String checkdate,String submitdate,Integer submitstate,String unit){
        record=new SubmitAndApprovalRecord();
        String id=submiter+"-"+checkdate+"-"+unit;
        record.setId(id);
        record.setSubmiter(submiter);
        record.setCheckdate(checkdate);
        record.setSubmitdate(submitdate);
        record.setSubmitstate(submitstate);
        record.setApprovalstate(0);
        record.setApprovalresult(0);
        record.setUnit(unit);
        submitAndApprovalRecordService.save(record);
        List<String> list=new ArrayList<>();
        list.add("1433658618@qq.com");
        emailUtil.sendEmail("系统邮件","<h1>一个大标题</h1><a href='www.baidu.com'>点击查看代办</a>",list);
        return "redirect:/dailyinspectinfo?date="+checkdate+"&unit="+unit;
    }

    @RequestMapping("/approvallist")
    public ModelAndView approvallist(ModelAndView map){
        List<SubmitAndApprovalRecord> submitAndApprovalRecords=submitAndApprovalRecordService.findApprovalstate0();
        map.addObject("submitAndApprovalRecords",submitAndApprovalRecords);
        map.setViewName("approvallist");
        return map;
    }

    @RequestMapping("/approvalcommit")
    public String  approvalcommit(SubmitAndApprovalRecord submitAndApprovalRecord){
        submitAndApprovalRecordService.save(submitAndApprovalRecord);
        return "redirect:/dailyinspectinfo?date="+submitAndApprovalRecord.getCheckdate()+"&unit="+submitAndApprovalRecord.getUnit();
    }

    @RequestMapping("/outputPDF")
    public ResponseEntity<byte[]> outputPDF(String checkdate, String submiter, String unit, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<ImportantPartRecord> importantPartRecords=importantPartRecordService.findAllByDateAndName(checkdate,submiter,unit);
        List<FireworkRecord> fireworkRecords=fireworkRecordService.findAllByDateAndName(checkdate,submiter,unit);
        List<DepositaryRecord> depositaryRecords=depositaryRecordService.findAllByDateAndName(checkdate,submiter,unit);
        List<FireproofdoorRecord> fireproofdoorRecords=fireproofdoorRecordService.findAllByDateAndName(checkdate,submiter,unit);
        List<PersonnelviolationRecord> personnelviolationRecords=personnelviolationRecordService.findAllByDateAndName(checkdate,submiter,unit);
        List<OtherRecord> otherRecords=otherRecordService.findAllByDateAndName(checkdate,submiter,unit);
        String PDFfilename=submiter+"-"+checkdate+"-"+unit;
        SubmitAndApprovalRecord record= submitAndApprovalRecordService.findById(PDFfilename);
        try {
            PdfUtil.createPDF(importantPartRecords,fireworkRecords,depositaryRecords,fireproofdoorRecords,personnelviolationRecords,otherRecords,record);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        req.setCharacterEncoding("utf-8");
        String path="src\\main\\resources\\static\\PDF\\"+PDFfilename+".pdf";
        String filename=PDFfilename+".pdf";
        File file=null;
        HttpHeaders headers=null;
        file=new File(path);
        headers=new HttpHeaders();
        String fileName1 =new String(filename.getBytes("UTF-8"),"iso-8859-1");//解决文件名乱码
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment",fileName1);
        //application/octet-stream二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }

    @RequestMapping("/dailyinspectExcel")
    public void dailyinspectExcel(HttpServletResponse response,String starttime,String endtime,String unit) throws IOException {
        String fileName=unit+"号机组消防巡查问题清单"+starttime+"-"+endtime+".xls";
        fileName= URLEncoder.encode(fileName,"UTF-8");
        List<ImportantPartRecord> list=importantPartRecordService.findByChecktimeAndUnit(unit,starttime,endtime);
        List<FireworkRecord> list1=fireworkRecordService.findByUnitAndChecktime(unit,starttime,endtime);
        List<DepositaryRecord> list2=depositaryRecordService.findByUnitAndChecktime(unit,starttime,endtime);
        List<FireproofdoorRecord> list3=fireproofdoorRecordService.findByUnitAndChecktime(unit,starttime,endtime);
        List<PersonnelviolationRecord> list4=personnelviolationRecordService.findByUnitAndChecktime(unit,starttime,endtime);
        List<OtherRecord> list5=otherRecordService.findByUnitAndChecktime(unit,starttime,endtime);

        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet("消防重点位置");
        int rowNum=1;
        String[] headers={"检查模块","检查人","检查时间","缺陷描述","整改方式","跟踪序号","备注"};
        HSSFRow row=sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (ImportantPartRecord record:list){
            Set<IPRDefect> ipRdefects = record.getIPRdefects();
            if (ipRdefects.size()!=0){
                for (IPRDefect defect:ipRdefects){
                    HSSFRow row1=sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue("消防重点位置");
                    row1.createCell(1).setCellValue(record.getChecker());
                    row1.createCell(2).setCellValue(record.getChecktime());
                    row1.createCell(3).setCellValue(defect.getDefectdesc());
                    row1.createCell(4).setCellValue(defect.getMethod());
                    row1.createCell(5).setCellValue(defect.getTracenumber());
                    row1.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                    rowNum++;
                }
            }else {
                HSSFRow row1=sheet.createRow(rowNum);
                row1.createCell(0).setCellValue("消防重点位置");
                row1.createCell(1).setCellValue(record.getChecker());
                row1.createCell(2).setCellValue(record.getChecktime());
                row1.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                rowNum++;
            }
        }

        HSSFSheet sheet1=workbook.createSheet("动火作业");
        int rowNum1=1;
        HSSFRow row1=sheet1.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row1.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (FireworkRecord record:list1){
            Set<FWDefect> fwDefects = record.getFWDefects();
            if (fwDefects.size()!=0){
                for (FWDefect defect:fwDefects){
                    HSSFRow row2=sheet1.createRow(rowNum1);
                    row2.createCell(0).setCellValue("动火作业");
                    row2.createCell(1).setCellValue(record.getChecker());
                    row2.createCell(2).setCellValue(record.getChecktime());
                    row2.createCell(3).setCellValue(defect.getDefectdesc());
                    row2.createCell(4).setCellValue(defect.getMethod());
                    row2.createCell(5).setCellValue(defect.getTracenumber());
                    row2.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                    rowNum1++;
                }
            }else {
                HSSFRow row2=sheet1.createRow(rowNum1);
                row2.createCell(0).setCellValue("动火作业");
                row2.createCell(1).setCellValue(record.getChecker());
                row2.createCell(2).setCellValue(record.getChecktime());
                row2.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                rowNum1++;
            }
        }

        HSSFSheet sheet2=workbook.createSheet("物品物料,易燃易爆危险化学品存放");
        int rowNum2=1;
        HSSFRow row2=sheet2.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row2.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (DepositaryRecord record:list2){
            Set<DPDefect> dpDefects = record.getDpDefects();
            if (dpDefects.size()!=0){
                for (DPDefect defect:dpDefects){
                    HSSFRow row3=sheet2.createRow(rowNum2);
                    row3.createCell(0).setCellValue("物品物料/易燃易爆危险化学品存放");
                    row3.createCell(1).setCellValue(record.getChecker());
                    row3.createCell(2).setCellValue(record.getChecktime());
                    row3.createCell(3).setCellValue(defect.getDefectdesc());
                    row3.createCell(4).setCellValue(defect.getMethod());
                    row3.createCell(5).setCellValue(defect.getTracenumber());
                    row3.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                    rowNum2++;
                }
            }else {
                HSSFRow row3=sheet2.createRow(rowNum2);
                row3.createCell(0).setCellValue("物品物料/易燃易爆危险化学品存放");
                row3.createCell(1).setCellValue(record.getChecker());
                row3.createCell(2).setCellValue(record.getChecktime());
                row3.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                rowNum2++;
            }
        }

        HSSFSheet sheet3=workbook.createSheet("防火门");
        int rowNum3=1;
        HSSFRow row3=sheet3.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row3.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (FireproofdoorRecord record:list3){
            Set<FDDefect> fdDefects = record.getFdDefects();
            if (fdDefects.size()!=0){
                for (FDDefect defect:fdDefects){
                    HSSFRow row4=sheet3.createRow(rowNum3);
                    row4.createCell(0).setCellValue("防火门");
                    row4.createCell(1).setCellValue(record.getChecker());
                    row4.createCell(2).setCellValue(record.getChecktime());
                    row4.createCell(3).setCellValue(defect.getDefectdesc());
                    row4.createCell(4).setCellValue(defect.getMethod());
                    row4.createCell(5).setCellValue(defect.getTracenumber());
                    row4.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                    rowNum3++;
                }
            }else {
                HSSFRow row4=sheet3.createRow(rowNum3);
                row4.createCell(0).setCellValue("防火门");
                row4.createCell(1).setCellValue(record.getChecker());
                row4.createCell(2).setCellValue(record.getChecktime());
                row4.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                rowNum3++;
            }
        }

        HSSFSheet sheet4=workbook.createSheet("人员违章");
        int rowNum4=1;
        HSSFRow row4=sheet4.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row4.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (PersonnelviolationRecord record:list4){
            Set<PVDefect> pvDefects = record.getPvDefects();
            if (pvDefects.size()!=0){
                for (PVDefect defect:pvDefects){
                    HSSFRow row5=sheet4.createRow(rowNum4);
                    row5.createCell(0).setCellValue("人员违章");
                    row5.createCell(1).setCellValue(record.getChecker());
                    row5.createCell(2).setCellValue(record.getChecktime());
                    row5.createCell(3).setCellValue(defect.getDefectdesc());
                    row5.createCell(4).setCellValue(defect.getMethod());
                    row5.createCell(5).setCellValue(defect.getTracenumber());
                    row5.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                    rowNum4++;
                }
            }else {
                HSSFRow row5=sheet4.createRow(rowNum4);
                row5.createCell(0).setCellValue("人员违章");
                row5.createCell(1).setCellValue(record.getChecker());
                row5.createCell(2).setCellValue(record.getChecktime());
                row5.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                rowNum4++;
            }

        }

        HSSFSheet sheet5=workbook.createSheet("其他问题");
        int rowNum5=1;
        HSSFRow row5=sheet5.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell=row5.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (OtherRecord record:list5){
            Set<ODefect> oDefects = record.getoDefects();
            if (oDefects.size()!=0){
                for (ODefect defect:oDefects){
                    HSSFRow row6=sheet5.createRow(rowNum5);
                    row6.createCell(0).setCellValue("其他问题");
                    row6.createCell(1).setCellValue(record.getChecker());
                    row6.createCell(2).setCellValue(record.getChecktime());
                    row6.createCell(3).setCellValue(defect.getDefectdesc());
                    row6.createCell(4).setCellValue(defect.getMethod());
                    row6.createCell(5).setCellValue(defect.getTracenumber());
                    row6.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                    rowNum5++;
                }
            }else {
                HSSFRow row6=sheet5.createRow(rowNum5);
                row6.createCell(0).setCellValue("其他问题");
                row6.createCell(1).setCellValue(record.getChecker());
                row6.createCell(2).setCellValue(record.getChecktime());
                row6.createCell(6).setCellValue(record.getAttachment()==1?"有附件":"无附件");
                rowNum5++;
            }

        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-disposition","attachment;filename="+fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
