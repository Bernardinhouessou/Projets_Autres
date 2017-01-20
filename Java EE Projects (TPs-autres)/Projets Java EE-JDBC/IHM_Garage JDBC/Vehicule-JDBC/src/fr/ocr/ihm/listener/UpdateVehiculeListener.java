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

		// R�cup�rer ID de v�hicule
		id = Integer.valueOf(jTable.getValueAt(row, 4).toString());

		Thread t = new Thread(new Runnable() {
			public void run() {
				// R�cup�rer objet V�hicule
				Vehicule vehicle = new DAOVehicule(HsqldbConnection.getInstance()).find(id);
				
				JFrame frame = (JFrame) jTable.getParent()
												.getParent() // Va retourner le JPanel contenant le JScrollPane
												.getParent() // Va retourner le JLayeredPane contenant le JPanel
												.getParent() // Va retourner le JRootPane contenant le JLayeredPane
												.getParent().getParent(); // Va retourner notre JFrame;				
				// Afficher la fen�tre en mode modale
				new DialogUpdateVehicule(frame, "Modification d'un v�hicule", true).showDialog(vehicle);
			}
		});
		t.start();
	}
}
