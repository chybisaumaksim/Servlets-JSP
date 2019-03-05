<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Добавить студента</title>
</head>
<body>
<form action="<c:url value="/main"/>" method="POST">
    <table>
        <td><% if (request.getAttribute("message") != null) { %>
            <%=request.getAttribute("message")%>
            <% } %></td>
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
            <td><input type="submit" value="Create" name="Create"/></td>
            <td><input type="submit" value="Cancel" name="Cancel"/></td>
        </tr>
    </table>
    <td><% if (request.getParameter("name") != null
            && request.getParameter("name").length() > 2
            && request.getParameter("name").length() < 15
            && request.getParameter("surname").length() > 2
            && request.getParameter("surname").length() < 15
            && Integer.parseInt(request.getParameter("enterYear")) > 1900
            && Integer.parseInt(request.getParameter("enterYear")) < 2030
    ) { %>
        Thank You
        <% } %></td>
    <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
</form>
</body>
</html>