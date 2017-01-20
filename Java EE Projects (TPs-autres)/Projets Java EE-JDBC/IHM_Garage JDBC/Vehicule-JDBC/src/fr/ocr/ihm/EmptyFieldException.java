package fr.ocr.ihm;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EmptyFieldException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7542861382808347049L;

	public EmptyFieldException(String err) {
		super(err);
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(showDialog(), err, "Champs obligatoire manquant", JOptionPane.ERROR_MESSAGE);		
	}
	
	/**
	 * 
	 * @return
	 */
	private JFrame showDialog() {
		JFrame jF = new JFrame();
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jF.setAlwaysOnTop(true);
		jF.setLocation(100, 100);
		jF.pack();
		jF.setLocationRelativeTo(null);
		return jF;
	}
	
}
