package ua.javarush.caesarCipher.cipher;

import ua.javarush.caesarCipher.constants.Alpabets;
import ua.javarush.caesarCipher.constants.Commands;
import ua.javarush.caesarCipher.constants.Language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaesarCipher {
    private int key;
    private int specialSymbolKey;

    public CaesarCipher(int key){
        setKey(key);
    }

    private void setKey(int key){
        this.key = key;
        int tempKey = getKey(Alpabets.ALPHABET_EN.length);
        this.specialSymbolKey = Math.abs(tempKey) >= Alpabets.SPECIAL_SYMBOL.length ? tempKey % Alpabets.SPECIAL_SYMBOL.length : tempKey;
    }

    private int getKey(int alphabetLength){
        return Math.abs(this.key) >= alphabetLength ? this.key % alphabetLength  : this.key;
    }

    public int getUserKey(){
        return this.key;
    }

    private ArrayList<String> getWords(String string){
        ArrayList<String> words = new ArrayList<>();
        List<String> specialSymbols = Arrays.asList(Alpabets.SPECIAL_SYMBOL);
        StringBuilder tempString = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {

            char c = string.charAt(i);
            if(specialSymbols.contains(String.valueOf(c)) || i == string.length() - 1){
                if(!tempString.isEmpty()) {
                    words.add(tempString.toString());
                }
                tempString = new StringBuilder();
            }else {
                tempString.append(c);
            }

        }
        return words;
    }

    private String encryptDecrypt(String string, Commands command){
        StringBuilder encryptString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {

            char c = string.charAt(i);

            String[] alphabet;
            int shiftKey;
            if (Character.isLetter(c)){
                Language code = Alpabets.getLanguage(c);
                alphabet = Alpabets.getAlphabet(code);
                shiftKey = getKey(alphabet.length);

            }else{
                alphabet = Alpabets.SPECIAL_SYMBOL;
                shiftKey = this.specialSymbolKey;
            }

            int index = -1;
            for (int j = 0; j < alphabet.length; j++) {
                if (alphabet[j].equals(Character.toString(c))){
                    if(command == Commands.ENCRYPT) {
                        index = j + shiftKey;
                    }else {
                        index = j - shiftKey;
                    }
                    if (index < 0){
                        index = index + alphabet.length;
                    }else {
                        index = index >= alphabet.length ? index - alphabet.length : index;
                    }
                    break;
                }
            }

            if (index == -1){
                //Не нашли символ в алфавите, оставляем без изменений
                encryptString.append(c);
            }else {
                encryptString.append(alphabet[index]);
            }
        }
        return encryptString.toString();
    }

    public String encrypt(String string){
        return encryptDecrypt(string, Commands.ENCRYPT);
    }

    public String decrypt(String string){
        return encryptDecrypt(string, Commands.DECRYPT);
    }

    public ArrayList<String> bruteForce(ArrayList<String> encryptedStrings, BruteForceConfiguration bruteForceConfig){
        ArrayList<String> decryptedStrings = new ArrayList<>();

        int maxKey = Math.max(Alpabets.ALPHABET_EN.length, Alpabets.ALPHABET_UK.length);
        boolean isKeyFound = false;

        for (String line : encryptedStrings){
            ArrayList<String> encryptedWords = getWords(line);

            for (String encryptWord : encryptedWords){
                ArrayList<String> keywords = bruteForceConfig.getDictionaryKey().get(encryptWord.length());
                if(keywords == null){
                    continue;
                }
                for (int i = 0; i < maxKey; i++) {
                    this.setKey(i);
                    String decryptedWord = this.decrypt(encryptWord);

                    for (String keyword : keywords){
                        if(keyword.equalsIgnoreCase(decryptedWord)){
                            isKeyFound = true;
                            break;
                        }
                    }

                    if(isKeyFound){
                        break;
                    }
                }

                if(isKeyFound){
                    break;
                }
            }

            if(isKeyFound){
                break;
            }
        }

        if (isKeyFound){
            for (String line: encryptedStrings){
                decryptedStrings.add(this.decrypt(line));
            }
        }
        return decryptedStrings;
    }
}
