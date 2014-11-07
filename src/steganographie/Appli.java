package steganographie;
import classeStatic.*;

public class Appli{
	public static void main(String[] args){
		//test récupération d'un fichier
		Object testRecupFichier = GestionFichier.fichierEnObject("fichierTemporaire/testRécupérerFichier");
		byte[] tmp1 = (byte[])testRecupFichier;
		for (int i = 0; i < tmp1.length ;i++) {
			System.out.print(tmp1[i]+" ");
		}
		System.out.println("\n");
 
 		//test écriture d'un fichier
 		// t,e,s,t,d,d,d,d,d,d ... en ascii
		byte[] testObjectEnFichier = {116,101,115,116,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
		//int[] testObjectEnFichier = {116,101,115,116,100,100,100,100,100,100,100,100,100,100,100,100,100};
		GestionFichier.objectEnFichier("fichierTemporaire/objetEnfichier",(Object)testObjectEnFichier);
		
		Object testCompresson = (byte[])GestionFichier.fichierEnObject("fichierTemporaire/aaa");
		//compression des octets
		System.out.println("avant compression: nombre d'octets "+GestionFichier.nbrOctetsDansObject(testCompresson));
		testCompresson = Compresser.compression(testCompresson);
		System.out.println("apres compression: nombre d'octets "+GestionFichier.nbrOctetsDansObject(testCompresson));

		//decompression
		testCompresson = Compresser.decompression(testCompresson);
		System.out.println("apres decompression: nombre d'octets "+GestionFichier.nbrOctetsDansObject(testCompresson));

	}
}

//javac -d bin -cp bin src/steganographie/*.java
//java -cp bin steganographie.Appli