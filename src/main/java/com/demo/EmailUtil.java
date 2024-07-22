package com.demo;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
    //private static final String USERNAME = "ming@xzcglx.cn"; // 替换为您的飞书邮箱
    //private static final String USERNAME = "aliyun999@foxmail.com"; // 替换为您的飞书邮箱
    private static final String USERNAME = "cn.999@foxmail.com"; // 替换为您的飞书邮箱
    //private static final String PASSWORD = "MCGE9OMeljDRenzu"; // 替换为您的飞书SMTP密码
    //private static final String PASSWORD = "kxqeygfnhliidbgd"; // 替换为您的飞书SMTP密码
    private static final String PASSWORD = "zuijmmznmpsgbddi"; // 替换为您的飞书SMTP密码

    public static void sendEmail(String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // 使用TLS
        //props.put("mail.smtp.host", "smtp.feishu.cn");
        props.put("mail.smtp.host", "smtp.qq.com");
        //props.put("mail.smtp.port", "587"); // TLS端口
        props.put("mail.smtp.port", "25"); // TLS端口

        // 如果需要使用SSL，请取消注释以下代码
        // props.put("mail.smtp.ssl.enable", "true"); // 使用SSL
        // props.put("mail.smtp.port", "465"); // SSL端口

        props.put("mail.smtp.connectiontimeout", "10000"); // 连接超时（毫秒）
        props.put("mail.smtp.timeout", "10000"); // 读取超时（毫秒）
        props.put("mail.smtp.writetimeout", "10000"); // 写入超时（毫秒）

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("邮件发送成功");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
