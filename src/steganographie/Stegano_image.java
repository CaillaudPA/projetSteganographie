package steganographie;

import classeStatic.*;

import javax.imageio.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Stegano_image extends Steganographie{


	private static final byte[] octetsReserver={0,0};//16 72 96 144 192
	private static final int tailleReserverDebut=octetsReserver.length*8;
	private static final int tailleBitsLongueurLettre=32;
	private static final int tailleTotalReserver = tailleBitsLongueurLettre+tailleReserverDebut;//doit etre divisible par 3 et 8
	
	public Stegano_image(String lettre, String enveloppe){
		super(lettre,enveloppe);
	}

	//i le numeros de l'algo a utilisé
	//1= remplacer le bit de poids faible de chaque couleur
	public void dissimulerDonnee(int i){
		if ((i == 1) && (this.verificationComptabilite(this.enveloppe.getEnveloppe())) ) {
			try{
				BufferedImage enveloppe = ImageIO.read(new File(this.enveloppe.getEnveloppe()));				
				byte[] lettre = GestionFichier.fichierEnFlux(this.lettre.getLettre());
				
				//ajout de 16 bits car 16+32 = 48 et 48 est un nombre commun dans la table de mutliplication de 6 et 8
				lettre = this.insererTableau(intEnQuatreOctets(lettre.length+(tailleTotalReserver/8)),lettre);
				lettre = this.insererTableau(octetsReserver,lettre);
				int[] lettreEnSuiteDeBits = this.bytesEnInt(lettre);
				
				
				int curseurBit = 0;
				for(int y = 0;y<enveloppe.getHeight();y++){
					for (int x=0;x<enveloppe.getWidth();x++ ) {			 
						Color tmpCouleur = new Color(enveloppe.getRGB(x,y));
			      		int red = this.decallerBit(tmpCouleur.getRed());
			      		int green = this.decallerBit(tmpCouleur.getGreen());
			      		int blue = this.decallerBit(tmpCouleur.getBlue());
			      		
			      		red += this.recupererBit(lettreEnSuiteDeBits[curseurBit],0);			      			
			      		
			      		curseurBit++;
	      				if(curseurBit==lettreEnSuiteDeBits.length){
				      		Color newColor = new Color(red,tmpCouleur.getGreen(),tmpCouleur.getBlue());
			      			enveloppe.setRGB(x,y,newColor.getRGB());
	      					x = enveloppe.getWidth();
	      					y = enveloppe.getHeight();
	      					break;
	      				}
	      				
			      		green += this.recupererBit(lettreEnSuiteDeBits[curseurBit],0);
			      		
			      		curseurBit++;
	      				if(curseurBit==lettreEnSuiteDeBits.length){
				      		Color newColor = new Color(red,green,tmpCouleur.getBlue());
			      			enveloppe.setRGB(x,y,newColor.getRGB());
	      					x = enveloppe.getWidth();
	      					y = enveloppe.getHeight();
	      					break;
	      				}
	      					
			      		blue += this.recupererBit(lettreEnSuiteDeBits[curseurBit],0);
			      		
			      		Color newColor = new Color(red,green,blue);
		      			enveloppe.setRGB(x,y,newColor.getRGB());
		      			curseurBit++;
		      			
		      			Color testCouleur = new Color(enveloppe.getRGB(x,y));
		      			
		      			System.out.println(testCouleur.getRed()+"||"+testCouleur.getGreen()+"||"+testCouleur.getBlue()+"//"+(curseurBit));
		      			if(curseurBit==lettreEnSuiteDeBits.length){
	      					x = enveloppe.getWidth();
	      					y = enveloppe.getHeight();
		      			}
					}
				}

				System.out.println("taille Lettre: "+lettreEnSuiteDeBits.length);
				

				//on r�cup�re l'extension du nom de l'enveloppe
				String[] tmpString = this.enveloppe.getEnveloppe().split("\\.");
				//tmpString[0] correspond au nom du fichier, tmpString[1] a l'extension du fichier
				File outputfile = new File("imageAvecMessageCacher."+tmpString[1]);
				ImageIO.write(enveloppe, tmpString[1], outputfile);
			}catch(Exception e){
				System.out.println("erreur: "+e.toString());
			}
		}
	}
	
	public int recupererBit(int octet, int position){
		return (octet << 31-position)>>>31;
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
		byte[] tmp = ByteBuffer.allocate(4).putInt(a).array();
		byte[] aRetourner = new byte[4];
		aRetourner[0] = tmp[3];
		aRetourner[1] = tmp[2];
		aRetourner[2] = tmp[1];
		aRetourner[3] = tmp[0];
		return aRetourner;
	}
	
	public byte[] insererTableau(byte[] debut,byte[] fin){
		ArrayList<Byte> tmp = new ArrayList<Byte>();
		for(int i = 0;i<debut.length;i++){
			tmp.add(debut[i]);
		}
		
		for(int j =0;j<fin.length;j++){
			tmp.add(fin[j]);
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
		for(int i =octets.length-1;i>=0;i--){
			aRetourner = (aRetourner << 8)| octets[i];
		}
		return aRetourner;
	}
	
	public boolean verificationComptabilite(String cheminAccesATester){
		boolean aRetourner = false;
		try{
			Image img = ImageIO.read(new File(cheminAccesATester));
			BufferedImage envTest = (BufferedImage)img;
			int nbrPixel = envTest.getHeight()*envTest.getWidth();
			if( (nbrPixel/4) >= GestionFichier.fichierEnFlux(this.lettre.getLettre()).length ){
				String[] tmpString = cheminAccesATester.split("\\.");
				if (tmpString.length ==2 ){
					aRetourner = true;
				}else{
					System.out.println("le nom de l'enveloppe doit contenir l'extension pr�c�der d'un points");
				}
			}else{
				System.out.print("la lettre est trop grande par rapport a l'enveloppe");
			}
		}catch(Exception e){
			System.out.println("l'enveloppe d�signer n'est pas une image ou la lettre n'existe pas");	
			System.out.println(e);	
		}
		return aRetourner;
	}

	@Override
	public void devoilerDonnee(int i,String cheminEnveloppe,String PathLettre) {
		if(i==1){
			try{
				BufferedImage enveloppe = ImageIO.read(new File(cheminEnveloppe));				
				Color tmpColor = null;
				int red = 0;
				int green = 0;
				int blue = 0;
				
				
				
				int curseurBits=0;
				int[] tailleLettreBit = new int[tailleBitsLongueurLettre];
				int[] partieInurilise = new int[tailleReserverDebut];
				for(int y = 0;y<enveloppe.getHeight();y++){
					for(int x = 0;x<enveloppe.getWidth();x++){
						tmpColor = new Color(enveloppe.getRGB(x, y));
						red = tmpColor.getRed();
						green = tmpColor.getGreen();
						blue = tmpColor.getBlue();
						
						if(curseurBits<tailleReserverDebut){
							partieInurilise[curseurBits] = this.recupererBit(red,0); 
							curseurBits++;
						}else if(curseurBits>=tailleReserverDebut && curseurBits<tailleTotalReserver){
							tailleLettreBit[curseurBits-tailleReserverDebut] = this.recupererBit(red,0); 
							curseurBits++;
						}else{
							x = enveloppe.getWidth();
							y = enveloppe.getHeight();
							break;
						}
						
						if(curseurBits<tailleReserverDebut){
							partieInurilise[curseurBits] = this.recupererBit(green,0); 
							curseurBits++;
						}else if(curseurBits>=tailleReserverDebut && curseurBits<tailleTotalReserver){
							tailleLettreBit[curseurBits-tailleReserverDebut] = this.recupererBit(green,0); 
							curseurBits++;
						}else{
							x = enveloppe.getWidth();
							y = enveloppe.getHeight();
							break;
						}
						
						if(curseurBits<tailleReserverDebut){
							partieInurilise[curseurBits] = this.recupererBit(blue,0); 
							curseurBits++;
						}else if(curseurBits>=tailleReserverDebut && curseurBits<tailleTotalReserver){
							tailleLettreBit[curseurBits-tailleReserverDebut] = this.recupererBit(blue,0); 
							curseurBits++;
						}else{
							x = enveloppe.getWidth();
							y = enveloppe.getHeight();
							break;
						}
					}
				}
				int tailleLettre = (octetsEnInteger(intEnBytes(tailleLettreBit))-(tailleTotalReserver/8));
				int partieInutile = octetsEnInteger(intEnBytes(partieInurilise));
				System.out.println("partie inutilisé: "+partieInutile);
				System.out.println("taille de la lettre: "+tailleLettre);
				
				curseurBits=0;
				int[] fichierCacherBits = new int[tailleLettre*8];
				for(int y = 0;y<enveloppe.getHeight();y++){
					for(int x = 0;x<enveloppe.getWidth();x++){
						tmpColor = new Color(enveloppe.getRGB(x, y));
						red = tmpColor.getRed();
						green = tmpColor.getGreen();
						blue = tmpColor.getBlue();
						
						if(curseurBits<tailleLettre*8+tailleTotalReserver){
							if(curseurBits>=tailleTotalReserver){
								fichierCacherBits[curseurBits-tailleTotalReserver] = this.recupererBit(red,0); 
							}
							curseurBits++;
						}else{
	      					x = enveloppe.getWidth();
	      					y = enveloppe.getHeight();
	      					break;
						}
						
						if(curseurBits<tailleLettre*8+tailleTotalReserver){
							if(curseurBits>=tailleTotalReserver){
								fichierCacherBits[curseurBits-tailleTotalReserver] = this.recupererBit(green,0); 
							}
							curseurBits++;
						}else{
	      					x = enveloppe.getWidth();
	      					y = enveloppe.getHeight();
	      					break;
						}
						
						if(curseurBits<tailleLettre*8+tailleTotalReserver){
							if(curseurBits>=tailleTotalReserver){
								fichierCacherBits[curseurBits-tailleTotalReserver] = this.recupererBit(blue,0); 
							}
							curseurBits++;
						}else{
	      					x = enveloppe.getWidth();
	      					y = enveloppe.getHeight();
	      					break;
						}
					}
				}
				byte[] fichierCacher = intEnBytes(fichierCacherBits);
				

				for(int ii = 0;ii<fichierCacher.length;ii++){
					System.out.println("test: "+fichierCacher[ii]+" numeros de l'octets: "+ii);
				}
				System.out.println(tailleLettre+6);
				GestionFichier.fluxEnFichier(PathLettre, fichierCacher);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	

	public int[] bytesEnInt(byte[] octets){
		int aRetourner[] = new int[octets.length*8];
		int curseurInt = 0;
		for(int i = 0;i<octets.length;i++){
			for(int j=0;j<8;j++){
				aRetourner[curseurInt] = recupererBit(octets[i],j);
				curseurInt++;
			}
		}
		return aRetourner;
	}
	
	public byte[] intEnBytes(int[] intable){
		byte[] aRetourner=new byte[intable.length/8];
		int curseurInt = 0;
		
		byte tmp = 0;
		for(int curseurOctet = 0;curseurOctet<aRetourner.length;curseurOctet++){
			for(int curseurBits=0;curseurBits<8;curseurBits++){				 
				tmp = (byte) (tmp + (byte) (intable[curseurInt]*Math.pow(2,curseurBits)));
				curseurInt++;
			}
			aRetourner[curseurOctet] = tmp;
			tmp=0;
		}		
		return aRetourner;
		
	}

	

}