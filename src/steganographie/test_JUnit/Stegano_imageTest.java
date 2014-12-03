package steganographie.test_JUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import steganographie.Stegano_image;

public class Stegano_imageTest {

	@Test
	public void testDissimulerDonnee() {
		Stegano_image test = new Stegano_image("messageACacher.txt","imageTest2.png");
		test.dissimulerDonnee(1);
	}

	@Test
	public void testDevoilerDonnee() {
		Stegano_image test = new Stegano_image("","");
		test.devoilerDonnee(1,"imageAvecMessageCacher.png","MessageDevoiler");

	}

	@Test
	public void testVerificationCompabilite() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecupererBit() {
		Stegano_image test = new Stegano_image("","");
		int tmp = 0xFFFFFFFF; //= 1111 1111
		System.out.println(test.recupererBit(tmp,32));
		
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
		Stegano_image test = new Stegano_image("","");
		
		System.out.println(test.intEnQuatreOctets(9999)[0]);
		System.out.println(test.intEnQuatreOctets(9999)[1]);
		System.out.println(test.intEnQuatreOctets(9999)[2]);
		System.out.println(test.intEnQuatreOctets(9999)[3]);//<-bits de poid faible
		//9999 = 100111 00001111 <- bits de poid faible
	}
	
	@Test
	public void testInsererTableau() {
		Stegano_image test = new Stegano_image("","");
		byte[] tmp1 = {1,2};
		byte[] aMettreDevants = {100};
		byte[] TableauModifier= test.insererTableau(aMettreDevants,tmp1);
		System.out.println(TableauModifier[0]);// 100
		System.out.println(TableauModifier[1]);// 1
		System.out.println(TableauModifier[2]);// 2
	}
	
	@Test
	public void testOctetsEnInteger() {
		Stegano_image test = new Stegano_image("","");
		byte[] tmp = test.intEnQuatreOctets(9999);
		System.out.println("test OctetsEnInteger: "+test.octetsEnInteger(tmp));
	}
	
	@Test
	public void testBytesEnInt(){
		Stegano_image test = new Stegano_image("","");
		byte[] test2 = {-1};
		int[] test3 =test.bytesEnInt(test2);
		for(int i =0;i<test3.length;i++){
			System.out.println(test3[i]);
		}
		
	}
	
	@Test
	public void testIntEnBytes(){
		Stegano_image test = new Stegano_image("","");
		byte[] test2 = {125,-35,125,-50};
		int[] test3 =test.bytesEnInt(test2);//test.bytesEnInt(test2);
		byte[] tmp = test.intEnBytes(test3);
		
		System.out.println(tmp[0]);
		System.out.println(tmp[1]);
		System.out.println(tmp[2]);
		System.out.println(tmp[3]);
		
	}

}
