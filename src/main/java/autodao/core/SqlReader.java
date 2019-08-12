package autodao.core;

import autodao.vo.TableStructure;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Data
public class SqlReader {
    private TableStructure table;

    public void readSql(String path) {
        File file = new File(path);
        try {
            String line = null;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        for (TableStructure.Column column : table.getColumns()) {

        }

    }
}
