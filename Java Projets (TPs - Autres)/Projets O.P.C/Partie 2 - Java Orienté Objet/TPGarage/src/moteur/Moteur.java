/**
 *
 * @author Bernardin Houessou
 */
package moteur;

import java.io.Serializable;

/**
 * Class "Moteur" impléméntant "Serializable"
 */
public abstract class Moteur implements Serializable{
	protected TypeMoteur type;
	protected String cylindre;
	protected Double prix;

	/**
         * Méthode "getPrix()"
	 * @return le prix
	 */
	public Double getPrix() {
		return prix;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Moteur " + this.type  + " " + this.cylindre + " (" + this.prix + " €)";
	}
}
