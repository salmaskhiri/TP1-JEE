package lab.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1.4.2.5 : ajoute / remplace / supprime des attributs dans la portee application (ServletContext)
 * et dans la portee requete (ServletRequest) pour declencher
 * ApplicationAttributeListener et RequestAttributeListener.
 */
@WebServlet("/AttributeTest")
public class AttributeTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("---- AttributeTest : debut des manipulations ----");
        ServletContext ctx = getServletContext();

        // Portee application : partagee par tous les utilisateurs, vit jusqu'a l'arret de l'application
        ctx.setAttribute("testAppAttr", "v1");      // attributeAdded
        ctx.setAttribute("testAppAttr", "v2");      // attributeReplaced
        ctx.removeAttribute("testAppAttr");         // attributeRemoved

        // Portee requete : vit seulement pendant le traitement de cette requete
        request.setAttribute("testReqAttr", "v1");  // attributeAdded
        request.setAttribute("testReqAttr", "v2");  // attributeReplaced
        request.removeAttribute("testReqAttr");     // attributeRemoved

        // Compteur global en portee application (montre qu'elle est partagee entre les clients)
        Integer hits;
        synchronized (ctx) {
            hits = (Integer) ctx.getAttribute("attributeTestHits");
            hits = (hits == null) ? 1 : hits + 1;
            ctx.setAttribute("attributeTestHits", hits);
        }
        System.out.println("---- AttributeTest : fin des manipulations ----");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Test des attributs application / requete"));
        out.println("<p>Les attributs <code>testAppAttr</code> (application) et <code>testReqAttr</code> (requete) "
                + "ont ete ajoutes, remplaces puis supprimes. Les evenements sont dans la console Tomcat.</p>");
        out.println("<p>Compteur global (portee application, tous navigateurs confondus) : <strong>" + hits + "</strong></p>");
        out.println(Html.footer());
    }
}
