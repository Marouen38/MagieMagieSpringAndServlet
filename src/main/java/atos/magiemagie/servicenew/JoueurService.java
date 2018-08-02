/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servicenew;

import atos.magiemagie.dao.CarteDAOCrud;
import atos.magiemagie.dao.JoueurDAOCrud;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.dao.PartieDAOCrud;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;



public class JoueurService {
    
    

    @Autowired
    private JoueurDAOCrud daojoueur;
    
    @Autowired
    private PartieDAOCrud daopartie ;
    
    @Autowired
    private CarteDAOCrud daocarte;
    
    
    
    public List<Joueur>  listerAutresJoueurDePartie(long joueurId,long partieId){
        
        
        return daojoueur.listerAutresJoueurDePartie(joueurId,partieId);
        
    } 

    public Joueur rejoindrePartie(String pseudo, String avatar, long idPartie) {
        //recherche si le joueur existe déjà
        Joueur joueur = daojoueur.findOneByPseudo(pseudo);

        if (joueur == null) {
            //le joueur n'existe pas 
            joueur = new Joueur();
            joueur.setPseudo(pseudo);

        }
        joueur.setAvatar(avatar);
        joueur.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
        joueur.setOdre(daopartie.ordreSuivant(idPartie));
        //Associe le joueur à la partie et vice versa(JPA relations bidirectionnels
        Partie partie = daopartie.findOne(idPartie);
        joueur.setPartie(partie);
        List<Joueur> listeJoueurs = partie.getJoueurs();
        listeJoueurs.add(joueur);
        if (joueur.getId() == null) {//nouveau
            daojoueur.save(joueur);
        } else { //existe déjà
            daojoueur.save(joueur);

        }
        return joueur;
    }

    public void demarerPartie(long idPartie) {

        //recherche la partie par son Id
        Partie p = daopartie.findOne(idPartie);

        //declencher un erreur s'il y'a moin de deux joueur
        /*
        if (p.getJoueurs().size() < 2) {
            throw new RuntimeException("la partie ne peut pas etre demmarer");
        }*/
        //passer le joueur d'odre 0 a l'etat A_LA_MAIN
        Joueur j = daojoueur.findOneByPartieIdAndOrdre(idPartie,0L);
        j.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
        daojoueur.save(j);

        //donner 7 cartes au hazard a chaque joueur
        for (Joueur jboucle : daojoueur.findAllByPartieId(idPartie)) {
            for (int i = 0; i < 7; i++) {
                ajouterCarte(jboucle, randomCarte());
            }

        }

    }

    public void piocherCarte(long idJoueur) {
        ajouterCarte(daojoueur.findOne(idJoueur), randomCarte());

    }

    public boolean joueurALesCartes(long idJoueur, Carte.TypeCarte type1, Carte.TypeCarte type2) {
        if( daocarte.countByJoueurIdAndJoueurCartesTypeCarte(idJoueur, type1)>=1 &&
            daocarte.countByJoueurIdAndJoueurCartesTypeCarte(idJoueur, type2)>=1){
            return true;
        }else{
            return false;
        }
    }

    public  Carte randomCarte() {

        Carte.TypeCarte[] tabTypesCartes = Carte.TypeCarte.values();

        Random r = new Random();
        int n = r.nextInt(tabTypesCartes.length);
        Carte c = new Carte();
        c.setTypeCarte(tabTypesCartes[n]);

        return c;

    }

    private void ajouterCarte(Joueur j, Carte c) {
        c.setJoueur(j);

        daocarte.save(c);

    }

    public Joueur joueurQuiALaMain(long idPartie) {
        return daojoueur.findOneByPartieIdAndEtat(idPartie,Joueur.EtatJoueur.A_LA_MAIN);
    }

    public List<Joueur> listerJoueur(long idPartie) {
        return daojoueur.findAllByPartieId(idPartie);
    }

    public Joueur rechercherParId(long cibleId) {

        return daojoueur.findOne(cibleId);

    }
    public Joueur ajouterJoueur(String pseudo,String avatar){
        Joueur joueur = new Joueur();
        joueur.setAvatar(avatar);
        joueur.setPseudo(pseudo);
        daojoueur.save(joueur);
        
        return joueur;
    }
    public void ajouterPartieIdAJoueur(Joueur joueur, long partieId){
        //recupere la partie via l'ID
        Partie partie = daopartie.findOne(partieId);
        //ajoute la partie au joueur
        joueur.setPartie(partie);
        //enrejister les modifications
        daojoueur.save(joueur);
        
    }
}


