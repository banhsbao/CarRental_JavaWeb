<%-- 
    Document   : active
    Created on : Feb 24, 2021, 10:43:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Active Page</title>
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
        <div>        
            <h1 style="color: green; text-align: center; font-family: sans-serif">CAR RENTAL</h1>
            <hr  width="80%" align="center"/>
        </div>
        <div style="margin-left: 30px; font-family: sans-serif">
            <h1 style="color: gray; text-transform: uppercase; font-size: 60px">Xin Chao</h1>
            <label>User's Name: </label>
            <h3 style="color: green">${USER.name}</h3>
            <label>User's Email:</label>
            <h5> ${USER.email}</h5>
        </div>
        <div style="margin-left: 500px;font-family: sans-serif">
            <h1 style="color: red">Your account has not been verified yet
            </h1>
            <form action="active" method="POST">
                Enter your verify code: <input type="password" name="txtVerifyCode" value="" />
                <input type="submit" value="Verify Now" /><br>
                <small style="font-family: sans-serif; color: green">Please check your mail to get verify code!</small>
            </form>
            <c:if test="${not empty FAIL}">
                <small style="color: red">${FAIL}</small> 
            </c:if>
        </div>
    </body>
</html>