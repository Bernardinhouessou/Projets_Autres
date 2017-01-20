package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import fr.ocr.ihm.DialogAddVehiculeNew;

/**
 * 
 * @author XXX
 *
 */
public class NewVehiculeListener implements ActionListener {

	private JFrame frame;
	
	/**
	 * 
	 * @param f
	 */
	public NewVehiculeListener(JFrame f) {
		frame = f;
	}
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		DialogAddVehiculeNew dialog = new DialogAddVehiculeNew(frame, "Ajouter une nouvelle voiture", true);
		dialog.showDialog();
	}
}
