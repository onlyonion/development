package com.onion.study.docs;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class MarkdownUtils {

    public static final String DEFAULT_ENCODING       = "UTF-8";
    public static final String DEFAULT_TEMPLATE_PATH  = "/META-INF/templates";
    public static final String DEFAULT_TEMPLATE_NAME  = "img.md.ftl";
    public static final String GENERATE_MARKDOWN_FILE = "99-image.md";
    public static final String IMG_FILE_DIRECTORY     = "img";
    public static final String FREEMARKER_DATA_KEY    = "imgList";
    static Configuration       cfg                    = new Configuration(Configuration.VERSION_2_3_22);
    static {
        cfg.setDefaultEncoding(DEFAULT_ENCODING);
        cfg.setClassForTemplateLoading(MarkdownUtils.class, DEFAULT_TEMPLATE_PATH);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public static String freeMarker(Map<String, Object> data) {
        Writer writer = new StringWriter();
        try {
            Template temp = cfg.getTemplate(DEFAULT_TEMPLATE_NAME);
            temp.process(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return writer.toString();
    }

    public static void getFile(String path, int deep) throws IOException {
        File file = new File(path);
        File[] array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isDirectory()) {
                // 文件夹img下的图像
                if (IMG_FILE_DIRECTORY.equals(array[i].getName())) {
                    String[] list = array[i].list();
                    Map<String, Object> map = new HashMap<>();
                    map.put(FREEMARKER_DATA_KEY, list);
                    String content = freeMarker(map);
                    File markdown = new File(array[i].getParent(), GENERATE_MARKDOWN_FILE);
                    FileUtils.writeStringToFile(markdown, content, DEFAULT_ENCODING);
                    System.out.println("write file " + markdown.getPath());
                }
                // 文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(), deep + 1);
            }
        }
    }

    public static void generate(String path) {
        path = path == null ? "./" : path;
        int deep = 0;
        try {
            getFile(path, deep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
