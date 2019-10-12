<%@ page pageEncoding="UTF-8" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.results.*" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.parameters.*" %>

<svg width="600" height="600">
    <% for (int i = 5; i >= 1; i--) { %>
        <path id="r<%= i %>-path" d="M 300,<%= 300-50*i %> A <%= 50*i %>,<%= 50*i %> 0 0 1 <%= 300+50*i %>,300 L <%= 300+25*i %>,300 L 300,<%= 300+50*i %> L <%= 300-50*i %>,<%= 300+50*i %> L <%= 300-50*i %>,300 L 300,300"></path>
    <% } %>
    <line x1="300" y1="10" x2="300" y2="600"></line>
    <line x1="0" y1="300" x2="590" y2="300"></line>
    <polygon points="300,0 295,10 305,10"></polygon>
    <polygon points="600,300 590,295 590,305"></polygon>
    <% for (int i = -5; i <= 5; i++) { %>
        <circle cx="<%= 300 + 50 * i %>" cy="300" r="3"></circle>
        <text x="<%= 296 + 50 * i %>" y="296" text-anchor="end"><%= i %></text>
        <% if (i != 0) { %>
        <circle cx="300" cy="<%= 300 - 50 * i %>" r="3"></circle>
        <text x="296" y="<%= 296 - 50 * i %>" text-anchor="end"><%= i %></text>
        <% } %>
    <% } %>
    <text x="290" y="10" text-anchor="end">y</text>
    <text x="595" y="290" text-anchor="end">x</text>

    <jsp:useBean id="requestResults" class="ResultsBean" scope="request" />
    <% for (Result r: requestResults.getResults()) { %>
        <circle cx="<%= 300 + 50 * r.getX() %>" cy="<%= 300 - 50 * r.getY() %>" r="5"
            style="fill: <%= r.doesFall() ? "#0f0" : "#00f" %>"></circle>
    <% } %>

    <circle id="mouse-circle" cx="-100" cy="-100" r="5" style="fill: #eba300;"></circle>
</svg>
