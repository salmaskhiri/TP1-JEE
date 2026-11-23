<%@ page contentType="text/html;charset=UTF-8" session="false" %>
<!DOCTYPE html>
<html lang="fr">
<head><meta charset="UTF-8"><title>declaration.jsp</title><style>body{font-family:Segoe UI,Arial,sans-serif;max-width:820px;margin:24px auto;padding:0 16px;color:#1f2933}h1{color:#1d4ed8;border-bottom:2px solid #dbe4ff;padding-bottom:6px}table{border-collapse:collapse;margin:12px 0}th,td{border:1px solid #cbd5e1;padding:6px 12px;text-align:left}th{background:#eef2ff}.big{font-size:1.3em}</style></head>
<body>
<%! private int numEntries = 10;
    private int randomInt(int range) {
    return(1 + ((int)(Math.random() * range)));
    } %>

<h1>A random list from 1 to 100:</h1>
<ul>
<% for(int i=0; i<numEntries; i++) {
out.println("<li>" + randomInt(100));
} %>
</ul>
<p><a href="index.html">&larr; Accueil</a></p>
</body>
</html>
