/**
 * Created by Tornike on 26.06.2015.
 */

/**
 * Created by Tornike on 26.06.2015.
 */

// This is called with the results from from FB.getLoginStatus().
function statusChangeCallbackRegister(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        testAPI_Register();
    } else if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.
        /*document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';*/
    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.
        /*document.getElementById('status').innerHTML = 'Please log ' +
        'into Facebook.';*/
    }
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkRegisterState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallbackRegister(response);
    });
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : '771449059639215',
        cookie     : true,  // enable cookies to allow the server to access
                            // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.2' // use version 2.2
    });

    // Now that we've initialized the JavaScript SDK, we call
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
        statusChangeCallbackRegister(response);
    });
};

// Load the SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function testAPI_Register() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
     /*   document.getElementById('status').innerHTML =
            'Thanks for logging in, ' + response.name + '!'; */
        var facebookId = response.id;
        var nameAndSurname = response.name;
        var email = response.email;
        var phoneNumber = response.phoneNumber;
        var name = nameAndSurname.split(" ", 1);
        var surname = "";
        var sex = response.gender;

        if (nameAndSurname.indexOf(' ') != -1)
            surname = nameAndSurname.substr(nameAndSurname.indexOf(' ') + 1, nameAndSurname.length - name.length - 1);
        if ($("#userReg:checked").val() != undefined) {
            if (facebookId != undefined){
                $("#userfacebookId").val(facebookId);
            }
            if (sex != undefined){
                if (sex == "male") {
                    $("#usergender").val('Male');
                }
                else {
                    $("#usergender").val('Female');
                }
            }
            if (name != "undefined") {
                $("#userfirstName").val(name);
            }
            if (surname != "undefined") {
                $("#userlastName").val(surname);
            }
            if (email != "undefined"){
                $("#useremail").val(email);
            }
            if (phoneNumber != "undefined"){
                $("#userphoneNumber").val(phoneNumber);
            }
        } else
        if ($("#driverReg:checked").val() != undefined) {
            if (facebookId != undefined){
                $("#driverfacebookId").val(facebookId);
            }
            if (name != "undefined") {
                $("#driverfirstName").val(name);
            }
            if (surname != "undefined") {
                $("#driverlastName").val(surname);
            }
            if (email != "undefined"){
                $("#driveremail").val(email);
            }
            if (phoneNumber != "undefined"){
                $("#driverphoneNumber").val(phoneNumber);
            }
            if (sex != undefined){
                if (sex == "male") {
                    $("#drivergender").val('Male');
                }
                else {
                    $("#drivergender").val('Female');
                }
            }
        }else
        if ($("#companyReg:checked").val() != undefined) {
            if (facebookId != undefined){
                $("#companyfacebookId").val(facebookId);
            }
            if (name != "undefined") {
                $("#companyName").val(name);
            }
            if (email != "undefined"){
                $("#companyemail").val(email);
            }
            if (phoneNumber != "undefined"){
                $("#companyphoneNumber").val(phoneNumber);
            }
        }
    });
}