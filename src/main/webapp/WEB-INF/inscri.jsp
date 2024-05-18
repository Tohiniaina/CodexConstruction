<%@ page import="java.util.List" %>
<%@ page import="codex.evaluation.model.Genre" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Genre> genres= (List<Genre>) request.getAttribute("genres");
%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8" />
    <title>CodexConstruction - Inscription</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/parsley.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
        .main {
            background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("${pageContext.request.contextPath}/assets/img/projects/construction-2.jpg");
            background-size: cover;
            position: relative;
        }
    </style>
</head>
<body>

<div class="bg-light py-3 py-md-5 main">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-12 col-md-11 col-lg-8 col-xl-7 col-xxl-6">
                <div class="bg-white p-4 p-md-5 rounded shadow-sm">
                    <div class="row">
                        <div class="col-12">
                            <div class="mb-5">
                                <h3>Sign Up</h3>
                            </div>
                        </div>
                    </div>
                    <form action="${pageContext.request.contextPath}/codexconstruction/auth/signupAdmin" method="post" id="inscri-form" data-parsley-validate="">
                        <div class="row gy-3 gy-md-4 overflow-hidden">
                            <div class="col-12">
                                <label for="nom" class="form-label">Nom <span class="text-danger">*</span></label>
                                <input type="text" class="form-control" name="nom" id="nom" required="">
                            </div>

                            <div class="col-12">
                                <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" class="form-control" name="email" id="email" data-parsley-trigger="change" required="">
                            </div>

                            <div class="col-12">
                                <label for="password" class="form-label">Password <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" name="password" id="password" required data-parsley-trigger="change" data-parsley-pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$">
                                <span class="text-muted">Le mot de passe doit contenir au moins 1 majuscule, 1 minuscule, 1 chiffre et avoir une longueur d'au moins 8 caractères.</span>
                            </div>


                            <div class="text-center">
                                <p style="color: orangered">${error}</p>
                            </div>
                            <div class="col-12">
                                <div class="d-grid">
                                    <button class="btn btn-lg btn-primary" type="submit">Sign up now</button>
                                </div>
                            </div>

                            <div class="text-center">
                                <div class="col-12">
                                    <div class="text-center">
                                        <a href="${pageContext.request.contextPath}/codexconstruction/auth" class="link-secondary text-decoration-none">Already have account</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/parsley.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('#inscri-form').parsley().on('field:validated', function() {
            var ok = $('.parsley-error').length === 0;
            $('.bs-callout-info').toggleClass('hidden', !ok);
            $('.bs-callout-warning').toggleClass('hidden', ok);
        })
            .on('form:submit', function() {
                return true; // Allow form submission
            });

        // Ajouter une validation personnalisée pour la date de naissance
        window.Parsley.addValidator('birthdate', {
            validateString: function(value, requirement) {
                // Convertir la date de naissance en objet Date
                var birthdate = new Date(value);
                // Calculer la date actuelle moins 18 ans
                var eighteenYearsAgo = new Date();
                eighteenYearsAgo.setFullYear(eighteenYearsAgo.getFullYear() - requirement);
                // Vérifier si la date de naissance est supérieure à il y a 18 ans
                return birthdate <= eighteenYearsAgo;
            },
            // Message d'erreur personnalisé
            messages: {
                fr: 'Vous devez avoir au moins %s ans.'
            }
        });
    });
</script>
</body>
</html>
