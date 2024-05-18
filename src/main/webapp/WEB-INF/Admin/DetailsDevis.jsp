<%@ page import="codex.evaluation.model.DetailDevis" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.sql.Date" %>
<%@ include file="template/header.jsp" %>
<%
    List<DetailDevis> listDetails = (List<DetailDevis>) request.getAttribute("listDetails");
    NumberFormat formatMonetaire = NumberFormat.getCurrencyInstance();
    double subTotal = 0;
    double pourcentage = 0;
    String numero = "";
    Date date = null;
    int numeroDevis = 0;
    if(listDetails.size()>0) {
        pourcentage = listDetails.get(0).getClientDevis().getFinition_pourcentage();
        numero = listDetails.get(0).getClientDevis().getUserClient().getNumero();
        date = listDetails.get(0).getClientDevis().getDatedevis();
        numeroDevis = listDetails.get(0).getClientDevis().getId();
    }
%>
<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <div class="row align-items-center mb-4">
                    <div class="col">
                        <h2 class="h5 page-title"><small class="text-muted text-uppercase">DEVIS</small><br />#<%=numeroDevis%></h2>
                    </div>
                    <div class="col-auto">
                        <button type="button" class="btn btn-secondary">Print</button>
                    </div>
                </div>
                <div class="card shadow">
                    <div class="card-body p-5">
                        <div class="row mb-5">
                            <div class="col-12 text-center mb-4">
                                <img width="200px" src="${pageContext.request.contextPath}/assets/img/logo.png" alt="...">
                                <h2 class="mb-0 text-uppercase">DEVIS</h2>
                            </div>
                            <div class="col-md-10">
                                <p class="small text-muted text-uppercase mb-2">Invoice from</p>
                                <p class="mb-4">
                                    <strong>Leo Randriantahina</strong><br />CEO<br />CodexConstruction<br /> Andoharanofotsy<br />034 72 562 74
                                </p>
                            </div>
                            <div class="col-md-2">
                                <p class="small text-muted text-uppercase mb-2">Invoice to</p>
                                <p class="mb-4">
                                    <strong><%=numero%></strong>
                                </p>
                                <p>
                                    <small class="small text-muted text-uppercase">Due date</small><br />
                                    <strong><%=date%></strong>
                                </p>
                            </div>
                        </div> <!-- /.row -->
                        <table class="table table-borderless table-striped">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Designation</th>
                                <th scope="col">Unite</th>
                                <th scope="col" class="text-right">Qte</th>
                                <th scope="col" class="text-right">PU</th>
                                <th scope="col" class="text-right">TOTAL</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for(DetailDevis detailDevis: listDetails) { %>
                            <tr>
                                <th scope="row"><%=detailDevis.getId()%></th>
                                <td><%=detailDevis.getTravauxNom()%></td>
                                <td><%=detailDevis.getUnite()%></td>
                                <td class="text-right"><%=detailDevis.getQte()%></td>
                                <td class="text-right"><%=formatMonetaire.format(detailDevis.getPu())%></td>
                                <td class="text-right"><%=formatMonetaire.format(detailDevis.getPu()*detailDevis.getQte())%></td>
                            </tr>
                            <% subTotal+=detailDevis.getPu()*detailDevis.getQte(); } %>
                            </tbody>
                        </table>
                        <div class="row mt-5">
                            <div class="col-2 text-center">
                                <img src="${pageContext.request.contextPath}/assets/img/qrcode.svg" class="navbar-brand-img brand-sm mx-auto my-4" alt="...">
                            </div>
                            <div class="col-md-5">
                            </div>
                            <div class="col-md-5">
                                <div class="text-right mr-2">
                                    <p class="mb-2 h6">
                                        <span class="text-muted">Subtotal : </span>
                                        <strong><%=formatMonetaire.format(subTotal) %></strong>
                                    </p>
                                    <p class="mb-2 h6">
                                        <span class="text-muted">Pourcentage finition : </span>
                                        <strong><%=pourcentage%> %</strong>
                                    </p>
                                    <p class="mb-2 h6">
                                        <span class="text-muted">Total : </span>
                                        <span><%=formatMonetaire.format(((pourcentage/100)*subTotal)+subTotal)%></span>
                                    </p>
                                </div>
                            </div>
                        </div> <!-- /.row -->
                    </div> <!-- /.card-body -->
                </div> <!-- /.card -->
            </div>
        </div>
    </div>
</main>
<%@ include file="template/footer.jsp" %>