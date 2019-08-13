package autodao.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EntityGenerator implements Generator {

    private SqlReader sqlReader;

    public EntityGenerator(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
    }

    public void generate() {
        File file = new File(sqlReader.path + sqlReader.table.getTableName() + ".java");
        if (!file.exists()) {
            try {
                file.createNewFile();
                write(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void write(File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write("import lombok.Data;");
        writer.newLine();
        writer.write("@Data");
        writer.newLine();
        writer.write("public class " + sqlReader.table.getTableName() + "{");
        writer.newLine();
        for (int i = 0; i < sqlReader.table.length(); i++) {
            writer.write("  private " +
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
