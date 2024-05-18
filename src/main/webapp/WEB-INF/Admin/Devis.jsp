<%@ page import="java.util.List" %>
<%@ page import="codex.evaluation.model.V_ClientDevis" %>
<%@ page import="java.text.NumberFormat" %>
<%@ include file="template/header.jsp" %>
<%
    List<V_ClientDevis> listDevis = (List<V_ClientDevis>) request.getAttribute("listDevis");
    int totalPages = (int) request.getAttribute("totalPages");
    int numberpage = (int) request.getAttribute("page");
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
                    <th class="col-md-1">Ref</th>
                    <th class="col-md-2">Client</th>
                    <th class="col-md-2">Lieu</th>
                    <th class="col-md-2">Type Maison</th>
                    <th class="col-md-2">Date Devis</th>
                    <th class="col-md-3">Montant Total</th>
                    <th class="col-md-3">Montant Paye</th>
                    <th class="col-md-3">% Paiement</th>
                </tr>
                </thead>
                <tbody>
                    <% for (V_ClientDevis devis: listDevis) { %>
                        <%
                            double pourcentage = (devis.getMontantpaye()*100)/devis.getMontanttotal();
                            String styleClasse = "accordion-toggle collapsed text-white";
                            if (pourcentage > 50) {
                                styleClasse += " bg-success";
                            }
                            if (pourcentage < 50) {
                                styleClasse += " bg-danger";
                            }

                        %>
                        <tr class="<%=styleClasse%>" id="c-2474" data-toggle="collapse" data-parent="#c-2474" href="#collap-2474">
                            <td><%=devis.getRef() %></td>
                            <td><%=devis.getUserClient().getNumero() %></td>
                            <td><%=devis.getLieu() %></td>
                            <td><%=devis.getTypeMaison().getNom() %><br><%=devis.getFinition_nom() %></td>
                            <td><%=devis.getDatedevis() %></td>
                            <td><%=formatMonetaire.format(devis.getMontanttotal())%></td>
                            <td><%=formatMonetaire.format(devis.getMontantpaye())%></td>
                            <td><%=Math.floor((devis.getMontantpaye()*100)/devis.getMontanttotal())%> %</td>
                            <td class="text-center">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/codexconstruction/admin/detailsdevis?devis=<%=devis.getId()%>">Details <i class="fe fe-16 fe-info"></i></a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <div class="row justify-content-between m-1">
                <div class="col-md-1"></div>

                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <%if (numberpage > 1) { %>
                        <li class="page-item"><a class="page-link bg-secondary text-white" href="${pageContext.request.contextPath}/codexconstruction/admin/devis?page=<%=numberpage-1%>">Previous</a></li>
                        <% } %>
                        <%for(int i = 1; i < totalPages; i++) { %>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/codexconstruction/admin/devis?page=<%=i%>"><%=i%></a></li>
                        <% } %>
                        <%if (numberpage < totalPages) { %>
                        <li class="page-item"><a class="page-link bg-secondary text-white" href="${pageContext.request.contextPath}/codexconstruction/admin/devis?page=<%=numberpage+1%>">Next</a></li>
                        <% } %>
                    </ul>
                </nav>
            </div>

        </div>
    </div>

</main>

<%@ include file="template/footer.jsp" %>
<script>

</script>


