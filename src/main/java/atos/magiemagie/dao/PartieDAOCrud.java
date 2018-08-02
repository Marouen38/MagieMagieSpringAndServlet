/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;
import atos.magiemagie.entity.Joueur;
import atos.magiemagie.entity.Partie;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface PartieDAOCrud extends CrudRepository<Partie, Long>{
    
    @Query("SELECT p "
               + "FROM Partie p "
               + "EXCEPT "
               + "SELECT p "
               + "FROM Partie p "
               + "JOIN p.joueurs j "
               + "WHERE j.etat=atos.magiemagie.entity.Joueur.EtatJoueur.A_LA_MAIN OR j.etat=atos.magiemagie.entity.Joueur.EtatJoueur.GAGNE")       
    public List<Partie> findAllPartieNonDemarees();
    
     public void ajouter (Partie p);

    public List<Partie> listerPartieNonDemarees();
    
    public void findJoueurEtat(Joueur j);

    @Query("SELECT MAX(j.odre) FROM Joueur j JOIN j.partie p WHERE p.id=?1")
    public long ordreSuivant(long idPartie);

    @Query("SELECT j FROM Joueur j  ")
    public boolean countJoueurId(long partieId);
   
    
}
