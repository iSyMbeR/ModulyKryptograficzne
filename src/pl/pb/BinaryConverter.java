package pl.pb;

public class BinaryConverter {
    public static String binaryConverter(String text) {

        StringBuilder result = new StringBuilder();
        char[] messChar = text.toCharArray();

        for (char c : messChar) {
            result.append(Integer.toBinaryString(c)).append(" ");
        }

        return result.toString();
    }

    public static String binaryToText(String binaryText) {
        String[] binaryNumbers = binaryText.split(" ");
        StringBuilder text = new StringBuilder();

        for (String currentBinary : binaryNumbers) {
            int decimal = binaryToDecimal(currentBinary);
            char letra = (char) decimal;
            text.append(letra);
        }
        return text.toString();
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

    public static void polyStringToBinary(String polyTmp, int degreeOfPoly, StringBuilder polynomial){
        String[] separatedPolynomial;
        separatedPolynomial = polyTmp.split(",");
        int index = 0;
        for (int i = 0; i < degreeOfPoly; i++) {
            try {
                if ((i + 1) == Integer.parseInt(separatedPolynomial[index]) && (!separatedPolynomial[index].isBlank())) {
                    polynomial.append("1");
                    index++;
                } else polynomial.append("0");
            } catch (ArrayIndexOutOfBoundsException ignored) {
                polynomial.append("0");
            }
        }
    }
    public static void polyIntArrayToBinary(String polyTmp, int degreeOfPoly, int[] polynomial){
        String[] separatedPolynomial;
        separatedPolynomial = polyTmp.split(",");
        int index = 0;
        for (int i = 0; i < degreeOfPoly; i++) {
            try {
                if ((i + 1) == Integer.parseInt(separatedPolynomial[index]) && (!separatedPolynomial[index].isBlank())) {
                    polynomial[i] = 1;
                    index++;
                } else polynomial[i] = 0;
            } catch (ArrayIndexOutOfBoundsException ignored) {
                polynomial[i] = 0;
            }
        }
    }
}
