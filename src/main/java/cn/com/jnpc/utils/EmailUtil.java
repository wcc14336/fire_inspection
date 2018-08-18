package cn.com.jnpc.utils;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by cc on 2018/7/17.
 */
@Service
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String mailTitle, String mailContent, List<String> toEmailList) {
        try {
            for (String toEmail : toEmailList) {
                /*email = new HtmlEmail();
                email.setHostName(HOSTNAME);//"email.jnpc.com.cn"
                //email.setAuthentication("notification-service", AUTHENTICATION);
                email.setFrom("notification-service@jnpc.net", "系统邮件");
                email.setSubject(mailTitle);
                email.setCharset("GBK");
                email.setHtmlMsg("<html>" + mailContent + "</html>");
                email.addTo(toEmail, "用户");
                email.send();*/
                MimeMessage message = null;
                message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom("1433658618@qq.com");
                helper.setTo(toEmail);
                helper.setSubject(mailTitle);/*
                StringBuffer sb = new StringBuffer();
                sb.append("<h1>大标题-h1</h1>")
                        .append("<p style='color:#F00'>红色字</p>")
                        .append("<p style='text-align:right'>右对齐</p>");*/
                helper.setText(mailContent, true);
                mailSender.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
