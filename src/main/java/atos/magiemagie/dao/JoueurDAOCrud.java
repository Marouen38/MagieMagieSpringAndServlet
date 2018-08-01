/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.dao;

import atos.magiemagie.entity.Joueur;
import java.io.Serializable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;

/**
 *
 * @author Administrateur
 */
public interface JoueurDAOCrud extends CrudRepository<Joueur,Long>{
    
    //public long carteIdAleatoireChezUnJoueur(long idJoueur);
}
