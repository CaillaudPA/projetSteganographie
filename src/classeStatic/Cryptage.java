package classeStatic;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cryptage{


	public static byte[] chiffrement(byte[] octetsAchiffrer, byte[] cle){		
		try
		{
			SecretKeySpec clef = new SecretKeySpec(cle,"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE,clef);
			return cipher.doFinal(octetsAchiffrer);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}

	}

	public static byte[] deChiffrement(byte[] octetsADechiffrer, byte[] cle){
		try
		{
			SecretKeySpec clef = new SecretKeySpec(cle,"Blowfish");
			Cipher cipher=Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE,clef);
			return cipher.doFinal(octetsADechiffrer);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}

}

//javac -d bin -cp bin src/classeStatic/*.java