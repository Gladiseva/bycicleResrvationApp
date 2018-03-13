package lv.lollija.bicyclereservation.dao;

import lv.lollija.bicyclereservation.domain.Bicycle;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class BicycleDAOImpl implements BicycleDAO {
    private final EntityManager entityManager = HibernateUtil.getEntityManager();

    @Override
    public List<Bicycle> getAll() {
        return entityManager.createQuery("from Bicycle", Bicycle.class).getResultList();
    }

    @Override
    public Bicycle getById(Long id) {
        return entityManager.find(Bicycle.class, id);
    }

    @Override
    public List<Bicycle> getAllExcludedByIdList(List<Long> excludedIdList) {
        if (excludedIdList.isEmpty()) {
            return getAll();
        }
        TypedQuery<Bicycle> query = entityManager.createQuery("from Bicycle b where b.id not in :excludedIdList", Bicycle.class);
        query.setParameter("excludedIdList", excludedIdList);
        return query.getResultList();
    }

    @Override
    public Bicycle create(Bicycle bicycle) {
        entityManager.getTransaction().begin();
        entityManager.persist(bicycle);
        entityManager.getTransaction().commit();

        return bicycle;
    }

    @Override
    public Bicycle update(Bicycle bicycle) {
        entityManager.getTransaction().begin();
        entityManager.merge(bicycle);
        entityManager.getTransaction().commit();

        return bicycle;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Bicycle b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
