package lv.lollija.bicyclereservation.presentation.dto;

import lv.lollija.bicyclereservation.domain.Bicycle;
import lv.lollija.bicyclereservation.domain.BicycleReservation;

import java.util.List;
import java.util.stream.Collectors;

public class ExtendedBicycleDTO extends BicycleDTO {
    List<BicycleReservationDTO> reservations;

    public ExtendedBicycleDTO() {
    }

    public ExtendedBicycleDTO(Bicycle bicycle, List<BicycleReservation> reservations) {
        super(bicycle);
        this.reservations = reservations.stream()
                .map(BicycleReservationDTO::new)
                .collect(Collectors.toList());
    }

    public List<BicycleReservationDTO> getReservations() {
        return reservations;
    }
}
