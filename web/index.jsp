<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <h1>Good <% if (new GregorianCalendar().get(Calendar.HOUR_OF_DAY) < 12) {%>
    Morning
    <% } else { %>
    Afternoon
    <% } %></h1>
  </body>
</html>