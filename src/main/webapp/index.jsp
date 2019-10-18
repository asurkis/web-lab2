<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Лабораторная работа № 2</title>
    <script src="main.js" defer></script>
    <style><%@ include file="style.css" %></style>
</head>
<body>

<div id="header-box">
    <div> Студент: Суркис Антон Игоревич </div>
    <div> Группа: P3213 </div>
    <div> Вариант: 213019 </div>
</div>
<div></div>

<svg width="600" height="600">
    <jsp:include page="/picture" />
</svg>
<jsp:include page="/input.jsp" />
<jsp:include page="/table.jsp" />

</body>
</html>
