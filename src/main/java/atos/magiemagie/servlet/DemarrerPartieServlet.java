/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servlet;

import atos.magiemagie.entity.Partie_;
import atos.magiemagie.servicenew.JoueurService;
import atos.magiemagie.servicenew.PartieService;
import atos.magiemagie.spring.AutowireServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "DemarrerPartieServlet", urlPatterns = {"/demarrer-partie"})
public class DemarrerPartieServlet extends AutowireServlet {

//    @Autowired
//    private PartieService service;
    private JoueurService service = new JoueurService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long idPartie = (Long) req.getSession().getAttribute("idPartie");
        
        service.demarerPartie(idPartie);
        
        req.getRequestDispatcher("Partie.jsp").forward(req, resp);

    }
}
