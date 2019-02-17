<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
    <title>Students</title>
</head>
<body>
<B> Все студенты: </B>
<table cellpadding="1" cellspacing="1" border="1">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>SurName</td>
        <td>BirthDate</td>
        <td>EnterYear</td>
    </tr>

    <c:forEach items="${students}" var="student">
    <tr>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>${student.surname}</td>
        <td>${student.birthDate}</td>
        <td>${student.enterYear}</td>
    </tr>
    </c:forEach>
</table>
    <a href="${pageContext.request.contextPath}/index.jsp">На главную</a>
</body>
</html>
