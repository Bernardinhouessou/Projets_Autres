package fr.ocr.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.voiture.Vehicule;
import com.voiture.option.Option;

/**
 * DAO pour le véhicule
 * 
 * @author XXX
 *
 */
public class DAOVehicule extends DAO<Vehicule> {
	
	/**
	 * 
	 * @param conx
	 */
	public DAOVehicule(Connection conx) {
		super(conx);
	}

	/**
	 * Création d'un véhicule
	 * 
	 * @param vehicule
	 * @return
	 * 	
	 */
	public boolean create(Vehicule vehicle) {

		String query_insert_vehicle = "INSERT INTO VEHICULE (id, nom, marque, moteur, prix) VALUES ?, ?, ?, ?, ?";
		String query_insert_options = "INSERT INTO vehicule_option (id_vehicule, id_option) VALUES ?, ?";

		try (PreparedStatement pStatVehicle = connect.prepareStatement(query_insert_vehicle);
				PreparedStatement pStatOptions = connect.prepareStatement(query_insert_options);) {

			// Contrôles de saisie IHM
			if (vehicle.getNom().trim().equals("")) {
				throw new DAOException("DAOVehicule : Erreur de création du véhicule. Champ 'nom' est obligatoire !");
			}
			if (vehicle.getMarque().getId() < 0) {
				throw new DAOException("DAOVehicule : Erreur de création du véhicule. Champ 'marque' est obligatoire !");
			}
			if (vehicle.getMoteur().getId() < 0) {
				throw new DAOException("DAOVehicule : Erreur de création du véhicule. Champ 'moteur' est obligatoire !");
			}
			if (vehicle.getPrix() <= 0 || vehicle.getPrix() == null) {
				throw new DAOException("DAOVehicule : Erreur de création du véhicule. Champ 'prix' est obligatoire !");
			}
			// Transaction
			connect.setAutoCommit(false);

			// Récupérer le prochain ID du véhicule
			ResultSet nextID = connect.prepareStatement("CALL NEXT VALUE FOR seq_vehicule_id").executeQuery();

			if (nextID.next()) {
				int ID = nextID.getInt(1);

				// Enregistrer le véhicule
				pStatVehicle.setInt(1, ID);
				pStatVehicle.setString(2, vehicle.getNom());
				pStatVehicle.setInt(3, vehicle.getMarque().getId());
				pStatVehicle.setInt(4, vehicle.getMoteur().getId());
				pStatVehicle.setDouble(5, vehicle.getPrix());

				pStatVehicle.executeUpdate();

				// Sauvegarder ses options
				List<Option> listOpt = vehicle.getOptions();

				for (Option opt : listOpt) {

					pStatOptions.setInt(1, ID);
					pStatOptions.setInt(2, opt.getId());

					try {
						pStatOptions.executeUpdate();
					} catch (SQLException eSql) {
						throw new DAOException("DAOVehicule : Erreur lors de la sauvegarde de l'option " + opt + " du vehicule ! \n" + eSql.getMessage());
					}
				}
			} else {
				throw new DAOException("DAOVehicule : Impossible de trouver le prochain ID du véhicule dans la séquence !");
			}
		} catch (SQLException e) {
			new DAOException("DAOVehicule : " + e.getMessage());
			try {
				connect.rollback();
			} catch (SQLException eSql) {
				eSql.printStackTrace();
			}
			return false;
		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException eSql) {
				eSql.printStackTrace();
			}
			return false;
		}
		// valider la transaction
		try {
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Suppression d'un véhicule
	 * 
	 * @param vehicule
	 * @return
	 * 
	 */
	public boolean delete(Vehicule vehicle) {

		String query_delete_options = "DELETE FROM vehicule_option WHERE id_vehicule = " + vehicle.getId();
		String query_delete_vehicle = "DELETE FROM vehicule WHERE id = " + vehicle.getId();

		try (PreparedStatement pStatDeleteOptions = connect.prepareStatement(query_delete_options);
			 PreparedStatement pStatDeleteVehicle = connect.prepareStatement(query_delete_vehicle);) {
			
			if (vehicle.getId() < 1) {
				throw new DAOException("DAOVehicule : Impossible de supprimer un véhicule qui n'existe pas! ");
			}

			// Démarrer la transaction
			connect.setAutoCommit(false);

			// Executer la suppression
			pStatDeleteOptions.executeUpdate();
			pStatDeleteVehicle.executeUpdate();

		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException eSql) {
				eSql.printStackTrace();
			}
			return false;
		} catch (SQLException e) {
			try {
				connect.rollback();
			} catch (SQLException eSql) {
				eSql.printStackTrace();
			}
			new DAOException("DAOVehicule : Erreur lors que la suppression d'un véhicule");
			return false;
		}

		try {
			connect.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	/**
	 * Mise à jour d'un véhicule
	 * 
	 * @param vehicule
	 * @return
	 */
	public boolean update(Vehicule vehicle) {
		
		int id = vehicle.getId();
		
		String query_delete_options = "DELETE FROM vehicule_option WHERE id_vehicule = " + id;
		String query_update_options = "INSERT INTO vehicule_option (id_vehicule, id_option) VALUES ?, ?";
		String query_update_vehicle = "UPDATE vehicule SET nom = ?, marque = ?, moteur = ?, prix = ? WHERE id = " + id;
		
		//System.out.println("Update vehicule n°" + id);
		
		try (PreparedStatement pStatDeleteOptions = connect.prepareStatement(query_delete_options);
			 PreparedStatement pStatUpdateOptions = connect.prepareStatement(query_update_options);	
			 PreparedStatement pStatUpdateVehicle = connect.prepareStatement(query_update_vehicle);) {
			
			if (id < 1) {
				throw new DAOException("DAOVehicule : Impossible de modifier un véhicule qui n'existe pas ! ");
			}

			// Démarrer la transaction
			connect.setAutoCommit(false);
									
			pStatUpdateVehicle.setString(1, vehicle.getNom());
			pStatUpdateVehicle.setInt(2, vehicle.getMarque().getId());
			pStatUpdateVehicle.setInt(3, vehicle.getMoteur().getId());
			pStatUpdateVehicle.setDouble(4, vehicle.getPrix());
			pStatUpdateVehicle.executeUpdate();
			
			// supprimer les anciennes options
			pStatDeleteOptions.executeUpdate();
			
			// sauvegarder les nouvelles options choisies
			List<Option> listOpt = vehicle.getOptions();

			for (Option opt : listOpt) {
				pStatUpdateOptions.setInt(1, id);				
				pStatUpdateOptions.setInt(2, opt.getId());				
				try {
					pStatUpdateOptions.executeUpdate();
				} catch (SQLException eSql) {
					throw new DAOException("DAOVehicule : Erreur lors de sauvegarde de l'option " + opt + " du vehicule ! \n" + eSql.getMessage());
				}
			}			

		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException eSql) {
				eSql.printStackTrace();
			}
			return false;
		} catch (SQLException e) {
			try {
				connect.rollback();
			} catch (SQLException eSql) {
				eSql.printStackTrace();
			}
			new DAOException("DAOVehicule : Erreur lors que la mise à jour du véhicule n°"+ id + " [" + e.getMessage() + "]");
			return false;
		}

		try {
			connect.commit();
		} catch (SQLException eSql) {
			eSql.printStackTrace();
		}
		return true;
	}

	/**
	 * Recherche d'un véhicule par identifiant
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public Vehicule find(int id) {

		Vehicule vehicle = new Vehicule();
		String query = "SELECT * FROM vehicule WHERE id = " + id;

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			ResultSet result = state.executeQuery(query);

			if (result.first()) {
				vehicle = new Vehicule(id, result.getString("nom"),
						new DAOMarque(this.connect).find(result.getInt("marque")),
						new DAOMoteur(this.connect).find(result.getInt("moteur")), getOptions(id),
						result.getDouble("prix"));
			}
		} catch (SQLException eSql) {
			new DAOException("DAOVehicule : " + eSql.getMessage());
		}
		return vehicle;
	}

	/**
	 * Recherche de tous les véhicules
	 * 
	 * @return
	 */
	public List<Vehicule> findAll() {

		List<Vehicule> listVehicles = new ArrayList<>();
		String query = "SELECT * FROM vehicule ORDER BY nom";

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			ResultSet result = state.executeQuery(query);

			while (result.next()) {
				int id = result.getInt("id");
				listVehicles.add(new Vehicule(id, result.getString("nom"),
						new DAOMarque(this.connect).find(result.getInt("marque")),
						new DAOMoteur(this.connect).find(result.getInt("moteur")), getOptions(id),
						result.getDouble("prix")));
			}
		} catch (SQLException eSql) {
			new DAOException("DAOVehicule : " + eSql.getMessage());
		}

		return listVehicles;
	}

	/**
	 * Récupération de la liste des options d'un véhicule
	 * 
	 * @param id
	 * @return
	 */
	private List<Option> getOptions(int id) {

		List<Option> listOpt = new ArrayList<>();
		String query = "SELECT id_option FROM vehicule_option WHERE id_vehicule = " + id;

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				listOpt.add(new DAOOption(this.connect).find(result.getInt("id_option")));
			}
		} catch (SQLException eSql) {
			new DAOException("DAOVehicule : " + eSql.getMessage());
		}

		return listOpt;
	}
}
