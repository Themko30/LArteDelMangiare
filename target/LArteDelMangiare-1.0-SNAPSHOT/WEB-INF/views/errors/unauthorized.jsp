<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Unauthorized"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site"/>
    </jsp:include>

</head>
<body>
<div class=" body app grid-x justify-center align-center">
    <H1>UNAUTHORIZED</H1>
    <br>
    <a href="/LArteDelMangiare_war_exploded/pages/home?page=1">Back To Homepage...</a>
</div>
</body>
</html>