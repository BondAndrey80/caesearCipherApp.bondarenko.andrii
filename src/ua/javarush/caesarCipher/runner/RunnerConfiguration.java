package ua.javarush.caesarCipher.runner;
import ua.javarush.caesarCipher.constants.Commands;

public class RunnerConfiguration {
    private Commands command = Commands.UNKNOWN;
    private String filePath = "";
    private String dictionaryFilePath = "";
    private int key = 0;

    public String getDictionaryFilePath() {
        return dictionaryFilePath;
    }

    public void setDictionaryFilePath(String dictionaryFilePath) {
        this.dictionaryFilePath = dictionaryFilePath;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Commands getCommand() {
        return command;
    }

    public void setCommand(Commands command) {
        this.command = command;
    }
}

