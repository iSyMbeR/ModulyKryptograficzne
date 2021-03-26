package pl.pb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LFSR {
    private static final int NUMBER_OF_TRANSFORMATIONS = 10;


    public static String generator(String polynomial,String SEED) throws InterruptedException {
        StringBuilder polynomialBinaryTmp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int[] seed = Arrays.stream(SEED.split("")).mapToInt(Integer::parseInt).toArray();
        String[] separatedPolynomial;
        separatedPolynomial = polynomial.split(",");
        //stopien wielomianu
        int degreeOfPolynomial = Integer.parseInt(separatedPolynomial[separatedPolynomial.length - 1]);

        int index = 0;
        for (int i = 0; i < degreeOfPolynomial; i++) {
            try {
                if ((i + 1) == Integer.parseInt(separatedPolynomial[index]) && (!separatedPolynomial[index].isBlank())) {
                    polynomialBinaryTmp.append("1");
                    index++;
                } else polynomialBinaryTmp.append("0");
            } catch (ArrayIndexOutOfBoundsException ignored) {
                polynomialBinaryTmp.append("0");
            }
        }

        int[] polynomialBinary = Arrays.stream(polynomialBinaryTmp.toString().split("")).mapToInt(Integer::parseInt).toArray();
//        System.out.println("polynomial: " + Arrays.toString(polynomialBinary));
//        System.out.println("seed: " + Arrays.toString(seed) + "\n");

        //tablica przechowuje indeksy jedynek znajdujacych sie w seedzie
        List<Integer> listWithIndexesSeed = new ArrayList<>();
        for (int i = 0; i < seed.length; i++) {
            if (seed[i] == 1) {
                listWithIndexesSeed.add(i);
            }
        }
        //dodanie pierwszego bitu na start
        result.append(polynomialBinary[0]);
        int iteration = 1;
        int transformation = 0;
        //while (System.in.available() == 0) {
        while (transformation != NUMBER_OF_TRANSFORMATIONS - 1) {
            //Thread.sleep(200);

            //xorowanie bitow wielomianu
            int tmpP = 0;
            for (Integer id : listWithIndexesSeed) {
                if (tmpP == 0) {
                    tmpP = polynomialBinary[id];
                    continue;
                }
                //System.out.println(tmpP + " xor " + polynomialBinary[integer]);
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
        //text = BinaryConverter.binaryConverter(text);
        //text = text.replaceAll("\\s+", "");
        StringBuilder polynomialBinaryTmp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int[] seed = Arrays.stream(SEED.split("")).mapToInt(Integer::parseInt).toArray();
        int[] intText = Arrays.stream(text.split("")).mapToInt(Integer::parseInt).toArray();
        String[] separatedPolynomial;
        separatedPolynomial = polynomial.split(",");
        //stopien wielomianu
        int degreeOfPolynomial = Integer.parseInt(separatedPolynomial[separatedPolynomial.length - 1]);

        int index = 0;
        for (int i = 0; i < degreeOfPolynomial; i++) {
            try {
                if ((i + 1) == Integer.parseInt(separatedPolynomial[index]) && (!separatedPolynomial[index].isBlank())) {
                    polynomialBinaryTmp.append("1");
                    index++;
                } else polynomialBinaryTmp.append("0");
            } catch (ArrayIndexOutOfBoundsException ignored) {
                polynomialBinaryTmp.append("0");
            }
        }

        int[] polynomialBinary = Arrays.stream(polynomialBinaryTmp.toString().split("")).mapToInt(Integer::parseInt).toArray();


        //tablica przechowuje indeksy jedynek znajdujacych sie w seedzie
        List<Integer> listWithIndexesSeed = new ArrayList<>();
        for (int i = 0; i < seed.length; i++) {
            if (seed[i] == 1) {
                listWithIndexesSeed.add(i);
            }
        }

        int iteration = 1;
        //dodanie pierwszego bitu na start
        result.append(polynomialBinary[0]);
        //System.out.println(result.toString());
        int transformation = 0;
        index = 0;
        //while (System.in.available() == 0) {
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
