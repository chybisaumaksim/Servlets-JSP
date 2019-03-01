<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Удаление студента</title>
</head>
<body>
<form action="<c:url value="main?Delete"/>" method="POST">
    <table>
        <tr>
            <td>Введите Id:</td>
            <td><input type="text" name="id" value="${student.id}" required pattern="^[0-9]+$"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="OK" name="OK"/></td>
            <td><input type="reset" value="Cancel" name="Cancel"/></td>
            <%--<td><%=request.getParameter("wrongId")%></td>--%>
            <td><%String s = response.getHeader("wrongId");%></td>
            <td><%=s%></td>
        <%--<td><% if (request.getParameter("id") != null && Integer.parseInt(request.getParameter("id")) < 1000) { %>--%>
            <%--Thank You--%>
            <%--<% } else { %>--%>
            <%--Wrong Id--%>
            <%--<% } %></td>--%>
        <%--<tr><%--%>
            <%--String id = request.getParameter("id");--%>
            <%--if (id == null || id.length() == 0 || Integer.parseInt(request.getParameter("id")) > 1000) {--%>
        <%--%>--%>
            <%--Error--%>
            <%--<%} else {%>--%>
            <%--This <%= id%> is correct--%>
            <%--<%}%></tr>--%>
        <%--</tr>--%>
    </table>
    <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
</form>
</body>
</html>