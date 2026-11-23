package lab.listeners;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;

/**
 * 1.4.2.5 : evenements sur les attributs de la portee requete.
 * Remarque : Tomcat pose lui-meme des attributs internes (ex. org.apache.catalina.*, jakarta.servlet.*)
 * pendant un include/forward ; on les ignore pour garder une console lisible.
 */
@WebListener
public class RequestAttributeListener implements ServletRequestAttributeListener {

    private static boolean internal(String name) {
        return name.startsWith("org.apache.") || name.startsWith("jakarta.");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent e) {
        if (!internal(e.getName())) {
            System.out.println("[RequestAttributeListener] attributeAdded()    " + e.getName() + " = " + e.getValue());
        }
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent e) {
        if (!internal(e.getName())) {
            System.out.println("[RequestAttributeListener] attributeReplaced() " + e.getName() + " (ancienne valeur = "
                    + e.getValue() + ", nouvelle = " + e.getServletRequest().getAttribute(e.getName()) + ")");
        }
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent e) {
        if (!internal(e.getName())) {
            System.out.println("[RequestAttributeListener] attributeRemoved()  " + e.getName());
        }
    }
}
