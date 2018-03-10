package lv.lollija.bicyclereservation.service;

import lv.lollija.bicyclereservation.domain.Bicycle;

import java.util.List;

public interface BicycleService {
    List<Bicycle> getAll();

    Bicycle getById(Long id);

    Bicycle create(Bicycle bicycle);

    Bicycle update(Bicycle bicycle);

    void delete(Long id);
}
