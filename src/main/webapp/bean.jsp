<%@ page contentType="text/html;charset=UTF-8" session="false" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%-- Cree (ou recupere) un bean Person dans la portee requete --%>
<jsp:useBean id="person" class="isep.lab1.bean.Person" scope="request" />
<%-- property="*" : appelle setXxx() pour chaque parametre de la requete qui porte le nom d'une propriete --%>
<jsp:setProperty name="person" property="*" />
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>bean.jsp</title><style>body{font-family:Segoe UI,Arial,sans-serif;max-width:820px;margin:24px auto;padding:0 16px;color:#1f2933}h1{color:#1d4ed8;border-bottom:2px solid #dbe4ff;padding-bottom:6px}table{border-collapse:collapse;margin:12px 0}th,td{border:1px solid #cbd5e1;padding:6px 12px;text-align:left}th{background:#eef2ff}.big{font-size:1.3em}</style></head>
<body>
<h1>2.2 Donnees lues depuis le JavaBean Person</h1>
<table>
  <tr><th>Propriete</th><th>Valeur (jsp:getProperty)</th></tr>
  <tr><td>firstName</td><td><jsp:getProperty name="person" property="firstName" /></td></tr>
  <tr><td>lastName</td><td><jsp:getProperty name="person" property="lastName" /></td></tr>
  <tr><td>birthDay</td><td><jsp:getProperty name="person" property="birthDay" /></td></tr>
  <tr><td>sex</td><td><jsp:getProperty name="person" property="sex" /></td></tr>
</table>
<p><a href="form-jsp.html">Nouveau formulaire</a> | <a href="index.html">&larr; Accueil</a></p>
</body>
</html>
