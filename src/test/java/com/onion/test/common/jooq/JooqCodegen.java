package com.onion.test.common.jooq;

import org.jooq.codegen.GenerationTool;

/**
 * @author lijicong
 * @since 2019-11-15 17:42
 */
public class JooqCodegen {

    public static void main(String[] args) throws Exception {
        String[] files = new String[]{"C:\\workspace\\my-gitee\\development\\src\\test\\java\\com\\onion\\test\\common\\jooq\\library.xml"};
        GenerationTool.main(files);
    }
}
