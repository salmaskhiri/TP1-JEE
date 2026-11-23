<%@ page contentType="text/html;charset=UTF-8" session="false" import="java.util.Date" %>
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>scriptlet.jsp</title><style>body{font-family:Segoe UI,Arial,sans-serif;max-width:820px;margin:24px auto;padding:0 16px;color:#1f2933}h1{color:#1d4ed8;border-bottom:2px solid #dbe4ff;padding-bottom:6px}table{border-collapse:collapse;margin:12px 0}th,td{border:1px solid #cbd5e1;padding:6px 12px;text-align:left}th{background:#eef2ff}.big{font-size:1.3em}</style></head>
<body>
<h1>2.1.2 JSP scriptlet</h1>
<%
    Date now = new Date();
    String remoteHost = request.getRemoteHost();
    out.println("<p>Heure actuelle : <strong>" + now + "</strong></p>");
    out.println("<p>request.getRemoteHost() : <strong>" + remoteHost + "</strong></p>");
%>
<p><small>Remarque : getRemoteHost() renvoie l'adresse du <em>client</em> (ici le meme poste, donc 127.0.0.1 / 0:0:0:0:0:0:0:1).
L'adresse du serveur s'obtient avec request.getLocalAddr() : <strong><%= request.getLocalAddr() %></strong>.</small></p>
<p><a href="index.html">&larr; Accueil</a></p>
</body>
</html>
