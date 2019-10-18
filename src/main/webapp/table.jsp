<%@ page pageEncoding="UTF-8" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.results.*" %>
<%@ page import="java.util.List" %>

<jsp:useBean id="sessionResults" class="ResultsBean" scope="session" />
<% List<Result> results = sessionResults.getResults(); %>

<% if (!results.isEmpty()) { %>
<form method="get">
<table id="results-table">
    <thead>
        <tr>
            <th> <input id="remove-check-all" type="checkbox"> </th>
            <th> Координата X </th>
            <th> Координата Y </th>
            <th> Радиус </th>
            <th> Попадает? </th>
        </tr>
    </thead>
    <tbody>
        <% for (int i = 0; i < results.size(); i++) {
               Result r = results.get(i); %>
            <tr>
                <td> <input type="checkbox" name="d" value="<%= i %>"> </td>
                <td> <%= r.getX() %> </td>
                <td> <%= r.getY() %> </td>
                <td> <%= r.getRadius() %> </td>
                <td> <%= r.doesFall() ? "да" : "нет" %> </td>
            </tr>
        <% } %>
    </tbody>
    <tfoot>
        <tr>
            <td colspan="5">
                <input type="submit" value="Очистить">
            </td>
        </tr>
    </tfoot>
</table>
</form>
<% } %>
