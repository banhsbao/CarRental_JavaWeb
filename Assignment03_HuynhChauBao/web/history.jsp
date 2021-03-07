<%-- 
    Document   : history
    Created on : Mar 7, 2021, 1:50:21 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>History Page</title>
</head>
<body>
    <!-- Welcome zone -->
    <c:set var="USER" value="${sessionScope.USER_ADMIN}"></c:set>   
    <c:set var="FAIL" value="${requestScope.ACTIVE_FAIL}"></c:set>
    <c:if test="${not empty USER}">
        <br>
        <div style="float:right;margin-right: 170px; font-family: sans-serif">
            <h5 style="color: coral">
                <small style="color: green">Xin chào</small>
                <br>
                <form action="logout">
                    ${USER.name}  &nbsp;<input type="submit" value="Logout" style="font-family:inherit "/> 
                </form>
            </h5>  
        </div>
    </c:if>
    <div>        
        <h1 style="color: green; text-align: center; font-family: sans-serif">CAR RENTAL</h1>
        <hr  width="80%" align="center"/>
    </div>
    <h2 style="color: green; margin-left: 10px;font-family: sans-serif">History Car Rental <a href="search" style="font-family: sans-serif;font-size: 30px; color: tomato">Back to Rental Page</a></h2> 
    <div style="font-family: sans-serif">
        <h3>Guest's name: <a style="color: green">${USER.name}</a>
        </h3>
        <h3>Guest's email: <a style="color: green">${USER.email}</a>
    </div>
    <h4>Search by</h4>
    <div style="margin-left: 50px">
        <form action="searchHistoryControl" method="POST">
            <input type="hidden" name="txtEmail" value="${USER.email}" />
            <c:if test="${sessionScope.CHOICE_SEARCH  == 1}">
                <input type="radio" id="name" name="HistorySearch" value="name" checked="check"> 
            </c:if>
            <c:if test="${sessionScope.CHOICE_SEARCH != 1}">
                <input type="radio" id="name" name="HistorySearch" value="name" checked="check" > 
            </c:if>
            <a>Car's Name  </a><input type="text" name="txtNameCar" value="${param.txtNameCar}" />
            <a style="color: red">${requestScope.NAME_HISTORY}</a>
            <br>
            <c:if test="${sessionScope.CHOICE_SEARCH != 2}">
                <input type="radio" id="date" name="HistorySearch" value="date">
            </c:if>
            <c:if test="${sessionScope.CHOICE_SEARCH == 2}">
                <input type="radio" id="date" name="HistorySearch" value="date" checked="check">
            </c:if>
            <a>Order Date    </a><input type="date" name="orderDate" value="${param.orderDate}" >
            <input type="submit" value="Search" />
            <h3 style="color: red">${requestScope.SEARCH_DATE}</h3>

        </form>
    </div>
    <c:set var="history" value="${sessionScope.HISTORY}"></c:set>
    <c:if test="${empty history}">
        <h2 style="color: tomato; text-align: center">No Records!</h2>
    </c:if>
    <c:if test="${not empty history}">
        <div style="text-align: center">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID Order</th>
                        <th>Create Date</th>
                        <th>Discount ID</th>
                        <th>Name of Car</th>
                        <th>Car image</th>
                        <th>Quantity</th>
                        <th>Price of each Car</th>
                        <th>Total Price</th>
                        <th>Payment</th>
                        <th>Rental Date</th>
                        <th>Return Date</th>
                        <th>Status of Rental</th>
                        <th>Cancel Renting</th>
                        <th>Your Rating Score</th>
                        <th>Rating</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="historyDetail" items="${history}">
                        <tr>
                            <td>${historyDetail.idOrder}</td>
                            <td>${historyDetail.createDate}</td>
                            <td>${historyDetail.discountId}</td>
                            <td>${historyDetail.carName}</td>
                            <th><img src="${historyDetail.imageCar}" width="50" height="36" alt="66ba167f2aa84361b6b4150784a7a697"/></th>
                            <td>${historyDetail.quantity}</td>
                            <td>${historyDetail.price} $</td>
                            <td>${historyDetail.totalPrice} $</td>
                            <td>${historyDetail.discountPrice} $</td>
                            <td>${historyDetail.rentalDate}</td>
                            <td>${historyDetail.returnDate}</td>
                            <td>
                                <c:if test="${historyDetail.status == true}">
                                    <h4>In Renting</h4>
                                </c:if>
                                <c:if test="${historyDetail.status == false}">
                                    <h4>Out of Rent</h4>
                                </c:if>
                            </td>
                            <td>
                                <form action="deleteHistory" method="POST">
                                    <input type="hidden" name="idCar" value="${historyDetail.idCar}" />
                                    <input type="hidden" name="txtAmount" value="${historyDetail.quantity}" />
                                    <input type="hidden" name="txtIdOrder" value="${historyDetail.idOrder}" />
                                    <c:if test="${sessionScope.DELETE_FAIL eq historyDetail.idOrder}">
                                        <small style="color: red">The car is being rented</small >
                                        <input type="submit" value="Out Of Date" disabled="disabled" />
                                    </c:if>
                                    <c:if test="${sessionScope.DELETE_FAIL != historyDetail.idOrder}">
                                        <c:if test="${historyDetail.status == false}">
                                            <input type="submit" value="Canceled" disabled="disabled" />
                                        </c:if>
                                        <c:if test="${historyDetail.status == true}">
                                            <input type="submit" value="Cancel" />
                                        </c:if>
                                    </c:if>  
                                </form>
                            </td>
                            <td>
                                <c:if test="${historyDetail.rating != 0}">
                                    ${historyDetail.rating}/10
                                </c:if>
                                <c:if test="${historyDetail.rating == 0}">
                                    Not rating Yet!
                                </c:if>
                            </td>
                            <td>
                                <form action="ratingCar" method="POST">
                                    <c:if test="${requestScope.RATING_FAIL == historyDetail.idOrder}">
                                        <a style="color: green; text-transform: uppercase">Please experience our service and rate it later!</a>
                                    </c:if>
                                    <c:if test="${requestScope.RATING_FAIL != historyDetail.idOrder}">
                                        <c:if test="${historyDetail.rating != 0}">
                                            <h4 style="color: peru;font-family: sans-serif;text-transform: uppercase">Rated</h4>
                                        </c:if>
                                        <c:if test="${historyDetail.rating == 0}">
                                            <input type="radio" id="1" name="${historyDetail.idOrder}" value="1">
                                            <label for="1">1</label>
                                            <input type="radio" id="2" name="${historyDetail.idOrder}" value="2">
                                            <label for="2">2</label>
                                            <input type="radio" id="3" name="${historyDetail.idOrder}" value="3">
                                            <label for="3">3</label>
                                            <input type="radio" id="4" name="${historyDetail.idOrder}" value="4">
                                            <label for="4">4</label>
                                            <input type="radio" id="5" name="${historyDetail.idOrder}" value="5">
                                            <label for="5">5</label>
                                            <input type="radio" id="6" name="${historyDetail.idOrder}" value="6">
                                            <label for="6">6</label>
                                            <input type="radio" id="7" name="${historyDetail.idOrder}" value="7">
                                            <label for="7">7</label>
                                            <input type="radio" id="8" name="${historyDetail.idOrder}" value="8">
                                            <label for="8">8</label>
                                            <input type="radio" id="9" name="${historyDetail.idOrder}" value="9">
                                            <label for="9">9</label>
                                            <input type="radio" id="10" name="${historyDetail.idOrder}" value="10" checked="check">
                                            <label for="10">10</label>
                                            &nbsp
                                        </c:if>
                                        <c:if test="${historyDetail.rating == 0}">
                                            <input type="submit" value="Rating" name="rate" />
                                        </c:if>
                                    </c:if>
                                    <input type="hidden" name="txtAmount" value="${historyDetail.quantity}" />
                                    <input type="hidden" name="txtIdOrder" value="${historyDetail.idOrder}" />
                                    <input type="hidden" name="txtIdCar" value="${historyDetail.idCar}" />
                                    <input type="hidden" name="txtEmail" value="${USER.email}" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>  
    </c:if>
</body>
</html>
