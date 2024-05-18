<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="codex.evaluation.model.Travaux" %>
<%@ include file="template/header.jsp" %>
<%
    List<Travaux> listTravaux = (List<Travaux>) request.getAttribute("listTravaux");
    /*int totalPages = (int) request.getAttribute("totalPages");
    int numberpage = (int) request.getAttribute("page");
    String motCles = (String) request.getAttribute("motCles");*/
    NumberFormat formatMonetaire = NumberFormat.getCurrencyInstance();
%>

<main role="main" class="main-content">

    <div class="card shadow">
        <div class="card-body">
            <div class="row m-1">
                <form id="bar-search" class="form-header pt-3 pb-3" action="${pageContext.request.contextPath}/type" method="GET">
                    <input class="au-input au-input--xl" type="text" name="motsCles" placeholder="Search ..."/>
                    <button class="au-btn--submit text-white" type="submit">
                        <i class="fe fe-22 fe-search"></i>
                    </button>
                </form>
            </div>

            <div class="text-center">
                <p style="color: orangered">${error}</p>
            </div>

            <!-- table -->
            <table class="table table-hover table-borderless border-v">
                <thead class="thead-dark">
                <tr>
                    <th class="col-md-1">Id</th>
                    <th class="col-md-1">Code</th>
                    <th class="col-md-3">Nom</th>
                    <th class="col-md-1">Unite</th>
                    <th class="col-md-3">PU</th>
                </tr>
                </thead>
                <tbody>
                    <% for (Travaux travaux: listTravaux) { %>
                        <tr class="accordion-toggle collapsed" id="c-2474" data-toggle="collapse" data-parent="#c-2474" href="#collap-2474">
                            <td><%=travaux.getId() %></td>
                            <td><%=travaux.getCode() %></td>
                            <td><%=travaux.getNom() %></td>
                            <td><%=travaux.getUnite() %></td>
                            <td><%=formatMonetaire.format(travaux.getPu()) %></td>
                            <td class="text-center">
                                <button class="dropdown-item modifyBtn" data-toggle="modal" data-target="#travauxModal" data-id="<%=travaux.getId()%>" data-code="<%=travaux.getCode()%>" data-nomtravaux="<%=travaux.getNom()%>" data-unite="<%=travaux.getUnite()%>" data-pu="<%=travaux.getPu()%>">Modifier <i class="fe fe-16 fe-edit"></i></button>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <div class="modal fade" id="travauxModal" tabindex="-1" role="dialog" aria-labelledby="travauxModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/codexconstruction/admin/savetravaux" method="post">
                            <div class="modal-header">
                                <h5 class="modal-title" id="travauxModalLabel">Travaux</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="recipient-id" name="idTravaux">
                                <div class="form-group mb-3">
                                    <label for="recipient-code" class="col-form-label">Code</label>
                                    <input type="text" id="recipient-code" class="form-control" name="code">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="recipient-nomtravaux" class="col-form-label">Nom Travaux</label>
                                    <input type="text" id="recipient-nomtravaux" class="form-control" name="nomTravaux">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="recipient-unite" class="col-form-label">Unite</label>
                                    <input type="text" id="recipient-unite" class="form-control" name="unite">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="recipient-pu" class="col-form-label">PU</label>
                                    <input type="number" step="0.01" id="recipient-pu" class="form-control" name="pu">
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn mb-2 btn-secondary" data-dismiss="modal">Close</button>
                                <input class="btn mb-2 btn-success" type="submit" value="Valider" name="btnValider" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>


        </div>
    </div>

</main>

<%@ include file="template/footer.jsp" %>
<script>
    $(document).ready(function (){
        $('.modifyBtn').click(function (){
            var id = $(this).data('id');
            var code = $(this).data('code');
            var nom = $(this).data('nomtravaux');
            var unite = $(this).data('unite');
            var pu = $(this).data('pu');
            $('#recipient-id').val(id);
            $('#recipient-code').val(code);
            $('#recipient-nomtravaux').val(nom);
            $('#recipient-unite').val(unite);
            $('#recipient-pu').val(pu);
        });
    });
</script>


