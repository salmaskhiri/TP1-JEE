package lab.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/** 1.2.4 (Extra) : compte le nombre de visites avec un cookie "visits". */
@WebServlet("/VisitCounter")
public class VisitCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int visits = 0;
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("visits".equals(c.getName())) {
                    try {
                        visits = Integer.parseInt(c.getValue());
                    } catch (NumberFormatException ignored) {
                        visits = 0; // cookie modifie a la main par l'utilisateur
                    }
                }
            }
        }
        visits++;

        Cookie cookie = new Cookie("visits", String.valueOf(visits));
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("Compteur de visites (cookie)"));
        out.println("<p class=\"big\">Vous avez visite cette page <strong>" + visits + "</strong> fois.</p>");
        out.println("<p>Rechargez la page (F5) pour incrementer le compteur.</p>");
        out.println(Html.footer());
    }
}
