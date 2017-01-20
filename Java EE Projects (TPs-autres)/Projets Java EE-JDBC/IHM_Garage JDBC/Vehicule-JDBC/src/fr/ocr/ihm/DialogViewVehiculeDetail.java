package fr.ocr.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.voiture.Vehicule;
import com.voiture.option.Option;

/**
 * Détails d'un véhicule
 * 
 * @author XXX
 *
 */
public class DialogViewVehiculeDetail extends JDialog {

	/**
	 * VersionID
	 */
	private static final long serialVersionUID = 8296278144071536650L;

	private JLabel jlPrice, jlPriceTotal;
	private JLabel[] jlOptionsValue;
	private Vehicule vehicle;

	/**
	 * 
	 * @param jpParent
	 * @param sTitle
	 * @param isModal
	 */
	public DialogViewVehiculeDetail(JFrame jpParent, String sTitle, boolean isModal) {
		super(jpParent, sTitle, isModal);
		this.setSize(550, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * 
	 * @param pVehicle
	 * @return
	 */
	public boolean showDialog(Vehicule pVehicle) {
		this.vehicle = pVehicle;
		this.initComponent();
		this.setVisible(true);
		return true;
	}

	/**
	 * Initialisation des composants
	 * 
	 */
	private void initComponent() {

		// POUR AJOUTER LES SEPARATEUR ENTRE LES MILIERS
		NumberFormat formatter = null;
		formatter = java.text.NumberFormat.getInstance(java.util.Locale.FRENCH);
		formatter = new DecimalFormat("##,###.## ");

		// ===================================================================================
		// Bloc Nom du véhicule
		// ===================================================================================
		JPanel jpName = new JPanel();
		jpName.setBackground(Color.white);
		jpName.setPreferredSize(new Dimension(220, 50));
		jpName.setBorder(BorderFactory.createTitledBorder("Nom du véhicule"));
		jpName.add(new JLabel(vehicle.getNom().toString().trim()));

		// ===================================================================================
		// Bloc Marque du véhicule
		// ===================================================================================
		JPanel jpMark = new JPanel();
		jpMark.setBackground(Color.white);
		jpMark.setPreferredSize(new Dimension(220, 50));
		jpMark.setBorder(BorderFactory.createTitledBorder("Marque du véhicule"));
		jpMark.add(new JLabel(vehicle.getMarque().toString()));

		// ===================================================================================
		// Bloc Moteur du véhicule
		// ===================================================================================
		JPanel jpMotor = new JPanel();
		jpMotor.setBackground(Color.white);
		jpMotor.setPreferredSize(new Dimension(440, 50));
		jpMotor.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
		jpMotor.add(new JLabel(vehicle.getMoteur().toString()));

		// ===================================================================================
		// Bloc Prix du véhicule
		// ===================================================================================
		JPanel jpPrice = new JPanel();
		jpPrice.setBackground(Color.white);
		jpPrice.setPreferredSize(new Dimension(440, 50));
		jpPrice.setBorder(BorderFactory.createTitledBorder("Prix du véhicule"));

		jlPrice = new JLabel("Prix sans option : ");
		jpPrice.add(jlPrice);
		jpPrice.add(new JLabel(formatter.format(vehicle.getPrix()).toString() + " €"));

		// ===================================================================================
		// Bloc Options du véhicule
		// ===================================================================================
		JPanel jpOptions = new JPanel();
		jpOptions.setBackground(Color.white);
		jpOptions.setPreferredSize(new Dimension(530, 80));
		jpOptions.setBorder(BorderFactory.createTitledBorder("Options Disponibles"));

		List<Option> listOptions = vehicle.getOptions();
		jlOptionsValue = new JLabel[listOptions.size()];

		for (int i = 0; i < listOptions.size(); i++) {
			Option opt = listOptions.get(i);
			jlOptionsValue[i] = new JLabel(opt.toString() + "(" + formatter.format(opt.getPrix()) + " €)");
			jpOptions.add(jlOptionsValue[i]);
		}

		// ===================================================================================
		// Bloc Prix Total du véhicule
		// ===================================================================================
		JPanel jpPriceTotal = new JPanel();
		jpPriceTotal.setBackground(Color.orange);
		jpPriceTotal.setPreferredSize(new Dimension(440, 60));
		jpPriceTotal.setBorder(BorderFactory.createTitledBorder(" PRIX TOTAL "));

		jlPriceTotal = new JLabel(formatter.format(vehicle.getPrixTotal()).toString() + " €");
		jlPriceTotal.setFont(new java.awt.Font("Arial", 2, 20));

		jpPriceTotal.add(jlPriceTotal);

		// Ajouter tous les composants au contenu
		JPanel jpContent = new JPanel();
		jpContent.setBackground(Color.white);
		jpContent.add(jpName);
		jpContent.add(jpMark);
		jpContent.add(jpMotor);
		jpContent.add(jpPrice);
		jpContent.add(jpOptions);
		jpContent.add(jpPriceTotal);

		this.getContentPane().add(jpContent, BorderLayout.CENTER);
	}
}