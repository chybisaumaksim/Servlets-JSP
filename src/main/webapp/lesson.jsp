<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Lessons</title>
</head>
<body>
<B> Все предметы: </B>
<table cellpadding="1" cellspacing="1" border="1">
    <tr>
        <td>Id</td>
        <td>Lesson</td>
    </tr>

    <c:forEach items="${lessons}" var="lesson">
        <tr>
            <td>${lesson.id}</td>
            <td>${lesson.lesson}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/index.jsp">На главную</a>
</body>
</html>