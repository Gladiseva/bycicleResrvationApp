package lv.lollija.bicyclereservation.dao;

import lv.lollija.bicyclereservation.domain.Bicycle;
import lv.lollija.bicyclereservation.domain.BicycleReservation;
import lv.lollija.bicyclereservation.presentation.dto.ExtendedBicycleDTO;
import lv.lollija.bicyclereservation.presentation.dto.ScheduleRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface BicycleDAO {
    List<Bicycle> getAll();

    Bicycle getById(Long id);

    List<Bicycle> getAllExcludedByIdList(List<Long> excludedIdList);

    Bicycle create(Bicycle bicycle);

    Bicycle update(Bicycle bicycle);

    void delete(Long id);
}
