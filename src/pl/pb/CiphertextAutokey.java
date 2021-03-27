package pl.pb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CiphertextAutokey {
    public static String encrypt(String polynomial, String SEED, String text) throws InterruptedException {
        String key = LFSR.generatorWithText(polynomial, SEED, text);
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
        int[] result = new int[text.length()];
        int[] seed = Arrays.stream(SEED.split("")).mapToInt(Integer::parseInt).toArray();
        int[] intText = Arrays.stream(text.split("")).mapToInt(Integer::parseInt).toArray();
        String[] separatedPolynomial;
        separatedPolynomial = polynomial.split(",");
        //stopien wielomianu
        int degreeOfPolynomial = polynomial.charAt(polynomial.length() - 1) % 48;

        int[] polyBinary = new int[degreeOfPolynomial];
        BinaryConverter.polyIntArrayToBinary(polynomial, degreeOfPolynomial, polyBinary);

        //tablica przechowuje indeksy jedynek znajdujacych sie w seedzie
        List<Integer> listWithIndexesSeed = new ArrayList<>();
        for (int i = 0; i < seed.length; i++) {
            if (seed[i] == 1) {
                listWithIndexesSeed.add(i);
            }
        }
        for (int i = 0; i < intText.length; i++) {
            result[i] = (polyBinary[0] ^ intText[i]);
            int tmpP = 0;
            //xorowanie wielomiana
            for (Integer integer : listWithIndexesSeed) {
                if (tmpP == 0) {
                    tmpP = polyBinary[integer];
                    continue;
                }
                tmpP ^= polyBinary[integer];
            }
            //przesuniecie wielominau o 1
            System.arraycopy(polyBinary, 0, polyBinary, 1, degreeOfPolynomial - 2 + 1);
            //xorowanie z bitem wyniku
            tmpP = tmpP ^ result[i];
            polyBinary[0] = tmpP;
        }
        return Arrays.toString(result);
    }
}
