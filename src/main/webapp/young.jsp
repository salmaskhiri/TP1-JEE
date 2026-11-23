<%@ page contentType="text/html;charset=UTF-8" session="false" %>
<%-- type= (et non class=) : la JSP ne cree pas l'objet, elle utilise celui mis dans la requete par le Controller.
     Si l'attribut "person" est absent, une exception est levee au lieu de creer un bean vide. --%>
<jsp:useBean id="person" type="lab.mvc.model.Person" scope="request" />
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>young.jsp</title><style>body{font-family:Segoe UI,Arial,sans-serif;max-width:820px;margin:24px auto;padding:0 16px;color:#1f2933}h1{color:#1d4ed8;border-bottom:2px solid #dbe4ff;padding-bottom:6px}table{border-collapse:collapse;margin:12px 0}th,td{border:1px solid #cbd5e1;padding:6px 12px;text-align:left}th{background:#eef2ff}.big{font-size:1.3em}</style><style>h1{color:#047857}</style></head>
<body>
<h1>young.jsp - Page des personnes nees en 1990 ou apres</h1>
<table>
  <tr><th>First name</th><td><jsp:getProperty name="person" property="firstName" /></td></tr>
  <tr><th>Last name</th><td><jsp:getProperty name="person" property="lastName" /></td></tr>
  <tr><th>Birth date</th><td><jsp:getProperty name="person" property="birthDay" /></td></tr>
  <tr><th>Sex</th><td><jsp:getProperty name="person" property="sex" /></td></tr>
</table>
<p><a href="index.html">&larr; Accueil</a></p>
</body>
</html>
