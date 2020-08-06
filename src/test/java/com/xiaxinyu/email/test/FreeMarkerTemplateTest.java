package com.xiaxinyu.email.test;

import com.xiaxinyu.email.EmailApplication;
import com.xiaxinyu.email.core.FreeMarkerTemplateHelper;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmailApplication.class})
public class FreeMarkerTemplateTest {

    @Autowired
    FreeMarkerTemplateHelper freeMarkerTemplateHelper;

    private String template = "<h1>Hello,${name}</h1>";

    @Test
    public void test_template_render() throws IOException, TemplateException {
        String key = "h1";

        //添加模板
        freeMarkerTemplateHelper.addTemplate(key, template);
        Template template = freeMarkerTemplateHelper.getTemplate(key);
        Assert.assertNotNull(template);

        //渲染模板
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "FreeMarker");
        String content = freeMarkerTemplateHelper.renderTemplate(key, template, variables);

        log.info(content);
    }

    @Test
    public void test_template_render_from_file() throws IOException, TemplateException {
        //读取模板文件内容
        String file = "test_template_render_from_file.html";
        String templateContent =  FileReader.loadEmailTemplate(file);

        String key = "h1";

        //添加模板
        freeMarkerTemplateHelper.addTemplate(key, templateContent);
        Template template = freeMarkerTemplateHelper.getTemplate(key);
        Assert.assertNotNull(template);

        //渲染模板
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "FreeMarker");
        String content = freeMarkerTemplateHelper.renderTemplate(key, template, variables);

        log.info(content);
    }
}
