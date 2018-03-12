function getReservationsByEmployeeId(employee, callback) {
    $.get("/api/v1/reservations/employees/" + employee.id, function (reservations) {
        callback(reservations);
    });
}


