package lv.lollija.bicyclereservation;

import lv.lollija.bicyclereservation.dao.*;
import lv.lollija.bicyclereservation.service.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder{
    @Override
    protected void configure() {
        bind(EmployeeServiceImpl.class).to(EmployeeService.class);
        bind(EmployeeDAOImpl.class).to(EmployeeDAO.class);
        bind(BicycleServiceImpl.class).to(BicycleService.class);
        bind(BicycleDAOImpl.class).to(BicycleDAO.class);
        bind(BicycleReservationServiceImpl.class).to(BicycleReservationService.class);
        bind(BicycleReservationDAOImpl.class).to(BicycleReservationDAO.class);
    }
}
