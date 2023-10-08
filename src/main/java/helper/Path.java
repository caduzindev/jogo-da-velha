package helper;

import java.io.File;

public class Path {
    public static String getResourcePath() {
        return System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + "main"
                + File.separator
                + "resources"
                + File.separator;
    }
}
