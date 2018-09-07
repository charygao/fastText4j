package fasttext.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by zqq on 18-9-7.
 */
public class FileUtil {

    public static void readFile(String filePath, FileProcess process){
        FileReader fr = null;
        BufferedReader br = null;
        String line;
        try {
            fr =new FileReader(filePath);
            br=new BufferedReader(fr);
            while ((line=br.readLine())!=null) {
                process.processLine(line);
            }
        }catch (IOException e){
            System.err.println(e);
        }finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (br != null){
                    br.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }


    public interface FileProcess{
        void processLine(String line);
    }
}
