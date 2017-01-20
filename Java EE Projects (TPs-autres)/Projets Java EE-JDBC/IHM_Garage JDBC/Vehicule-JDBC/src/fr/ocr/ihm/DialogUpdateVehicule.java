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
public class DialogUpdateVehicule extends JDialog {

	/**
	 * VersionID
	 */
	private static final long serialVersionUID = 833211110212963852L;
	
	private JTextField jtName;
	private JFormattedTextField jtPrice;
	private JLabel jlName, jlMarque, jlMotor, jlPrice;
	private JComboBox<Object> jcbMotors, jcbMarks;
	private JCheckBox[] jcbOptionsNew;

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
	public DialogUpdateVehicule(JFrame jpParent, String sTitle, boolean isModal) {
		super(jpParent, sTitle, isModal);
		frame = jpParent;
		this.setSize(550, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * 
	 * @return
	 */
	public boolean showDialog(Vehicule pVehicle) {
		this.vehicle = pVehicle;
		this.initComponent();
		this.setVisible(true);
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
		jpName.setBorder(BorderFactory.createTitledBorder("Nom du véhicule"));
		
		jtName = new JTextField();
		jtName.setPreferredSize(new Dimension(100, 25));
		jtName.setText(vehicle.getNom().toString().trim());
		
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

		for (Marque m : listMarks){
			jcbMarks.addItem(m);
			if (vehicle.getMarque().toString().trim().equals(m.toString().trim())) {
				jcbMarks.setSelectedItem(m);
			}
		}
		jlMarque = new JLabel("Marque : ");
		jpMark.add(jlMarque);
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
			if (vehicle.getMoteur().toString().trim().equals(m.toString().trim())) {
				jcbMotors.setSelectedItem(m);
			}
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
		jtPrice.setValue(vehicle.getPrix());
		
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
		
		// Liste des Options disponibles
		jcbOptionsNew = new JCheckBox[listOptions.size()];
		
		//Liste des options choisies avant la modification
		List<Option> listOptionsOld = vehicle.getOptions();
		
		for (int i = 0; i < listOptions.size(); i++) {
			Option opt = listOptions.get(i);
			jcbOptionsNew[i] = new JCheckBox(opt.toString());
			
			//afficher les options ont été choisies parmi les options disponibles
			for (int j = 0; j < listOptionsOld.size(); j++) {
				Option o = listOptionsOld.get(j);
				if (opt.toString().trim().equals(o.toString().trim())) {
					jcbOptionsNew[i].setSelected(true);
				}
			}
			jpOptions.add(jcbOptionsNew[i]);
		}

		JPanel jpContent = new JPanel();
		jpContent.setBackground(Color.white);
		jpContent.add(jpName);
		jpContent.add(jpMark);
		jpContent.add(jpMotor);
		jpContent.add(jpPrice);
		jpContent.add(jpOptions);

		JPanel jpControl = new JPanel();
		JButton jBtnSave = new JButton("Enregistrer");

		// Quand on clique sur le bouton Enregistrer
		jBtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Récupérer nom du véhicule
				String name = jtName.getText();
				
				//Récupérer ID du véhicule
				int id = vehicle.getId();
				
				try {

					// Message de contrôle si le Nom est vide
					if (name.trim().equals("")) {
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
					for (int i = 0; i < jcbOptionsNew.length; i++) {
						if (jcbOptionsNew[i].isSelected()) {
							listOpt.add(listOptions.get(i));
						}
					}
					
					// Récupérer les données Prix
					Double price = ((Number) jtPrice.getValue()).doubleValue();

					// Update les données de l'objet véhicule
					vehicle = new Vehicule(id, name, mark, motor, listOpt, price);

					// Sauvegarder en base et update JTable
					Thread t = new Thread(new Runnable() {
						public void run() {
							DAOVehicule vehicleDao = new DAOVehicule(HsqldbConnection.getInstance());
							boolean result = vehicleDao.update(vehicle);
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

		jpControl.add(jBtnSave);
		jpControl.add(jBtnCancel);

		this.getContentPane().add(jpContent, BorderLayout.CENTER);
		this.getContentPane().add(jpControl, BorderLayout.SOUTH);
	}
}