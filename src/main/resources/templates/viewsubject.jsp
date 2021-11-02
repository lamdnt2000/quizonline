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

        <title>QuizOnline Homepage</title>

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
                        <div class="card-header" >Subject</div>


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
                    console.log(data);
                    data.forEach(function (item) {
                        var subjectId = item.id;
                        var subjectName = item.name;
                        $('#showSubject').append('<div class="card-body">\n\
                            <h5 class="card-title"><a href="ShowExam?subjectId='+subjectId+'">'+subjectId+'</a></h5>\n\
                                <p class="card-text">'+subjectName+'</p>\n\
                            <hr></div>');
                    });

                }
            });

        </script>


    </body>

</html>
