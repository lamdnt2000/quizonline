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
        <title>Dashboard - Category</title>
        <link href="${applicationScope.PATHADMIN}/css/styles.css" rel="stylesheet" />
        <link href="${applicationScope.PATHADMIN}/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="${applicationScope.PATHADMIN}/js/all.min.js" crossorigin="anonymous"></script>

    </head>
    <body>
        <div id="layoutSidenav">
            <jsp:include page="menu.jsp"/>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid">
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Subject / Create Subject </li>
                        </ol>

                        <div class="row">
                            <div class="col-xl-4">

                                <form action="CreateSubjectProcess" method="POST" id="formSubject">
                                    <small class="text-dark">Subject Id</small><br>
                                    <input type="text" name="txtSubjectId" placeholder="Subject Id" class="form-control form-control-sm" value="${param.txtSubjectId}">

                                    <small class="text-dark">Subject Name</small><br>
                                    <textarea class="form-control" name="txtSubjectName" placeholder="Description" rows="3" >${param.txtSubjectName}</textarea><br>
                                    <button type="submit" class="btn btn-primary">Create</button>

                                </form>
                                <br>
                                <div id="message"></div>
                            </div>
                            <div class="col-xl-8">
                                <table class="table" >
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Id</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Date Create</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
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
                    var count = 0;
                    data.forEach(function (item) {
                        count++;

                        var subjectId = item.id;
                        var subjectName = item.name;
                        var dateCreate = item.dateCreate;
                        $('tbody').append('<tr>')
                                .append('<th scope="row">' + count + '</th>')
                                .append('<td>' + subjectId + '</td>')
                                .append('<td>' + subjectName + '</td>')
                                .append('<td>' + dateCreate + '</td>')
                                .append('</tr>')
                                ;
                    });

                }
            })
            $().ready((function () {
                $('#formSubject').validate({
                    rules: {
                        txtSubjectId: {
                            required: true,
                            rangelength: [3, 10]
                        },
                        txtSubjectName: {
                            required: true,
                            rangelength: [5, 50]
                        },
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
                })
            })
                    )
        </script>
    </body>
</html>
