package autodao.core;

import autodao.vo.TableStructure;
import lombok.Data;

@Data
public class SqlReader {
    private TableStructure table;

    public void readSql() {
        for (TableStructure.Column column : table.getColumns()) {

        }
    }
}
