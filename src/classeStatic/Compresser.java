package classeStatic;

public class Compresser{
	
	public static byte[] compression(byte[] fluxACompresser){
		byte[] aRetourner = null;
		try{
			GestionFichier.fluxEnFichier("fichierTemporaire/fichierACompresserTemporaire",fluxACompresser);
			Runtime.getRuntime().exec("gzip -9 fichierTemporaire/fichierACompresserTemporaire");
			//attente de compression
			Thread.sleep((fluxACompresser.length/1000)+1000);
			aRetourner = GestionFichier.fichierEnFlux("fichierTemporaire/fichierACompresserTemporaire.gz");
			Runtime.getRuntime().exec("rm fichierTemporaire/fichierACompresserTemporaire.gz");
		} catch (Exception e) {
			System.out.println(e);
		}
		return aRetourner;
	}

	public static byte[] decompression(byte[] fluxADecompresser){
		byte[] aRetourner = null;
		try{
			GestionFichier.fluxEnFichier("fichierTemporaire/fichierADecompresserTemporaire.gz",fluxADecompresser);
			Runtime.getRuntime().exec("gzip -d fichierTemporaire/fichierADecompresserTemporaire.gz");
			//attente de décompression
			Thread.sleep((fluxADecompresser.length/1000)+1000);
			aRetourner = GestionFichier.fichierEnFlux("fichierTemporaire/fichierADecompresserTemporaire");
			Runtime.getRuntime().exec("rm fichierTemporaire/fichierADecompresserTemporaire");
		} catch (Exception e) {
			System.out.println(e);
		}
		return aRetourner;
	}
}

//javac -d bin -cp bin src/classeStatic/*.java