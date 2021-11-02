<%-- 
    Document   : menu
    Created on : Nov 28, 2020, 11:52:39 PM
    Author     : sasuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div id="layoutSidenav_nav">
    
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading"><font color="red">Welcome,  ${sessionScope.RESULTLOGIN.name}</font> </div>

                <div class="sb-sidenav-menu-heading">Manage Exam</div>
                <a class="nav-link" href="CreateSubject">
                    <div class="sb-nav-link-icon"></div>
                    Create Subject
                </a>
                 <a class="nav-link" href="CreateExam">
                    <div class="sb-nav-link-icon"></div>
                    Create Exam
                </a>

                <div class="sb-sidenav-menu-heading">Manage Quiz</div>
                <a class="nav-link" href="CreateQuestion">
                    <div class="sb-nav-link-icon"></div>
                    Create Question
                </a>
                <a class="nav-link" href="ShowQuestion">
                    <div class="sb-nav-link-icon"></div>
                    Question
                </a>
                <div class="sb-sidenav-menu-heading">Setting</div>
                <a class="nav-link" href="LogoutProcess">
                    <div class="sb-nav-link-icon"></div>
                    Log out
                </a>
            </div>
        </div>
        
    </nav>
</div>