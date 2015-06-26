<%@ page import="ge.taxistgela.bean.Driver" %>
<%@ page import="ge.taxistgela.model.OrderManager" %>
<%@ page import="ge.taxistgela.bean.Order" %>
<%@ page import="java.util.List" %>
\<%--
  Created by IntelliJ IDEA.
  User: Ratmach
  Date: 19.06.2015
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="Resources/assets/js/driver.js"></script>
<% Driver driver = (Driver) session.getAttribute(Driver.class.getName());%>
<section id="map" style="position:absolute;width:100%;height:100%;">

</section>
<div class="prefPanel">
    <div class="row uniform" style="margin-left:10px">
        <br>

        <h2> Greetings <%=driver.getFirstName()%> <br></h2>

        <div class="5u$">
            <a href="#"><h3>Your Current Rating: <%= driver.getRating()%>
            </h3></a>
            <% for (int i = 1; i <= 5; ++i) { %>
            <% if (driver.getRating() >= i) { %>
            <li class="fa fa-star"></li>
            <% } else { %>
            <% if (Math.ceil(driver.getRating()) > i) { %>
            <li class="fa fa-star-half-o"></li>
            <% } else { %>
            <li class="fa fa-star-o"></li>
            <%} %>
            <%} %>
            <%} %>
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="email" disabled value="<%=driver.getEmail()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(driver.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>">
                <% if (driver.getIsVerifiedEmail()) {
                    out.println("Verified");
                } else {
                    out.println("Verify");
                } %>
            </button>
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="text" disabled value="<%=driver.getPhoneNumber()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(driver.getIsVerifiedEmail()){out.println("disabled verified");}else{out.println("special");}%>">
                <% if (driver.getIsVerifiedPhone()) {
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
                <button id="passChange" class="special button">Save</button>
                <input type="text" name="action" value="dPassword" class="hidden"/>
            </form>
        </div>
        <div style="float:right" class="4u$ (xsmall)">
            <a href="#" style="float:left"
               class="<%if(driver.getGoogleID()!=null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">getGoogleID</span></a>
            <br><br>
            <a href="#" style="float:left" class="<%if(driver.getFacebookID()!=null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">Facebook</span></a>
        </div>
        <div class="5u$ 12u$(small)">
            <a href="#" onclick="$('#cP').toggleClass('hidden');" class="button special small fa fa-adjust"> Register To
                Company</a>
        </div>
        <div id="cP" class="5u$ 12u$(small) hidden">
            <form id="companyCodeForm" action="" type="post">
                <input name="companyCode" type="text" placeholder="Company Code"/><br>
                <button id="companyCodeBtn" style="float:right;" class="button special">Register</button>
                <input type="text" name="action" value="dCompanyCode" class="hidden"/>
            </form>
        </div>
        <div class="5u$ 12u$(small)">
            <a href="#" onclick="$('#cPref').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit Your Preferences</a>
        </div>
        <div class="5u$ 12u$(small)">
            <a href="#" onclick="$('#cPref').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit Your Preferences</a>
        </div>
        <div id="cPref" class="6u$ hidden">
            <form id="cPrefForm">
                <input type="text" name="action" value="dPreferences" class="hidden"/>
                <label for="minimumUserRating"> Minimum User Rating </label>
                <input type="number" id="minimumUserRating" name="minimumUserRating" value="<%=driver.getPreferences().getMinimumUserRating()%>" step="1"/>
                <label for="coefficientPer"> Coefficient Per KM. </label>
                <input type="number" id="coefficientPer" name="coefficientPer" value="<%=driver.getPreferences().getCoefficientPer()%>" step=".1"/>
            </form>
            <button id="cPrefBtn" class="button special small fa fa-adjust"> Save </button>
        </div>
        <div class="12u$(small)">
            <a href="#" onclick="$('#cCar').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit Your Car Details</a>
        </div><br>
        <div id="cCar" class="12u hidden">
            <form id="cCarForm">
                <input type="text" name="action" value="dCar" class="hidden"/>
                <input type="checkbox" id="conditioning" name="conditioning" <%if(driver.getCar().hasConditioning()){out.println("checked");}%>>
                    <label for="conditioning"> Conditioning </label>
                </input><br>
                <label for="carDescription"> Car Description </label>
                <textarea id="carDescription" name="carDescription" style="width:60%;font-size:1em"><%=driver.getCar().getCarDescription()%></textarea>
                <label for="carYear"> Car Year </label>
                <input type="number" id="carYear" name="carYear" value="<%=driver.getCar().getCarYear()%>" step="1"/>
                <label for="numPassengers"> Max. Number Of Passengers </label>
                <input type="number" id="numPassengers" name="numPassengers" value="<%=driver.getCar().getNumPassengers()%>" step="1"/><br>
            </form>
            <button id="cCarBtn" class="button special small fa fa-adjust"> Save </button>
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
                { field: 'User', caption: 'User', size: '30px' },
                { field: 'StLoc', caption: 'Start', size: '30px' },
                { field: 'EndLoc', caption: 'End', size: '30px' },
                { field: 'paymentAmount', caption: 'Amount', size: '30px' }
            ],
            records: [
                    <% OrderManager man = (OrderManager) application.getAttribute(OrderManager.class.getName());
                        if (man != null) {
                            List<Order> orders = man.getOrderByUserID(driver.getDriverID());
                            for (Order ord : orders) {
                                %>{ Date: <%=ord.getStartTime()%>, callTime: <%=ord.getCallTime()%>, User: <%=ord.getUserID()%>, StLoc: <%=ord.getStartLocation()%>, EndLoc: <%=ord.getEndLocation()%>,paymentAmount:<%=ord.getPaymentAmount()%> },<%
                    }
                }
            %>
            ]
        });
    }
</script>