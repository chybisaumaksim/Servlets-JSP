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

    <c:forEach items="${students}" var="students">
    <tr>
        <td>${students.id}</td>
        <td>${students.name}</td>
        <td>${students.surname}</td>
        <td>${students.birthDate}</td>
        <td>${students.enterYear}</td>
    </tr>
    </c:forEach>
</table>
    <a href="${pageContext.request.contextPath}/index.jsp">На главную</a>
</body>
</html>
