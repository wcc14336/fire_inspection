package cn.com.jnpc.controller;

import cn.com.jnpc.entity.*;
import cn.com.jnpc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    private RegularInspectApprovalRecordService regularInspectApprovalRecordService;
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
        return "redirect:/entryRegularinspect";
    }
    @RequestMapping("/entryRegularinspect")
    public ModelAndView entryRegularinspect(String unit, Integer year, HttpSession session,ModelAndView map,String username){
        if (username==null||"".equals(username)){
            username = (String) session.getAttribute("username");
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
        record.setSubmitstate(submitstate);
        record.setYear(year);
        record.setUnit(unit);
        record.setApprovalstate(0);
        record.setApprovalresult(0);
        entryAndApprovalRecordSerivce.save(record);
        attributes.addAttribute("unit",record.getUnit());
        attributes.addAttribute("year",record.getYear());
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
        String username= (String) session.getAttribute("username");
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
                regularInspectRecordService.save(regularInspectRecord);
            }
        }
        list=regularInspectRecordService.findByTaskid(taskid);
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
            checker=checker.substring(0,checker.length()-1);
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
}
