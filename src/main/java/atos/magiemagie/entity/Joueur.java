/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.magiemagie.entity;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Administrateur
 */
@Entity
public class Joueur implements Serializable {

    public enum EtatJoueur {
        N_A_PAS_LA_MAIN,
        A_LA_MAIN,
        SOMMEIL_PROFOND,
        PERDU,
        GAGNE
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Partie partie;

    @OneToMany(mappedBy = "joueur")
    List<Carte> cartes = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EtatJoueur etat;

    @Column(unique = true)
    private String pseudo;

    private String avatar;

    @Column(nullable = false)
    private long nbPartiesJouees;

    @Column(nullable = false)
    private long nbPartiesGagnees;

    @Column(nullable = false)
    private long odre;

    public long getOdre() {
        return odre;
    }

    public void setOdre(long odre) {
        this.odre = odre;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }

    public EtatJoueur getEtat() {
        return etat;
    }

    public void setEtat(EtatJoueur etat) {
        this.etat = etat;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getNbPartiesJouees() {
        return nbPartiesJouees;
    }

    public void setNbPartiesJouees(long nbPartiesJouees) {
        this.nbPartiesJouees = nbPartiesJouees;
    }

    public long getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    public void setNbPartiesGagnees(long nbPartiesGagnees) {
        this.nbPartiesGagnees = nbPartiesGagnees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Joueur)) {
            return false;
        }
        Joueur other = (Joueur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "magiemagie.Joueur[ id=" + id + " ]";
    }

}
