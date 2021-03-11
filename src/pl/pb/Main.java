package pl.pb;

public class Main {
    public static void main(String[] args) {
        Cryptography cryptography = new Cryptography();
        String text;
        String key;
        int intKey;
        String encryptedText;
        String decryptedText;

        // Zad 1. Zaimplementuj algorytm kodujacy i dekodujacy z wykorzystaniem szyfru prostego przestawiania „rail fence" dla k = n. Skorzystaj z przykładu 1 (1 punkt).
        text = "POLITECHNIKA";
        intKey = 3;

        System.out.println("\u001B[32m" + "Zad 1. RailFence\nText: " + text + "\nKey: " + intKey + "\u001B[0m");
        encryptedText = cryptography.railFenceEncryption(text, intKey);
        decryptedText = cryptography.railFenceDecryption(encryptedText, intKey);
        System.out.println("Text '" + text + "' po zaszyfrowaniu wyglada tak : " + encryptedText);
        System.out.println("\nText '" + encryptedText + "' po odszyfrowaniu wyglada tak : " + decryptedText);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");


        // Zad 2. Zaimplementuj kryptosystem przedstawieniowy bazujacy na przykładzie 2a dla d = 5 oraz klucza key = 3-4-1-5-2 (1 punkt).
        text = "POLITECHNIKA";
        key = "3-4-1-5-2";

        System.out.println("\u001B[32m" + "Zad 2.Przestawienia macierzowe 2a\nText: " + text + "\nKey: " + key + "\u001B[0m");
        encryptedText = cryptography.przestawienieMacierzowe2aEncryption(text, key);
        decryptedText = cryptography.przestawienieMacierzowe2aDecryption(encryptedText, key);
        System.out.println("Text '" + text + "' po zaszyfrowaniu wyglada tak : " + encryptedText);
        System.out.println("\nText '" + encryptedText + "' po odszyfrowaniu wyglada tak : " + decryptedText);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");


        // Zad 3. Zaimplementuj kryptosystem przedstawieniowy bazuj ˛acy na przykładzie 2b (1 punkt)
        text = "POLITECHNIKA";
        key = "DZIEKAN";

        System.out.println("\u001B[32m" + "Zad 2. Przestawienia macierzowe 2b\nText: " + text + "\nKey: " + key + "\u001B[0m");
        encryptedText = cryptography.przestawienieMacierzowe2bEncryption(text, key);
        decryptedText = cryptography.przestawienieMacierzowe2bDecryption(encryptedText, key);
        System.out.println("Text '" + text + "' po zaszyfrowaniu wyglada tak : " + encryptedText);
        System.out.println("\nText '" + encryptedText + "' po odszyfrowaniu wyglada tak : " + decryptedText);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");


        // oraz 2c (2 punkty) dla dowolnego klucza
        text = "POLITECHNIKA";
        key = "DZIEKAN";

        System.out.println("\u001B[32m" + "Zad 3. Przestawienia macierzowe 2c\nText: " + text + "\nKey: " + key + "\u001B[0m");
        encryptedText = cryptography.przestawienieMacierzowe2cEncryption(text, key);
        decryptedText = cryptography.przestawienieMacierzowe2cDecryption(encryptedText, key);
        System.out.println("Text '" + text + "' po zaszyfrowaniu wyglada tak : " + encryptedText);
        System.out.println("\nText '" + encryptedText + "' po odszyfrowaniu wyglada tak : " + decryptedText);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");

        //Zad 4. Cezar
        text = "POLITECHNIKA";
        intKey = 3;

        System.out.println("\u001B[32m" + "Zad 4. Szyfrowanie cezara (Caesar cipher)3a\nText: " + text + "\nKey: " + intKey + "\u001B[0m");
        encryptedText = cryptography.caesarEncryption(text, intKey);
        decryptedText = cryptography.caesarDecryption(encryptedText, intKey);
        System.out.println("Text '" + text + "' po zaszyfrowaniu wyglada tak : " + encryptedText);
        System.out.println("\nText '" + encryptedText + "' po odszyfrowaniu wyglada tak : " + decryptedText);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");


        // Zad 5. Zaimplementuj kryptosystem bazuj ˛acy na tablicy Vigenere’a (1 punkt).
        text = "POLITECHNIKA";
        key = "DZIEKAN";
        System.out.println("\u001B[32m" + "Zad 5. Szyfrowanie Vigenere’a\nText: " + text + "\nKey: " + key + "\u001B[0m");
        encryptedText = cryptography.vigenereEncryption(text, key);
        decryptedText = cryptography.vigenereDecryption(encryptedText, key);
        System.out.println("Text '" + text + "' po zaszyfrowaniu wyglada tak : EK(M) = " + encryptedText);
        System.out.println("\nText '" + encryptedText + "' po odszyfrowaniu wyglada tak : " + decryptedText);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }
}
