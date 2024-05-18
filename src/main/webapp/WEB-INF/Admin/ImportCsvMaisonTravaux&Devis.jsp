
<%@ include file="template/header.jsp" %>

<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-8">
                <h2 class="page-title">Import Donnees</h2>
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form method="post" action="${pageContext.request.contextPath}/codexconstruction/util/importercsvmaisontravaux&devis" enctype="multipart/form-data">
                                    <div class="form-group mb-3">
                                        <div class="form-group mb-3">
                                            <label for="csvmaisontravaux">Maison et Travaux (*.csv)</label>
                                            <input type="file" id="csvmaisontravaux" class="form-control" name="csvmaisontravaux" accept=".csv" required>
                                        </div>
                                    </div>
                                    <div class="form-group mb-3">
                                        <div class="form-group mb-3">
                                            <label for="csvdevis">Devis (*.csv)</label>
                                            <input type="file" id="csvdevis" class="form-control" name="csvdevis" accept=".csv" required>
                                        </div>
                                    </div>
                                    <input class="btn btn-success" type="submit" value="Importer" name="btnValider" />
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<%@ include file="template/footer.jsp" %>


