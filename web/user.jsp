<%@ page import="ge.taxistgela.bean.User" %>
<%@ page import="ge.taxistgela.model.OrderManager" %>
<%@ page import="ge.taxistgela.bean.Order" %>
<%@ page import="java.util.ArrayList" %>
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
            <button class="fa fa-check-circle button small <% if(user.getIsVerifiedEmail()){out.println("disalbed");}%> verified">
                Verified
            </button>
        </div>
        <div class="6u 12u$(xsmall)">
            <input type="text" disabled value="<%=user.getPhoneNumber()%>">
        </div>
        <div class="6u$ 12u$(xsmall)">
            <button class="fa fa-check-circle button small <% if(user.getIsVerifiedEmail()){out.println("disalbed");}%> special">
                Verify
            </button>
        </div>
        <div class="12u$">
            <button onclick="$('#cPass').toggleClass('hidden');" class="button special fa fa-key"> Change Password
            </button>
        </div>
        <div id="cPass" class="5u$ hidden">
            <form action="" type="post">
                <span> Current Password: </span>
                <input name="oldPassword" type="password" value="" required/>
                <span> New Password: </span>
                <input name="password" type="password" value="" required/>
                <span> Repeat Password: </span>
                <input type="password" value="" required/><br>
                <input type="submit" class="special button" value="Save"/>
            </form>
        </div>
        <div style="float:right" class="4u$ (xsmall)">
            <li style="float:left" class="<%if(user.getGoogleID()!=null){ %> disabled <%}%> icon fa-google"><span
                    class="label">Google+</span></li>
            <br><br>
            <a href="#" style="float:left" class="<%if(user.getFacebookID()!=null){ %> disabled <%}%> icon fa-facebook"><span
                    class="label">Facebook</span></a>
        </div>
        <div class="5u$ 12u$(small)">
            <a href="#" onclick="$('#cPref').toggleClass('hidden');" class="button special small fa fa-adjust"> Edit
                Your Preferences</a>
        </div>
        <div id="cPref" class="6u$ hidden">
            <form action="" type="post">
                <span> Minimum Driver Rating </span>
                <i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i
                    class="fa fa-star-o"></i><i class="fa fa-star-o"></i>
                <input type="checkbox" id="conditioning"
                       name="conditioning" <% if(user.getPreference().isConditioning()){%> checked <%}%>>
                <label for="conditioning"> Conditioning Required </label>
                </input><br>
                <input type="number" value="<%=user.getPreference().getCarYear()%>" id="carYear"
                       style="color:black;padding-left:5px" value="1990" step="1">
                <label for="carYear"> Minimum Car Year </label>
                <input type="number" id="timeLimit" value="<%=user.getPreference().getTimeLimit()%>"
                       style="color:black;padding-left:5px" value="10" step="1">
                <label for="timeLimit"> Maximum Time Limit (Minutes) </label>
                <input type="number" id="passengerCount" value="<%=user.getPreference().getPassengersCount()%>"
                       style="color:black;padding-left:5px" value="1" step="1">
                <label for="passengerCount"> Passenger Count </label><br>
                <input type="checkbox" id="wantsAlone" value="<%=user.getPreference().isWantsAlone()%>"
                       name="wantsAlone" checked>
                <label for="wantsAlone"> Want To Travel Alone </label>
                </input>
                <input type="submit" style="margin-top:5px" class="button special"><br>
            </form>
        </div>
        <div class="12u 1u$(small)" style="float:left">
            <a href="#" data-toggle="modal" data-target="#historyModal" class="button special small fa fa-bar-chart">
                View Order History</a>
        </div>
    </div>
</div>
<div id="historyModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h5 class="modal-title">History</h5>
            </div>
            <div class="modal-body">
                <% OrderManager man = (OrderManager) application.getAttribute(OrderManager.class.getName());
                    if (man != null) {
                        List<Order> orders = man.getOrderByUserID(user.getUserID());
                        for (Order ord : orders) {

                        }
                    }
                %>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>