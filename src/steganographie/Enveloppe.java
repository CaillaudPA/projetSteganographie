package steganographie;

public class Enveloppe{
	private String enveloppe;

	public Enveloppe(String cheminAcces){
		this.enveloppe = cheminAcces;
	}

	public String getEnveloppe(){
		return enveloppe;
	}
}
//javac -d bin -cp bin src/steganographie/*.java