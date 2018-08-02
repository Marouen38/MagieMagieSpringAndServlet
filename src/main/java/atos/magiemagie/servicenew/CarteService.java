/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servicenew;

import atos.magiemagie.dao.CarteDAOCrud;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.dao.JoueurDAOCrud;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class CarteService {
    
    @Autowired
    private JoueurDAOCrud joueurDAOCrud;
    
    @Autowired
    private CarteDAOCrud dao;

    public List<Carte> listerCartesParJoueurId(long joueurId) {

        return dao.findAllByJoueurId(joueurId);

    }

    public void changerProprietaire(long idNouveauProprietaire, long idCarte) {
        //on récupère la carte avec l'id
        Carte c=dao.findOne(idCarte);

        //recupere le nouveau proprio par son  id 
        Joueur joueur = joueurDAOCrud.findOne(idNouveauProprietaire);
        
        //on change le proprietaire de cette carte
        c.setJoueur(joueur);
        joueur.getCartes().add(c);
 
        
        //on met a jour la carte a l'aide du dao
        dao.save(c);

       
    }

    public long carteIdAleatoireChezUnJoueur(long idJoueur) {
        //on recupere les cartes du joueur
        List<Carte> cartes= (List<Carte>) joueurDAOCrud.findOne(idJoueur);
        
        //on prend une carte aleatoire parmis ses cartes
       int indice = cartes.size();
       Random r = new Random();
        int indiceAleatoire = r.nextInt(indice);
        Carte c = cartes.get(indiceAleatoire);
       
        //on renvoie son id
        
        long idCarte = c.getId() ;
        
        return idCarte;
    }

    
    public void modifier(Carte carteAPrendre) {
        dao.save(carteAPrendre);
    }

}
