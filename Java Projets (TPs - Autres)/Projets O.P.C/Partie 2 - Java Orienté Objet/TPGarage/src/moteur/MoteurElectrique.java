/**
 *
 * @author Bernardin Houessou
 */

package moteur;

/**
 * Class "MoteurElectrique" héritant de "Moteur"
 */
public class MoteurElectrique extends Moteur{
	
	public MoteurElectrique(){
		
	}
	
	public MoteurElectrique(String cylindre, Double prix){
		this.type = TypeMoteur.ELECTRIQUE;
		this.cylindre = cylindre;
		this.prix=prix;
	}
}
