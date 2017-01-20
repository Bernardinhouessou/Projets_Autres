package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.voiture.moteur.TypeMoteur;

/**
 * DAO pour le Type Moteur du véhicule
 * 
 * @author XXX
 *
 */
public class DAOTypeMoteur extends DAO<TypeMoteur> {

	/**
	 * 
	 * @param conx
	 */
	public DAOTypeMoteur(Connection conx) {
		super(conx);
	}

	/**
	 * 
	 */
	public boolean create(TypeMoteur obj) {
		return true;
	}

	/**
	 * 
	 */
	public boolean delete(TypeMoteur obj) {
		return false;
	}

	/**
	 * 
	 */
	public boolean update(TypeMoteur obj) {
		return true;
	}

	/**
	 * Recherche des types de moteur par ID
	 */
	public TypeMoteur find(int id) {

		TypeMoteur type = new TypeMoteur();
		String query = "SELECT * FROM type_moteur WHERE id = " + id;

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			if (result.first()) {
				type = new TypeMoteur(id, result.getString("description"));
			}
		} catch (SQLException e) {
			new DAOException("DAOTypeMoteur : " + e.getMessage());
		}
		return type;
	}

	/**
	 * Recherche de tous les types de moteur
	 */
	public List<TypeMoteur> findAll() {

		List<TypeMoteur> listTypeMotors = new ArrayList<>();
		String query = "SELECT * FROM type_moteur ORDER BY nom";

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				listTypeMotors.add(new TypeMoteur(result.getInt("id"), result.getString("description")));
			}
		} catch (SQLException e) {
			new DAOException("DAOTypeMoteur : " + e.getMessage());
		}
		return listTypeMotors;
	}
}