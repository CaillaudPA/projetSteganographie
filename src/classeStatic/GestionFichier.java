package classeStatic;

import java.util.LinkedList;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.io.DataInputStream;

public class GestionFichier{
	//convertie un fichier en object java
	public static Object fichierEnObject(String cheminAcces) {
		LinkedList<Byte> tmp = new LinkedList<Byte>();
		Object aRetourner = null;
		try {
			FileInputStream fichierIn = new FileInputStream(cheminAcces);
			DataInputStream in = new DataInputStream(fichierIn);
			
			byte octetActuel = -1;
			try{
				octetActuel = in.readByte();
			}catch(Exception e){}

			while(octetActuel != -1){
				tmp.add(octetActuel);
				try{
					octetActuel = in.readByte();
				}catch(Exception e){
					octetActuel = -1;
				}
			}
			aRetourner = GestionFichier.creationObject(tmp);
			fichierIn.close();
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return aRetourner;
	}


	//convertis un object java en fichier 
	public static void objectEnFichier(String cheminDeDestination, Object objetAMettreEnfichier){
		try{
			FileOutputStream fichier = new FileOutputStream(cheminDeDestination);
			try{
				fichier.write((byte[])objetAMettreEnfichier);
			}catch(ClassCastException e){
				System.out.println("l'objet doit pouvoir se convertir en flux binaire");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//méthode permetant de convertir une linkedList en object en prenants uniquement les octets présent dans la liste
	private static Object creationObject(LinkedList<Byte> liste){
		Object aRetourner = null;
		byte[] tmp = new byte[liste.size()];
		for (int i = 0; i<	liste.size();i++ ) {
			tmp[i] = liste.get(i);
		}
		aRetourner = (Object)(tmp);
		return aRetourner;
	}

	//calcul le nombre d'octet dans un Object
	public static int nbrOctetsDansObject(Object o){
		LinkedList<Byte> tmp = new LinkedList<Byte>();
		GestionFichier.objectEnFichier("fichierTemporaire/NbrOctetsDansObject", o);
		try {
			FileInputStream fichierIn = new FileInputStream("fichierTemporaire/NbrOctetsDansObject");
			byte octetActuel = (byte)fichierIn.read();
			while(octetActuel != -1){
				tmp.add(octetActuel);
				octetActuel = (byte)fichierIn.read();
			}
			fichierIn.close();
			Runtime.getRuntime().exec("rm fichierTemporaire/NbrOctetsDansObject");
		} catch (Exception e) {
			System.out.println(e);
		}
		return tmp.size();
	}
}

//javac -d bin -cp bin src/classeStatic/*.java