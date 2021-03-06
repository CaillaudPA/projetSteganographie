package steganographie;

public abstract class Steganographie{

	protected Lettre lettre;
	protected Enveloppe enveloppe;

	public Steganographie(String l, String e)
	{
		this.lettre = new Lettre(l);
		this.enveloppe = new Enveloppe(e);
	}

	public abstract void dissimulerDonnee(int i,String cheminEnveloppeModifier, boolean compresserLettre,int chiffreLettre);

	public abstract void devoilerDonnee(int i,String cheminEnveloppe,String PathLettre, boolean lettreCompresser, int lettreChiffre);

	public abstract boolean verificationComptabilite(String cheminAccesATester);
}

//javac -d bin -cp bin src/steganographie/*.java