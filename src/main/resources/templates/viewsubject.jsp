<!DOCTYPE html>
<html xmlns:c="http://java.sun.com/jstl/core">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Subject</title>
</head>
<body>
<div class="container text-center">
<div> <h1> Subject </h1> </div>
    <div class="container">
        <div class="row">

            <c:forEach items="${allCategory}" var="category">
                <div class="col-md-4">
                    <a href="${pageContext.request.contextPath}/category/${category.id}">${category.name }</a>
                </div>
            </c:forEach>

        </div>
    </div>


</div>
</body>
</html>