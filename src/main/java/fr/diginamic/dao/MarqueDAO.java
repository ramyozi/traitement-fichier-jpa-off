package fr.diginamic.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import fr.diginamic.entities.Marque;

public class MarqueDAO extends DAO<Marque> {

    public MarqueDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Marque findByName(String marqueName) {
        try {
            Query query = entityManager.createQuery("SELECT m FROM Marque m WHERE m.nom = :nom", Marque.class);
            query.setParameter("nom", marqueName);
            return (Marque) query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
    }

    public void createIfNotExists(String marqueName) {
        Marque existingMarque = findByName(marqueName);
        if (existingMarque == null) {
            Marque newMarque = new Marque();
            newMarque.setNom(marqueName);
            create(newMarque);
        }
    }
}
