package com.xiaxinyu.email.test;

import com.xiaxinyu.email.core.EmailBean;
import com.xiaxinyu.email.core.EmailHelper;

import java.util.Random;


public class EmailHelperExample {
	
	public static void main(String[] args) throws Exception {
		EmailBean bean = new EmailBean();
		bean.setHost("smtp.126.com");
		bean.setProtocol("smtp");
		bean.setAuthFlag(true);
		bean.setUsername("summer_west2010@126.com");
		bean.setPassword("summer156242");
		bean.setFromAccount("summer_west2010@126.com");
		bean.setToAccout("330146068@qq.com");
		bean.setAttachments("c:\\linux_command_manual.txt,c:\\WeChat Image_20170510161848.jpg");
		//bean.setCcAccout("summer_west2010@126.com");
		//bean.setBccAccount("Summer.Xiasz@homecredit.cn");
		//bean.setToAccout("summer_west2010@126.com");
		//bean.setToAccout("Xiaolu5771@163.com");
		//bean.setToAccout("Summer.Xiasz@homecredit.cn");
		bean.setTitle(String.valueOf(Math.abs(new Random().nextLong())));
		//bean.setDocumentPath("c:\\limitorder-approved.html");
		bean.setContent("123456");
		EmailHelper.sendEmailViaHTMLTemplate(bean);
	}
}
