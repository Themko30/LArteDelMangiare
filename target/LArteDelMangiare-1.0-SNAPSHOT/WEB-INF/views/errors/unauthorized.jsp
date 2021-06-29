<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Unauthorized"/>
    </jsp:include>

    <style>
      .internal-error {
        background-image: url("../../../IMAGES/unerror.jpg");
        background-repeat: no-repeat;
        background-size: cover;
        background-position: center center;
      }

      .internal-error > * {
        color: black;
      }
    </style>
</head>
<body>
<div class="internal-error">
    <H1>UNAUTHORIZED</H1>
    <br>
    <a href="/LArteDelMangiare_war_exploded/pages/">Back To Homepage...</a>
</div>
</body>
</html>
