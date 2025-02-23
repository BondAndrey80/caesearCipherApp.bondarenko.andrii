package ua.javarush.caesarCipher.cipher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForceConfiguration {

    private final Map<Integer, List<String>> dictionaryKey = new HashMap<>();

    public Map<Integer, List<String>> getDictionaryKey() {
        return dictionaryKey;
    }

    public void loadDictionary(List<String> keywords){
        for(String word : keywords){
            List<String> wordList = this.dictionaryKey.get(word.length());
            if(wordList == null){
                wordList = new ArrayList<>();
            }
            wordList.add(word);
            this.dictionaryKey.put(word.length(), wordList);
        }
    }
}
