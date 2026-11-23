# TP1 – Servlets, JSP, MVC, Filtres et Listeners (Jakarta EE / Tomcat 10.1)

MP2-GL – ISIMM – 2026/2027 – Salma Skhiri

## Contenu
| Partie | Fichiers |
|---|---|
| 1.1 POST + 2 URL | `form.html`, `lab.servlets.FormServlet` (déclarée dans `web.xml` : `/FormServlet` et `/Form_Servlet`) |
| 1.2 Cookies | `FormServlet`, `ReadCookie`, `VisitCounter` (extra) |
| 1.3 Sessions | `ShowSession` (+ extra anniversaire), `items.html`, `ListItems` (+ extra compteur) |
| 2 JSP / JavaBean | `expression.jsp`, `scriptlet.jsp`, `declaration.jsp`, `form-jsp.html`, `bean.jsp`, `isep.lab1.bean.Person` |
| 3 MVC | `index.html`, `lab.mvc.controller.Controller`, `lab.mvc.model.Person`, `old.jsp`, `young.jsp` |
| 1.4.1 Filtres | `lab.filters.LoggingFilter` (web.xml + `logPrefix` + temps en ms), `SecurityFilter`, `login.html`, `LoginServlet`, `Logout` |
| 1.4.2 Listeners | `lab.listeners.*` : `ApplicationListener`, `ActiveSessionListener`, `RequestListener` (déclaré dans web.xml), `SessionAttributeListener`, `ApplicationAttributeListener`, `RequestAttributeListener`, servlets `SessionAttributes` et `AttributeTest` |
| 1.4.3 Extra | `SessionUser` (HttpSessionBindingListener) |

## Environnement
- JDK 17 (Microsoft OpenJDK 17.0.20.1)
- Apache Tomcat 10.1.62 → API `jakarta.servlet` (Servlet 6.0)
- IntelliJ IDEA Ultimate 2026.2 (import via `pom.xml`)

## Compiler et déployer à la main
```powershell
$env:JAVA_HOME = "C:\chemin\vers\jdk-17"
$env:CATALINA_HOME = "C:\tomcat\apache-tomcat-10.1.62"
.\build.ps1                     # javac + copie dans %CATALINA_HOME%\webapps\TP1
& "$env:CATALINA_HOME\bin\catalina.bat" run
```
Puis ouvrir http://localhost:8080/TP1/

Avec Maven : `mvn package` produit `target/TP1.war`.

## Rapport
`docs/rapport/Rapport_TP1.pdf` (source HTML à côté), captures dans `docs/screenshots/`.
