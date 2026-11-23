<%-- session="false" : sinon chaque JSP cree une HttpSession (et fausse le compteur ActiveSessionListener) --%>
<%@ page contentType="text/html;charset=UTF-8" session="false" %>
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>expression.jsp</title><style>body{font-family:Segoe UI,Arial,sans-serif;max-width:820px;margin:24px auto;padding:0 16px;color:#1f2933}h1{color:#1d4ed8;border-bottom:2px solid #dbe4ff;padding-bottom:6px}table{border-collapse:collapse;margin:12px 0}th,td{border:1px solid #cbd5e1;padding:6px 12px;text-align:left}th{background:#eef2ff}.big{font-size:1.3em}</style></head>
<body>
<h1>2.1.1 JSP expression</h1>
<p class="big">Nombre aleatoire : <strong><%= Math.random() %></strong></p>
<p>Rechargez la page : la valeur change a chaque requete.</p>
<p><a href="index.html">&larr; Accueil</a></p>
</body>
</html>
