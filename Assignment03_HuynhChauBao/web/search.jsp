<%-- 
    Document   : search
    Created on : Feb 26, 2021, 12:42:35 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <style>
        .searchPanel{
            float: left;
            width: 31%;
            padding: 15px;
        }
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
        .item{
            display: grid;
            grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
        }
        .itemDetail{
            content: "";
            display: table;
            clear: both;
        }
        .itemContent{
            float: left;
            width: 35%;
            padding: 20px;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
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
                    <c:if test="${USER.status != 'Active'}">
                        <a style="color: red">Account not active yet!</a>
                    </c:if>
                </h5>
            </div>
        </c:if>
        <c:if test="${empty USER}">
            <br>
            <div style="float:right;margin-right: 170px; font-family: sans-serif">
                <h5 style="color: coral">
                    <small style="color: green">Xin chào</small>
                    <a style="color: tomato">Quý Khách!</a>
                    <br>
                    <form action="indexPage">
                        <a style="color:green">Login To Rent Car</a>&nbsp;&nbsp;
                        <input type="submit" value="Login" style="font-family:inherit " name="btnAction"/>
                        <br>
                        Don't have account. <input type="submit" value="Register" name="btnAction" />
                    </form>
                </h5>  
            </div>
        </c:if>
        <div>        
            <h1 style="color: green; text-align: center; font-family: sans-serif">CAR RENTAL</h1>
            <hr  width="80%" align="center"/>
        </div>
        <div class="row">
            <c:set var="error" value="${requestScope.ERROR}"></c:set>
                <div class="searchPanel" style="text-align: center">
                    <h1 style="color: white; font-size: 30px;background-color: coral; font-family: sans-serif">Search Panel</h1>
                    <form action="searchControl" method="POST">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>
                                        <label style="font-size: 20px">Car name</label>
                                    </th>
                                    <th>
                                        <label style="font-size: 20px">Rental date</label> 
                                    </th>
                                    <th>
                                        <label style="font-size: 20px">Return date</label>
                                    </th>
                                    <th>
                                        <label style="font-size: 20px">Search</label>
                                    </th>

                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="text" name="txtCarName" value="${sessionScope.CARNAME}"/>
                                    <small style="color: red">${error.searchName}</small>
                                </td>
                                <td><input id="RentalDate" type="date" name="txtRentalDate" required="true" value="${sessionScope.RENTALDATE}">
                                    <small style="color: red">${error.rentalDateSmallerthanCurrentDate}</small>
                                    <small style="color: red">${error.rentalDateIsEmpty}</small>
                                    <small style="color: red">${error.rentalDateLargethanReturnDate}</small>
                                </td>
                                <td><input id="ReturnDate" type="date" name="txtReturnDate" required="true" value="${sessionScope.RETURNDATE}">
                                    <small style="color: red">${error.returnDateSmallerthanCurrentDate}</small>
                                    <small style="color: red">${error.returnDateISEmpty}</small>
                                    <small style="color: red">${error.returnDateSmallerthanRentalDate}</small>
                                </td>
                                <td>
                                    <input type="submit" value="Search Car Now" name="btnSearch" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br>
                    <c:set var="SearchBy" value="${sessionScope.SEARCH_BY}"></c:set>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>
                                        <label style="font-size: 20px">Amount</label>
                                    </th>
                                    <th>
                                        <label style="font-size: 20px">Category</label>
                                    </th>
                                    <th>
                                        <label style="font-size: 20px">Search by Name and Amount</label>
                                    </th>
                                    <th>
                                        <label style="font-size: 20px">Search by Category and Amount</label>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="number" name="txtAmount"  min="1" value="${sessionScope.AMOUNT}"/>
                                    <small style="color: red">${error.searchAmount}</small>
                                    <small style="color: red">${error.amountNumberType}</small>
                                    <small style="color: red">${error.amountNumberLargerThanZero}</small>
                                </td>
                                <td><select name="txtCategory">
                                        <c:set var="Category" value="${sessionScope.LIST_CATEGORY}"></c:set>
                                        <c:forEach var="Cat" items="${Category}">
                                            <option value="${Cat.id}">${Cat.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <c:if test="${SearchBy == 1}">
                                        <input type="radio" name="radioSelec" value="nameAmount" checked="checked">
                                    </c:if>
                                    <c:if test="${SearchBy == 0}">
                                        <input type="radio" name="radioSelec" value="nameAmount" >
                                    </c:if>
                                    <c:if test="${empty SearchBy}">
                                        <input type="radio" name="radioSelec" value="nameAmount" >
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${SearchBy ==1}">
                                        <input type="radio" name="radioSelec" value="categoryAmount">
                                    </c:if>
                                    <c:if test="${SearchBy ==0}">
                                        <input type="radio" name="radioSelec" value="categoryAmount" checked="checked">
                                    </c:if>
                                    <c:if test="${empty SearchBy}">
                                        <input type="radio" name="radioSelec" value="categoryAmount" checked="checked">
                                    </c:if>

                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <c:if test="${not empty SearchBy}">
                    <c:if test="${SearchBy ==1 }">
                        <h4>Status: <a style="color: green">Search by Name</a></h4>
                    </c:if>
                    <c:if test="${SearchBy ==0 }">
                        <h4>Status: <a style="color: green">Search by Category</a></h4>
                    </c:if>

                </c:if>
            </div>
            <div class="searchPanel">
                <div style="font-family: sans-serif">
                    <h1 style="text-align: center;color: white; font-size: 30px;background-color: coral; font-family: sans-serif">Guest's Infor</h1>
                    <h3>Guest's name: <a style="color: green">${USER.name}</a>
                    </h3>
                    <h3>Guest's email: <a style="color: green">${USER.email}</a>
                </div>
                <div>
                    <h3 style="color: red">${requestScope.HISTORY_NULL}</h3>
                </div>
                <div>
                    <form action="history" method="POST">
                        <input type="hidden" name="txtUser" value="${USER.email}" />
                        <input type="submit" value="Show History Rental" />
                    </form>
                </div>
                <div>
                    <c:if test="${not empty sessionScope.RENTALDATE}">
                        <a style="color: tomato">The Date you want to rental car:</a>
                        <h2>${sessionScope.RENTALDATE}</h2>
                    </c:if>
                    <c:if test="${not empty sessionScope.RETURNDATE}">
                        <a style="color: tomato">The Date you want to return car: </a>
                        <h2>${sessionScope.RETURNDATE}</h2>
                    </c:if>
                </div>
            </div>
            <div class="searchPanel">
                <div style="font-family: sans-serif">
                    <h1 style="text-align: center;color: white; font-size: 30px;background-color: coral; font-family: sans-serif">Guest's Cart</h1>
                </div>
                <c:set var="cart" value="${sessionScope.CART_INFOR}"></c:set>
                <c:if test="${empty cart}">
                    <h2 style="green">Buy Somethings!</h2>
                </c:if>
                <c:if test="${not empty CART_INFOR}">

                    <c:set var="total" value="${0}"></c:set>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Rental Date</th>
                                    <th>Return Date</th>
                                    <th>Category</th>
                                    <th>Amount</th>
                                    <th>Price</th>
                                    <th>Total Price</th>
                                </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="cartInfor" items="${sessionScope.CART_INFOR}">
                                <tr>
                                    <td><img src="${cartInfor.image}" width="50" height="36" alt="66ba167f2aa84361b6b4150784a7a697"/></td>
                                    <td>${cartInfor.name}</td>
                                    <td>${cartInfor.rentalDate}</td>
                                    <td>${cartInfor.returnDate}</td>
                                    <td>${cartInfor.category}</td>
                                    <td>
                                        <form action="updateCart" method="POST">
                                            <c:set var="error" value="${requestScope.CHECKOUT_FAIL}"></c:set>
                                            <h3 style="color:  red">${error.msg}</h3> 
                                            <input type="number" name="txtNumber" min="0" max="${cartInfor.maxCar}" value="${cartInfor.amount}" />
                                            <h3 style="color: red">${requestScope.UPDATE_FAIL}</h3>
                                            <input type="hidden" name="txtIdCart" value="${cartInfor.id}" />
                                            <input type="submit" value="Update" name="btnAction"/>
                                            <input type="submit" value="Delete" name="btnAction"/>
                                        </form>
                                    </td>
                                    <td>${cartInfor.price}</td>
                                    <td>${cartInfor.amount * cartInfor.price}</td>
                                    <c:set var="total" value="${total + cartInfor.amount* cartInfor.price}"></c:set>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <br>
                    <form action="checkOutControl" method="POST">
                        <a>Discount code: </a><input type="text" name="txtDiscount" value="${param.txtDiscount}" /> <input type="submit" value="GetDiscount" name="btnAction" />
                        <c:set var="discount" value="${sessionScope.DISCOUNT}"></c:set>
                        <c:if test="${not empty sessionScope.DISCOUNT}">
                            <a style="color: green">${sessionScope.DISCOUNT.name}</a>
                        </c:if>
                        <c:if test="${not empty sessionScope.DISCOUNT_FAIL}">
                            <h3 style="color: red">${sessionScope.DISCOUNT_FAIL}</h3>
                        </c:if>    
                        <h3 style="color: brown">Total Price: ${total} $</h3>
                        <c:set var="discountValue" value="${discount.value}"></c:set>
                        <h3 style="color: orangered">Total Price OFF: ${total*discountValue/100} $</h3>
                        <h3 style="color: green">Remain Price: ${total - total*discountValue/100} $</h3>
                        <h3 style="color: tomato">${requestScope.UPDATED}</h3>
                        <input type="submit" value="Checkout" name="btnAction" onclick="return confirm('Confirm to Check Out!')" />
                    </form>
                </c:if>
                <h2 style="color: green">${requestScope.CHECKOUT_SUCCESS}</h2>
            </div>
        </div>
        <div style="text-align: center; color: red">
            <h2>${sessionScope.RENTAL_FAIL}</h2>
        </div>
        <div>
            <h1 style="background-color: green; margin-left: 10px">Search Result</h1>
            <c:set var="totalPage" value="${sessionScope.TOTAL_PAGE}"></c:set>
            <c:if test="${totalPage > 1}">
                <a>Page Number: </a>
                <c:if test="${SearchBy == 1}">
                    <c:forEach var="count" begin="1" end="${totalPage}">
                        <c:url var="page" value="searchName">
                            <c:param name="PageNumber" value="${count}"></c:param>
                            <c:param name="txtCarName" value="${param.txtCarName}"></c:param>
                            <c:param name="txtRentalDate" value="${param.txtRentalDate}"></c:param>
                            <c:param name="txtReturnDate" value="${param.txtReturnDate}"></c:param>
                            <c:param name="txtAmount" value="${param.txtAmount}"></c:param>
                        </c:url>
                        <a href="${page}">${count}</a>
                    </c:forEach>
                </c:if>
                <c:if test="${SearchBy == 0}">
                    <c:forEach var="count" begin="1" end="${totalPage}">
                        <c:url var="page" value="searchCategory">
                            <c:param name="PageNumber" value="${count}"></c:param>
                            <c:param name="txtCarName" value="${param.txtCarName}"></c:param>
                            <c:param name="txtRentalDate" value="${param.txtRentalDate}"></c:param>
                            <c:param name="txtReturnDate" value="${param.txtReturnDate}"></c:param>
                            <c:param name="txtAmount" value="${param.txtAmount}"></c:param>
                        </c:url>
                        <a href="${page}">${count}</a>
                    </c:forEach>
                </c:if>
                <c:if test="${empty SearchBy}">
                    <c:forEach var="count" begin="1" end="${totalPage}">
                        <c:url var="page" value="search">
                            <c:param name="PageNumber" value="${count}"></c:param>
                            <c:param name="txtCarName" value="${param.txtCarName}"></c:param>
                            <c:param name="txtRentalDate" value="${param.txtRentalDate}"></c:param>
                            <c:param name="txtReturnDate" value="${param.txtReturnDate}"></c:param>
                            <c:param name="txtAmount" value="${param.txtAmount}"></c:param>
                        </c:url>
                        <a href="${page}">${count}</a>
                    </c:forEach>
                </c:if>
            </c:if>
            <div class="item">
                <c:set var="car" value="${sessionScope.LIST_CAR}"></c:set>
                <c:if test="${empty car}">
                    <h1>No Recording</h1>
                </c:if>
                <c:forEach var="carDetail" items="${car}">
                    <div style="  background-color: lightgrey;
                         width: 270px;
                         border: 5px solid green;
                         padding: 30px;
                         margin: 20px;"
                         class="itemDetail">
                        <img src="${carDetail.image}" width="257" height="162" alt="66ba167f2aa84361b6b4150784a7a697"/>
                        <div class="itemContent">
                            <h2 style="color: green">${carDetail.name}</h2>
                            <h4>Category: ${carDetail.category}</h4>
                            <h5>Color: ${carDetail.color}</h5>
                            <h5>Year: ${carDetail.year}</h5>
                        </div>
                        <div class="itemContent" style="font-family: sans-serif">
                            <h5>Price: ${carDetail.price}</h5>
                            <h5>Created Date: ${carDetail.createdDate} </h5>
                            <h5>Quantity: ${carDetail.quantity}</h5>
                            <c:if test="${carDetail.rating == 0}">
                                <h5 style="color: coral">Not Voting yet!</h5>
                            </c:if>
                            <c:if test="${carDetail.rating != 0}">
                                <h5 style="color: green">Rate: ${carDetail.rating}/10</h5>
                            </c:if>
                            <form action="addItem" method="POST">
                                <input type="hidden" name="txtRentalDate" value="${sessionScope.RENTALDATE}" />
                                <input type="hidden" name="txtReturnDate" value="${sessionScope.RETURNDATE}" />
                                <input type="hidden" name="txtAmount" value="${sessionScope.AMOUNT}" />
                                <input type="hidden" name="txtCarName" value="${sessionScope.CARNAME}" />
                                <input type="hidden" name="CarId" value="${carDetail.id}" />
                                <c:if test="${USER.role != 'admin'}">
                                    <input type="submit" value="Add To Card" name="btnAdd" />
                                </c:if>
                                <c:if test="${USER.role eq 'admin'}">
                                    <h3 style="color: red">Admin</h3>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
