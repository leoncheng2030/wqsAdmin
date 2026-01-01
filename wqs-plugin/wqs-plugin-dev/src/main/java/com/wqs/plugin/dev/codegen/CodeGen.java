package com.wqs.plugin.dev.codegen;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class CodeGen {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("usage: CodeGen <module> <entity> <package>");
            return;
        }
        String module = args[0];
        String entity = args[1];
        String basePkg = args[2];
        String entityPkg = basePkg + ".entity";
        String mapperPkg = basePkg + ".mapper";
        String controllerPkg = basePkg + ".controller";
        String root = new File("").getAbsolutePath();
        String modulePath = root + File.separator + module + File.separator + "src" + File.separator + "main" + File.separator + "java";
        writeFile(modulePath, entityPkg, entity + ".java", entityTemplate(entityPkg, entity));
        writeFile(modulePath, mapperPkg, entity + "Mapper.java", mapperTemplate(mapperPkg, entity, entityPkg));
        writeFile(modulePath, controllerPkg, entity + "Controller.java", controllerTemplate(controllerPkg, entity));
        System.out.println("ok");
    }

    static void writeFile(String modulePath, String pkg, String name, String content) throws Exception {
        String dir = modulePath + File.separator + pkg.replace('.', File.separatorChar);
        new File(dir).mkdirs();
        try (FileOutputStream fos = new FileOutputStream(new File(dir, name))) {
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        }
    }

    static String entityTemplate(String pkg, String name) {
        return "package " + pkg + ";\n" +
                "import com.baomidou.mybatisplus.annotation.TableId;\n" +
                "import com.baomidou.mybatisplus.annotation.IdType;\n" +
                "import com.baomidou.mybatisplus.annotation.TableName;\n" +
                "import io.swagger.v3.oas.annotations.media.Schema;\n" +
                "import lombok.Data;\n" +
                "@Data\n" +
                "@TableName(\"" + toTable(name) + "\")\n" +
                "public class " + name + " {\n" +
                "  @TableId(type = IdType.AUTO)\n" +
                "  @Schema(description = \"id\")\n" +
                "  private Long id;\n" +
                "}\n";
    }

    static String mapperTemplate(String pkg, String name, String entityPkg) {
        return "package " + pkg + ";\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "import " + entityPkg + "." + name + ";\n" +
                "public interface " + name + "Mapper extends BaseMapper<" + name + "> {}\n";
    }

    static String controllerTemplate(String pkg, String name) {
        return "package " + pkg + ";\n" +
                "import org.noear.solon.annotation.Controller;\n" +
                "import org.noear.solon.annotation.Mapping;\n" +
                "import org.noear.solon.annotation.Inject;\n" +
                "import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;\n" +
                "import com.baomidou.mybatisplus.extension.service.IService;\n" +
                "import java.util.List;\n" +
                "@Controller\n" +
                "@Mapping(\"/" + toPath(name) + "\")\n" +
                "public class " + name + "Controller {\n" +
                "  @Inject\n" +
                "  IService<" + name + "> service;\n" +
                "  @Mapping(\"list\")\n" +
                "  public List<" + name + "> list() { return service.list(new QueryWrapper<>()); }\n" +
                "}\n";
    }

    static String toTable(String name) {
        String s = name.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        return s;
    }

    static String toPath(String name) {
        return toTable(name);
    }
}

