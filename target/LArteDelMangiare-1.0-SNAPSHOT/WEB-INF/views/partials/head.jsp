<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="context" value="${pageContext.request.contextPath}"/>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1, viewport-fit=cover">
<title>${param.title}</title>
<meta name="description" content="Top quality sushi restaurant">
<link rel="icon" type="image/png" href="${context}/IMAGES/logoText.png">
<meta name="apple-mobile-web-capable" content="yes">
<meta name="format-detection" content="telephone-no">
<meta name="apple-mobile-web-app-title" content="L'Arte Del Mangiare">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<link rel="apple-touch-icon" href="${context}/IMAGES/logoText.png">
<link rel="apple-touch-startup-image" href="${context}/IMAGES/logoText.png">
<meta name="theme-color" content="#FD5901">
<link href="${context}/CSS/reset.css" rel="stylesheet">
<link href="${context}/CSS/library.css" rel="stylesheet">

<c:if test="${not empty param.styles}">
    <c:forTokens items="${param.styles}" delims="," var="style">
        <link href="${context}/CSS/${style}.css" rel="stylesheet">
    </c:forTokens>
</c:if>

<script src="${context}/JS/library.js" defer></script>

<c:if test="${not empty param.scripts}">
    <c:forTokens items="${param.scripts}" delims="," var="script">
        <script src="${context}/JS/${script}.js" defer></script>
    </c:forTokens>
</c:if>