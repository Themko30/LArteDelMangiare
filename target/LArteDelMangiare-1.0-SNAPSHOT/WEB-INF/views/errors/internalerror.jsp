<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/views/partials/head.jsp">
        <jsp:param name="title" value="Internal Error"/>
    </jsp:include>

    <style>
      .internal-error {
        background-image: url("../../../IMAGES/interror.jpg");
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
    <h1>INTERNAL ERROR!</h1>
    <br>
    <a href="/LArteDelMangiare_war_exploded/pages/">Back To Homepage...</a>
</div>
</body>
</html>
