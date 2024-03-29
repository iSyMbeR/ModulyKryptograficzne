package pl.pb;


import jdk.swing.interop.SwingInterOpUtils;

import java.util.Arrays;
import java.util.HashMap;

public class Cryptography {
    private String encryptedText = "";
    private String decryptedText = "";

    public Cryptography() {
    }

    public String railFenceEncryption(String text, int key) {
        encryptedText = "";
        boolean direction = false;
        int currentRow = 0;
        int column = text.length();
        char[][] a = new char[key][column];

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < column; j++)
                a[i][j] = '#';
        }

        for (int i = 0; i < column; i++) {
            if (currentRow == 0 || currentRow == key - 1) {
                direction = !direction;
            }
            a[currentRow][i] = text.charAt(i);

            if (direction) currentRow++;
            else currentRow--;
        }

        //filling encryptedText
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < column; j++) {
                if (a[i][j] != '#')
                    encryptedText += a[i][j];
            }
        }

        //display matrix
//        for (int i = 0; i < key; i++) {
//            for (int j = 0; j < column; j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println();
//        }

        return encryptedText;
    }

    public String railFenceDecryption(String text, int key) {
        decryptedText = "";
        boolean direction = false;
        int currentRow = 0;
        int column = text.length();


        char[][] a = new char[key][column];

        for (int i = 0; i < column; i++) {
            if (currentRow == 0 || currentRow == key - 1) {
                direction = !direction;
            }
            a[currentRow][i] = '*';

            if (direction) currentRow++;
            else currentRow--;
        }

        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < column; j++) {
                if (a[i][j] == '*' && index < column) {
                    a[i][j] = text.charAt(index++);
                }
            }
        }

        direction = false;
        currentRow = 0;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < key; j++) {
                if (currentRow == 0 || currentRow == key - 1) {
                    direction = !direction;
                }
                decryptedText += a[j][i];
                if (direction) currentRow++;
                else currentRow--;
            }
        }
        return decryptedText;
    }

    public String przestawienieMacierzowe2aEncryption(String text, String key) {
        encryptedText = "";

        String[] tmpKey = key.split("-");
        int[] intKey = new int[tmpKey.length];

        for (int i = 0; i < tmpKey.length; i++) {
            intKey[i] = Integer.parseInt(String.valueOf(tmpKey[i].charAt(0)));
        }

        double column = intKey.length;
        double row = Math.ceil(text.length() / column);
        char[][] a = new char[(int) row][(int) column];
        int index = 0;

        //filling & display
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (index < text.length())
                    a[i][j] = text.charAt(index++);
                //System.out.print(a[i][j]);

            }
            //System.out.println();
        }

        for (int i = 0; i < row; i++) {
            for (int k : intKey) {
                encryptedText += a[i][k - 1];
            }
        }
        return encryptedText;
    }

    public String przestawienieMacierzowe2aDecryption(String text, String key) {
        decryptedText = "";
        String[] tmpKey = key.split("-");
        int[] intKey = new int[tmpKey.length];

        for (int i = 0; i < tmpKey.length; i++) {
            intKey[i] = Integer.parseInt(String.valueOf(tmpKey[i].charAt(0)));
        }

        double column = intKey.length;
        double row = Math.ceil(text.length() / column);
        char[][] a = new char[(int) row][(int) column];
        int index = 0;

        //filling & display
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (index < text.length())
                    a[i][j] = text.charAt(index++);
            }
            //System.out.println();
        }
        // System.out.println();

        for (int i = 0; i < row; i++) {
            index = 0;
            for (int k = 0; k < intKey.length; k++) {
                //jak mamy klucz key=3-1-4-2 to to wczytuje od indexu tam gdzie sie znajduje 1 czyli 1 potem 2 czyli 3
                if (intKey[k] - 1 == index) {
                    decryptedText += a[i][k];
                    index++;
                    if (k == intKey.length - 1) {
                        for (int x = 0; x < intKey.length; x++) {
                            if (intKey[x] - 1 == index) {
                                decryptedText += a[i][x];
                                index++;
                                x = 0;
                            }
                        }
                    }
                    k = 0;
                }
            }
        }

        return decryptedText;
    }

    public String przestawienieMacierzowe2bEncryption(String text, String key) {
        encryptedText = "";
        StringBuilder stringBuilder = new StringBuilder();
        double column = key.length();
        double row = Math.ceil(text.length() / column);
        char[][] textCharArray = new char[(int) row][(int) column];
        char[] keyCharArray = new char[key.length()];
        char[] sortedKeyCharArray;

        for (int i = 0; i < keyCharArray.length; i++) {
            keyCharArray[i] = key.charAt(i);
        }

        sortedKeyCharArray = keyCharArray.clone();
        Arrays.sort(sortedKeyCharArray);

        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (index < text.length()) {
                    textCharArray[i][j] = text.charAt(index++);
                    // System.out.print(textCharArray[i][j]);
                }

            }
            //System.out.println();
        }
        System.out.println();
        int[] arrayOfIndexes = new int[keyCharArray.length];
        char[] tmpKeyCharArray;
        tmpKeyCharArray = keyCharArray.clone();
        // zapisuje kolejnosc indexow
        for (int i = 0; i < keyCharArray.length; i++) {
            for (int j = 0; j < sortedKeyCharArray.length; j++) {
                if (sortedKeyCharArray[i] == tmpKeyCharArray[j]) {
                    tmpKeyCharArray[j] = 0;
                    arrayOfIndexes[i] = j;
                    break;
                }
            }
        }

        for (int i = 0; i < column; i++) {
            index = arrayOfIndexes[i];
            for (int j = 0; j < row; j++) {
                stringBuilder.append(textCharArray[j][index]);
            }
            encryptedText = String.valueOf(stringBuilder);
        }

        return encryptedText;
    }

    public String przestawienieMacierzowe2bDecryption(String text, String key) {
        decryptedText = "";
        StringBuilder stringBuilder = new StringBuilder();
        double column = key.length();
        double row = Math.ceil(text.length() / column);
        char[][] textCharArray = new char[(int) row][(int) column];
        char[] keyCharArray = new char[key.length()];
        char[] sortedKeyCharArray;

        //string -> char
        for (int i = 0; i < keyCharArray.length; i++) {
            keyCharArray[i] = key.charAt(i);
        }
        //sklonowanie tablicy charow
        sortedKeyCharArray = keyCharArray.clone();
        //sortowanie liter
        Arrays.sort(sortedKeyCharArray);


        //tablica przechowujaca indexy pokolei posorotowanych liter
        int[] arrayOfIndexes = new int[keyCharArray.length];
        char[] tmpKeyCharArray;
        tmpKeyCharArray = keyCharArray.clone();
        // zapisuje kolejnosc indexow
        for (int i = 0; i < keyCharArray.length; i++) {
            for (int j = 0; j < sortedKeyCharArray.length; j++) {
                if (sortedKeyCharArray[i] == tmpKeyCharArray[j]) {
                    tmpKeyCharArray[j] = 0;
                    arrayOfIndexes[i] = j;
                    break;
                }
            }
        }

        int index = 0;
        int index2 = 0;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                textCharArray[j][arrayOfIndexes[index]] = text.charAt(index2++);
            }
            index++;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                stringBuilder.append(textCharArray[i][j]);
            }
        }

        decryptedText = String.valueOf(stringBuilder);
        return decryptedText;
    }

    public String przestawienieMacierzowe2cEncryption(String text, String key) {
        encryptedText = "";
        StringBuilder stringBuilder = new StringBuilder();
        double column = key.length();
        double row = column;
        char[][] textCharArray = new char[(int) row][(int) column];
        char[] keyCharArray = new char[key.length()];
        char[] sortedKeyCharArray;

        if (ciag(key.length()) < text.length()) {
            return "Klucz ktory podales jest za krotki, lub tekst ktory podales jest za dlugi";
        }
        for (int i = 0; i < keyCharArray.length; i++) {
            keyCharArray[i] = key.charAt(i);
        }

        sortedKeyCharArray = keyCharArray.clone();
        Arrays.sort(sortedKeyCharArray);

        int[] arrayOfIndexes = new int[keyCharArray.length];
        char[] tmpKeyCharArray;
        tmpKeyCharArray = keyCharArray.clone();
        // zapisuje kolejnosc indexow
        for (int i = 0; i < keyCharArray.length; i++) {
            for (int j = 0; j < sortedKeyCharArray.length; j++) {
                if (sortedKeyCharArray[i] == tmpKeyCharArray[j]) {
                    tmpKeyCharArray[j] = 0;
                    arrayOfIndexes[i] = j;
                    break;
                }
            }
        }
        //sprawdza czy tablica nie ma wiecej liter niz text
        int textSizeCounter = 0;
        boolean textSizeChecker = false;
        int index = 0;

        for (int i = 0; i < column; i++) {
            if (textSizeChecker) {
                break;
            }
            for (int j = 0; j <= arrayOfIndexes[i]; j++) {
                if (index < text.length()) {
                    if (textSizeCounter >= text.length()) {
                        textSizeChecker = true;
                        break;
                    }
                    textCharArray[i][j] = text.charAt(index++);
                    // System.out.print(textCharArray[i][j]);
                    textSizeCounter++;
                }

            }
            //System.out.println();
        }

        for (int i = 0; i < column; i++) {
            index = arrayOfIndexes[i];
            for (int j = 0; j < row; j++) {
                stringBuilder.append(textCharArray[j][index]);
            }
        }
        encryptedText = String.valueOf(stringBuilder);
        return encryptedText;

    }

    public String przestawienieMacierzowe2cDecryption(String text, String key) {
        decryptedText = "";
        StringBuilder stringBuilder = new StringBuilder();
        double column = key.length();
        double row = column;
        char[][] textCharArray = new char[(int) row][(int) column];
        char[] keyCharArray = new char[key.length()];
        char[] sortedKeyCharArray;
        if (encryptedText.equals("")) {
            return "brak textu";
        }
        //string -> char
        for (int i = 0; i < keyCharArray.length; i++) {
            keyCharArray[i] = key.charAt(i);
        }
        //sklonowanie tablicy charow
        sortedKeyCharArray = keyCharArray.clone();
        //sortowanie liter
        Arrays.sort(sortedKeyCharArray);


        //tablica przechowujaca indexy pokolei posorotowanych liter
        int[] arrayOfIndexes = new int[keyCharArray.length];
        char[] tmpKeyCharArray;
        tmpKeyCharArray = keyCharArray.clone();
        // zapisuje kolejnosc indexow
        for (int i = 0; i < keyCharArray.length; i++) {
            for (int j = 0; j < sortedKeyCharArray.length; j++) {
                if (sortedKeyCharArray[i] == tmpKeyCharArray[j]) {
                    tmpKeyCharArray[j] = 0;
                    arrayOfIndexes[i] = j;
                    break;
                }
            }
        }
        int index = 0;
        int index2 = 0;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                textCharArray[j][arrayOfIndexes[index]] = text.charAt(index2++);

            }
            index++;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                stringBuilder.append(textCharArray[i][j]);
            }
        }

        decryptedText = String.valueOf(stringBuilder);
        return decryptedText;
    }

    public String caesarEncryption(String text, int key) {
        text = text.toUpperCase();
        StringBuilder encryptedTextBuilder = new StringBuilder();
        int firstLetter = 65;
        int lastLetter = 90;
        int tmpValue;
        for (int i = 0; i < text.length(); i++) {
            //jezeli wieksze od Z to
            if (text.charAt(i) + key > lastLetter) {
                tmpValue = text.charAt(i) + key; // y = 89 -> 89 + 3 =  92
                tmpValue = tmpValue - lastLetter; // 92 - 90 = 2
                tmpValue = firstLetter + tmpValue - 1; //65 + 2= 67 - 1 = 66
                encryptedTextBuilder.append((char) (tmpValue));
            } else
                encryptedTextBuilder.append((char) (text.charAt(i) + key));
        }
        return encryptedTextBuilder.toString();
    }

    public String caesarDecryption(String text, int key) {
        text = text.toUpperCase();
        StringBuilder encryptedTextBuilder = new StringBuilder();
        int firstLetter = 65;
        int lastLetter = 90;
        int tmpValue;
        for (int i = 0; i < text.length(); i++) {
            //jezeli mniejsze od A to
            if (text.charAt(i) - key < firstLetter) {
                tmpValue = text.charAt(i) - key; // 66 - 3 = 63
                tmpValue = firstLetter - tmpValue; // 65 - 63 = 2
                tmpValue = lastLetter - tmpValue + 1; // 90 - 2 = 89
                encryptedTextBuilder.append((char) (tmpValue));
            } else
                encryptedTextBuilder.append((char) (text.charAt(i) - key));
        }
        return encryptedTextBuilder.toString();
    }

    public String vigenereEncryption(String text, String key) {
        //sprawdzam czy text jawny jest dluzszy od klucza, jezeli tak to duplikuje klucz
        key = keyDuplicator(text, key).toUpperCase();
        text = text.toUpperCase();
        int firstLetter = 65;

        StringBuilder encryptedTextBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encryptedTextBuilder.append((char) (firstLetter + ((text.charAt(i)) + key.charAt(i)) % 26));
        }
        return encryptedTextBuilder.toString();
    }

    public String vigenereDecryption(String text, String key) {
        //sprawdzam czy text jawny jest dluzszy od klucza, jezeli tak to duplikuje klucz
        key = keyDuplicator(text, key).toUpperCase();
        text = text.toUpperCase();
        int firstLetter = 65;

        StringBuilder encryptedTextBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encryptedTextBuilder.append((char) (firstLetter + ((text.charAt(i)) - key.charAt(i) + 26) % 26));
        }

        return encryptedTextBuilder.toString();
    }

    long ciag(int n) {
        long suma = 0;
        for (int i = 1; i < n + 1; i++) {
            suma += i;
        }
        return suma;
    }

    public String keyDuplicator(String text, String key) {
        int diff = 0;
        //sprawdzam czy text jawny jest dluzszy od klucza, jezeli tak to obliczam roznice
        if (text.length() > key.length()) {
            diff = text.length() - key.length();
        }
        // petla dodajaca tyle liter do klucza tyle co wynosi roznica ich dlugosci
        StringBuilder keyBuilder = new StringBuilder(key);
        for (int i = 0; i < diff; i++) {
            keyBuilder.append(keyBuilder.charAt(i));
        }

        return keyBuilder.toString();
    }
}

