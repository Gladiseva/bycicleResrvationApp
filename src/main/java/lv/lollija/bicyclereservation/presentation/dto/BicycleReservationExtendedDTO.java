package lv.lollija.bicyclereservation.presentation.dto;

import lv.lollija.bicyclereservation.domain.BicycleReservation;

public class BicycleReservationExtendedDTO extends BicycleReservationDTO {
    private EmployeeDTO employee;
    private BicycleDTO bicycle;

    public BicycleReservationExtendedDTO() {
    }

    public BicycleReservationExtendedDTO(BicycleReservation bicycleReservation) {
        super(bicycleReservation);
        this.employee = new EmployeeDTO(bicycleReservation.getEmployee());
        this.bicycle = new BicycleDTO(bicycleReservation.getBicycle());
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public BicycleDTO getBicycle() {
        return bicycle;
    }
}
