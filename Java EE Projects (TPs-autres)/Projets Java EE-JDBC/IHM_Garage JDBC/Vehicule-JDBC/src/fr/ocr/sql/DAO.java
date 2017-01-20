package fr.ocr.sql;

import java.sql.Connection;
import java.util.List;

/**
 * Couche d'acc�s aux donn�es en base
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
	 * Cr�ation des donn�es
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(T obj);

	/**
	 * Suppresion des donn�es
	 * 
	 * @param obj
	 * @return boolean
	 * @throws DAOException
	 */
	public abstract boolean delete(T obj);

	/**
	 * Mise � jour des donn�es
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);

	/**
	 * Recherche des donn�es par identifiant
	 * 
	 * @param id
	 * @return T
	 */
	public abstract T find(int id);

	/**
	 * Recherche de toutes donn�es
	 * 
	 * @param id
	 * @return T
	 */
	public abstract List<T> findAll();
}