package com.xiaxinyu.email.core;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


import com.xiaxinyu.email.util.TxtFileReader;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Summer.Xiasz on 2016/06/02.
 */
public class EmailHelper {

	public static void sendEmailViaHTMLTemplate(EmailBean bean) throws SendEmailException, MessagingException {
		Properties prop = new Properties();
		prop.put("mail.host", bean.getHost());
		prop.put("mail.transport.protocol", bean.getProtocol());
		prop.put("mail.smtp.auth", bean.getAuthFlag());
		// 使用java发送邮件5步骤
		// 1.创建sesssion
		Session session = Session.getInstance(prop);
		// 开启session的调试模式，可以查看当前邮件发送状态
		session.setDebug(true);

		// 2.通过session获取Transport对象（发送邮件的核心API）
		Transport ts = session.getTransport();
		// 3.通过邮件用户名密码链接
		ts.connect(bean.getUsername(), bean.getPassword());

		// 4.创建邮件
		// 创建邮件对象
		MimeMessage mm = new MimeMessage(session);
		// 设置发件人
		mm.setFrom(new InternetAddress(bean.getFromAccount()));

		// set recipients
		InternetAddress[] toAccouts = getReceivers(bean.getToAccout());
		if (toAccouts == null) {
			throw new SendEmailException("Can't find Recipients.");
		}
		mm.setRecipients(Message.RecipientType.TO, toAccouts);
		// set copy list
		InternetAddress[] ccAcounts = getReceivers(bean.getCcAccout());
		if (ccAcounts != null && ccAcounts.length > 0) {
			mm.setRecipients(Message.RecipientType.CC, ccAcounts); // 抄送人
		}
		// set private copy list
		InternetAddress[] bccAcounts = getReceivers(bean.getBccAccount());
		if (bccAcounts != null && bccAcounts.length > 0) {
			mm.setRecipients(Message.RecipientType.BCC, bccAcounts); // 抄送人
		}

		Multipart multipart = new MimeMultipart();
		// set title for mail
		mm.setSubject(bean.getTitle());
		// set content for mail body
		MimeBodyPart text = new MimeBodyPart();
		String html = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(bean.getHtmlTemplatePath())) {
			if (new File(bean.getHtmlTemplatePath()).exists()) {
				try {

					html = TxtFileReader.readText(bean.getHtmlTemplatePath());
				} catch (IOException e) {
					throw new SendEmailException("Reading doucument conetent fail.");
				}
			}
		} else {
			html = bean.getContent();
		}
		text.setContent(html, "text/html; charset=utf-8");
		multipart.addBodyPart(text);
		// set attachments for email
		setDocuments(multipart, bean.getAttachments());
		// set full content for email
		mm.setContent(multipart);

		// 5.发送电子邮件
		ts.sendMessage(mm, mm.getAllRecipients());
	}

	/**
	 * like summer1@123.com,summer2@123.com...
	 * 
	 * @param receivers
	 * @return
	 * @throws AddressException
	 */
	private static InternetAddress[] getReceivers(String receivers) throws AddressException {
		InternetAddress[] toAccouts = null;
		if (StringUtils.isNotBlank(receivers)) {
			String[] tos = receivers.split(",");
			if (tos != null && tos.length > 0) {
				toAccouts = new InternetAddress[tos.length];
				for (int i = 0; i < toAccouts.length; i++) {
					toAccouts[i] = new InternetAddress(tos[i]);
				}
			}
		}
		return toAccouts;
	}

	/**
	 * like c:\\temp1.txt,c:\\temp2.doc...
	 * 
	 * @param receivers
	 * @return
	 * @throws SendEmailException
	 * @throws AddressException
	 */
	private static void setDocuments(Multipart multipart, String documentPath) throws SendEmailException {
		if (StringUtils.isNotBlank(documentPath)) {
			String[] paths = documentPath.split(",");
			if (paths != null && paths.length > 0) {
				for (String path : paths) {
					try {
						File file = new File(path);
						if (file.exists()) {
							BodyPart attachment = new MimeBodyPart();
							String fileName = StringUtils.EMPTY;

							DataSource source = new FileDataSource(file.getAbsolutePath());
							attachment.setDataHandler(new DataHandler(source));
							fileName = MimeUtility.encodeWord(file.getName());
							if (StringUtils.isBlank(fileName)) {
								throw new SendEmailException("File name is blank.");
							}
							attachment.setFileName(fileName);
							multipart.addBodyPart(attachment);
						} else {
							System.out.println("can't find path from system. AbsolutePath:" + file.getAbsolutePath());
						}
					} catch (MessagingException e1) {
						throw new SendEmailException(e1.getMessage());
					} catch (UnsupportedEncodingException e) {
						throw new SendEmailException("Unsupported encoding exception.");
					}
				}
			}
		}
	}
}
