package pl.pb;

public class Cryptography {
    private String encryptedText = "";
    private String decryptedText = "";

    public Cryptography() {
    }

    public String railFenceEncryption(String text, int key) {

        boolean direction = false;
        int currentRow = 0;
        int row = key;
        int column = text.length();


        char[][] a = new char[row][column];

        for (int i = 0; i < column; i++) {
            if (currentRow == 0 || currentRow == key - 1) {
                direction = !direction;
            }
            a[currentRow][i] = text.charAt(i);

            if (direction) currentRow++;
            else currentRow--;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(a[i][j]);
                encryptedText += a[i][j];
            }
            System.out.println();
        }
        return encryptedText;
    }
}

