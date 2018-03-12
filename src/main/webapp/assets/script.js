$(document).ready(function () {
    showEmployees();
});

function showEmployees() {
    $("#bicycles").hide();
    $("#reservations").hide();
    $("#employees").show();
    $(".nav-item").removeClass("active");
    $("#employee-menu-item").addClass("active");
    loadEmployees();
}

function showBicycles() {
    $("#reservations").hide();
    $("#employees").hide();
    $("#bicycles").show();
    $(".nav-item").removeClass("active");
    $("#bicycle-menu-item").addClass("active");
    loadBicycles();
}

function showReservations() {
    $("#bicycles").hide();
    $("#employees").hide();
    $("#reservations").show();
    $(".nav-item").removeClass("active");
    $("#reservation-menu-item").addClass("active");
}