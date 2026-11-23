package lab.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.mvc.model.Person;
import lab.servlets.DateUtil;

import java.io.IOException;
import java.time.LocalDate;

/**
 * 3.3 : le Controleur. Lit le formulaire, cree le bean Person (Modele),
 * le place dans la requete et choisit la Vue (old.jsp / young.jsp).
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Person person = new Person(
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("birthDay"),
                request.getParameter("sex"));

        request.setAttribute("person", person); // portee "request"

        LocalDate birth = DateUtil.parse(person.getBirthDay());
        String view;
        if (birth == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Date de naissance invalide (format attendu : mm/dd/yyyy)");
            return;
        } else if (birth.getYear() < 1990) {
            view = "/old.jsp";
        } else {
            view = "/young.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        // L'enonce utilise include() ; forward() serait plus classique pour un controleur
        // (le controleur n'ecrit rien lui-meme dans la reponse).
        dispatcher.include(request, response);
    }
}
