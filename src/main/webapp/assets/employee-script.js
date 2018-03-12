function loadEmployees() {
    $("#table-body").empty();
    $.get("/api/v1/employees", function (employees) {
        $.each(employees, function (index, employee) {
            var row = document.createElement('tr');

            var idColumn = document.createElement('td');
            idColumn.innerHTML = employee.id;

            var nameColumn = document.createElement('td');
            nameColumn.innerHTML = employee.name;

            var surnameColumn = document.createElement('td');
            surnameColumn.innerHTML = employee.surname;

            var optionsColumn = document.createElement('td');

            var editButton = document.createElement('button');
            editButton.addEventListener("click", function () {
                editEmployee(employee);
            });
            var editIcon = document.createElement('span');
            editIcon.className = "oi oi-pencil";
            editButton.appendChild(editIcon);
            editButton.type = "button";
            editButton.className = "btn btn-light custom-button";
            optionsColumn.appendChild(editButton);

            var deleteButton = document.createElement('button');
            deleteButton.addEventListener("click", function () {
                deleteEmployee(employee);
            });
            var deleteIcon = document.createElement('span');
            deleteIcon.className = "oi oi-trash";
            deleteButton.appendChild(deleteIcon);
            deleteButton.type = "button";
            deleteButton.className = "btn btn-light custom-button";
            optionsColumn.appendChild(deleteButton);

            var reservationButton = document.createElement('button');
            reservationButton.addEventListener("click", function () {
                makeReservation(employee);
            });
            var reservationIcon = document.createElement('i');
            reservationIcon.className = "fas fa-bicycle";
            reservationButton.appendChild(reservationIcon);
            reservationButton.type = "button";
            reservationButton.className = "btn btn-light custom-button";
            optionsColumn.appendChild(reservationButton);

            row.append(idColumn, nameColumn, surnameColumn, optionsColumn);
            $("#table-body").append(row);
        });
    })
}

function deleteEmployee(employee) {
    $.ajax({url: "/api/v1/employees/" + employee.id, type: "DELETE"})
        .done(function () {
            loadEmployees();
        })
}

function saveEmployee() {
    var id = $("#form-group-id-input").val();
    if (id === undefined) {
        createEmployee();
    } else {
        updateEmployee(id);
    }
}

function editEmployee(employee) {
    $("#form-group-id-input").val(employee.id);
    $("#form-group-name-input").val(employee.name);
    $("#form-group-surname-input").val(employee.surname);
    $("#user-modal").modal('show');
}

function makeReservation(employee) {
    getReservationsByEmployeeId(employee, function (reservations) {
        $('#made-reservations').empty();
        for (var i = 0; i < reservations.length; i++) {
            var optionInSelect = document.createElement('option');
            optionInSelect.value = reservations[i];
            optionInSelect.text = "" + reservations[i].startUsageDate + " " + reservations[i].endUsageDate;
            $('#made-reservations').append(optionInSelect);
        }
        $("#make-reservation-modal").modal('show');
        $("#reservation-employee-id").val(employee.id);
        initializeDateRangePicker();
    });
}

function initializeDateRangePicker() {
    $('#bicycle-reservation-period').daterangepicker(
        {
            locale: {
                format: 'MM.DD.YYYY HH:mm'
            },
            timePicker: true,
            timePicker24Hour: true,
            timePickerIncrement: 30,
            minDate: getFormattedCurrentDate()
        },
        function(start, end, label) {
            var dateFrom = start.format('YYYY-MM-DDTHH:mm:ss.SSS') + 'Z';
            var dateTo = end.format('YYYY-MM-DDTHH:mm:ss.SSS') + 'Z';
            var request = {
                dateFrom: dateFrom,
                dateTo: dateTo
            };
            $("#reservation-date-from").val(dateFrom);
            $("#reservation-date-to").val(dateTo);

            $.ajax({
                url: '/api/v1/bicycles/date',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: JSON.stringify(request)
            }).done(function (bicycles) {
                $('#available-bicycles').empty();
                $.each(bicycles, function(index, bicycle) {
                    var optionInSelect = document.createElement('option');
                    optionInSelect.value = bicycle.id;
                    optionInSelect.text = bicycle.model + " " + bicycle.manufacturer + " (" + bicycle.yearProduced + ")";
                    $('#available-bicycles').append(optionInSelect);
                });
            });
        });
}

function getFormattedCurrentDate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var hh = today.getHours();

    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd;
    }
    if(mm<10){
        mm='0'+mm;
    }
    return dd+'.'+mm+'.'+yyyy+' '+hh+':00';
}

function updateEmployee(id) {
    var employee = {
        id: id,
        name: $("#form-group-name-input").val(),
        surname: $("#form-group-surname-input").val(),
    };

    $.ajax({
        url: '/api/v1/employees/',
        type: 'PUT',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(employee)
    }).done(function () {
        loadEmployees();
        $("#user-modal").modal('hide');

    });
}

function addEmployee() {
    $("#form-group-id-input").val(undefined);
    $("#form-group-name-input").val("");
    $("#form-group-surname-input").val("");
    $("#user-modal").modal('show');
}

function createEmployee() {
    var employee = {
        name: $("#form-group-name-input").val(),
        surname: $("#form-group-surname-input").val(),
    };

    $.ajax({
        url: '/api/v1/employees/',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(employee)
    }).done(function () {
        loadEmployees();
        $("#user-modal").modal('hide');

    });
}

function reserve() {
    var reservation = {
        employeeId: $("#reservation-employee-id").val(),
        bicycleId: $("#available-bicycles").val(),
        startUsageDate: $("#reservation-date-from").val(),
        endUsageDate: $("#reservation-date-to").val()
    };

    $.ajax({
        url: '/api/v1/reservations/',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(reservation)
    }).done(function () {
        $("#make-reservation-modal").modal('hide');
    });
}



