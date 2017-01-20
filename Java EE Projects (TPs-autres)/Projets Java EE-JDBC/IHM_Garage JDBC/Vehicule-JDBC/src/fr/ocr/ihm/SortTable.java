/**
 * 
 */
package fr.ocr.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import fr.ocr.sql.DAOTableFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;

/**
 * Tri des tables
 * 
 * @author XXX
 * 
 * @return JTable triable
 */
public class SortTable {

	static JTable jTable = new JTable();

	public static JTable getTableSorted(DatabaseTable pTable) {

		// Récupérer les données des véhicules vers une JTable
		jTable = DAOTableFactory.getTable(HsqldbConnection.getInstance(), pTable);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.setAutoCreateRowSorter(true);

		// Model de tri
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable.getModel());
		jTable.setRowSorter(sorter);
		final List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		// Définition des colonnes pour le tri en fonction des tables
		if (pTable.equals(DatabaseTable.VEHICULE)) {
			int columnIndexToSort = 4; // Tri Asc par défaut sur la colonne ID
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			sorter.setSortKeys(sortKeys);
			sorter.setSortable(5, false); // désactiver le tri pour la colonne DETAIL
			sorter.setSortable(6, false); // désactiver le tri pour la colonne MODIFIER
			sorter.setSortable(7, false); // désactiver le tri pour la colonne SUPPRIMER
			sorter.sort();
		} else {
			int columnIndexToSort = 0; // Tri Asc par défaut sur la colonne ID
			sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
			sorter.setSortKeys(sortKeys);
			sorter.sort();
		}
		return jTable;
	}
}
