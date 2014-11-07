package classeStatic;

public class Compresser{
	
	public static Object compression(Object objectACompresser){
		Object aRetourner = null;
		try{
			GestionFichier.objectEnFichier("fichierTemporaire/fichierACompresserTemporaire",objectACompresser);
			Runtime.getRuntime().exec("gzip -9 fichierTemporaire/fichierACompresserTemporaire");
			//attente de compression
			Thread.sleep(1000);
			aRetourner = GestionFichier.fichierEnObject("fichierTemporaire/fichierACompresserTemporaire.gz");
			Runtime.getRuntime().exec("rm fichierTemporaire/fichierACompresserTemporaire.gz");
		} catch (Exception e) {
			System.out.println(e);
		}
		return aRetourner;
	}

	public static Object decompression(Object objectADecompresser){
		Object aRetourner = null;
		try{
			GestionFichier.objectEnFichier("fichierTemporaire/fichierADecompresserTemporaire.gz",objectADecompresser);
			Runtime.getRuntime().exec("gzip -d fichierTemporaire/fichierADecompresserTemporaire.gz");
			//attente de décompression
			Thread.sleep(1000);
			aRetourner = GestionFichier.fichierEnObject("fichierTemporaire/fichierADecompresserTemporaire");
			Runtime.getRuntime().exec("rm fichierTemporaire/fichierADecompresserTemporaire");
		} catch (Exception e) {
			System.out.println(e);
		}
		return aRetourner;
	}
}

//javac -d bin -cp bin src/classeStatic/*.java