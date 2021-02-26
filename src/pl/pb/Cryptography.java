package pl.pb;

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

    public void przestawienieMacierzowe2a(String text, int[] key) {
        encryptedText = "";
        double column = key.length;
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

        for (int i = 0; i < row; i++) {
            for (int k : key) {
                encryptedText += a[i][k-1];
            }
        }
        System.out.println(encryptedText);
    }


}

