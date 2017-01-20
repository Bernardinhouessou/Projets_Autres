package fr.ocr.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.voiture.Marque;
import com.voiture.Vehicule;
import com.voiture.moteur.Moteur;
import com.voiture.option.Option;

import fr.ocr.sql.DAOMarque;
import fr.ocr.sql.DAOMoteur;
import fr.ocr.sql.DAOOption;
import fr.ocr.sql.DAOVehicule;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.HsqldbConnection;

/***
 * 
 * @author XXX
 *
 */
public class DialogAddVehiculeNew extends JDialog {

	/**
	 * VersionID
	 */
	private static final long serialVersionUID = 6378094983421420551L;

	private JTextField jtName;
	private JFormattedTextField jtPrice;
	private JLabel jlName, jlMark, jlMotor, jlPrice;
	private JComboBox<Object> jcbMotors, jcbMarks;
	private JCheckBox[] jcbOptions;

	private List<Option> listOptions = new DAOOption(HsqldbConnection.getInstance()).findAll();
	private List<Moteur> listMotors = new DAOMoteur(HsqldbConnection.getInstance()).findAll();
	private List<Marque> listMarks = new DAOMarque(HsqldbConnection.getInstance()).findAll();

	private Vehicule vehicle;
	private JFrame frame;

	/**
	 * 
	 * @param jpParent
	 * @param sTitle
	 * @param isModal
	 */
	public DialogAddVehiculeNew(JFrame jpParent, String sTitle, boolean isModal) {
		super(jpParent, sTitle, isModal);
		frame = jpParent;
		this.setSize(550, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
	}

	/**
	 * 
	 * @return
	 */
	public boolean showDialog() {
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		return true;
	}

	/**
	 * Init des composants
	 */
	private void initComponent() {

		// ===================================================================================
		// Bloc Nom du véhicule
		// ===================================================================================
		JPanel jpName = new JPanel();
		jpName.setBackground(Color.white);
		jpName.setPreferredSize(new Dimension(220, 60));
		jtName = new JTextField();
		jtName.setPreferredSize(new Dimension(100, 25));
		jpName.setBorder(BorderFactory.createTitledBorder("Nom du véhicule"));
		jlName = new JLabel("Nom : ");
		jpName.add(jlName);
		jpName.add(jtName);

		// ===================================================================================
		// Bloc Marque du véhicule
		// ===================================================================================
		JPanel jpMark = new JPanel();
		jpMark.setBackground(Color.white);
		jpMark.setPreferredSize(new Dimension(220, 60));
		jpMark.setBorder(BorderFactory.createTitledBorder("Marque du véhicule"));
		jcbMarks = new JComboBox<Object>();

		for (Marque m : listMarks)
			jcbMarks.addItem(m);

		jlMark = new JLabel("Marque : ");
		jpMark.add(jlMark);
		jpMark.add(jcbMarks);

		// ===================================================================================
		// Bloc Moteur du véhicule
		// ===================================================================================
		JPanel jpMotor = new JPanel();
		jpMotor.setBackground(Color.white);
		jpMotor.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
		jpMotor.setPreferredSize(new Dimension(440, 60));

		jcbMotors = new JComboBox<Object>();

		for (Moteur m : listMotors) {
			jcbMotors.addItem(m);
		}
		jlMotor = new JLabel("Moteur : ");
		jpMotor.add(jlMotor);
		jpMotor.add(jcbMotors);

		// ===================================================================================
		// Bloc Prix du véhicule
		// ===================================================================================
		JPanel jpPrice = new JPanel();
		
		jpPrice.setBackground(Color.white);
		jpPrice.setPreferredSize(new Dimension(440, 60));
		jpPrice.setBorder(BorderFactory.createTitledBorder("Prix du véhicule"));		
		
		jtPrice = new JFormattedTextField(NumberFormat.getNumberInstance());
		jtPrice.setPreferredSize(new Dimension(100, 25));
		jlPrice = new JLabel("Prix :");
		
		jpPrice.add(jlPrice);
		jpPrice.add(jtPrice);

		// ===================================================================================
		// Bloc Options du véhicule
		// ===================================================================================
		JPanel jpOptions = new JPanel();
		jpOptions.setBackground(Color.white);
		jpOptions.setPreferredSize(new Dimension(530, 80));
		jpOptions.setBorder(BorderFactory.createTitledBorder("Options Disponibles"));

		jcbOptions = new JCheckBox[listOptions.size()];

		for (int i = 0; i < listOptions.size(); i++) {
			Option opt = listOptions.get(i);
			jcbOptions[i] = new JCheckBox(opt.toString());
			jpOptions.add(jcbOptions[i]);
		}

		JPanel jpContent = new JPanel();
		jpContent.setBackground(Color.white);
		jpContent.add(jpName);
		jpContent.add(jpMark);
		jpContent.add(jpMotor);
		jpContent.add(jpPrice);
		jpContent.add(jpOptions);

		JPanel jpControl = new JPanel();
		JButton jBtnOK = new JButton("OK");

		// Quand on valide l'ajout du nouveau véhicule par clique sur le bouton
		// OK
		jBtnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String nameVehicle = jtName.getText();
				try {

					// Message de contrôle si le Nom est vide
					if (nameVehicle.trim().equals("")) {
						throw new EmptyFieldException("Le champ 'nom' est obligatoire ! ");
					}

					// Message de contrôle si le prixe est vide
					if (jtPrice.getText().trim().equals("")) {
						throw new EmptyFieldException("Le champ 'prix' est obligatoire ! ");
					}
					// Récupérer les données de la Marque
					Marque mark = (Marque) jcbMarks.getSelectedItem();

					// Récupérer les donénes du Moteur
					Moteur motor = (Moteur) jcbMotors.getSelectedItem();

					// Récupérer les données des Options
					List<Option> listOpt = new ArrayList<>();
					for (int i = 0; i < jcbOptions.length; i++) {
						if (jcbOptions[i].isSelected()) {
							listOpt.add(listOptions.get(i));
						}
					}

					// Récupérer les données Prix
					Double price = ((Number) jtPrice.getValue()).doubleValue();

					// Créer le nouveau véhicule
					vehicle = new Vehicule(0, nameVehicle, mark, motor, listOpt, price);

					// Sauvegarder en base et update JTable
					Thread t = new Thread(new Runnable() {
						public void run() {
							DAOVehicule vehicleDao = new DAOVehicule(HsqldbConnection.getInstance());
							boolean result = vehicleDao.create(vehicle);
							if (result) {
								SwingUtilities.invokeLater(new Runnable() {
									public void run() {
										frame.getContentPane().removeAll();
										frame.getContentPane().add(new JScrollPane(SortTable.getTableSorted(DatabaseTable.VEHICULE)), BorderLayout.CENTER);
										frame.getContentPane().revalidate();
									}
								});
							}
						}
					});
					t.start();
					setVisible(false);
				} catch (EmptyFieldException e) {
				} catch (NumberFormatException e) {
					new EmptyFieldException(e.getMessage());
				}
			}
		});

		// Clique sur le bouton Annuler
		JButton jBtnCancel = new JButton("Annuler");
		jBtnCancel.setForeground(Color.red);
		jBtnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		jpControl.add(jBtnOK);
		jpControl.add(jBtnCancel);

		this.getContentPane().add(jpContent, BorderLayout.CENTER);
		this.getContentPane().add(jpControl, BorderLayout.SOUTH);
	}
}