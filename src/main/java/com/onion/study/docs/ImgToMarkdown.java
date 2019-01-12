package com.onion.study.docs;

import java.io.UnsupportedEncodingException;

public class ImgToMarkdown {
    /**
     * 将img文件夹下的图像在父路径生成 99-img.md 文件，方便阅读
     * @param args
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String path = "./";
        MarkdownUtils.generate(path, false);
    }
}
