<%-- 
    Document   : index
    Created on : Dec 1, 2020, 8:26:31 PM
    Author     : sasuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>QuizOnline History</title>

        <!-- Bootstrap core CSS -->
        <link href="${applicationScope.PATHHOME}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${applicationScope.PATHHOME}/css/shop-homepage.css" rel="stylesheet">
        <script src="${applicationScope.PATHADMIN}/js/all.min.js" crossorigin="anonymous"></script>

    </head>

    <body>

        <!-- Navigation -->

        <jsp:include page="header.jsp"/>


        <!-- Page Content -->
        <div class="container">
            <br>
            <div class="row">
                <div class="col-md-12">
                    <form action="" id="searchForm" >
                        <div class="input-group">
                            <div class="col-md-7">
                                <small class="text-muted">Name</small>
                                <input id="txtSearchName" name="txtSearchName" class="form-control" placeholder="Name of quiz" value="${param.txtSearchName}">
                                <br>
                            </div>
                            <div class="col-md-5 justify-content-end">
                                <small class="text-muted">Subject</small>
                                <select class="form-control" id="subject" name="subjectId">
                                    <option value="0">All</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="row justify-content-center ">
                            <div clas="col-md-4">
                                <input class="btn btn-primary" type="submit" value="Search History">

                            </div>
                        </div>
                    </form>
                    <br>

                    <div class="card text-white bg-dark" id="showHistory">
                        <div class="card-header" >History</div>


                    </div>
                </div>
            </div>
            <!-- /.row -->


        </div>
        <!-- /.container -->

        <!-- Footer -->


        <!-- Bootstrap core JavaScript -->
        <script src="${applicationScope.PATHADMIN}/js/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="${applicationScope.PATHADMIN}/js/scripts.js"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery-1.12.0.min.js" crossorigin="anonymous"></script>

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
                }
            });
            $.ajax({
                type: "GET",
                url: "${applicationScope.PATHAPI}/GetHistoryQuiz",
                data: {
                    "txtSearchName": $('#txtSearchName').val(),
                    "subjectId": $('#subject').val()
                },
                async: false,
                success: function (data) {
                    if (data != null) {
                        data.forEach(function (item) {
                            var id = item.id;
                            var total = item.total;
                            var timeDo = item.dateCreate;
                            var status = (total >= 4) ? '<p class="font-italic text-success">PASSED</p>' : '<p class="font-italic text-danger">NOT PASSED</p>';
                            $('#showHistory').append('<div class="card-body">\n\
                            <h5 class="card-title"><p class="font-weight-bold text-primary">' + item.subjectId + '-' + item.examName + '</p></h5>\n\
                                <div class="row">\n\
                                        <div class="col-md-2">\n\
                                            <p class="font-weight-bold text-success">Date Take:</p>\n\
                                        </div>\n\
                                         <div class="col-md-3">\n\
                                            <p class="font-italic text-light">' + timeDo + '</p>\n\
                                        </div>\n\
                                        <div class="col-md-1">\n\
                                            <p class="font-weight-bold text-danger">Score:</p>\n\
                                        </div>\n\
                                         <div class="col-md-1">\n\
                                            <p class="font-italic text-light">' + total + '/10</p>\n\
                                        </div>\n\
                                        <div class="col-md-2">\n\
                                            <p class="font-weight-bold text-primary">Status:</p>\n\
                                        </div>\n\
                                         <div class="col-md-3">' + status + '</div>\n\
                                </div>\n\
                            <hr></div>');
                        });
                    }
                    else{
                        $('#showHistory').append('<p>No quiz found</p>');
                    }
                }
            });
        </script>


    </body>

</html>
