<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.text.NumberFormat" %>
<%@ include file="template/header.jsp" %>
<%
    int annee = Integer.parseInt(request.getAttribute("year").toString());
    double sommeDevis = Double.parseDouble(request.getAttribute("sommeDevis").toString());
    double sommePaimentDevis = Double.parseDouble(request.getAttribute("sommePaimentDevis").toString());
    NumberFormat formatMonetaire = NumberFormat.getCurrencyInstance();
%>

<main role="main" class="main-content">
    <div class="container">
        <div class="col-md-6 offset-md-3">
            <form method="get" action="${pageContext.request.contextPath}/codexconstruction/admin/dashboard">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">Annee</span>
                    </div>
                    <input type="number" class="form-control" placeholder="Saisissez une annee" name="year" value="<%=annee%>" aria-label="Year" aria-describedby="basic-addon1">
                </div>
                <div class="text-center m-2">
                    <button class="btn btn-outline-info col-md-3">Voir</button>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="col-md-3">
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <p class="mb-0">
                            <strong class="mb-0 text-uppercase text-muted">Devis</strong>
                        </p>
                        <h3><%=formatMonetaire.format(sommeDevis)%></h3>
                        <p class="text-muted">Somme des devis demander cumule</p>
                    </div>
                </div>
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <p class="mb-0">
                            <strong class="mb-0 text-uppercase text-muted">Paiement</strong>
                        </p>
                        <h3><%=formatMonetaire.format(sommePaimentDevis)%></h3>
                        <p class="text-muted">Somme des Paiement Effectuer</p>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <canvas id="myChart"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%
    List<String> monthsAndYears = Arrays.asList("Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre");
    List<Double> amounts = Arrays.asList(1000.0, 1500.0, 2000.0, 1800.0, 1200.0, 1000.0, 1600.0, 1700.0, 1300.0, 900.0, 800.0, 950.0);
%>

<script>
    // Récupérer les données du serveur (JSP)
    var monthsAndYears = <%= new Gson().toJson(monthsAndYears) %>;
    var amounts = <%= new Gson().toJson(amounts) %>;

    // Extraire les mois et années pour l'affichage sur l'axe X
    var labels = monthsAndYears.map(function(dateString) {
        return dateString.split(" ")[0]; // Extraire le mois
    });

    // Dessiner l'histogramme
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Montant des devis',
                data: ${dataChart},
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>

<%@ include file="template/footer.jsp" %>


