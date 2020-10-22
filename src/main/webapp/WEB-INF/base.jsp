<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${requestScope.title}</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css"/>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="/WEB-INF/navbar.jsp" flush="true"/>
<main role="main" class="container flex-shrink-0">
    <jsp:include page="${requestScope.content}" flush="true"/>
</main>

<footer class="footer mt-auto py-3">
    <div class="container">
        <span class="text-muted">FourThings+ (version ${requestScope.version})</span>
    </div>
</footer>

</body>
</html>
