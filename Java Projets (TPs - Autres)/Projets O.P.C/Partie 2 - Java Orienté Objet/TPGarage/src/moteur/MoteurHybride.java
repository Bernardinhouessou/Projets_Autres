/**
 *
 * @author Bernardin Houessou
 */

package moteur;

/**
 * Class "MoteurHybride" héritant de "Moteur"
 */
public class MoteurHybride extends Moteur{

	public MoteurHybride(){
		
	}
	
	public MoteurHybride(String cylindre, Double prix){
		this.type = TypeMoteur.HYBRIDE;
		this.cylindre = cylindre;
		this.prix=prix;
	}
}
