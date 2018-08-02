package cn.com.jnpc.controller;

import cn.com.jnpc.entity.*;
import cn.com.jnpc.service.*;
import cn.com.jnpc.utils.PdfUtil;
import com.lowagie.text.DocumentException;
import org.apache.commons.io.FileUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
