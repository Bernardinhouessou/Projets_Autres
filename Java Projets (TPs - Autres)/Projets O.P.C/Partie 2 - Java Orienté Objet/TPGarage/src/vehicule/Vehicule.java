/**
 *
 * @author Bernardin Houessou
 */

package vehicule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import moteur.Moteur;
import option.Option;

/**
 * Class "Vehicule" impléméntant "Serializable"
 */
public class Vehicule implements Serializable {
	protected Double prix;
	protected String nom;
	protected List<Option> options;
	protected Marque nomMarque;
	protected Moteur moteur;
	
	/***
	 * Constructeur par défaut de la classe Vehicule
	 */
	public Vehicule() {
		this.prix=0.0d;
		this.options = new ArrayList<Option>();
	}

	//*******  ACCESSEURS *****
	
	/**
	 * @return la marque
	 */
	public Marque getMarque() {
		return this.nomMarque;
	}


	/**
	 * @return les options
	 */
	public List<Option> getOptions() {
		return this.options;
	}

	/**
	 * @return le moteur
	 */
	public Moteur getMoteur() {
		return this.moteur;
	}

	/**
	 * @return le prix
	 */
	public Double getPrix() {
		
		this.prix=0.0d;
		for(Option op : options){
			this.prix+=op.getPrix();
		}
		
		//J'ajoute le prix du moteur
		this.prix+=moteur.getPrix();
		
		return this.prix;
	}
	
	
	/**
	 * @return le nom du véhicule
	 */
	public String getNom() {
		return this.nom;
	}

	//******* MUTATEURS *****

	/**
	 * @param moteur le moteur à définir
	 */
	public void setMoteur(Moteur moteur) {
		this.moteur = moteur;
	}
	
	
	/**
	 *Ajoute une option au véhicule
	 */
	public void addOption(Option opt) {
		this.options.add(opt);
	}

	/**
	 * @param prix le prix à définir
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}


	/**
	 * @param nom le nom à définir
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @param nomMarque le nom de la Marque à définir
	 */
	public void setNomMarque(Marque nomMarque) {
		this.nomMarque = nomMarque;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		
		//Charge les caractéristiques de la voiture
		str = "+ Voiture " + this.nomMarque + " : " + this.nom + " " + this.moteur.toString() + " [";
		
		//Charge les options de la voiture
		for(Option op : options){
			str+=op.toString();
		}
		
		str+=" ] d'une valeur totale de " + this.getPrix() + " €";
		return  str;
	}
	
}
