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
        <title>Dashboard - Create Exam</title>
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
                            <li class="breadcrumb-item active">Question/Create Exam</li>
                        </ol>

                        <div class="row">
                            <div class="col-xl-10">


                                <form action="CreateExamProcess" method="POST" id="examForm" >
                                    <small class="text-dark">Exam Name</small><br>
                                    <input id="txtExamName" type="text" name="txtExamName" placeholder="Exam Name" class="form-control form-control-sm" value="${param.txtExamName}">

                                    <small class="text-dark">Subject</small>
                                    <select class="custom-select" name="subjectId" id="subject">
                                    </select>
                                    <small class="text-danger">Total Question: </small>
                                    <small class="text-danger" id="totalQuestion"></small>
                                    <br>
                                    <small class="text-dark">Time do Exam</small>
                                    <input id="timeDoExam" type="number" name="timeDoExam" placeholder="Time do Exam" class="form-control form-control-sm" value="${resultParams.productAmount}" step="1">

                                    <small class="text-dark">Question per Exam</small>
                                    <input id="numQuest" type="number" name="numQuest" placeholder="Question per Exam" class="form-control form-control-sm" value="${resultParams.productAmount}" step="1">

                                    <br>

                                    <button id="submit" type="submit" class="btn btn-primary" >Create Exam</button>

                                </form>
                                <br>
                                <div id="message"></div>
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
                async: false,
                success: function (data) {

                    data.forEach(function (item) {
                        var subjectId = item.id;

                        var checked = (subjectId === '${param.subjectId}') ? "selected" : "";
                        $('#subject').append('<option value="' + subjectId + '"' + checked + '>' + subjectId + '</option>');
                    });
                    getTotalQuestion();
                }
            });
            $('#subject').change(function () {
                getTotalQuestion();
            });
            function getTotalQuestion() {
                var dataGet = {};
                dataGet['subjectId'] = $('#subject').val();
                dataGet['status'] = 1;
                dataGet['txtSearchTitle'] = "";
                $.ajax({
                    type: "GET",
                    url: "${applicationScope.PATHAPI}/GetDataForPaging",
                    data: dataGet,
                    async: true,
                    success: function (data) {
                        $('#totalQuestion').html(data.totalQuestion);
                    }
                });
            }

        </script>
        <script type="text/javascript">
            $().ready(function () {
                $('#examForm').validate({
                    rules: {
                        txtExamName: {
                            required: true,
                            rangelength: [5, 50]
                        },
                        subjectId: {
                            required: true,

                        },
                        timeDoExam: {
                            required: true,
                            range: [1, 90]

                        },
                        numQuest: {
                            required: true,
                            min: 1,
                            max: function () {
                                return parseInt($('#totalQuestion').html());
                            }
                        },
                    },
                    submitHandler: function (form) {

                        $.ajax({
                            type: form.method,
                            url: form.action,
                            data: $(form).serialize(),
                            success: function (data) {

                               alert(data.message);
                            }
                        });
                    }
                });

            }
            );

        </script>
    </body>
</html>
