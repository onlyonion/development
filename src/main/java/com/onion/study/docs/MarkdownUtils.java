package com.onion.study.docs;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class MarkdownUtils {

    private static final Logger logger = LoggerFactory.getLogger(MarkdownUtils.class);

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String DEFAULT_TEMPLATE_PATH = "/META-INF/templates";
    public static final String DEFAULT_TEMPLATE_NAME = "img.md.ftl";
    public static final String GENERATE_MARKDOWN_FILE = "99-image.md";
    public static final String IMG_FILE_DIRECTORY = "img";
    public static final String FREEMARKER_DATA_KEY = "imgList";
    private static volatile boolean isCopy = false;
    private static File defaultFile;
    static Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

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
        if (array == null) {
            logger.info("未发现文件");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            File subFile = array[i];
            if (subFile.isDirectory()) {
                // 文件夹img下的图像
                if (IMG_FILE_DIRECTORY.equals(subFile.getName())) {
                    if (isCopy) {
                        File[] imgFiles = subFile.listFiles();
                        for (File img : imgFiles) {
                            File destDir = getDestDir();
                            FileUtils.copyFileToDirectory(img, destDir);
                            logger.info("copy file {} to {}", img.getName(), destDir.getName());
                        }
                    } else {
                        String[] list = subFile.list();
                        Map<String, Object> map = new HashMap<>();
                        map.put(FREEMARKER_DATA_KEY, list);
                        String content = freeMarker(map);
                        File markdown = new File(subFile.getParent(), GENERATE_MARKDOWN_FILE);
                        FileUtils.writeStringToFile(markdown, content, DEFAULT_ENCODING);
                        logger.info("write file {}", markdown.getPath());
                    }
                }
                // 文件夹需要调用递归 ，深度+1
                getFile(array[i].getPath(), deep + 1);
            }
        }
    }

    private static File getDestDir() {
        if (defaultFile == null) {
            defaultFile = new File("./temp");
        }
        return defaultFile;
    }

    public static void generate(String path) {
        generate(path, false);
    }
    
    public static void generate(String path, boolean copy) {
        path = path == null ? "./" : path;
        int deep = 0;
        try {
            if (copy) {
                isCopy = true;
            }
            getFile(path, deep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
