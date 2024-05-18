<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8" />
    <title>CodexConstruction - Bienvenue</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
        .main {
            background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("${pageContext.request.contextPath}/assets/img/hero-carousel/hero-carousel-1.jpg");
            background-size: cover;
            position: relative;
        }
    </style>
</head>
<body>

<main class="main">
    <section id="hero" class="hero section">
        <div class="info d-flex align-items-center">
            <div class="container">
                <div class="row justify-content-center" data-aos="fade-up" data-aos-delay="100">
                    <div class="col-lg-6 text-center">
                        <h2 class="">Bienvenue sur CodexConstruction</h2>
                        <p>
                            Bienvenue sur notre site dédiée au BTP, ou nous construisons votre avenir brique par brique.
                            Découvrez nos services des construction innovants et nos projets qui transforment des idées en réalité.
                        </p>
                        <a href="${pageContext.request.contextPath}/codexconstruction/auth/loginclient" class="btn-get-started col-md-5">Commencer</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>

</body>
</html>
