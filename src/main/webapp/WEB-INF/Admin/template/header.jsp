
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>CodexConstruction - Admin</title>
        <!-- Simple bar CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/simplebar.css">
        <!-- Icons CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/feather.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/Style.css">

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/app-light.css" id="lightTheme">
        <script src="${pageContext.request.contextPath}/assets/js/chart.js"></script>
    </head>
    <body class="vertical  light">
        <div class="wrapper">
            <nav class="topnav navbar navbar-light mt-2">
                <button type="button" class="navbar-toggler text-muted mt-2 p-0 mr-3 collapseSidebar">
                    <i class="fe fe-menu navbar-toggler-icon"></i>
                </button>
                <form class="form-inline mr-auto searchform text-muted">
                    <input class="form-control mr-sm-2 bg-transparent border-0 pl-4 text-muted" type="search" placeholder="Type something..." aria-label="Search">
                </form>

                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link text-muted my-2" >
                            <span class="fe fe-grid fe-16 item-text" style="color: #0e3685"> Codex</span>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-muted pr-0" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <span class="avatar avatar-sm mt-2">
                            <img src="${pageContext.request.contextPath}/assets/img/avatar.jpg" alt="..." class="avatar-img rounded-circle">
                          </span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">Profile</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/codexconstruction/auth/logout">Log out <i class="fe fe-log-out fe-12"></i></a>
                        </div>
                    </li>
                </ul>
            </nav>
            <aside class="sidebar-left border-right bg-white shadow" id="leftSidebar" data-simplebar>
                <a href="#" class="btn collapseSidebar toggle-btn d-lg-none text-muted ml-2 mt-3" data-toggle="toggle">
                    <i class="fe fe-x"><span class="sr-only"></span></i>
                </a>
                <nav class="vertnav navbar navbar-light">
                    <!-- nav bar -->
                    <div class="w-100 mb-4 d-flex">
                        <a class="navbar-brand mx-auto mt-2 flex-fill text-center">
                            <img width="200px" src="${pageContext.request.contextPath}/assets/img/logo.png">
                        </a>
                    </div>
                    <ul class="navbar-nav flex-fill w-100 mb-2">
                        <li class="nav-item dropdown">
                            <a href="#dashboard" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                                <i class="fe fe-home fe-16"></i>
                                <span class="ml-3 item-text">Dashboard</span><span class="sr-only">(current)</span>
                            </a>
                            <ul class="collapse list-unstyled pl-4 w-100" id="dashboard">
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="${pageContext.request.contextPath}/codexconstruction/admin/dashboard"><span class="ml-1 item-text">Dashboard</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="navbar-nav flex-fill w-100 mb-2">
                        <li class="nav-item dropdown">
                            <a href="#tables" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                                <i class="fe fe-package fe-16"></i>
                                <span class="ml-3 item-text">Tables</span>
                            </a>
                            <ul class="collapse list-unstyled pl-4 w-100 w-100" id="tables">
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="${pageContext.request.contextPath}/codexconstruction/admin/devis"><span class="ml-1 item-text">Devis</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="${pageContext.request.contextPath}/codexconstruction/admin/typetravaux"><span class="ml-1 item-text">Types de Travaux</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="${pageContext.request.contextPath}/codexconstruction/admin/typefinition"><span class="ml-1 item-text">Types de finition</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="navbar-nav flex-fill w-100 mb-2">
                        <li class="nav-item dropdown">
                            <a href="#pages" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle nav-link">
                                <i class="fe fe-tool fe-16"></i>
                                <span class="ml-3 item-text">Util</span>
                            </a>
                            <ul class="collapse list-unstyled pl-4 w-100 w-100" id="pages">
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="${pageContext.request.contextPath}/codexconstruction/util/importcsv"><span class="ml-1 item-text">Import Csv</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link pl-3" href="${pageContext.request.contextPath}/codexconstruction/util/databaseconfirm"><span class="ml-1 item-text">Rentialisation Database</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </aside>