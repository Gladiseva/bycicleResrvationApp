$(document).ready(function () {
    showEmployees();
    $("#submit-btn").click(function (e) {
        e.preventDefault();
        var name = $("#form-group-name-input").val();
        var surname = $("#form-group-surname-input").val();
        if (name != '' && surname != '') {
            saveEmployee()
        } else {
            alert("Please Fill All Fields.");
        }
    });
});

function showEmployees() {
    $("#bicycles").hide();
    $("#reservations").hide();
    $("#reservations-by-date").hide();
    $("#employees").show();
    $(".nav-item").removeClass("active");
    $("#employee-menu-item").addClass("active");
    loadEmployees();
}

function showBicycles() {
    $("#reservations").hide();
    $("#employees").hide();
    $("#reservations-by-date").hide();
    $("#bicycles").show();
    $(".nav-item").removeClass("active");
    $("#bicycle-menu-item").addClass("active");
    loadBicycles();
}

function showReservations() {
    $("#bicycles").hide();
    $("#employees").hide();
    $("#reservations").show();
    $("#reservations-by-date").show();
    $(".nav-item").removeClass("active");
    $("#reservation-menu-item").addClass("active");
    initializeReservationView();
}