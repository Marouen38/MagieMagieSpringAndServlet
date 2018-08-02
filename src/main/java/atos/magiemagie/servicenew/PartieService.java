/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.servicenew;

import atos.magiemagie.dao.JoueurDAOCrud;
import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import atos.magiemagie.dao.PartieDAOCrud;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrateur
 */
@Service
public class PartieService {
    
    @Autowired
    private PartieDAOCrud daoCrud;

    @Autowired
    private PartieDAOCrud partieDAO;
    
    @Autowired
    private JoueurDAOCrud joueurDAO;
    private JoueurService joueurService = new JoueurService();
    private CarteService carteService = new CarteService();

    
    
    
    public void sortInvisibilite(Joueur joueurMain, long idpartie) {
        if (joueurService.joueurALesCartes(joueurMain.getId(), Carte.TypeCarte.CORNE_LICORNE, Carte.TypeCarte.BAVE_CRAPAUD)) {
            //on recupere la liste des autres joueurs
            List<Joueur> joueurs = joueurService.listerJoueur(idpartie);
            //on trouve une carte aleatoire pour chaque joueur
            for (Joueur joueur : joueurs) {
                List<Carte> cartesact = joueur.getCartes();
                int taille = cartesact.size();

                Random random = new Random();

                int indiceAleat = random.nextInt(taille);

                Carte carteAPrendre = cartesact.get(indiceAleat);

                joueur.getCartes().remove(indiceAleat);

                joueurMain.getCartes().add(carteAPrendre);

                carteAPrendre.setJoueur(joueurMain);

                carteService.modifier(carteAPrendre);

            }
            //le nouveau propietaire de toutes ces cartes c'est nous

        } else {
            System.out.println("Vous n'avez pas les cartes");
        }
    }

    public void sortPhiltreDAmour(Joueur joueurMain, long cibleId) {

        //recupere la cible a partir de ID
        System.out.print("entrez l'id de la cible: ");
        Joueur cible = joueurService.rechercherParId(cibleId);
        Carte carteARecuperer;

        //determiner le nbre de carte à prendre
        int nbreCartes = cible.getCartes().size();

        for (int i = 0; i < nbreCartes; i++) {

            carteARecuperer = cible.getCartes().get(i);
            if (i % 2 == 0) {
                System.out.println("le nombre de Cartes à recuperer est " + nbreCartes / 2);
            }
            System.out.println("le nombre de Cartes à recuperer est " + nbreCartes / 2 + 1);
            joueurMain.getCartes().add(carteARecuperer);

        }

    }

    public void sortHypnose(long idpartie, Joueur joueurMain, long idVictime) {
        if (joueurService.joueurALesCartes(joueurMain.getId(), Carte.TypeCarte.LAPIS_LAZULI, Carte.TypeCarte.BAVE_CRAPAUD)) {
            System.out.println("Vous lancez HYPNOSE");

            //choisir une victime
            Joueur victime = joueurService.rechercherParId(idVictime);

            // donner une carte au choix  à cette victime
            Random random = new Random();

            int indiceAleat = random.nextInt();

            Carte carteAuChoix = new Carte();

            victime.getCartes().add(carteAuChoix);

            //prendre 3 cartes au hazard de cette victime
            int nbreCartes = victime.getCartes().size() / 2;
            for (int i = 0; i < 3; i++) {
                Carte carteAuHazard = new Carte();

                carteAuHazard = victime.getCartes().get(i);

                joueurMain.getCartes().add(carteAuHazard);

                int indiceAlea = random.nextInt();

                Carte carteAPrendre = joueurMain.getCartes().get(indiceAlea);

                victime.getCartes().remove(indiceAleat);

                joueurMain.getCartes().add(carteAPrendre);

                carteAPrendre.setJoueur(joueurMain);

                carteService.modifier(carteAPrendre);

                //passer au joueur suivant
                passeJoueurSuivant(idpartie);

            }
        }

    }

    public void sortDivination(Joueur joueurMain) {
        if (joueurService.joueurALesCartes(joueurMain.getId(), Carte.TypeCarte.LAPIS_LAZULI, Carte.TypeCarte.CHAUVE_SOURIS)) {
            System.out.println("Vous lancez DIVINATION");
            for (Joueur j1 : autresJoueurs(joueurMain.getId())) {
                System.out.println("Le joueur " + j1.getPseudo() + " a les cartes");
                for (Carte c : carteService.listerCartesParJoueurId(j1.getId())) {
                    System.out.println(c.getTypeCarte());
                    

                }
            }
        } 
        else 
        {
            System.out.println("Vous n'avez pas les cartes");
        }

    }

    /**
     * changer l'etat d'une victime vers sommeil profond
     * 
     * @param idVictime 
     */
    public void sortSommeilProfond(long idVictime) {

        //choisir une victime 
        Joueur victime = joueurService.rechercherParId(idVictime);
        
        //une victime qui ne pourra pas lancer de sorts pendant 1 tours
        
        joueurDAO.findOne(victime.getId()).setEtat(Joueur.EtatJoueur.SOMMEIL_PROFOND);
   
        joueurDAO.save(victime);
        
        
        
    }

    public void passeJoueurSuivant(long partieId) {

        //1- recuperer joueur qui a la main = joueurQuiALaMain
        Joueur joueurQuiALaMain = joueurDAO.findOneByPartieIdAndEtat(partieId,Joueur.EtatJoueur.A_LA_MAIN);

        //2- Determine si tous autres joueurs ont perdu
        
        
        //3-et passe le joueur a l'etat gagné si c'est le cas 
        
        
        //4-puis quitte la fonction
        
        
        //4-une autre solution  
        
        //if (servPartie.finPartie(partieId))
        
        if (partieDAO.countJoueurId(partieId)) {
            joueurQuiALaMain.setEtat(Joueur.EtatJoueur.GAGNE);
            joueurDAO.save(joueurQuiALaMain);
            return;
        }
        //  else {
        
        //la partie n'est pas fini
        
        //3-recuperer ordre Max des joueurs de la partie
        long ordreMax = joueurDAO.rechercheOrdreMaxJoueurPourPartieId(partieId);
        //joueurEvalue= joueurQuiAlaMain
        Joueur joueurEvalue = joueurQuiALaMain;
        
        //long ordreProchain;
        
        // boolean prochainNonTrouve = true;
        while (true) {

            //si joueurEvalue est le dernier joueur alors on evalue
            if (joueurEvalue.getOdre() >= ordreMax) {
                //ordreProchain = 1;
                joueurEvalue = joueurDAO.findOneByPartieIdAndOrdre(partieId, 1L);
            } else {
                //ordreProchain = joueurQuiALaMain.getOrdre() + 1;
                joueurEvalue = joueurDAO.findOneByPartieIdAndOrdre(partieId, joueurEvalue.getOdre() + 1);
            }
            //Joueur prochain = joueurDAO.recupererJoueurProchain(partieId, ordreProchain);

            //Return si tout les joueurs non étiminés etaient en sommeil profond et q'on la  a juste réveillés
            if (joueurEvalue.getId() == joueurQuiALaMain.getId()) {
                return;
            }
            if (joueurEvalue.getEtat() == Joueur.EtatJoueur.SOMMEIL_PROFOND) {
                joueurEvalue.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                joueurDAO.save(joueurEvalue);
                //prochainNonTrouve = true;
                //si joueurEvalue pas la main alors c'est lui qui prend la main
            } else if (joueurEvalue.getEtat() == Joueur.EtatJoueur.N_A_PAS_LA_MAIN) {
                joueurEvalue.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
                joueurDAO.save(joueurEvalue);
                joueurQuiALaMain.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                joueurDAO.save(joueurQuiALaMain);
                // prochainNonTrouve = false;
                return;
            }
        }
       
    }

    /*public void passeJoueurSuivant(long idPartie){
        Joueur joueurQuiALaMain = joueurDAO.rechercherJOueurOrdre0ParPartieId(idPartie);
        
   
    
    if(joueurDAO.determineSiPlusQueJoueurDansPartie(idPartie)){
        joueurQuiALaMain.setEtat(Joueur.EtatJoueur.GAGNE);
        
        joueurDAO.modifier(joueurQuiALaMain);
        
        return;
    }
        long ordreMax=joueurDAO.rechercheOrdreMaxJoueurPourPartieId(idPartie);
        Joueur joueurEvalue = joueurQuiALaMain;
        
        while (true){//boucle qui permet de determiner le joueur qui 'attrape' la main
            if (joueurEvalue.getOdre()>=ordreMax){
                //si joueur evalue est le dernier joueur alors on evaluera le premier
                joueurEvalue=joueurDAO.rechercheJoueurParOrdreEtParId(idPartie,1L);
            }
            
            else{joueurEvalue=joueurDAO.rechercheJoueurParOrdreEtParId(idPartie, joueurEvalue.getOdre()+1);
                
            }
            //Si joueur evalue en sommeil Profond son etat passe a pas la main
            
            if(joueurEvalue.getEtat()==Joueur.EtatJoueur.SOMMEIL_PROFOND){
                joueurEvalue.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
                joueurDAO.modifier(joueurEvalue);
            }
           else{
            if(joueurEvalue.getEtat()==Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
            {joueurQuiALaMain.setEtat(Joueur.EtatJoueur.N_A_PAS_LA_MAIN);
            joueurDAO.modifier(joueurQuiALaMain);
            
            joueurEvalue.setEtat(Joueur.EtatJoueur.A_LA_MAIN);
        
            
             
            }
                        
                        }
            }
        }*/
    public Partie creerNouvellePartie(String nom) {
        Partie p = new Partie();
        p.setNom(nom);
        partieDAO.ajouter(p);
        return p;

        /**
         * LISTE DES PARTIE DONT AUCUN JOUEUR N'EST à L'ETAT A_LA_MAIN
         *
         */
    }

    public List<Partie> listerPartieNonDemarees() {

        return daoCrud.listerPartieNonDemarees();

    }

    /**
     *
     * @return
     */
    public List<Joueur> autresJoueurs(long joueurId) {
        Joueur joueur = joueurDAO.findOne(joueurId);
        Partie partie = joueur.getPartie();
        List<Joueur> joueurs = joueurDAO.findAllByPartieId(partie.getId());
        for (Joueur joueur1 : joueurs) {
            if (joueur == joueur1) {
                joueurs.remove(joueur1);
                
            }
        }
        return joueurs;
        
    }
    
    
    
}
