package lv.lollija.bicyclereservation.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "bicycle")
public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "bicycle_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;


    @Length(min = 2)
    @NotEmpty
    @Column(name = "model")
    private String model;

    @Length(min = 2)
    @NotEmpty
    @Column(name = "manufacturer")
    private String manufacturer;

    @NotNull
    @Column(name = "year_produced")
    private Integer yearProduced;

    @Column(name = "breakages")
    private String breakages;

    @OneToMany(mappedBy = "bicycle", fetch = FetchType.LAZY)
    private Set<BicycleReservation> reservations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getYearProduced() {
        return yearProduced;
    }

    public void setYearProduced(Integer yearProduced) {
        this.yearProduced = yearProduced;
    }

    public String getBreakages() {
        return breakages;
    }

    public void setBreakages(String breakages) {
        this.breakages = breakages;
    }

    public Set<BicycleReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<BicycleReservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bicycle bicycle = (Bicycle) o;
        return Objects.equals(id, bicycle.id) &&
                Objects.equals(model, bicycle.model) &&
                Objects.equals(manufacturer, bicycle.manufacturer) &&
                Objects.equals(yearProduced, bicycle.yearProduced);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, model, manufacturer, yearProduced);
    }
}
