package classeStatic.test_JUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import steganographie.Stegano_image;
import classeStatic.Cryptage;

public class CryptageTest {
	//la cl� doit avoir une taille entre 8 bits et 128 bits
	private byte[] cleChiffrement = {123,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	Stegano_image tmp = new Stegano_image("", "");
	

	@Test
	public void testChiffrement() {
		
		String texteTest = "test de chiffrement";
		byte[] texteChiffrer = Cryptage.chiffrement(texteTest.getBytes(), cleChiffrement);
		System.out.println("cle chiffrer: "+new String(texteChiffrer));
	}

	@Test
	public void testDechiffrement() {
		String texteTest = "test de chiffrement";
		byte[] texteChiffrer = Cryptage.chiffrement(texteTest.getBytes(), tmp.intEnQuatreOctets(2));
		
		byte[] texteDechiffrer = Cryptage.deChiffrement(texteChiffrer, tmp.intEnQuatreOctets(3));
		System.out.println("message déchiffrer: "+new String(texteDechiffrer));
		
	}

}
