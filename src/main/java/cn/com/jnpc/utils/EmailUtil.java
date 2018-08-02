package cn.com.jnpc.utils;

import org.apache.commons.mail.HtmlEmail;

import java.util.List;

/**
 * Created by cc on 2018/7/17.
 */
public class EmailUtil {
    private static String HOSTNAME = "10.23.10.5";
    public static void sendEmail(String mailTitle, String mailContent, List<String> toEmailList) {
        try {
            HtmlEmail email = null;
            for (String toEmail : toEmailList) {
                email = new HtmlEmail();
                email.setHostName(HOSTNAME);//"email.jnpc.com.cn"
                //email.setAuthentication("notification-service", AUTHENTICATION);
                email.setFrom("notification-service@jnpc.net", "系统邮件");
                email.setSubject(mailTitle);
                email.setCharset("GBK");
                email.setHtmlMsg("<html>" + mailContent + "</html>");
                email.addTo(toEmail, "用户");
                email.send();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
