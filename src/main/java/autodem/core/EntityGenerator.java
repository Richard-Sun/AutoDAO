package autodem.core;

import autodem.util.AutoUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EntityGenerator implements Generator {

    private SqlReader sqlReader;
    private String packageName;
    private String entityPath;

    public EntityGenerator(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
        this.entityPath = AutoUtil.searchPath(sqlReader.projectPath, "entity");
        this.packageName = AutoUtil.getPackageName(entityPath);
    }

    public boolean generate() {
        if ("".equals(sqlReader.table.getTableName()) || sqlReader.table.getTableName() == null) {
            return false;
        }
        File file = new File(entityPath + "\\" + sqlReader.table.getTableName() + ".java");
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
        writer.write("package " + packageName + ";\n");
        writer.newLine();
        writer.write("import lombok.Data;\n");
        writer.newLine();
        writer.write("import java.util.Date;\n");
        writer.newLine();
        writer.write("@Data");
        writer.newLine();
        writer.write("public class " + sqlReader.table.getTableName() + " {");
        writer.newLine();
        writer.newLine();
        for (int i = 0; i < sqlReader.table.length(); i++) {
            writer.write("    private " +
                    sqlReader.table.getColumns().get(i).getColumnType() + " " +
                    sqlReader.table.getColumns().get(i).getColumnName() + ";"
            );
            writer.newLine();
        }
        writer.newLine();
        writer.write("}");
        writer.flush();
        fileWriter.close();
    }


}
