package logic;

import java.lang.StringBuilder;

import collections.HashMap;

import java.lang.StringBuilder;

public class WordConverter {
    public static void start() {
        String marleyText = "rsgc(qqd^i$tkz)$ko$^udzhd,(rld$sgk^z$)$^gpssld";
        String key = "marley";
        String encryptedText = encryptText(marleyText, key);
        String decryptedText = decryptText(encryptedText, key);
        System.out.println("Marley Text: " + marleyText);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
        System.out.println("Paradis Text: " + convertWord(decryptedText));
    }
    public static String convertWord(String input){
        HashMap<Character, Character> map = new HashMap<>();
        char[] paradis = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '$', ','};
        char[] marley = {'j', 'c', 't', 'a', 'k', 'z', 's', 'i', 'w', 'x', 'o', 'n', 'g', 'b', 'f', 'h', 'l', 'd', 'e', 'y', 'm', 'v', 'u', 'p', 'q', 'r', ' ', ','};
        for(int i = 0; i<paradis.length; i++){ //put in all paradis characters and their respective marley character through for loop
            map.put(paradis[i], marley[i]);
        }
        StringBuilder res = new StringBuilder(); //act as result which will be return in this method;
        StringBuilder invert = new StringBuilder(); //a temperary variable that use to store the string that are need to invert
        boolean toUpper = false; //to check whether need to convert the character to upper case or not
        boolean inverting = false; //to check whether the character need to store inside invert
        for(int i = 0; i<input.length(); i++){ //loop through every character in input
            char c = input.charAt(i);
            switch(c){
                case '^':
                    toUpper = true; //next character will be uppercase
                    break;
                case '(':
                    inverting = true; //will start invert the string until reach ')'
                    break;
                case ')':
                    res.append(invert.reverse().toString()); //reverse the invert variable and append it to res
                    invert.setLength(0); //clear the invert
                    inverting = false; //stop inverting since it reached ')'
                    break;
                default:
                    Character convertedChar = map.get(c); //get the value of the key from hashmap
                    if(inverting){ //if inverting, character will store inside invert
                        if(toUpper){ //if toUpper is true, the character will convert to upper case
                            invert.append(Character.toUpperCase(convertedChar));
                            toUpper = false;
                        }
                        else{
                            invert.append(convertedChar);
                        }
                    }
                    else{
                        if(toUpper){
                            res.append(Character.toUpperCase(convertedChar));
                            toUpper = false;
                        }
                        else{
                            res.append(convertedChar);
                        } 
                    }
            }
        }
        return res.toString();
    }


    //EXTRA FEATURE --> CIPHER
    public static String encryptText(String str, String key){
        str = str.toUpperCase();
        key = generateKey(str, key);
        String res = "";

        for(int i = 0; i<str.length(); i++){
            if(!Character.isAlphabetic(str.charAt(i))){
                res += str.charAt(i);
                continue;
            }
            int c = (str.charAt(i) + key.charAt(i)) % 26; // mod 26 to convert the number in to range 0 - 25
            c += 'A';
            res += (char)c;
        }
        return res;
    }

    public static String decryptText(String encrypt, String key){
        encrypt = encrypt.toUpperCase();
        key = generateKey(encrypt, key);
        String res = "";
        for(int i = 0; i<encrypt.length(); i++){
            if(!Character.isAlphabetic(encrypt.charAt(i))){
                res += encrypt.charAt(i);
                continue;
            }
            int c = (encrypt.charAt(i) - key.charAt(i) + 26) % 26;
            c += 'A';
            res += (char)c;
        }
        return res.toLowerCase();
    }

    public static String generateKey(String str, String key){
        key = key.toUpperCase();
        int s = str.length();
        int k = key.length();
        if(s == k){
            return key;
        }
        if(s < k){
            return key.substring(0, s);
        }
        String newKey = key;
        int i = k;
        while(i < s){
            newKey += key.charAt(i % k);
            i++;
        }
        return newKey;
    }
}
