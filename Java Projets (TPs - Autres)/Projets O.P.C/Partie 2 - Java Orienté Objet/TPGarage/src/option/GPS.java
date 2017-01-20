/**
 *
 * @author Bernardin Houessou
 */

package option;

/**
 * Classe "GPS" implémentant l'interface "Option"
 */
public class GPS implements Option{
	private Double prix;
	private TypeOption type;
	
	public GPS(){
		this.prix=113.5d;
		this.type = TypeOption.GPS;
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
