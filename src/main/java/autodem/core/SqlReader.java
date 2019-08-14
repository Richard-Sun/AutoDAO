package autodem.core;

import autodem.util.AutoUtil;
import autodem.vo.Context;
import autodem.vo.TableStructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlReader {

    public String projectPath;
    public TableStructure table = new TableStructure();

    public SqlReader(String sqlPath) {
        readSql(sqlPath);
        this.projectPath = setProjectPath(sqlPath); //结尾不带\
    }

    private String setProjectPath(String path) {
        String filePath = "";
        String[] paths = path.split("\\\\");
        for (int i = 0; i < paths.length; i++) {
            if (i != paths.length - 1) {
                filePath = filePath + paths[i] + "\\";
            }
        }
        return filePath;
    }

    private void readSql(String path) {
        File file = new File(path);
        try {
            String line = null;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String className = "";
            String rawTableName = "";
            while ((line = bufferedReader.readLine()) != null) {
                TableStructure.Column column = explainVar(line);
                if (column != null) {
                    table.add(column);
                }
                if ("".equals(className)) {             //类名、表名大写
                    className = explainClassName(line);
                    rawTableName = explainDatabaseTableName(line);
                }
                if (table.getKeyName() == null || "".equals(table.getKeyName())) {
                    table.setKeyName(explainKeyName(line));
                    table.setDatabaseKeyName(explainDatabaseKeyName(line));
                }

            }
            table.setTableName(className);
            table.setDatabaseTableName(rawTableName);
            fileReader.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static TableStructure.Column explainVar(String line) {
        String pattern = "`[a-z_]*`\\s(" + Context.VARCHAR + "|" + Context.TIMESTAMP + "|" + Context.INT + "|" + Context.TINYINT + ")";
        String name = "";
        String type = "";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while (m.find()) {
            String[] sentence = m.group().split(" ");
            String rawName = sentence[0].replace("`", "");
            name = AutoUtil.adjustParamName(rawName);
            if (sentence[1].equals(Context.VARCHAR)) {
                type = "String";
            } else if (sentence[1].equals(Context.TIMESTAMP)) {
                type = "Date";
            } else if (sentence[1].equals(Context.INT)) {
                type = "Integer";
            } else if (sentence[1].equals(Context.TINYINT)) {
                type = "Integer";
            } else if (sentence[1].equals(Context.TEXT)) {
                type = "String";
            } else if (sentence[1].equals(Context.LONGTEXT)) {
                type = "String";
            } else if (sentence[1].equals(Context.SMALLINT)) {
                type = "Boolean";
            }
            return new TableStructure.Column(name, rawName, type, sentence[1].toUpperCase());
        }
        return null;
    }

    private static String explainDatabaseTableName(String line) {
        String name = "";
        String pattern = "TABLE\\s`[a-z_]*`";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while (m.find()) {
            String rawName = m.group().substring(6).replace("`", "");
            name = rawName;
            break;
        }
        return name;
    }

    private static String explainClassName(String line) {
        String name = "";
        String pattern = "TABLE\\s`[a-z_]*`";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while (m.find()) {
            String rawName = m.group().substring(6).replace("`", "");
            name = AutoUtil.adjustParamName(rawName);
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            break;
        }
        return name;
    }

    private static String explainKeyName(String line) {
        String key = "";
        String pattern = "PRIMARY\\sKEY\\s\\(`[a-z_]*`\\)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while (m.find()) {
            String rawKey = m.group().substring(12).replace("`", "")
                    .replace("(", "")
                    .replace(")", "");
            key = AutoUtil.adjustParamName(rawKey);
            break;
        }
        return key;
    }

    private String explainDatabaseKeyName(String line) {
        String key = "";
        String pattern = "PRIMARY\\sKEY\\s\\(`[a-z_]*`\\)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while (m.find()) {
            String rawKey = m.group().substring(12).replace("`", "")
                    .replace("(", "")
                    .replace(")", "");
            key = rawKey;
            break;
        }
        return key;
    }
}
