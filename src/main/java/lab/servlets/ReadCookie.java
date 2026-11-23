package lab.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/** 1.2.3 : lit les quatre cookies crees par FormServlet et affiche leur valeur. */
@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {

    private static final String[] NAMES = {"firstName", "lastName", "birthDay", "sex"};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String> values = new LinkedHashMap<>();
        for (String n : NAMES) {
            values.put(n, null);
        }
        Cookie[] cookies = request.getCookies(); // null si le navigateur n'envoie aucun cookie
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (values.containsKey(c.getName())) {
                    values.put(c.getName(), URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8));
                }
            }
        }

        PrintWriter out = response.getWriter();
        out.println(Html.header("ReadCookie - cookies du navigateur"));
        out.println("<table><tr><th>Cookie</th><th>Valeur</th></tr>");
        for (Map.Entry<String, String> e : values.entrySet()) {
            out.println("<tr><td>" + e.getKey() + "</td><td>" + Html.esc(e.getValue()) + "</td></tr>");
        }
        out.println("</table>");
        out.println(Html.footer());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
