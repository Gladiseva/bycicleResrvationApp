package lv.lollija.bicyclereservation.presentation;

import lv.lollija.bicyclereservation.domain.Employee;
import lv.lollija.bicyclereservation.service.EmployeeService;
import lv.lollija.bicyclereservation.presentation.dto.EmployeeDTO;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/employees")
public class EmployeeController {

    @Inject
    private EmployeeService employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll().stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployeeDTO create(EmployeeDTO dto) {
        Employee employee = dto.toDomainObject();
        Employee savedEmployee = employeeService.create(employee);
        return new EmployeeDTO(savedEmployee);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployeeDTO update(EmployeeDTO dto) {
        Employee employee = dto.toDomainObject();
        Employee updatedEmployee = employeeService.update(employee);
        return new EmployeeDTO(updatedEmployee);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        employeeService.delete(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public EmployeeDTO getById(@PathParam("id") Long id) {

        Employee employee = employeeService.getById(id);
        return new EmployeeDTO(employee);
    }


}
