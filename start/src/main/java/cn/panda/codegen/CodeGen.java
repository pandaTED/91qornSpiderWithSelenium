package cn.panda.codegen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class CodeGen {

    public static void main(String[] args) {


        FastAutoGenerator.create("jdbc:mysql://localhost:3306/porn?serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("zyp") // 设置作者
                            .outputDir("C:\\Users\\panda\\project\\spider02\\start\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com") // 设置父包名
                            .moduleName("example") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\panda\\project\\spider02\\start\\src\\main\\resources\\mappers")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("spider_history") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok().idType(IdType.AUTO);
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }


}