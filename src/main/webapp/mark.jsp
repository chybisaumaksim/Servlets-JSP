<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Marks</title>
</head>
<body>
<B> Все оценки: </B>
<table cellpadding="1" cellspacing="1" border="1">
    <tr>
        <td>Id</td>
        <td>StudentId</td>
        <td>LessonId</td>
        <td>Mark</td>
    </tr>

    <c:forEach items="${marks}" var="mark">
    <tr>
        <td>${mark.id}</td>
        <td>${mark.studentId}</td>
        <td>${mark.lessonId}</td>
        <td>${mark.mark}</td>
    </tr>
    </c:forEach>
</table>
    <a href="${pageContext.request.contextPath}/index.jsp">На главную</a>
</body>
</html>
