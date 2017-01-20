package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fr.ocr.ihm.SortTable;
import fr.ocr.sql.DatabaseTable;

/**
 * Classe permettant de g�rer l'affichage des diff�rentes tables de la BDD en
 * fonction du menu cliqu�
 * 
 * @author XXX
 */
public class ViewMenuListener implements ActionListener {
	DatabaseTable table;
	JFrame frame;

	public ViewMenuListener(JFrame f, DatabaseTable tab) {
		table = tab;
		frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		frame.getContentPane().removeAll();
		frame.setTitle("- MON GARAGE - " + table.name());
		frame.getContentPane().add(new JScrollPane(SortTable.getTableSorted(table)), BorderLayout.CENTER);
		frame.getContentPane().revalidate();
	}
}
