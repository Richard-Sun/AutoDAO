package autodem.util;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AutoUtil {

    public static String adjustParamName(String rawName){
        String name = "";
        String words[] = rawName.split("_");
        for (int i = 0; i < words.length; i++) {
            name = name + words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        name = name.substring(0,1).toLowerCase() + name.substring(1);
        return name;
    }

    public static String searchPath(String path, String type) {
        File filePoint = null;
        File file = new File(path + "\\src\\main");
        BlockingQueue<File> files = new ArrayBlockingQueue<File>(100);
        files.add(file);
        while (!files.isEmpty()) {
            filePoint = files.poll();
            File[] fileArray = filePoint.listFiles();
            for (File f : fileArray) {
                if (f.isDirectory()) {
                    if (!type.equals(f.getName())) {
                        files.add(f);
                    } else {
                        return f.getAbsolutePath();
                    }
                }
            }
        }
        return "";
    }


    public static String getPackageName(String classPath) {
        String packageName = "";
        String[] words = classPath.split("\\\\");
        int point = 2147483647;
        for (int i = 0; i < words.length; i++) {
            if ("java".equals(words[i])) {
                point = i;
            }
            if (i > point) {
                packageName = packageName + words[i] + ".";
            }
        }
        return packageName.substring(0, packageName.length() - 1);
    }
}
