<%@ page pageEncoding="UTF-8" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.results.*" %>
<%@ page import="ru.ifmo.se.asurkis.web.lab2.parameters.*" %>

<%! private static int[] colorByMatches(int matches, double[] radiuses) {
        if (radiuses != null && radiuses.length != 0) {
            return new int[] {
                255 - 255 * matches / radiuses.length,
                255 * matches / radiuses.length,
                0
            };
        } else {
            return new int[] { 128, 128, 128 };
        }
    } %>
<%! private static String styleByMatches(int matches, double[] radiuses) {
        int[] color = colorByMatches(matches, radiuses);
        return String.format("fill: #%02x%02x%02x; stroke: #000", color[0], color[1], color[2]);
    } %>

<% Object radiusesObj = request.getAttribute("radiuses");
   double[] radiuses = null;
   if (radiusesObj instanceof double[]) {
       radiuses = (double[]) radiusesObj;
   } %>

<% if (radiuses != null) {
   for (double r: radiuses) { %>
<path id="r<%= (int) r %>-path" d="M 300,<%= 300-50*r %> A <%= 50*r %>,<%= 50*r %> 0 0 1 <%= 300+50*r %>,300 L <%= 300+25*r %>,300 L 300,<%= 300+50*r %> L <%= 300-50*r %>,<%= 300+50*r %> L <%= 300-50*r %>,300 L 300,300"></path>
<% }} %>
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

<jsp:useBean id="sessionResults" class="ResultsBean" scope="session" />
<% for (Result res: sessionResults.getResults()) {
       int matches = 0;
       if (radiuses != null) {
       for (double r: radiuses) {
           matches += res.fallsInto(r) ? 1 : 0;
       }} %>
    <circle cx="<%= 300 + 50 * res.getX() %>" cy="<%= 300 - 50 * res.getY() %>" r="5"
        style="<%= styleByMatches(matches, radiuses) %>"></circle>
<% } %>

<circle id="mouse-circle" cx="-100" cy="-100" r="5" style="fill: #eba300;"></circle>

<% if (radiuses != null) {
   for (int i = 0; i <= radiuses.length; i++) {
       int[] color = colorByMatches(i, radiuses); %>
<circle cx="12" cy="<%= 24 * i + 12 %>" r="5"
    style="<%= styleByMatches(i, radiuses) %>"></circle>
<text x="24" y="<%= 24 * i + 18 %>"> Попадает в <%= i %> радиус(ов) </text>
<% }} else { %>
<circle cx="12" cy="12" r="5" style="<%= styleByMatches(0, null) %>"></circle>
<text x="24" y="18"> Радиусы не заданы </text>
<% } %>
