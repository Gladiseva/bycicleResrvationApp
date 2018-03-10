package lv.lollija.bicyclereservation.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bicycle_reservation")
public class BicycleReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "reservation_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "reservation_employee_fk"))
    private Employee employee;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bicycle_id", foreignKey = @ForeignKey(name = "reservation_bicycle_fk"))
    private Bicycle bicycle;

    @NotNull
    @Column(name = "start_usage_date")
    private LocalDateTime startUsageDate;

    @NotNull
    @Column(name = "end_usage_date")
    private LocalDateTime endUsageDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeId) {
        this.employee = employeeId;
    }

    public Bicycle getBicycle() {
        return bicycle;
    }

    public void setBicycle(Bicycle bicycleId) {
        this.bicycle = bicycleId;
    }

    public LocalDateTime getStartUsageDate() {
        return startUsageDate;
    }

    public void setStartUsageDate(LocalDateTime startUsageDate) {
        this.startUsageDate = startUsageDate;
    }

    public LocalDateTime getEndUsageDate() {
        return endUsageDate;
    }

    public void setEndUsageDate(LocalDateTime endUsageDate) {
        this.endUsageDate = endUsageDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BicycleReservation that = (BicycleReservation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(employee, that.employee) &&
                Objects.equals(bicycle, that.bicycle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, employee, bicycle);
    }
}
