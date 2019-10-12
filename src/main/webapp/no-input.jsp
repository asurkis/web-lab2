<%@ page pageEncoding="UTF-8" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.results.*" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.parameters.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Лабораторная работа № 2</title>
    <script src="main.js" defer></script>
    <style><%@ include file="style.css" %></style>
</head>
<body>

<%@ include file="svg.jsp" %>
<%@ include file="header.html" %>

<jsp:useBean id="parameters" class="RequestParameters" scope="request" />
<% boolean somethingSet = !parameters.getX().isUnset() || !parameters.getY().isUnset() || !parameters.getR().isUnset();
   boolean fixX = somethingSet && !parameters.getX().isValid();
   boolean fixY = somethingSet && !parameters.getY().isValid();
   boolean fixR = somethingSet && !parameters.getR().isValid();

   boolean[] xsSet = new boolean[9];
   if (parameters.getX().isValid()) {
       for (double x: parameters.getX().getValues()) {
           xsSet[(int) x + 4] = true;
       }
   }

   double ySet = parameters.getY().isValid() ? parameters.getY().getValues()[0] : 0;

   boolean[] rsSet = new boolean[5];
   if (parameters.getR().isValid()) {
       for (double r: parameters.getR().getValues()) {
           rsSet[(int) r - 1] = true;
       }
   } %>

<form method="get" autocomplete="off">
<table>
    <tr>
        <td> Координата X: </td>
        <td>
        <% for (int i = -4; i <= 4; i++) { %>
            <div>
                <input type="checkbox" id="x<%= i %>-input" name="x" value="<%= i %>"
                    <% if (xsSet[i + 4]) { %> checked <% } %>>
                <label for="x<%= i %>-input"><%= i %></label>
            </div>
        <% } %>
        </td>
        <td>
            <span id="invalid-x" class="invalid-input" <% if (fixX) { %> style="visibility: visible;" <% } %>> Выберите значения </span>
        </td>
    </tr>
    <tr>
        <td> Координата Y: </td>
        <td> <input type="text" name="y" value="<%= ySet %>" required> </td>
        <td>
            <span id="invalid-y" class="invalid-input"> Введите число от -3 до 5 </span>
        </td>
    </tr>
    <tr>
        <td> Радиус: </td>
        <td>
        <% for (int i = 1; i <= 5; i++) { %>
            <div>
                <input type="checkbox" id="r<%= i %>-input" name="r" value="<%= i %>"
                    <% if (rsSet[i - 1]) { %> checked <% } %>>
                <label for="r<%= i %>-input"><%= i %></label>
            </div>
        <% } %>
        </td>
        <td>
            <span id="invalid-r" class="invalid-input" <% if (fixR) { %> style="visibility: visible;" <% } %>> Выберите значения </span>
        </td>
    </tr>
    <tr>
        <td colspan="3"> <input id="area-check-submit" type="submit" value="Проверить"> </td>
    </tr>
</table>
</form>

<jsp:useBean id="sessionResults" class="ResultsBean" scope="session" />
<% List<Result> results = sessionResults.getResults(); %>
<%@ include file="table.jsp" %>

</body>
</html>
