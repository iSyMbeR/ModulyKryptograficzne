package pl.pb;

public class SynchronousStreamCipher {
    public static String encrypt(String text, String key) {
        String binaryText = binaryConverter(text);
        StringBuilder result = new StringBuilder();
        int keyIter = 0;
        for (int i = 0; i < binaryText.length(); i++) {
            if (binaryText.charAt(i) == ' ') {
                result.append(" ");
                continue;
            }
            if(keyIter > key.length() - 1){
                keyIter = 0;
            }
            result.append(binaryText.charAt(i) ^ key.charAt(keyIter));
            keyIter++;
        }
        return result.toString();
    }

    public static String decrypt(String text, String key){
        StringBuilder result = new StringBuilder();
        int keyIter = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result.append(" ");
                continue;
            }
            if(keyIter > key.length() - 1){
                keyIter = 0;
            }
            result.append(text.charAt(i) ^ key.charAt(keyIter));
            keyIter++;
        }
        return binaryToText(result.toString());
    }


    public static String binaryConverter(String text) {

        StringBuilder result = new StringBuilder();
        char[] messChar = text.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            result.append(Integer.toBinaryString(messChar[i])).append(" ");
        }
        return result.toString();
    }
    public static String binaryToText(String binaryText) {
        String[] binaryNumbers = binaryText.split(" ");
        String text = "";

        for (String currentBinary : binaryNumbers) {
            int decimal = binaryToDecimal(currentBinary);
            char letra = (char) decimal;
            text += letra;
        }
        return text;
    }
    public static int binaryToDecimal(String binary) {
        int decimal = 0;
        int position = 0;
        for (int x = binary.length() - 1; x >= 0; x--) {
            // Saber si es 1 o 0; primero asumimos que es 1 y abajo comprobamos
            short digit = 1;
            if (binary.charAt(x) == '0') {
                digit = 0;
            }
            double multiplier = Math.pow(2, position);
            decimal += digit * multiplier;
            position++;
        }
        return decimal;
    }
}
