package lv.lollija.bicyclereservation.service;

import lv.lollija.bicyclereservation.dao.EmployeeDAO;
import lv.lollija.bicyclereservation.domain.Employee;

import javax.inject.Inject;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    private EmployeeDAO employeeDAO;
    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public Employee getById(Long id) {
        return employeeDAO.getById(id);
    }

    @Override
    public Employee create(Employee employee) {
        return employeeDAO.create(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeDAO.update(employee);
    }

    @Override
    public void delete(Long id) {
        employeeDAO.delete(id);
    }
}
