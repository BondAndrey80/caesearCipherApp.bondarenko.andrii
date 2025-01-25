package ua.javarush.caesarCipher.constants;
import java.util.Arrays;

public class Alpabets {
    public final static String[] ALPHABET_UK = {
            "А", "Б", "В", "Г", "Ґ", "Д", "Е", "Є", "Ж", "З", "И", "І", "Ї", "Й",
            "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч",
            "Ш", "Щ", "Ь", "Ю", "Я",
            "а", "б", "в", "г", "ґ", "д", "е", "є", "ж", "з", "и", "і", "ї", "й",
            "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч",
            "ш", "щ", "ь", "ю", "я"
    };
    public final static String[] ALPHABET_EN = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };
    public final static String[] SPECIAL_SYMBOL = {
            ".", "+", "=", ",", "«", "*", "—", "(", "»", "\"", "'", ":", "!", "?", " ", ")", "-", "#", ";"
    };
    public static Language getLanguage(char symbol){
        if(Arrays.asList(ALPHABET_UK).contains(String.valueOf(symbol))){
            return Language.UK;
        }else {
            return Language.EN;
        }
    }

    public static String[] getAlphabet(Language code){
        if (code == Language.UK){
            return Alpabets.ALPHABET_UK;
        }else {
            return Alpabets.ALPHABET_EN;
        }
    }

}
