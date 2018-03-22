package com.thinkgem.jeesite.common.utils;

import com.thinkgem.jeesite.common.supcan.common.properties.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public class EmailUtils {

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String myEmailAccount = "hezijian6338@163.com";
    public static String myEmailPassword = "hezijian6338";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.163.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    //public static String receiveMailAccount = "373330860@qq.com";

    public static boolean WriteLetter(String receiveMailAccount) throws Exception {
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 3. 创建一封邮件

        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();

        return true;

    }

    public static boolean WriteLetter(String receiveMailAccount,String loginFlag) throws Exception {
        // 1. 创建一封邮件
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, loginFlag);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();

        return true;
    }

    /**
     * 创建一封只包含文本的简单邮件 这是一封关于用户新建时候的邮箱通知
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String loginFlag) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, "PDF模版系统管理总公司", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        if(loginFlag.equals("0")) {
            message.setSubject("关于新建用户未能通过审核通知", "UTF-8");
            message.setContent("亲爱的用户，<br>  由于你的申请未能通过管理员的审核，如果需要重新创建请联系系统管理员，或者重新申请。<br>  谢谢你对我公司的支持。", "text/html;charset=UTF-8");
        }else if(loginFlag.equals("1")){
            message.setSubject("关于新建用户成功通过审核通知", "UTF-8");
            message.setContent("亲爱的用户，<br>  由于你的申请通过了管理员的审核，你的用户已经能登录我们公司的系统了。<br>  谢谢你对我公司的支持。", "text/html;charset=UTF-8");
        }else if(loginFlag.equals("3")){
            message.setSubject("关于该公司已成功创建通知","UTF-8");
            message.setContent("亲爱的用户，<br>我PDF总公司已经成功为贵公司创建账号，欢迎使用我们系统。<br>谢谢您对我公司的支持。", "text/html;charset=UTF-8");
        }

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    /**
     * 创建一封只包含文本的简单邮件 这是一封关于用户新建元素后用户收到的邮件通知
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, "PDF模版系统管理总公司", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setSubject("管理元已审核模版元素通知", "UTF-8");
        message.setContent("亲爱的用户，<br>  您之前申请的模版元素，管理员你已经经过审核，结果请自行查询！<br>  谢谢你对我公司的支持。", "text/html;charset=UTF-8");


        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
}
