package steganographie;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class Stegano_image extends Steganographie{

	public Stegano_image(String lettre, String enveloppe){
		super(lettre,enveloppe);
	}

	//i le numeros de l'algo a utilisé
	//1= remplacer le bit de poids faible de chaque couleur
	public void dissimulerDonnee(int i){

		if (i = 1 && this.verificationComptabilite()) {
			Image img = ImageIO.read(new File(this.enveloppe.getEnveloppe()));
			BufferedImage enveloppe = (BufferedImage)img;

			byte[] lettre = (byte[])GestionFichier.fichierEnObject(this.lettre.getLettre());



			int[] tableau;
			Color tmpCouleur;
			for (int curseurOctet = 0; curseurOctet< GestionFichier.nbrOctetsDansObject(lettre) ;curseurOctet++ ) {
				for (int y=0;y<enveloppe.getHeight() ;y++ ) {
					for (int x=0;x<enveloppe.getWidth() ;x++ ) {
						tmpCouleur = new Color(enveloppe.getRGB(x,y));
						for (int curseurBit = 0;curseurBit<8 ; curseurBit+=4) {
							
						}
					}
				}	
			}
		}

	}
	//mettre en entrer la position du bit que l'on souhaite récupérer 
	//retourner true si le bit voulue correspond a 1 sinon 0
	private boolean recupererBit(byte octet, int position){
		byte tmp = octet;
		tmp >> position;
		tmp%2
		if(tmp == 1){
			return true;
		}
		return false;

    }


	//i le numéros de l'algo utilise lorsque le message a été cacher
	public void devoilerDonnee(int i){

	}

	public boolean verificationComptabilite(){
		try{

			Image img = ImageIO.read(new File(this.enveloppe.getEnveloppe()));
			BufferedImage envTest = (BufferedImage)img;
		}catch(Exception e){
			System.out.println("l'enveloppe désigner n'est pas une image");		
		}
		return true;
	}
	


}