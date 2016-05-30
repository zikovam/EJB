<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>
    <table border="1">
        <c:forEach items="${requestScope.students}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
