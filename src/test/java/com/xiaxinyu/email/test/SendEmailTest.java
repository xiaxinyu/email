package com.xiaxinyu.email.test;

import com.xiaxinyu.email.EmailApplication;
import com.xiaxinyu.email.core.EmailBean;
import com.xiaxinyu.email.core.EmailHelper;
import com.xiaxinyu.email.core.FreeMarkerTemplateHelper;
import com.xiaxinyu.email.core.SendEmailException;
import com.xiaxinyu.email.util.FileReader;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmailApplication.class})
public class SendEmailTest {

    @Autowired
    FreeMarkerTemplateHelper freeMarkerTemplateHelper;

    private String username = "xxx";
    private String password = "xxxxxx";
    private String fromAccount = "xxx@126.com";
    private String toAccount = "xxx@qq.com";

    @Test
    public void test_send_email_from_file() throws IOException, TemplateException, SendEmailException, MessagingException {
        //读取模板文件内容
        String file = "test_template_render_from_file.html";
        String templateContent = FileReader.loadEmailTemplate(file);

        String key = "h1";

        //添加模板
        freeMarkerTemplateHelper.addTemplate(key, templateContent);
        Template template = freeMarkerTemplateHelper.getTemplate(key);
        Assert.assertNotNull(template);

        //渲染模板
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "Email");
        String content = freeMarkerTemplateHelper.renderTemplate(key, template, variables);
        log.info(content);

        //发送邮件
        EmailBean bean = new EmailBean();
        bean.setHost("smtp.126.com");
        bean.setProtocol("smtp");
        bean.setAuthFlag(true);
        bean.setUsername(username);
        bean.setPassword(password);
        bean.setFromAccount(fromAccount);
        bean.setToAccout(toAccount);
        bean.setTitle(String.valueOf(Math.abs(new Random().nextLong())));
        bean.setContent(content);
        EmailHelper.sendEmailViaHTMLTemplate(bean);
    }
}
