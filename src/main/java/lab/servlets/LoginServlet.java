package lab.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lab.listeners.SessionUser;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 1.4.1.4 : recupere le nom d'utilisateur, le stocke dans l'attribut de session "user"
 * et affiche un lien vers items.html.
 * 1.4.3.1 (Extra) : stocke aussi un objet SessionUser (HttpSessionBindingListener).
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        if (username == null || username.isBlank()) {
            response.sendRedirect("login.html");
            return;
        }
        username = username.trim();

        HttpSession session = request.getSession(); // cree ou recupere la session
        // 1er login -> attributeAdded ; login avec un autre nom -> attributeReplaced
        session.setAttribute("user", username);
        session.setAttribute("sessionUser", new SessionUser(username)); // -> valueBound()

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Connexion reussie"));
        out.println("<p class=\"big\">Bienvenue <strong>" + Html.esc(username) + "</strong>, vous etes connecte.</p>");
        out.println("<p><a href=\"items.html\">Aller a la page des items</a></p>");
        out.println("<h2>Tester les evenements sur les attributs de session</h2><ul>"
                + "<li><a href=\"SessionAttributes?action=replace\">Modifier l'attribut <code>user</code></a> (attributeReplaced)</li>"
                + "<li><a href=\"SessionAttributes?action=remove\">Supprimer l'attribut <code>user</code></a> (attributeRemoved)</li>"
                + "<li><a href=\"SessionAttributes?action=removeSessionUser\">Supprimer l'objet <code>SessionUser</code></a> (valueUnbound)</li>"
                + "<li><a href=\"Logout\">Invalider la session</a></li></ul>");
        out.println(Html.footer());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.html");
    }
}
