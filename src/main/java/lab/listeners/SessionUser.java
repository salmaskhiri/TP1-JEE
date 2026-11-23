package lab.listeners;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import java.io.Serializable;

/**
 * 1.4.3.1 (Extra) : objet qui est lui-meme prevenu quand il est ajoute / retire d'une session.
 * Pas besoin de @WebListener ni de web.xml : le conteneur appelle valueBound/valueUnbound
 * directement sur l'objet stocke.
 */
public class SessionUser implements HttpSessionBindingListener, Serializable {

    private static final long serialVersionUID = 1L;

    private final String username;

    public SessionUser(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("[SessionUser] valueBound()   : " + username + " ajoute a la session "
                + event.getSession().getId() + " sous le nom '" + event.getName() + "'");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("[SessionUser] valueUnbound() : " + username + " retire de la session "
                + event.getSession().getId());
    }

    @Override
    public String toString() {
        return "SessionUser(" + username + ")";
    }
}
