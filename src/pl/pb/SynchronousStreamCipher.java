package pl.pb;

public class SynchronousStreamCipher {
    public static String encrypt(String polynomial, String SEED, String text) throws InterruptedException {
        String key = LFSR.generator(polynomial, SEED);
        StringBuilder result = new StringBuilder();
        int keyIter = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result.append(" ");
                continue;
            }
            if (keyIter > key.length() - 1) {
                keyIter = 0;
            }
            result.append(text.charAt(i) ^ key.charAt(keyIter));
            keyIter++;
        }
        return result.toString();
    }

    public static String decrypt(String polynomial, String SEED, String text) throws InterruptedException {
        String key = LFSR.generator(polynomial, SEED);
        StringBuilder result = new StringBuilder();
        int keyIter = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result.append(" ");
                continue;
            }
            if (keyIter > key.length() - 1) {
                keyIter = 0;
            }
            result.append(text.charAt(i) ^ key.charAt(keyIter));
            keyIter++;
        }
        return result.toString();
    }


}
