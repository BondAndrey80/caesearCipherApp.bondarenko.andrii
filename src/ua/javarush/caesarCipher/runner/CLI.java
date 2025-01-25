package ua.javarush.caesarCipher.runner;

import ua.javarush.caesarCipher.constants.Commands;
import java.util.Scanner;

public class CLI {
    final static String CLI_COMMANDS = "Оберіть одну з наступних команд і натисніть <Enter>\n" +
            "[e]ncrypt | [d]ecrypt | [b]rut force | [q]uit";
    final static String FILE_PATH_MESSAGE = "Введіть шлях к текстовому файлу: ";
    final static String FILE_PATH_DICTIONARY_MESSAGE = "Введіть шлях к текстовому файлу з ключовими словами: ";
    final static String KEY_MESSAGE = "Введіть ключ шифрування (ціле число): ";

    private static Commands getCommand(){
        System.out.println("Ви можете зашифрувати, розшифрувати або взломати шифр текстового файлу\n" + CLI_COMMANDS);
        System.out.print("Введіть команду: ");
        Scanner scanner = new Scanner(System.in);
        Commands command;
        while (true){
            String value = scanner.nextLine();
            if(value.equalsIgnoreCase("q")){
                command = Commands.UNKNOWN;
                break;
            } else if (value.equalsIgnoreCase("e")) {
                command = Commands.ENCRYPT;
                break;
            } else if (value.equalsIgnoreCase("d")) {
                command = Commands.DECRYPT;
                break;
            } else if (value.equalsIgnoreCase("b")) {
                command = Commands.BRUTE_FORCE;
                break;
            }else {
                System.out.println(CLI_COMMANDS);
                System.out.print("Введіть команду ще раз: ");
            }
        }
        return command;
    }

    private static String getFilePath(Boolean isBruteForce){
        Scanner scanner = new Scanner(System.in);
        System.out.print(isBruteForce ? FILE_PATH_DICTIONARY_MESSAGE : FILE_PATH_MESSAGE);
        String filePath = scanner.nextLine();
        return filePath;
    }

    private static int getKey(){
        int key = 0;
        System.out.print(KEY_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String value = scanner.nextLine();
            try {
                key = Integer.parseInt(value);
                break;
            }catch (NumberFormatException e){
                System.out.println("Невірний формат");
                System.out.print(KEY_MESSAGE);
            }
        }
        return key;
    }

    public static RunnerConfiguration mainMenu(){
        RunnerConfiguration config;
        Scanner scaner = new Scanner(System.in);
        while (true){
            config = new RunnerConfiguration();
            config.setCommand(getCommand());
            if(config.getCommand() == Commands.UNKNOWN){
                return config;
            }
            config.setFilePath(getFilePath(false));
            if(config.getCommand() == Commands.BRUTE_FORCE){
                config.setDictionaryFilePath(getFilePath(true));
            }else{
                config.setKey(getKey());
            }

            System.out.println("\nВи обрали наступні параметри:");
            if(config.getCommand() == Commands.BRUTE_FORCE){
                System.out.printf("Команда: %s\nШлях до файлу: %s\nКлюч: %s\n",
                        config.getCommand(), config.getFilePath(), config.getKey());
            }else {
                System.out.printf("Команда: %s\nШлях до файлу: %s\nШлях до файлу з ключовими словами: %s\n",
                        config.getCommand(), config.getFilePath(), config.getDictionaryFilePath());
            }

            System.out.print("Продовжити?\n[y]Так | [n]Ні\nВведіть команду: ");
            if (scaner.nextLine().equalsIgnoreCase("y")){
                break;
            }
        }
        return config;
    }
}
