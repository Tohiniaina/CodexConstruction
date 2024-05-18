
<%@ include file="template/header.jsp" %>

<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">

            <div class="col-4">
                <a href="${pageContext.request.contextPath}/codexconstruction/admin/csvmaisontravaildevis">
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <p class="mb-0 text-center">
                                <strong class="mb-0 text-uppercase text-muted">Maison et Travaux</strong>
                                <strong class="mb-0 text-uppercase text-muted">Devis</strong>
                            </p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-4">
                <a href="${pageContext.request.contextPath}/codexconstruction/admin/csvpaiement">
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <p class="mb-0 text-center">
                                <strong class="mb-0 text-uppercase text-muted">Paiement</strong>
                            </p>
                        </div>
                    </div>
                </a>
            </div>

        </div>
    </div>
</main>

<%@ include file="template/footer.jsp" %>


