package lab.listeners;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

/** 1.4.2.5 : evenements sur les attributs de la portee application (ServletContext). */
@WebListener
public class ApplicationAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent e) {
        System.out.println("[ApplicationAttributeListener] attributeAdded()    " + e.getName() + " = " + e.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent e) {
        System.out.println("[ApplicationAttributeListener] attributeReplaced() " + e.getName() + " (ancienne valeur = "
                + e.getValue() + ", nouvelle = " + e.getServletContext().getAttribute(e.getName()) + ")");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent e) {
        System.out.println("[ApplicationAttributeListener] attributeRemoved()  " + e.getName());
    }
}
