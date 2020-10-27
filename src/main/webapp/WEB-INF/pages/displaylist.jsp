<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="content">
    <h1>${fn:escapeXml(requestScope.list.name)}</h1>
    <p>${fn:escapeXml(requestScope.list.description)}</p>
</div>

