package autodao;

import autodao.core.EntityGenerator;
import autodao.core.Generator;
import autodao.core.SqlReader;

public class Entrance {
    public static void main(String[] args) {
        String path = "C:\\Users\\Administrator\\Desktop\\mapping_item.sql";
        SqlReader sr = new SqlReader(path);
        System.out.println(sr.table);
        EntityGenerator g = new EntityGenerator(sr);
        g.generate();
    }
}
