/**
 * Created by Ratmach on 01.07.2015.
 */
var orderID=null;
var isUser=null;
function constructReview(order,user){
    orderID=order;
    isUser=user;
    $("#reviewModal").modal('show');
}
function addReview(){
    if(isUser){
        addReviewUser($("#reviewText").val(),$("#reviewRating").val());
    }else{
        addReviewDriver($("#reviewText").val(),$("#reviewRating").val());
    }
}
function addReviewUser(text,rating){
    if(orderID==null) return;
    $("#reviewModal").modal('hide');
    $.ajax({
        url: "/review",
        method: "post",
        data: {action:"addUserReview",description:text,rating:rating},
        cache: false,
        success: function (data) {
            $("#reviewModal").modal('hide');
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(data));
        }
    });
}
function addReviewDriver(text,rating){
    if(orderID==null) return;
    $("#reviewModal").modal('hide');
    $.ajax({
        url: "/review",
        method: "post",
        data: {action:"addDriverReview",description:text,rating:rating},
        cache: false,
        success: function (data) {
            $("#reviewModal").modal('hide');
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(data));
        }
    });
}