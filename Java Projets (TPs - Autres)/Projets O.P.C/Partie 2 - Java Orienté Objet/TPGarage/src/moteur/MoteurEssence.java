/**
 *
 * @author Bernardin Houessou
 */

package moteur;

/**
 * Class "MoteurEssence" héritant de "Moteur"
 */
public class MoteurEssence extends Moteur{

	public MoteurEssence(){
		
	}
	
	public MoteurEssence(String cylindre, Double prix){
		this.type = TypeMoteur.ESSENCE;
		this.cylindre = cylindre;
		this.prix=prix;
	}
}
