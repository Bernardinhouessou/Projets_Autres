/**
 *
 * @author Bernardin Houessou
 */

package garage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import  vehicule.Vehicule;

public class Garage {
	private final String fichier = "garage.txt";                  // Nom de mon fichier contenant les infos de garages... 
	private List<Vehicule> voitures = new ArrayList<Vehicule>(); //  Liste contenant les véhicules... 

	/**
	 * Constructeur par défaut du Garage
	 */
	public Garage(){
		
		lireFichierSauvegarde();
	}

	/**
         * Méthode "addVoiture()"
	 * Ajoute une voiture au garage
	 * @param voit
	 */
	public void addVoiture(Vehicule voit){

		//J'ajoute une voiture à ma liste et j'enregistre ma liste dans le fichier de sauvegarde
		this.voitures.add(voit);
		this.sauverFichierSauver();
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str ="";
		str = "*****************************\n";
		str += "*   Garage OpenClassRooms   *\n";
		str +="*****************************\n";

		for(Vehicule v : voitures){
			str += v.toString() + "\n";
		}
		return str;
	}

	/***
	 * Méthode privée  "sauverFichierSauver()"  pour enregistrer le fichier de sauvegarde
	 */
	private void sauverFichierSauver(){
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier))){
			oos.writeObject(voitures);
			oos.close();
		} catch (FileNotFoundException e) {
			// Erreur relevée si le fichier de sauvegarde est inexistant
			System.err.println("Fichier de sauvegarde inexistant !");
		} catch (IOException e) {
			// Erreur relevée si un problème d'écriture ce produit
			System.err.println("Erreur d'écriture dans le fichier !");
		}
				
	}
	
	/***
	 * Méthode "lireFichierSauvegarde()" pour lire le fichier de sauvegarde
	 */
	private void lireFichierSauvegarde() {
		//J'essai d'ouvrir mon fichier.
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier))){
			
			//Je lis le contenu du fichier qui contient la liste des voitures et je ferme l'accès à mon fichier
			this.voitures=(List<Vehicule>)ois.readObject();
			ois.close();
			
		}catch (FileNotFoundException e){
			//Erreur relevée si le fichier n'existe pas
			System.err.println("Aucune voiture sauvegardée !\n");
		}catch (IOException e) {
			// Erreur relevée si l'accès au fichier en lecture pose problème
			System.err.println("Erreur lors de l'accès au fichier");
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}

}
