<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>обновление списка студентов</title>
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
<form action="<c:url value="/main"/>" method="POST">
    <b><% if (request.getAttribute("messageSuccess") != null) { %>
        <div id="success" class="message">
            <%=request.getAttribute("messageSuccess")%>
        </div>
        <% } else if (request.getAttribute("messageForName") != null
                || request.getAttribute("messageForSurname") != null
                || request.getAttribute("messageForBirthDate") != null
                || request.getAttribute("messageForEnterYear") != null) { %>
        <div id="fall" class="message">
            "Ошибка создания записи о студенте"
        </div>
        <% } %>
    </b>
    <input type="hidden" name="id" value="${student.id}"/>
    <table>
        <tr>
            <td>Имя:</td>
            <td><input type="text" name="name" value="${student.name}" required pattern="^[a-zA-Zа-яёА-ЯЁ]+$"/></td>
            <% if (request.getAttribute("messageForName") != null) { %>
            <td>
                <div id="error1" class="text">
                    <%=request.getAttribute("messageForName")%>
                </div>
            </td>
            <% } %>
        </tr>
        <tr>
            <td>Фамилия:</td>
            <td><input type="text" name="surname" value="${student.surname}" required pattern="^[a-zA-Zа-яёА-ЯЁ]+$"/>
                    <% if (request.getAttribute("messageForSurname") != null) { %>
            <td>
                <div id="error2" class="text">
                    <%=request.getAttribute("messageForSurname")%>
                </div>
            </td>
            <% } %>
            </td>
        </tr>
        <tr>
            <td>Дата рождения:</td>
            <td><input type="text" name="birthDate" value="${student.birthDate}" required
                       pattern="^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$"/></td>
            <% if (request.getAttribute("messageForBirthDate") != null) { %>
            <td>
                <div id="error3" class="text">
                    <%=request.getAttribute("messageForBirthDate")%>
                </div>
            </td>
            <% } %>
        </tr>
        <tr>
            <td>Год поступления:</td>
            <td><input type="text" name="enterYear" value="${student.enterYear}" required pattern="^[0-9]{4}"/></td>
            <% if (request.getAttribute("messageForEnterYear") != null) { %>
            <td>
                <div id="error4" class="text">
                    <%=request.getAttribute("messageForEnterYear")%>
                </div>
            </td>
            <% } %>
        </tr>
    </table>
    <table>
        <tr>
            <td><input type="submit" value="Обновить" name="Update"/></td>
        </tr>
    </table>
</form>
</body>
</html>