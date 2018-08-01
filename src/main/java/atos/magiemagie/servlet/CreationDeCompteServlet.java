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
@WebServlet(name = "CreationDeCompte", urlPatterns = {"/CreationDeCompte"})
public class CreationDeCompteServlet extends AutowireServlet {

    private JoueurService service = new JoueurService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        //recperer  les donnees du joueur
        String pseudo = req.getParameter("pseudo");
        String avatar = req.getParameter("avatar");
        
        //ajouter le jouueur dans la base de donnee
        Joueur newJoueur = service.ajouterJoueur(pseudo, avatar);
        //recuperer ID du Joueur 
        Long joueurID= newJoueur.getId();
        //stocker id de joueur creer
        req.getSession().setAttribute("JoueurID", joueurID);
        //renvoi vers la liste des parties(aller a la liste des parties )
        req.getRequestDispatcher("lister-Partie.jsp").forward(req, resp);
    }
    
    
}
