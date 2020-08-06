package com.xiaxinyu.email.core;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author XIAXINYU3
 * @date 2020.8.6
 */
@Service
public class FreeMarkerTemplateHelper {

    private static final String ENCODE_UTF8 = "utf-8";

    private Configuration config;
    private StringTemplateLoader stringTemplateLoader;

    public FreeMarkerTemplateHelper() {
        config = new Configuration(Configuration.VERSION_2_3_28);
        stringTemplateLoader = new StringTemplateLoader();
        config.setTemplateLoader(stringTemplateLoader);
    }

    /**
     * 添加模板
     *
     * @param key      模板主键
     * @param template 模板文本
     * @return freemarker 模板对象
     */
    public Template addTemplate(String key, String template) {
        stringTemplateLoader.putTemplate(key, template, System.currentTimeMillis());
        config.clearTemplateCache();
        try {
            return config.getTemplate(key, ENCODE_UTF8);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取模板
     *
     * @param key 模板主键
     * @return freemarker 模板对象
     */
    public Template getTemplate(String key) {
        try {
            return config.getTemplate(key, ENCODE_UTF8);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 渲染模板
     *
     * @param key       模板key
     * @param template  模板对象
     * @param variables 参数
     * @return 渲染好模板文本
     * @throws IOException
     * @throws TemplateException
     */
    public String renderTemplate(String key, final Template template, final Map<String, Object> variables)
            throws IOException, TemplateException {
        Template ft = getTemplate(key);
        return FreeMarkerTemplateUtils.processTemplateIntoString(ft, variables);
    }
}

