package pl.pb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LFSR {
    private static final int NUMBER_OF_TRANSFORMATIONS = 10;

    public static String generator(String polynomial, String SEED) throws InterruptedException {
        StringBuilder polynomialBinaryTmp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int[] seed = Arrays.stream(SEED.split("")).mapToInt(Integer::parseInt).toArray();
        int degreeOfPolynomial =  polynomial.charAt(polynomial.length()-1)%48;
        BinaryConverter.polyStringToBinary(polynomial, degreeOfPolynomial, polynomialBinaryTmp);
        int[] polynomialBinary = Arrays.stream(polynomialBinaryTmp.toString().split("")).mapToInt(Integer::parseInt).toArray();

        //tablica przechowuje indeksy jedynek znajdujacych sie w seedzie
        List<Integer> listWithIndexesSeed = new ArrayList<>();
        for (int i = 0; i < seed.length; i++) {
            if (seed[i] == 1) {
                listWithIndexesSeed.add(i);
            }
        }
        int transformation = 0;
        while (transformation != NUMBER_OF_TRANSFORMATIONS) {
            //xorowanie bitow wielomianu
            int tmpP = 0;
            for (Integer id : listWithIndexesSeed) {
                if (tmpP == 0) {
                    tmpP = polynomialBinary[id];
                    continue;
                }
                tmpP ^= polynomialBinary[id];
            }
            //przesuneicie wielomianu w prawo
            if (degreeOfPolynomial - 2 + 1 >= 0)
                System.arraycopy(polynomialBinary, 0, polynomialBinary, 1, degreeOfPolynomial - 2 + 1);
            result.append(tmpP);
            polynomialBinary[0] = tmpP;
            transformation++;
        }
        return result.toString();
    }

    public static String generatorWithText(String polynomial, String SEED, String text) throws InterruptedException {
        StringBuilder polynomialBinaryTmp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int[] seed = Arrays.stream(SEED.split("")).mapToInt(Integer::parseInt).toArray();
        int[] intText = Arrays.stream(text.split("")).mapToInt(Integer::parseInt).toArray();
        int degreeOfPolynomial =  polynomial.charAt(polynomial.length()-1)%48;
        BinaryConverter.polyStringToBinary(polynomial, degreeOfPolynomial, polynomialBinaryTmp);
        int[] polynomialBinary = Arrays.stream(polynomialBinaryTmp.toString().split("")).mapToInt(Integer::parseInt).toArray();

        //tablica przechowuje indeksy jedynek znajdujacych sie w seedzie
        List<Integer> listWithIndexesSeed = new ArrayList<>();
        for (int i = 0; i < seed.length; i++) {
            if (seed[i] == 1) {
                listWithIndexesSeed.add(i);
            }
        }

        //dodanie pierwszego bitu na start
        result.append(polynomialBinary[0]);
        int transformation = 0;
        int index = 0;

        while (transformation != text.length() - 1) {
            //xorowanie bitow wielomianu
            int tmpP = 0;
            for (Integer integer : listWithIndexesSeed) {
                if (tmpP == 0) {
                    tmpP = polynomialBinary[integer];
                    continue;
                }
                tmpP ^= polynomialBinary[integer];
            }
            //przesuneicie wielomianu o jeden
            if (degreeOfPolynomial - 2 + 1 >= 0)
                System.arraycopy(polynomialBinary, 0, polynomialBinary, 1, degreeOfPolynomial - 2 + 1);

            tmpP = tmpP ^ intText[index++];
            result.append(tmpP);
            polynomialBinary[0] = tmpP;
            transformation++;
        }

        return result.toString();
    }


}
