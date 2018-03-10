package lv.lollija.bicyclereservation.dao;

import lv.lollija.bicyclereservation.domain.Bicycle;

import java.util.List;

public interface BicycleDAO {
    List<Bicycle> getAll();

    Bicycle getById(Long id);

    Bicycle create(Bicycle bicycle);

    Bicycle update(Bicycle bicycle);

    void delete(Long id);
}
