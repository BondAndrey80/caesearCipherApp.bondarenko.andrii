package ua.javarush.caesarCipher.runner;

import ua.javarush.caesarCipher.IO.FileService;
import ua.javarush.caesarCipher.cipher.BruteForceConfiguration;
import ua.javarush.caesarCipher.cipher.CaesarCipher;
import ua.javarush.caesarCipher.constants.Commands;

import java.io.IOException;
import java.util.ArrayList;

public class Runner {
    String[] args;
    RunnerConfiguration config = new RunnerConfiguration();

    public Runner(String[] args){
        this.args = args;
    }

    private void runBruteForce(){
        ArrayList<String> encryptedStrings;
        try{
            encryptedStrings = FileService.getStringsFromFile(this.config.getFilePath());
        }catch (IOException e){
            System.out.println("Помилка при чітанні з файла - " + this.config.getFilePath());
            e.printStackTrace();
            return;
        }
        ArrayList<String> keywords;
        try{
            keywords = FileService.getStringsFromFile(this.config.getDictionaryFilePath());
        }catch (IOException e){
            System.out.println("Помилка при чітанні з файла - " + this.config.getDictionaryFilePath());
            e.printStackTrace();
            return;
        }
        BruteForceConfiguration bruteForceConfig = new BruteForceConfiguration();
        bruteForceConfig.loadDictionary(keywords);
        CaesarCipher caesarCipher = new CaesarCipher(config.getKey());

        ArrayList<String> decryptedStrings = caesarCipher.bruteForce(encryptedStrings, bruteForceConfig);
        if(decryptedStrings.size() == 0){
            System.out.println("Не вдалося розшифрувати файл " + this.config.getFilePath());
        }else {
            String outPathFile = FileService.getOutFileName(this.config.getFilePath(), "_" + String.valueOf(caesarCipher.getUserKey()));
            try {
                FileService.writeTextToFile(decryptedStrings, outPathFile);
            }catch (IOException e){
                System.out.println("Не вдалося записати розшифрований файл " + outPathFile);
                e.printStackTrace();
            }
        }
    }

    private void runEncryptDecrypt(){
        ArrayList<String> inStringList;
        try {
            inStringList = FileService.getStringsFromFile(this.config.getFilePath());
        }catch (IOException e){
            System.out.println("Помилка при чітанні з файла - " + this.config.getFilePath());
            e.printStackTrace();
            return;
        }

        ArrayList<String> outStringList = new ArrayList<>();
        CaesarCipher caesarCipher = new CaesarCipher(config.getKey());
        boolean encrypting = config.getCommand() == Commands.ENCRYPT;
        for (String line : inStringList){
            if(encrypting) {
                outStringList.add(caesarCipher.encrypt(line));
            }else {
                outStringList.add(caesarCipher.decrypt(line));
            }
        }
        String outFilePath = FileService.getOutFileName(this.config.getFilePath(), encrypting ? "[ENCRYPTED]" : "[DECRYPTED]");
        try {
            FileService.writeTextToFile(outStringList, outFilePath);
        }catch (IOException e){
            System.out.println("Помилка при спробі збереження файла - " + outFilePath);
            e.printStackTrace();
        }
    }

    public void run(){
        if (args.length == 0){
            this.config = CLI.mainMenu();
            if(this.config.getCommand() == Commands.UNKNOWN){
                return;
            }
        }else {
            if(args.length < 3){
                System.out.println("Потрібно ввести 3 параметри! Наприклад:\n" +
                        "java -jar \"c:/My Project/target/my App.jar\" ENCRYPT \"folder name/textFile1.txt\" 20");
                return;
            }
            String command = args[0];
            if (command.equalsIgnoreCase("ENCRYPT")){
                this.config.setCommand(Commands.ENCRYPT);
            } else if (command.equalsIgnoreCase("DECRYPT")) {
                this.config.setCommand(Commands.DECRYPT);
                this.config.setDictionaryFilePath(args[2]);
            } else if (command.equalsIgnoreCase("BRUTE_FORCE")) {
                this.config.setCommand(Commands.BRUTE_FORCE);
                this.config.setDictionaryFilePath(args[2]);
            } else {
                System.out.println("Невірний перший параметр - " + args[0]);
                return;
            }

            this.config.setFilePath(args[1]);
            if (this.config.getCommand() != Commands.BRUTE_FORCE){
                try{
                    this.config.setKey(Integer.parseInt(args[2]));
                }catch (NumberFormatException e){
                    System.out.println("Невірний третій параметр - " + args[2]);
                    return;
                }
            }

        }

        if(this.config.getCommand() != Commands.BRUTE_FORCE){
            runEncryptDecrypt();
        }else {
            runBruteForce();
        }
        System.out.println("Обробку файла завершено");
    }
}

