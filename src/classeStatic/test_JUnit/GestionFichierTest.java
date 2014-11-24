package classeStatic.test_JUnit;

import static org.junit.Assert.*;

import org.junit.Test;

import classeStatic.GestionFichier;

public class GestionFichierTest {

	

	@Test
	public void testFichierEnFlux() {
		byte[] testRecupFichier = GestionFichier.fichierEnFlux("fichierTemporaire/testRécupérerFichier");
		System.out.println(new String(testRecupFichier));
	}

	@Test
	public void testFluxEnFichier() {
		byte[] testFluxEnFichiers = new byte[128];
		testFluxEnFichiers[127] = 127;
		//génère tout le code ascii
		for(byte i = 0 ;i<testFluxEnFichiers.length-1;i++){
			testFluxEnFichiers[i] = i;
		}
		
		
		GestionFichier.fluxEnFichier("fichierTemporaire/testFluxEnfichier",testFluxEnFichiers);
		
		byte[] testRecupFichier = GestionFichier.fichierEnFlux("fichierTemporaire/testFluxEnfichier");
		System.out.println("test fichier en flux: "+GestionFichier.toStringBytes(testRecupFichier));
		System.out.println("code ascii: \n"+new String(testRecupFichier)+"\n\n");
	}

	@Test
	public void testEcrire() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreerFichier() {
		fail("Not yet implemented");
	}

	@Test
	public void testLireFichier() {
		fail("Not yet implemented");
	}

}
