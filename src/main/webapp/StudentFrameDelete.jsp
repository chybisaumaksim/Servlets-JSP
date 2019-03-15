<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Удаление студента</title>
</head>
<body>
<form action="<c:url value="/main"/>" method="POST">
    <table cellpadding="1" cellspacing="1" border="1">
        <tr>
            <td> </td>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Дата рождения</td>
            <td>Год поступления</td>
            <td>Удалить</td>
            <td>Править</td>
        </tr>
        <c:forEach items="${student}" var="student">
            <tr>
                <td><input type="radio" name="id" value="${student.id}"></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.surname}"/></td>
                <td><c:out value="${student.birthDate}"/></td>
                <td><c:out value="${student.enterYear}"/></td>
                <td><input type="submit" value="Удалить" name="Delete"/></td>
                <td><input type="submit" value="Править" name="Update"/></td>
            </tr>
        </c:forEach>
    </table>
    <b><% if (request.getAttribute("message") != null) { %>
        <%=request.getAttribute("message")%>
        <% } %></b>

    <table>
        <tr>
            <td><input type="reset" value="Cancel" name="Cancel"/></td>
    </table>
    <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
</form>
</body>
</html>
