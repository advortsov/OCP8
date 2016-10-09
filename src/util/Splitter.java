package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aldvc
 * @date 08.10.2016.
 */
public class Splitter {
    public static List<String> readFile() throws IOException {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String codeLine;
            List<String> emptyStrings = new ArrayList<>();
            System.out.println("Please, input sources from Entuware: ");
            while ((codeLine = reader.readLine()) != null) { // -1 for stream. for reader/writer - null
                if (codeLine.equals('\n') || codeLine.equals("")) {
                    emptyStrings.add(codeLine);
                } else {
                    emptyStrings.clear();
                }
                if (emptyStrings.size() > 3) break;
                data.add(codeLine.trim());
            }
        }
        return data;
    }

    public static void writeFile(List<String> data, File destination) throws
            IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(destination))) {
            for (String s : data) {
                writer.write(s);
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        File source = new File(".java");
        List<String> data = readFile();
        String className = getClassNameForCreating(data);
        File destination = new File(className + ".java");

        for (String record : data) {
            System.out.println(record);
        }
        writeFile(data, destination);
    }

    private static String getClassNameForCreating(List<String> data) throws IOException {
        if (data == null || data.isEmpty()){
            throw new IOException("Empty data!");
        }
        String classSignature = data.get(0);
        if (classSignature.contains("class")){
            String rightPart = classSignature.split("class ")[1];
            return rightPart.substring(0, rightPart.indexOf('{')).trim();
        }

        return "NotFoundName";
    }

}
