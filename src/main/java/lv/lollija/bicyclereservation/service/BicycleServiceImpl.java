package lv.lollija.bicyclereservation.service;

import lv.lollija.bicyclereservation.dao.BicycleDAO;
import lv.lollija.bicyclereservation.dao.EmployeeDAO;
import lv.lollija.bicyclereservation.domain.Bicycle;

import javax.inject.Inject;
import java.util.List;

public class BicycleServiceImpl implements BicycleService {
    @Inject
    private BicycleDAO bicycleDAO;
    @Override
    public List<Bicycle> getAll() {
        return bicycleDAO.getAll();
    }

    @Override
    public Bicycle getById(Long id) {
        return bicycleDAO.getById(id);
    }

    @Override
    public Bicycle create(Bicycle bicycle) {
        return bicycleDAO.create(bicycle);
    }

    @Override
    public Bicycle update(Bicycle bicycle) {
        return bicycleDAO.update(bicycle);
    }

    @Override
    public void delete(Long id) {
        bicycleDAO.delete(id);
    }
}
