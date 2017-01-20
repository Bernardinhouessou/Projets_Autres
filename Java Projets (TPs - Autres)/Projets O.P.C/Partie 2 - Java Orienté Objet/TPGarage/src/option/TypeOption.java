/**
 *
 * @author Bernardin Houessou
 */

package option;

/**
 *Enumération "TypeOption"
 */
public enum TypeOption {
	//Objets représentant le type d'option
		GPS("GPS"),
		CLIMATISATION("Climatisation"),
		SIEGECHAUFFANT("Siège chauffant"),
		VITREELECTRIQUE("Vitre electrique"),
		BARREDETOIT("Barre de toit");
			
		private String nom = "";
			
		//Constructeur
		TypeOption(String nom){
			this.nom = nom;
		}
			
		public String toString(){
			return nom;
		}
}
