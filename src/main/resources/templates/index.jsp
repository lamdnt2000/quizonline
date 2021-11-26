<!DOCTYPE html>
<html xmlns:c="http://java.sun.com/jstl/core">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>QuizOnline Homepage</title>
</head>
<body>
<h3>Subject</h3>
<ol type="1">
    <c:forEach var="subject" items="${subjects}">
        <li>
                ${subject.subjectName}
        </li>
    </c:forEach>
</ol>
</body>
</html>