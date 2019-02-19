<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список студентов</title>
</head>

<body>
<form action="<c:url value="/main?getAll"/>" method="GET">
    <B> Все студенты: </B>
    <table cellpadding="1" cellspacing="1" border="1">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>SurName</td>
            <td>BirthDate</td>
            <td>EnterYear</td>
        </tr>

        <c:forEach items="${student}" var="student">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.surname}</td>
                <td>${student.birthDate}</td>
                <td>${student.enterYear}</td>
            </tr>
        </c:forEach>
    </table>
        <table>
            <tr>
                <td><input type="submit" value="Create" name="Create"/></td>
                <td><input type="submit" value="Update" name="Update"/></td>
                <td><input type="submit" value="Delete" name="Delete"/></td>
            </tr>
        </table>
    <a href="${pageContext.request.contextPath}/index.jsp">Назад</a>
</form>
</body>
</html>