package lab.listeners;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 1.4.2.3 : debut / fin de chaque requete.
 * 1.4.2.6 : ce listener n'a PAS d'annotation @WebListener, il est declare avec <listener> dans web.xml.
 */
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        System.out.println("[RequestListener] requestInitialized() : " + req.getMethod() + " " + req.getRequestURI());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
        System.out.println("[RequestListener] requestDestroyed()   : " + req.getMethod() + " " + req.getRequestURI());
    }
}
