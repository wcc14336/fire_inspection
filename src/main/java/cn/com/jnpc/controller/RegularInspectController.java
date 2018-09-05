package cn.com.jnpc.controller;

import cn.com.jnpc.dao.MethodDao;
import cn.com.jnpc.dao.PersonDao;
import cn.com.jnpc.entity.*;
import cn.com.jnpc.service.*;
import cn.com.jnpc.utils.EmailUtil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by cc on 2018/7/27.
 */
@Controller
public class RegularInspectController {
    @Autowired
    private RegularInspectService regularInspectService;
    @Autowired
    private RegularInspectRecordService regularInspectRecordService;
    @Autowired
    private EntryAndApprovalRecordSerivce entryAndApprovalRecordSerivce;
    @Autowired
    private EquipService equipService;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private RegularInspectApprovalRecordService regularInspectApprovalRecordService;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private MethodDao methodDao;
    EntryAndApprovalRecord record;
    RegularInspectRecord regularInspectRecord;
    @RequestMapping("/regularinspect")
    public ModelAndView regularinspect(ModelAndView map){

        map.setViewName("regularinspect");
        return map;
    }

    @RequestMapping("/regularinspectlist")
    public ModelAndView regularinspectlist(ModelAndView map,String unit,Integer year){
        if (year==null||"".equals(year)){
            Calendar calendar=null;
            calendar=Calendar.getInstance();
            year=calendar.get(Calendar.YEAR);
        }
        List<RegularInspect> list=regularInspectService.findByUnitAndYear(unit,year);
        map.addObject("list",list);
        map.addObject("year",year);
        map.addObject("unit",unit);
        map.setViewName("regularinspectlist");
        return map;
    }

    @RequestMapping("/changeregularinspect")
    public String changeregularinspect(RegularInspect regularInspect,String unit,Integer year,RedirectAttributes attributes){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularInspectService.save(regularInspect);
        return "redirect:/regularinspectlist";
    }
    @RequestMapping("/changeentryregularinspect")
    public String changeentryregularinspect(RegularInspect regularInspect,String unit,Integer year,RedirectAttributes attributes){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularInspectService.save(regularInspect);
        return "redirect:/entryRegularinspect";
    }

    @RequestMapping("/deleteregularinspect")
    public String deleteregularinspect(String id, String unit, Integer year, RedirectAttributes attributes){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularInspectService.deleteById(id);
        return "redirect:/regularinspectlist";
    }
    @RequestMapping("/deleteentryregularinspect")
    public String deleteentryregularinspect(String id, String unit, Integer year, RedirectAttributes attributes){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularInspectService.deleteById(id);
        regularInspectRecordService.deleteAllByTaskid(id);
        return "redirect:/entryRegularinspect";
    }

    @RequestMapping("/addregularinspect")
    public String addregularinspect(RegularInspect regularInspect,String unit,Integer year,RedirectAttributes attributes){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularInspectService.save(regularInspect);
        return "redirect:/regularinspectlist";
    }
    @RequestMapping("/addentryregularinspect")
    public String addentryregularinspect(RegularInspect regularInspect,String unit,Integer year,RedirectAttributes attributes){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularInspectService.save(regularInspect);
        List<Equipment> equipments=equipService.findAllByCondition(unit,regularInspect.getCheckproject());
        String taskid=regularInspect.getId();
        for (Equipment equipment:equipments){
            regularInspectRecord=new RegularInspectRecord();
            regularInspectRecord.setUnit(unit);
            regularInspectRecord.setYear(year);
            regularInspectRecord.setTaskid(taskid);
            regularInspectRecord.setFactoryBuilding(equipment.getFactoryBuilding());
            regularInspectRecord.setLocation(equipment.getLocation());
            regularInspectRecord.setKks(equipment.getKks());
            regularInspectRecord.setIfchecked(0);
            regularInspectRecord.setName(equipment.getName());
            regularInspectRecord.setCheckcycle(equipment.getCheckcycle());
            regularInspectRecord.setAttachment(0);
            regularInspectRecordService.save(regularInspectRecord);
        }
        return "redirect:/entryRegularinspect";
    }
    @RequestMapping("/entryRegularinspect")
    public ModelAndView entryRegularinspect(String unit, Integer year, HttpSession session,ModelAndView map,String username){
        if (username==null||"".equals(username)){
            Person person= (Person) session.getAttribute("person");
            username=person.getName();
        }
        String id=username+"-"+year+"-"+unit;
        EntryAndApprovalRecord entryAndApprovalRecord=entryAndApprovalRecordSerivce.findById(id);
        if (entryAndApprovalRecord==null){
            record=new EntryAndApprovalRecord();
            record.setId(id);
            record.setUnit(unit);
            record.setYear(year);
            entryAndApprovalRecordSerivce.save(record);
        }
        EntryAndApprovalRecord entryAndApprovalRecord1=entryAndApprovalRecordSerivce.findById(id);
        map.addObject("entryAndApprovalRecord",entryAndApprovalRecord1);
        List<RegularInspect> list = regularInspectService.findByUnitAndYear(unit, year);
        map.addObject("list",list);
        map.addObject("unit",unit);
        map.addObject("year",year);
        map.setViewName("entryRegularinspect");
        return map;
    }

    @RequestMapping("/entrysubmit")
    public String entrysubmit(RedirectAttributes attributes,String submiter,String submitdate,Integer submitstate,Integer year,String unit){
        String id=submiter+"-"+year+"-"+unit;
        record=new EntryAndApprovalRecord();
        record.setId(id);
        record.setSubmiter(submiter);
        record.setSubmitdate(submitdate);
        record.setSubmitstate(1);
        record.setYear(year);
        record.setUnit(unit);
        record.setApprovalstate(0);
        record.setApprovalresult(0);
        entryAndApprovalRecordSerivce.save(record);
        List<String> list=personDao.findEngineer();
        //list.add("1433658618@qq.com");
        emailUtil.sendEmail("年度定检计划审批","有一份年度定期检查计划需要审批",list);
        attributes.addAttribute("unit",record.getUnit());
        attributes.addAttribute("year",record.getYear());
        attributes.addAttribute("username",record.getSubmiter());
        return "redirect:entryRegularinspect";
    }
    @RequestMapping("/entryapprovalcommit")
    public String entryapprovalcommit(RedirectAttributes attributes,EntryAndApprovalRecord entryAndApprovalRecord){
        entryAndApprovalRecordSerivce.save(entryAndApprovalRecord);
        attributes.addAttribute("year",entryAndApprovalRecord.getYear());
        attributes.addAttribute("unit",entryAndApprovalRecord.getUnit());
        return "redirect:/entryRegularinspect";
    }
    @RequestMapping("/approvalregularinspect")
    public ModelAndView approvalregularinspect(ModelAndView map){
        map.setViewName("approvalregularinspectlist");
        List<EntryAndApprovalRecord> list=entryAndApprovalRecordSerivce.findBycondition();
        map.addObject("list",list);
        return map;
    }
    @RequestMapping("/myRegularinspecttask")
    public ModelAndView myRegularinspecttask(ModelAndView map,HttpSession session){
        Person person= (Person) session.getAttribute("person");
        String username=person.getName();
        List<RegularInspect> list=regularInspectService.findMyUndoneInspect(username,0);
        List<RegularInspect> list1=regularInspectService.findMyUndoneInspect(username,1);
        map.addObject("list1",list1);
        map.addObject("list",list);
        map.setViewName("myRegularinspecttask");
        return map;
    }
    @RequestMapping("/regularinspectrecord")
    public ModelAndView regularinspectrecord(ModelAndView map,String checkproject,String unit,Integer year,String taskid){
        String id=taskid;
        RegularInspectApprovalRecord regularInspectApprovalRecord=regularInspectApprovalRecordService.findById(id);
        if (regularInspectApprovalRecord==null){
            regularInspectApprovalRecord=new RegularInspectApprovalRecord();
            regularInspectApprovalRecord.setId(id);
            regularInspectApprovalRecord.setTaskid(id);
            regularInspectApprovalRecord.setUnit(unit);
            regularInspectApprovalRecord.setCheckproject(checkproject);
            regularInspectApprovalRecord.setYear(year);
            regularInspectApprovalRecordService.save(regularInspectApprovalRecord);
        }
        RegularInspectApprovalRecord regularInspectApprovalRecord1=regularInspectApprovalRecordService.findById(id);
        map.addObject("regularInspectApprovalRecord",regularInspectApprovalRecord1);
        List<RegularInspectRecord> list=regularInspectRecordService.findByTaskid(taskid);
        if (list.size()==0){
            List<Equipment> equipments=equipService.findAllByCondition(unit,checkproject);
            for (Equipment equipment:equipments){
                regularInspectRecord=new RegularInspectRecord();
                regularInspectRecord.setUnit(unit);
                regularInspectRecord.setYear(year);
                regularInspectRecord.setTaskid(taskid);
                regularInspectRecord.setFactoryBuilding(equipment.getFactoryBuilding());
                regularInspectRecord.setLocation(equipment.getLocation());
                regularInspectRecord.setKks(equipment.getKks());
                regularInspectRecord.setIfchecked(0);
                regularInspectRecord.setName(equipment.getName());
                regularInspectRecord.setCheckcycle(equipment.getCheckcycle());
                regularInspectRecord.setAttachment(0);
                regularInspectRecord.setState(0);
                regularInspectRecordService.save(regularInspectRecord);
            }
        }
        list=regularInspectRecordService.findByTaskid(taskid);
        List<String> list1=methodDao.finddistinctmethod();
        map.addObject("methods",list1);
        map.addObject("list",list);
        map.setViewName("regularinspectrecordlist");
        map.addObject("taskid",taskid);
        map.addObject("unit",unit);
        map.addObject("year",year);
        map.addObject("checkproject",checkproject);
        return map;
    }
    @RequestMapping("/entryregularinspectrecord")
    public String entryregularinspectrecord(RedirectAttributes attributes,RegularInspectRecord regularInspectRecord){
        regularInspectRecordService.save(regularInspectRecord);
        attributes.addAttribute("checkproject",regularInspectRecord.getName());
        attributes.addAttribute("year",regularInspectRecord.getYear());
        attributes.addAttribute("unit",regularInspectRecord.getUnit());
        attributes.addAttribute("taskid",regularInspectRecord.getTaskid());
        return "redirect:regularinspectrecord";
    }
    @RequestMapping("/submitregularinspectrecord")
    public String submitregularinspectrecord(RedirectAttributes attributes,String submiter,String submitdate,Integer submitstate,String taskid,String unit,String checkproject,Integer year){
        String id=taskid;
        RegularInspectApprovalRecord regularInspectApprovalRecord=regularInspectApprovalRecordService.findById(taskid);

        regularInspectApprovalRecord.setSubmiter(submiter);
        regularInspectApprovalRecord.setSubmitdate(submitdate);
        regularInspectApprovalRecord.setSubmitstate(submitstate);
        regularInspectApprovalRecord.setApprovalstate(0);
        regularInspectApprovalRecord.setApprovalresult(0);
        regularInspectApprovalRecordService.save(regularInspectApprovalRecord);
        List<String> list=personDao.findEngineer();
        //list.add("1433658618@qq.com");
        emailUtil.sendEmail("定期检查任务审批","系统中有定期检查任务需要审批",list);
        attributes.addAttribute("checkproject",checkproject);
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        attributes.addAttribute("taskid",taskid);
        return "redirect:regularinspectrecord";
    }
    @RequestMapping("/regularinspectrecordcommit")
    public String regularinspectrecordcommit(RedirectAttributes attributes,RegularInspectApprovalRecord regularInspectApprovalRecord,String taskid,String unit,String checkproject,Integer year){
        regularInspectApprovalRecordService.save(regularInspectApprovalRecord);
        if (regularInspectApprovalRecord.getApprovalresult()==1){
            RegularInspect regularInspect=regularInspectService.findByid(taskid);
            String begintime=regularInspectRecordService.findbegintime(taskid);
            String endtime=regularInspectRecordService.findendtime(taskid);
            List<String> checkers=regularInspectRecordService.findAllcheckers(taskid);
            String checker="";
            for(String s:checkers){
                checker+=s+"/";
            }
            if (checker.length()==0){
                checker="";
            }else {
                checker=checker.substring(0,checker.length()-1);
            }
            regularInspect.setBegintime(begintime);
            regularInspect.setState(1);
            regularInspect.setEndtime(endtime);
            regularInspect.setChecker(checker);
            regularInspectService.save(regularInspect);
        }
        attributes.addAttribute("checkproject",checkproject);
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        attributes.addAttribute("taskid",taskid);
        return "redirect:regularinspectrecord";
    }
    @RequestMapping("/approvalRegularinspecttask")
    public ModelAndView approvalRegularinspecttask(ModelAndView map){
        List<RegularInspectApprovalRecord> list=regularInspectApprovalRecordService.findBycondition();
        map.addObject("list",list);
        map.setViewName("approvalRegularinspecttask");
        return map;
    }
    @RequestMapping("/regularinspectrecordPDF")
    public void regularinspectrecordPDF(HttpServletResponse response,String taskid,String unit,String checkproject,Integer year) throws IOException, DocumentException {
        String fileName=unit+"号机组"+checkproject+"定期检查文件"+year+".pdf";
        fileName= URLEncoder.encode(fileName,"UTF-8");
        response.setHeader("content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        //字体设置
        BaseFont bf=BaseFont.createFont("C:\\Windows\\Fonts\\simkai.ttf", BaseFont.IDENTITY_H, false);
        //创建Font对象，将基础字体对象，字体大小，字体风格
        Font font=new Font(bf,13,Font.NORMAL);
        Font font1=new Font(bf,15,Font.BOLD);
        Font font2=new Font(bf,10,Font.NORMAL);
        List<RegularInspectRecord> list=regularInspectRecordService.findByTaskid(taskid);
        System.out.println(list.size());
        RegularInspectApprovalRecord approvalRecord=regularInspectApprovalRecordService.findByTaskid(taskid);
        Paragraph title=new Paragraph(unit+"号机组"+checkproject+"定期检查文件("+year+")\n\n",font1);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        Paragraph line=new Paragraph("        检查人："+approvalRecord.getSubmiter()+"/"+approvalRecord.getSubmitdate()+"             "+"批准人："+approvalRecord.getApprovaler()+"/"+approvalRecord.getApprovaldate()+"\n\n",font);
        document.add(line);
        PdfPTable table=new PdfPTable(14);
        PdfPCell header=new PdfPCell();
        header.setPhrase(new Paragraph("序号",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("机组",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("厂房",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("位置",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("kks",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("名称",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("定检周期",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("状态",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("检查人",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("检查时间",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("缺陷描述",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("整改方式",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("跟踪单号",font2));
        table.addCell(header);
        header=new PdfPCell();
        header.setPhrase(new Paragraph("备注",font2));
        table.addCell(header);
        for (int i = 0; i < list.size(); i++) {
            PdfPCell cell=new PdfPCell();
            cell.setPhrase(new Paragraph(i+"",font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getUnit(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getFactoryBuilding(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getLocation(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getKks(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getName(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getCheckcycle(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getState()==1?"√":"×",font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getChecker(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getChecktime(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getDefectdesc(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getMethod(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getTracenumber(),font2));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Paragraph(list.get(i).getAttachment()==null||list.get(i).getAttachment()!=1?"无附件":"有附件",font2));
            table.addCell(cell);
        }
        document.add(table);
        document.close();
    }
}
