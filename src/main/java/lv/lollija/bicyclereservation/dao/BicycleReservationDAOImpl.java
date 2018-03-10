package lv.lollija.bicyclereservation.dao;

import lv.lollija.bicyclereservation.domain.Bicycle;
import lv.lollija.bicyclereservation.domain.BicycleReservation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BicycleReservationDAOImpl implements BicycleReservationDAO {
    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    @Override
    public List<BicycleReservation> getAll() {
        return entityManager.createQuery("from BicycleReservation ", BicycleReservation.class).getResultList();
    }

    @Override
    public BicycleReservation getById(Long id) {
        return entityManager.find(BicycleReservation.class, id);
    }

    @Override
    public List<BicycleReservation> getByEmployeeId(Long id) {
        TypedQuery<BicycleReservation> query = entityManager.createQuery("from BicycleReservation br where br.employee.id = :id", BicycleReservation.class);
        query.setParameter("id", id);
        return query.getResultList();
    }


    @Override
    public BicycleReservation create(BicycleReservation bicycleReservation) {
        entityManager.getTransaction().begin();
        entityManager.persist(bicycleReservation);
        entityManager.getTransaction().commit();

        return bicycleReservation;
    }

    @Override
    public BicycleReservation update(BicycleReservation bicycleReservation) {
        entityManager.getTransaction().begin();
        entityManager.merge(bicycleReservation);
        entityManager.getTransaction().commit();

        return bicycleReservation;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from BicycleReservation b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
