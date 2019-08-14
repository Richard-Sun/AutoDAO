package autodem.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableStructure {

    private String keyName;
    private String databaseKeyName;
    private String tableName;
    private String databaseTableName;
    private List<Column> columns = new ArrayList<Column>();

    @Data
    public static class Column {

        private String columnName;
        private String columnType;
        private String columnDatabaseName;
        private String columnDatabaseType;

        public Column(String columnName, String columnDatabaseName, String columnType, String columnDatabaseType) {
            this.columnDatabaseName = columnDatabaseName;
            this.columnName = columnName;
            this.columnType = columnType;
            this.columnDatabaseType = columnDatabaseType;
        }
    }

    public void add(Column column) {
        this.columns.add(column);
    }

    public int length() {
        return this.columns.size();
    }

}
