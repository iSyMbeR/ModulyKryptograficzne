package pl.pb;

public class Main {
    public static void main(String[] args) {
        Cryptography cryptography = new Cryptography();

        String text = "CRYPTOGRAPHY";
        int key = 3;

        String encryption = cryptography.railFenceEncryption(text, key);
        System.out.println(encryption);
        System.out.println(cryptography.railFenceDecryption(encryption, key));

    }
}
