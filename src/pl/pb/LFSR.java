package pl.pb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LFSR {
    private static final int NUMBER_OF_TRANSFORMATIONS = 10;
    private static final String SEED = "1001";

    public static String generator(String polynomial) throws InterruptedException {
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
        System.out.println("polynomial: " + Arrays.toString(polynomialBinary));
        System.out.println("seed: " + Arrays.toString(seed) + "\n");

        //tablica przechowuje indeksy jedynek znajdujacych sie w seedzie
        List<Integer> listWithIndexesSeed = new ArrayList<>();
        for (int i = 0; i < seed.length; i++) {
            if (seed[i] == 1) {
                listWithIndexesSeed.add(i);
            }
        }

        int iteration = 1;
        int transformation = 0;
        //while (System.in.available() == 0) {
        while (transformation != NUMBER_OF_TRANSFORMATIONS) {
            //Thread.sleep(200);

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

            result.append(tmpP);
            polynomialBinary[0] = tmpP;
            transformation++;
        }

        return result.toString();
    }

    public static String generator(String polynomials, String textCA) throws InterruptedException {
        StringBuilder polynomialBinaryTmp = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int[] seed = Arrays.stream(SEED.split("")).mapToInt(Integer::parseInt).toArray();
        String[] separatedPolynomial;
        separatedPolynomial = polynomials.split(",");
        //stopien wielomiana
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
        System.out.println("polynomial: " + Arrays.toString(polynomialBinary));
        System.out.println("seed: " + Arrays.toString(seed) + "\n");

        int iteration = 1;
        int transformation = 0;
        //while (System.in.available() == 0) {
        while (transformation != textCA.length() - 1) {
            Thread.sleep(800);
            //xorowanie 1bita wielomiana z 1 bitem seeda i kazdym bitem textu
            int tmpP = polynomialBinary[0] ^ seed[0] ^ textCA.charAt(iteration - 1);
            int tmpS = seed[0] ^ seed[seed.length - 1];
            System.out.println(iteration++ + ". " + polynomialBinary[0] + " xor " + seed[0] + " = " + tmpP);

            //przesuneicie wielomianu o jeden
            if (degreeOfPolynomial - 2 + 1 >= 0)
                System.arraycopy(polynomialBinary, 0, polynomialBinary, 1, degreeOfPolynomial - 2 + 1);
            //przesuneicie seeda o jeden
            System.arraycopy(seed, 0, seed, 1, seed.length - 2 + 1);

            result.append(tmpP);
            polynomialBinary[0] = tmpP;
            seed[0] = tmpS;
            transformation++;
        }
        return result.toString();
    }
}


//       for (int i = degreeOfPolynomial - 2; i >= 0; i--) {
//                polynomialBinary[i + 1] = polynomialBinary[i];
//            }

//      for (int i = seed.length - 2; i >= 0; i--) {
//                seed[i + 1] = seed[i];
//            }
