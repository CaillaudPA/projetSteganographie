package steganographie.test_JUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import steganographie.Stegano_image;

public class Stegano_imageTest {

	@Test
	public void testDissimulerDonnee() {
		//Stegano_image test = new Stegano_image("texteACacher","imageTest.jpg");
		//test.dissimulerDonnee(1);
	}

	@Test
	public void testDevoilerDonnee() {
		fail("Not yet implemented");

	}

	@Test
	public void testVerificationComptabilite() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecupererBit() {
		Stegano_image test = new Stegano_image("","");
		byte tmp = 0x47; //= 0100 0111
		System.out.println(test.recupererBit(tmp,3));
		
	}

	@Test
	public void testDecallerBit() {
		Stegano_image test = new Stegano_image("","");
		int tmp = 0x47; //= 0100 0111=71
		System.out.println(test.decallerBit(tmp));
		//0x46= 0100 0110=70
	}
	
	@Test
	public void testIntEnQuatreOctets() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInsererTableau() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testOctetsEnInteger() {
		Stegano_image test = new Stegano_image("","");
		byte[] tmp = {0,0,0,127};
		System.out.println("test OctetsEnInteger: "+test.octetsEnInteger(tmp));
	}

}
