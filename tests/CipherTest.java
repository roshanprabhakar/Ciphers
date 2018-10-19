import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CipherTest {

	@Test
	public void rotationCipherEncryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3, Cipher.ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "wkh!txlfn!eurzq!irA!mxpshg!ryhu!wkh!odCB!grjv";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3, Cipher.ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "MAxeJNBvDeuKHPGeyHQeCNFIxweHOxKeMAxeEtSRewHzL";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100, Cipher.ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "MAxeJNBvDeuKHPGeyHQeCNFIxweHOxKeMAxeEtSRewHzL";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100, Cipher.ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherEncryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\nQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHcTXLFNcEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 3, Cipher.ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}
	
	@Test
	public void rotationCipherDecryptBy3CapsWithPunctuation() {
		String plaintext = "\"THE\nQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "]WKHcTXLFNcEURZQ!IR0 !MXPSHG!RYHU!WKH!OD21!GRJV/]";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 3, Cipher.ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherDecryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\nQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "g(0Xs9)1V3sU,7'6eY7\"be2)58XWe7 X,e(0Xe4T[!eW7Z.hg";
		String testPlainText = Cipher.rotationCipherDecrypt(correctCipherText, 100, Cipher.ALPHABET);
		assertEquals(testPlainText, plaintext);
	}
	
	@Test
	public void rotationCipherEncryptBy100CapsWithPunctuation() {
		String plaintext = "\"THE\nQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "g(0Xs9)1V3sU,7'6eY7\"be2)58XWe7 X,e(0Xe4T[!eW7Z.hg";
		String testCipherText = Cipher.rotationCipherEncrypt(plaintext, 100, Cipher.ALPHABET);
		assertEquals(testCipherText, correctCipherText);
	}

	@Test
	public void vigenereCipherEncryptWith3LetterPassword() {
		String plaintext = "the quick brown fox jumped over the lazy dogs";
		String correctCipherText = "2w)uFaRr]uq=XL_uu;6a[3B?NsUXK)0a\nQtUUpf7a(Xv:";
		String testCipherText = Cipher.vigenereCipherEncrypt(plaintext, "Jp9", Cipher.ALPHABET);
		assertEquals(correctCipherText, testCipherText);
	}

	@Test
	public void vigenereCipherEncryptWithLongerPassword() {
		String plaintext = "\"THE\nQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String correctCipherText = "(EeNbfX.6]2czwSyLOQm o)amqml,zsNTDW,8ulbHG,olPUG]";
		String testCipherText = Cipher.vigenereCipherEncrypt(plaintext, "- 0jcSdDEJ34..", Cipher.ALPHABET);
		assertEquals(correctCipherText, testCipherText);
	}

	@Test
	public void vigenereCipherDecryptWith3LetterPassword() {
		String correctPlainText = "the quick brown fox jumped over the lazy dogs";
		String cipherText = "2w)uFaRr]uq=XL_uu;6a[3B?NsUXK)0a\nQtUUpf7a(Xv:";
		String testPlainText = Cipher.vigenereCipherDecrypt(cipherText, "Jp9", Cipher.ALPHABET);
		assertEquals(correctPlainText, testPlainText);
	}

	@Test
	public void vigenereCipherDecryptWithLongerPassword() {
		String correctPlainText = "\"THE\nQUICK\nBROWN FOX. JUMPED OVER THE LAZY DOGS!\"";
		String cipherText = "(EeNbfX.6]2czwSyLOQm o)amqml,zsNTDW,8ulbHG,olPUG]";
		String testPlainText = Cipher.vigenereCipherDecrypt(cipherText, "- 0jcSdDEJ34..", Cipher.ALPHABET);
		assertEquals(correctPlainText, testPlainText);
	}
}
