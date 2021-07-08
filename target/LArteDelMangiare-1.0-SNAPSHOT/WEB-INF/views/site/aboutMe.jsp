<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <jsp:include page="../partials/head.jsp">
        <jsp:param name="title" value="Welcome to L`Arte Del Mangiare"/>
        <jsp:param name="styles" value="site"/>
        <jsp:param name="scripts" value="site"/>
    </jsp:include>

    <style>
      body {
        background: linear-gradient(var(--primary), var(--secondary));
      }
    </style>
</head>
<body>
<main class="app grid-y">
    <%@include file="../partials/site/header.jsp" %>
    <section class="grid-x justify-center align-center">
        <h1>About Me!</h1>
        <p>
            Hi i'm Alfonso D'Albenzio a student in Computer Science from Unisa.
            I've been making this site as part of a project for an exam and during the coding i've
            come to the conclusion that this is the job i wanna do in the future.
            Hope you like it! Also i look forward to the feeling when i'll come back to this...my
            first site ever in a couple of years...

            Sincerely,
            Alfonso D'Albenzio.
        </p>
    </section>
    <%@include file="../partials/site/footer.jsp" %>
</main>
</body>
</html>