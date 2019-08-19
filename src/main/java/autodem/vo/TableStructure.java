package autodem.vo;



import java.util.ArrayList;
import java.util.List;

public class TableStructure {

    private String keyName;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getDatabaseKeyName() {
        return databaseKeyName;
    }

    public void setDatabaseKeyName(String databaseKeyName) {
        this.databaseKeyName = databaseKeyName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDatabaseTableName() {
        return databaseTableName;
    }

    public void setDatabaseTableName(String databaseTableName) {
        this.databaseTableName = databaseTableName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    private String databaseKeyName;
    private String tableName;
    private String databaseTableName;
    private List<Column> columns = new ArrayList<Column>();

    public static class Column {

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnType() {
            return columnType;
        }

        public void setColumnType(String columnType) {
            this.columnType = columnType;
        }

        public String getColumnDatabaseName() {
            return columnDatabaseName;
        }

        public void setColumnDatabaseName(String columnDatabaseName) {
            this.columnDatabaseName = columnDatabaseName;
        }

        public String getColumnDatabaseType() {
            return columnDatabaseType;
        }

        public void setColumnDatabaseType(String columnDatabaseType) {
            this.columnDatabaseType = columnDatabaseType;
        }

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
