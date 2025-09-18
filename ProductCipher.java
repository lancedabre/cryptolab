import java.util.Arrays;
import java.util.Scanner;
public class ProductCipher {
private static int charToNum(char c)
{ return c - 'A';
}
private static char numToChar(int n)
{ return (char) (n + 'A');
}
private static String repeatOrTrim(String key, int length)
{ StringBuilder sb = new StringBuilder();
int keyLen = key.length();
for (int i = 0; i < length; i++)
{ sb.append(key.charAt(i %
keyLen));
}

return sb.toString();
}
private static int[] getOrder(String transKey) {

int n = transKey.length();
int[] order = new int[n];
Integer[] digits = new Integer[n];
for (int i = 0; i < n; i++) {
digits[i] = transKey.charAt(i) - '0';
}
Integer[] indices = new Integer[n];
for (int i = 0; i < n; i++) indices[i] = i;
Arrays.sort(indices, (a, b) -> digits[a] - digits[b]);
for (int rank = 0; rank < n; rank++) {
order[indices[rank]] = rank + 1;
}
return order;
}
public static String productEncrypt(String plaintext, String vigKey, String transKey)
{ plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
vigKey = vigKey.toUpperCase().replaceAll("[^A-Z]", "");
transKey = transKey.trim();
String repeatedKey = repeatOrTrim(vigKey, plaintext.length());
StringBuilder vigenereText = new StringBuilder();
for (int i = 0; i < plaintext.length(); i++)
{ int p = charToNum(plaintext.charAt(i));
int k = charToNum(repeatedKey.charAt(i));
int c = (p + k) % 26;
vigenereText.append(numToChar(c));
}
int n = transKey.length();
int[] order = getOrder(transKey);
String vText = vigenereText.toString();
if (vText.length() < n) {
StringBuilder padded = new StringBuilder(vText);
while (padded.length() < n) {
padded.append('X');
}
vText = padded.toString();
}

StringBuilder ciphertext = new StringBuilder();

for (int rank = 1; rank <= n; rank++)
{ for (int col = 0; col < n; col++) {
if (order[col] == rank)
{ ciphertext.append(vText.charAt(col));
break;
}
}
}
return ciphertext.toString();
}
public static String productDecrypt(String ciphertext, String vigKey, String transKey)
{ ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");
vigKey = vigKey.toUpperCase().replaceAll("[^A-Z]", "");
transKey = transKey.trim();
int n = transKey.length();
int[] order = getOrder(transKey);
char[] transposed = new char[n];
for (int rank = 1, idx = 0; rank <= n; rank++) {
for (int col = 0; col < n; col++) {
if (order[col] == rank) {
transposed[col] = ciphertext.charAt(idx++);
break;
}
}
}
StringBuilder vigenereText = new StringBuilder();
for (char c : transposed) {
vigenereText.append(c);
}
String repeatedKey = repeatOrTrim(vigKey, vigenereText.length());
StringBuilder plaintext = new StringBuilder();
for (int i = 0; i < vigenereText.length(); i++)
{ int c =
charToNum(vigenereText.charAt(i)); int k =
charToNum(repeatedKey.charAt(i)); int p =

(c - k + 26) % 26;
plaintext.append(numToChar(p));
}
return plaintext.toString();

}
public static void main(String[] args)
{ Scanner scanner = new
Scanner(System.in);
System.out.println("Enter plaintext:");
String plaintext = scanner.nextLine();
System.out.println("Enter Vigenere key:");
String vigKey = scanner.nextLine();
System.out.println("Enter transposition key (digits only, e.g., 43125):");
String transKey = scanner.nextLine();
String cipher = productEncrypt(plaintext, vigKey, transKey);
System.out.println("Encrypted text: " + cipher);
String decrypted = productDecrypt(cipher, vigKey, transKey);
System.out.println("Decrypted text: " + decrypted);
scanner.close();
}
}