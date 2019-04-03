<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.codecool.web.model.Task2" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>Task1</title>

    <!-- List init start-->
    <% List<Task2> list = (ArrayList<Task2>) request.getAttribute("list"); %>
    <!-- List init end -->
</head>
<body>
    <table class="result">
            <tr>
                <th>Company</th>
                <th>Number of Products</th>
            </tr>
        <% for (Task2 t : list) { %>
            <tr>
                <td align="left"><%= t.getCompany() %></td>
                <td align="left"><%= t.getNumberOfProducts() %></td>
            </tr>
        <% } %>
    </table>

</body>
</html>
