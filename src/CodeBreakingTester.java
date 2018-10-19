import javax.xml.bind.annotation.XmlType;

public class CodeBreakingTester {

	public static void main(String[] args) {
		String cipherText, plainText, password;

		// -========== CRACK CIPHER 1 ===========-
		// cipherText1.txt was encoded with a rotation cipher
		// -=====================================-
//
//		cipherText = Cipher.loadFileAsString("text/cipherText1.txt");
//		System.out.println("Cipher text 4:\n" + cipherText);
////
////		int rotAmount = Cipher.bruteForceCrackRotation(cipherText);
////		System.out.println("Rotation amount is " + rotAmount);
//
//		String key = Cipher.decodeLengthN(cipherText, 2);
//
//		System.out.println("KEY IS: " + key);

		// -========== CRACK CIPHER 2 ===========-
		// cipherText2.txt was encoded with a 2-letter password
		// ---------------------------------------
//
//		cipherText = Cipher.loadFileAsString("text/cipherText2.txt");
//		System.out.println("Cipher text 2:\n" + cipherText);
//
//		password = Cipher.bruteForceCrackLength2Password(cipherText);
//		System.out.println("CRACKED! Rotation amount is " + password);
//
//		plainText = Cipher.vigenereCipherDecrypt(cipherText, password, Cipher.ALPHABET);
//
//		System.out.println("PLAINTEXT IS: " + plainText);

		// -========== CRACK CIPHER 3 ===========-
		// cipherText3.txt was encoded with a 3-letter password
		// ---------------------------------------
//
//		cipherText = Cipher.loadFileAsString("text/cipherText3.txt");
//		System.out.println("Cipher text 3:\n" + cipherText);
//
//		password = Cipher.bruteForceCrackLength3Password(cipherText);
//		System.out.println("CRACKED! Rotation amount is " + password);
//
//		plainText = Cipher.vigenereCipherDecrypt(cipherText, password, Cipher.ALPHABET);
//
//		System.out.println("PLAINTEXT IS: " + plainText);

		// -========== CRACK CIPHER 4 ===========-
		// cipherText4.txt was encoded with a 8-letter password
		// ---------------------------------------

//		cipherText = Cipher.loadFileAsString("text/cipherText4.txt");
//		System.out.println("Cipher text 4:\n" + cipherText);
//
//		password = Cipher.crackLength8Password(cipherText);
//		System.out.println("CRACKED! Rotation amount is " + password);
//
//		plainText = Cipher.vigenereCipherDecrypt(cipherText, password, Cipher.ALPHABET);
//
//		System.out.println("PLAINTEXT IS: " + plainText);

		// -========== CRACK CIPHER 5 ===========-
		// cipherText4.txt was encoded with a password of unknown length
		// ---------------------------------------

		cipherText = Cipher.loadFileAsString("text/cipherText5.txt");
		System.out.println("Cipher text 5:\n" + cipherText);

		password = Cipher.crackPassword(cipherText);
		System.out.println("CRACKED! Rotation amount is " + password);

		plainText = Cipher.vigenereCipherDecrypt(cipherText, password, Cipher.ALPHABET);

		System.out.println("PLAINTEXT IS: " + plainText);
	}
}