<%-- 
    Document   : index
    Created on : Mar 7, 2021, 5:52:22 PM
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
    <style>
        .container {
            position: relative;
        }

        .center {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            font-size: 18px;
        }

        img { 
            width: 100%;
            height: auto;
            opacity: 0.5;
        }
    </style>
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
        <h4>Hãy nhấn nút Rent Car để đặt xe!! Cảm ơn các bạn!</h4>
        <div class="container">
            <img style=" max-width: 100%;
                 height: auto;  margin-left: auto;
                 margin-right: auto;display: block;" src="images/background.jpg" alt="background"/>
            <div class="center" style="  width: 300px;
                 border: 15px solid white;
                 padding: 50px;
                 margin: 20px;">
                <a href="search" style="font-size: 100px; font-family: sans-serif; color: white">Rent Car Now</a>
            </div>
        </div>
    </body>
</html>
