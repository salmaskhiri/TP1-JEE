package lab.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 * 1.3.1 : "Welcome on my site" pour un nouveau visiteur, "Welcome back" ensuite,
 * et compteur de visites stocke dans la session.
 * Extra : demande le nom complet + date de naissance a la premiere visite, puis affiche
 * le nombre de jours avant l'anniversaire.
 * 1.4.2.2 : affiche le nombre de sessions actives (attribut d'application "activeSessions").
 */
@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(); // cree la session si elle n'existe pas

        Integer count = (Integer) session.getAttribute("visitCount");
        count = (count == null) ? 1 : count + 1;
        session.setAttribute("visitCount", count);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("ShowSession"));

        if (session.isNew()) {
            out.println("<h2>Welcome on my site</h2>");
        } else {
            out.println("<h2>Welcome back</h2>");
        }

        String fullName = (String) session.getAttribute("fullName");
        LocalDate birth = (LocalDate) session.getAttribute("birthDate");
        if (fullName == null || birth == null) {
            // Extra : premiere visite -> on demande le nom et la date de naissance
            out.println("<form method=\"post\" action=\"ShowSession\">"
                    + "<label>Nom complet : <input type=\"text\" name=\"fullName\" required></label>"
                    + "<label>Date de naissance (mm/dd/yyyy) : <input type=\"text\" name=\"birthDate\" "
                    + "placeholder=\"mm/dd/yyyy\" required></label>"
                    + "<button type=\"submit\">Enregistrer</button></form>");
        } else {
            long days = DateUtil.daysToNextBirthday(birth, LocalDate.now());
            out.println("<p class=\"big\">Hi, " + Html.esc(fullName) + ". There are " + days
                    + " days to your birthday.</p>");
        }

        out.println("<table>");
        out.println("<tr><td>Nombre de visites (cette session)</td><td>" + count + "</td></tr>");
        out.println("<tr><td>ID de session</td><td><code>" + session.getId() + "</code></td></tr>");
        out.println("<tr><td>Sessions actives (ActiveSessionListener)</td><td><strong>"
                + getServletContext().getAttribute("activeSessions") + "</strong></td></tr>");
        out.println("<tr><td>Nom de l'application (ApplicationListener)</td><td>"
                + Html.esc(getServletContext().getAttribute("appName")) + "</td></tr>");
        out.println("</table>");
        out.println("<p><a href=\"ShowSession\">Recharger</a> | <a href=\"Logout\">Invalider la session</a></p>");
        out.println(Html.footer());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        LocalDate birth = DateUtil.parse(request.getParameter("birthDate"));
        String name = request.getParameter("fullName");
        if (birth != null && name != null && !name.isBlank()) {
            session.setAttribute("fullName", name.trim());
            session.setAttribute("birthDate", birth);
        }
        // Post/Redirect/Get : evite de re-soumettre le formulaire avec F5
        response.sendRedirect("ShowSession");
    }
}
