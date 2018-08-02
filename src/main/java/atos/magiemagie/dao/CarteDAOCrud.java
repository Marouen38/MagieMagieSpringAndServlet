/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Carte;
import atos.magiemagie.entity.Joueur;
import java.io.Serializable;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Administrateur
 */
public interface CarteDAOCrud extends CrudRepository<Carte,Long>{


    //@Query("SELECT c from Carte c join c.joueur j where j.id =idJoueur")

     // private List<Carte> findAllJoueurId(long idJoueur);
//   


    
//     public void saveCarte(Carte c );
//   
//     public Carte findCarteById(long idCarte);
//    
    

    //public boolean existsCarteJoueur(Long idjoueur, Carte.TypeCarte type1, Carte.TypeCarte type2);
    
     //public void save(Carte carteAPrendre);

    public List<Carte> findAllByJoueurId(long joueurId);

    public int countByJoueurIdAndJoueurCartesTypeCarte(long idJoueur, Carte.TypeCarte type1);
 
    
}
