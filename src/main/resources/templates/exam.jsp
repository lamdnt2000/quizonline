<%-- 
    Document   : index
    Created on : Dec 1, 2020, 8:26:31 PM
    Author     : sasuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>QuizOnline Exam</title>

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
                    <div class="card text-white bg-dark" id="showSubject">
                        <div class="card-header" >${param.subjectId}</div>
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
        <script src="${applicationScope.PATHRESOURCE}/js/jquery-1.12.0.min.js"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery.simple.timer.js"></script>


        <script type="text/javascript">
            $.ajax({
                type: "GET",
                url: "${applicationScope.PATHAPI}/ShowAllExam",
                data: {
                    subjectId: '${param.subjectId}'
                },
                async: false,
                success: function (data) {
                    if (data != null) {
                        data.forEach(function (item) {
                            var name = item.name;
                            var id = item.id;
                            var dateCreate = item.dateCreate;
                            var timeDoExam = item.timeDoExam;
                            var numQuest = item.numQuest;
                            $('#showSubject').append('<div class="card-body">\n\
                            <h5 class="card-title"><a href="CreateQuizProcess?examId=' + id + '&subjectId=' + '${param.subjectId}' + '">' + name + '</a></h5>\n\
                                <div class="row">\n\
                                        <div class="col-md-2">\n\
                                            <p class="font-weight-bold text-success">Date create:</p>\n\
                                        </div>\n\
                                         <div class="col-md-3">\n\
                                            <p class="font-italic text-light">' + dateCreate + '</p>\n\
                                        </div>\n\
                                        <div class="col-md-1">\n\
                                            <p class="font-weight-bold text-danger">Time:</p>\n\
                                        </div>\n\
                                         <div class="col-md-3">\n\
                                            <p class="font-italic text-light">' + timeDoExam + ' minutes</p>\n\
                                        </div>\n\
                                        <div class="col-md-2">\n\
                                            <p class="font-weight-bold text-danger">Questions:</p>\n\
                                        </div>\n\
                                         <div class="col-md-1">\n\
                                            <p class="font-italic text-light">' + numQuest + '</p>\n\
                                        </div>\n\
                                </div>\n\
                            </div>');
                        });

                    } else {
                        $('#showSubject').append('<p>No exam avalible yet</p>');
                    }
                }});


            $(function () {
                $('#timer').startTimer({
                    onComplete: function () {
                        console.log('Complete');
                        //call ajax
                    },
                    elementContainer: "span"
                });
            });


        </script>


    </body>

</html>
