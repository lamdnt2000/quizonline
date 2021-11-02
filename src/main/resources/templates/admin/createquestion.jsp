<%-- 
    Document   : createcategory
    Created on : Jan 8, 2021, 10:32:34 AM
    Author     : sasuk
--%>

<%-- 
    Document   : category
    Created on : Jan 7, 2021, 9:44:53 PM
    Author     : sasuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - Create Question</title>
        <link href="${applicationScope.PATHADMIN}/css/styles.css" rel="stylesheet" />
        <script src="${applicationScope.PATHADMIN}/js/all.min.js" crossorigin="anonymous"></script>

    </head>
    <body>

        <jsp:include page="header.html"/>
        <div id="layoutSidenav">
            <jsp:include page="menu.jsp"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Question/Create Question</li>
                        </ol>

                        <div class="row">
                            <div class="col-xl-10">


                                <form action="CreateQuestionProcess" method="POST" id="questionForm" >
                                    <small class="text-dark">Question Title</small><br>
                                    <input id="txtQuestionTitle" type="text" name="txtQuestionTitle" placeholder="Question title" class="form-control form-control-sm" value="${param.txtQuestionTitle}">

                                    <small class="text-dark">Subject</small>
                                    <select class="custom-select" name="subjectId" id="subject">
                                    </select>


                                    <small class="text-dark">Answer Option</small><br>
                                    <c:forEach begin="1" end="4" var="count">
                                        <div class="row justify-content-center">
                                            <div class="col-md-1">
                                                <input type="radio" name="answer"  value="${count}" 
                                                       <c:if test="${count eq param.answer}">
                                                           checked="true"
                                                       </c:if>
                                                       />
                                            </div>
                                            <div class="col-md-9">
                                                <c:set var="name" value="txtOption${count}"/>
                                                <input  type="text" name="${name}" placeholder="Answer content ${count}" class="form-control form-control-sm" value="${param[name]}">
                                            </div>
                                        </div>
                                        <br>
                                    </c:forEach>
                                    <br>

                                    <small class="text-dark">Status</small><br>
                                    <input type="checkbox" name="status"  id="status" disabled="true" checked/>
                                    <label for="status">Active</label><br><br>

                                    <button id="submit" type="submit" class="btn btn-primary">Create Question</button>

                                </form>
                                <br>
           
                            </div>

                        </div>

                    </div>
                </main>
                <jsp:include page="footer.jsp"/>
            </div>
        </div>
        <script src="${applicationScope.PATHADMIN}/js/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>

        <script src="${applicationScope.PATHADMIN}/js/scripts.js"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery-1.12.0.min.js" crossorigin="anonymous"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery.validate.min.js"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/additional-methods.min.js"></script>
        <script type="text/javascript">
            $.ajax({
                type: "GET",
                url: "${applicationScope.PATHAPI}/ShowAllSubject",
                async: true,
                success: function (data) {

                    data.forEach(function (item) {
                        var subjectId = item.id;

                        var checked = (subjectId === '${param.subjectId}') ? "selected" : "";
                        $('#subject').append('<option value="' + subjectId + '"' + checked + '>' + subjectId + '</option>');
                    });

                }
            });


        </script>
        <script type="text/javascript">
            $().ready(function () {
                $('#questionForm').validate({
                    rules: {
                        txtQuestionTitle: {
                            required: true,
                            rangelength: [5, 255]
                        },
                        subjectId: {
                            required: true,

                        },
                        answer: {
                            required: true,
                            digits: true

                        },
                        txtOption1: {
                            required: true,
                            rangelength: [1, 255]
                        },
                        txtOption2: {
                            required: true,
                            rangelength: [1, 255]
                        },
                        txtOption3: {
                            required: true,
                            rangelength: [1, 255]
                        },
                        txtOption4: {
                            required: true,
                            rangelength: [1, 255]
                        }
                        
                    },
                    submitHandler: function (form) {
                            $.ajax({
                                type: form.method,
                                url: form.action,
                                data: $(form).serialize(),
                                success: function (data) {

                                    alert(data.message);
                                    window.location.reload();
                                }
                            });
                        }
                });

            }
            );
        </script>
    </body>
</html>
