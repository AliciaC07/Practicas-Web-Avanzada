package practica.mocky.practica2pwa.config;

import java.util.HashMap;
import java.util.Map;

public class RamEncoder {
    private final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] allowedCharacters = allowedString.toCharArray();
    //en base 62 segun la doc
    private final int base = allowedCharacters.length;
    private Map<String, String> keyString;
    private Map<String, String> stringKey;

    public RamEncoder() {
        keyString = new HashMap<>();
        stringKey = new HashMap<>();
    }

    public String encode(String string){
        String stringCutted = "";
        if (stringKey.containsKey(string)){
            stringCutted = keyString.get(string);
        }else {
            stringCutted = setKey(string);
        }

        return stringCutted;

    }
    public String decode(String cuttedUrl){
        String decoded = "";
        String key = cuttedUrl.substring(cuttedUrl.lastIndexOf("/") + 1);
        decoded = keyString.get(key);
        return decoded;

    }

    public String ramChar(){
        StringBuilder ramString = new StringBuilder();
        for (int i =0; i < 8; i++){
            ramString.append(allowedString.charAt((int) Math.floor(Math.random() * base)));
        }
        return ramString.toString();
    }
    public String setKey(String string){
        String key;
        key = ramChar();
        keyString.put(key,string);
        stringKey.put(string,key);
        return key;
    }
}
