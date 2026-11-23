package lab.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.3.2 : stocke les items saisis dans une ArrayList&lt;String&gt; en session et affiche la liste.
 * Extra : affiche aussi combien de fois chaque item a ete saisi.
 * Protegee par SecurityFilter (1.4.1.5).
 */
@WebServlet("/ListItems")
public class ListItems extends HttpServlet {

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        List<String> items;
        // synchronized : deux onglets du meme navigateur partagent la meme session
        synchronized (session) {
            items = (List<String>) session.getAttribute("items");
            if (items == null) {
                items = new ArrayList<>();
                session.setAttribute("items", items);
            }
            String newItem = request.getParameter("newItem");
            if (newItem != null && !newItem.isBlank()) {
                items.add(newItem.trim());
            }
        }

        // Extra : nombre d'occurrences de chaque item
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (String it : items) {
            counts.merge(it, 1, Integer::sum);
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(Html.header("ListItems"));
        out.println("<p>Utilisateur connecte : <strong>" + Html.esc(session.getAttribute("user")) + "</strong></p>");
        out.println("<h2>Items saisis (" + items.size() + ")</h2><ol>");
        for (String it : items) {
            out.println("<li>" + Html.esc(it) + "</li>");
        }
        out.println("</ol><h2>Nombre de saisies par item</h2>");
        out.println("<table><tr><th>Item</th><th>Saisi</th></tr>");
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            out.println("<tr><td>" + Html.esc(e.getKey()) + "</td><td>" + e.getValue() + " fois</td></tr>");
        }
        out.println("</table>");
        out.println("<p><a href=\"items.html\">Ajouter un autre item</a> | <a href=\"Logout\">Se deconnecter</a></p>");
        out.println(Html.footer());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response); // affiche la liste sans rien ajouter
    }
}
