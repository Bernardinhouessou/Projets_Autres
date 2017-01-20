package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.voiture.Marque;

/**
 * DAO pour la marque du véhicule
 * 
 * @author XXX
 *
 */
public class DAOMarque extends DAO<Marque> {
	
	/**
	 * 
	 * @param conx
	 */
	public DAOMarque(Connection conx) {
		super(conx);
	}
	
	/**
	 * 
	 */
	public boolean create(Marque obj) {
		return true;
	}
	
	/**
	 * 
	 */
	public boolean delete(Marque obj) {
		return false;
	}
	
	/**
	 * 
	 */
	public boolean update(Marque obj) {
		return true;
	}

	/**
	 * Recherche des marques par ID
	 */
	public Marque find(int id) {

		Marque mark = new Marque();
		String query = "SELECT * FROM marque WHERE id = " + id;

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			if (result.first()) {
				mark = new Marque(id, result.getString("nom"));
			}
		} catch (SQLException e) {
			new DAOException("DAOMarque : " + e.getMessage());
		}
		return mark;
	}

	/**
	 * Recherche de toutes les marques
	 */
	public List<Marque> findAll() {

		List<Marque> listMarks = new ArrayList<>();
		String query = "SELECT * FROM marque ORDER BY nom";

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				listMarks.add(new Marque(result.getInt("id"), result.getString("nom")));
			}
		} catch (SQLException e) {
			new DAOException("DAOMarque : " + e.getMessage());
		}

		return listMarks;
	}

}