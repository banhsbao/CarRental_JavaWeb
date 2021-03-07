<%-- 
    Document   : login
    Created on : Feb 24, 2021, 8:50:49 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>
    <body>
        <h1 style="color: coral; text-align: center; font-family: sans-serif">Welcome to Login Page</h1>
        <hr  width="80%" align="center"/>
        <h3 style="color: green; text-align: center; font-family: fantasy; text-transform: uppercase">Car Rental<br>
            <a style="color: salmon">Please login to rent Car!</a>
        </h3>
        <form id="my_captcha" action="login" method="POST" style="text-align: center">
            Email <input type="text" name="txtEmail" value="" />
            Password <input type="password" name="txtPassword" value="" />
            <div class="g-recaptcha "data-sitekey="6LeQJOAZAAAAACHIazdolgWHUAZmBSgcEOLQbA1N" style="margin-left: 810px"></div>
            <c:set var="BUG" value="${requestScope.LOGIN_FAIL}"></c:set>
                <input type="submit" value="Login" name="btnAction" />
                <input type="reset" value="Clear"/>
            <c:if test="${not empty BUG}">
                <h5 style="color: crimson; text-align: center">Login Fail: ${BUG}</h5>
            </c:if>
            <h3 style="color: slategrey; font-family: serif">Don't have account.  <a href="registerPage" style="color: lightsalmon; font-family: sans-serif; font-size: 75%;text-transform: ">Register</a> </h3>
        </form>
    </body> 
</html>
<script>
    document.getElementById("my_captcha").addEventListener("submit", function (evt)
    {
        var response = grecaptcha.getResponse();
        if (response.length == 0)
        {
            alert("Please verify not a robot!");
            evt.preventDefault();
            return false;
        }
    });
</script>