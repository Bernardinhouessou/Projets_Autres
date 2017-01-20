package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.voiture.moteur.Moteur;
import com.voiture.moteur.TypeMoteur;

/**
 * DAO pour le moteur du véhicule
 * 
 * @author XXX
 *
 */
public class DAOMoteur extends DAO<Moteur> {

	/**
	 * 	
	 * @param conx
	 */
	public DAOMoteur(Connection conx) {
		super(conx);
	}

	/**
	 * 
	 */
	public boolean create(Moteur obj) {
		return true;
	}

	/**
	 * 
	 */
	public boolean delete(Moteur obj) {
		return false;
	}

	/**
	 * 
	 */
	public boolean update(Moteur obj) {
		return true;
	}

	/**
	 * Recherche des moteurs par ID
	 */
	public Moteur find(int id) {

		Moteur motor = new Moteur();
		String query = "SELECT * FROM moteur WHERE id = " + id;

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			if (result.first()) {
				DAO<TypeMoteur> typeMot = new DAOTypeMoteur(this.connect);
				motor = new Moteur(id, typeMot.find(result.getInt("moteur")), result.getString("cylindre"), result.getDouble("prix"));
			}
		} catch (SQLException e) {
			new DAOException("DAOMoteur : " + e.getMessage());
		}
		return motor;
	}

	/**
	 * Recherche de tous les moteurs
	 */
	public List<Moteur> findAll() {

		List<Moteur> listMotors = new ArrayList<>();
		String query = "SELECT * FROM moteur inner join type_moteur on type_moteur.id = moteur ORDER BY description, cylindre";

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				DAO<TypeMoteur> typeMot = new DAOTypeMoteur(this.connect);
				listMotors.add(new Moteur(result.getInt("id"), typeMot.find(result.getInt("moteur")), result.getString("cylindre"), result.getDouble("prix")));
			}
		} catch (SQLException e) {
			new DAOException("DAOMoteur : " + e.getMessage());
		}

		return listMotors;
	}
}
