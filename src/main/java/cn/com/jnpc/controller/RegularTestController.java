package cn.com.jnpc.controller;

import cn.com.jnpc.entity.RegularInspectRecord;
import cn.com.jnpc.entity.RegularTest;
import cn.com.jnpc.entity.RegularTestApprovalRecord;
import cn.com.jnpc.service.RegularTestApprovalRecordService;
import cn.com.jnpc.service.RegularTestService;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
        return map;
    }
}
