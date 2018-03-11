package lv.lollija.bicyclereservation.service;

import lv.lollija.bicyclereservation.domain.Bicycle;
import lv.lollija.bicyclereservation.presentation.dto.ExtendedBicycleDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface BicycleService {
    List<Bicycle> getAll();

    Bicycle getById(Long id);

    List<ExtendedBicycleDTO> getAllWithReservationsInPeriod(LocalDateTime dateFrom, LocalDateTime dateTo);

    List<Bicycle> getAllAvailableInPeriod(LocalDateTime startUsageDate, LocalDateTime endUsageDate);

    Bicycle create(Bicycle bicycle);

    Bicycle update(Bicycle bicycle);

    void delete(Long id);
}
