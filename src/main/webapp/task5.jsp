<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.codecool.web.model.Task5" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>Task1</title>

    <!-- List init start-->
    <% List<Task5> list = (ArrayList<Task5>) request.getAttribute("list"); %>
    <!-- List init end -->
</head>
<body>
    <form action="task5" method="POST">
        <p>Set minimum price:<p>
        <p><input type="number" name="value"></p>
        <input type="submit" value="FILTER">
    </form>
    <table class="result">
        <tr>
            <th>Company</th><th>Product</th><th>Price</th>
        </tr>
        <% for (Task5 t : list) { %>
            <tr>
                <td align="left"><%= t.getCompany() %></td>
                <td align="left"><%= t.getProduct() %></td>
                <td align="left"><%= t.getPrice() %></td>
            </tr>
        <% } %>
    </table>

</body>
</html>
