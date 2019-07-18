package com.onion.test.java.nio.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import lombok.Data;

public class PathsTest {
    @Test
    public void test() {
        Path path = Paths.get("D:\\temp\\");
        System.out.println(JSON.toJSONString(path));
    }

    @Test
    public void fileVisit() throws IOException {
        copy("f:", "\\BaiduNetdiskDownload\\bilibili\\56729636");

    }

    public static void copy(String path, String more) throws IOException {
        Bili bili1 = new Bili();
        Map<String, String> names = new HashMap<>();
        Map<String, File> files = new HashMap<>();

        Files.walkFileTree(Paths.get(path, more), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.endsWith("entry.json")) {
                    List<String> list = Files.readAllLines(file);
                    Bili bili = JSON.parseObject(list.get(0), Bili.class);
                    bili1.setTitle(bili.getTitle());
                    names.put(bili.getPage_data().getPage().toString(), bili.getPage_data().getPart());
                } else if (file.endsWith("0.blv")) {
                    String key = file.getParent().getParent().getFileName().toString();
                    files.put(key, file.toFile());
                    File file1 = new File(path + more + bili1.getTitle() + "\\", names.get(key) + ".mp4");
                    FileUtils.copyFile(file.toFile(), file1);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Data
    private static class Bili {
        private String title;
        private PageData page_data;
        private Map<String, String> names;
        private Map<String, File> files;
    }

    @Data
    private static class PageData {
        private String part;
        private Integer page;
    }

}
