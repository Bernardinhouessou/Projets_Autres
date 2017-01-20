package fr.ocr.sql;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JTable;

import fr.ocr.ihm.ButtonEditor;
import fr.ocr.ihm.ButtonRenderer;
import fr.ocr.ihm.listener.UpdateVehiculeListener;
import fr.ocr.ihm.listener.ViewDetailVehiculeListener;

/**
 * Classe permettant de créer l'objet JTable en fonction de la table afficher
 * 
 * @author XXX
 */
public class DAOTableFactory {

	/**
	 * 
	 * @param conn
	 * @param table
	 * @return jTable
	 */
	public static JTable getTable(Connection conn, DatabaseTable table) {

		JTable jTab = new JTable();

		try (Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet result = state.executeQuery("SELECT * FROM " + table);) {

			ResultSetMetaData resultMeta = result.getMetaData();
			int nbreColumn = resultMeta.getColumnCount();

			// Récupérer le nombre total de ligne
			result.last();
			int nbreRow = result.getRow();
			result.beforeFirst();

			String[] title = new String[nbreColumn];
			Object[][] data = new Object[nbreRow][nbreColumn];

			// Ajout des colonnes pour les boutons Detail, Modifier et Supprimer
			if (table.equals(DatabaseTable.VEHICULE)) {
				title = new String[nbreColumn + 3];
				title[nbreColumn] = "DETAIL";
				title[nbreColumn + 1] = "MODIFICATION";
				title[nbreColumn + 2] = "ACTION";

				data = new Object[nbreRow][nbreColumn + 3];
				for (Object[] rows : data) {
					rows[nbreColumn] = "DETAIL";
					rows[nbreColumn + 1] = "MODIFIER";
					rows[nbreColumn + 2] = "SUPPRIMER";
				}
			}

			for (int i = 0; i < nbreColumn; i++) {
				title[i] = resultMeta.getColumnName(i + 1).toUpperCase();
			}

			int nbreLine = 0;
			while (result.next()) {
				for (int i = 0; i < nbreColumn; i++) {
					data[nbreLine][i] = result.getObject(i + 1).toString();
				}
				nbreLine++;
			}

			jTab = new JTable(data, title);

			// Affichage des boutons pour la table véhicule
			if (table.equals(DatabaseTable.VEHICULE)) {
				
				// bouton Détail
				jTab.getColumn("DETAIL").setCellRenderer(new ButtonRenderer("DETAIL"));
				jTab.getColumn("DETAIL").setCellEditor(new ButtonEditor(new JCheckBox(), "DETAIL", new ViewDetailVehiculeListener()));
				
				// bouton Modifier
				ButtonRenderer btUpdate = new ButtonRenderer("MODIFIER");
				btUpdate.setBackground(Color.yellow);
				jTab.getColumn("MODIFICATION").setCellRenderer(btUpdate);
				jTab.getColumn("MODIFICATION").setCellEditor(new ButtonEditor(new JCheckBox(), "MODIFIER", new UpdateVehiculeListener()));
				
				// bouton Supprimer
				ButtonRenderer btSupp = new ButtonRenderer("SUPPRIMER");
				btSupp.setBackground(Color.red);
				jTab.getColumn("ACTION").setCellRenderer(btSupp);
				jTab.getColumn("ACTION").setCellEditor(new ButtonEditor(new JCheckBox(), "SUPPRIMER"));
			}
			jTab.setRowHeight(30);
		} catch (SQLException e) {
			new DAOException(e.getMessage());
		}

		return jTab;
	}

}
