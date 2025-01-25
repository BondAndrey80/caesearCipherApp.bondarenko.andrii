package ua.javarush.caesarCipher.cipher;
import java.util.ArrayList;
import java.util.HashMap;

public class BruteForceConfiguration {

    private HashMap<Integer, ArrayList<String>> dictionaryKey = new HashMap<>();

    public BruteForceConfiguration(){

    }

    public HashMap<Integer, ArrayList<String>> getDictionaryKey() {
        return dictionaryKey;
    }

    public void loadDictionary(ArrayList<String> keywords){
        for(String word : keywords){
            ArrayList<String> wordList = this.dictionaryKey.get(word.length());
            if(wordList == null){
                wordList = new ArrayList<>();
            }
            wordList.add(word);
            this.dictionaryKey.put(word.length(), wordList);
        }
    }

}
