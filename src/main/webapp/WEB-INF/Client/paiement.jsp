<%@ page import="java.util.List" %>
<%@ page import="codex.evaluation.model.TypeMaison" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="codex.evaluation.model.Finition" %>
<%@ page import="codex.evaluation.model.ClientDevis" %>
<%
    List<String> bg = new ArrayList<>();
    bg.add("l-bg-cherry");
    bg.add("l-bg-blue-dark");
    bg.add("l-bg-green-dark");
    bg.add("l-bg-orange-dark");
    List<ClientDevis> devisList = (List<ClientDevis>) request.getAttribute("devisList");
%>
<%@ include file="template/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/StyleAlertModal.css">

        <!-- Page Title -->
        <div class="page-title text-center p-4 text-white" data-aos="fade">
            <div class="container position-relative">
                <h1 class="p-4 text-white">PAIEMENT</h1>
            </div>
        </div><!-- End Page Title -->
    </header>

<div class="container">

    <div id="successModal" class="modal fade" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-confirm">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="icon-box">
                        <i class="fe fe-check fe-24">&#xE876;</i>
                    </div>
                    <h4 class="modal-title w-100">Awesome!</h4>
                </div>
                <div class="modal-body">
                    <p class="text-center">Payement reussi.</p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-success btn-block" data-dismiss="modal">OK</button>
                </div>
            </div>
        </div>
    </div>

    <div id="errorModal" class="modal fade" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-confirm">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="icon-box" style="background: #ef513a !important;">
                        <i class="fe fe-x fe-24">&#xE876;</i>
                    </div>
                    <h4 class="modal-title w-100">Sorry!</h4>
                </div>
                <div class="modal-body">
                    <p class="text-center">Payement echoue.</p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger btn-block" style="background: #ef513a !important;" data-dismiss="modal">OK</button>
                </div>
            </div>
        </div>
    </div>

    <form id="paiement-form" data-parsley-validate="">
        <div class="row gy-3 gy-md-4 overflow-hidden mt-2">
            <div class="col-12">
                <label for="devis" class="form-label">Devis :</label>
                <select id="devis" name="devis" class="form-control" required data-parsley-trigger="change">
                    <%for(ClientDevis devis :devisList) {%>
                        <option value="<%=devis.getId()%>"><%=devis.getId()%></option>
                    <%}%>
                </select>
            </div>

            <div class="col-12">
                <label for="date" class="form-label">Date <span class="text-danger">*</span></label>
                <input type="date" class="form-control" name="date" id="date"  data-parsley-trigger="change" required="">
            </div>

            <div class="col-12">
                <label for="montant" class="form-label">Montant <span class="text-danger">*</span></label>
                <input type="number" class="form-control" name="montant" id="montant"  data-parsley-trigger="change" required="">
            </div>

            <div class="text-center">
                <p style="color: orangered">${error}</p>
            </div>
            <div class="col-12">
                <div class="d-grid">
                    <button class="btn btn-lg btn-primary" type="submit">Payer</button>
                </div>
            </div>

        </div>
    </form>

</div>

</main>

<%@ include file="template/footer.jsp" %>
<script>
    $(document).ready(() => {
        $('#paiement-form').submit((event) => {
            event.preventDefault();

            let formData = {
                devis: $('#devis').val(),
                date: $('#date').val(),
                montant: $('#montant').val()
            };

            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/codexconstruction/client/payer',
                data: formData,
                success: function(response) {
                    if (response.etat ==="succes") {
                        $('#successModal').modal('show');
                    }else {
                        $('#errorModal').modal('show');
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Erreur lors du paiement');
                    console.error(xhr.responseText);
                }
            });
        });
    });
</script>