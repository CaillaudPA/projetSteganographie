package classeStatic;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class GestionFichier{
	//convertie un fichier en flux Binaire
	public static byte[] fichierEnFlux(String cheminAcces) {
		FileInputStream fileInputStream=null;
        File file = new File(cheminAcces);
        byte[] bFile = new byte[(int) file.length()];
 
        try {
		    fileInputStream = new FileInputStream(file);
		    fileInputStream.read(bFile);
		    fileInputStream.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
        return bFile;
	}



	//convertis un object java en fichier 
	public static void fluxEnFichier(String cheminDeDestination,byte[] sauvegarde){
		try{
			FileOutputStream fichier = new FileOutputStream(cheminDeDestination);
			fichier.write(sauvegarde);
			fichier.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static boolean ecrire(String path, String texte)
	{
		try
		{
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter output = new BufferedWriter(fw);
			output.write(texte);	
			output.flush();	
			output.close();
			System.out.println("fichier rempli");
			return true;
		}
		catch(IOException ioe){
			System.out.print("Erreur : ");
			ioe.printStackTrace();
			return false;
		}

	}
	
	public static boolean creerFichier(String path,String nomfichier){
		try{
			java.io.File monFichier = new java.io.File(path+nomfichier);
			monFichier.createNewFile(); 
			System.out.println("Fichie cree");
			return true;
		  }
		  catch (IOException e ){
		  		return false;
		  } 
	}
	
	
	public static String lireFichier(String cheminDeDestination) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(cheminDeDestination));
		String line="";
		line = in.readLine();
		in.close();
		return line.trim();
	}
	
	//affiche toute les valeur d'un tableau de byte
	public static String toStringBytes(byte[] octets){
		String aRetourner = "[";
		for(int i = 0;i<octets.length;i++){
			if (i == 0){
				aRetourner += " "+octets[i];
			}else{
				aRetourner += ", "+octets[i];
			}
		}
		
		aRetourner += "]";
		return aRetourner;
	}
}
