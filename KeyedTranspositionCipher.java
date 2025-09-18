import java.util.Arrays;
import java.util.Scanner;
public class KeyedTranspositionCipher {
public static String encrypt(String plaintext, String key) {


plaintext = plaintext.replaceAll("\\s+", "");
key = key.toUpperCase();
int col = key.length();
int row = (int) Math.ceil((double) plaintext.length() / col); char[]
[] matrix = new char[row][col];
int k = 0;
for (int i = 0; i < row; i++)
{ for (int j = 0; j < col; j++)
{
if (k < plaintext.length()) {
matrix[i][j] = plaintext.charAt(k++);
} else {
matrix[i][j] = 'X';
}
}
}
Character[] keyChars = new Character[col];
for (int i = 0; i < col; i++) {
keyChars[i] = key.charAt(i);
}
Integer[] colOrder = new Integer[col];
for (int i = 0; i < col; i++) colOrder[i] = i;
Arrays.sort(colOrder, (a, b) -> Character.compare(keyChars[a], keyChars[b]));
StringBuilder ciphertext = new StringBuilder();
for (int c : colOrder) {
for (int r = 0; r < row; r++)
{ ciphertext.append(matrix[r][c]);
}
}
return ciphertext.toString();
}
public static String decrypt(String ciphertext, String key)
{ ciphertext = ciphertext.replaceAll("\\s+", "");
key = key.toUpperCase();

int col = key.length();
int row = (int) Math.ceil((double) ciphertext.length() / col);
char[][] matrix = new char[row][col];

Character[] keyChars = new Character[col];
for (int i = 0; i < col; i++) {
keyChars[i] = key.charAt(i);
}
Integer[] colOrder = new Integer[col];
for (int i = 0; i < col; i++) colOrder[i] = i;
Arrays.sort(colOrder, (a, b) -> Character.compare(keyChars[a], keyChars[b]));
int k = 0;
for (int c : colOrder) {
for (int r = 0; r < row; r++)
{ if (k <
ciphertext.length()) {
matrix[r][c] = ciphertext.charAt(k++);
} else {
matrix[r][c] = 'X';
}
}
}
StringBuilder plaintext = new StringBuilder();
for (int i = 0; i < row; i++) {
for (int j = 0; j < col; j++)
{ plaintext.append(matrix[i][j]);
}
}
while (plaintext.length() > 0 && plaintext.charAt(plaintext.length() - 1) == 'X')
{ plaintext.deleteCharAt(plaintext.length() - 1);
}
return plaintext.toString();
}
public static void main(String[] args)
{ Scanner scanner = new
Scanner(System.in);
System.out.println("= Keyed Transposition Cipher =");
System.out.print("Enter the plaintext: ");
String plaintext = scanner.nextLine();

System.out.print("Enter the key: ");
String key = scanner.nextLine();
String ciphertext = encrypt(plaintext, key);

System.out.println("Encrypted Ciphertext: " + ciphertext);
String decryptedText = decrypt(ciphertext, key);
System.out.println("Decrypted Plaintext: " + decryptedText);
scanner.close();
}
}