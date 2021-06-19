<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1, viewport-fit=cover">
<title>${param.title}</title>
<meta name="description" content="Top quality sushi restaurant">
<link rel="icon" type="image/png" href="../../../IMAGES/logoText.png">
<meta name="apple-mobile-web-capable" content="yes">
<meta name="format-detection" content="telephone-no">
<meta name="apple-mobile-web-app-title" content="L'Arte Del Mangiare">
<meta name="apple-mobile-web-app-status-bar-style" content="default">
<link rel="apple-touch-icon" href="../../../IMAGES/logoText.png">
<link rel="apple-touch-startup-image" href="../../../IMAGES/logoText.png">
<meta name="theme-color" content="#FD5901">
<link href="CSS/library.css" rel="stylesheet">
<c:if test="${not empty param.style}">
    <link rel="stylesheet" href="CSS/${param.style}"
</c:if>
<script src="JS/library.js" defer></script>
<c:if test="${not empty param.script}">
    <script src="JS/${param.script}" defer></script>
</c:if>