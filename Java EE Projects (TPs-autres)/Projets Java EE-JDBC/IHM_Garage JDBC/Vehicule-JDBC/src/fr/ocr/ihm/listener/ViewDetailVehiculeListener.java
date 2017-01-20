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

		// R�cup�rer ID de v�hicule
		id = Integer.valueOf(jTable.getValueAt(row, 4).toString());

		Thread t = new Thread(new Runnable() {
			public void run() {
				// R�cup�rer objet V�hicule
				Vehicule vehicle = new DAOVehicule(HsqldbConnection.getInstance()).find(id);

				// Afficher la fen�tre en mode modale
				new DialogViewVehiculeDetail(null, "D�tail d'un v�hicule", true).showDialog(vehicle);
			}
		});
		t.start();
	}
}
