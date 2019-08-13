package autodao.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableStructure {

    private String tableName;
    private List<Column> columns = new ArrayList<Column>();

    @Data
    public static class Column {

        private String columnName;
        private String columnType;

        public Column(String columnName, String columnType) {
            this.columnName = columnName;
            this.columnType = columnType;
        }
    }

    public void add(Column column) {
        this.columns.add(column);
    }

    public int length(){
        return this.columns.size();
    }

}
