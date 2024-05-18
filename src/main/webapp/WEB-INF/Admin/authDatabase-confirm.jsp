<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">
    <title>BTP - Confirmation Database</title>
    <!-- Simple bar CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css">
    <!-- Icons CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/feather.css">
    <!-- App CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app-light.css" id="lightTheme">
</head>
<body class="light ">
<div class="wrapper vh-100">
    <div class="row align-items-center h-100">
        <form class="col-lg-3 col-md-4 col-10 mx-auto text-center">
            <div class="mx-auto text-center my-4">
                <img width="250px" src="${pageContext.request.contextPath}/assets/img/logo.png">
                <h4 class="my-3">Reinitialisation du base de données</h4>
            </div>
            <div class="alert alert-danger" role="alert"> Tous votre données vont etre perdu. Cliquer sur le bouton si vous voulez continuer</div>
            <a href="${pageContext.request.contextPath}/codexconstruction/util/resetDatabase" class="btn btn-lg btn-danger btn-block">Reinitialiser</a>
            <p class="mt-5 mb-3 text-muted">© 2024</p>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/simplebar.min.js"></script>
<script src='${pageContext.request.contextPath}/assets/js/jquery.stickOnScroll.js'></script>
<script src="${pageContext.request.contextPath}/assets/js/apps.js"></script>
</body>
</html>