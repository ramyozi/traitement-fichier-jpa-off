package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import fr.diginamic.entities.Categorie;

public class CategorieDAO extends DAO<Categorie> {

	public CategorieDAO(EntityManager entityManager) {
		super(entityManager);
	}

	public Categorie findByName(String categoryName) {
		try {
			Query query = entityManager.createQuery(
					"SELECT c FROM Categorie c WHERE c.nom = :nom",
					Categorie.class);
			query.setParameter("nom", categoryName);
			return (Categorie) query.getSingleResult();
		} catch (NoResultException e) {
			return null; 
		}
	}

	public void createIfNotExists(String categoryName) {
		Categorie existingCategory = findByName(categoryName);
		if (existingCategory == null) {
			Categorie newCategory = new Categorie();
			newCategory.setNom(categoryName);
			create(newCategory);
		}
	}

}