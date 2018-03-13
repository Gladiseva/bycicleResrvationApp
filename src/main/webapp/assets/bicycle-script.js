function loadBicycles() {
    $("#table-bicycle-body").empty();
    $.get("/api/v1/bicycles", function (bicycles) {
        $.each(bicycles, function (index, bicycle) {
            var row = document.createElement('tr');

            var idColumn = document.createElement('td');
            idColumn.innerHTML = bicycle.id;

            var modelColumn = document.createElement('td');
            modelColumn.innerHTML = bicycle.model;

            var manufacturerColumn = document.createElement('td');
            manufacturerColumn.innerHTML = bicycle.manufacturer;

            var yearColumn = document.createElement('td');
            yearColumn.innerHTML = bicycle.yearProduced;

            var breakagesColumn = document.createElement('td');
            breakagesColumn.innerHTML = bicycle.breakages;

            var optionsColumn = document.createElement('td');

            var editButton = document.createElement('button');
            editButton.addEventListener("click", function () {
                editBicycle(bicycle);
            });
            var editIcon = document.createElement('span');
            editIcon.className = "oi oi-pencil";
            editButton.appendChild(editIcon);
            editButton.type = "button";
            editButton.className = "btn btn-light custom-button";
            optionsColumn.appendChild(editButton);

            var deleteButton = document.createElement('button');
            deleteButton.addEventListener("click", function () {
                deleteBicycle(bicycle);
            });
            var deleteIcon = document.createElement('span');
            deleteIcon.className = "oi oi-trash";
            deleteButton.appendChild(deleteIcon);
            deleteButton.type = "button";
            deleteButton.className = "btn btn-light custom-button";
            optionsColumn.appendChild(deleteButton);

            row.append(idColumn, modelColumn, manufacturerColumn, yearColumn, breakagesColumn, optionsColumn);
            $("#table-bicycle-body").append(row);
        });
    })
}

function deleteBicycle(bicycle) {
    $.ajax({url: "/api/v1/bicycles/" + bicycle.id, type: "DELETE"})
        .done(function () {
            loadBicycles();
        })
}

function saveBicycle() {
    var id = $("#id").val();
    if (id === undefined) {
        createBicycle();
    } else {
        updateBicycle(id);
    }
}

function editBicycle(bicycle) {
    $("#id").val(bicycle.id);
    $("#model").val(bicycle.model);
    $("#manufacturer").val(bicycle.manufacturer);
    $("#year").val(bicycle.yearProduced);
    $("#breakages").val(bicycle.breakages);
    $("#bicycle-modal").modal('show');
}

function updateBicycle(id) {
    var bicycle = {
            id: id,
            model: $("#model").val(),
            manufacturer: $("#manufacturer").val(),
            yearProduced: $("#year").val(),
            breakages: $("#breakages").val(),
        }
    ;

    $.ajax({
        url: '/api/v1/bicycles/',
        type: 'PUT',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(bicycle)
    }).done(function () {
        loadBicycles();
        $("#bicycle-modal").modal('hide');

    });
}

function addBicycle() {
    $("#id").val(undefined);
    $("#model").val("");
    $("#manufacturer").val("");
    $("#year").val("");
    $("#breakages").val("");
    $("#bicycle-modal").modal('show');
}

function createBicycle() {
    var bicycle = {
        model: $("#model").val(""),
        manufacturer: $("#manufacturer").val(""),
        yearProduced: $("#year").val(""),
        breakages: $("#breakages").val(""),
    };

    $.ajax({
        url: '/api/v1/bicycles/',
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: JSON.stringify(bicycle)
    }).done(function () {
        loadBicycles();
        $("#bicycle-modal").modal('hide');

    });
}
