package lv.lollija.bicyclereservation.dao;

import lv.lollija.bicyclereservation.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    @Override
    public List<Employee> getAll() {
        return entityManager.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public Employee getById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee create(Employee employee) {
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        return employee;
    }

    @Override
    public Employee update(Employee employee) {

        entityManager.getTransaction().begin();
        entityManager.merge(employee);
        entityManager.getTransaction().commit();

        return employee;
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("delete from Employee e where e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }
}
