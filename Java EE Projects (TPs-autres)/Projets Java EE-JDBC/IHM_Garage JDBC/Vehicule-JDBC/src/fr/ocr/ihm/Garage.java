package fr.ocr.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import fr.ocr.ihm.listener.NewVehiculeListener;
import fr.ocr.ihm.listener.ViewMenuListener;
import fr.ocr.sql.DatabaseTable;

/**
 * 
 * @author XXX
 *
 */
public class Garage extends JFrame {

	private static final long serialVersionUID = -9035930054045026263L;

	// Les diff�rents objets de IHM
	private JMenuBar bar = new JMenuBar();
	private JMenu menuVehicule = new JMenu("Vehicule");
	private JMenuItem menuVehiculeAjouter = new JMenuItem("Ajouter");
	private JMenuItem menuVehiculeVoir = new JMenuItem("Voir");

	private JMenu menuMarque = new JMenu("Marque");
	private JMenuItem menuMarqueVoir = new JMenuItem("Voir");

	private JMenu menuMoteur = new JMenu("Moteur");
	private JMenuItem menuMoteurVoir = new JMenuItem("Voir");

	private JMenu menuOption = new JMenu("Option");
	private JMenuItem menuOptionVoir = new JMenuItem("Voir");

	private JMenu menuTypemoteur = new JMenu("Type de moteur");
	private JMenuItem menuTypemoteurVoir = new JMenuItem("Voir");

	public Garage() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("- MON GARAGE -");
		this.setSize(840, 400);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setBackground(Color.blue);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("ressources/icone-car.png"));

		// Tableau des v�hicules (avec fonctionalit� de TRI sur les ent�tes des colonnes)
		this.getContentPane().add(new JScrollPane(SortTable.getTableSorted(DatabaseTable.VEHICULE)), BorderLayout.CENTER);
		this.setLocationRelativeTo(null);
		initMenu();
	}

	/**
	 * Initialisation du Menu
	 */
	private void initMenu() {
		menuVehicule.add(menuVehiculeVoir);
		menuVehicule.add(menuVehiculeAjouter);
		menuVehiculeAjouter.addActionListener(new NewVehiculeListener(this));
		menuVehiculeAjouter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menuVehiculeVoir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menuVehiculeVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.VEHICULE));
		menuVehicule.setMnemonic('v');

		menuMarque.add(menuMarqueVoir);
		menuMarque.setMnemonic('a');
		menuMarqueVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.MARQUE));

		menuMoteur.add(menuMoteurVoir);
		menuMoteur.setMnemonic('m');
		menuMoteurVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.MOTEUR));

		menuOption.add(menuOptionVoir);
		menuOption.setMnemonic('o');
		menuOptionVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.OPTION));

		menuTypemoteur.add(menuTypemoteurVoir);
		menuTypemoteur.setMnemonic('t');
		menuTypemoteurVoir.addActionListener(new ViewMenuListener(this, DatabaseTable.TYPEMOTEUR));

		bar.add(menuVehicule);
		bar.add(menuMarque);
		bar.add(menuMoteur);
		bar.add(menuOption);
		bar.add(menuTypemoteur);

		this.setJMenuBar(bar);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Garage g = new Garage();
		g.setVisible(true);
	}

}
