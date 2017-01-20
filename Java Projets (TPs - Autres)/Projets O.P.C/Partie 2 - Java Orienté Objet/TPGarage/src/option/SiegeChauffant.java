/**
 *
 * @author Bernardin Houessou
 */

package option;

/**
 * Classe "SiegeChauffant" implémentant l'interface "Option"
 */
public class SiegeChauffant implements Option{
	private Double prix;
	private TypeOption type;
	
	public SiegeChauffant(){
		this.prix=562.9d;
		this.type = TypeOption.SIEGECHAUFFANT;
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
