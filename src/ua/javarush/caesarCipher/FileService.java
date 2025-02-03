package ua.javarush.caesarCipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileService {

    public static String getOutFileName(String inFilePath, String postfix){
        String outFilePath;
        String directory = Path.of(inFilePath).getParent().toString();
        if(directory == null){
            directory = Path.of(inFilePath).getRoot().toString();
        }
        String fileName = Path.of(inFilePath).getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");

        outFilePath = Path.of(directory, fileName.substring(0, dotIndex) + postfix + fileName.substring(dotIndex)).toString();
        return outFilePath;
    }

    public static ArrayList<String> getStringsFromFile(String filePath) throws IOException{
        ArrayList<String> stringList = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                stringList.add(line);
            }
        }catch (IOException e){
            throw e;
        }
        return stringList;
    }

    public static void writeTextToFile(ArrayList<String> text, String filePath) throws IOException{
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))){
            for (int i = 0; i < text.size(); i++) {
                writer.write(text.get(i));
                if (i < text.size() - 1){
                    writer.newLine();
                }
            }
        }catch (IOException e){
            throw e;
        }
    }
}
