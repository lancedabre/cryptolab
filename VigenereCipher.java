import java.util.Scanner;
public class VigenereCipher {
public static String encrypt(String plaintext, String key)
{ StringBuilder result = new StringBuilder();
plaintext = plaintext.toUpperCase();
key = key.toUpperCase();
for (int i = 0, j = 0; i < plaintext.length(); i++)
{ char c = plaintext.charAt(i);
if (c < 'A' || c > 'Z')
{ result.append(c);
} else {
int encryptedChar = ((c - 'A') + (key.charAt(j) - 'A')) % 26;
result.append((char) (encryptedChar + 'A'));
j = (j + 1) % key.length();
}
}
return result.toString();
}
public static String decrypt(String ciphertext, String key)
{ StringBuilder result = new StringBuilder();
ciphertext = ciphertext.toUpperCase();
key = key.toUpperCase();
for (int i = 0, j = 0; i < ciphertext.length(); i++)
{ char c = ciphertext.charAt(i);
if (c < 'A' || c > 'Z') {


result.append(c);
} else {
int decryptedChar = ((c - 'A') - (key.charAt(j) - 'A') + 26) % 26;
result.append((char) (decryptedChar + 'A'));
j = (j + 1) % key.length();
}
}
return result.toString();
}
public static void main(String[] args)
{ Scanner scanner = new
Scanner(System.in);
System.out.println("= Vigenere Cipher =");
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