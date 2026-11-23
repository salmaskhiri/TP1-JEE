package lab.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/** 1.4.2.1 : cycle de vie de l'application. */
@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[ApplicationListener] contextInitialized() : application "
                + sce.getServletContext().getContextPath() + " demarree");
        sce.getServletContext().setAttribute("appName", "MP2 Web Application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[ApplicationListener] contextDestroyed() : application arretee");
    }
}
