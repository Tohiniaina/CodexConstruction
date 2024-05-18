<%@ page import="codex.evaluation.model.V_ClientDevis" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ include file="template/header.jsp" %>
<%
    List<V_ClientDevis> listDevis = (List<V_ClientDevis>) request.getAttribute("listDevis");
    NumberFormat formatMonetaire = NumberFormat.getCurrencyInstance();
    int totalPages = (int) request.getAttribute("totalPages");
    int numberpage = (int) request.getAttribute("page");
%>

        <!-- Page Title -->
        <div class="page-title text-center p-4 text-white" data-aos="fade">
            <div class="container position-relative">
                <h1 class="p-4 text-white">DEVIS</h1>
                <a href="${pageContext.request.contextPath}/codexconstruction/client/newdevis" class="btn btn-outline-light col-md-2">Nouveau Devis</a>
            </div>
        </div><!-- End Page Title -->
    </header>

    <div class="container">
        <table class="table table-hover table-borderless border-v m-4">
            <thead class="thead-dark">
            <tr>
                <th class="col-md-1">Ref</th>
                <th class="col-md-2">Client</th>
                <th class="col-md-2">Lieu</th>
                <th class="col-md-2">Type Maison</th>
                <th class="col-md-2">Date Devis</th>
                <th class="col-md-3">Montant Total</th>
                <th class="col-md-3">Montant Paye</th>
            </tr>
            </thead>
            <tbody>
                <% for (V_ClientDevis devis: listDevis) { %>
                <tr class="accordion-toggle collapsed" id="c-2474" data-toggle="collapse" data-parent="#c-2474" href="#collap-2474">
                    <td><%=devis.getRef() %></td>
                    <td><%=devis.getUserClient().getNumero() %></td>
                    <td><%=devis.getLieu() %></td>
                    <td><%=devis.getTypeMaison().getNom() %><br><%=devis.getFinition_nom() %></td>
                    <td><%=devis.getDatedevis() %></td>
                    <td><%=formatMonetaire.format(devis.getMontanttotal())%></td>
                    <td><%=formatMonetaire.format(devis.getMontantpaye())%></td>
                    <td class="text-center">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/codexconstruction/client/detailsdevis?devis=<%=devis.getId()%>">PDF <i class="fe fe-16 fe-download"></i></a>
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
                    <li class="page-item"><a class="page-link bg-secondary text-white" href="${pageContext.request.contextPath}/codexconstruction/client/devis?page=<%=numberpage-1%>">Previous</a></li>
                    <% } %>
                    <%for(int i = 1; i < totalPages; i++) { %>
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/codexconstruction/client/devis?page=<%=i%>"><%=i%></a></li>
                    <% } %>
                    <%if (numberpage < totalPages) { %>
                    <li class="page-item"><a class="page-link bg-secondary text-white" href="${pageContext.request.contextPath}/codexconstruction/client/devis?page=<%=numberpage+1%>">Next</a></li>
                    <% } %>
                </ul>
            </nav>
        </div>
    </div>

</main>

<%@ include file="template/footer.jsp" %>