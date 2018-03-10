package lv.lollija.bicyclereservation.dao;

import lv.lollija.bicyclereservation.domain.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getAll();

    Employee getById(Long id);

    Employee create(Employee employee);

    Employee update(Employee employee);

    void delete(Long id);
}
