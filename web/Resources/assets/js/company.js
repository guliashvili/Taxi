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
            console.log(data);
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
            console.log(data);
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
            history=data;
            for (var i=0;i<records.length;++i){
                history[i]['recid']= "<button class='special fit' onclick='constructReview("+history[i].orderID+",true)'>+</button>";
                if(history[i].revokedByDriver){
                    history[i]['style']="background-color:red";
                }
                if(history[i].revokedByUser){
                    history[i]['style']="background-color:green";
                }
                var recM=[history[i].startLocation.latitude,history[i].startLocation.longitude,history[i].endLocation.latitude,history[i].endLocation.longitude];
                console.log(recM);
                history[i]['startLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[0]+','+recM[1]+','+recM[2]+','+recM[3]+");'>";
                history[i]['endLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[2]+','+recM[3]+','+recM[0]+','+recM[1]+");'>";
            }
            console.log(data);
            generateHistoryGrid();
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
            for (var i=0;i<records.length;++i){
                drivers_T[i]['recid']= "<button class='special fit' onclick='constructReview("+drivers_T[i].orderID+",true)'>+</button>";
                if(drivers_T[i].revokedByDriver){
                    drivers_T[i]['style']="background-color:red";
                }
                if(drivers_T[i].revokedByUser){
                    drivers_T[i]['style']="background-color:green";
                }
                var recM=[drivers_T[i].startLocation.latitude,drivers_T[i].startLocation.longitude,drivers_T[i].endLocation.latitude,drivers_T[i].endLocation.longitude];
                console.log(recM);
                drivers_T[i]['startLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[0]+','+recM[1]+','+recM[2]+','+recM[3]+");'>";
                drivers_T[i]['endLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[2]+','+recM[3]+','+recM[0]+','+recM[1]+");'>";
            }
            console.log(data);
            generateDriverGrid();
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
                reviews[i]['recid']= "<button class='special fit' onclick='constructReview("+reviews[i].orderID+",true)'>+</button>";
                if(reviews[i].revokedByDriver){
                    reviews[i]['style']="background-color:red";
                }
                if(reviews[i].revokedByUser){
                    reviews[i]['style']="background-color:green";
                }
                var recM=[reviews[i].startLocation.latitude,reviews[i].startLocation.longitude,reviews[i].endLocation.latitude,reviews[i].endLocation.longitude];
                console.log(recM);
                reviews[i]['startLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[0]+','+recM[1]+','+recM[2]+','+recM[3]+");'>";
                reviews[i]['endLocation']="<img src='http://www.iconarchive.com/download/i75881/martz90/circle/maps.ico' style='width:16px;height:16px' onclick='pinpoint("+recM[2]+','+recM[3]+','+recM[0]+','+recM[1]+");'>";
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
var history;
function generateHistoryGrid(){
    $('#gridHistory').w2grid({
        name: 'gridHistory',
        header: 'Orders',
        show: {
            toolbar: false,
            footer: false
        },
        columns: [
            { field: 'recid', caption: 'Review', size: '10%', sortable: true, attr: 'align=center' },
            { field: 'driverID', caption: 'driverID', size: '5%', sortable: true, resizable: false },
            { field: 'numPassengers', caption: 'numPassengers', size: '5%', resizable: true },
            { field: 'startLocation', caption: 'startLocation', size: '5%', resizable: true },
            { field: 'endLocation', caption: 'endLocation', size: '5%', resizable: true },
            { field: 'endTime', caption: 'endTime', size: '30%', resizable: true },
            { field: 'paymentAmount', caption: 'paymentAmount', size: '10%', resizable: true },
            { field: 'callTime', caption: 'callTime', size: '30%', resizable: true },
        ],
        searches: [
            { field: 'driverID', caption: 'driverID', type: 'text' },
            { field: 'orderID', caption: 'orderID', type: 'text' },
        ],
        sortData: [{ field: 'orderID', direction: 'ASC' }],
        records: history
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
            { field: 'recid', caption: 'driverID', size: '10%', sortable: true, attr: 'align=center' },
            { field: 'numPassengers', caption: 'numPassengers', size: '5%', resizable: true },
            { field: 'startLocation', caption: 'startLocation', size: '5%', resizable: true },
            { field: 'endLocation', caption: 'endLocation', size: '5%', resizable: true },
            { field: 'endTime', caption: 'endTime', size: '30%', resizable: true },
            { field: 'paymentAmount', caption: 'paymentAmount', size: '10%', resizable: true },
            { field: 'callTime', caption: 'callTime', size: '30%', resizable: true },
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
            { field: 'recid', caption: 'Review', size: '10%', sortable: true, attr: 'align=center' },
            { field: 'driverID', caption: 'driverID', size: '5%', sortable: true, resizable: false },
            { field: 'numPassengers', caption: 'numPassengers', size: '5%', resizable: true },
            { field: 'startLocation', caption: 'startLocation', size: '5%', resizable: true },
            { field: 'endLocation', caption: 'endLocation', size: '5%', resizable: true },
            { field: 'endTime', caption: 'endTime', size: '30%', resizable: true },
            { field: 'paymentAmount', caption: 'paymentAmount', size: '10%', resizable: true },
            { field: 'callTime', caption: 'callTime', size: '30%', resizable: true },
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
    //TODO
}