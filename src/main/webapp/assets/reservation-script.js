function getReservationsByEmployeeId(employee, callback) {
    $.get("/api/v1/reservations/employees/" + employee.id, function (reservations) {
        callback(reservations);
    });
}

function initializeReservationView() {
    initializeReservationDateRangePicker();
    var date = moment()
    var dateFrom = moment().format('YYYY-MM-DDT00:00:00.SSS') + 'Z';
    var dateTo = moment().format('YYYY-MM-DDT23:mm:ss.SSS') + 'Z';
    loadReservations(dateFrom, dateTo);
}

function initializeReservationDateRangePicker() {
    $('#reservations-date').daterangepicker({
        locale: {
            format: 'DD.MM.YYYY'
        },
        showCustomRangeLabel: false
    }, function (start, end) {
        var dateFrom = start.format('YYYY-MM-DDTHH:mm:ss.SSS') + 'Z';
        var dateTo = end.format('YYYY-MM-DDTHH:mm:ss.SSS') + 'Z';

        loadReservations(dateFrom, dateTo);
    });
}

function loadReservations(dateFrom, dateTo) {
    var request = {
        dateFrom: dateFrom,
        dateTo: dateTo
    };
    $.ajax({
        url: '/api/v1/bicycles/reservations/date',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(request)
    }).done(function (bicycles) {
        loadBicyclesWithReservationsOnDate(bicycles);
    });
}

function loadBicyclesWithReservationsOnDate(bicycles) {
    $("#bicycles-with-reservations").empty();
    $.each(bicycles, function (index, bicycle) {
        var row = document.createElement('tr');

        var bicycleColumn = document.createElement('td');
        bicycleColumn.innerHTML = "" + bicycle.model + " " + bicycle.manufacturer;

        var reservationsColumn = document.createElement('td');

        var reservationsInnerTable = document.createElement(('tbody'));
        $.each(bicycle.reservations, function (index, reservation) {
            var rowInner = document.createElement('tr');

            var reservationRangeColumn = document.createElement('td');
            reservationRangeColumn.innerHTML = moment(reservation.startUsageDate).format('MM.DD.YYYY HH:mm') + " - " + moment(reservation.endUsageDate).format('MM.DD.YYYY HH:mm');
            rowInner.append(reservationRangeColumn);
            var reservationEmployeeColumn = document.createElement('td');
            reservationEmployeeColumn.innerHTML = reservation.employee.name + " " + reservation.employee.surname;
            rowInner.append(reservationEmployeeColumn);

            reservationsInnerTable.append(rowInner);
        });
        reservationsColumn.append(reservationsInnerTable);
        row.append(bicycleColumn, reservationsColumn);
        $("#bicycles-with-reservations").append(row);
    });
}
