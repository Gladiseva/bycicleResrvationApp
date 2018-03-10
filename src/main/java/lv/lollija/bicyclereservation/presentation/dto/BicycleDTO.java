package lv.lollija.bicyclereservation.presentation.dto;

import lv.lollija.bicyclereservation.domain.Bicycle;

public class BicycleDTO {
    private Long id;
    private String model;
    private String manufacturer;
    private Integer yearProduced;

    private String breakages;

    public BicycleDTO() {
    }

    public BicycleDTO(Bicycle bicycle) {
        this.id = bicycle.getId();
        this.model = bicycle.getModel();
        this.manufacturer = bicycle.getManufacturer();
        this.yearProduced = bicycle.getYearProduced();
        this.breakages = bicycle.getBreakages();

    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Integer getYearProduced() {
        return yearProduced;
    }

    public String getBreakages() {
        return breakages;
    }

    public Bicycle toDomainObject() {
        Bicycle bicycle = new Bicycle();
        bicycle.setId(id);
        bicycle.setModel(model);
        bicycle.setManufacturer(manufacturer);
        bicycle.setYearProduced(yearProduced);
        bicycle.setBreakages(breakages);
        return bicycle;
    }
}
