package cn.com.jnpc.controller;

import cn.com.jnpc.entity.RegularTest;
import cn.com.jnpc.entity.RegularTestApprovalRecord;
import cn.com.jnpc.service.RegularTestApprovalRecordService;
import cn.com.jnpc.service.RegularTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
