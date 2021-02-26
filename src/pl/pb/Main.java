package pl.pb;

public class Main {
    public static void main(String[] args) {
        Cryptography cryptography = new Cryptography();

        String text = "CRYPTOGRAPHYOSA";
        String key = "3-4-1-2";

        String encryptedWord = cryptography.przestawienieMacierzowe2aEncryption(text, key);
        System.out.println(cryptography.przestawienieMacierzowe2aDecryption(encryptedWord, key));
    }
}
