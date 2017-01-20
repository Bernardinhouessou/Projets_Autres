package fr.ocr.sql;

import java.sql.Connection;
import java.util.List;

/**
 * Couche d'accès aux données en base
 * 
 * @author XXX
 *
 * @param <T>
 */
public abstract class DAO<T> {
	protected Connection connect = null;

	public DAO(Connection conn) {
		this.connect = conn;
	}

	/**
	 * Création des données
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(T obj);

	/**
	 * Suppresion des données
	 * 
	 * @param obj
	 * @return boolean
	 * @throws DAOException
	 */
	public abstract boolean delete(T obj);

	/**
	 * Mise à jour des données
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);

	/**
	 * Recherche des données par identifiant
	 * 
	 * @param id
	 * @return T
	 */
	public abstract T find(int id);

	/**
	 * Recherche de toutes données
	 * 
	 * @param id
	 * @return T
	 */
	public abstract List<T> findAll();
}