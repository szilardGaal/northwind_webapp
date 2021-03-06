<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.codecool.web.model.Task3" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>Task1</title>

    <!-- List init start-->
    <% List<Task3> list = (ArrayList<Task3>) request.getAttribute("list"); %>
    <!-- List init end -->
</head>
<body>
    <form action="task3" method="POST">
        <p>Show companies with <input type="number" name="value" placeholder="5"> products</p>
        <input type="submit" value="FILTER">
    </form>
    <table class="result">
        <tr>
            <th>Company</th>
        </tr>
        <% for (Task3 t : list) { %>
            <tr>
                <td align="left"><%= t.getCompany() %></td>
            </tr>
        <% } %>
    </table>

</body>
</html>
