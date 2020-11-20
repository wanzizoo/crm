package cn.wanzizoo.crm.test;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @program: crm
 * @author: LiuFan
 * @create: 2020/10/19 4:04 下午
 * @description: hello freemarker
 **/
public class FreemarkerTest {

    @Test
    public void freemarkerTest() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("templates"));
        //cfg.setObjectWrapper(new DefaultObjectWrapper());
        Map root = new HashMap();
        root.put("user","WanZi");
        Template temp = cfg.getTemplate("test.ftl");
        Writer out = new OutputStreamWriter(new FileOutputStream("test.html"));
        temp.process(root,out);
        out.flush();
    }

}
