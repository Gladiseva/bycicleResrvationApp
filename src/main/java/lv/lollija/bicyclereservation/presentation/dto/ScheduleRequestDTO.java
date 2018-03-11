package lv.lollija.bicyclereservation.presentation.dto;

import java.time.LocalDateTime;

public class ScheduleRequestDTO {
    LocalDateTime dateFrom;
    LocalDateTime dateTo;

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }
}
