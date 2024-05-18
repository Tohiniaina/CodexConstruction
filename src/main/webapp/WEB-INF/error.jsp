<%@ page import="java.util.List" %>
<%@ page import="codex.evaluationbtp.model.Error" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Error> errorList = (List<Error>) request.getAttribute("listError");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>BTP - Error Page</title>
    <!-- Simple bar CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css">
    <!-- Icons CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/feather.css">
    <!-- App CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app-light.css" id="lightTheme">
</head>
<body class="light ">
<div class="wrapper vh-100">
    <div class="align-items-center h-100 d-flex w-50 mx-auto">
        <div class="mx-auto text-center">
            <h1 class="display-1 m-0 font-weight-bolder text-muted" style="font-size:80px;">Error</h1>
            <% for (Error error: errorList) { %>
               <p class="text-danger">Line <%=error.getLineNumber()%> : <%=error.getError()%></p>
            <% } %>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
<script src='${pageContext.request.contextPath}/assets/js/jquery.stickOnScroll.js'></script>
<script src="${pageContext.request.contextPath}/assets/js/apps.js"></script>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag()
    {
        dataLayer.push(arguments);
    }
    gtag('js', new Date());
    gtag('config', 'UA-56159088-1');
</script>
</body>
</html>
</body>
</html>