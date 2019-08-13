package autodao.core;

import autodao.vo.Context;
import autodao.vo.TableStructure;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlReader {

    public String path;
    public TableStructure table = new TableStructure();

    public SqlReader(String path) {
        readSql(path);
        this.path = setPath(path);
    }

    private String setPath(String path) {
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
            while ((line = bufferedReader.readLine()) != null) {
                TableStructure.Column column = explainVar(line);
                if (column != null) {
                    table.add(column);
                }
                if (className.equals("")) {
                    className = explainName(line);
                }
            }
            table.setTableName(className);
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
            String[] words = sentence[0].replace("`", "").split("_");
            for (int i = 0; i < words.length; i++) {
                if (i == 0) {
                    name = name + words[i];
                } else {
                    name = name + words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
                }
            }
            if (sentence[1].equals(Context.VARCHAR)) {
                type = "String";
            } else if (sentence[1].equals(Context.TIMESTAMP)) {
                type = "Date";
            } else if (sentence[1].equals(Context.INT)) {
                type = "int";
            }
            return new TableStructure.Column(name, type);
        }
        return null;
    }

    private static String explainName(String line) {
        String name = "";
        String pattern = "TABLE\\s`[a-z_]*`";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);
        while (m.find()) {
            String rawName = m.group().substring(6).replace("`", "");
            String words[] = rawName.split("_");
            for (int i = 0; i < words.length; i++) {
                name = name + words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
            }
            break;
        }
        return name;
    }

}
