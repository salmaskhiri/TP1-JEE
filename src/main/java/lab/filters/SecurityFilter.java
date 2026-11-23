package lab.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 1.4.1.5 : protege /ListItems. Declare et mappe dans web.xml (1.4.1.6).
 * Si l'utilisateur n'est pas connecte, on redirige vers login.html SANS appeler chain.doFilter() :
 * la servlet ListItems n'est alors jamais executee.
 */
public class SecurityFilter implements Filter {

    private static final String P = "[SecurityFilter]";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false); // false : ne cree PAS de session
        boolean connected = session != null && session.getAttribute("user") != null;

        if (connected) {
            System.out.println(P + " AVANT chain.doFilter() : acces autorise pour " + session.getAttribute("user"));
            chain.doFilter(request, response);
            System.out.println(P + " APRES chain.doFilter()");
        } else {
            System.out.println(P + " acces REFUSE (session=" + (session == null ? "aucune" : session.getId())
                    + ") -> redirection vers login.html, chain.doFilter() n'est pas appele");
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
    }
}
