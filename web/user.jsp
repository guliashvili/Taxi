<%@ page import="ge.taxistgela.bean.Order" %>
<%@ page import="ge.taxistgela.bean.User" %>
<%@ page import="ge.taxistgela.model.OrderManager" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Ratmach
  Date: 19.06.2015
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="Resources/assets/js/user.js"></script>
<section id="map" style="position:absolute;width:100%;height:100%;">

</section>
<section id="order" class="hidden" style="background-color:#FFD800;padding:10px;position: absolute; top: 10px; left: 10px; z-index: 99;">
    <div class='input-group date' id='datetimepicker10'>
        <label for="startDate">Please Desired Time Of Departure</label>
        <input type='text' id="startDate" name="startDate" class="form-control" />
        <span class="input-group-addon">
            <span class="glyphicon glyphicon-calendar">
            </span>
        </span>
    </div>
</section><!-- TOOD -->
<% User user = (User) session.getAttribute(User.class.getName());%>
<div class="prefPanel">
    <div class="row uniform" style="margin-left:10px">
        <br>

        <h2> Greetings <%= user.getFirstName()%> <br></h2>

        <div class="5u$">
            <a href="#"><h3>Your Current Rating: <%= user.getRating()%>
            </h3></a>
            <% for (int i = 1; i <= 5; ++i) { %>
            <% if (user.getRating() >= i) { %>
            <li class="fa fa-star"></li>
            <% } else { %>
            <% if (Math.ceil(user.getRating()) > i) { %>
            <li class="fa fa-star-half-o"></li>
            <% } else { %>
            <li class="fa fa-star-o"></li>
            <%} %>
            <%} %>
            <%} %>
        </div>
        <div class="12u$ (small) fit" style="float:left">
            <a href="#" onclick="$('.prefPanel').toggleClass('zero');$('#map').toggleClass('mOP');"
               class="button special small fa fa-bar-chart"> Add Order</a>
        </div>
        <br><br><br><br>

        <div class="6u 12u$(xsmall)">
            <input type="email" disabled value="<%=user.getEmail()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(user.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>">
                <% if (user.getIsVerifiedEmail()) {
                    out.println("Verified");
                } else {
                    out.println("Verify");
                } %>
            </button>
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="text" disabled value="<%=user.getPhoneNumber()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(user.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>">
                <% if (user.getIsVerifiedPhone()) {
                    out.println("Verified");
                } else {
                    out.println("Verify");
                } %>
            </button>
        </div>
        <div class="12u$">
            <button onclick="$('#cPass').toggleClass('hidden');" class="button special fa fa-key"> Change Password
            </button>
        </div>
        <div id="cPass" class="5u$ hidden">
            <form id="passForm" action="" type="post">
                <span> Current Password: </span>
                <input name="oldPassword" type="password" value=""/>
                <span> New Password: </span>
                <input name="password" type="password" value=""/>
                <span> Repeat Password: </span>
                <input type="password" value=""/><br>
                <input type="text" name="action" value="uPassword" class="hidden"/>
            </form>
            <button id="passChange" class="special button">Save</button>
        </div>
        <div style="float:right" class="4u$ (xsmall)">
            <a href="#" style="float:left"
               class="<%if(user.getGoogleID()!=null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">getGoogleID</span></a>
            <br><br>
            <a href="#" style="float:left" class="<%if(user.getFacebookID()!=null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">Facebook</span></a>
        </div>
        <div class="5u$ 12u$(small)">
            <a href="#" onclick="$('#cPref').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit
                Your Preferences</a>
        </div>
        <div id="cPref" class="6u$ hidden">
            <form id="prefForm" action="" type="post">
                <input type="text" name="action" value="uPreferences" class="hidden"/>
                <input type="number" id="minimumDriverRating" name="minimumDriverRating"
                       value="<%=user.getPreference().getPassengersCount()%>"
                       style="color:black;padding-left:5px" value="1" step="1">
                <label for="minimumDriverRating"> Minimum Driver Rating </label><br>
                <input type="checkbox" id="conditioning"
                       name="conditioning" <% if(user.getPreference().isConditioning()){%> checked <%}%>>
                <label for="conditioning"> Conditioning Required </label>
                </input><br>
                <input type="number" name="carYear" value="<%=user.getPreference().getCarYear()%>" id="carYear"
                       style="color:black;padding-left:5px" value="1990" step="1">
                <label for="carYear"> Minimum Car Year </label>
                <input type="number" name="timeLimit" id="timeLimit" value="<%=user.getPreference().getTimeLimit()%>"
                       style="color:black;padding-left:5px" value="10" step="1">
                <label for="timeLimit"> Maximum Time Limit (Minutes) </label>
                <input type="number" name="passengerCount" id="passengerCount"
                       value="<%=user.getPreference().getPassengersCount()%>"
                       style="color:black;padding-left:5px" value="1" step="1">
                <label for="passengerCount"> Passenger Count </label><br>
                <input type="checkbox" name="wantsAlone" id="wantsAlone"
                       value="<%if(user.getPreference().isWantsAlone()){%>checked<%}%>"
                       name="wantsAlone">
                <label for="wantsAlone"> Want To Travel Alone </label>
                </input>
                <button id="savePref" style="margin-top:5px" class="button special"> Save</button>
                <br>
            </form>
        </div>
        <div class="12u 1u$(small)" style="float:left">
            <a href="#" onclick="$('#history').toggleClass('hidden');" class="button special small fa fa-bar-chart">
                View Order History</a>
        </div>
        <div id="history" class="12 1u$ hidden" >
            <div id="grid">

            </div>
        </div>
    </div>
</div>
<script>
    function generateGrid(){
        $('#grid').w2grid({
            name: 'grid',
            header: 'List of Names',
            columns: [
                { field: 'Date', caption: 'Date', size: '30%' },
                { field: 'callTime', caption: 'Call Time', size: '30%' },
                { field: 'Driver', caption: 'Driver', size: '30px' },
                { field: 'StLoc', caption: 'Start', size: '30px' },
                { field: 'EndLoc', caption: 'End', size: '30px' },
                { field: 'paymentAmount', caption: 'Amount', size: '30px' }
            ],
            records: [
            <% OrderManager man = (OrderManager) application.getAttribute(OrderManager.class.getName());
                if (man != null) {
                    List<Order> orders = man.getOrderByUserID(user.getUserID());
                    for (Order ord : orders) {
                        %>{ Date: <%=ord.getStartTime()%>, callTime: <%=ord.getCallTime()%>, Driver: <%=ord.getDriverID()%>, StLoc: <%=ord.getStartLocation()%>, EndLoc: <%=ord.getEndLocation()%>,paymentAmount:<%=ord.getPaymentAmount()%> },<%
                    }
                }
            %>
            ]
        });
    }
</script>
