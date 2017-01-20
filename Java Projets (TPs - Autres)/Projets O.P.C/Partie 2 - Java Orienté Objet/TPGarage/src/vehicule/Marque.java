/**
 *
 * @author Bernardin Houessou
 */
package vehicule;

/**
 *Enumération "Marque"
 */
public enum Marque {
	//Objets représentant la marque du Véhicule
	RENO("RENO"),
	PIGEOT("PIGEOT"),
	TROEN("TROEN");
		
	private String nom = "";
		
	//Constructeur
	Marque(String nom){
		this.nom = nom;
	}
		
	public String toString(){
		return nom;
	}
}
