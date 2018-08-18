package cn.com.jnpc.controller;

import cn.com.jnpc.entity.*;
import cn.com.jnpc.service.*;
import cn.com.jnpc.utils.EmailUtil;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by cc on 2018/8/2.
 */
@Controller
public class RegularTestController {
    @Autowired
    private RegularTestService regularTestService;
    @Autowired
    private RegularTestApprovalRecordService regularTestApprovalRecordService;
    @Autowired
    private RegularTestRecordApprovalService regularTestRecordApprovalService;
    @Autowired
    private RegularTestRecordService regularTestRecordService;
    @Autowired
    private EquipService equipService;
    @Autowired
    private RTDefectService rtDefectService;
    @Autowired
    private EmailUtil emailUtil;
    @RequestMapping("/regulartest")
    public ModelAndView regulartest(ModelAndView map){
        map.setViewName("regulartest");
        return map;
    }
    @RequestMapping("/entryRegulartest")
    public ModelAndView entryRegulartest(ModelAndView map, String unit, Integer year, HttpSession session,String username){
        if (username==null||"".equals(username)){
            username = (String) session.getAttribute("username");
        }
        String id=username+"-"+year+"-"+unit;
        RegularTestApprovalRecord regularTestApprovalRecord=regularTestApprovalRecordService.findByid(id);
        if (regularTestApprovalRecord==null){
            RegularTestApprovalRecord record=new RegularTestApprovalRecord();
            record.setId(id);
            record.setYear(year);
            record.setUnit(unit);
            regularTestApprovalRecordService.save(record);
        }
        RegularTestApprovalRecord regularTestApprovalRecord1=regularTestApprovalRecordService.findByid(id);
        map.addObject("regularTestApprovalRecord",regularTestApprovalRecord1);
        List<RegularTest> list=regularTestService.findByUnitAndYear(unit,year);
        map.addObject("list",list);
        map.addObject("unit",unit);
        map.addObject("year",year);
        map.setViewName("entryRegulartest");
        return map;
    }
    @RequestMapping("/addentryregulartest")
    public String addentryregulartest(RedirectAttributes attributes,RegularTest regularTest,String unit,Integer year){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularTestService.save(regularTest);
        return "redirect:entryRegulartest";
    }
    @RequestMapping("/changeentryregulartest")
    public String changeentryregulartest(RedirectAttributes attributes,RegularTest regularTest,String unit,Integer year){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularTestService.save(regularTest);
        return "redirect:entryRegulartest";
    }
    @RequestMapping("/deleteentryregulartest")
    public String deleteentryregulartest(RedirectAttributes attributes,String id,String unit,Integer year){
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        regularTestService.deleteByid(id);
        return "redirect:entryRegulartest";
    }
    @RequestMapping("/entrytestsubmit")
    public String entrytestsubmit(RedirectAttributes attributes,String submiter,String submitdate,Integer submitstate,Integer year,String unit){
        String id=submiter+"-"+year+"-"+unit;
        RegularTestApprovalRecord regularTestApprovalRecord=new RegularTestApprovalRecord();
        regularTestApprovalRecord.setId(id);
        regularTestApprovalRecord.setUnit(unit);
        regularTestApprovalRecord.setYear(year);
        regularTestApprovalRecord.setSubmiter(submiter);
        regularTestApprovalRecord.setSubmitdate(submitdate);
        regularTestApprovalRecord.setSubmitstate(submitstate);
        regularTestApprovalRecord.setApprovalstate(0);
        regularTestApprovalRecord.setApprovalstate(0);
        regularTestApprovalRecordService.save(regularTestApprovalRecord);
        List<String> list=new ArrayList<>();
        list.add("1433658618@qq.com");
        emailUtil.sendEmail("年度定期试验计划审批","系统中有年度定期试验计划需要审批",list);
        attributes.addAttribute("unit",regularTestApprovalRecord.getUnit());
        attributes.addAttribute("year",regularTestApprovalRecord.getYear());
        return "redirect:entryRegulartest";
    }
    @RequestMapping("/entrytestapprovalcommit")
    public String entrytestapprovalcommit(RegularTestApprovalRecord regularTestApprovalRecord,RedirectAttributes attributes){
        regularTestApprovalRecordService.save(regularTestApprovalRecord);
        attributes.addAttribute("unit",regularTestApprovalRecord.getUnit());
        attributes.addAttribute("year",regularTestApprovalRecord.getYear());
        return "redirect:entryRegulartest";
    }
    @RequestMapping("/approvalregulartest")
    public ModelAndView approvalregulartest(ModelAndView map){
        map.setViewName("approvalregulartest");
        List<RegularTestApprovalRecord> list=regularTestApprovalRecordService.findbyCondition();
        map.addObject("list",list);
        return map;
    }
    @RequestMapping("/myRegulartesttask")
    public ModelAndView myRegulartesttask(ModelAndView map, HttpSession session){
        String username= (String) session.getAttribute("username");
        List<RegularTest> list=regularTestService.findMyUndonetask(username,0);
        List<RegularTest> list1=regularTestService.findMyUndonetask(username,1);
        map.addObject("list",list);
        map.addObject("list1",list1);
        map.setViewName("myRegulartesttask");
        return map;
    }
    @RequestMapping("/regulartestrecord")
    public ModelAndView regulartestrecord(ModelAndView map,String checkproject,String unit,Integer year,String taskid){
        String id=taskid;
        RegularTestRecordApproval regularTestRecordApproval=regularTestRecordApprovalService.findById(id);
        if (regularTestRecordApproval==null){
            regularTestRecordApproval=new RegularTestRecordApproval();
            regularTestRecordApproval.setId(id);
            regularTestRecordApproval.setTaskid(id);
            regularTestRecordApproval.setUnit(unit);
            regularTestRecordApproval.setYear(year);
            regularTestRecordApproval.setCheckproject(checkproject);
            regularTestRecordApprovalService.save(regularTestRecordApproval);
        }
        RegularTestRecordApproval regularTestRecordApproval1=regularTestRecordApprovalService.findById(id);
        map.addObject("regulartestApprovalRecord",regularTestRecordApproval1);
        List<RegularTestRecord> list=regularTestRecordService.findByTaskid(taskid);
        if (list.size()==0){
            List<Equipment> equipments=equipService.findAllByCondition(unit,checkproject);
            for(Equipment equipment:equipments){
                RegularTestRecord regularTestRecord=new RegularTestRecord();
                regularTestRecord.setUnit(unit);
                regularTestRecord.setYear(year);
                regularTestRecord.setTaskid(taskid);
                regularTestRecord.setFactoryBuilding(equipment.getFactoryBuilding());
                regularTestRecord.setLocation(equipment.getLocation());
                regularTestRecord.setKks(equipment.getKks());
                regularTestRecord.setIfchecked(0);
                regularTestRecord.setName(equipment.getName());
                regularTestRecord.setCheckcycle(equipment.getTestcycle());
                regularTestRecord.setAttachment(0);
                regularTestRecordService.save(regularTestRecord);
            }
        }
        list=regularTestRecordService.findByTaskid(taskid);
        map.addObject("list",list);
        map.setViewName("regulartestrecordlist");
        map.addObject("taskid",taskid);
        map.addObject("unit",unit);
        map.addObject("year",year);
        map.addObject("checkproject",checkproject);
        return map;
    }
    @RequestMapping("/submitregulartestrecord")
    public String submitregulartestrecord(RedirectAttributes attributes,String submiter,String submitdate,Integer submitstate,String taskid,String unit,String checkproject,Integer year){
        String id=taskid;
        RegularTestRecordApproval regularTestRecordApproval = regularTestRecordApprovalService.findById(id);
        regularTestRecordApproval.setSubmiter(submiter);
        regularTestRecordApproval.setSubmitstate(submitstate);
        regularTestRecordApproval.setSubmitdate(submitdate);
        regularTestRecordApproval.setApprovalstate(0);
        regularTestRecordApproval.setApprovalresult(0);
        regularTestRecordApprovalService.save(regularTestRecordApproval);
        List<String> list=new ArrayList<>();
        list.add("1433658618@qq.com");
        emailUtil.sendEmail("定期试验任务待审批","系统中有定期试验任务需要审批",list);
        attributes.addAttribute("checkproject",checkproject);
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        attributes.addAttribute("taskid",taskid);
        return "redirect:regulartestrecord";
    }
    @RequestMapping("/regulartestrecordcommit")
    public String regulartestrecordcommit(RedirectAttributes attributes,RegularTestRecordApproval regularTestRecordApproval,String taskid,String unit,String checkproject,Integer year){
        regularTestRecordApprovalService.save(regularTestRecordApproval);
        if (regularTestRecordApproval.getApprovalresult()==1){
            RegularTest regularTest=regularTestService.findbyid(taskid);
            String begintime=regularTestRecordService.findbegintime(taskid);
            String endtime=regularTestRecordService.findendtime(taskid);
            List<String> checkers=regularTestRecordService.findAllcheckers(taskid);
            System.out.println(checkers.size());
            String checker="";
            for(String s:checkers){
                checker+=s+"/";
            }
            checker=checker.substring(0,checker.length()-1);
            regularTest.setBegintime(begintime);
            regularTest.setState(1);
            regularTest.setEndtime(endtime);
            regularTest.setChecker(checker);
            regularTestService.save(regularTest);
        }
        attributes.addAttribute("checkproject",checkproject);
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        attributes.addAttribute("taskid",taskid);
        return "redirect:regulartestrecord";
    }
    @RequestMapping("/changertdefect")
    public String changertdefect(RTDefect rtDefect,String recordid,String taskid,String unit,String checkproject,Integer year,RedirectAttributes attributes){
        RegularTestRecord regularTestRecord=regularTestRecordService.findbyid(recordid);
        rtDefect.setRegularTestRecord(regularTestRecord);
        rtDefectService.save(rtDefect);
        attributes.addAttribute("checkproject",checkproject);
        attributes.addAttribute("unit",unit);
        attributes.addAttribute("year",year);
        attributes.addAttribute("taskid",taskid);
        return "redirect:regulartestrecord";
    }
    @RequestMapping("/approvalRegulartesttask")
    public ModelAndView approvalRegulartesttask(ModelAndView map){
        List<RegularTestRecordApproval> list=regularTestRecordApprovalService.findbyCondition();
        map.addObject("list",list);
        map.setViewName("approvalRegulartesttask");
        return map;
    }
    @RequestMapping("/regulartestlist")
    public ModelAndView regulartestlist(ModelAndView map,String unit,Integer year){
        if (year==null||"".equals(year)){
            Calendar calendar=null;
            calendar=Calendar.getInstance();
            year=calendar.get(Calendar.YEAR);
        }
        List<RegularTest> list=regularTestService.findByUnitAndYear(unit,year);
        map.addObject("list",list);
        map.addObject("year",year);
        map.addObject("unit",unit);
        map.setViewName("regulartestlist");
        return map;
    }
}
