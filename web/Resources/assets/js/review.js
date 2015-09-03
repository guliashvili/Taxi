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
 * Created by Ratmach on 01.07.2015.
 */
var orderID = null;
var isUser = null;
function constructReview(order, user) {
    orderID = order;
    isUser = user;
    $("#reviewModal").modal('show');
}
function addReview() {
    if (isUser) {
        addReviewUser($("#reviewText").val(), $("#reviewRating").val());
    } else {
        addReviewDriver($("#reviewText").val(), $("#reviewRating").val());
    }
}
function addReviewUser(text, rating) {
    if (orderID == null) return;
    $("#reviewModal").modal('hide');
    $.ajax({
        url: "/review",
        method: "post",
        data: {action: "addUserReview", orderID: orderID, description: text, rating: rating},
        cache: false,
        success: function (data) {
            $("#reviewModal").modal('hide');
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(data));
        }
    });
}
function addReviewDriver(text, rating) {
    if (orderID == null) return;
    $("#reviewModal").modal('hide');
    $.ajax({
        url: "/review",
        method: "post",
        data: {action: "addDriverReview", orderID: orderID, description: text, rating: rating},
        cache: false,
        success: function (data) {
            $("#reviewModal").modal('hide');
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(data));
        }
    });
}