package lv.lollija.bicyclereservation.presentation;

import lv.lollija.bicyclereservation.domain.Bicycle;
import lv.lollija.bicyclereservation.domain.BicycleReservation;
import lv.lollija.bicyclereservation.domain.Employee;
import lv.lollija.bicyclereservation.presentation.dto.BicycleReservationDTO;
import lv.lollija.bicyclereservation.presentation.dto.BicycleReservationExtendedDTO;
import lv.lollija.bicyclereservation.service.BicycleReservationService;
import lv.lollija.bicyclereservation.service.BicycleService;
import lv.lollija.bicyclereservation.service.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/reservations")
public class BicycleReservationController {
    @Inject
    private BicycleReservationService bicycleReservationService;
    @Inject
    private BicycleService bicycleService;
    @Inject
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BicycleReservationExtendedDTO> getAll() {
        return bicycleReservationService.getAll().stream()
                .map(BicycleReservationExtendedDTO::new)
                .collect(Collectors.toList());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public BicycleReservationDTO getById(@PathParam("id") Long id) {

        BicycleReservation bicycleReservation = bicycleReservationService.getById(id);
        return new BicycleReservationExtendedDTO(bicycleReservation);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BicycleReservationExtendedDTO create(BicycleReservationDTO dto) {
        Long employeeId = dto.getEmployeeId();
        Employee employee = employeeService.getById(employeeId);
        if (employee == null) {
            throw new NotFoundException("Employee not found by id " + employeeId);
        }
        Long bicycleId = dto.getBicycleId();
        Bicycle bicycle = bicycleService.getById(bicycleId);
        if (bicycle == null) {
            throw new NotFoundException("Bicycle not found by id " + bicycleId);
        }
        BicycleReservation bicycleReservation = dto.toDomainObject(employee, bicycle);
        BicycleReservation savedReservation = bicycleReservationService.create(bicycleReservation);
        return new BicycleReservationExtendedDTO(bicycleReservationService.getById(savedReservation.getId()));
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BicycleReservationExtendedDTO update(BicycleReservationDTO dto) {
        BicycleReservation reservation = bicycleReservationService.getById(dto.getId());
        BicycleReservation bicycleReservation = dto.populateDomainObject(reservation);
        BicycleReservation updatedReservation = bicycleReservationService.update(bicycleReservation);
        return new BicycleReservationExtendedDTO(updatedReservation);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        bicycleReservationService.delete(id);
    }
}
