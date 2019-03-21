<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Удаление студента</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/myStyle.css"%>
</style>
<style>
    <%@include file="/WEB-INF/css/menuStyle.css"%>
</style>
<div class="navbar">
    <div class="dropdown">
        <button class="dropbtn" onclick="myFunction()">Студенты
            <i class="fa fa-caret-down"></i>
        </button>
        <form action="/" method="POST">
            <div class="dropdown-content" id="myDropdown">
                <br><a href="${pageContext.request.contextPath}/main?getAll">Список всех студентов</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Delete">Редактировать студента</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Create">Добавить студента</a></br>
            </div>
        </form>
    </div>
    <div class="dropdown">
        <button class="dropbtn" onclick="myFunction()">Предметы
            <i class="fa fa-caret-down"></i>
        </button>
        <form action="/" method="POST">
            <div class="dropdown-content" id="myDropdown2">
                <br><a href="${pageContext.request.contextPath}/main?getAll">Список всех студентов</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Delete">Редактировать студента</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Create">Добавить студента</a></br>
            </div>
        </form>
    </div>
    <div class="dropdown">
        <button class="dropbtn" onclick="myFunction()">Оценки
            <i class="fa fa-caret-down"></i>
        </button>
        <form action="/" method="POST">
            <div class="dropdown-content" id="myDropdown3">
                <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Delete">Редактировать студента</a></br>
                <br><a href="${pageContext.request.contextPath}/main?Create">Добавить студента</a></br>
            </div>
        </form>
    </div>
</div>
<script>
    <%@include file="/WEB-INF/js/index.js"%>
</script>
<b><% if (request.getAttribute("messageDelete") != null) { %>
    <div id="success" class="message">
        <%=request.getAttribute("messageDelete")%>
    </div>
    <% } %>
</b>
<form action="<c:url value="/main"/>" method="POST">
    <table width="100%" cellpadding="1" cellspacing="1">
        <tr>
            <td></td>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Дата рождения</td>
            <td>Год поступления</td>
            <td>Редактировать</td>
        </tr>
        <c:forEach items="${student}" var="student">
            <tr>
                <td><input type="radio" name="id" value="${student.id}"></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.surname}"/></td>
                <td><c:out value="${student.birthDate}"/></td>
                <td><c:out value="${student.enterYear}"/></td>
                <td>
                    <select name="menu" size="1">
                        <div class="dropdown-content" id="myDropdown4">
                            <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
                            <br><a href="${pageContext.request.contextPath}/main?Delete">Редактировать студента</a></br>
                            <br><a href="${pageContext.request.contextPath}/main?Create">Добавить студента</a></br>
                        </div>
                    </select>
                </td>
                <%--<td>--%>
                    <%--<select name="menu" size="1">--%>
                            <%--<option type="submitForm" value="Удалить" name="Delete">удалить</option>--%>
                            <%--<option selected="selected" value="Править" name="Update">редактировать</option>--%>
                    <%--</select>--%>
                <%--</td>--%>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="  OK   " name="Update"/>
</form>
</body>
</html>
