<%-- 
    Document   : register
    Created on : Feb 24, 2021, 12:45:03 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account Page</title>
    </head>
    <body>
        <h1 style="color: coral; text-align: center; font-family: sans-serif">Register Page</h1>
        <h5 style="padding-left: 10.8em;color: slategrey">Already have an Account..&ensp;<a href="loginPage" style="color: orangered; font-family: serif">Login Now!</a></h5>
        <hr  width="80%" align="center"/>
        <h3 style="color: green; text-align: center; font-family: fantasy; text-transform: uppercase">Create Account</h3>
        <c:set var="errors" value="${requestScope.REGISTER_FAIL}"></c:set>
            <form action="register" method="POST" style="text-align: center">
                <label style="margin-left: -50px;">Email*</label>
                <input style="padding: 2px 10px" type="text" name="txtEmail" value="${param.txtEmail}" />&nbsp;(4 - 60 characters) [Ex: xxxx@gmail.com.vn]
            <h6 style="color: red">${errors.emailLengthError}</h6>
            <h6 style="color: red">${errors.emailFormatError}</h6>
            <h6 style="color: red">${errors.emailAlreadyExist}</h6>
            <label style="margin-left: -75px;">FullName*</label>
            <input style="padding: 2px 10px" type="text" name="txtName" value="${param.txtName}" />&nbsp;(6 - 30characters)<br>
            <h6 style="color: red">${errors.nameLengthError}</h6>
            <label style="margin-left: -73px;">Password*</label>
            <input style="padding: 2px 10px" type="password" name="txtPassword" value="${param.txtPassword}" />&nbsp;(6 - 30characters)<br><br>
            <h6 style="color: red">${errors.passwordLengthError}</h6>
            <label style="margin-left: -65px;">Confirm*</label>
            <input style="padding: 2px 10px" type="password" name="txtConfirm" value="" />&nbsp;(6 - 30characters)<br>
            <h6 style="color: red">${errors.confirmNoMatchError}</h6>
            <label style="margin-left: -73px;">Phone*</label>
            <input style="padding: 2px 10px" type="text" name="txtPhone" value="${param.txtPhone}" />&nbsp;(9 characters)<br>
            <h6 style="color: red">${errors.phoneFormatError}</h6>
            <label style="margin-left: -60px;">Address*</label>
            <input style="padding: 2px 10px" type="text" name="txtAddress" value="${param.txtAddress}" />&nbsp;(1 - 80 characters)<br>
            <h6 style="color: red">${errors.addressLengthError}</h6>
            <input type="submit" value="Register" name="btnAction" />
        </form>
    </body>
</html>