<%-- 
    Document   : index
    Created on : Dec 1, 2020, 8:26:31 PM
    Author     : sasuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <div class="col-md-3">
                    <h2><span id="timer" class="badge badge-danger" data-seconds-left=""></span></h2>
                </div>
                <div class="col-md-3">
                    <span id="nameQuiz" class="font-weight-bold text-primary"></span>
                </div>
                <div class="col-md-3">
                    <span id="numQuest" class="font-weight-bold text-dark"></span>
                </div>
            </div>
            <form action="EndQuizProcess" method="post" id="endQuiz">
                <input type="hidden" name="id" value="${param.quizId}"/>
                <div class="row">
                    <div class="col-md-12">
  
                        <div class="card text-white bg-dark" id="showQuestion">

                        </div>

                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-md-4 align-content-lg-end">
                        <input type="submit" class="btn btn-primary"/>
                    </div>
                </div>
            </form>
            <!-- /.row -->
            <br>
            <div class="row">
                <div class="col-sm-12 col-md-7">
                    <input type="hidden" id="page" name="page" value="1"/>
                    <div id="paper">
                        <ul id="pagination" class="paginaion-sm">
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <!-- /.container -->

        <!-- Footer -->


        <!-- Bootstrap core JavaScript -->
        <script src="${applicationScope.PATHADMIN}/js/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="${applicationScope.PATHADMIN}/js/scripts.js"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery-1.12.0.min.js"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery.simple.timer.js"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery.twbsPagination.min.js" crossorigin="anonymous"></script>

        <script type="text/javascript">
            var examId;
            var totalRow;
            var timeCreated;
            var timeCountDown;
            var startPage = $('#page').val();
            var rowPerPage = 10;
            var count = 1;
            var timeTakeQuiz;
            $.ajax({
                type: "GET",
                url: "${applicationScope.PATHAPI}/GetDataForQuiz",
                data: {
                    "quizId": '${param.quizId}',
                    "status": 0
                },
                async: false,
                success: function (data) {
                    if (jQuery.isEmptyObject(data)) {
                        window.location.href = "ShowHistory";
                    } else {
                        examId = data.examId;
                        timeTakeQuiz = data.milisecon;
                    }
                }
            });

            $.ajax({
                type: "GET",
                url: "${applicationScope.PATHAPI}/GetDataForExam",
                data: {
                    "examId": examId
                },
                async: false,
                success: function (data) {
                    totalRow = data.numQuest;
                    timeCountDown = parseInt(data.timeDoExam);
                    var current = new Date();
                    var dateTakeQuiz = new Date(timeTakeQuiz);
                    dateTakeQuiz.setMinutes(dateTakeQuiz.getMinutes()+timeCountDown);
                    $('#timer').attr("data-seconds-left", (dateTakeQuiz.getTime()-current.getTime())/1000);
                    $('#nameQuiz').html(data.name);
                    $('#numQuest').html(totalRow);
                }
            });
            var totalPage = Math.ceil(totalRow / rowPerPage);
            $(function () {
                $('#timer').startTimer({
                    onComplete: function () {
                        $('#endQuiz').submit();
                    },
                    elementContainer: "span"
                });
            });
            $('#pagination').twbsPagination({
                startPage: parseInt(startPage),
                totalPages: totalPage,
                visiblePages: 5,
                hideOnlyOnePage: true,
                prev: '<span aria-hidden="true">&laquo;</span>',
                next: '<span aria-hidden="true">&raquo;</span>',
                onPageClick: function (event, page) {
                    window.scrollTo(0,0);
                    $('.group' + $('#page').val()).hide();
                    loadAjax(page);
                    $('#page').val(page);
                }

            });
            function loadAjax(page) {
                if ($('.group' + page).length === 0) {
                    var data = {};
                    data['quizId'] = '${param.quizId}';
                    data['page'] = page;
                    $.ajax({
                        type: "GET",
                        url: "${applicationScope.PATHAPI}/ShowQuestionByQuizId",
                        data: data,
                        async: false,
                        success: function (data) {

                            data.forEach(function (item) {

                                var title = item.questionTitle;
                                var id = item.id;
                                var listChoice = $.map(item.listChoice, function (value, index) {
                                    return [value];
                                });


                                $('#showQuestion').append('<div class="card-body group' + page + '" id="' + (count++) + '">\n\
                            <input type="hidden" name="quizId" value="' + id + '"/>\n\
                            <h5 class="card-title"><p class="font-weight-bold text-success">' + title + '</p></h5>\n\
                                <div class="row">\n\
                                        <div class="col-md-1">\n\
                                            <input name="' + id + '" class="form-control-sm" type="radio" value="1" size="1"/>\n\
                                        </div>\n\
                                        <div class="col-md-10">\n\
                                            <p class="font-italic text-light">' + listChoice[0].answer + '</p>\n\
                                        </div>\n\
                                </div>\n\
                                <div class="row">\n\
                                        <div class="col-md-1">\n\
                                            <input name="' + id + '" class="form-control-sm" type="radio" value="2" size="1"/>\n\
                                        </div>\n\
                                        <div class="col-md-10">\n\
                                            <p class="font-italic text-light">' + listChoice[1].answer + '</p>\n\
                                        </div>\n\
                                </div>\n\
                                <div class="row">\n\
                                        <div class="col-md-1">\n\
                                            <input name="' + id + '" class="form-control-sm" type="radio" value="3" size="1"/>\n\
                                        </div>\n\
                                        <div class="col-md-10">\n\
                                            <p class="font-italic text-light">' + listChoice[2].answer + '</p>\n\
                                        </div>\n\
                                </div>\n\
                                <div class="row">\n\
                                        <div class="col-md-1">\n\
                                            <input name="' + id + '" class="form-control-sm" type="radio" value="4" size="1"/>\n\
                                        </div>\n\
                                        <div class="col-md-10">\n\
                                            <p class="font-italic text-light">' + listChoice[3].answer + '</p>\n\
                                        </div>\n\
                                </div>\n\
                            <hr></div>\n\
                                ');

                            });

                        }
                    });
                } else {
                    $('.group' + page).show();
                }
            }
        </script>


    </body>

</html>
