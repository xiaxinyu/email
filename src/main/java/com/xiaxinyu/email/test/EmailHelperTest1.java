package com.xiaxinyu.email.test;

import com.xiaxinyu.email.util.TxtFileReader;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHelperTest1 {
	public static MimeMessage createSimpleMail(Session session) throws AddressException, MessagingException, IOException {
		// 创建邮件对象
		MimeMessage mm = new MimeMessage(session);
		// 设置发件人
		mm.setFrom(new InternetAddress("summer_west2010@126.com"));
		// 设置收件人
		mm.setRecipient(Message.RecipientType.TO, new InternetAddress("330146068@qq.com"));
		// 设置抄送人
		//mm.setRecipient(Message.RecipientType.CC, new InternetAddress("用户名@163.com"));
		mm.setSubject(String.valueOf(new Random().nextLong()));
		
		String html = TxtFileReader.readText("c:\\limitorder-approved.html");
		mm.setContent(html, "text/html;charset=UTF-8");

		return mm;

	}

	public static void main(String[] args) throws Exception {
		Properties prop = new Properties();
		prop.put("mail.host", "smtp.126.com");
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", true);
		// 使用java发送邮件5步骤
		// 1.创建sesssion
		Session session = Session.getInstance(prop);
		// 开启session的调试模式，可以查看当前邮件发送状态
		session.setDebug(true);

		// 2.通过session获取Transport对象（发送邮件的核心API）
		Transport ts = session.getTransport();
		// 3.通过邮件用户名密码链接
		ts.connect("summer_west2010@126.com", "summer156242");

		// 4.创建邮件

		Message msg = createSimpleMail(session);

		// 5.发送电子邮件

		ts.sendMessage(msg, msg.getAllRecipients());
	}
}
