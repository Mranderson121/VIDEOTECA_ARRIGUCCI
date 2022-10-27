package com.advancia.stage.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.advancia.stage.model.Attore;
import com.advancia.stage.model.Film;
import com.advancia.stage.model.Regista;

public class VideotecaDAO {
	private static final String PERSISTANCE_UNIT_NAME = "Videoteca";
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");	
	
	//Methods for connection to database and transaction management
	private static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
		}
		return factory;
	}
	private static void beginTransaction() {
		if (entityManager == null) {
			entityManager = getEntityManagerFactory().createEntityManager();
		}
		entityManager.getTransaction().begin();
	}
	private static void commit() {
		entityManager.getTransaction().commit();
	}
	private static void rollback() {
		entityManager.getTransaction().rollback();
	}
	public static void shutdown() {
		if (entityManager != null) entityManager.close();
		if (factory != null) factory.close();
	}	
	//-------------------------------------------------------
	
	//Operations versus database
	public static List<Film> getAllFilms() {
		try {
			beginTransaction();
			TypedQuery<Film> query = entityManager.createQuery("select f from Film f", Film.class);
			List<Film> listaFilm = query.getResultList();
			commit();
			return listaFilm;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
	
	public static Film addFilm(String nome, Date dataUscita, Regista regista) {
		try {
			beginTransaction();
			TypedQuery<Film> query = entityManager.createQuery("select f from Film f where f.nome = :nome "
					+ "and f.dataUscita = :dataUscita and f.regista = :regista", Film.class);
			query.setParameter("nome", nome);
			query.setParameter("dataNascita", dataUscita);
			query.setParameter("regista", regista);
			List<Film> filmGiaPresente = query.getResultList();
			if (filmGiaPresente == null || filmGiaPresente.isEmpty()) {
				Film film = new Film(nome, dataUscita, regista);
				entityManager.persist(film);
				commit();
				return film;
			} else {
				commit();
				return filmGiaPresente.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
	
	public static List<Regista> getAllRegisti() {
		try {
			beginTransaction();
			TypedQuery<Regista> query = entityManager.createQuery("select r from Regista r", Regista.class);
			List<Regista> registi = query.getResultList();
			commit();
			return registi;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
	
	public static Regista addRegista(String nome, String cognome, String _dataNascita) {
		try {
			beginTransaction();
			Date dataNascita = formatter.parse(_dataNascita);
			TypedQuery<Regista> query = entityManager.createQuery("select r from Regista r where r.nome = :nome "
					+ "and r.cognome = :cognome and r.dataNascita = :dataNascita", Regista.class);
			query.setParameter("nome", nome);
			query.setParameter("cognome", cognome);
			query.setParameter("dataNascita", dataNascita);
			List<Regista> registaGiaPresente = query.getResultList();
			if (registaGiaPresente == null || registaGiaPresente.isEmpty()) {
				Query querySequence = entityManager.createNativeQuery("SELECT REGISTA_SEQ.NEXTVAL FROM DUAL");
				BigDecimal result = (BigDecimal)querySequence.getSingleResult();
				Regista regista = new Regista(nome, cognome, dataNascita);
				regista.setId(Long.parseLong(String.valueOf(result)));
				entityManager.persist(regista);
				commit();
				return regista;
			} else {
				commit();
				return registaGiaPresente.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
	
	public static Attore addAttore(String nome, String cognome, String _dataNascita) {
		try {
			beginTransaction();
			Date dataNascita = formatter.parse(_dataNascita);
			TypedQuery<Attore> query = entityManager.createQuery("select a from Attore a where a.nome = :nome "
					+ "and a.cognome = :cognome and a.dataNascita = :dataNascita", Attore.class);
			query.setParameter("nome", nome);
			query.setParameter("cognome", cognome);
			query.setParameter("dataNascita", dataNascita);
			List<Attore> attoreGiaPresente = query.getResultList();
			if (attoreGiaPresente == null || attoreGiaPresente.isEmpty()) {
				Attore attore = new Attore(nome, cognome, dataNascita);
				entityManager.persist(attore);
				commit();
				return attore;
			} else {
				commit();
				return attoreGiaPresente.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			return null;
		}
	}
}








