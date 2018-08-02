package cn.com.jnpc.controller;

import cn.com.jnpc.entity.Method;
import cn.com.jnpc.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by cc on 2018/7/25.
 */
@Controller
public class MethodController {
    @Autowired
    private MethodService methodService;
    @RequestMapping("/method")
    public ModelAndView method(ModelAndView map){
        map.setViewName("method");
        List<Method> methods=methodService.getAll();
        map.addObject("methods",methods);
        return map;
    }
    @RequestMapping("/deletemethod")
    public String deletemethod(String id){
        methodService.deletemethodById(id);
        return "redirect:/method";
    }
    @RequestMapping("/addmethod")
    public String addmethod(Method method){
        methodService.save(method);
        return "redirect:/method";
    }
}
