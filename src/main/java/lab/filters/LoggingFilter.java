package lab.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * 1.4.1 : journalise chaque requete HTTP dans la console Tomcat.
 *
 * Etape 1 : configure avec l'annotation @WebFilter("/*").
 * Etape 3 : l'annotation a ete retiree et le filtre est maintenant declare dans web.xml
 *           (<filter> + <filter-mapping> + <init-param> logPrefix). On n'utilise qu'une seule
 *           methode d'enregistrement a la fois, sinon Tomcat creerait deux instances du filtre.
 * Extra   : mesure du temps d'execution avec System.nanoTime().
 */
// @WebFilter("/*")   <- version de l'etape 1
public class LoggingFilter implements Filter {

    private String prefix = "[LoggingFilter]";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String p = filterConfig.getInitParameter("logPrefix"); // null si configure par annotation
        if (p != null && !p.isBlank()) {
            prefix = p;
        }
        System.out.println(prefix + " init() : filtre initialise (logPrefix = " + p + ")");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getMethod() + " " + req.getRequestURL()
                + (req.getQueryString() != null ? "?" + req.getQueryString() : "");

        System.out.println(prefix + " AVANT chain.doFilter() -> " + url);
        long start = System.nanoTime();

        chain.doFilter(request, response); // filtre suivant, ou la servlet si c'est le dernier

        double ms = (System.nanoTime() - start) / 1_000_000.0;
        System.out.println(prefix + " APRES chain.doFilter() <- " + url
                + String.format(" (traitee en %.3f ms)", ms));
    }

    @Override
    public void destroy() {
        System.out.println(prefix + " destroy() : filtre detruit");
    }
}
