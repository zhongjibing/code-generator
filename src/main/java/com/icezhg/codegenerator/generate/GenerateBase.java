package com.icezhg.codegenerator.generate;

import com.icezhg.codegenerator.component.FieldBean;
import com.icezhg.codegenerator.component.GenerateException;
import com.icezhg.codegenerator.utils.NameUtil;
import com.icezhg.codegenerator.utils.PathUtil;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.lang.model.element.Modifier;

/**
 * 生成的基类
 */
public class GenerateBase {
    private static final String BIZ_PACKAGE_NAME = ".biz";
    protected TypeSpec.Builder classBuilder;
    protected ClassName beanClassName; //依赖一个生成的类
    protected String table;//表名
    protected String srcPackage;//生成文件的包名
    protected String basePackage;//基础的包名
    protected String type;//生成文件类型 Bean Mapper Controller等
    protected List<FieldBean> fields;//表的字段

    /**
     * 生成实体类，所以没有实体类类名
     */
    public GenerateBase(String table, List<FieldBean> fields, String basePackage, TypeSpec.Builder classBuilder, String type) {
        this.table = table;
        this.basePackage = basePackage;
        this.srcPackage = basePackage + BIZ_PACKAGE_NAME + "." + table;
        this.fields = fields;
        this.classBuilder = classBuilder;
        this.type = type;
    }

    /**
     * 生成初开实体类其他的类，都需要用到实体类的类名
     */
    public GenerateBase(ClassName beanClassName, String table, List<FieldBean> fields, String basePackage, TypeSpec.Builder classBuilder, String type) {
        this.beanClassName = beanClassName;
        this.table = table;
        this.basePackage = basePackage;
        this.srcPackage = basePackage + ".biz." + table;
        this.fields = fields;
        this.classBuilder = classBuilder;
        this.type = type;
    }

    //输出文件，并且返回完整的生成的类
    public ClassName out() {
        //判断文件夹不存在，就先生成项目文件夹
        File packageDir = new File(PathUtil.SRC_ROOT_DIR, PathUtil.package2Path(srcPackage));
        if (!packageDir.exists()) {
            packageDir.mkdirs();
        }
        JavaFile javaFile = JavaFile.builder(srcPackage, classBuilder.build()).build();
        try {
            javaFile.writeTo(PathUtil.SRC_ROOT_DIR);
            String fullClass = javaFile.toJavaFileObject().getName()
                    .replace('/', '.')
                    .replace('\\', '.');
            if (fullClass.endsWith(".java")) {
                fullClass = fullClass.substring(0, fullClass.length() - 5);
            }
            System.out.println("[" + table + "]" + type + "生成成功:" + fullClass);
            return ClassName.bestGuess(fullClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenerateException("[" + table + "]" + type + "生成失败:" + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Builder builder = TypeSpec.classBuilder(NameUtil.className("table") + "Controller").addModifiers(Modifier.PUBLIC)
                .addAnnotation(AnnotationSpec.builder(ClassName.bestGuess("org.springframework.web.bind.annotation.RestController")).build())
                .addAnnotation(AnnotationSpec.builder(ClassName.bestGuess("org.springframework.web.bind.annotation.RequestMapping"))
                        .addMember("value", "\"/$L\"", "table").build());

        JavaFile javaFile = JavaFile.builder("com.icezhg.controller", builder.build()).build();
        javaFile.writeTo(new File("src/main/java2/"));
    }
}
