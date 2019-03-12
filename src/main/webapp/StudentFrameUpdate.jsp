<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Обновление студента</title>
</head>

<body>
<form action="<c:url value="main"/>" method="POST">
    <b><% if (request.getAttribute("message") != null) { %>
        <c:forEach items="${message}" var="message">
            <br>${message}
        </c:forEach>
        <% } %></b>
    <table>
        <tr>
            <td>Id:</td>
            <td><input type="text" name="id" value="${student.id}" required pattern="^[0-9]+$"/></td>
        </tr>
        <tr>
            <td>Имя:</td>
            <td><input type="text" name="name" value="${student.name}" required pattern="^[a-zA-Zа-яёА-ЯЁ]+$"/></td>
        </tr>
        <tr>
            <td>Фамилия:</td>
            <td><input type="text" name="surname" value="${student.surname}" required pattern="^[a-zA-Zа-яёА-ЯЁ]+$"/>
            </td>
        </tr>
        <tr>
            <td>Дата рождения:</td>
            <td><input type="text" name="birthDate" value="${student.birthDate}" required
                       pattern="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$"/></td>
        </tr>
        <tr>
            <td>Год поступления:</td>
            <td><input type="text" name="enterYear" value="${student.enterYear}" required pattern="^[0-9]{4}"/></td>
        </tr>
    </table>
    <table>
        <tr>
            <td><input type="submit" value="Update" name="Update"/></td>
            <td><input type="submit" value="Cancel" name="Cancel"/></td>
        </tr>
    </table>
    <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
</form>
</body>
</html>