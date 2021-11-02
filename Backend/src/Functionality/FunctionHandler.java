package Functionality;

import java.io.*;
import java.util.ArrayList;

/**
 * @author C. Maas
 * @version 1.0
 */
public class FunctionHandler {

    /**
     * @param path Path to the corresponding file
     * @return ArrayList with the data from the file
     * @throws IOException IO error
     */
    public ArrayList<String> readFile(String path) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        File file = new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String line;
        while((line = br.readLine()) != null) {
            result.add(line);
        }
        reader.close();
        return result;
    }

}
