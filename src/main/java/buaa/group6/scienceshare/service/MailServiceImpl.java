package buaa.group6.scienceshare.service;


import buaa.group6.scienceshare.Result.Result;
import buaa.group6.scienceshare.Result.ResultCode;
import buaa.group6.scienceshare.Result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@Primary
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    public static final String SINGLE_EMAIL_REGEX = "(?:(?:[A-Za-z0-9\\-_@!#$%&'*+/=?^`{|}~]|(?:\\\\[\\x00-\\xFF]?)|(?:\"[\\x00-\\xFF]*\"))+(?:\\.(?:(?:[A-Za-z0-9\\-_@!#$%&'*+/=?^`{|}~])|(?:\\\\[\\x00-\\xFF]?)|(?:\"[\\x00-\\xFF]*\"))+)*)@(?:(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+(?:(?:[A-Za-z0-9]*[A-Za-z][A-Za-z0-9]*)(?:[A-Za-z0-9-]*[A-Za-z0-9])?))";


    @Override
    public Result sendPin(String toEmail) {
        if(!validEmailAddress(toEmail))return ResultFactory.buildFailResult(ResultCode.INVALID_EMAIL_ADDRESS);
        String uuid=new String();
        try{
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg,true,"utf-8");
            helper.setFrom("learningCom110@163.com");//发件人
            helper.setTo(toEmail);//收件人
            helper.setCc("learningCom110@163.com");
            helper.setSubject("学术资源交流论坛登录验证");//邮件标题
            //验证码
            String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            for(int i=0;i<6;i++) {
                char ch = str.charAt(new Random().nextInt(str.length()));
                uuid += ch;
            }
            String message = "<div><font face=\"宋体\"><span style=\"font-size: large;\">您的注册验证码是：&nbsp;<br></span><b><font size=\"6\"><u>" +
                    uuid+"</font></b><font size=\"4\">（区分大小写）</font></font><div style=\"font-size: large;\"><font face=\"宋体\">欢迎使用学术资源交流论坛。" +
                    "</font></div><div style=\"font-size: large;\"><font face=\"宋体\">这个验证码将在您收到这封邮件5分钟后失效。</font>&nbsp;</div></div>";
            helper.setText(message, true);
            mailSender.send(msg);
        }catch (MessagingException e){
            return ResultFactory.buildFailResult(e.getMessage());
        }
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setFrom("learningCom110@163.com");
//        message.setTo(toEmail);
//        message.setSubject("学习生活交流论坛登录验证");
        return ResultFactory.buildSuccessResult(uuid);
    }

    Boolean validEmailAddress(String emailAddress){
        return emailAddress.matches(SINGLE_EMAIL_REGEX);
    }



}
