/**
 *
 * @author Bernardin Houessou
 */

package moteur;

/**
 * Enumération "TypeMoteur" 
 */
public enum TypeMoteur {
    
	//Objets représentant le type de moteur
	DIESEL("DIESEL"),
	ELECTRIQUE("ELECTRIQUE"),
	ESSENCE("ESSENCE"),
	HYBRIDE("HYBRIDE");
	
	private String nom = "";
	
	//Constructeur
	TypeMoteur(String nom){
		this.nom = nom;
	}
	
	public String toString(){
		return nom;
	}
}