/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

/**
 * Created by Alex on 6/18/2015.
 */

/**
 * Created by Alex on 6/18/2015.
 */

// Here goes user panel management.
$(document).ready(function(){
    initializeO();
});
function resendEmail(){
    $.ajax({
        url: "/sendverification",
        method: "post",
        data: {action: "cEmail"},
        cache: false,
        success: function (data) {
            //console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function resendPhone(){
    $.ajax({
        url: "/sendverification",
        method: "post",
        data: {action: "cPhone"},
        cache: false,
        success: function (data) {
            //console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function initializeO(){
    // checkLoginState();
    $(".headCaption").removeClass("hidden");
    initializeMap();
    createPreferencesSaves();
    $.ajax({
        url: "/company",
        method: "post",
        data: {action:"getOrders"},
        cache: false,
        success: function (data) {
            historyT = data;
            for (var i = 0; i < historyT.length; ++i) {
                historyT[i]['recid'] = historyT[i].driverID;
                if (historyT[i].revokedByDriver) {
                    historyT[i]['style'] = "background-color:red";
                }
                if (historyT[i].revokedByUser) {
                    historyT[i]['style'] = "background-color:green";
                }
            }
            //console.log(data);
            generatehistoryGrid();
        },
        error: function (data) {
            console.error(data);
        }
    });
    $.ajax({
        url: "/company",
        method: "post",
        data: {action:"getDrivers"},
        cache: false,
        success: function (data) {
            drivers_T=data;
            for (var i = 0; i < drivers_T.length; ++i) {
                drivers_T[i]['recid'] = "<button class='special fit' onclick='banDriver(" + drivers_T[i].driverID + ",true)'>+</button>";
                if(drivers_T[i].revokedByDriver){
                    drivers_T[i]['style']="background-color:red";
                }
                if(drivers_T[i].revokedByUser){
                    drivers_T[i]['style']="background-color:green";
                }
                drivers_T[i]['driverID'] = "<h4 style='color:black'>" + drivers_T[i].car.carID + "</h4>";
            }
            generateDriverGrid();
            console.log("aaa");
        },
        error: function (data) {
            console.error(data);
        }
    });
    $.ajax({
        url: "/company",
        method: "post",
        data: {action:"getReviews"},
        cache: false,
        success: function (data) {
            reviews=data;
            for (var i=0;i<reviews.length;++i){
                reviews[i]['recid'] = reviews[i].reviewID;
                reviews[i]['description'] = "<textarea>" + reviews[i].description + "</textarea>";
                if (reviews[i].orientationFlag) {
                    reviews[i]['style'] = "background-color:green";
                } else {
                    reviews[i]['style'] = "background-color:red";
                }
            }
            console.log(data);
            generateReviewGrid();
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function createPreferencesSaves(){
    $("#passChange").click(function(e){
        var formData = $("#passForm").serialize();
        $.ajax({
            url: "/update",
            method: "post",
            data: formData,
            cache: false,
            success: function (data) {
                console.log(data);
            },
            error: function (data) {
                console.error("Couldn't log in\n" + JSON.stringify(formData));
            }
        });
    });
}
var historyT;
function generatehistoryGrid() {
    $('#gridHistory').w2grid({
        name: 'gridHistory',
        header: 'Orders',
        show: {
            toolbar: false,
            footer: false
        },
        columns: [
            {field: 'recid', caption: 'OrderID', size: '10%', sortable: true, attr: 'align=center'},
            { field: 'driverID', caption: 'driverID', size: '5%', sortable: true, resizable: false },
            { field: 'startLocation', caption: 'startLocation', size: '5%', resizable: true },
            { field: 'endLocation', caption: 'endLocation', size: '5%', resizable: true },
            { field: 'endTime', caption: 'endTime', size: '30%', resizable: true },
            {field: 'numPassengers', caption: 'Passengers', size: '30%', resizable: true},
            { field: 'callTime', caption: 'callTime', size: '30%', resizable: true },
        ],
        searches: [
            { field: 'driverID', caption: 'driverID', type: 'text' },
            { field: 'orderID', caption: 'orderID', type: 'text' },
        ],
        sortData: [{ field: 'orderID', direction: 'ASC' }],
        records: historyT
    });
}
var drivers_T;
function generateDriverGrid(){
    $('#gridDrivers').w2grid({
        name: 'gridDrivers',
        header: 'Drivers',
        show: {
            toolbar: false,
            footer: false
        },
        columns: [
            {field: 'recid', caption: 'driverID', size: '10%', sortable: true, attr: 'align=center', resizable: false},
            {field: 'driverID', caption: 'driverID', size: '80px', resizable: false},
            {field: 'firstName', caption: 'firstName', size: '100px', resizable: true},
            {field: 'lastName', caption: 'lastName', size: '100px', resizable: true},
            {field: 'personalID', caption: 'personalID', size: '100px', resizable: true},
            {field: 'phoneNumber', caption: 'Phone', size: '100px', resizable: true},
            {field: 'rating', caption: 'rating', size: '30%', resizable: true},
        ],
        searches: [
            { field: 'driverID', caption: 'driverID', type: 'text' },
            { field: 'orderID', caption: 'orderID', type: 'text' },
        ],
        sortData: [{ field: 'orderID', direction: 'ASC' }],
        records: drivers_T
    });
}
var reviews;
function generateReviewGrid(){
    $('#gridReview').w2grid({
        name: 'gridReview',
        header: 'Orders',
        show: {
            toolbar: false,
            footer: false
        },
        columns: [
            {field: 'recid', caption: 'reviewID', size: '0%', sortable: true, resizable: false, attr: 'align=center'},
            {field: 'orderID', caption: 'ID', size: '10%', sortable: true, resizable: false},
            {field: 'numPassengers', caption: 'Passangers', size: '5%', resizable: false},
            {field: 'rating', caption: 'Rating', size: '80px', resizable: false},
            {field: 'description', caption: 'description', size: '60%', resizable: true},
        ],
        searches: [
            { field: 'driverID', caption: 'driverID', type: 'text' },
            { field: 'orderID', caption: 'orderID', type: 'text' },
        ],
        sortData: [{ field: 'orderID', direction: 'ASC' }],
        records: reviews
    });
}
function banDriver(driverID){
    $.ajax({
        url: "/company",
        method: "post",
        data: {action: "fireDriver", driverID: driverID},
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.error(data);
        }
    });
}
function getPlate(plate) {
    var img = new Image;
    var canvas, ctx;
    canvas = document.createElement('canvas');
    canvas.width = 351;
    canvas.height = 83;
    ctx = canvas.getContext('2d');
    img.onload = function () {
        ctx.drawImage(img, 0, 0);
        ctx.font = "bold 32px Arial";
        ctx.fillText(plate, 30, 35);
    };
    img.src = "/Resources/images/gela.jpg";
    return canvas.toDataURL();
}
