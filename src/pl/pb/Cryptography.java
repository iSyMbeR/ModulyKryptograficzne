package pl.pb;


import java.util.Arrays;

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
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        return encryptedText;
    }

    public String railFenceDecryption(String text, int key) {
        decryptedText = "";
        System.out.println(text.length());
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
                if (currentRow == 0 || currentRow == key - 1) direction = !direction;
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
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < row; i++) {
            for (int k : intKey) {
                encryptedText += a[i][k - 1];
            }
        }
        System.out.println(encryptedText);
        return encryptedText;
    }

    public String przestawienieMacierzowe2aDecryption(String text, String key) {
        decryptedText = "";

        String[] tmpKey = key.split("-");
        int[] intKey = new int[tmpKey.length];

        for (int i = 0; i < tmpKey.length; i++) {
            intKey[i] = Integer.parseInt(String.valueOf(tmpKey[i].charAt(0)));
        }

//        //reverse array
//        for (int i = 0; i < intKey.length / 2; i++) {
//            int temp = intKey[i];
//            intKey[i] = intKey[intKey.length - 1 - i];
//            intKey[intKey.length - 1 - i] = temp;
//        }

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
            System.out.println();
        }
        System.out.println();



        for (int i = 0; i < row; i++) {
            index = 0;
            for (int k = 0; k < intKey.length; k++) {
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
        System.out.println(decryptedText);
        return decryptedText;
    }

}

