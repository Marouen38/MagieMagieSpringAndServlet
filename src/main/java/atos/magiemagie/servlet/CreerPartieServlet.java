/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.entity.Partie;
import atos.magiemagie.servicenew.PartieService;
import atos.magiemagie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "CreerPartie", urlPatterns = {"/creer-partie"})
public class CreerPartieServlet extends AutowireServlet {

    private PartieService service = new PartieService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("creer-partie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        //recuperer le nom de la partie
        String partieNom = req.getParameter("partieName");
         
        //creer la partie
        Partie nouvellePartie = service.creerNouvellePartie(partieNom);
        
        //stocker l'ID de la partie en session 
        req.getSession().setAttribute("idPartie", nouvellePartie.getId());
        
        //renvoie la vue rejoindre partie
        resp.sendRedirect("rejoindre-partie");
        
    
    }
}
