package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.voiture.option.Option;

/**
 * DAO pour les Options du véhicule
 * 
 * @author XXX
 *
 */
public class DAOOption extends DAO<Option> {

	/**
	 * 
	 * @param conx
	 */
	public DAOOption(Connection conx) {
		super(conx);
	}

	/**
	 * 
	 */
	public boolean create(Option obj) {
		return true;
	}

	/**
	 * 
	 */
	public boolean delete(Option obj) {
		return false;
	}

	/**
	 * 
	 */
	public boolean update(Option obj) {
		return true;
	}

	/**
	 * Recherche des options par ID
	 */
	public Option find(int id) {

		Option opt = new Option();
		String query = "SELECT * FROM option WHERE id = " + id;

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			if (result.first()) {
				opt = new Option(id, result.getString("description"), result.getDouble("prix"));
			}
		} catch (SQLException e) {
			new DAOException("DAOOption : " + e.getMessage());
		}
		return opt;
	}

	/**
	 * Recherche de toutes les options
	 */
	public List<Option> findAll() {

		List<Option> list = new ArrayList<>();
		String query = "SELECT * FROM option ORDER BY description";

		try (Statement state = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet result = state.executeQuery(query);
			while (result.next()) {
				list.add(new Option(result.getInt("id"), result.getString("description"), result.getDouble("prix")));
			}
		} catch (SQLException e) {
			new DAOException("DAOOption : " + e.getMessage());
		}
		return list;
	}
}