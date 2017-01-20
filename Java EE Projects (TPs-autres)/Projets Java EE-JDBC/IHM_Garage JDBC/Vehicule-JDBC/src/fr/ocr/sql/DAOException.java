package fr.ocr.sql;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652868337064812965L;

	@SuppressWarnings("static-access")
	public DAOException(String mess) {
		super(mess);
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(displayDialog(), mess, "Erreur de recherche dans le DAO", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Boite de dialog
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
