function getReservationsByEmployeeId(employee, callback) {
    $.get("/api/v1/reservations/employees/" + employee.id, function (reservations) {
        callback(reservations);
    });
}

function initializeReservationView() {
    intializeDatePicker();
    var date = moment()
    var dateFrom = moment().format('YYYY-MM-DDT00:00:00.SSS') + 'Z';
    var dateTo = moment().format('YYYY-MM-DDT23:mm:ss.SSS') + 'Z';
    loadReservations(dateFrom, dateTo);
}

function intializeDatePicker() {
    $('#reservations-date').daterangepicker({
        locale: {
            format: 'MM.DD.YYYY'
        },
        showCustomRangeLabel: false,
        minDate: getFormattedCurrentDate()
    }, function (start, end, label) {
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
        reservationsColumn.innerHTML = "Reservations list";

        row.append(bicycleColumn, reservationsColumn);
        $("#bicycles-with-reservations").append(row);
    });
}

function getFormattedCurrentDate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1;

    var yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    return dd + '.' + mm + '.' + yyyy;
}
