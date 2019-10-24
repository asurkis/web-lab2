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

<div> <svg width="600" height="600">
    <jsp:include page="/picture" />
</svg> </div>
<div>
    <div id="header-box">
        <div> Студент: Суркис Антон Игоревич </div>
        <div> Группа: P3213 </div>
        <div> Вариант: 213019 </div>
    </div>
    <jsp:include page="/input.jsp" />
</div>
<div> <jsp:include page="/table.jsp" /> </div>

</body>
</html>
