<%@ page import="java.util.List" %>
<%@ page import="codex.evaluation.model.TypeMaison" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="codex.evaluation.model.Finition" %>
<%
    List<String> bg = new ArrayList<>();
    bg.add("l-bg-cherry");
    bg.add("l-bg-blue-dark");
    bg.add("l-bg-green-dark");
    bg.add("l-bg-orange-dark");
%>
<%@ include file="template/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/StyleFinition.css">
<style>
    /**
  Component
**/

    label {
        width: 100%;
    }

    .card-input-element {
        display: none;
    }

    .card-input {
        margin: 10px;
        padding: 0px;
    }

    .card-input:hover {
        cursor: pointer;
        box-shadow: 0 0 5px 5px #2ecc71;
    }

    .card-input-element:checked + .card-input {
        box-shadow: 0 0 5px 5px #2ecc71;
    }
</style>
<%
    List<TypeMaison> typeMaisons = (List<TypeMaison>) request.getAttribute("typeMaisons");
    List<Finition> finitionList = (List<Finition>) request.getAttribute("finitionList");
%>

        <!-- Page Title -->
        <div class="page-title text-center p-4 text-white" data-aos="fade">
            <div class="container position-relative">
                <h1 class="p-4 text-white">DEVIS</h1>
            </div>
        </div><!-- End Page Title -->
    </header>

<div class="container">
    <form action="${pageContext.request.contextPath}/codexconstruction/client/insertdevis" method="post" id="devis-form" data-parsley-validate="">
        <div class="p-4 ml-4">
            <label for="lieu" class="form-label">Lieu <span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="lieu" id="lieu" data-parsley-trigger="change" required="">
        </div>

    <h1 class="m-4 pr-2">Type de Maison</h1>
    <div class="row m-4 justify-content-around">
        <% for (TypeMaison typeMaison: typeMaisons) { %>
            <div class="col-md-5 col-lg-5 col-sm-5">
                <label>
                    <input type="radio" name="typeMaison" class="card-input-element" value="<%=typeMaison.getId()%>" />
                    <div class="card card-default card-input">
                        <div class="card-header text-center">
                            <strong><%=typeMaison.getNom()%></strong>
                        </div>
                        <div class="card-body">

                            <div class="mt-4 text-center">
                                <%= typeMaison.getDescription().replace("\n", "<br>") %>
                            </div>

                            <div class="mt-4">
                                <div class="text-center">
                                    <h3><%=typeMaison.getDuree()%> jours</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </label>
            </div>
        <% } %>
    </div>



    <div class="container">
        <h1 class="">Type de Finition</h1>
        <div class="row justify-content-around">
            <%
                int i = 0;
                for (Finition finition: finitionList) { %>
                <div class="col-xl-5 col-lg-5">
                    <label>
                        <input type="radio" name="typeFinition" class="card-input-element" value="<%=finition.getId()%>"/>
                        <div class="card card-default card-input">
                            <div class="card <%=bg.get(i)%>">
                                <div class="card-statistic-3 p-4">
                                    <div class="card-icon card-icon-large"><i class="fas fa-shopping-cart"></i></div>

                                    <div class="text-center mb-2">
                                        <h2 class="text-light mb-0">
                                            <%=finition.getNom()%>
                                        </h2>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </label>
                </div>
            <% i++;} %>
        </div>
    </div>

    <div class="p-4 ml-4">
        <label for="datedebut" class="form-label">Date Debut de Travail <span class="text-danger">*</span></label>
        <input type="date" class="form-control" name="datedebut" id="datedebut" data-parsley-trigger="change" required="">
    </div>

    <div class="text-center mb-4">
        <button class="btn btn-success btn-lg" style="font-size: 24px; padding: 20px 40px;">Creer Devis</button>
    </div>
    </form>
</div>



</main>

<%@ include file="template/footer.jsp" %>