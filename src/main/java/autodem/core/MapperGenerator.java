package autodem.core;

import autodem.util.AutoUtil;
import autodem.vo.TableStructure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MapperGenerator implements Generator {

    private SqlReader sqlReader;
    private String mapperPath;
    private String namespace;
    private String daoClass;
    private String entityPath;
    private String entityClass;

    public MapperGenerator(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
        this.mapperPath = AutoUtil.searchPath(sqlReader.projectPath, "mapper");
        this.daoClass = AutoUtil.searchPath(sqlReader.projectPath, "dao");
        this.namespace = AutoUtil.getPackageName(daoClass) + "." + sqlReader.table.getTableName() + "Dao";
        this.entityPath = AutoUtil.searchPath(sqlReader.projectPath, "entity");
        this.entityClass = AutoUtil.getPackageName(entityPath) + "." + sqlReader.table.getTableName();

    }

    public boolean generate() {
        if ("".equals(sqlReader.table.getTableName()) || sqlReader.table.getTableName() == null) {
            return false;
        }
        File file = new File(this.mapperPath + "\\" + sqlReader.table.getTableName() + "Mapper.xml");
        if(!file.exists()){
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
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        writer.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n");
        writer.write("<mapper namespace=\"" + this.namespace + "\">\n");
        writer.newLine();
        writer.write("    <resultMap id=\"BaseResultMap\" type=\"" + this.entityClass + "\">\n");
        for (TableStructure.Column column : sqlReader.table.getColumns()) {
            if (sqlReader.table.getKeyName().equals(column.getColumnName())) {
                writer.write("        <" + column.getColumnDatabaseName() +
                        " column=\"" + column.getColumnDatabaseName() + "\" " +
                        "property=\"" + column.getColumnName() +
                        "\" jdbcType=\"" + column.getColumnDatabaseType() + "\"/>\n");
            } else {
                writer.write("        <result column=\"" + column.getColumnDatabaseName() +
                        "\" property=\"" + column.getColumnName() + "\" " +
                        "jdbcType=\"" + column.getColumnDatabaseType() + "\"/>\n");
            }
        }
        writer.write("    </resultMap>\n");
        writer.flush();
        writer.write("\n    <sql id=\"Base_Column_List\">\n" +
                "        *\n" +
                "    </sql>\n");
        writer.write("\n    <insert id=\"insert\" parameterType=\"" + entityClass + "\">\n" +
                "        <selectKey keyProperty=\"" + sqlReader.table.getKeyName() +
                "\" order=\"BEFORE\" resultType=\"java.lang.String\">\n" +
                "            select UUID()\n" +
                "        </selectKey>\n");
        writer.write("\n        insert into " + sqlReader.table.getDatabaseTableName() + " (");
        for (int i = 0; i < sqlReader.table.getColumns().size(); i++) {
            if (i == sqlReader.table.getColumns().size() - 1) {
                writer.write(sqlReader.table.getColumns().get(i).getColumnDatabaseName() + ")\n");
            } else {
                writer.write(sqlReader.table.getColumns().get(i).getColumnDatabaseName() + ", ");
            }
            if (i != 0 && i % 4 == 0 && i + 4 < sqlReader.table.getColumns().size() - 1) {
                writer.write("\n        ");
            }
        }
        writer.flush();
        writer.write("\n        values (");
        for (int i = 0; i < sqlReader.table.getColumns().size(); i++) {
            if (i == sqlReader.table.getColumns().size() - 1) {
                writer.write("#{" + sqlReader.table.getColumns().get(i).getColumnName() + "})\n");
            } else {
                writer.write("#{" + sqlReader.table.getColumns().get(i).getColumnName() + "}, ");
            }
            if (i != 0 && i % 4 == 0 && i + 4 < sqlReader.table.getColumns().size() - 1) {
                writer.write("\n        ");
            }
        }
        writer.write("\n    </insert>\n\n");
        writer.flush();
        writer.write("    <update id=\"update\" parameterType=\"" + entityClass + "\">\n");
        writer.write("        update " + sqlReader.table.getDatabaseTableName() + "\n");
        writer.write("        <set>\n");
        for (int i = 0; i < sqlReader.table.getColumns().size(); i++) {
            if (sqlReader.table.getKeyName().equals(sqlReader.table.getColumns().get(i).getColumnName())) {
                continue;
            }
            writer.write("            <if test=\"" + sqlReader.table.getColumns().get(i).getColumnName() +
                    " != null\">\n" +
                    "                " + sqlReader.table.getColumns().get(i).getColumnDatabaseName() +
                    " = #{" + sqlReader.table.getColumns().get(i).getColumnName() + "},\n" +
                    "            </if>\n");
        }
        writer.write("        </set>\n" +
                "        where " + sqlReader.table.getDatabaseKeyName() +
                " = #{" + sqlReader.table.getDatabaseKeyName() + "}\n");
        writer.write("    </update>\n\n</mapper>\n");
        writer.flush();
        fileWriter.close();

    }
}
