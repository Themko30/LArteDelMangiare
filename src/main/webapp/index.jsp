<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="HomePage"/>
    </jsp:include>
</head>
<body>
<% response.sendRedirect("./pages/home?page=1"); %>
</body>
</html>