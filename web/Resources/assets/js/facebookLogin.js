/**
 * Created by Tornike on 26.06.2015.
 */

 // This is called with the results from from FB.getLoginStatus().
function statusChangeCallbackLogin(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        testAPI_Login();
    } else if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.
       /* document.getElementById('loginStatus').innerHTML = 'Please log ' +
        'into this app.'; */
    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.
        /* document.getElementById('loginStatus').innerHTML = 'Please log ' +
        'into Facebook.'; */
    }
}

// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallbackLogin(response);
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
        statusChangeCallbackLogin(response);
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
function testAPI_Login() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
        console.log('Successful login for: ' + response.name);
      /*  document.getElementById('loginStatus').innerHTML =
            'Thanks for logging in, ' + response.name + '!';*/
        var facebookId = response.id;
        createFbLogin(facebookId);
    });
}
function createFbLogin (facebookId) {
    var objectName;
    if ($("#userLogin:checked").val() != undefined) {
        objectName = "loginFbUser";
    } else
    if ($("#driverLogin:checked").val() != undefined) {
        objectName = "loginFbDriver";
    }else
    if ($("#companyLogin:checked").val() != undefined) {
        objectName = "loginFbCompany";
    }
    $.ajax({
        url: "/login",
        method: "post",
        data: {
            "action" : objectName,
            "facebookID" : facebookId
        },
        cache: false,
        success: function (data) {
            console.log("Logged in\n" + JSON.stringify(facebookId));
            console.log(data);
            //$("#panel").load(data);
            $("footer").hide();
            $("#panel").html(data);
            $("#loginModal").modal('hide');
            $("#banner").hide();
            initializeMap();
        },
        error: function (data) {
            console.error("Couldn't log in\n" + JSON.stringify(facebookId));
        }
    });
}