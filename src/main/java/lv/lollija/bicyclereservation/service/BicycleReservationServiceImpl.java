package lv.lollija.bicyclereservation.service;

import lv.lollija.bicyclereservation.dao.BicycleReservationDAO;
import lv.lollija.bicyclereservation.domain.BicycleReservation;

import javax.inject.Inject;
import java.util.List;

public class BicycleReservationServiceImpl implements BicycleReservationService {
    @Inject
    private BicycleReservationDAO bicycleReservationDAO;

    @Override
    public List<BicycleReservation> getAll() {
        return bicycleReservationDAO.getAll();
    }

    @Override
    public BicycleReservation getById(Long id) {
        return bicycleReservationDAO.getById(id);
    }

    @Override
    public List<BicycleReservation> getByEmployeeId(Long id) {
        return bicycleReservationDAO.getByEmployeeId(id);
    }

    @Override
    public BicycleReservation create(BicycleReservation bicycleReservation) {
        return bicycleReservationDAO.create(bicycleReservation);
    }

    @Override
    public BicycleReservation update(BicycleReservation bicycleReservation) {
        return bicycleReservationDAO.update(bicycleReservation);
    }

    @Override
    public void delete(Long id) {
        bicycleReservationDAO.delete(id);
    }
}
