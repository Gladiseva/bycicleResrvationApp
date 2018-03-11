$(document).ready(function () {
    showEmployees();
});

function showEmployees() {
    $(".bicycles").hide();
    $(".reservations").hide();
    $(".employees").show();
    loadEmployees();
}

function showBicycles() {
    $(".reservations").hide();
    $(".employees").hide();
    $(".bicycles").show();
}

function showReservations() {
    $(".bicycles").hide();
    $(".employees").hide();
    $(".reservations").show();
}

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
            var editIcon = document.createElement('span');
            editIcon.className = "oi oi-pencil";
            editButton.appendChild(editIcon);
            editButton.type = "button";
            editButton.className = "btn btn-light custom-button";
            optionsColumn.appendChild(editButton);

            var deleteButton = document.createElement('button');
            var deleteIcon = document.createElement('span');
            deleteIcon.className = "oi oi-trash";
            deleteButton.appendChild(deleteIcon);
            deleteButton.type = "button";
            deleteButton.className = "btn btn-light custom-button";
            optionsColumn.appendChild(deleteButton);

            row.append(idColumn, nameColumn, surnameColumn, optionsColumn);
            $("#table-body").append(row);
        });
    })
}