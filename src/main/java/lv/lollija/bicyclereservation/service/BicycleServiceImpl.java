package lv.lollija.bicyclereservation.service;

import lv.lollija.bicyclereservation.dao.BicycleDAO;
import lv.lollija.bicyclereservation.domain.Bicycle;
import lv.lollija.bicyclereservation.domain.BicycleReservation;
import lv.lollija.bicyclereservation.presentation.dto.ExtendedBicycleDTO;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BicycleServiceImpl implements BicycleService {

    @Inject
    private BicycleDAO bicycleDAO;

    @Inject
    private BicycleReservationService bicycleReservationService;

    @Override
    public List<Bicycle> getAll() {
        return bicycleDAO.getAll();
    }

    @Override
    public Bicycle getById(Long id) {
        return bicycleDAO.getById(id);
    }

    @Override
    public List<ExtendedBicycleDTO> getAllWithReservationsInPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {
        List<Bicycle> bicycles = getAll();
        List<BicycleReservation> reservationsOfDate = bicycleReservationService.getInPeriod(dateFrom, dateTo);
        Map<Long, List<BicycleReservation>> reservationsOfBicycle = reservationsOfDate.stream()
                .collect(Collectors.groupingBy(br -> br.getBicycle().getId()));
        return bicycles.stream()
                .map(bicycle -> {
                    List<BicycleReservation> reservations = reservationsOfBicycle.get(bicycle.getId());
                    if(reservations==null){
                        reservations = new ArrayList<>();
                    }
                    return new ExtendedBicycleDTO(bicycle, reservations);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Bicycle> getAllAvailableInPeriod(LocalDateTime startUsageDate, LocalDateTime endUsageDate) {
        List<BicycleReservation> reservationsInPeriod = bicycleReservationService.getInPeriod(startUsageDate, endUsageDate);
        List<Long> bicycleIdsReservedInPeriod = reservationsInPeriod.stream()
                .map(reservation -> reservation.getBicycle().getId())
                .collect(Collectors.toList());
        return bicycleDAO.getAllExcludedByIdList(bicycleIdsReservedInPeriod);
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
