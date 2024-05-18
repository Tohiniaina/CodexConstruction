<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="codex.evaluation.model.Finition" %>
<%@ include file="template/header.jsp" %>
<%
    List<Finition> listFinition = (List<Finition>) request.getAttribute("listFinition");
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
                    <th class="col-md-5">Nom</th>
                    <th class="col-md-3">% Pourcentage</th>
                </tr>
                </thead>
                <tbody>
                    <% for (Finition finition: listFinition) { %>
                        <tr class="accordion-toggle collapsed" id="c-2474" data-toggle="collapse" data-parent="#c-2474" href="#collap-2474">
                            <td><%=finition.getId() %></td>
                            <td><%=finition.getNom() %></td>
                            <td><%=finition.getPourcentage() %> %</td>
                            <td class="text-center">
                                <button class="dropdown-item modifyBtn" data-toggle="modal" data-target="#finitionModal" data-id="<%=finition.getId()%>" data-nomfinition="<%=finition.getNom()%>" data-pourcentage="<%=finition.getPourcentage()%>">Modifier <i class="fe fe-16 fe-edit"></i></button>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <div class="modal fade" id="finitionModal" tabindex="-1" role="dialog" aria-labelledby="finitionModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form action="${pageContext.request.contextPath}/codexconstruction/admin/savefinition" method="post">
                            <div class="modal-header">
                                <h5 class="modal-title" id="finitionModalLabel">Finition</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <input type="hidden" id="recipient-id" name="idFinition">
                                <div class="form-group mb-3">
                                    <label for="recipient-nomfinition" class="col-form-label">Nom Finition</label>
                                    <input type="text" id="recipient-nomfinition" class="form-control" name="nomFinition">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="recipient-pourcentage" class="col-form-label">Pourcentage %</label>
                                    <input type="number" step="0.01" id="recipient-pourcentage" class="form-control" name="pourcentage">
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
            var nom = $(this).data('nomfinition');
            var pourcentage = $(this).data('pourcentage');
            $('#recipient-id').val(id);
            $('#recipient-nomfinition').val(nom);
            $('#recipient-pourcentage').val(pourcentage);
        });
    });
</script>


