package lab.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 1.1 + 1.2 : recoit le formulaire (POST), affiche les donnees dans un tableau HTML
 * et cree un cookie pour chaque champ.
 *
 * Pas d'annotation @WebServlet : la servlet est declaree a la main dans web.xml
 * avec deux url-pattern (/FormServlet et /Form_Servlet).
 */
public class FormServlet extends HttpServlet {

    private static final String[] FIELDS = {"firstName", "lastName", "birthDay", "sex"};

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1.2 : un cookie par champ (valeur encodee : un cookie ne peut pas contenir d'espace, de / ...)
        for (String field : FIELDS) {
            String value = request.getParameter(field);
            if (value != null && !value.isBlank()) {
                Cookie cookie = new Cookie(field, URLEncoder.encode(value, StandardCharsets.UTF_8));
                cookie.setMaxAge(60 * 60 * 24); // 1 jour
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
        }

        PrintWriter out = response.getWriter();
        out.println(Html.header("FormServlet - donnees recues"));
        out.println("<p>URL appelee : <code>" + Html.esc(request.getRequestURI()) + "</code></p>");
        out.println("<table>");
        out.println("<tr><th>Champ</th><th>Valeur</th></tr>");
        out.println("<tr><td>Prenom</td><td>" + Html.esc(request.getParameter("firstName")) + "</td></tr>");
        out.println("<tr><td>Nom</td><td>" + Html.esc(request.getParameter("lastName")) + "</td></tr>");
        out.println("<tr><td>Date de naissance</td><td>" + Html.esc(request.getParameter("birthDay")) + "</td></tr>");
        out.println("<tr><td>Sexe</td><td>" + Html.esc(request.getParameter("sex")) + "</td></tr>");
        out.println("</table>");
        out.println("<p>4 cookies ont ete envoyes au navigateur. <a href=\"ReadCookie\">Lire les cookies</a></p>");
        out.println(Html.footer());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Acces direct par l'URL : on renvoie vers le formulaire
        response.sendRedirect("form.html");
    }
}
