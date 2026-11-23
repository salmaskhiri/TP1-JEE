package lab.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/** 1.4.2.4 / 1.4.3.1 : modifie ou supprime les attributs de session pour observer les listeners. */
@WebServlet("/SessionAttributes")
public class SessionAttributes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = String.valueOf(request.getParameter("action"));
        HttpSession session = request.getSession(false);
        String message;

        if (session == null) {
            message = "Pas de session : connectez-vous d'abord.";
        } else {
            switch (action) {
                case "replace" -> {
                    Object old = session.getAttribute("user");
                    session.setAttribute("user", old + "_modifie");
                    message = "Attribut user remplace : " + old + " -> " + session.getAttribute("user");
                }
                case "remove" -> {
                    session.removeAttribute("user");
                    message = "Attribut user supprime.";
                }
                case "removeSessionUser" -> {
                    session.removeAttribute("sessionUser");
                    message = "Objet SessionUser retire de la session.";
                }
                default -> message = "Action inconnue.";
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Attributs de session"));
        out.println("<p>" + Html.esc(message) + "</p>");
        out.println("<p>Regardez la console Tomcat pour voir les evenements des listeners.</p>");
        out.println("<p><a href=\"login.html\">Se reconnecter</a> | <a href=\"ListItems\">ListItems</a></p>");
        out.println(Html.footer());
    }
}
