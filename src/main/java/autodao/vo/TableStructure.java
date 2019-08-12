package autodao.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableStructure {

    private List<Column> columns = new ArrayList<Column>();

    @Data
    public static class Column{
        private String columnName;
        private String columnType;
    }

//    public

}
