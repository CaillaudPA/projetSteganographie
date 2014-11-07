package steganographie;

import classeStatic.*;

public class Enveloppe{
	private Object enveloppe;

	public Enveloppe(String cheminAcces){
		this.enveloppe = GestionFichier.fichierEnObject(cheminAcces);
	}

	public Object getEnveloppe(){
		return enveloppe;
	}
}
//javac -d bin -cp bin src/steganographie/*.java