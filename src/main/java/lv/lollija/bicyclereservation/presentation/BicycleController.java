package lv.lollija.bicyclereservation.presentation;


import lv.lollija.bicyclereservation.domain.Bicycle;
import lv.lollija.bicyclereservation.presentation.dto.BicycleDTO;
import lv.lollija.bicyclereservation.service.BicycleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/bicycles")
public class BicycleController {
    @Inject
    private BicycleService bicycleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BicycleDTO> getAll() {
        return bicycleService.getAll().stream()
                .map(BicycleDTO::new)
                .collect(Collectors.toList());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BicycleDTO create(BicycleDTO dto) {
        Bicycle bicycle = dto.toDomainObject();
        Bicycle savedBicycle = bicycleService.create(bicycle);
        return new BicycleDTO(savedBicycle);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BicycleDTO update(BicycleDTO dto) {
        Bicycle bicycle = dto.toDomainObject();
        Bicycle updatedBicycle = bicycleService.update(bicycle);
        return new BicycleDTO(updatedBicycle);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        bicycleService.delete(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public BicycleDTO getById(@PathParam("id") Long id) {

        Bicycle bicycle = bicycleService.getById(id);
        return new BicycleDTO(bicycle);
    }
}
