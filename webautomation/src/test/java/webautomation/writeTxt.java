package webautomation;

import java.io.File;
import java.io.FileWriter;

public class writeTxt {
    public static void txt(String textItem, String textPrice, String textURL) throws Exception {
        File path = new File("webautomation\\src\\I-O Files\\output.txt");
        FileWriter fw = new FileWriter(path);
        fw.write("Item: " + textItem + "\n" + "Price: " + textPrice + "\n" + "URL: " + textURL);
        fw.close();
    }
}
