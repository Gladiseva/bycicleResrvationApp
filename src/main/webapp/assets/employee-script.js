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
