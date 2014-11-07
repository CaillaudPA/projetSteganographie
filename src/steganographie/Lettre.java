package steganographie;

import classeStatic.*;

public class Lettre{
	private Object message;

	public Lettre(String cheminAcces){
		this.message = GestionFichier.fichierEnObject(cheminAcces);
	}

	public Object getLettre(){
		return message;
	}
}

//javac -d bin -cp bin src/steganographie/*.java