package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import com.voiture.Vehicule;

import fr.ocr.ihm.DialogViewVehiculeDetail;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.DAOVehicule;

/**
 * 
 * @author XXX
 *
 */
public class ViewDetailVehiculeListener extends ButtonListener {
	
	private Integer id;

	public void actionPerformed(ActionEvent e) {

		// Récupérer ID de véhicule
		id = Integer.valueOf(jTable.getValueAt(row, 4).toString());

		Thread t = new Thread(new Runnable() {
			public void run() {
				// Récupérer objet Véhicule
				Vehicule vehicle = new DAOVehicule(HsqldbConnection.getInstance()).find(id);

				// Afficher la fenêtre en mode modale
				new DialogViewVehiculeDetail(null, "Détail d'un véhicule", true).showDialog(vehicle);
			}
		});
		t.start();
	}
}
