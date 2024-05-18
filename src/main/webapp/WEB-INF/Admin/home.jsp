<%@ page import="codex.evaluation.model.UserAdmin" %><%
    UserAdmin utilisateurs = (UserAdmin) request.getAttribute("user");
%>
<%@ include file="template/header.jsp" %>

<main role="main" class="main-content">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="content" >
                <h2 class="mt-5 page-title">Bonjour Admin <%=utilisateurs.getUsername()%></h2>
            </div>
        </div>
    </div>
</main>

<%@ include file="template/footer.jsp" %>


