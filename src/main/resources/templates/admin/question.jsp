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
        <title>Dashboard - Question</title>
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
                            <li class="breadcrumb-item active">Question/ Show Question</li>
                        </ol>
                        <div id="message"></div>
                        <div class="row" >
                            <div class="col-md-12">
                                <form action="" >
                                    <div class="row">
                                        <div class="col-md-12">
                                            <input name="txtSearchTitle" class="form-control" placeholder="Search title" id="txtSearch" value="${param.txtSearchTitle}"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">

                                        <div class="col-md-3">
                                            <select class="custom-select" name="subjectId" id="subject">

                                            </select>
                                        </div>
                                        <div class="col-md-3">
                                            <select class="custom-select" name="status" id="status">
                                                <option value="1"
                                                        <c:if test="${param.status.equals('1')}">selected</c:if>
                                                            >Active</option>
                                                        <option value="0" 
                                                        <c:if test="${param.status.equals('0')}">selected</c:if>
                                                            >Disable</option>

                                                </select>                                       
                                            </div>
                                            <div class="col-md-3 justify-content-end">
                                                <button type="submit" class="btn btn-primary" onclick="this.form.action = 'ShowQuestion'">Search</button>
                                            </div>
                                            <div class="col-md-3">
                                                <button type="button" class="btn btn-danger" id="deleteBtn" data-toggle="modal" data-target="delete">Delete</button>
                                            </div>
                                            <br>
                                        </div>

                                        <div class="row"></br></div>
                                        <div class="row">

                                            <div class="table-responsive">


                                                <table id="mytable" class="table table-bordred table-striped">

                                                    <thead>

                                                    <th><input type="checkbox" id="checkall" /></th>
                                                    <th>Title</th>
                                                    <th>Subject</th>
                                                    <th>Correct Answer</th>
                                                    <th>Date Create</th>
                                                    <th>Action</th>
                                                    </thead>
                                                    <tbody id="mainBody">


                                                    </tbody>

                                                </table>



                                            </div>
                                        </div>
                                    </form> 

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
                            </div>

                        </div>

                    </main>

                <jsp:include page="footer.jsp"/>
            </div>

        </div>
        <script src="${applicationScope.PATHADMIN}/js/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="${applicationScope.PATHADMIN}/js/scripts.js"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery-1.12.0.min.js" crossorigin="anonymous"></script>
        <script src="${applicationScope.PATHRESOURCE}/js/jquery.twbsPagination.min.js" crossorigin="anonymous"></script>
        <Script>
                                                    $(document).ready(function () {
                                                        $("#mytable #checkall").click(function () {
                                                            if ($("#mytable #checkall").is(':checked')) {
                                                                $("#mytable input[type=checkbox]").each(function () {
                                                                    $(this).prop("checked", true);
                                                                });

                                                            } else {
                                                                $("#mytable input[type=checkbox]").each(function () {
                                                                    $(this).prop("checked", false);
                                                                });
                                                            }
                                                        });
                                                    });
        </script>

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

        </script>

        <script>
            var subjectIdParam = $('#subject').val();
            var statusParam = $('#status').val();
            var rowCount;
            var searchTitleParam = $('#txtSearch').val();
            var dataGet = {};
            dataGet['subjectId'] = subjectIdParam;
            dataGet['status'] = statusParam;
            dataGet['txtSearchTitle'] = searchTitleParam;
            $.ajax({
                type: "GET",
                url: "${applicationScope.PATHAPI}/GetDataForPaging",
                data: dataGet,
                async: false,
                success: function (data) {
                    rowCount = data.totalQuestion;
                }
            });
            if (rowCount > 0) {
                var startPage = $('#page').val();
                var rowPerPage = 10;
                var totalPage = Math.ceil(rowCount / rowPerPage);

                $('#pagination').twbsPagination({
                    startPage: parseInt(startPage),
                    totalPages: totalPage,
                    visiblePages: rowPerPage,

                    hideOnlyOnePage: true,
                    prev: '<span aria-hidden="true">&laquo;</span>',
                    next: '<span aria-hidden="true">&raquo;</span>',
                    onPageClick: function (event, page) {
                        $("tbody").children().remove(),
                                loadAjax(page),
                                $('#page').val(page)
                    }

                });
            }
            else{
                $("tbody").append("<p>No question found</p>");
            }
            function loadAjax(page) {
                var data = {};
                data['subjectId'] = subjectIdParam;
                data['status'] = statusParam;
                data['page'] = page;
                data['txtSearchTitle'] = searchTitleParam;
                $.ajax({
                    type: "GET",
                    url: "${applicationScope.PATHAPI}/ShowAllQuestion",
                    data: data,
                    async: true,
                    success: function (data) {
                        data.forEach(function (item) {
                            var questionId = item.id;
                            var questionTitle = item.questionTitle;
                            var subjectId = item.subjectId;
                            var correctAnswer = item.correctAnswer;
                            var dateCreate = item.dateCreate;
                            $('tbody').append('<tr>')
                                    .append('<td><input type="checkbox" class="checkthis" name="questionId" value="' + questionId + '" /></td>')
                                    .append('<td>' + questionTitle + '</td>')
                                    .append('<td>' + subjectId + '</td>')
                                    .append('<td>' + correctAnswer + '</td>')
                                    .append('<td>' + dateCreate + '</td>')
                                    .append('<td><a class="btn btn-primary" href="GetQuestionProcess?id=' + questionId + '">Update</a></td>')
                                    .append('</tr>');
                        });

                    }
                });
            }


            $('#deleteBtn').click(function () {
                var questionId = [];
                $("#mytable td input:checked").each(function ()
                {
                    questionId.push($(this).val());
                });

                $.ajax({
                    type: "POST",
                    url: "DeleteQuestionProcess",
                    cache: false,
                    data: {questionId: questionId,
                        subjectId: subjectIdParam},
                    success: function (data) {
                        alert(data.message);
                        window.location.reload();

                    }
                });
                setTimeout(location.reload(), 3000);
            });
        </script>
    </body>
</html>
