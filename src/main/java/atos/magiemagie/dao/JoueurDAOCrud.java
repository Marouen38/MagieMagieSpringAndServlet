/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Administrateur
 */
public interface JoueurDAOCrud extends CrudRepository<Joueur,Long>{
    
    public long carteIdAleatoireChezUnJoueur(long idJoueur);
    
    @Query("SELECT j FROM Joueur j join j.partie p where p.id :partieId EXCEPT SELECT j FROM Joueur j JOIN j.partie p WHERE j.id :monId")
    public  List<Joueur> findAllPartieId(long monId, long partieId);
    public Joueur findOneJoueurById(long idJoueur);

    /**
     *
     * @param joueurId
     * @param partieId
     * @return
     */
    @Query("SELECT j FROM Joueur j JOIN j.partie p WHERE p.id=?2 AND j.id<>?1")
    public List<Joueur> listerAutresJoueurDePartie(long joueurId, long partieId);

    /**
     *
     * @param pseudo
     * @return
     */
    public Joueur findOneByPseudo(String pseudo);

    /**
     *
     * @param idPartie
     * @param l
     * @return
     */
    public Joueur findOneByPartieIdAndOrdre(long idPartie, long l);

    /**
     *
     * @param idPartie
     * @param etatJoueur
     * @return
     */
    public Joueur findOneByPartieIdAndEtat(long idPartie, Joueur.EtatJoueur etatJoueur);

    /**
     *
     * @param idPartie
     * @return
     */
    public List<Joueur> findAllByPartieId(long idPartie);
    
    /**
     *
     * @param partieId
     * @return
     */
    @Query("SELECT MAX(j.ordre) FROM Joueur j JOIN j.partie p WHERE p.id=?1")
    public long rechercheOrdreMaxJoueurPourPartieId(long partieId);
}
