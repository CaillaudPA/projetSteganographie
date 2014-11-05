package src;

public abstract class Steganographie<E>{

	private Lettre lettre;
	private Enveloppe<E> enveloppe;

	public Steganographie(String l, String e)
	{
		this.lettre = new Lettre(l);
		this.enveloppe = new Enveloppe<E>(e);
	}

	public abstract void dissimulerDonnee(int i);

	public abstract void devoilerDonnee(int i);

	public abstract boolean verificationComptabilite();
	

}
