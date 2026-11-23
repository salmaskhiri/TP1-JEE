package lab.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/** Invalide la session courante (tests de SecurityFilter et d'ActiveSessionListener). */
@WebServlet("/Logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // ne pas en creer une juste pour la detruire
        boolean existed = session != null;
        if (existed) {
            session.invalidate(); // -> sessionDestroyed() + valueUnbound() + attributeRemoved()
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Session invalidee"));
        out.println(existed ? "<p>La session a ete invalidee avec <code>session.invalidate()</code>.</p>"
                : "<p>Aucune session n'existait.</p>");
        out.println("<p>Sessions actives maintenant : <strong>"
                + getServletContext().getAttribute("activeSessions") + "</strong></p>");
        out.println("<p><a href=\"ListItems\">Essayer d'acceder a ListItems</a> | "
                + "<a href=\"ShowSession\">ShowSession</a> | <a href=\"login.html\">Se connecter</a></p>");
        out.println(Html.footer());
    }
}
