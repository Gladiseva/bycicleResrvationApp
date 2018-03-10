package lv.lollija.bicyclereservation.presentation.dto;

import lv.lollija.bicyclereservation.domain.Employee;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String surname;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Employee toDomainObject() {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setSurname(surname);
        return employee;

    }
}
