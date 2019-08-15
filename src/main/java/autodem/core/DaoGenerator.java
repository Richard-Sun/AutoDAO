package autodem.core;

import autodem.util.AutoUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DaoGenerator implements Generator {

    private SqlReader sqlReader;
    private String packageName;
    private String entityPath;
    private String entityClass;
    private String daoPath;

    public DaoGenerator(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
        this.daoPath = AutoUtil.searchPath(sqlReader.projectPath, "dao");
        this.packageName = AutoUtil.getPackageName(daoPath);
        this.entityPath = AutoUtil.searchPath(sqlReader.projectPath, "entity");
        this.entityClass = AutoUtil.getPackageName(entityPath) + "." + sqlReader.table.getTableName();
    }

    public boolean generate() {
        if ("".equals(sqlReader.table.getTableName()) || sqlReader.table.getTableName() == null) {
            return false;
        }
        File file = new File(daoPath + "\\" + sqlReader.table.getTableName() + "Dao.java");
        if (!file.exists()) {
            try {
                file.createNewFile();
                write(file);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private void write(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write("package " + packageName + ";\n\n");
        writer.write("import " + entityClass + ";\n\n");
        writer.write("import java.util.Date;\n");
        writer.write("import org.apache.ibatis.annotations.Param;\n" +
                "import org.springframework.stereotype.Repository;\n");
        writer.newLine();
        writer.write("@Repository\n");
        writer.write("public interface " + sqlReader.table.getTableName() + "Dao {\n\n");
        writer.write("    int insert(" + sqlReader.table.getTableName() + " record);\n\n");
        writer.write("    int update(" + sqlReader.table.getTableName() + " record);\n\n");
        writer.write("}\n\n");
        writer.flush();
        fileWriter.close();

    }

}
