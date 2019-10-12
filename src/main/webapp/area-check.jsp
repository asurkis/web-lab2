<%@ page pageEncoding="UTF-8" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.results.*" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.parameters.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Лабораторная работа № 2</title>
    <style><%@ include file="style.css" %></style>
</head>
<body>

<%@ include file="svg.jsp" %>
<%@ include file="header.html" %>

<% List<Result> results = requestResults.getResults(); %>
<%@ include file="table.jsp" %>

<a href="controller"> Назад </a>

</body>
</html>
