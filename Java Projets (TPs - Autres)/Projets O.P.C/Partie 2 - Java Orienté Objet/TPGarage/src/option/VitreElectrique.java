/**
 *
 * @author Bernardin Houessou
 */

package option;

/**
 * Classe "VitreElectrique" implémentant l'interface "Option"
 */
public class VitreElectrique implements Option{
	private Double prix;
	private TypeOption type;
	
	public VitreElectrique(){
		this.prix=212.35d;
		this.type = TypeOption.VITREELECTRIQUE;
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
