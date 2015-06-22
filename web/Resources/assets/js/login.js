/**
 * Created by Alex on 6/18/2015.
 */


// Login management goes here.

$(function () {
    $('#loginBtn').click(function () {
        console.log(JSON.stringify($("#loginForm").serialize()));
        return false;
    });
});