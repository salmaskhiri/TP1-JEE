package lab.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

/** 1.4.2.4 : evenements sur les attributs de TOUTES les sessions. */
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent e) {
        System.out.println("[SessionAttributeListener] attributeAdded()    " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent e) {
        // e.getValue() = ANCIENNE valeur ; la nouvelle est dans la session
        System.out.println("[SessionAttributeListener] attributeReplaced() " + e.getName() + " : " + e.getValue()
                + " -> " + e.getSession().getAttribute(e.getName()));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent e) {
        System.out.println("[SessionAttributeListener] attributeRemoved()  " + e.getName() + " (valeur = " + e.getValue() + ")");
    }
}
