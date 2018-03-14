package lv.lollija.bicyclereservation.presentation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lv.lollija.bicyclereservation.domain.Bicycle;
import lv.lollija.bicyclereservation.domain.BicycleReservation;
import lv.lollija.bicyclereservation.domain.Employee;

import java.time.LocalDateTime;

public class BicycleReservationDTO {
    private Long id;
    private Long employeeId;
    private EmployeeDTO employee;
    private Long bicycleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime startUsageDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime endUsageDate;

    public BicycleReservationDTO() {
    }

    public BicycleReservationDTO(BicycleReservation bicycleReservation) {
        this.id = bicycleReservation.getId();
        Employee employee = bicycleReservation.getEmployee();
        this.employeeId = employee.getId();
        this.employee = new EmployeeDTO(employee);
        this.bicycleId = bicycleReservation.getBicycle().getId();
        this.startUsageDate = bicycleReservation.getStartUsageDate();
        this.endUsageDate = bicycleReservation.getEndUsageDate();
    }

    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Long getBicycleId() {
        return bicycleId;
    }

    public LocalDateTime getStartUsageDate() {
        return startUsageDate;
    }

    public LocalDateTime getEndUsageDate() {
        return endUsageDate;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public BicycleReservation populateDomainObject(BicycleReservation reservation) {
        reservation.setStartUsageDate(startUsageDate);
        reservation.setEndUsageDate(endUsageDate);
        return reservation;
    }

    public BicycleReservation toDomainObject(Employee employee, Bicycle bicycle) {
        BicycleReservation bicycleReservation = new BicycleReservation();
        bicycleReservation.setEmployee(employee);
        bicycleReservation.setBicycle(bicycle);
        return populateDomainObject(bicycleReservation);
    }
}
