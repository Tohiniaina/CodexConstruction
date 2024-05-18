<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8" />
    <title>CodexConstruction - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/parsley.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
        body {
            background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("${pageContext.request.contextPath}/assets/img/projects/features-4.jpg");
            background-size: cover;
            position: relative;
        }
    </style>
</head>
<body>

<div class="py-md-2 main">
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col-12 col-md-11 col-lg-8 col-xl-7 col-xxl-6">
                <div class="p-4 p-md-5 rounded shadow-sm">
                    <div class="row">
                        <div class="col-12">
                            <div class="mb-5">
                                <h3 class="text-white">Log in</h3>
                            </div>
                        </div>
                    </div>
                    <form action="${pageContext.request.contextPath}/codexconstruction/auth/signinAdmin" method="post" id="login-form" data-parsley-validate="">
                        <div class="row gy-3 gy-md-4 overflow-hidden text-white">
                            <div class="col-12">
                                <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com" value="${username}"  data-parsley-trigger="change" required="">
                            </div> 
                            <div class="col-12">
                                <label for="password" class="form-label">Password <span class="text-danger">*</span></label>
                                <input type="password" class="form-control" name="password" id="password" required="" data-parsley-trigger="change">
                            </div>

                            <div class="text-center">
                                <p style="color: orangered">${error}</p>
                            </div>
                            <div class="col-12">
                                <div class="d-grid">
                                    <button class="btn btn-lg btn-success" type="submit">Log in now</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!--<div class="row">
                        <div class="col-12">
                            <hr class="mt-5 mb-4 border-secondary-subtle">
                            <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-end">
                                <a href="${pageContext.request.contextPath}/codexconstruction/auth/inscriAdmin" class="link-light text-decoration-none">Create new account</a>
                            </div>
                        </div>
                    </div>-->

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
        $('#login-form').parsley().on('field:validated', function () {
            var ok = $('.parsley-error').length === 0;
            $('.bs-callout-info').toggleClass('hidden', !ok);
            $('.bs-callout-warning').toggleClass('hidden', ok);
        })
    });
</script>
</body>
</html>
