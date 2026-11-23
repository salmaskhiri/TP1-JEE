package lab.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.4.2.2 : compte les sessions actives. AtomicInteger car plusieurs threads de Tomcat
 * (une requete = un thread) peuvent creer/detruire des sessions en meme temps.
 */
@WebListener
public class ActiveSessionListener implements HttpSessionListener {

    private final AtomicInteger active = new AtomicInteger();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int n = active.incrementAndGet();
        se.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[ActiveSessionListener] sessionCreated()   id=" + se.getSession().getId()
                + " -> sessions actives = " + n);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        int n = active.decrementAndGet();
        se.getSession().getServletContext().setAttribute("activeSessions", n);
        System.out.println("[ActiveSessionListener] sessionDestroyed() id=" + se.getSession().getId()
                + " -> sessions actives = " + n);
    }
}
