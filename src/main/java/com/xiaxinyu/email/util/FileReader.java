package com.xiaxinyu.email.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author XIAXINYU3
 * @date 2020.8.6
 */
@Slf4j
public class FileReader {
    /**
     * 获取邮件模板内容
     *
     * @param name 模板名称
     * @return 邮件模板内容
     */
    public static String loadEmailTemplate(String name) {
        InputStream in = loadTemplate("email", name);
        return loadFile(name, in);
    }

    /**
     * 根据模板名称加载模板
     *
     * @param folder 文件夹
     * @param name   文件名称
     * @return 模板文件流
     */
    public static InputStream loadTemplate(String folder, String name) {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(folder + File.separator + name);
        return in;
    }

    /**
     * 获取文件内容
     *
     * @param name        名称
     * @param inputStream 输入流
     * @return 文件内容
     */
    public static String loadFile(String name, InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        BufferedReader buf = null;
        String line = null;
        try {
            buf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            while ((line = buf.readLine()) != null) {
                line = line.trim();
                builder.append(line);
            }
        } catch (Exception e) {
            log.error("加载文件出错.", name, e);
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (Exception e) {
                    log.error("关闭文件流出错. 文件名称={}", name, e);
                }
            }
        }
        return builder.toString();
    }
}
