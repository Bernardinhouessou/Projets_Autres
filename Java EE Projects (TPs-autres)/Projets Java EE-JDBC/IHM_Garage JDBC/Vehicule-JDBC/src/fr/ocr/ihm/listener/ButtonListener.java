package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.voiture.Vehicule;

import fr.ocr.ihm.SortTable;
import fr.ocr.sql.HsqldbConnection;
import fr.ocr.sql.DAOVehicule;
import fr.ocr.sql.DatabaseTable;

/**
 * Listener pour le bouton
 * 
 * @author XXX
 *
 */
public class ButtonListener implements ActionListener {

	protected int column, row, id;
	protected JTable jTable;

	public void setColumn(int col) {
		this.column = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTable(JTable table) {
		this.jTable = table;
	}

	public void actionPerformed(ActionEvent event) {

		// Récupérer ID du véhicule
		id = Integer.valueOf(jTable.getValueAt(row, 4).toString());
		
		// Demande la confirmation de suppression
		String strQuestion = "Voulez-vous vraiment supprimer ce véhicule n°" + id + " ?";
		int result = JOptionPane.showConfirmDialog(displayDialog(), strQuestion, "Confirmation", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {

			Thread t = new Thread(new Runnable() {
				public void run() {
					// Récupérer objet Véhicule
					Vehicule vehicle = new DAOVehicule(HsqldbConnection.getInstance()).find(id);

					// Si OK suppresion -> update JTable
					if (new DAOVehicule(HsqldbConnection.getInstance()).delete(vehicle)) {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								// Récupérer les objets parents d'un composant, le JFrame depuis le JTable
								JFrame frame = (JFrame) jTable.getParent() // Va retourner le JScrollPane contenant le JTable
										.getParent() // Va retourner le JPanel contenant le JScrollPane
										.getParent() // Va retourner le JLayeredPane contenant le JPanel
										.getParent() // Va retourner le JRootPane contenant le JLayeredPane
										.getParent().getParent(); // Va retourner notre JFrame;

								frame.getContentPane().removeAll();
								// Mise à jour du tableau
								frame.getContentPane().add(new JScrollPane(SortTable.getTableSorted(DatabaseTable.VEHICULE)), BorderLayout.CENTER);
								frame.getContentPane().revalidate();
							}
						});
					}
				}
			});
			t.start();
		} else {

			System.out.println("Annuler la suppression");
		}
	}

	/**
	 * Boite de dialog pour la confirmation pour suppression
	 * 
	 * @return
	 */
	private JFrame displayDialog() {
		JFrame jF = new JFrame();
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setAlwaysOnTop(true);
		jF.setLocation(100, 100);
		jF.pack();
		jF.setLocationRelativeTo(null);
		return jF;
	}

}