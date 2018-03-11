package lv.lollija.bicyclereservation.dao;

import lv.lollija.bicyclereservation.domain.BicycleReservation;

import java.time.LocalDateTime;
import java.util.List;

public interface BicycleReservationDAO {
    List<BicycleReservation> getAll();

    BicycleReservation getById(Long id);

    List<BicycleReservation> getByEmployeeId(Long id);

    BicycleReservation create(BicycleReservation bicycleReservation);

    BicycleReservation update(BicycleReservation bicycleReservation);

    void delete(Long id);

    List<BicycleReservation> getInPeriod(LocalDateTime dateFrom, LocalDateTime dateTo);
}
