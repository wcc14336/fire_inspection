package cn.com.jnpc.controller;

import cn.com.jnpc.entity.Attachment;
import cn.com.jnpc.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by cc on 2018/7/24.
 */
@Controller
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;
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
    private FireworkconformRecordService fireworkconformRecordService;
    @Autowired
    private FirerisktaskRecordService firerisktaskRecordService;
    @Autowired
    private RegularInspectRecordService regularInspectRecordService;
    @RequestMapping("/attachments")
    public ModelAndView attachments(String recordid,ModelAndView map,String record){
        if (record==null||"".equals(record)){
            record="";
        }
        List<Attachment> attachments=attachmentService.findByRecordid(recordid);
        map.addObject("attachments",attachments);
        map.addObject("recordid",recordid);
        map.addObject("record",record);
        map.setViewName("attachments");
        return map;
    }
    @RequestMapping("/addattachment")
    public String addattachment(RedirectAttributes attributes, String recordid, @RequestParam(value = "file") MultipartFile file,String record){
        if (record!=null&&!"".equals(record)){
            switch (record){
                case "iprecord":
                    System.out.println("1");
                    importantPartRecordService.updateattachment(1,recordid);
                    System.out.println("2");
                    break;
                case "fwrecord":
                    fireworkRecordService.updateattachment(1,recordid);
                    break;
                case "drecord":
                    depositaryRecordService.updateattachment(1,recordid);
                    break;
                case "fdrecord":
                    fireproofdoorRecordService.updateattachment(1,recordid);
                    break;
                case "precord":
                    personnelviolationRecordService.updateattachment(1,recordid);
                    break;
                case "orecord":
                    otherRecordService.updateattachment(1,recordid);
                    break;
                case "fwcrecord":
                    fireworkconformRecordService.updateattachment(1,recordid);
                    break;
                case "frtrecord":
                    firerisktaskRecordService.updateattachment(1,recordid);
                    break;
                case "ricrecord":
                    regularInspectRecordService.updateattachment(1,recordid);
                    break;

            }
        }
        attributes.addAttribute("recordid",recordid);
        String filename=System.currentTimeMillis()+"-"+file.getOriginalFilename();
        try {
           String filePath = ResourceUtils.getURL("classpath:static/attachments/").getPath()+filename;
            file.transferTo(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(filePath);
        Attachment attachment=new Attachment();
        attachment.setRecordid(recordid);
        attachment.setPicurl(filename);
        attachmentService.save(attachment);
        return "redirect:attachments";
    }
    @RequestMapping("/download")
    public ResponseEntity<byte[]> downloadAttachment(String picurl, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        //String path="src\\main\\resources\\static\\PDF\\"+picurl+".pdf";
        String path=ResourceUtils.getURL("classpath:static/attachments/").getPath()+picurl;
        String filename=picurl;
        File file=null;
        HttpHeaders headers=null;
        file=new File(path);
        headers=new HttpHeaders();
        String fileName=new String(filename.getBytes("UTF-8"),"iso-8859-1");//解决文件名乱码
        //通知浏览器以attachment（下载方式）打开
        headers.setContentDispositionFormData("attachment",fileName);
        //application/octet-stream二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }
}
