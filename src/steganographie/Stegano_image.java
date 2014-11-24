package steganographie;

import classeStatic.*;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Stegano_image extends Steganographie{

	public Stegano_image(String lettre, String enveloppe){
		super(lettre,enveloppe);
	}

	//i le numeros de l'algo a utilisé
	//1= remplacer le bit de poids faible de chaque couleur
	public void dissimulerDonnee(int i){
		if ((i == 1) && (this.verificationComptabilite()) ) {
			try{
				BufferedImage enveloppe = ImageIO.read(new File(this.enveloppe.getEnveloppe()));

				byte[] lettre = GestionFichier.fichierEnFlux(this.lettre.getLettre());

				Color tmpCouleur;
				int curseurOctet = 0;
		      		 
				for (int y=0;y<enveloppe.getHeight() ;y++ ) {
					for (int x=0;x<enveloppe.getWidth() ;x++ ) {			 
		      			for (int curseurBit = 0;curseurBit<8 ; curseurBit++) {
		      				
		      				tmpCouleur = new Color(enveloppe.getRGB(x,y));
		      				int red = this.decallerBit(tmpCouleur.getRed());
		      				int green = this.decallerBit(tmpCouleur.getGreen());
		      				int blue = this.decallerBit(tmpCouleur.getBlue());
		      				int alpha = this.decallerBit(tmpCouleur.getAlpha());
		      				System.out.println(curseurOctet);
		      				
		      				red += this.recupererBit(lettre[curseurOctet],curseurBit+0);i++;
		      				green += this.recupererBit(lettre[curseurOctet],curseurBit+1);i++;
		      				blue += this.recupererBit(lettre[curseurOctet],curseurBit+2);i++;
		      				alpha += this.recupererBit(lettre[curseurOctet],curseurBit+3);

		      				
		      				Color newColor = new Color(red,green,blue,alpha);
		      				int rgb = newColor.getRGB();

		      				enveloppe.setRGB(x,y,rgb);
		      				x++;
		      				if(x == enveloppe.getWidth()){
		      					break;
		      				}
		      			}
		      			curseurOctet++;
		      			if(curseurOctet==lettre.length){
		      				x = enveloppe.getHeight();
		      				y = enveloppe.getWidth();
		      				break;
		      			}
					}
				}
				
				//on r�cup�re l'extension du nom de l'enveloppe
				String[] tmpString = this.enveloppe.getEnveloppe().split("\\.");
				
				File outputfile = new File("imageAvecMessageCacher.jpg");
				ImageIO.write(enveloppe, tmpString[1], outputfile);
			}catch(Exception e){
				System.out.println("erreur: "+e.toString());
			}
		}
	}
	
	public int recupererBit(int octet, int position){
		int tmp;
		tmp = (octet >> position);
	   	tmp = (octet%2);
	   	if(tmp == 1){
	   		return 1;
	   	}else{
	   		return 0;
	   	}
	}

	public int decallerBit(int octet){
		int tmp;
		tmp =octet>>>1;
		tmp =tmp<<1;
		return tmp;
	}
	
	
	//convertie un int en un tableau de 4 octets (= 32 bits)
	//byte[0] correspond aux bits de poid faible
	public byte[] intEnQuatreOctets(int a ){
		return ByteBuffer.allocate(4).putInt(a).array();
	}
	
	public byte[] insererTableau(byte[] debut,byte[] fin){
		ArrayList<Byte> tmp = new ArrayList<Byte>();
		for(int i = 0;i<fin.length;i++){
			tmp.add(fin[i]);
		}
		
		for(int j =0;j<debut.length;j++){
			tmp.add(debut[j]);
		}
		
		byte[] aRetourner= new byte[tmp.size()];
		for(int k =0;k<tmp.size();k++){
			aRetourner[k] = tmp.get(k);
		}		
		return aRetourner;
	}

	//octets[0] correspond au bits de poid faible
	public int octetsEnInteger(byte[] octets){
		int aRetourner = 0;
		if(octets.length==4){
			aRetourner = aRetourner | (octets[0]);
			aRetourner = aRetourner | (octets[1]*0xFF);
			aRetourner = aRetourner | (octets[2]*0xFFFF);
			aRetourner = aRetourner | (octets[3]*0xFFFFFF);
			
		}else{
			System.out.println("un int tien sur 32 bits au maximum soit un tableau de 4 byte");
		}
		return aRetourner;
	}
	
	
	
	
	public boolean verificationComptabilite(){
		boolean aRetourner = false;
		try{

			Image img = ImageIO.read(new File(this.enveloppe.getEnveloppe()));
			BufferedImage envTest = (BufferedImage)img;
			int nbrPixel = envTest.getHeight()*envTest.getWidth();
			if( (nbrPixel/4) >= GestionFichier.fichierEnFlux(this.lettre.getLettre()).length ){
				String[] tmpString = this.enveloppe.getEnveloppe().split("\\.");
				if (tmpString.length ==2 ){
					aRetourner = true;
				}else{
					System.out.println("le nom de l'enveloppe doit contenir l'extension pr�c�der d'un points");
				}
				
				
			}else{
				System.out.print("la lettre est trop grande par rapport a l'enveloppe");
			}
		}catch(Exception e){
			System.out.println("l'enveloppe d�signer n'est pas une image");		
		}
		return aRetourner;
	}

	@Override
	public void devoilerDonnee(int i) {
		// TODO Auto-generated method stub
		
	}

}