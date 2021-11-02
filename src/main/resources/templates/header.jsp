<%-- 
    Document   : header
    Created on : Dec 1, 2020, 8:30:21 PM
    Author     : sasuk
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" onclick='window.location.href="./";'>Quiz Online</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                
                <c:set var="resultLogin" value="${sessionScope.RESULTLOGIN}"/>
                <c:if test="${empty resultLogin}">
                    <li class="nav-item">
                        <a class="nav-link" href="login">Sign In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Signup">Sign Up</a>
                    </li>
                </c:if>
                <c:if test="${not empty resultLogin}">
                    <li class="nav-item">
                        <a class="nav-link text-danger">Welcome ${resultLogin.name}</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet">Log out</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ShowHistory">History</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>