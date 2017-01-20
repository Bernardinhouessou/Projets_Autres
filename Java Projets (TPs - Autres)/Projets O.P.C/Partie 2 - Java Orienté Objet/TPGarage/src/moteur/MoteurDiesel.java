/**
 *
 * @author Bernardin Houessou
 */

package moteur;

/**
 * Class "MoteurDiesel" héritant de "Moteur"
 */
public class MoteurDiesel extends Moteur{

	public MoteurDiesel(){
		
	}
	
	public MoteurDiesel(String cylindre, Double prix){
		this.type = TypeMoteur.DIESEL;
		this.cylindre = cylindre;
		this.prix=prix;
	}
}
