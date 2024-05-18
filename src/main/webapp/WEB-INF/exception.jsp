<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String exception = request.getAttribute("exception").toString();
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>BTP - Exception</title>
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
            <h1 class="display-1 m-0 font-weight-bolder text-muted" style="font-size:80px;">404</h1>
            <h1 class="mb-1 text-muted font-weight-bold"><%=exception%></h1>
            <h6 class="mb-3 text-muted">The page could not be found.</h6>
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