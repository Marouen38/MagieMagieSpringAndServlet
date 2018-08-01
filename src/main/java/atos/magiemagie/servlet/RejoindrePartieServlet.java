/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.entity.Joueur;
import atos.magiemagie.servicenew.JoueurService;
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
@WebServlet(name = "RejoindrePartie", urlPatterns = {"/rejoindre-partie"})
public class RejoindrePartieServlet extends AutowireServlet {

    private JoueurService service = new JoueurService();
    
    Long idPartie;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        req.getSession().getAttribute("partie_id");
        
        //idPartie = Long.parseLong(req.getParameter("partieId"));
        

        req.getRequestDispatcher("inscription.jsp").forward(req, resp);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // idPartie = Long.parseLong(req.getParameter("partie_id"));
       
       //recuper ou ajoute le joueur dans la partie
        
        String pseudo = req.getParameter("pseudo");
        
        String avatar = req.getParameter("radio");
        
        service.rejoindrePartie(pseudo, avatar, idPartie);
        
        req.getSession().setAttribute("idPartie", idPartie);
        
        //redirection vers la servlet de demmarege de partie
        
        resp.sendRedirect("rejoindre-partie");
       
    }
    
}
