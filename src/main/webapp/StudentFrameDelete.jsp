<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Удаление студента</title>
</head>
<body>
<form action="<c:url value="/main"/>" method="POST">
    <table>
        <tr>
            <td>Введите Id:</td>
            <td><input type="text" name="id" value="${student.id}" required pattern="^[0-9]+$"/></td>
            <td><% if (request.getAttribute("message") != null) { %>
                <%=request.getAttribute("message")%>
                <% } %></td>
        </tr>
        <tr>
            <td><input type="submit" value="Delete" name="Delete"/></td>
            <td><input type="reset" value="Cancel" name="Cancel"/></td>
    </table>
    <td><% if (request.getParameter("id") != null && Integer.parseInt(request.getParameter("id")) < 1000) { %>
    Thank You
    <% } %></td>
    <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
</form>
</body>
</html>
