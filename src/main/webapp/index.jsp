<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<form action="/" method="POST">
    <h1>Добро пожаловать</h1>
    <br><a href="${pageContext.request.contextPath}/main?getAll">Отобразить студентов</a></br>
    <br><a href="${pageContext.request.contextPath}/main?Delete">Редактировать студента</a></br>
    <br><a href="${pageContext.request.contextPath}/main?Create">Добавить студента</a></br>
</form>
</body>
</html>
