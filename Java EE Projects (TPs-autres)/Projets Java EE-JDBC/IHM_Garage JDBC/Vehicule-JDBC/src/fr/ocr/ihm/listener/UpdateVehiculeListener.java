package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.voiture.Vehicule;
import fr.ocr.ihm.DialogUpdateVehicule;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.DAOVehicule;


/**
 * 
 * @author XXX
 *
 */
public class UpdateVehiculeListener extends ButtonListener implements ActionListener{
	
	private Integer id;

	public void actionPerformed(ActionEvent e) {

		// Récupérer ID de véhicule
		id = Integer.valueOf(jTable.getValueAt(row, 4).toString());

		Thread t = new Thread(new Runnable() {
			public void run() {
				// Récupérer objet Véhicule
				Vehicule vehicle = new DAOVehicule(HsqldbConnection.getInstance()).find(id);
				
				JFrame frame = (JFrame) jTable.getParent()
												.getParent() // Va retourner le JPanel contenant le JScrollPane
												.getParent() // Va retourner le JLayeredPane contenant le JPanel
												.getParent() // Va retourner le JRootPane contenant le JLayeredPane
												.getParent().getParent(); // Va retourner notre JFrame;				
				// Afficher la fenêtre en mode modale
				new DialogUpdateVehicule(frame, "Modification d'un véhicule", true).showDialog(vehicle);
			}
		});
		t.start();
	}
}
