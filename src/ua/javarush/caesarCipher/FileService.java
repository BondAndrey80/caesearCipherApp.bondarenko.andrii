package ua.javarush.caesarCipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public static String getOutFileName(String inFilePath, String postfix) {
        String outFilePath;
        Path path = Path.of(inFilePath);
        String directory = path.getParent().toString();
        if (directory == null) {//directory завжди не null
            directory = path.getRoot().toString();
        }
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");

        outFilePath = Path.of(directory, fileName.substring(0, dotIndex) + postfix + fileName.substring(dotIndex)).toString();
        return outFilePath;
    }

    public static List<String> getStringsFromFile(String filePath) throws IOException {
        ArrayList<String> stringList = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringList.add(line);
            }
        } catch (IOException e) {//навіщо цей catch якщо він не обробляє помилку а прокидає її далі?
            throw e;
        }
        return stringList;
    }

    public static void writeTextToFile(List<String> text, String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (int i = 0; i < text.size(); i++) {
                writer.write(text.get(i));
                if (i < text.size() - 1) {
                    writer.newLine();
                }
            }
        } catch (IOException e) { //навіщо цей catch якщо він не обробляє помилку а прокидає її далі?
            throw e;
        }
    }
}
