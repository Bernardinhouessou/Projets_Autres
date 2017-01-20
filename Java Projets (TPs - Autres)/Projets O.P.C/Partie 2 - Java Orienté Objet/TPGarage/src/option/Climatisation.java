/**
 *
 * @author Bernardin Houessou
 */

package option;

/**
 * Classe "Climatisation" implémentant l'interface "Option"
 */
public class Climatisation implements Option{
	private Double prix;
	private TypeOption type;
	
	public Climatisation(){
		this.prix=347.3d;
		this.type = TypeOption.CLIMATISATION;
	}
	@Override
	public Double getPrix() {
		// TODO Auto-generated method stub
		return prix;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " " + this.type + " (" + this.prix + " €)";
	}

}
