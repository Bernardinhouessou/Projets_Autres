/**
 *
 * @author Bernardin Houessou
 */

package option;

/**
 * Classe "BarreDeToit" implémentant l'interface "Option"
 */
public class BarreDeToit implements Option{
	private Double prix;
	private TypeOption type;
	
	public BarreDeToit(){
		this.prix=29.9d;
		this.type = TypeOption.BARREDETOIT;
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
