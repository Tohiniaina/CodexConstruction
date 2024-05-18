<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8" />
    <title>CodexConstruction - Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css">
    <!-- Icons CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/feather.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/Style.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/app-light.css" id="lightTheme">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
        .head {
            background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("${pageContext.request.contextPath}/assets/img/page-title-bg.jpg");
            background-size: cover;
        }

        .nav-link {
            color: rgba(255, 255, 255, 0.55) !important;
        }

        .nav-link:hover {
            color: white !important;
        }
    </style>
</head>
<body>

<header id="header" class="header d-flex align-items-center fixed-top">

</header>

<main class="main">
    <header class="head">
        <nav class="navbar navbar-expand-lg navbar-light">
            <h2 class="m-3 text-white"><img width="150px" src="${pageContext.request.contextPath}/assets/img/logo.png">CodexConstruction<span style="color: #feb900">.</span></h2>
            <button class="navbar-toggler m-4" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end p-4" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/codexconstruction/client">HOME</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/codexconstruction/client/devis">DEVIS</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/codexconstruction/client/paiement">PAIEMENT</a>
                    </li>
                </ul>
            </div>

            <div>
                <a class="nav-link dropdown-toggle text-muted pr-0" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <span class="avatar avatar-sm mt-2">
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.jpg" alt="..." class="avatar-img rounded-circle" width="50px">
                      </span>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Profile</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/codexconstruction/auth/logout">Log out <i class="fe fe-log-out fe-12"></i></a>
                </div>
            </div>
        </nav>