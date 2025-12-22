package Model;

import java.io.*;
import java.util.ArrayList;

public class CSVUtil {

    public static ArrayList<String> read(String file) {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        } catch (Exception e) { }
        return lines;
    }

    public static void write(String file, ArrayList<String> lines) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) { }
    }
}

